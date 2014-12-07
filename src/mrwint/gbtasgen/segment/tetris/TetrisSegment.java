package mrwint.gbtasgen.segment.tetris;

import mrwint.gbtasgen.state.tetris.TetrisStateBuffer;

public abstract class TetrisSegment {

	public abstract TetrisStateBuffer execute(TetrisStateBuffer in);
}
