package mrwint.gbtasgen.move.pokemon.gen1;

import static mrwint.gbtasgen.state.Gameboy.curGb;
import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.move.Move;
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
		int startSteps = curGb.currentStepCount;
		// forward to first possible input frame
		while(true) {
      Util.runToFirstDifference(0, Move.UP, Metric.DOWN_JOY); // for bike road
//      Util.runToFirstDifference(0, dir, Metric.DOWN_JOY);
			State s = curGb.newState();
			int add = curGb.step(dir, curGb.pokemon.doPlayerMovementFuncAddress);//, 0x700); // .handleDirectionButtonPress
			curGb.restore(s);
			if(add != 0) {
				//System.out.println("found walk input frame ("+steps+")");
//				if(add == 0x700) {
//					System.out.println("INFO: WalkStep: warp found at ("+steps+")");
//					skipStandStillTest = true;
//					check = false;
//				}
				break;
			}
			System.out.println("INFO: WalkStep: found non-walk input frame ("+(curGb.currentStepCount - startSteps)+")");
			curGb.step();
		}
	}

	public void prepareMovement() {
		int startSteps = curGb.currentStepCount;
		runToNextWalkFrame(dir);
		if(!skipStandStillTest) {
			int standStill = curGb.readMemory(curGb.pokemon.playerMovingIndicatorAddress);
			if(standStill != 0) {
				int oldDirection = curGb.readMemory(curGb.pokemon.playerDirectionAddress);
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
					  curGb.step(dir); // perform turn
					} else {
						while(true) {
							State s = curGb.newState();
							int add = curGb.step(dir, curGb.pokemon.doTurnPreEncounterCheckAddress);
							if(add != curGb.pokemon.doTurnPreEncounterCheckAddress)
								System.out.println("ERROR: didn't find doTurnPreEncounterCheckAddress");
							add = Util.runToAddressNoLimit(0, dir, curGb.pokemon.doTurnPostEncounterCheckAddress, curGb.pokemon.encounterCheckMainFuncEncounterAddress);
							if(add == curGb.pokemon.doTurnPostEncounterCheckAddress) {
							  curGb.step(); // finish frame;
								break;
							}
							curGb.restore(s);
							System.out.println("prepareMovement: avoiding encounter ("+(curGb.currentStepCount - startSteps)+")");
							curGb.step(); // wait one more frame
							runToNextWalkFrame(dir); // find next walk frame
						}
					}
					runToNextWalkFrame(dir); // find next walk frame
					if(curGb.readMemory(curGb.pokemon.playerMovingIndicatorAddress) != 0)
						throw new RuntimeException("standStill not fixed: "+curGb.readMemory(curGb.pokemon.playerMovingIndicatorAddress));
				}
			}
		}
	}

	@Override
	public boolean doMove() {

		int startSteps = curGb.currentStepCount;
		prepareMovement();

		while(true) {
			if(!check) {
			  curGb.step(dir); // walk
				break;
			} else {
				State s = curGb.newState();
				int add = Util.runToAddressNoLimit(0, dir, curGb.pokemon.walkSuccessAddress, curGb.pokemon.walkFailAddress);
				if(add != curGb.pokemon.walkSuccessAddress) { // test if we are in the walk animation
					System.err.println("moving failed ("+(curGb.currentStepCount - startSteps)+")");
					if((curGb.currentStepCount - startSteps) > 20) {
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
					Util.runToAddressNoLimit(0, 0, curGb.pokemon.doWalkPreEncounterCheckAddress);
					add = Util.runToAddressNoLimit(0,dir, curGb.pokemon.doWalkPostEncounterCheckAddress, curGb.pokemon.encounterCheckMainFuncEncounterAddress);
					if(add == curGb.pokemon.doWalkPostEncounterCheckAddress) {
					  curGb.restore(finished);
						break;
					}
					curGb.restore(s);
					System.out.println("WalkStep: avoiding encounter ("+(curGb.currentStepCount - startSteps)+")");
					curGb.step(); // wait one more frame
					prepareMovement(); // find next walk frame
					continue;
				} else
					break;
			}
		}
		return true;
	}
}
