package mrwint.gbtasgen.segment.gen1.common;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;

public class TossItemSegment extends SeqSegment {
	
	int scroll;
	boolean alreadySelected;
	public TossItemSegment(int amount) {
		this.scroll = 1-amount;
	}
	public TossItemSegment(int amount, boolean alreadySelected) {
		this.scroll = 1-amount;
		this.alreadySelected = alreadySelected;
	}
	
	@Override
	public void execute() {
		if (!alreadySelected)
			seq(Segment.repress(Move.A)); // select
		seq(Move.DOWN); // toss
		seq(Move.A); // toss
		if (scroll == 0)
			seq(Segment.repress(Move.A));
		else
			seq(Segment.scrollA(scroll)); // all
		seq(new SkipTextsSegment(1)); // ok to toss?
		seq(Move.A); // yes
		seq(new SkipTextsSegment(1)); // threw away
	}
}
