package mrwint.gbtasgen.move.pokemon.gen1;

import static mrwint.gbtasgen.state.Gameboy.curGb;
import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.util.EflUtil;
import mrwint.gbtasgen.util.EflUtil.PressMetric;
import mrwint.gbtasgen.util.Util;

public class EflWalkStep extends Move {

	private int dir;
	private boolean check;
	private boolean skipStandStillTest;
	private boolean avoidEncounters = true;

	public EflWalkStep(int dir, boolean check) {
		this(dir,check,false);
	}

	@Override
	public int getInitialKey() {
		return dir;
	}

	public EflWalkStep(int dir, boolean check, boolean skipStandStillTest) {
    EflUtil.assertEfl();

    this.dir = dir;
		this.check = check;
		this.skipStandStillTest = skipStandStillTest;
	}

	public static void runToNextWalkFrame(int dir) {
		int startSteps = curGb.currentStepCount;
		// forward to first possible input frame
		while(true) {
//      Util.runToFirstDifference(0, Move.UP, Metric.DOWN_JOY); // for bike road
      EflUtil.runToNextInputFrameForMetricNoLimit(Move.UP, PressMetric.DOWN);
//      System.out.println("runToNextWalkFrame steps: " + curGb.currentStepCount);
      State s = curGb.newState();
      int add = curGb.step(Move.UP, curGb.rom.readJoypadInputHi);
      if (add != curGb.rom.readJoypadInputHi)
        throw new RuntimeException("runToNextInputFrame returned invalid input frame!");
      EflUtil.runToAddressNoLimit(0, 0, curGb.pokemon.readJoypadAddress); // go to actual joypad input
      add = EflUtil.runToAddressNoLimit(0, 0, curGb.pokemon.doPlayerMovementFuncAddress, curGb.rom.readJoypadInputHi);
      curGb.restore(s);
      if (add == curGb.pokemon.doPlayerMovementFuncAddress) { // move handled before next VBlank;
        break;
      }
      System.out.println("INFO: WalkStep: found non-walk input frame ("+(curGb.currentStepCount - startSteps)+", add: "+Util.toHex(add)+")");
      curGb.step(); // skip unsuitable input frame
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

				if(newDirection != oldDirection) { // need to perform turn, which needs an additional step
					if(!avoidEncounters) {
					  curGb.step(dir); // perform turn; two frame delay so ending frame is ok
					} else {
						while(true) {
							State s = curGb.newState();
							int add = EflUtil.runToAddressLimit(0, dir, 100, curGb.pokemon.doTurnPreEncounterCheckAddress);
							if(add == 0)
								System.out.println("ERROR: didn't find doTurnPreEncounterCheckAddress");
							add = EflUtil.runToAddressLimit(0, 0, 100, curGb.pokemon.doTurnPostEncounterCheckAddress, curGb.pokemon.encounterCheckMainFuncEncounterAddress);
              if(add == 0)
                System.out.println("ERROR: didn't find doTurnPostEncounterCheckAddress or encounterCheckMainFuncEncounterAddress");
							if(add == curGb.pokemon.doTurnPostEncounterCheckAddress) {
	              curGb.restore(s);
	              curGb.step(dir); // perform turn; two frame delay so ending frame is ok
								break;
							}
							curGb.restore(s);
							System.out.println("prepareMovement: avoiding encounter ("+(curGb.currentStepCount - startSteps)+")");
							curGb.step(); // wait one more frame; two frame delay so ending frame is ok
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
				int add = EflUtil.runToAddressLimit(0, dir, 100, curGb.pokemon.walkSuccessAddress, curGb.pokemon.walkFailAddress);
        if(add == 0)
          System.out.println("ERROR: didn't find walkSuccessAddress or walkFailAddress");
				if(add != curGb.pokemon.walkSuccessAddress) { // test if we are in the walk animation
					System.err.println("moving failed ("+(curGb.currentStepCount - startSteps)+")");
					if((curGb.currentStepCount - startSteps) > 20) {
						System.out.println("moving failed too often, giving up!");
						return false;
					}
					curGb.restore(s);
					curGb.step(); // wait frame; two frame delay so ending frame is ok
					prepareMovement(); // find next walk frame
					continue;
				}
//				curGb.step(); // finish frame
				if(avoidEncounters) {
					State finished = curGb.newState();
					add = EflUtil.runToAddressLimit(0, 0, 100, curGb.pokemon.doWalkPreEncounterCheckAddress);
	        if(add == 0)
	          System.out.println("ERROR: didn't find doWalkPreEncounterCheckAddress");
	        else {
  					add = EflUtil.runToAddressLimit(0,dir, 100, curGb.pokemon.doWalkPostEncounterCheckAddress, curGb.pokemon.encounterCheckMainFuncEncounterAddress);
            if(add == 0)
              System.out.println("ERROR: didn't find doWalkPostEncounterCheckAddress or encounterCheckMainFuncEncounterAddress");
  					if(add == curGb.pokemon.doWalkPostEncounterCheckAddress) {
  					  curGb.restore(finished);
  						break;
  					}
	        }
					curGb.restore(s);
					System.out.println("WalkStep: avoiding encounter ("+(curGb.currentStepCount - startSteps)+")");
					curGb.step(); // wait one more frame; two frame delay so ending frame is ok
					prepareMovement(); // find next walk frame
					continue;
				} else
					break;
			}
		}
		return true;
	}
}
