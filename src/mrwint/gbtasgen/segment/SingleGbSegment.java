package mrwint.gbtasgen.segment;

import mrwint.gbtasgen.state.Gameboy;
import mrwint.gbtasgen.state.SingleGbState;

public abstract class SingleGbSegment {

	private SingleGbState state;

	public SingleGbState execute(SingleGbState state) {
	  this.state = state;
	  execute();
	  return this.state;
	}

	protected abstract void execute();

	public void seq(Segment s) {
	  Gameboy.curGb = state.gb;
	  state = new SingleGbState(state.gb, s.execute(state.stateBuffer));
    Gameboy.curGb = null;
	}

	public void load(SingleGbState sb) {
	  state = sb;
	}

	public void load(String name) {
	  state = SingleGbState.load(state.gb, name);
	}
	public SingleGbState save() {
		return state;
	}
	public void save(String name) {
	  state.save(name);
	}
}
