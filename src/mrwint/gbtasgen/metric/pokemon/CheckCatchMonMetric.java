package mrwint.gbtasgen.metric.pokemon;

import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.rom.RomInfo;
import mrwint.gbtasgen.util.Util;

public class CheckCatchMonMetric implements StateResettingMetric {

	@Override
	public int getMetricInternal() {
		int add = Util.runToAddressNoLimit(0,0, RomInfo.pokemon.catchSuccessAddress, RomInfo.pokemon.catchFailureAddress);
		if(add != RomInfo.pokemon.catchSuccessAddress)
			return 0;
		return 1;
	}
}