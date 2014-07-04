package mrwint.gbtasgen.move.gen1;

import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.main.RomInfo;
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
	
	public static int runToNextWalkFrame(int dir) {
		int steps = 0;
		// forward to first possible input frame
		while(true) {
			steps += Util.runToFirstDifference(0, dir, Metric.DOWN_JOY);
			State s = new State();
			int add = State.step(dir, RomInfo.rom.doPlayerMovementFuncAddress);//, 0x700); // .handleDirectionButtonPress
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
			System.out.println("INFO: WalkStep: found non-walk input frame ("+steps+")");
			State.step();
			steps++;
		}
		return steps;
	}
	
	public int prepareMovement() {
		int steps = 0;
		steps += runToNextWalkFrame(dir);
		if(!skipStandStillTest) {
			int standStill = Gb.readMemory(RomInfo.rom.playerMovingIndicatorAddress);
			if(standStill != 0) {
				int oldDirection = Gb.readMemory(RomInfo.rom.playerDirectionAddress);
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
						++steps;
						State.step(dir); // perform turn
					} else {
						while(true) {
							++steps;
							State s = new State();
							int add = State.step(dir, RomInfo.rom.doTurnPreEncounterCheckAddress);
							if(add != RomInfo.rom.doTurnPreEncounterCheckAddress)
								System.out.println("ERROR: didn't find doTurnPreEncounterCheckAddress");
							add = Util.runToAddress2(0, dir, RomInfo.rom.doTurnPostEncounterCheckAddress, RomInfo.rom.encounterCheckMainFuncEncounterAddress);
							if(add == RomInfo.rom.doTurnPostEncounterCheckAddress) {
								State.step(); // finish frame;
								break;
							}
							s.restore();
							System.out.println("prepareMovement: avoiding encounter ("+steps+")");
							State.step(); // wait one more frame
							steps += runToNextWalkFrame(dir); // find next walk frame
						}
					}
					steps += runToNextWalkFrame(dir); // find next walk frame
					if(Gb.readMemory(RomInfo.rom.playerMovingIndicatorAddress) != 0)
						throw new RuntimeException("standStill not fixed: "+Gb.readMemory(RomInfo.rom.playerMovingIndicatorAddress));
				}
			}
		}
		return steps;
	}
	
	@Override
	public boolean doMove() {

		int steps = prepareMovement();

		while(true) {
			++steps;
			if(!check) {
				State.step(dir); // walk
				break;
			} else {
				State s = new State();
				int add = Util.runToAddress2(0, dir, RomInfo.rom.walkSuccessAddress, RomInfo.rom.walkFailAddress);
				if(add != RomInfo.rom.walkSuccessAddress) { // test if we are in the walk animation
					System.err.println("moving failed ("+steps+")");
					if(steps > 20) {
						System.out.println("moving failed too often, giving up!");
						return false;
					}
					s.restore();
					State.step(); // wait frame
					steps += prepareMovement(); // find next walk frame
					continue;
				}
				State.step(); // finish frame
				if(avoidEncounters) {
					State finished = new State();
					Util.runToAddress(0, 0, RomInfo.rom.doWalkPreEncounterCheckAddress);
					add = Util.runToAddress2(0,dir, RomInfo.rom.doWalkPostEncounterCheckAddress, RomInfo.rom.encounterCheckMainFuncEncounterAddress);
					if(add == RomInfo.rom.doWalkPostEncounterCheckAddress) {
						finished.restore();
						break;
					}
					s.restore();
					System.out.println("WalkStep: avoiding encounter ("+steps+")");
					State.step(); // wait one more frame
					steps += prepareMovement(); // find next walk frame					
					continue;
				} else
					break;
			}
		}
		return true;
	}
}
