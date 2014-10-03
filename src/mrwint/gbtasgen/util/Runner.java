package mrwint.gbtasgen.util;
import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.movie.BizhawkMovie;
import mrwint.gbtasgen.rom.RomInfo;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.state.StateBuffer;



public class Runner {

	public static void run(Segment segment) {

		/// load rom ///
		Gb.loadGambatte();
		State.init(RomInfo.rom.romFileName);

		long start = System.currentTimeMillis();

		/// execute segment ///
		StateBuffer outBuffer = new StateBuffer();
		outBuffer.addState(new State());
		outBuffer = segment.execute(outBuffer);

		//// save movie ////
		BizhawkMovie.saveMovie(outBuffer.getStates().iterator().next(),"test");

		printStepCounts(outBuffer);

		System.out.println("Time: " +(System.currentTimeMillis()-start));
		System.out.println("Re-records: "+State.rerecordCount);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {}

		for(State s : outBuffer.getStates()) {
			s.restore();
			State.steps(1000);
		}

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {}
	}

	public static void printStepCounts(StateBuffer buf) {
		System.out.println("size: "+buf.size());
		int minStepCount = Integer.MAX_VALUE;
		int maxStepCount = Integer.MIN_VALUE;
		for(State s : buf.getStates()) {
			minStepCount = Math.min(minStepCount, s.stepCount);
			maxStepCount = Math.max(maxStepCount, s.stepCount);
			if(s.stepCount < State.currentStepCount)
				s.restore();
		}
		System.out.println("min: "+minStepCount+", max: "+maxStepCount);
	}
}
