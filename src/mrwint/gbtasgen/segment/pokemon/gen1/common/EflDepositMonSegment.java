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
	  if (opscroll == 0)
      seqEflButton(Move.A, PressMetric.MENU); // deposit
    else if  (opscroll == -1)
      seqEflButton(Move.UP | Move.A, PressMetric.MENU); // withdraw
    else if  (opscroll == 1)
      seqEflButton(Move.DOWN | Move.A, PressMetric.MENU); // withdraw
	  else
      seqEflScrollAF(opscroll);

		if (monscroll <= 1 && monscroll >= -1)
		  seqEflSkipInput(1);
		seqEflScrollFastAF(monscroll);
		seqEflButton(Move.A, PressMetric.PRESSED); // deposit
		seq(new EflSkipTextsSegment(1)); // stored in box
	}
}
