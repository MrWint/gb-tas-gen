package mrwint.gbtasgen.util;
import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.movie.BizhawkMovie;
import mrwint.gbtasgen.movie.GbMovie;
import mrwint.gbtasgen.movie.LsmvMovie;
import mrwint.gbtasgen.rom.RomInfo;
import mrwint.gbtasgen.segment.SingleGbSegment;
import mrwint.gbtasgen.state.Gameboy;
import mrwint.gbtasgen.state.SingleGbState;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.state.StateBuffer;


/**
 * Utility for initializing and running a single Gameboy instance.
 */
public class SingleGbRunner {

  public static void run(RomInfo rom, SingleGbSegment segment) {
    run(rom, segment, false);
  }
  public static void run(RomInfo rom, SingleGbSegment segment, boolean equalLengthFrames) {

    // Initialize Gambatte with 1 screen.
    Gb.loadGambatte(1);

    // load rom
    Gameboy gb = new Gameboy(rom, 0, equalLengthFrames);

    // init inital state
    StateBuffer initalBuffer = new StateBuffer();
    initalBuffer.addState(gb.createState());
    SingleGbState state = new SingleGbState(gb, initalBuffer);

    long start = System.currentTimeMillis();

    // execute
    state = segment.execute(state);

    // save movie
    BizhawkMovie.exportMovie(state, "test");
    LsmvMovie.exportMovie(GbMovie.fromSingleGbState(state), "test");

    printStepCounts(state);

    System.out.println("Time: " +(System.currentTimeMillis()-start));
    System.out.println("Re-records: "+gb.rerecordCount);

    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {}

    for(State s : state.stateBuffer.getStates()) {
      gb.restore(s);
      gb.steps(1000);
    }

    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {}
  }

	public static void printStepCounts(SingleGbState state) {
		System.out.println("size: "+state.stateBuffer.size());
		int minStepCount = Integer.MAX_VALUE;
		int maxStepCount = Integer.MIN_VALUE;
    int minDelayStepCount = Integer.MAX_VALUE;
    int maxDelayStepCount = Integer.MIN_VALUE;
		for(State s : state.stateBuffer.getStates()) {
      if (minStepCount > s.stepCount) {
        minStepCount = s.stepCount;
        minDelayStepCount = s.delayStepCount;
      }
      if (maxStepCount < s.stepCount) {
        maxStepCount = s.stepCount;
        maxDelayStepCount = s.delayStepCount;
      }
			if(s.stepCount < state.gb.stepCount)
			  state.gb.restore(s);
		}
		System.out.println("min: " + minStepCount + "(-" + minDelayStepCount + "), max: " + maxStepCount + "(-" + maxDelayStepCount + ")");
	}
}
