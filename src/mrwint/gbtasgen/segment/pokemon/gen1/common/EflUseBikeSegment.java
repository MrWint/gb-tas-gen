package mrwint.gbtasgen.segment.pokemon.gen1.common;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.util.EflUtil;
import mrwint.gbtasgen.util.EflUtil.PressMetric;

public class EflUseBikeSegment extends SeqSegment {

	int menuscroll;
	int itemscroll;
	public EflUseBikeSegment() {
		this(2, 1);
	}
	public EflUseBikeSegment(int menuscroll, int itemscroll) {
	  EflUtil.assertEfl();

		this.menuscroll = menuscroll;
		this.itemscroll = itemscroll;
	}

	@Override
	public void execute() {
	  seqEflButton(Move.START, PressMetric.PRESSED);
	  seqEflScrollA(menuscroll);
		if (itemscroll == 0) {
		  seqEflSkipInput(1);
		  seqEflButton(Move.A);
		}
		else {
	    if (itemscroll == 1 || itemscroll == -1)
	      seqEflSkipInput(1);
		  seqEflScrollFastAF(itemscroll);
		}
		seq(new EflSkipTextsSegment(1)); // got on bike
	}
}
