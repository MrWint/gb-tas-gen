package mrwint.gbtasgen.metric.pokemon.gen1;

import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.util.Util;

public class CheckSwitchAndTeleportEffectUsed implements StateResettingMetric {
	@Override
	public int getMetricInternal() {
		if(Util.runToAddressLimit(0, 0, 100, 0x3f739) == 0) // start of SwitchAndTeleportEffect
			return 0;
		return 1;
	}
}