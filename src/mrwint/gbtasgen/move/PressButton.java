package mrwint.gbtasgen.move;

import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.util.Util;

public class PressButton extends DelayableCachableMove {

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
	public boolean doMove() {
		State.step(moves);
		return true;
	}

	@Override
	public void prepareInternal(int skips, boolean assumeOnSkip) {
		if(!assumeOnSkip)
			if (metric != null)
				Util.runToFirstDifference(waitKeys, moves, metric);
			else
				Util.runToNextInputFrame(waitKeys);
		while(skips-- > 0) {
			State.step(waitKeys);
			if (metric != null)
				Util.runToFirstDifference(waitKeys, moves, metric);
			else
				Util.runToNextInputFrame(waitKeys);
		}
	}
}
