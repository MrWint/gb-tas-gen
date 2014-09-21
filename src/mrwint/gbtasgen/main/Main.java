package mrwint.gbtasgen.main;
import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.movie.BizhawkMovie;
import mrwint.gbtasgen.segment.gen2.any.GetStarter1Segment;
import mrwint.gbtasgen.segment.gen2.any.GetStarter2Segment;
import mrwint.gbtasgen.segment.gen2.any.IntroSegment;
import mrwint.gbtasgen.segment.gen2.any.MysteryEggSegment;
import mrwint.gbtasgen.segment.gen2.any.OakSpeechSegment;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.state.StateBuffer;



public class Main {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws Throwable {

		// select ROM to use
//		RomInfo.rom = new RomInfo.CrystalRomInfo();
//		RomInfo.rom = new RomInfo.GoldRomInfo();
		RomInfo.rom = new RomInfo.SilverRomInfo();

		//Gb.loadVBA();
		Gb.loadGambatte();
		State.init(RomInfo.rom.romFileName);

		long start = System.currentTimeMillis();

		StateBuffer outBuffer = new StateBuffer();

		//State ss = BizhawkMovie.loadMovie("test");

		outBuffer.addState(new State());
		outBuffer = new IntroSegment().execute(outBuffer);
//		outBuffer.save("doneIntro_1b");
////		outBuffer.save("doneIntroOptionsTIDAny4");
//		outBuffer = StateBuffer.load("doneIntroOptionsTIDAny");

//		outBuffer = StateBuffer.load("doneIntro_1b");
		outBuffer = new OakSpeechSegment().execute(outBuffer);
//		outBuffer.save("doneOakSpeech_50");

//		outBuffer = StateBuffer.load("doneOakSpeech_50");
		outBuffer = new GetStarter1Segment().execute(outBuffer);
//		outBuffer.save("doneGetStarter1_50");

//		outBuffer = StateBuffer.load("doneGetStarter1_50");
		outBuffer = new GetStarter2Segment().execute(outBuffer);
//		outBuffer.save("doneGetStarter2_50");

//		outBuffer = StateBuffer.load("doneGetStarter2_50");
		outBuffer = new MysteryEggSegment().execute(outBuffer);
		outBuffer.save("doneMysteryEgg_1");

//		outBuffer = StateBuffer.load("doneMysteryEgg_50");
//		outBuffer = new CherrygroveRivalSegment().execute(outBuffer);
//		outBuffer.save("doneCherrygroveRival_50");

//		outBuffer = StateBuffer.load("doneCherrygroveRival_50");
//		outBuffer = new ToViolet1Segment().execute(outBuffer);
//		outBuffer.save("doneToViolet1_50");

//		outBuffer = StateBuffer.load("doneToViolet1_50");
//		outBuffer = new Route30YoungsterSegment().execute(outBuffer);
//		outBuffer.save("doneRoute30YoungsterSegment_50");

//		outBuffer = StateBuffer.load("doneRoute30YoungsterSegment_50");
//		outBuffer = new ToViolet15Segment().execute(outBuffer);
//		outBuffer.save("doneToViolet15_50");

//		outBuffer = StateBuffer.load("doneToViolet15_50");
//		outBuffer = new ToViolet2Segment().execute(outBuffer);
//		outBuffer.save("doneToViolet2_50");

//		outBuffer = StateBuffer.load("doneToViolet2_50");
//		outBuffer = new VioletBirdKeeperAbe().execute(outBuffer);
//		outBuffer.save("doneVioletBirdKeeperAbe_50");

//		outBuffer = StateBuffer.load("doneVioletBirdKeeperAbe_50");
//		outBuffer = new VioletBirdkeeperRon().execute(outBuffer);
//		outBuffer.save("doneVioletBirdKeeperRon_50");

//		outBuffer = StateBuffer.load("doneVioletBirdKeeperRon_50");
//		outBuffer = new VioletLeader().execute(outBuffer);
//		outBuffer.save("doneVioletLeader_50");

//		outBuffer = StateBuffer.load("doneVioletLeader_50a");
//		outBuffer = new ToAzalea1Segment().execute(outBuffer);
//		outBuffer.save("tmp");
//		outBuffer.save("doneToAzalea1Segment_100");

//		outBuffer = StateBuffer.load("doneToAzalea1Segment_100");
//		outBuffer = new Route32YoungsterAlbert().execute(outBuffer);
//		outBuffer.save("doneRoute32YoungsterAlbert_10");

//		outBuffer = StateBuffer.load("doneRoute32YoungsterAlbert_1");
//		outBuffer = new ToAzalea21Segment().execute(outBuffer);
//		outBuffer.save("doneToAzalea21Segment_1");

//		outBuffer = StateBuffer.load("doneRoute32YoungsterAlbert_10");
//		outBuffer = new ToAzalea22Segment().execute(outBuffer);
//		outBuffer.save("doneToAzalea22Segment_50");

//		outBuffer = StateBuffer.load("doneToAzalea22Segment_50");
//		outBuffer = new UnionCaveHikerRussell().execute(outBuffer);
//		outBuffer.save("doneUnionCaveHikerRussell_100");

//		outBuffer = StateBuffer.load("doneUnionCaveHikerRussell_100");
//		outBuffer = new ToAzalea3Segment().execute(outBuffer);
//		outBuffer.save("doneToAzalea3Segment_50");

//		outBuffer = StateBuffer.load("doneToAzalea3Segment_50");
//		outBuffer = new UnionCaveFirebreatherRay().execute(outBuffer);
//		outBuffer.save("doneUnionCaveFirebreatherRay_50");

//		outBuffer = StateBuffer.load("doneUnionCaveFirebreatherRay_50");
//		outBuffer = new ToAzalea4Segment().execute(outBuffer);
//		outBuffer.save("doneToAzalea4Segment_50");

//		outBuffer = StateBuffer.load("doneToAzalea4Segment_50");
//		outBuffer = new ToAzalea5Segment().execute(outBuffer);
//		outBuffer.save("doneToAzalea5Segment_50a");

//		outBuffer = StateBuffer.load("doneToAzalea5Segment_50a");
//		outBuffer = new SlowpokeWellRocket1().execute(outBuffer);
//		outBuffer.save("doneSlowpokeWellRocket1_50");

//		outBuffer = StateBuffer.load("doneSlowpokeWellRocket1_50");
//		outBuffer = new SlowpokeWellRocket2().execute(outBuffer);
//		outBuffer.save("doneSlowpokeWellRocket2_50a");

//		outBuffer = StateBuffer.load("doneSlowpokeWellRocket2_50a");
//		outBuffer = new SlowpokeWellRocket3().execute(outBuffer);
//		outBuffer.save("doneSlowpokeWellRocket3_50");

//		outBuffer = StateBuffer.load("doneSlowpokeWellRocket3_50");
//		outBuffer = new SlowpokeWellRocket4().execute(outBuffer);
//		outBuffer.save("doneSlowpokeWellRocket4_200");

//		outBuffer = StateBuffer.load("doneSlowpokeWellRocket4_200");
//		outBuffer = new ToAzaleaArenaSegment().execute(outBuffer);
//		outBuffer.save("doneToAzaleaArenaSegment_50");

//		outBuffer = StateBuffer.load("doneToAzaleaArenaSegment_50");
//		outBuffer = new AzaleaTwinsAmyMay().execute(outBuffer);
//		outBuffer.save("doneAzaleaTwinsAmyMay_50");

//		outBuffer = StateBuffer.load("doneAzaleaTwinsAmyMay_50");
//		outBuffer = new AzaleaBugCatcherJosh().execute(outBuffer);
//		outBuffer.save("doneAzaleaBugCatcherJosh_50");

//		outBuffer = StateBuffer.load("doneAzaleaBugCatcherJosh_50");
//		outBuffer = new AzaleaLeader().execute(outBuffer);
//		outBuffer.save("doneAzaleaLeader_50");

//		outBuffer = StateBuffer.load("doneAzaleaLeader_50");
//		outBuffer = new AzaleaRivalSegment().execute(outBuffer);
//		outBuffer.save("doneAzaleaRivalSegment_30");

//		outBuffer = StateBuffer.load("doneAzaleaRivalSegment_30");
//		outBuffer = new ToGoldenrod1Segment().execute(outBuffer);
//		outBuffer.save("doneToGoldenrod1Segment_30");

//		outBuffer = StateBuffer.load("doneToGoldenrod1Segment_30");
//		outBuffer = new GetCoincase1Segment().execute(outBuffer);
//		outBuffer.save("doneGetCoincase1Segment_30");

//		outBuffer = StateBuffer.load("doneGetCoincase1Segment_30");
//		outBuffer = new GoldenrodSuperNerdEric().execute(outBuffer);
//		outBuffer.save("doneGoldenrodSuperNerdEric_50");

//		outBuffer = StateBuffer.load("doneGoldenrodSuperNerdEric_50");
//		outBuffer = new GetCoincase2Segment().execute(outBuffer);
//		outBuffer.save("doneGetCoincase2Segment_10");

//		outBuffer = StateBuffer.load("tmp");
//		outBuffer = new TempSegment().execute(outBuffer);
//		outBuffer.save("tmp");

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

		Thread.sleep(2000);

		for(State s : outBuffer.getStates()) {
			s.restore();
			State.steps(1000);
		}

		Thread.sleep(2000);
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
