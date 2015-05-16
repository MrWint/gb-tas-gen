package mrwint.gbtasgen.move;

import static mrwint.gbtasgen.metric.comparator.Comparator.EQUAL;
import static mrwint.gbtasgen.state.Gameboy.curGb;
import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.metric.comparator.Comparator;

@Deprecated
public class CheckMetricMove extends Move {

	private Metric metric;
	private Comparator comp;
	private int value;
	private String attributeName;

	public CheckMetricMove(Metric metric) {
		this(metric, EQUAL, 1, null);
	}

	public CheckMetricMove(Metric metric, Comparator comp, int value, String attributeName) {
		this.metric = metric;
		this.comp = comp;
		this.value = value;
		this.attributeName = attributeName;
	}

	@Override
	public boolean doMove() {
		int curVal = metric.getMetric();
		if(attributeName != null)
			curGb.setAttributeInt(attributeName, curVal);
		return comp.compare(curVal, value);
	}

	@Override
	public int getInitialKey() {
		return 0;
	}

	@Override
	public boolean isStateAltering() {
		return false;
	}
}
