package mrwint.gbtasgen.move;

import static mrwint.gbtasgen.metric.comparator.Comparator.EQUAL;
import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.metric.comparator.Comparator;
import mrwint.gbtasgen.util.Util;

public class RunUntil extends Move {

	private Metric metric;
	private Comparator comp;
	private int value;
	private int keys;

	public RunUntil(Metric metric) {
		this(0, metric, EQUAL, 1, null);
	}

	public RunUntil(int keys, Metric metric, Comparator comp, int value, String attributeName) {
		this.keys = keys;
		this.metric = metric;
		this.comp = comp;
		this.value = value;
	}

	@Override
	public int getInitialKey() {
		return 0;
	}

	@Override
	public boolean doMove() {
		Util.runUntil(keys, metric, comp, value);
		return true;
	}
}
