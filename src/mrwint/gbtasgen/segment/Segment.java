package mrwint.gbtasgen.segment;

import mrwint.gbtasgen.state.StateBuffer;

public abstract class Segment {

	public abstract StateBuffer execute(StateBuffer in) throws Throwable;
}
