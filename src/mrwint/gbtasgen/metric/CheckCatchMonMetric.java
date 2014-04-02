package mrwint.gbtasgen.metric;

import mrwint.gbtasgen.main.RomInfo;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.util.Util;

public class CheckCatchMonMetric extends Metric {
	
	@Override
	public int getMetric() {
		State s = new State();
		int add = Util.runToAddress2(0,0, RomInfo.rom.catchSuccessAddress, RomInfo.rom.catchFailureAddress);
		s.restore();
		if(add != RomInfo.rom.catchSuccessAddress)
			return 0;
		return 1;
	}
}