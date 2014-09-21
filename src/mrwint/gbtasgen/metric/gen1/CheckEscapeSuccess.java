package mrwint.gbtasgen.metric.gen1;

import mrwint.gbtasgen.main.RomInfo;
import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.util.Util;

public class CheckEscapeSuccess implements StateResettingMetric {
	
	int startMove;
	public CheckEscapeSuccess(int startMove) {
		this.startMove = startMove;
	}

	@Override
	public int getMetricInternal() {
		int add = Util.runToAddress2(0, startMove, 0x3cb81, RomInfo.rom.printLetterDelayJoypadAddress); // got away safely text
		return add != RomInfo.rom.printLetterDelayJoypadAddress ? 1 : 0;
	}

}
