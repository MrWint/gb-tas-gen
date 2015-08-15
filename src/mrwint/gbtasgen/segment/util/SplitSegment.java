package mrwint.gbtasgen.segment.util;

import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.state.StateBuffer;

public class SplitSegment implements Segment {

	private Segment segment;

	public SplitSegment(Segment segment) {
		this.segment = segment;
	}

	@Override
	public StateBuffer execute(StateBuffer in) {
		StateBuffer out = new StateBuffer();
		int i = 0;
		for(State s : in.getStates()) {
			System.out.println("SplitSegment "+i+"/"+in.size()+" - size: "+out.size());
			StateBuffer tmp = new StateBuffer();
			tmp.addState(s);
			out.addAll(segment.execute(tmp));
			i++;
		}
		return out;
	}
}
