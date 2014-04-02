package mrwint.gbtasgen.move;

import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.main.RomInfo;
import mrwint.gbtasgen.metric.Metric;
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
	
	public int runToNextWalkFrame(int excuseFrames) throws CapturedByRotatoException {
		int steps = 0;
		// forward to first possible input frame
		while(true) {
			steps += Util.runToFirstDifference(0, dir, Metric.DOWN_JOY);
			State s = new State();
			
			int add2 = Util.runToAddress2(0, dir, RomInfo.rom.doPlayerMovementFuncAddress, RomInfo.rom.printLetterDelayJoypadAddress);
			if(add2 == RomInfo.rom.printLetterDelayJoypadAddress)
				throw new CapturedByRotatoException();
			s.restore();
			
			int add = State.step(dir, RomInfo.rom.doPlayerMovementFuncAddress); // .handleDirectionButtonPress
			s.restore();
			if(add != 0) {
				//System.out.println("found walk input frame ("+steps+")");
				break;
			}
			if(excuseFrames-- <= 0)
				System.out.println("INFO: WalkStep: found non-walk input frame ("+steps+")");
			State.step();
			steps++;
		}
		return steps;
	}
	
	public int prepareMovement() throws CapturedByRotatoException {
		int steps = 0;
		steps += runToNextWalkFrame(0);
		if(!skipStandStillTest) {
			int standStill = Gb.readMemory(RomInfo.rom.playerMovingIndicatorAddress) == 0 ? 1 : 0;
			if(standStill != 0) {
				int oldDirection = Gb.readMemory(RomInfo.rom.playerDirectionAddress);
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
						++steps;
						State.step(dir); // perform turn
					} else {
						while(true) {
							++steps;
							State s = new State();
							//int add = State.step(dir, RomInfo.rom.encounterCheckMainFuncAddress);
							int add2 = Util.runToAddress2(0,dir, RomInfo.rom.encounterCheckMainFuncAddress, RomInfo.rom.printLetterDelayJoypadAddress);
							if(add2 == RomInfo.rom.printLetterDelayJoypadAddress)
								throw new CapturedByRotatoException();
							//if(add != RomInfo.rom.encounterCheckMainFuncAddress)
							//	System.out.println("ERROR: didn't find 0x575");
							int add = Util.runToAddress2(0, dir, RomInfo.rom.encounterCheckMainFuncNoEncounterAddress, RomInfo.rom.encounterCheckMainFuncEncounterAddress);
							//add = State.step(dir, 0x578, 0x13916);
							if(add == RomInfo.rom.encounterCheckMainFuncNoEncounterAddress) {
								s.restore();
								State.step(dir); // finish frame;
								//System.out.println("found 0x578");
								break;
							}
							if(add != RomInfo.rom.encounterCheckMainFuncEncounterAddress)
								System.out.println("ERROR: didn't find 0x13916");
							s.restore();
							System.out.println("prepareMovement: avoiding encounter ("+steps+")");
							State.step(); // wait one more frame
							steps += runToNextWalkFrame(0); // find next walk frame
						}
					}
//					steps += Util.runToFirstDifference(0, dir, Metric.FFB4); // forward to next input frame
					steps += runToNextWalkFrame(3); // find next walk frame
					if(Gb.readMemory(RomInfo.rom.playerMovingIndicatorAddress) == 0)
						throw new RuntimeException("standStill not fixed: "+Gb.readMemory(RomInfo.rom.playerMovingIndicatorAddress));
				}
			}
		}
		return steps;
	}
	
	@Override
	public int execute() {

		try {
			int steps = prepareMovement();
	
			while(true) {
				++steps;
				if(!check) {
					State.step(dir); // walk
					break;
				} else {
					State s = new State();
					int add = Util.runToAddress2(0, dir, RomInfo.rom.doPlayerMovementFuncEndAddress);
					int moveAni = Gb.readMemory(RomInfo.rom.movementAnimationAddress);
					
					if((moveAni < 0x8 || moveAni > 0x13) && moveAni != 0x30) { // test if we are in the walk animation (or ledge jump)
						System.err.println("moving failed ("+steps+", moveAni = "+moveAni+")");
						if(steps > 100) {
							System.out.println("moving failed too often, giving up!");
							return -1;
						}
						s.restore();
						State.step(); // wait frame
						steps += prepareMovement(); // find next walk frame
						continue;
					}
					State.step(); // finish frame
					if(avoidEncounters) {
						State finished = new State();
						add = Util.runToAddress2(0, 0, RomInfo.rom.encounterCheckMainFuncAddress, RomInfo.rom.printLetterDelayJoypadAddress);
						if(add == RomInfo.rom.printLetterDelayJoypadAddress) {
							System.out.println("WalkStep: ran into rotato ("+steps+")");
							if(steps > 100) {
								System.out.println("ran into rotato too often, giving up!");
								return -1;
							}
							s.restore();
							State.step(); // wait one more frame
							steps += prepareMovement(); // find next walk frame					
						} else {
							add = Util.runToAddress2(0,dir, RomInfo.rom.encounterCheckMainFuncNoEncounterAddress, RomInfo.rom.encounterCheckMainFuncEncounterAddress);
							if(add == RomInfo.rom.encounterCheckMainFuncNoEncounterAddress) {
								finished.restore();
								//System.out.println("found 0x62f");
								break;
							}
							if(add != RomInfo.rom.encounterCheckMainFuncEncounterAddress)
								System.out.println("ERROR: didn't find 0x13916 (2)");
							s.restore();
							System.out.println("WalkStep: avoiding encounter ("+steps+")");
							State.step(); // wait one more frame
							steps += prepareMovement(); // find next walk frame					
						}
					} else
						break;
				}
			}
			return steps;
		} catch (CapturedByRotatoException e) {
			System.out.println("WalkStep: captured by rotato, giving up!");
			return -1;
		}
	}
}
