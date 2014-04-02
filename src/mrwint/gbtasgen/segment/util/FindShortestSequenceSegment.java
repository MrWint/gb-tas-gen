package mrwint.gbtasgen.segment.util;

import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.move.DelayableMove;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.state.StateBuffer;

public class FindShortestSequenceSegment extends Segment {
	
	private DelayableMove[] moves;
	private Metric metric;
	private boolean onlyOneStateNeeded;

	public FindShortestSequenceSegment(DelayableMove[] moves, Metric metric) {
		this.moves = moves;
		this.metric = metric;
		this.onlyOneStateNeeded = false;
	}
	
	public FindShortestSequenceSegment(DelayableMove[] moves, Metric metric, boolean onlyOneStateNeeded) {
		this.moves = moves;
		this.metric = metric;
		this.onlyOneStateNeeded = onlyOneStateNeeded;
	}
	
	@Override
	public StateBuffer execute(StateBuffer in) throws Throwable {
		StateBuffer out = new StateBuffer();
		int sumDelay = -1;
		while(!out.isFull() && (!onlyOneStateNeeded || out.isEmpty())) {
			sumDelay++;
			System.out.println("FindShortestSequenceSegment: testing delay "+sumDelay+" size="+out.size());
			for(State s : in.getStates())
				rec(s,0,sumDelay,out);
		}
		return out;
	}

	private void rec(State s, int i, int remDelay, StateBuffer out) throws Throwable {
		if(i >= moves.length-1) {
			s.restore();
			moves[i].execute(remDelay);
			if(metric.getMetric() != 0)
				out.addState(State.createState(true));
		} else {
			moves[i].clearCache();
			for(int stepDelay=0;stepDelay<=remDelay;stepDelay++) {
				s.restore();
				moves[i].prepareMove(stepDelay, true);
				moves[i].doMove();
				rec(new State(),i+1,remDelay-stepDelay,out);
			}
		}
	}

}
