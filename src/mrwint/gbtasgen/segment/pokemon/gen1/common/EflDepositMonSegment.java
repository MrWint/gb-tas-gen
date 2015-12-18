package mrwint.gbtasgen.segment.pokemon.gen1.common;

import static mrwint.gbtasgen.move.Move.A;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PC_MON_DEPOSIT;
import static mrwint.gbtasgen.state.Gameboy.curGb;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.PRESSED;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.util.EflUtil;

public class EflDepositMonSegment extends SeqSegment {

  int mon;
	int monGoalPosition = -1;

	public EflDepositMonSegment(int mon) {
    EflUtil.assertEfl();

    this.mon = mon;
	}

	@Override
	public void execute() {
	  seq(new EflSelectPcMonOperationSegment(PC_MON_DEPOSIT));

	  seqSample(() -> {
      int numMons = curGb.readMemory(curGb.pokemon.numPartyMonAddress);
      for (int i = 0; i < numMons; i++) {
        int curMon = curGb.readMemory(curGb.pokemon.numPartyMonAddress + i + 1);
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
		seqEflButton(A, PRESSED); // deposit
		seq(new EflSkipTextsSegment(1)); // stored in box
	}
}
