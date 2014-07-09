package mrwint.gbtasgen.segment.gen1.common;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;

public class DepositMonSegment extends SeqSegment {
	
	int monscroll;
	public DepositMonSegment(int monscroll) {
		this.monscroll = monscroll;
	}
	
	@Override
	public void execute() {
		seq(Segment.menu(Move.A)); // deposit
		if (monscroll == 0)
			seq(Segment.repress(Move.A)); // select
		else
			seq(Segment.scrollFastAF(monscroll));
		seq(Segment.repress(Move.A)); // deposit
		seq(new SkipTextsSegment(1)); // stored in box
	}
}
