package mrwint.gbtasgen.segment.pokemon.gen1.common;

import static mrwint.gbtasgen.move.Move.A;
import static mrwint.gbtasgen.move.Move.B;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PC_MON_CHANGEBOX;
import static mrwint.gbtasgen.state.Gameboy.curGb;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.MENU;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.PRESSED;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.util.EflUtil;

public class EflChangeMonBoxSegment extends SeqSegment {

  int box;
	int boxScroll;

	boolean textSkip;

	public EflChangeMonBoxSegment(int box) {
    EflUtil.assertEfl();

    this.box = box;
	}

	@Override
	public void execute() {
	  seq(new EflSelectPcMonOperationSegment(PC_MON_CHANGEBOX));

	  seqSample(() -> {
      int curBox = curGb.readMemory(curGb.pokemon.curMonBoxAddress) & 0xf;
      boxScroll = box - curBox;

      textSkip = (curGb.readMemory(0xd730) & 0x40) != 0;
    });

	  if (textSkip) {
      seqEflButton(B, PRESSED); // save game
      seqEflButton(B, PRESSED); // save game
      seqEflButton(A, PRESSED); // yes
	  } else {
	    seq(new EflSkipTextsSegment(2)); // save game
	    seq(new EflSkipTextsSegment(1, true)); // yes
	  }
    seqEflScrollAF(boxScroll, MENU);
	}
}
