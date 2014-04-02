package mrwint.gbtasgen.segment.util;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.TextSegment;
import mrwint.gbtasgen.state.StateBuffer;

public class SkipTextsSegment extends Segment {

	SequenceSegment sequence;

	public SkipTextsSegment(int numTexts) {
		this(numTexts,false);
	}

	public SkipTextsSegment(int numTexts, boolean confirmWithA) {
		List<Segment> segments = new ArrayList<Segment>();
		
		for(int i=0;i<numTexts;i++) {
			segments.add(new TextSegment(confirmWithA ? Move.B : Move.A));
			segments.add(new MoveSegment(new PressButton(confirmWithA ? Move.A : Move.B)));
		}

		sequence = new SequenceSegment(segments.toArray(new Segment[0]));
	}
	
	@Override
	public StateBuffer execute(StateBuffer in) throws Throwable {
		return sequence.execute(in);
	}
}
