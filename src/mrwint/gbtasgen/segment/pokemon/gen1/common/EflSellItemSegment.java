package mrwint.gbtasgen.segment.pokemon.gen1.common;

import static mrwint.gbtasgen.move.Move.A;
import static mrwint.gbtasgen.move.Move.DOWN;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.PRESSED;

import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.util.EflUtil;

public class EflSellItemSegment extends SeqSegment {

  int itemscroll;
  int scroll;
  boolean endWithA;
  public EflSellItemSegment(int itemscroll, int amount) {
    this(itemscroll, amount, false);
  }
	public EflSellItemSegment(int itemscroll, int amount, boolean endWithA) {
	  EflUtil.assertEfl();

	  this.itemscroll = itemscroll;
	  this.scroll = 1-amount;
	  this.endWithA = endWithA;
	}

	@Override
	public void execute() {
	  if (itemscroll == 0)
	    seqEflButton(A);
	  else if (itemscroll == 1)
      seqEflButton(A | DOWN);
	  else if (itemscroll == 2)
	    seqEflScrollFastAF(itemscroll);
	  else
      seqEflScrollFastA(itemscroll);
	  if (scroll == 0)
	    seqEflButton(A, PRESSED);
	  else
	    seqEflScrollA(scroll);
		seq(new EflSkipTextsSegment(1, true)); //ok?
//		seq(new EflSkipTextsSegment(1, endWithA)); // thank you
	}
}
