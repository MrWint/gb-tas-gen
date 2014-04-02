package mrwint.gbtasgen.move;

import mrwint.gbtasgen.state.State;
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
	public int execute() throws Throwable {
		if(numSkip <= 0) return Util.runToNextInputFrame();
		int steps=0;
		for(int i=0;i<numSkip;i++) {
			steps += Util.runToNextInputFrame();
			State.step();
			steps++;
		}
		return steps;
	}
	
	@Override
	public int getInitialKey() {
		return 0;
	}
}
