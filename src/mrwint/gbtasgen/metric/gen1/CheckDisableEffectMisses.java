package mrwint.gbtasgen.metric.gen1;

import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.util.Util;

public class CheckDisableEffectMisses extends StateResettingMetric {
	@Override
	public int getMetricInternal() {
		int add = Util.runToAddress2(0, 0, 0x3faa8, 0x3fb06); // hit, miss
		return (add == 0x3fb06 ? 1 : 0);
	}
}