package mrwint.gbtasgen.metric.gen1;

import mrwint.gbtasgen.main.RomInfo;
import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.util.Util;

public class CheckNoAIMove2 implements StateResettingMetric {

	@Override
	public int getMetricInternal() {
		State.step();
		int add = Util.runToAddress2Limit(0, 0, 500, 0x3e732, RomInfo.rom.printLetterDelayJoypadAddress);
		if (add == 0) return 0;
		System.out.println("found!");
		return (add == 0) ? 0 : 1;
	}
}