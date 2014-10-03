package mrwint.gbtasgen.move.pokemon;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.util.Util;

public class SoftResetMove extends Move {
	
	@Override
	public boolean doMove() {
		Util.runToNextInputFrame();
		for(int i=0;i<16;i++)
			State.step(Move.A | Move.B | Move.START | Move.SELECT);
		return true;
	}

	@Override
	public int getInitialKey() {
		return Move.A | Move.B | Move.START | Move.SELECT;
	}
}
