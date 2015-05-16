package mrwint.gbtasgen.metric;

import static mrwint.gbtasgen.state.Gameboy.curGb;
import mrwint.gbtasgen.state.State;

public interface StateResettingMetric extends Metric {

	@Override
	default public int getMetric() {
		State s = curGb.newState();
		int ret = getMetricInternal();
		curGb.restore(s);
		return ret;
	}

	public abstract int getMetricInternal();
}
