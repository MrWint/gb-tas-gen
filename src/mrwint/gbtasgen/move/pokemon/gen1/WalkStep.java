package mrwint.gbtasgen.move.pokemon.gen1;

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
	private boolean avoidEncounters = true;

	public WalkStep(int dir, boolean check) {
		this(dir,check,false);
	}

	@Override
	public int getInitialKey() {
		return dir;
	}

	public WalkStep(int dir, boolean check, boolean skipStandStillTest) {
		this.dir = dir;
		this.check = check;
		this.skipStandStillTest = skipStandStillTest;
	}

	public static void runToNextWalkFrame(int dir) {
		int startSteps = State.currentStepCount;
		// forward to first possible input frame
		while(true) {
			Util.runToFirstDifference(0, dir, Metric.DOWN_JOY);
			State s = new State();
			int add = State.step(dir, RomInfo.pokemon.doPlayerMovementFuncAddress);//, 0x700); // .handleDirectionButtonPress
			s.restore();
			if(add != 0) {
				//System.out.println("found walk input frame ("+steps+")");
//				if(add == 0x700) {
//					System.out.println("INFO: WalkStep: warp found at ("+steps+")");
//					skipStandStillTest = true;
//					check = false;
//				}
				break;
			}
			System.out.println("INFO: WalkStep: found non-walk input frame ("+(State.currentStepCount - startSteps)+")");
			State.step();
		}
	}

	public void prepareMovement() {
		int startSteps = State.currentStepCount;
		runToNextWalkFrame(dir);
		if(!skipStandStillTest) {
			int standStill = Gb.readMemory(RomInfo.pokemon.playerMovingIndicatorAddress);
			if(standStill != 0) {
				int oldDirection = Gb.readMemory(RomInfo.pokemon.playerDirectionAddress);
				int newDirection;
				if(dir == Move.LEFT)
					newDirection = 0x02;
				else if(dir == Move.RIGHT)
					newDirection = 0x01;
				else if(dir == Move.UP)
					newDirection = 0x08;
				else if(dir == Move.DOWN)
					newDirection = 0x04;
				else
					throw new IllegalArgumentException();

				if(newDirection != oldDirection) { // need to perform turn, which needs and additional step
					if(!avoidEncounters) {
						State.step(dir); // perform turn
					} else {
						while(true) {
							State s = new State();
							int add = State.step(dir, RomInfo.pokemon.doTurnPreEncounterCheckAddress);
							if(add != RomInfo.pokemon.doTurnPreEncounterCheckAddress)
								System.out.println("ERROR: didn't find doTurnPreEncounterCheckAddress");
							add = Util.runToAddressNoLimit(0, dir, RomInfo.pokemon.doTurnPostEncounterCheckAddress, RomInfo.pokemon.encounterCheckMainFuncEncounterAddress);
							if(add == RomInfo.pokemon.doTurnPostEncounterCheckAddress) {
								State.step(); // finish frame;
								break;
							}
							s.restore();
							System.out.println("prepareMovement: avoiding encounter ("+(State.currentStepCount - startSteps)+")");
							State.step(); // wait one more frame
							runToNextWalkFrame(dir); // find next walk frame
						}
					}
					runToNextWalkFrame(dir); // find next walk frame
					if(Gb.readMemory(RomInfo.pokemon.playerMovingIndicatorAddress) != 0)
						throw new RuntimeException("standStill not fixed: "+Gb.readMemory(RomInfo.pokemon.playerMovingIndicatorAddress));
				}
			}
		}
	}

	@Override
	public boolean doMove() {

		int startSteps = State.currentStepCount;
		prepareMovement();

		while(true) {
			if(!check) {
				State.step(dir); // walk
				break;
			} else {
				State s = new State();
				int add = Util.runToAddressNoLimit(0, dir, RomInfo.pokemon.walkSuccessAddress, RomInfo.pokemon.walkFailAddress);
				if(add != RomInfo.pokemon.walkSuccessAddress) { // test if we are in the walk animation
					System.err.println("moving failed ("+(State.currentStepCount - startSteps)+")");
					if((State.currentStepCount - startSteps) > 20) {
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
					Util.runToAddressNoLimit(0, 0, RomInfo.pokemon.doWalkPreEncounterCheckAddress);
					add = Util.runToAddressNoLimit(0,dir, RomInfo.pokemon.doWalkPostEncounterCheckAddress, RomInfo.pokemon.encounterCheckMainFuncEncounterAddress);
					if(add == RomInfo.pokemon.doWalkPostEncounterCheckAddress) {
						finished.restore();
						break;
					}
					s.restore();
					System.out.println("WalkStep: avoiding encounter ("+(State.currentStepCount - startSteps)+")");
					State.step(); // wait one more frame
					prepareMovement(); // find next walk frame
					continue;
				} else
					break;
			}
		}
		return true;
	}
}
