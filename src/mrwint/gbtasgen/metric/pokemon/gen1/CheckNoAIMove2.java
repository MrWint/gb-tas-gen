package mrwint.gbtasgen.metric.pokemon.gen1;

import static mrwint.gbtasgen.state.Gameboy.curGb;
import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.util.Util;

public class CheckNoAIMove2 implements StateResettingMetric {

	@Override
	public int getMetricInternal() {
	  curGb.step();
		int add = Util.runToAddressLimit(0, 0, 500, 0x3e732, curGb.pokemon.printLetterDelayJoypadAddress);
		if (add == 0) return 0;
		System.out.println("found!");
		return (add == 0) ? 0 : 1;
	}
}