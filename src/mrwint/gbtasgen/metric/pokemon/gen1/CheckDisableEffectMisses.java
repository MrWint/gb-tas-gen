package mrwint.gbtasgen.metric.pokemon.gen1;

import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.util.EflUtil;

public class CheckDisableEffectMisses implements StateResettingMetric {
	@Override
	public int getMetricInternal() {
		int add = EflUtil.runToAddressLimit(0, 0, 100, 0x3faa8, 0x3fb06); // hit, miss
		return (add == 0x3fb06 ? 1 : 0);
	}
}