package mrwint.gbtasgen.metric.pokemon.gen1;

import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.rom.RomInfo;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.util.Util;

public class CheckNoAIMove2 implements StateResettingMetric {

	@Override
	public int getMetricInternal() {
		State.step();
		int add = Util.runToAddressLimit(0, 0, 500, 0x3e732, RomInfo.pokemon.printLetterDelayJoypadAddress);
		if (add == 0) return 0;
		System.out.println("found!");
		return (add == 0) ? 0 : 1;
	}
}