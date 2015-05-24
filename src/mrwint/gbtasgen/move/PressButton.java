package mrwint.gbtasgen.move;

import static mrwint.gbtasgen.state.Gameboy.curGb;
import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.util.EflUtil;
import mrwint.gbtasgen.util.Util;

public class PressButton extends DelayableCachableMove {

	private int moves;
	private int waitKeys;
	private Metric metric;

	public PressButton(int moves) {
		this(moves, null, 0);
	}

	public PressButton(int moves, Metric metric) {
		this(moves, metric, 0);
	}

	public PressButton(int moves, Metric metric, int waitKeys) {
    EflUtil.assertNoEfl();

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
	  curGb.step(moves);
		return true;
	}

	@Override
	public void prepareInternal(int skips, boolean assumeOnSkip) {
		if(!assumeOnSkip)
			if (metric != null)
				Util.runToFirstDifference(waitKeys, moves, metric);
			else
				Util.runToNextInputFrame(waitKeys, waitKeys);
		while(skips-- > 0) {
		  curGb.step(waitKeys);
			if (metric != null)
				Util.runToFirstDifference(waitKeys, moves, metric);
			else
				Util.runToNextInputFrame(waitKeys, waitKeys);
		}
	}
}
