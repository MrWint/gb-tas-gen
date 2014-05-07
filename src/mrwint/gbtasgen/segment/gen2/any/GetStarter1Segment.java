package mrwint.gbtasgen.segment.gen2.any;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.move.gen2.OverworldInteract;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.TextSegment;
import mrwint.gbtasgen.segment.WalkToSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.state.StateBuffer;
import mrwint.gbtasgen.util.Util;

public class GetStarter1Segment extends Segment {

	SequenceSegment sequence;
	
	public GetStarter1Segment() {
		List<Segment> segments = new ArrayList<Segment>();
		
		segments.add(new WalkToSegment(7, 0, false)); // downstairs
		if(Util.isCrystal()) {
			segments.add(new WalkToSegment(8, 3)); // align
			segments.add(new WalkToSegment(8, 4, false)); // talk to mom
		}
		
		segments.add(new SkipTextsSegment(7));
		segments.add(new TextSegment()); // pokegear
		segments.add(new SkipTextsSegment(5));
		segments.add(new SkipTextsSegment(2, true)); // sunday
		segments.add(new SkipTextsSegment(1)); // no dst
		segments.add(new SkipTextsSegment(1, true)); // confirm
		segments.add(new SkipTextsSegment(3));
		segments.add(new SkipTextsSegment(1, true)); // know phone
		segments.add(new SkipTextsSegment(5));

		segments.add(new WalkToSegment(7, 6)); // leave house
		segments.add(new WalkToSegment(7, 8, false)); // leave house
		segments.add(new WalkToSegment(6, 3, false)); // enter elm's lab

		if(Util.isCrystal()) {
			segments.add(new SkipTextsSegment(12));
			segments.add(new SkipTextsSegment(1, true)); // agree help elm
			segments.add(new SkipTextsSegment(26));
		} else {
			segments.add(new SkipTextsSegment(16));
		}

		segments.add(new WalkToSegment(7, 4)); // go to ball
		segments.add(new MoveSegment(new PressButton(Move.UP))); // face ball
		segments.add(new MoveSegment(new PressButton(Move.UP))); // face ball
		
		segments.add(new MoveSegment(new OverworldInteract())); // grab totodile
		segments.add(new MoveSegment(new PressButton(Move.B))); // cancel totodile screen
		segments.add(new SkipTextsSegment(2, true)); // yes, I want totodile

//		segments.add(new SkipTextsSegment(2));
//		segments.add(new TextSegment(Move.A, true, 0)); // 3rd text
//		//segments.add(new MoveSegment(new SkipInput(0))); // move to next input frame
//		segments.add(new DelayMoveSegment(new PressButtonFactory(Move.B), new Segment() {
//			@Override
//			public StateBuffer execute(StateBuffer in) throws Throwable {
////				in = new TextSegment(Move.A,true,0).execute(in);
////				in = new MoveSegment(new PressButton(Move.B), 2, 0).execute(in);
//				in = new CheckMetricSegment(new CheckDVMetric(0/*Move.B*/, 0, 0, 0, 0,
//						0xded8, 0xdfd8, 0xe0d8, 0xe1d8, 0xe2d8, 0xe3d8,
//						0xdef8, 0xdff8, 0xe0f8, 0xe1f8, 0xe2f8, 0xe3f8)).execute(in);
//				return in;
//			}
//		}, 5, 10));
//		segments.add(new SkipTextsSegment(1));
//
//		segments.add(new SkipTextsSegment(1, true)); // name it
//		segments.add(new NamingSegment("I"));
//		if(Util.isCrystal())
//			segments.add(new SkipTextsSegment(11));
//		else
//			segments.add(new SkipTextsSegment(9));
//
//		segments.add(new WalkToSegment(4, 7));
//		segments.add(new WalkToSegment(4, 8, false));
//		segments.add(new SkipTextsSegment(7)); // talk to assistant
//		segments.add(new WalkToSegment(4, 12, false));

		sequence = new SequenceSegment(segments.toArray(new Segment[0]));
	}
	
	@Override
	public StateBuffer execute(StateBuffer in) {
		return sequence.execute(in);
	}
}
