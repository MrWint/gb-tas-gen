package mrwint.gbtasgen.metric.gen1;

import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.util.Util;

public class CheckConfusionEffectMisses implements StateResettingMetric {
	@Override
	public int getMetricInternal() {
		int add = Util.runToAddress2(0, 0, 0x3f96f, 0x3f9a6); // hit, miss
		return (add == 0x3f9a6 ? 1 : 0);
	}
}