package mrwint.gbtasgen.segment;

import mrwint.gbtasgen.metric.CheckEncounterMetric;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.move.WriteMemory;
import mrwint.gbtasgen.move.gen1.WalkStep;
import mrwint.gbtasgen.move.gen1.OverworldInteract;
import mrwint.gbtasgen.segment.util.CheckMetricSegment;
import mrwint.gbtasgen.segment.util.DelayMoveSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.DelayMoveSegment.PressButtonFactory;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.segment.util.SleepSegment;
import mrwint.gbtasgen.state.StateBuffer;


public class TestSegment extends SeqSegment {

	@Override
	public void execute() {
		seq(new WriteMemory(0xd355, 0xc0)); // fastest text
		seq(new WriteMemory(0xd356, 0xff)); // get all badges
//		seq(new WriteMemory(0xd19f, 19));   // fly
		seq(new WriteMemory(0xd19f, 91));   // dig
//		seq(new WriteMemory(0xd31e, 4));    // 1st item pokeball
//		seq(new WriteMemory(0xd31e, 6));    // 1st item bicycle
//		seq(new WriteMemory(0xd31e, 29));   // 1st item escape rope
//		seq(new WriteMemory(0xd31e, 0x1F)); // 1st item OLD AMBER
		seq(new WriteMemory(0xd31e, 0xb4)); // 1st item JACK
		seq(new WriteMemory(0xd31f, 0x50)); // x80
		seq(new WriteMemory(0xd320, 4));    // 2nd item pokeball
//		seq(new WriteMemory(0xd320, 0x28)); // 2nd item rare candy
		seq(new WriteMemory(0xd321, 0x50)); // x80
		seq(new WriteMemory(0xd31d, 0xff)); // underflow item counter

		seq(new WalkToSegment(19, 17));
		seq(Segment.press(Move.START));
		seq(new WriteMemory(0xd3b1, 0x00)); // set index to 0
		seq(Segment.press(Move.DOWN));
		seq(Segment.press(Move.A));
		seq(Segment.scrollFast(4));
		seq(Segment.press(Move.SELECT));
		seq(Segment.scrollFast(31));
		seq(Segment.press(Move.SELECT));
		seq(Segment.press(Move.A));
		seq(Segment.press(Move.DOWN));
		seq(Segment.press(Move.A));
		seq(Segment.scroll(-82));
		seq(Segment.press(Move.A));
		seq(new SkipTextsSegment(1));
		seq(Segment.press(Move.A));
		seq(new SkipTextsSegment(1, true));
		seq(Segment.scrollFast(38));
		seq(Segment.press(Move.A));
		seq(Segment.press(Move.DOWN));
		seq(Segment.press(Move.A));
		seq(Segment.scroll(30));
		seq(Segment.press(Move.A));
		seq(new SkipTextsSegment(1));
		seq(Segment.press(Move.A));
		seq(new SkipTextsSegment(1, true));
		seq(Segment.press(Move.B));
		seq(Segment.press(Move.START));


		seq(new WalkToSegment(3, 8, false));
		seq(new WalkStep(Move.LEFT, true));
		seq(new WalkStep(Move.LEFT, true));
		seq(Segment.skip(1));
		seq(Segment.press(Move.UP));
		seq(Segment.skip(1));
		seq(Segment.press(Move.START));
		seq(Segment.press(Move.A));
		seq(Segment.scrollFast(-35));
		seq(Segment.press(Move.SELECT));
		seq(Segment.scrollFast(-2));
		seq(Segment.press(Move.SELECT));
		seq(Segment.press(Move.A));
		seq(Segment.press(Move.DOWN));
		seq(Segment.press(Move.A));
		seq(Segment.scroll(4));
		seq(Segment.press(Move.A));
		seq(new SkipTextsSegment(1));
		seq(Segment.press(Move.A));
		seq(new SkipTextsSegment(1, true));
		seq(Segment.scrollFast(-36));
		seq(Segment.press(Move.A));
		seq(Segment.repress(Move.A));
		seq(Segment.press(Move.B));
		seq(Segment.repress(Move.B));
		seq(Segment.press(Move.START));
		seq(new WalkStep(Move.LEFT, true));
		seq(new WalkStep(Move.UP, true));
		seq(new WalkStep(Move.UP, true));
		seq(new WalkStep(Move.LEFT, true));
		seq(new WalkStep(Move.LEFT, true));
		seq(new WalkStep(Move.LEFT, true));
		seq(new WalkStep(Move.LEFT, true));
		seq(new WalkStep(Move.DOWN, true));
		seq(new WalkStep(Move.LEFT, true));
		seq(Segment.press(Move.START));
		seq(new WriteMemory(0xd719, 0x06)); // set teleport to celadon
		seq(Segment.scroll(-1));
		seq(Segment.press(Move.A));
		seq(Segment.repress(Move.A));
		seq(Segment.repress(Move.A));
		seq(Segment.skip(1));
		seq(new WalkToSegment(50, 10));
		seq(new WriteMemory(0xd173, 0x00)); // delete first move
		seq(new WalkToSegment(7, 2, false));
		seq(new DelayMoveSegment(new PressButtonFactory(Move.RIGHT), new CheckMetricSegment(new CheckEncounterMetric(0, 0))));
//		seq(new SkipTextsSegment(2)); // wild mon, go mon
//		seq(Segment.skip(1));
//		seq(new MoveSegment(new PressButton(Move.A), 0, 1));
//		seq(Segment.press(Move.B));
//		{
//			seq(Segment.press(Move.DOWN)); // items
//			seq(Segment.press(Move.A)); // select items
//			seq(Segment.scrollFast(1)); // select ball
//			seq(new BallSuccessSegment());
//
//			seq(new SkipTextsSegment(4)); // cought, new dex data
//			seq(Segment.press(Move.A)); // skip dex
//			seq(Segment.press(Move.B)); // skip dex
//			seq(new SkipTextsSegment(2)); // no nickname
//		}

		
		
//		seq(new WalkToSegment(13, 10);
//		seq(Segment.press(Move.START);
//		seq(Segment.press(Move.A);
//		seq(Segment.scroll(-1);
//		seq(Segment.press(Move.SELECT);
//		seq(Segment.scroll(5);
//		seq(Segment.press(Move.SELECT);
//		seq(Segment.press(Move.A);
//		seq(Segment.press(Move.DOWN);
//		seq(Segment.press(Move.A);
//		seq(Segment.scroll(73);
//		seq(Segment.press(Move.A);
//		seq(new SkipTextsSegment(1);
//		seq(Segment.press(Move.A);
//		seq(new SkipTextsSegment(1, true);
//		seq(Segment.press(Move.B);
//		seq(Segment.press(Move.START);
//		seq(new MoveSegment(new Gen1OverworldInteract(4));
//		seq(new SkipTextsSegment(1);
//		seq(new CatchMonSegment(0);

//		seq(new MoveSegment(new WriteMemory(0xd719, 0x05)); // set set teleport to vermilion
//		seq(new MoveSegment(new WriteMemory(0xd173, 0x00)); // delete first move
//
//		seq(Segment.scroll(-1);
//		seq(Segment.press(Move.A);
//		seq(Segment.repress(Move.A);
//		seq(Segment.repress(Move.A);
//		seq(new TextSegment();
//		seq(Segment.skip(1);
//
//		seq(new WalkToSegment(40, 14);
//		seq(new WalkToSegment(11,6);
//
//		in.save("tmp2");
//		seq(StateBuffer.load("tmp");
//
//		seq(new MoveSegment(new WriteMemory(0xd165, 0x05)); // convert to spearow
//
//		seq(new WalkToSegment(-1,6);
//		seq(new WalkToSegment(15,13);
//		seq(new WalkToSegment(3,7);
//		seq(new WalkToSegment(3,6);
//		seq(new MoveSegment(new Gen1OverworldInteract(1)); // trade
//		seq(new SkipTextsSegment(1); // trade spearow farfetchd
//		seq(new SkipTextsSegment(1, true); // trade spearow farfetchd
//		seq(Segment.repress(Move.A);
//		seq(new SkipTextsSegment(1); // trade spearow farfetchd
//		seq(new SkipTextsSegment(1); // trade traded
//		seq(new TextSegment();
//		seq(new SkipTextsSegment(1); // thanks
//		seq(new WalkToSegment(3,8, false);
		

//		seq(new MoveSegment(new WriteMemory(0xcdfa, 108)); // ekans
//		seq(new MoveSegment(new WriteMemory(0xcde9, 0x80)); // music
//		seq(new MoveSegment(new WriteMemory(0xcdea, 0x50)); // eos
//		seq(new MoveSegment(new WriteMemory(0xD2B6, 0x50)); // eos 2nd nickname char
//
//		seq(new DelayMoveSegment(new PressButtonFactory(Move.RIGHT), new CheckMetricSegment(new CheckEncounterMetric(0, 0, null,0)));
//		seq(new SkipTextsSegment(2); // wild mon, go mon
//		seq(new MoveSegment(new PressButton(Move.A), 0, 1);
//		seq(Segment.press(Move.B);
//		{
//			seq(Segment.press(Move.DOWN); // items
//			seq(Segment.press(Move.RIGHT); // end
//			seq(Segment.press(Move.A); // select items
//			seq(Segment.scrollFast(0); // select ball
//			seq(new BallSuccessSegment();

//			seq(new SkipTextsSegment(1); // cought, new dex data
//			seq(new SkipTextsSegment(4); // cought, new dex data
//			seq(Segment.press(Move.A); // skip dex
//			seq(Segment.press(Move.B); // skip dex
//			seq(new SkipTextsSegment(2); // no nickname
//		}

		seq(Segment.skip(1));
		
//		seq(new MoveSegment(new WriteMemory(0xd367, 0x3)); // enable dig
//		seq(new WalkToSegment(5, 16);
//		seq(new MoveSegment(new PressButton(Move.START), 0);
//		seq(Segment.scroll(3);
//		seq(Segment.press(Move.A);
//		seq(Segment.repress(Move.A);
//		seq(Segment.repress(Move.A);
//		seq(Segment.press(Move.DOWN);
//		seq(Segment.press(Move.A);
//		seq(new TextSegment();
//		seq(Segment.skip(1);
//		seq(new WalkToSegment(19, 19);
//		seq(Segment.scroll(1);
//		seq(Segment.press(Move.A);
//		seq(Segment.scrollFast(1);
//		seq(Segment.press(Move.A);
//		seq(Segment.repress(Move.A);
////		seq(Segment.scroll(1);
//		seq(Segment.repress(Move.A);
//		seq(Segment.press(Move.B);
//		seq(Segment.press(Move.A);
//		
////		for (int i=0;i<6;i++) {
////			seq(Segment.repress(Move.A);
////			seq(Segment.repress(Move.A);
////			seq(Segment.repress(Move.A);
////			seq(Segment.press(Move.B);
////			seq(Segment.press(Move.A);
////		}
//		seq(new MoveSegment(new WriteMemory(0xd1b8, 18)); // set level to 18
//		seq(new MoveSegment(new WriteMemory(0xd1a0, 1)); // fill move slot 2
//		seq(new MoveSegment(new WriteMemory(0xd1a1, 1)); // fill move slot 3
//		seq(new MoveSegment(new WriteMemory(0xd1a2, 1)); // fill move slot 4
//		for (int i=0;i<1;i++) {
//			seq(Segment.repress(Move.A);
//			seq(Segment.repress(Move.A);
//			seq(Segment.repress(Move.A);
//			seq(Segment.press(Move.B);
//			seq(Segment.press(Move.A);
//		}
//		seq(new TextSegment();
//		seq(new TextSegment();
//		for (int i=0;i<1;i++) {
//			seq(Segment.repress(Move.A);
//			seq(Segment.repress(Move.A);
//			seq(Segment.repress(Move.A);
//			seq(Segment.press(Move.B);
//			seq(Segment.press(Move.A);
//		}
////		seq(new SkipTextsSegment(1);
//		{ // cancel learning disable
//			seq(new SkipTextsSegment(6);
//			seq(new SkipTextsSegment(1, true);
//			seq(new SkipTextsSegment(2);
//		}
//		seq(Segment.scroll(1);

//		seq(new WalkToSegment(-1,18);
//
//		seq(new WalkToSegment(66,14);
//		seq(new DelayMoveSegment(new PressButtonFactory(Move.LEFT), new CheckMetricSegment(new CheckEncounterMetric(0, 0, null,0)));
//		seq(new CatchMonSegment(0); // ball in 1st slot
//		seq(new WalkToSegment(63,14);
	}
}
