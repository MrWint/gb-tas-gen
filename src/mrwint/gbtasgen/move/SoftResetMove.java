package mrwint.gbtasgen.move;

import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.util.Util;

public class SoftResetMove extends Move {

	@Override
	public int execute() throws Exception {
		int steps = 0;
		steps += Util.runToNextInputFrame();
		for(int i=0;i<16;i++)
			State.step(Move.A | Move.B | Move.START | Move.SELECT);
		return steps+16;
	}

	@Override
	public int getInitialKey() {
		return Move.A | Move.B | Move.START | Move.SELECT;
	}
}
