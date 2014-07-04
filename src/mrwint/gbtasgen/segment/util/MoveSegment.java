package mrwint.gbtasgen.segment.util;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.state.StateBuffer;

public class MoveSegment extends Segment {
	
	public static final int MAX_DELAY = 4;

	private Move move;
	private int maxDelay;
	private int bufferSize;
	private boolean useCache;

	public MoveSegment(Move move) {
		this(move,MAX_DELAY);
	}
	
	public MoveSegment(Move move, int maxDelay) {
		this(move,maxDelay,StateBuffer.MAX_BUFFER_SIZE);
	}

	public MoveSegment(Move move, int maxDelay, int bufferSize) {
		this(move,maxDelay, bufferSize, true);
	}

	public MoveSegment(Move move, int maxDelay, int bufferSize, boolean useCache) {
		this.move = move;
		this.bufferSize = bufferSize;
		this.useCache = useCache;
		this.maxDelay = move.isDelayable() ? maxDelay : 0;
	}
	
	@Override
	public StateBuffer execute(StateBuffer in) {
		StateBuffer out = new StateBuffer(bufferSize);
		for(State s : in.getStates()) {
			move.clearCache();
			if (move.isCachable())
				s.restore();
			for(int delay = 0; delay <= maxDelay; delay++) {
				if (!move.isCachable())
					s.restore();
				move.prepare(delay,useCache);
				if (move.doMove())
					out.addState(State.createState(true));
			}
		}
		return out;
	}
}
