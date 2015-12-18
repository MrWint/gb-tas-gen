package mrwint.gbtasgen.segment.pokemon.gen1.common;

import static mrwint.gbtasgen.move.Move.A;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PC_MON_DEPOSIT;
import static mrwint.gbtasgen.state.Gameboy.curGb;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.PRESSED;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.util.EflUtil;

public class EflDepositItemSegment extends SeqSegment {

  int item;
	int itemGoalPosition = -1;

	public EflDepositItemSegment(int item) {
    EflUtil.assertEfl();

    this.item = item;
	}

	@Override
	public void execute() {
	  seqSample(() -> {
      int numItems = curGb.readMemory(curGb.pokemon.numItemsAddress);
      for (int i = 0; i < numItems; i++) {
        int curItem = curGb.readMemory(curGb.pokemon.numItemsAddress + 2*i + 1);
        if (curItem == item) {
          itemGoalPosition = i;
          break;
        }
      }
      if (itemGoalPosition == -1)
        throw new IllegalStateException("Item " + item + " not found.");
    });

    if (itemGoalPosition <= 2)
      seqEflScrollFastAF(itemGoalPosition);
    else
      seqEflScrollFastA(itemGoalPosition);
    seq(new EflSkipTextsSegment(1)); // stored in box
    seq(new EflTextSegment()); // what do you want to deposit
	}
}
