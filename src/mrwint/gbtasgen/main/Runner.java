package mrwint.gbtasgen.main;
import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.movie.BizhawkMovie;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.state.StateBuffer;



public class Runner {

	public static void run(Segment segment) {
		
		Metric.initMetrics();
		
		Gb.loadGambatte();
		State.init(RomInfo.rom.romFileName);

		long start = System.currentTimeMillis();
				
		StateBuffer outBuffer = new StateBuffer();
		outBuffer.addState(new State());
		outBuffer = segment.execute(outBuffer);
		
		//// print world map ////
//		outBuffer.getStates().iterator().next().restore();
//		new SkipInput(10).execute();
		//new Map().printBlockMap();
		//new Map().printCollisionMap();
		//new Map().printCollisionMap2();

		//// save movie ////
		BizhawkMovie.saveMovie(outBuffer.getStates().iterator().next(),"test");
		//VBAMovie.saveMovie(outBuffer.getStates().iterator().next(),"test");

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
