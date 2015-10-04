package mrwint.gbtasgen.metric.pokemon.gen1.slots;

import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.util.EflUtil;

public class SlotsWin300Metric implements StateResettingMetric {

	@Override
	public int getMetricInternal() {
    int add = EflUtil.runToAddressLimit(0, 0, 100, 0x37716, 0x37719); // reset, after reset
    if (add != 0x37719)
      return 0;
    return 1;
	}
}
