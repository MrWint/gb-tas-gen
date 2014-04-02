package mrwint.gbtasgen.segment.util;

import mrwint.gbtasgen.move.DelayableMove;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.state.StateBuffer;

public class MoveSegment extends Segment {
	
	public static final int MAX_DELAY = 4;

	private Move move;
	private DelayableMove delayableMove;
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
		if(move instanceof DelayableMove)
			delayableMove = (DelayableMove)move;
		else
			delayableMove = null;
		this.maxDelay = maxDelay;
	}
	
	@Override
	public StateBuffer execute(StateBuffer in) throws Throwable {
		StateBuffer out = new StateBuffer(bufferSize);
		for(State s : in.getStates()) {
			if(delayableMove != null) {
				delayableMove.clearCache();
				for(int delay = 0; delay <= maxDelay; delay++) {
					s.restore();
					delayableMove.prepareMove(delay,useCache);
					delayableMove.doMove();
					State.currentDelayStepCount += delay;
					out.addState(State.createState(true));
				}
			} else {
				s.restore();
				int res = move.execute();
				if(res != -1)
					out.addState(State.createState(true));
			}
		}
		return out;
	}
}
