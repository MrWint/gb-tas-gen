package mrwint.gbtasgen.move;

import static mrwint.gbtasgen.state.Gameboy.curGb;
import mrwint.gbtasgen.util.EflUtil;

public class EflSkipInput extends Move {

	private int numSkip;

	public EflSkipInput() {
		this(1);
	}

	public EflSkipInput(int numSkip) {
	  EflUtil.assertEfl();

		this.numSkip = numSkip;
	}

	@Override
	public boolean doMove() {
		if(numSkip <= 0) {
			EflUtil.runToNextInputFrameNoLimit(0b11111111);
			return true;
		}
		for(int i=0;i<numSkip;i++) {
      while (EflUtil.runToNextInputFrameNoLimit(0b11111111) == 2) {
        System.out.println("EflSkipInput: skip split imput frame!");
        curGb.step();
      }
			curGb.step();
		}
		return true;
	}

	@Override
	public int getInitialKey() {
		return 0;
	}
}
