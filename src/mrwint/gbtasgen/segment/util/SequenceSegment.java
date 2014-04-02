package mrwint.gbtasgen.segment.util;

import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.state.StateBuffer;

public class SequenceSegment extends Segment {

	private Segment[] segments;
	
	public SequenceSegment(Segment... segments) {
		this.segments = segments;
	}
	
	@Override
	public StateBuffer execute(StateBuffer in) throws Throwable {
		StateBuffer out = in;
		for(Segment s : segments)
			out = s.execute(out);
		return out;
	}
}
