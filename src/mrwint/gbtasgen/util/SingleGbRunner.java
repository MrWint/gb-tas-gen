package mrwint.gbtasgen.util;
import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.movie.BizhawkMovie;
import mrwint.gbtasgen.rom.RomInfo;
import mrwint.gbtasgen.segment.SingleGbSegment;
import mrwint.gbtasgen.state.Gameboy;
import mrwint.gbtasgen.state.SingleGbState;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.state.StateBuffer;



public class SingleGbRunner {

  public static void run(RomInfo rom, SingleGbSegment segment) {

    Gb.loadGambatte(2);

    // load rom
    Gameboy gb = new Gameboy(rom, 0);

    // init inital state
    StateBuffer initalBuffer = new StateBuffer();
    initalBuffer.addState(gb.createState());
    SingleGbState state = new SingleGbState(gb, initalBuffer);

    long start = System.currentTimeMillis();

    // execute
    state = segment.execute(state);

    // save movie
    BizhawkMovie.saveMovie(gb, state.stateBuffer.getStates().iterator().next(), "test");

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
		for(State s : state.stateBuffer.getStates()) {
			minStepCount = Math.min(minStepCount, s.stepCount);
			maxStepCount = Math.max(maxStepCount, s.stepCount);
			if(s.stepCount < state.gb.currentStepCount)
			  state.gb.restore(s);
		}
		System.out.println("min: " + minStepCount + ", max: " + maxStepCount);
	}
}
