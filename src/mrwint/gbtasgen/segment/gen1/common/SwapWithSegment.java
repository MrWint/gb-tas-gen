package mrwint.gbtasgen.segment.gen1.common;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.util.SeqSegment;

public class SwapWithSegment extends SeqSegment {
	
	int scroll;
	public SwapWithSegment(int offset) {
		this.scroll = offset;
	}
	
	@Override
	public void execute() {
		seq(Segment.repress(Move.SELECT)); // select
		seq(Segment.scrollFast(scroll));
		seq(Segment.repress(Move.SELECT)); // swap
	}
}
