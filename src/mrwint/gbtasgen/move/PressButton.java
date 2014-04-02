package mrwint.gbtasgen.move;

import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.util.Util;

public class PressButton extends DelayableMove {

	private int moves;
	private int waitKeys;
	private Metric metric;
	
	public PressButton(int moves) {
		this(moves,Metric.DOWN_JOY,0);
	}
	
	public PressButton(int moves, Metric metric) {
		this(moves,metric,0);
	}

	public PressButton(int moves, Metric metric, int waitKeys) {
		this.moves = moves;
		this.metric = metric;
		this.waitKeys = waitKeys;
	}
	
	@Override
	public int getInitialKey() {
		return moves;
	}

	@Override
	public int doMove() throws Throwable {
		State.step(moves);
		return 1;
	}

	@Override
	public int prepareMoveInternal(int skips, boolean assumeOnSkip) throws Throwable {
		int steps = 0;
		if(!assumeOnSkip)
			steps += Util.runToFirstDifference(waitKeys, moves, metric);
		while(skips-- > 0) {
			State.step(waitKeys);
			steps++;
			steps += Util.runToFirstDifference(waitKeys, moves, metric);
		}
		return steps;
	}
}
