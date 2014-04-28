package mrwint.gbtasgen.move;

import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.util.Util;

public class PressButton extends DelayableMove {

	private int moves;
	private int waitKeys;
	private Metric metric;
	
	public PressButton(int moves) {
		this(moves,null,0);
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
			if (metric != null)
				steps += Util.runToFirstDifference(waitKeys, moves, metric);
			else
				steps += Util.runToNextInputFrame(waitKeys);
		while(skips-- > 0) {
			State.step(waitKeys);
			steps++;
			if (metric != null)
				steps += Util.runToFirstDifference(waitKeys, moves, metric);
			else
				steps += Util.runToNextInputFrame(waitKeys);
		}
		return steps;
	}
}
