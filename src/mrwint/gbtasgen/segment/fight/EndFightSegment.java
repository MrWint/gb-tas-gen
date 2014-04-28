package mrwint.gbtasgen.segment.fight;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.state.StateBuffer;

public class EndFightSegment extends Segment {

	SequenceSegment sequence;
	
	public EndFightSegment(int numEndTextScrolls) {
		List<Segment> segments = new ArrayList<Segment>();

		segments.add(new SkipTextsSegment(1 + numEndTextScrolls + 1)); // player defeated enemy, end of fight texts, player got money for winning

		sequence = new SequenceSegment(segments.toArray(new Segment[0]));
	}
	
	@Override
	public StateBuffer execute(StateBuffer in) throws Throwable {
		return sequence.execute(in);
	}
}
