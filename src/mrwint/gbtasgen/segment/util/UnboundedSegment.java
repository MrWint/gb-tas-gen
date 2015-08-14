package mrwint.gbtasgen.segment.util;

import mrwint.gbtasgen.segment.Segment;

public class UnboundedSegment extends SeqSegment {

	private Segment inner;

	public UnboundedSegment(Segment inner) {
	  this.inner = inner;
  }
	@Override
	protected void execute() {
	  seqUnbounded(inner);
	}
}
