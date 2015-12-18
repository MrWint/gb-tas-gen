package mrwint.gbtasgen.metric.pokemon.gen1;

import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.util.Util;

public class CheckPoisonEffectMisses implements StateResettingMetric {
	@Override
	public int getMetricInternal() {
		if(Util.runToAddressLimit(0, 0, 100, 0x3f24f) == 0) // start of poison effect handler
			return 0;
		int add = Util.runToAddressNoLimit(0, 0, 0x3f295, 0x3f2d7); // hit, miss
		return (add == 0x3f2d7 ? 1 : 0);
	}
}