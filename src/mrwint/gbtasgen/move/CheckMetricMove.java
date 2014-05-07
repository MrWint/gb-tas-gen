package mrwint.gbtasgen.move;

import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.metric.comparator.Comparator;
import mrwint.gbtasgen.metric.comparator.Equal;
import mrwint.gbtasgen.state.State;

@Deprecated
public class CheckMetricMove extends Move {

	private Metric metric;
	private Comparator comp;
	private int value;
	private String attributeName;

	public CheckMetricMove(Metric metric) {
		this(metric,new Equal(),1,null);
	}
	
	public CheckMetricMove(Metric metric, Comparator comp, int value,String attributeName) {
		this.metric = metric;
		this.comp = comp;
		this.value = value;
		this.attributeName = attributeName;
	}

	@Override
	public boolean doMove() {
		int curVal = metric.getMetric();
		if(attributeName != null)
			State.setCAttributeInt(attributeName, curVal);
		if(!comp.compare(curVal, value))
			return false;
		return true;
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
