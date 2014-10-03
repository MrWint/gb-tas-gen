package mrwint.gbtasgen.move.pokemon.gen2;

import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.rom.RomInfo;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.util.Util;

public class WalkStep extends Move {

	private int dir;
	private boolean check;
	private boolean skipStandStillTest;
	private boolean avoidEncounters;

	public WalkStep(int dir, boolean check) {
		this(dir,check,false);
	}

	public WalkStep(int dir, boolean check, boolean skipStandStillTest) {
		this.dir = dir;
		this.check = check;
		this.skipStandStillTest = skipStandStillTest;
		this.avoidEncounters = true;
	}

	@Override
	public int getInitialKey() {
		return dir;
	}

	public static class CapturedByRotatoException extends Exception {
		private static final long serialVersionUID = 3309466932402974508L;
	}

	public void runToNextWalkFrame(int excuseFrames) throws CapturedByRotatoException {
		int startSteps = State.currentStepCount;
		// forward to first possible input frame
		while(true) {
			Util.runToFirstDifference(0, dir, Metric.DOWN_JOY);
			State s = new State();

			int add2 = Util.runToAddressNoLimit(0, dir, RomInfo.pokemon.doPlayerMovementFuncAddress, RomInfo.pokemon.printLetterDelayJoypadAddress);
			if(add2 == RomInfo.pokemon.printLetterDelayJoypadAddress)
				throw new CapturedByRotatoException();
			s.restore();

			int add = State.step(dir, RomInfo.pokemon.doPlayerMovementFuncAddress);
			s.restore();
			if(add != 0) {
				//System.out.println("found walk input frame ("+steps+")");
				break;
			}
			if(excuseFrames-- <= 0)
				System.out.println("INFO: WalkStep: found non-walk input frame ("+(State.currentStepCount - startSteps)+")");
			State.step();
		}
	}

	public void prepareMovement() throws CapturedByRotatoException {
		int startSteps = State.currentStepCount;
		runToNextWalkFrame(0);
		if(!skipStandStillTest) {
			int standStill = Gb.readMemory(RomInfo.pokemon.playerMovingIndicatorAddress) == 0 ? 1 : 0;
			if(standStill != 0) {
				int oldDirection = Gb.readMemory(RomInfo.pokemon.playerDirectionAddress);
				int newDirection;
				if(dir == Move.LEFT)
					newDirection = 0x08;
				else if(dir == Move.RIGHT)
					newDirection = 0x0c;
				else if(dir == Move.UP)
					newDirection = 0x04;
				else if(dir == Move.DOWN)
					newDirection = 0x00;
				else
					throw new IllegalArgumentException();

				if(newDirection != oldDirection) { // need to perform turn, which needs and additional step
					if(!avoidEncounters) {
						State.step(dir); // perform turn
					} else {
						while(true) {
							State s = new State();
							//int add = State.step(dir, RomInfo.rom.encounterCheckMainFuncAddress);
							int add2 = Util.runToAddressNoLimit(0,dir, RomInfo.pokemon.encounterCheckMainFuncAddress, RomInfo.pokemon.printLetterDelayJoypadAddress);
							if(add2 == RomInfo.pokemon.printLetterDelayJoypadAddress)
								throw new CapturedByRotatoException();
							//if(add != RomInfo.rom.encounterCheckMainFuncAddress)
							//	System.out.println("ERROR: didn't find 0x575");
							int add = Util.runToAddressNoLimit(0, dir, RomInfo.pokemon.encounterCheckMainFuncNoEncounterAddress, RomInfo.pokemon.encounterCheckMainFuncEncounterAddress);
							//add = State.step(dir, 0x578, 0x13916);
							if(add == RomInfo.pokemon.encounterCheckMainFuncNoEncounterAddress) {
								s.restore();
								State.step(dir); // finish frame;
								//System.out.println("found 0x578");
								break;
							}
							if(add != RomInfo.pokemon.encounterCheckMainFuncEncounterAddress)
								System.out.println("ERROR: didn't find 0x13916");
							s.restore();
							System.out.println("prepareMovement: avoiding encounter ("+(State.currentStepCount - startSteps)+")");
							State.step(); // wait one more frame
							runToNextWalkFrame(0); // find next walk frame
						}
					}
//					steps += Util.runToFirstDifference(0, dir, Metric.FFB4); // forward to next input frame
					runToNextWalkFrame(3); // find next walk frame
					if(Gb.readMemory(RomInfo.pokemon.playerMovingIndicatorAddress) == 0)
						throw new RuntimeException("standStill not fixed: "+Gb.readMemory(RomInfo.pokemon.playerMovingIndicatorAddress));
				}
			}
		}
	}

	@Override
	public boolean doMove() {
		int startSteps = State.currentStepCount;

		try {
			prepareMovement();

			while(true) {
				if(!check) {
					State.step(dir); // walk
					break;
				} else {
					State s = new State();
					int add = Util.runToAddressNoLimit(0, dir, RomInfo.pokemon.doPlayerMovementFuncEndAddress);
					int moveAni = Gb.readMemory(RomInfo.pokemon.movementAnimationAddress);

					if((moveAni < 0x8 || moveAni > 0x13) && moveAni != 0x30) { // test if we are in the walk animation (or ledge jump)
						System.err.println("moving failed ("+(State.currentStepCount - startSteps)+", moveAni = "+moveAni+")");
						if((State.currentStepCount - startSteps) > 100) {
							System.out.println("moving failed too often, giving up!");
							return false;
						}
						s.restore();
						State.step(); // wait frame
						prepareMovement(); // find next walk frame
						continue;
					}
					State.step(); // finish frame
					if(avoidEncounters) {
						State finished = new State();
						add = Util.runToAddressNoLimit(0, 0, RomInfo.pokemon.encounterCheckMainFuncAddress, RomInfo.pokemon.printLetterDelayJoypadAddress);
						if(add == RomInfo.pokemon.printLetterDelayJoypadAddress) {
							System.out.println("WalkStep: ran into rotato ("+(State.currentStepCount - startSteps)+")");
							if((State.currentStepCount - startSteps) > 100) {
								System.out.println("ran into rotato too often, giving up!");
								return false;
							}
							s.restore();
							State.step(); // wait one more frame
							prepareMovement(); // find next walk frame
						} else {
							add = Util.runToAddressNoLimit(0,dir, RomInfo.pokemon.encounterCheckMainFuncNoEncounterAddress, RomInfo.pokemon.encounterCheckMainFuncEncounterAddress);
							if(add == RomInfo.pokemon.encounterCheckMainFuncNoEncounterAddress) {
								finished.restore();
								//System.out.println("found 0x62f");
								break;
							}
							if(add != RomInfo.pokemon.encounterCheckMainFuncEncounterAddress)
								System.out.println("ERROR: didn't find 0x13916 (2)");
							s.restore();
							System.out.println("WalkStep: avoiding encounter ("+(State.currentStepCount - startSteps)+")");
							State.step(); // wait one more frame
							prepareMovement(); // find next walk frame
						}
					} else
						break;
				}
			}
			return true;
		} catch (CapturedByRotatoException e) {
			System.out.println("WalkStep: captured by rotato, giving up!");
			return false;
		}
	}
}
