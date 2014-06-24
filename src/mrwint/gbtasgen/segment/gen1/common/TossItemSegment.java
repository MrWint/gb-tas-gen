package mrwint.gbtasgen.segment.gen1.common;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;

public class TossItemSegment extends SeqSegment {
	
	int scroll;
	public TossItemSegment(int amount) {
		this.scroll = 1-amount;
	}
	
	@Override
	public void execute() {
		seq(Segment.repress(Move.A)); // select
		seq(Move.DOWN); // toss
		seq(Move.A); // toss
		seq(Segment.scroll(scroll)); // all
		seq(Segment.repress(Move.A));
		seq(new SkipTextsSegment(1)); // ok to toss?
		seq(Move.A); // yes
		seq(new SkipTextsSegment(1)); // threw away
	}
}
