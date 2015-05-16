package mrwint.gbtasgen.move;

import static mrwint.gbtasgen.state.Gameboy.curGb;

public class HardResetMove extends Move {

	@Override
	public boolean doMove() {
	  curGb.step(Move.RESET);
		return true;
	}

	@Override
	public int getInitialKey() {
		return 0;
	}
}
