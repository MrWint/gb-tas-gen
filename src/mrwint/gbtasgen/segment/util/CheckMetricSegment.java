package mrwint.gbtasgen.segment.util;

import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.metric.comparator.Comparator;
import mrwint.gbtasgen.metric.comparator.Equal;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.state.StateBuffer;

public class CheckMetricSegment extends Segment {

	private Metric metric;
	private Comparator comp;
	private int value;
	private String attributeName;

	public CheckMetricSegment(Metric metric) {
		this(metric,new Equal(),1,null);
	}
	
	public CheckMetricSegment(Metric metric, Comparator comp, int value,String attributeName) {
		this.metric = metric;
		this.comp = comp;
		this.value = value;
		this.attributeName = attributeName;
	}

	@Override
	public StateBuffer execute(StateBuffer in) throws Throwable {
		StateBuffer out = new StateBuffer(0); // output buffer is unbounded
		for(State s : in.getStates()) {
			s.restore();
			int curVal = metric.getMetric();
			if(attributeName != null)
				s.setAttributeInt(attributeName, curVal);
			if(!comp.compare(curVal, value))
				continue;
			out.addState(s);
		}
		return out;
	}
}
