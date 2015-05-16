package mrwint.gbtasgen.move.pokemon.gen2;

import static mrwint.gbtasgen.state.Gameboy.curGb;
import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.move.Move;
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
		int startSteps = curGb.currentStepCount;
		// forward to first possible input frame
		while(true) {
			Util.runToFirstDifference(0, dir, Metric.DOWN_JOY);
			State s = curGb.newState();

			int add2 = Util.runToAddressNoLimit(0, dir, curGb.pokemon.doPlayerMovementFuncAddress, curGb.pokemon.printLetterDelayJoypadAddress);
			if(add2 == curGb.pokemon.printLetterDelayJoypadAddress)
				throw new CapturedByRotatoException();
			curGb.restore(s);

			int add = curGb.step(dir, curGb.pokemon.doPlayerMovementFuncAddress);
			curGb.restore(s);
			if(add != 0) {
				//System.out.println("found walk input frame ("+steps+")");
				break;
			}
			if(excuseFrames-- <= 0)
				System.out.println("INFO: WalkStep: found non-walk input frame ("+(curGb.currentStepCount - startSteps)+")");
			curGb.step();
		}
	}

	public void prepareMovement() throws CapturedByRotatoException {
		int startSteps = curGb.currentStepCount;
		runToNextWalkFrame(0);
		if(!skipStandStillTest) {
			int standStill = curGb.readMemory(curGb.pokemon.playerMovingIndicatorAddress) == 0 ? 1 : 0;
			if(standStill != 0) {
				int oldDirection = curGb.readMemory(curGb.pokemon.playerDirectionAddress);
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
					  curGb.step(dir); // perform turn
					} else {
						while(true) {
							State s = curGb.newState();
							//int add = State.step(dir, RomInfo.rom.encounterCheckMainFuncAddress);
							int add2 = Util.runToAddressNoLimit(0,dir, curGb.pokemon.encounterCheckMainFuncAddress, curGb.pokemon.printLetterDelayJoypadAddress);
							if(add2 == curGb.pokemon.printLetterDelayJoypadAddress)
								throw new CapturedByRotatoException();
							//if(add != RomInfo.rom.encounterCheckMainFuncAddress)
							//	System.out.println("ERROR: didn't find 0x575");
							int add = Util.runToAddressNoLimit(0, dir, curGb.pokemon.encounterCheckMainFuncNoEncounterAddress, curGb.pokemon.encounterCheckMainFuncEncounterAddress);
							//add = State.step(dir, 0x578, 0x13916);
							if(add == curGb.pokemon.encounterCheckMainFuncNoEncounterAddress) {
							  curGb.restore(s);
							  curGb.step(dir); // finish frame;
								//System.out.println("found 0x578");
								break;
							}
							if(add != curGb.pokemon.encounterCheckMainFuncEncounterAddress)
								System.out.println("ERROR: didn't find 0x13916");
							curGb.restore(s);
							System.out.println("prepareMovement: avoiding encounter ("+(curGb.currentStepCount - startSteps)+")");
							curGb.step(); // wait one more frame
							runToNextWalkFrame(0); // find next walk frame
						}
					}
//					steps += Util.runToFirstDifference(0, dir, Metric.FFB4); // forward to next input frame
					runToNextWalkFrame(3); // find next walk frame
					if(curGb.readMemory(curGb.pokemon.playerMovingIndicatorAddress) == 0)
						throw new RuntimeException("standStill not fixed: "+curGb.readMemory(curGb.pokemon.playerMovingIndicatorAddress));
				}
			}
		}
	}

	@Override
	public boolean doMove() {
		int startSteps = curGb.currentStepCount;

		try {
			prepareMovement();

			while(true) {
				if(!check) {
				  curGb.step(dir); // walk
					break;
				} else {
					State s = curGb.newState();
					int add = Util.runToAddressNoLimit(0, dir, curGb.pokemon.doPlayerMovementFuncEndAddress);
					int moveAni = curGb.readMemory(curGb.pokemon.movementAnimationAddress);

					if((moveAni < 0x8 || moveAni > 0x13) && moveAni != 0x30) { // test if we are in the walk animation (or ledge jump)
						System.err.println("moving failed ("+(curGb.currentStepCount - startSteps)+", moveAni = "+moveAni+")");
						if((curGb.currentStepCount - startSteps) > 100) {
							System.out.println("moving failed too often, giving up!");
							return false;
						}
						curGb.restore(s);
						curGb.step(); // wait frame
						prepareMovement(); // find next walk frame
						continue;
					}
					curGb.step(); // finish frame
					if(avoidEncounters) {
						State finished = curGb.newState();
						add = Util.runToAddressNoLimit(0, 0, curGb.pokemon.encounterCheckMainFuncAddress, curGb.pokemon.printLetterDelayJoypadAddress);
						if(add == curGb.pokemon.printLetterDelayJoypadAddress) {
							System.out.println("WalkStep: ran into rotato ("+(curGb.currentStepCount - startSteps)+")");
							if((curGb.currentStepCount - startSteps) > 100) {
								System.out.println("ran into rotato too often, giving up!");
								return false;
							}
							curGb.restore(s);
							curGb.step(); // wait one more frame
							prepareMovement(); // find next walk frame
						} else {
							add = Util.runToAddressNoLimit(0,dir, curGb.pokemon.encounterCheckMainFuncNoEncounterAddress, curGb.pokemon.encounterCheckMainFuncEncounterAddress);
							if(add == curGb.pokemon.encounterCheckMainFuncNoEncounterAddress) {
							  curGb.restore(finished);
								//System.out.println("found 0x62f");
								break;
							}
							if(add != curGb.pokemon.encounterCheckMainFuncEncounterAddress)
								System.out.println("ERROR: didn't find 0x13916 (2)");
							curGb.restore(s);
							System.out.println("WalkStep: avoiding encounter ("+(curGb.currentStepCount - startSteps)+")");
							curGb.step(); // wait one more frame
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
