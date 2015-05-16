package mrwint.gbtasgen.move;

import static mrwint.gbtasgen.state.Gameboy.curGb;

public class Wait extends Move {

	private int numSkip;

	public Wait() {
		this(1);
	}

	public Wait(int numSkip) {
		this.numSkip = numSkip;
	}

	@Override
	public boolean doMove() {
	  curGb.steps(numSkip);
		return true;
	}

	@Override
	public int getInitialKey() {
		return 0;
	}
}
