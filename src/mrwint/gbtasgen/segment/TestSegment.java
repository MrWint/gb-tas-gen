package mrwint.gbtasgen.segment;

import mrwint.gbtasgen.metric.CheckEncounterMetric;
import mrwint.gbtasgen.move.Gen1OverworldInteract;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.move.WriteMemory;
import mrwint.gbtasgen.segment.util.CheckMetricSegment;
import mrwint.gbtasgen.segment.util.DelayMoveSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.DelayMoveSegment.PressButtonFactory;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.state.StateBuffer;


public class TestSegment extends Segment {

	@Override
	public StateBuffer execute(StateBuffer in) throws Throwable {
		in = new MoveSegment(new WriteMemory(0xd355, 0xc0)).execute(in); // fastest text
		in = new MoveSegment(new WriteMemory(0xd356, 0xff)).execute(in); // get all badges
//		in = new MoveSegment(new WriteMemory(0xd19f, 19)).execute(in); // fly
//		in = new MoveSegment(new WriteMemory(0xd19f, 91)).execute(in); // dig
		in = new MoveSegment(new WriteMemory(0xd31e, 4)).execute(in); // 1st item pokeball
//		in = new MoveSegment(new WriteMemory(0xd31e, 29)).execute(in); // 1st item escape rope
		in = new MoveSegment(new WriteMemory(0xd31f, 0x50)).execute(in); // x80
//		in = new MoveSegment(new WriteMemory(0xd320, 0x28)).execute(in); // 2nd item rare candy
//		in = new MoveSegment(new WriteMemory(0xd321, 0x50)).execute(in); // x80
		in = new MoveSegment(new WriteMemory(0xd31d, 0xff)).execute(in); // underflow item counter

//		in = new WalkToSegment(19, 17).execute(in);
//		
//		in = Segment.press(Move.START).execute(in);		
//		in = Segment.press(Move.DOWN).execute(in);
//		in = Segment.press(Move.A).execute(in);
//		in = Segment.scrollFast(4).execute(in);
//		in = Segment.press(Move.SELECT).execute(in);
//		in = Segment.scrollFast(31).execute(in);
//		in = Segment.press(Move.SELECT).execute(in);
//		in = Segment.press(Move.A).execute(in);
//		in = Segment.press(Move.DOWN).execute(in);
//		in = Segment.press(Move.A).execute(in);
//		in = Segment.scroll(34).execute(in);
//		in = Segment.press(Move.A).execute(in);
//		in = new SkipTextsSegment(1).execute(in);
//		in = Segment.press(Move.A).execute(in);
//		in = new SkipTextsSegment(1, true).execute(in);
//		in = Segment.press(Move.B).execute(in);
//		in = Segment.press(Move.START).execute(in);
//
//		in = new MoveSegment(new WriteMemory(0xd3b1, 0x0b)).execute(in); // set index to 11
//
//		in = new WalkToSegment(3, 8, false).execute(in);
//		in = Segment.press(Move.START).execute(in);
//
//		in = new MoveSegment(new WriteMemory(0xd719, 0x05)).execute(in); // set set teleport to vermilion
//		in = new MoveSegment(new WriteMemory(0xd173, 0x00)).execute(in); // delete first move
//
//		in = Segment.scroll(-1).execute(in);
//		in = Segment.press(Move.A).execute(in);
//		in = Segment.repress(Move.A).execute(in);
//		in = Segment.repress(Move.A).execute(in);
//		in = new TextSegment().execute(in);
//		in = Segment.skip(1).execute(in);
//
//		in = new WalkToSegment(40, 14).execute(in);
//		in = new WalkToSegment(11,6).execute(in);
//
//		in.save("tmp2");
		in = StateBuffer.load("tmp");

		in = new MoveSegment(new WriteMemory(0xd165, 0x05)).execute(in); // convert to spearow

		in = new WalkToSegment(-1,6).execute(in);
		in = new WalkToSegment(15,13).execute(in);
//		in = new WalkToSegment(3,7).execute(in);
//		in = new WalkToSegment(3,6).execute(in);
//		in = new MoveSegment(new Gen1OverworldInteract(1)).execute(in); // trade
//		in = new SkipTextsSegment(1).execute(in); // trade spearow farfetchd
//		in = new SkipTextsSegment(1, true).execute(in); // trade spearow farfetchd
//		in = Segment.repress(Move.A).execute(in);
//		in = new SkipTextsSegment(1).execute(in); // trade spearow farfetchd
//		in = new SkipTextsSegment(1).execute(in); // trade traded
//		in = new TextSegment().execute(in);
//		in = new SkipTextsSegment(1).execute(in); // thanks
//		in = new WalkToSegment(3,8, false).execute(in);
		

//		in = new MoveSegment(new WriteMemory(0xcdfa, 108)).execute(in); // ekans
//		in = new MoveSegment(new WriteMemory(0xcde9, 0x80)).execute(in); // music
//		in = new MoveSegment(new WriteMemory(0xcdea, 0x50)).execute(in); // eos
//		in = new MoveSegment(new WriteMemory(0xD2B6, 0x50)).execute(in); // eos 2nd nickname char
//
//		in = new DelayMoveSegment(new PressButtonFactory(Move.RIGHT), new CheckMetricSegment(new CheckEncounterMetric(0, 0, null,0))).execute(in);
//		in = new SkipTextsSegment(2).execute(in); // wild mon, go mon
//		in = new MoveSegment(new PressButton(Move.A), 0, 1).execute(in);
//		in = Segment.press(Move.B).execute(in);
//		{
//			in = Segment.press(Move.DOWN).execute(in); // items
//			in = Segment.press(Move.RIGHT).execute(in); // end
//			in = Segment.press(Move.A).execute(in); // select items
//			in = Segment.scrollFast(0).execute(in); // select ball
//			in = new BallSuccessSegment().execute(in);

//			in = new SkipTextsSegment(1).execute(in); // cought, new dex data
//			in = new SkipTextsSegment(4).execute(in); // cought, new dex data
//			in = Segment.press(Move.A).execute(in); // skip dex
//			in = Segment.press(Move.B).execute(in); // skip dex
//			in = new SkipTextsSegment(2).execute(in); // no nickname
//		}

		in = Segment.skip(1).execute(in);
		
//		in = new MoveSegment(new WriteMemory(0xd367, 0x3)).execute(in); // enable dig
//		in = new WalkToSegment(5, 16).execute(in);
//		in = new MoveSegment(new PressButton(Move.START), 0).execute(in);
//		in = Segment.scroll(3).execute(in);
//		in = Segment.press(Move.A).execute(in);
//		in = Segment.repress(Move.A).execute(in);
//		in = Segment.repress(Move.A).execute(in);
//		in = Segment.press(Move.DOWN).execute(in);
//		in = Segment.press(Move.A).execute(in);
//		in = new TextSegment().execute(in);
//		in = Segment.skip(1).execute(in);
//		in = new WalkToSegment(19, 19).execute(in);
//		in = Segment.scroll(1).execute(in);
//		in = Segment.press(Move.A).execute(in);
//		in = Segment.scrollFast(1).execute(in);
//		in = Segment.press(Move.A).execute(in);
//		in = Segment.repress(Move.A).execute(in);
////		in = Segment.scroll(1).execute(in);
//		in = Segment.repress(Move.A).execute(in);
//		in = Segment.press(Move.B).execute(in);
//		in = Segment.press(Move.A).execute(in);
//		
////		for (int i=0;i<6;i++) {
////			in = Segment.repress(Move.A).execute(in);
////			in = Segment.repress(Move.A).execute(in);
////			in = Segment.repress(Move.A).execute(in);
////			in = Segment.press(Move.B).execute(in);
////			in = Segment.press(Move.A).execute(in);
////		}
//		in = new MoveSegment(new WriteMemory(0xd1b8, 18)).execute(in); // set level to 18
//		in = new MoveSegment(new WriteMemory(0xd1a0, 1)).execute(in); // fill move slot 2
//		in = new MoveSegment(new WriteMemory(0xd1a1, 1)).execute(in); // fill move slot 3
//		in = new MoveSegment(new WriteMemory(0xd1a2, 1)).execute(in); // fill move slot 4
//		for (int i=0;i<1;i++) {
//			in = Segment.repress(Move.A).execute(in);
//			in = Segment.repress(Move.A).execute(in);
//			in = Segment.repress(Move.A).execute(in);
//			in = Segment.press(Move.B).execute(in);
//			in = Segment.press(Move.A).execute(in);
//		}
//		in = new TextSegment().execute(in);
//		in = new TextSegment().execute(in);
//		for (int i=0;i<1;i++) {
//			in = Segment.repress(Move.A).execute(in);
//			in = Segment.repress(Move.A).execute(in);
//			in = Segment.repress(Move.A).execute(in);
//			in = Segment.press(Move.B).execute(in);
//			in = Segment.press(Move.A).execute(in);
//		}
////		in = new SkipTextsSegment(1).execute(in);
//		{ // cancel learning disable
//			in = new SkipTextsSegment(6).execute(in);
//			in = new SkipTextsSegment(1, true).execute(in);
//			in = new SkipTextsSegment(2).execute(in);
//		}
//		in = Segment.scroll(1).execute(in);

//		in = new WalkToSegment(-1,18).execute(in);
//
//		in = new WalkToSegment(66,14).execute(in);
//		in = new DelayMoveSegment(new PressButtonFactory(Move.LEFT), new CheckMetricSegment(new CheckEncounterMetric(0, 0, null,0))).execute(in);
//		in = new CatchMonSegment(0).execute(in); // ball in 1st slot
//		in = new WalkToSegment(63,14).execute(in);
		return in;
	}
}
