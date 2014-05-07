package mrwint.gbtasgen.segment;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.state.StateBuffer;


public class TempSegment extends Segment {

	SequenceSegment sequence;
	
	public TempSegment() {
		List<Segment> segments = new ArrayList<Segment>();

//		segments.add(new WalkToSegment(5, 70, false));			// trigger text
//		segments.add(new WalkToSegment(7, 71, false));			// trigger text
//		segments.add(new WalkToSegment(7, 71, false));			// trigger text

//		segments.add(new WalkToSegment(6, 70, false));			// trigger text
//		segments.add(new MoveSegment(new OverworldInteract()));			// trigger text
		segments.add(new WalkToSegment(7, 71, false));			// trigger text

		segments.add(new SkipTextsSegment(5));					// skip text
		segments.add(new WalkToSegment(7, 72, false));			// trigger text
//		segments.add(new WalkToSegment(6, 79, false));			// route32 -> union cave

		sequence = new SequenceSegment(segments.toArray(new Segment[0]));
	}
	
	@Override
	public StateBuffer execute(StateBuffer in) {
		return sequence.execute(in);
	}
}
