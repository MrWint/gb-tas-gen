package mrwint.gbtasgen.segment.tetris;

import mrwint.gbtasgen.state.tetris.TetrisStateBuffer;

public abstract class TetrisSeqSegment implements TetrisSegment {

	private TetrisStateBuffer in;
	private TetrisStateBuffer save;

	protected abstract void execute();

	@Override
	public TetrisStateBuffer execute(TetrisStateBuffer in) {
		this.in = in;
		execute();
		return this.in;
	}

	public void seq(TetrisSegment s) {
		in = s.execute(in);
	}

	public void load(TetrisStateBuffer sb) {
		in = sb;
	}
	public void load(String name) {
		in = TetrisStateBuffer.load(name);
	}
	public void qload() {
		in = save;
	}

	public TetrisStateBuffer save() {
		return in;
	}
	public void save(String name) {
		in.save(name);
	}
	public void qsave() {
		save = in;
	}
}
