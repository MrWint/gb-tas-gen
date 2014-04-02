package mrwint.gbtasgen.segment.section;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.move.SkipInput;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.WalkToSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.state.StateBuffer;

public class ToAzaleaArenaSegment extends Segment {

	SequenceSegment sequence;
	
	public ToAzaleaArenaSegment() {
		List<Segment> segments = new ArrayList<Segment>();

		segments.add(new SkipTextsSegment(10));			// rocket speech, kurt speech

		segments.add(new WalkToSegment(3, 8, false));			// leave kurt's house
		segments.add(new MoveSegment(new SkipInput(2)));
		segments.add(new WalkToSegment(10, 15, false));			// enter arena
		
		segments.add(new WalkToSegment(4, 11, false));			// engage twins amy & may
		
		sequence = new SequenceSegment(segments.toArray(new Segment[0]));
	}
	
	@Override
	public StateBuffer execute(StateBuffer in) throws Throwable {
		return sequence.execute(in);
	}
}
