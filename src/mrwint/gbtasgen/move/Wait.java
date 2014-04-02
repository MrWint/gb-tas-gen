package mrwint.gbtasgen.move;

import mrwint.gbtasgen.state.State;

public class Wait extends Move {

	private int numSkip;
	
	public Wait() {
		this(1);
	}

	public Wait(int numSkip) {
		this.numSkip = numSkip;
	}

	@Override
	public int execute() throws Throwable {
		State.steps(numSkip);
		return numSkip;
	}
	
	@Override
	public int getInitialKey() {
		return 0;
	}
}
