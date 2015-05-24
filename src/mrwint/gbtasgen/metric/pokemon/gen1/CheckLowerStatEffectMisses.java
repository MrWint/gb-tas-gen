package mrwint.gbtasgen.metric.pokemon.gen1;

import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.util.EflUtil;
import mrwint.gbtasgen.util.Util;

public class CheckLowerStatEffectMisses implements StateResettingMetric {
	@Override
	public int getMetricInternal() {
		if(EflUtil.runToAddressLimit(0, 0, 100, 0x3f54c) == 0) // start of LowerStatEffectHandler
		  return 0;
		int add = 0;
		while(add == 0)
			add = EflUtil.runToAddressLimit(0, 0, 100, 0x3f5a9, 0x3f65a); // hit, miss
//		System.out.println("CheckLowerStatEffectMisses: " + Util.toHex(add));
		return (add == 0x3f65a ? 1 : 0);
	}
}