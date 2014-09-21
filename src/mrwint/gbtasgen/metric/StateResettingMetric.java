package mrwint.gbtasgen.metric;

import mrwint.gbtasgen.state.State;

public interface StateResettingMetric extends Metric {

	@Override
	default public int getMetric() {
		State s = new State();
		int ret = getMetricInternal();
		s.restore();
		return ret;
	}

	public abstract int getMetricInternal();
}
