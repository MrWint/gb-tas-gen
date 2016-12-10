package mrwint.gbtasgen.segment.pokemon.gen1.common;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.util.EflUtil.PressMetric;

public class EflTossItemSegment extends SeqSegment {
	
	int scroll;
	boolean alreadySelected;
	boolean skipOnceBeforeScroll = false;
	public EflTossItemSegment(int amount) {
		this.scroll = 1-amount;
	}
	public EflTossItemSegment(int amount, boolean alreadySelected) {
		this.scroll = 1-amount;
		this.alreadySelected = alreadySelected;
	}
	public EflTossItemSegment withSkipBeforeScroll() {
	  skipOnceBeforeScroll = true;
	  return this;
	}
	
	
	@Override
	public void execute() {
		if (!alreadySelected)
		   seqEflButton(Move.A, PressMetric.PRESSED); // select
		seqEflButton(Move.DOWN); // toss
		seqEflButton(Move.A); // toss
		if (scroll == 0)
      seqEflButton(Move.A, PressMetric.PRESSED);
		else {
		  if (skipOnceBeforeScroll)
		    seqEflSkipInput(1);
			seqEflScrollA(scroll, PressMetric.DOWN); // all
		}
		seq(new EflSkipTextsSegment(1)); // ok to toss?
		seqEflButton(Move.A); // yes
		seq(new EflSkipTextsSegment(1)); // threw away
	}
}
