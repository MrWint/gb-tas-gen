package mrwint.gbtasgen.segment.gen1.common;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;

public class SwapWithSegment extends SeqSegment {
	
	int scroll;
	public SwapWithSegment(int offset) {
		this.scroll = offset;
	}
	
	@Override
	public void execute() {
		seq(Segment.repress(Move.SELECT)); // select
		seq(Segment.scroll(scroll));
		seq(Segment.repress(Move.SELECT)); // swap
	}
}
