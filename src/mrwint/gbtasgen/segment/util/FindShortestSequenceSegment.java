package mrwint.gbtasgen.segment.util;

import static mrwint.gbtasgen.state.Gameboy.curGb;
import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.state.Gameboy;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.state.StateBuffer;

public class FindShortestSequenceSegment implements Segment {

	private Move[] moves;
	private Metric metric;
	private boolean onlyOneStateNeeded;
	private int lastDelayableMove;

	public FindShortestSequenceSegment(Move[] moves, Metric metric) {
		this.moves = moves;
		this.metric = metric;
		this.onlyOneStateNeeded = false;
		this.lastDelayableMove = -1;
		for(int i=moves.length-1; i>=0; i--)
			if(moves[i].isDelayable()) {
				lastDelayableMove = i;
				break;
			}
		if(lastDelayableMove == -1)
			throw new RuntimeException("no delayable move found");
	}

	public FindShortestSequenceSegment(Move[] moves, Metric metric, boolean onlyOneStateNeeded) {
		this.moves = moves;
		this.metric = metric;
		this.onlyOneStateNeeded = onlyOneStateNeeded;
	}

	@Override
	public StateBuffer execute(StateBuffer in) {
		StateBuffer out = new StateBuffer();
		int sumDelay = -1;
		while(!out.isFull() && (!onlyOneStateNeeded || out.isEmpty())) {
			sumDelay++;
			System.out.println("FindShortestSequenceSegment: testing delay "+sumDelay+" size="+out.size());
			for(State s : in.getStates()) {
			  curGb.restore(s);
				rec(s,0,sumDelay,out);
			}
		}
		return out;
	}

	// Assumption: s is currently loaded
	private void rec(State s, int i, int remDelay, StateBuffer out) {
		moves[i].clearCache();
		if(i == moves.length-1) {
			moves[i].execute(remDelay);
			if(metric.getMetric() != 0)
				out.addState(Gameboy.curGb.createState(true));
		} else if (!moves[i].isDelayable()) {
			moves[i].execute(0);
			rec(curGb.newState(),i+1,remDelay,out);
		} else if (i == lastDelayableMove) {
			moves[i].execute(remDelay);
			rec(curGb.newState(),i+1,0,out);
		} else {
			for(int stepDelay=0;stepDelay<=remDelay;stepDelay++) {
			  curGb.restore(s);
				moves[i].prepare(stepDelay, true);
				moves[i].doMove();
				rec(curGb.newState(),i+1,remDelay-stepDelay,out);
			}
		}
	}

}
