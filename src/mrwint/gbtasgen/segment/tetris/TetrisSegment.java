package mrwint.gbtasgen.segment.tetris;

import mrwint.gbtasgen.state.tetris.TetrisStateBuffer;

public interface TetrisSegment {

	public TetrisStateBuffer execute(TetrisStateBuffer in);
}
