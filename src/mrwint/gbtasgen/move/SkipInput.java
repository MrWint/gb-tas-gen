package mrwint.gbtasgen.move;

import static mrwint.gbtasgen.state.Gameboy.curGb;
import mrwint.gbtasgen.util.Util;

public class SkipInput extends Move {

	private int numSkip;

	public SkipInput() {
		this(1);
	}

	public SkipInput(int numSkip) {
		this.numSkip = numSkip;
	}

	@Override
	public boolean doMove() {
		if(numSkip <= 0) {
			Util.runToNextInputFrame();
			return true;
		}
		for(int i=0;i<numSkip;i++) {
			Util.runToNextInputFrame();
			curGb.step();
		}
		return true;
	}

	@Override
	public int getInitialKey() {
		return 0;
	}
}
