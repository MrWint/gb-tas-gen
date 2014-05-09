package mrwint.gbtasgen.segment.gen1.common;

import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;

public class CancelMoveLearnSegment extends SeqSegment {
	
	@Override
	public void execute() {
		seq(new SkipTextsSegment(6));
		seq(new SkipTextsSegment(1, true));
		seq(new SkipTextsSegment(2));
	}
}
