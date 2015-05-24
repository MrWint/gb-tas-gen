package mrwint.gbtasgen.move;

import static mrwint.gbtasgen.metric.comparator.Comparator.EQUAL;
import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.metric.comparator.Comparator;
import mrwint.gbtasgen.util.EflUtil;

public class EflWithMetric extends Move {

	private Move move;
	private Metric metric;
	private Comparator comp;
	private int value;

	public EflWithMetric(Move move, Metric metric) {
		this(move, metric, EQUAL, 1);
	}

	public EflWithMetric(Move move, Metric metric, Comparator comp, int value) {
    EflUtil.assertEfl();

    this.move = move;
		this.metric = metric;
		this.comp = comp;
		this.value = value;
	}

	@Override
	public int getInitialKey() {
		return move.getInitialKey();
	}
	@Override
	public void clearCache() {
		move.clearCache();
	}
	@Override
	public boolean isDelayable() {
		return move.isDelayable();
	}
	@Override
	public boolean isCachable() {
		return move.isCachable();
	}
	@Override
	public boolean isStateAltering() {
		return move.isStateAltering();
	}

	@Override
	public void prepareInternal(int skips, boolean assumeOnSkip) {
		move.prepareInternal(skips, assumeOnSkip);
	}

	@Override
	public boolean doMove() {
		return move.doMove() && comp.compare(metric.getMetric(), value);
	}
}
