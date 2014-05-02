package mrwint.gbtasgen.main;
import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.movie.BizhawkMovie;
import mrwint.gbtasgen.segment.TempSegment;
import mrwint.gbtasgen.segment.TestSegment;
import mrwint.gbtasgen.segment.gen1.CatchAbraSegment;
import mrwint.gbtasgen.segment.gen1.ChooseStarterSegment;
import mrwint.gbtasgen.segment.gen1.IntroSegment;
import mrwint.gbtasgen.segment.gen1.LeaveHouseSegment;
import mrwint.gbtasgen.segment.gen1.MtMoonNoTrainerSegment;
import mrwint.gbtasgen.segment.gen1.NuggetBridgeSegment_1;
import mrwint.gbtasgen.segment.gen1.NuggetBridgeSegment_2;
import mrwint.gbtasgen.segment.gen1.NuggetBridgeSegment_3;
import mrwint.gbtasgen.segment.gen1.NuggetBridgeSegment_4;
import mrwint.gbtasgen.segment.gen1.NuggetBridgeSegment_5;
import mrwint.gbtasgen.segment.gen1.NuggetBridgeSegment_6;
import mrwint.gbtasgen.segment.gen1.NuggetBridgeSegment_7;
import mrwint.gbtasgen.segment.gen1.OakSpeechSegment;
import mrwint.gbtasgen.segment.gen1.OaksParcelSegment;
import mrwint.gbtasgen.segment.gen1.PewterSegment;
import mrwint.gbtasgen.segment.gen1.RivalFightSegment;
import mrwint.gbtasgen.segment.gen1.Route3Segment_1;
import mrwint.gbtasgen.segment.gen1.Route3Segment_2;
import mrwint.gbtasgen.segment.gen1.Route3Segment_3;
import mrwint.gbtasgen.segment.gen1.Route3Segment_4;
import mrwint.gbtasgen.segment.gen1.ViridianForestSegment;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.state.StateBuffer;



public class GenITest {
	
	public static void init() {
		Metric.initMetrics();
	}

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws Throwable {
		
		// select ROM to use
		RomInfo.rom = new RomInfo.RedRomInfo();
		
		init();
		
		//Gb.loadVBA();
		Gb.loadGambatte();
		State.init(RomInfo.rom.romFileName);

		long start = System.currentTimeMillis();
				
		StateBuffer outBuffer = new StateBuffer();

		//State ss = BizhawkMovie.loadMovie("test");
		
//		outBuffer.addState(new State());
//		outBuffer = new IntroSegment().execute(outBuffer);
//		outBuffer.save("doneIntro_1");

//		outBuffer = StateBuffer.load("doneIntro_1");
//		outBuffer = new OakSpeechSegment(null, null).execute(outBuffer);
//		outBuffer.save("doneOakSpeech_1");	

//		outBuffer = StateBuffer.load("doneOakSpeech_1");
//		outBuffer = new LeaveHouseSegment().execute(outBuffer);
//		outBuffer.save("doneLeaveHouse_1");		

//		outBuffer = StateBuffer.load("doneLeaveHouse_1");
//		outBuffer = new ChooseStarterSegment().execute(outBuffer);
//		outBuffer.save("doneChooseStarter_10");		

//		outBuffer = StateBuffer.load("doneChooseStarter_10");
//		outBuffer = new RivalFightSegment().execute(outBuffer);
//		outBuffer.save("doneRivalFightSegment_1");

//		outBuffer = StateBuffer.load("doneRivalFightSegment_1");
//		outBuffer = new OaksParcelSegment().execute(outBuffer);
//		outBuffer.save("doneOaksParcel_1");
		
//		outBuffer = StateBuffer.load("doneOaksParcel_1");
//		outBuffer = new ViridianForestSegment().execute(outBuffer);
//		outBuffer.save("doneViridianForest_1");
		
//		outBuffer = StateBuffer.load("doneViridianForest_1");
//		outBuffer = new PewterSegment().execute(outBuffer);
//		outBuffer.save("donePewter_1");
		
//		outBuffer = StateBuffer.load("donePewter_1");
//		outBuffer = new Route3Segment_1().execute(outBuffer);
//		outBuffer.save("doneRoute3_1_1");
		
//		outBuffer = StateBuffer.load("doneRoute3_1_1");
//		outBuffer = new Route3Segment_2().execute(outBuffer);
//		outBuffer.save("doneRoute3_2_1");
		
//		outBuffer = StateBuffer.load("doneRoute3_2_1");
//		outBuffer = new Route3Segment_3().execute(outBuffer);
//		outBuffer.save("doneRoute3_3_1");
		
//		outBuffer = StateBuffer.load("doneRoute3_3_1");
//		outBuffer = new Route3Segment_4().execute(outBuffer);
//		outBuffer.save("doneRoute3_4_1");
		
//		outBuffer = StateBuffer.load("doneRoute3_4_1");
//		outBuffer = new MtMoonNoTrainerSegment().execute(outBuffer);
//		outBuffer.save("doneMtMoonNoTrainer_1");
		
//		outBuffer = StateBuffer.load("doneMtMoonNoTrainer_1");
//		outBuffer = new NuggetBridgeSegment_1().execute(outBuffer);
//		outBuffer.save("doneNuggetBridge_1_1");
		
//		outBuffer = StateBuffer.load("doneNuggetBridge_1_1");
//		outBuffer = new NuggetBridgeSegment_2().execute(outBuffer);
//		outBuffer.save("doneNuggetBridge_2_1");
		
//		outBuffer = StateBuffer.load("doneNuggetBridge_2_1");
//		outBuffer = new NuggetBridgeSegment_3().execute(outBuffer);
//		outBuffer.save("doneNuggetBridge_3_1");
		
//		outBuffer = StateBuffer.load("doneNuggetBridge_3_1");
//		outBuffer = new NuggetBridgeSegment_4().execute(outBuffer);
//		outBuffer.save("doneNuggetBridge_4_1");
		
//		outBuffer = StateBuffer.load("doneNuggetBridge_4_1");
//		outBuffer = new NuggetBridgeSegment_5().execute(outBuffer);
//		outBuffer.save("doneNuggetBridge_5_1");
		
//		outBuffer = StateBuffer.load("doneNuggetBridge_5_1");
//		outBuffer = new NuggetBridgeSegment_6().execute(outBuffer);
//		outBuffer.save("doneNuggetBridge_6_1");
		
//		outBuffer = StateBuffer.load("doneNuggetBridge_6_1");
//		outBuffer = new NuggetBridgeSegment_7().execute(outBuffer);
//		outBuffer.save("doneNuggetBridge_7_1");
		
//		outBuffer = StateBuffer.load("doneNuggetBridge_7_1");
//		outBuffer = new CatchAbraSegment().execute(outBuffer);
//		outBuffer.save("doneCatchAbra_1");
		
//		outBuffer = StateBuffer.load("doneCatchAbra_1");
		outBuffer = StateBuffer.load("test_1");
		outBuffer = new TestSegment().execute(outBuffer);
		outBuffer.save("test_2");
		
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
