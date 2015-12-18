package mrwint.gbtasgen.segment.pokemon.gen1.common;

import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PC_MON_WITHDRAW;
import static mrwint.gbtasgen.state.Gameboy.curGb;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.util.EflUtil;
import mrwint.gbtasgen.util.EflUtil.PressMetric;

public class EflWithdrawMonSegment extends SeqSegment {

  int mon;
	int monGoalPosition = -1;

	public EflWithdrawMonSegment(int mon) {
    EflUtil.assertEfl();

		this.mon = mon;
	}

	@Override
	public void execute() {
    seq(new EflSelectPcMonOperationSegment(PC_MON_WITHDRAW));

    seqSample(() -> {
      int numMons = curGb.readMemory(0xDA80);
      for (int i = 0; i < numMons; i++) {
        int curMon = curGb.readMemory(0xDA80 + i + 1);
        if (curMon == mon) {
          monGoalPosition = i;
          break;
        }
      }
      if (monGoalPosition == -1)
        throw new IllegalStateException("Mon " + mon + " not found.");
    });


    if (monGoalPosition <= 2)
      seqEflScrollFastAF(monGoalPosition);
    else
      seqEflScrollFastA(monGoalPosition);
		seqEflButton(Move.A, PressMetric.PRESSED); // withdraw
		seq(new EflSkipTextsSegment(2)); // taken out, got
	}
}
