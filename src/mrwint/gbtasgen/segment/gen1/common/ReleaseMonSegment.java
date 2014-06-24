package mrwint.gbtasgen.segment.gen1.common;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;

public class ReleaseMonSegment extends SeqSegment {
	
	int monscroll;
	public ReleaseMonSegment(int monscroll) {
		this.monscroll = monscroll;
	}
	
	@Override
	public void execute() {
		seq(Segment.menu(Move.A)); // release
		seq(Segment.scrollFast(monscroll));
		seq(Segment.repress(Move.A)); // select
		seq(new SkipTextsSegment(1)); // release
		seq(new SkipTextsSegment(1, true)); // yes
		seq(new SkipTextsSegment(2)); // released
	}
}
