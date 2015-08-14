package mrwint.gbtasgen.segment.pokemon.gen1.common;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.util.EflUtil;
import mrwint.gbtasgen.util.EflUtil.PressMetric;

public class EflDepositMonSegment extends SeqSegment {
	
	int monscroll;
	int opscroll;
	
	public EflDepositMonSegment(int opscroll, int monscroll) {
    EflUtil.assertEfl();

    this.opscroll = opscroll;
		this.monscroll = monscroll;
	}
	
	@Override
	public void execute() {
	  if (opscroll != 0)
	    seqEflScrollAF(opscroll);
	  else
	    seqEflButton(Move.A, PressMetric.MENU); // deposit
		
		if (monscroll <= 1 && monscroll >= -1)
		  seqEflSkipInput(1);
		seqEflScrollFastAF(monscroll);
		seqEflButton(Move.A, PressMetric.PRESSED); // deposit
		seq(new EflSkipTextsSegment(1)); // stored in box
	}
}
