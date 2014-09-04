package mrwint.gbtasgen.metric.gen1;

import mrwint.gbtasgen.main.RomInfo;
import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.util.Util;

public class CheckSleepEffectMisses extends StateResettingMetric {
	@Override
	public int getMetricInternal() {
		if(Util.runToAddress2Limit(0, 0, 100, 0x3f1fc) == 0) // start of sleep effect handler
			return 0;
		int add = Util.runToAddress2(0, 0, 0x3f231, 0x3f242); // hit, miss
		return (add == 0x3f242 ? 1 : 0);
	}
}