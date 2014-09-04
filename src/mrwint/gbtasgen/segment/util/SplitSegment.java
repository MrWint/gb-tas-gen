package mrwint.gbtasgen.segment.util;

import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.state.StateBuffer;

public class SplitSegment extends Segment {

	private Segment segment;
	private int bufferSize;

	public SplitSegment(Segment segment) {
		this(segment, StateBuffer.MAX_BUFFER_SIZE);
	}
	
	public SplitSegment(Segment segment, int bufferSize) {
		this.segment = segment;
		this.bufferSize = bufferSize;
	}
	
	@Override
	public StateBuffer execute(StateBuffer in) {
		StateBuffer out = new StateBuffer(bufferSize);
		int i = 0;
		for(State s : in.getStates()) {
			System.out.println("SplitSegment "+i+"/"+in.size()+" - size: "+out.size());
			StateBuffer tmp = new StateBuffer(bufferSize);
			tmp.addState(s);
			out.addAll(segment.execute(tmp));
			i++;
		}
		return out;
	}
}
