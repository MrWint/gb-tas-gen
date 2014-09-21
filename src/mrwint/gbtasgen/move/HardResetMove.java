package mrwint.gbtasgen.move;

import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.util.Util;

public class HardResetMove extends Move {

	@Override
	public boolean doMove() {
		State.step(Move.RESET);
		return true;
	}

	@Override
	public int getInitialKey() {
		return 0;
	}
}
