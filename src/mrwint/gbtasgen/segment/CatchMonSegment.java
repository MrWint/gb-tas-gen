package mrwint.gbtasgen.segment;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.state.StateBuffer;

public class CatchMonSegment extends Segment {

	SequenceSegment sequence;
	
	public CatchMonSegment(int numScrolls) {
		List<Segment> segments = new ArrayList<Segment>();

		segments.add(new SkipTextsSegment(2)); // wild mon, go mon
		segments.add(Segment.press(Move.DOWN)); // items
		segments.add(Segment.press(Move.A)); // select items
		segments.add(Segment.scrollFast(numScrolls)); // select ball
		segments.add(new BallSuccessSegment());

		segments.add(new SkipTextsSegment(4)); // cought, new dex data
		segments.add(Segment.press(Move.A)); // skip dex
		segments.add(Segment.press(Move.B)); // skip dex
		segments.add(new SkipTextsSegment(2)); // no nickname
		
		sequence = new SequenceSegment(segments.toArray(new Segment[0]));
	}
	
	@Override
	public StateBuffer execute(StateBuffer in) {
		return sequence.execute(in);
	}
}
