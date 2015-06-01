package mrwint.gbtasgen.util;
import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.movie.BizhawkMovie;
import mrwint.gbtasgen.rom.RomInfo;
import mrwint.gbtasgen.segment.DualGbSegment;
import mrwint.gbtasgen.state.DualGbHelper;
import mrwint.gbtasgen.state.DualGbState;
import mrwint.gbtasgen.state.Gameboy;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.state.StateBuffer;



public class DualGbRunner {

  public static void run(RomInfo romL, RomInfo romR, DualGbSegment segment) {

    Gb.loadGambatte(2);

    // load roms
    Gameboy gbL = new Gameboy(romL, 0, true);
    Gameboy gbR = new Gameboy(romR, 1, true);
    DualGbHelper.initDualGb(gbL, gbR);

    // init inital state
    StateBuffer initalBufferL = new StateBuffer();
    initalBufferL.addState(gbL.createState());
    StateBuffer initalBufferR = new StateBuffer();
    initalBufferR.addState(gbR.createState());
    DualGbState state = new DualGbState(gbL, gbR, initalBufferL, initalBufferR);

    long start = System.currentTimeMillis();

    // execute
    state = segment.execute(state);

    // save movie
    BizhawkMovie.exportMovie(state, "test");

    printStepCounts(state);

    System.out.println("Time: " +(System.currentTimeMillis()-start));
    System.out.println("Re-records: "+gbL.rerecordCount + " + " + gbR.rerecordCount);

    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {}

    for(State s : state.stateBufferL.getStates()) {
      gbL.restore(s);
      gbL.steps(1000);
    }
    for(State s : state.stateBufferR.getStates()) {
      gbR.restore(s);
      gbR.steps(1000);
    }

    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {}
  }

	public static void printStepCounts(DualGbState state) {
		System.out.println("size: "+state.stateBufferL.size() + " + " + state.stateBufferR.size());
    int minStepCountL = Integer.MAX_VALUE;
    int maxStepCountL = Integer.MIN_VALUE;
    int minDelayStepCountL = Integer.MAX_VALUE;
    int maxDelayStepCountL = Integer.MIN_VALUE;
    for(State s : state.stateBufferL.getStates()) {
      if (minStepCountL > s.stepCount) {
        minStepCountL = s.stepCount;
        minDelayStepCountL = s.delayStepCount;
      }
      if (maxStepCountL < s.stepCount) {
        maxStepCountL = s.stepCount;
        maxDelayStepCountL = s.delayStepCount;
      }
      if(s.stepCount < state.gbL.stepCount)
        state.gbL.restore(s);
    }
    int minStepCountR = Integer.MAX_VALUE;
    int maxStepCountR = Integer.MIN_VALUE;
    int minDelayStepCountR = Integer.MAX_VALUE;
    int maxDelayStepCountR = Integer.MIN_VALUE;
    for(State s : state.stateBufferR.getStates()) {
      if (minStepCountR > s.stepCount) {
        minStepCountR = s.stepCount;
        minDelayStepCountR = s.delayStepCount;
      }
      if (maxStepCountR < s.stepCount) {
        maxStepCountR = s.stepCount;
        maxDelayStepCountR = s.delayStepCount;
      }
      if(s.stepCount < state.gbR.stepCount)
        state.gbR.restore(s);
    }
		System.out.println("min: " + minStepCountL + "(-" + minDelayStepCountL + ") + " + minStepCountR + "(-" + minDelayStepCountR
		    + "), max: " + maxStepCountL + "(-" + maxDelayStepCountL + ") + " + maxStepCountR + "(-" + maxDelayStepCountR + ")");
	}
}
