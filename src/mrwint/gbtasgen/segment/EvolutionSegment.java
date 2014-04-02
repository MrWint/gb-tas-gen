package mrwint.gbtasgen.segment;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.state.StateBuffer;


public class EvolutionSegment extends Segment{
	
	SequenceSegment sequence;

	public EvolutionSegment() {
		List<Segment> segments = new ArrayList<Segment>();
		
		segments.add(new TextSegment()); // is evolving...
		segments.add(new SkipTextsSegment(1)); // evolved into...
		segments.add(new TextSegment()); // [evolution]
		
		sequence = new SequenceSegment(segments.toArray(new Segment[0]));
	}
	
	@Override
	public StateBuffer execute(StateBuffer in) throws Throwable {
		return sequence.execute(in);
	}
}