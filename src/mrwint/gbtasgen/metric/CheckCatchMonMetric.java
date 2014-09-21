package mrwint.gbtasgen.metric;

import mrwint.gbtasgen.main.RomInfo;
import mrwint.gbtasgen.util.Util;

public class CheckCatchMonMetric implements StateResettingMetric {
	
	@Override
	public int getMetricInternal() {
		int add = Util.runToAddress2(0,0, RomInfo.rom.catchSuccessAddress, RomInfo.rom.catchFailureAddress);
		if(add != RomInfo.rom.catchSuccessAddress)
			return 0;
		return 1;
	}
}