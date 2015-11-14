package mrwint.gbtasgen.segment.pokemon.gen1.common;

import static mrwint.gbtasgen.state.Gameboy.curGb;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.MENU;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.util.EflUtil;

public class EflSelectPcMonOperationSegment extends SeqSegment {

  int operation;
	int opscroll;

	public EflSelectPcMonOperationSegment(int operation) {
    EflUtil.assertEfl();

    this.operation = operation;
	}

	@Override
	public void execute() {
    seqSample(() -> {
      EflUtil.runFor(60, 0, 0); // Wait for menu to load.
      opscroll = operation - curGb.readMemory(curGb.pokemon.pcMonOperationIndexAddress);
    });

    seqEflScrollAF(opscroll, MENU);
	}
}
