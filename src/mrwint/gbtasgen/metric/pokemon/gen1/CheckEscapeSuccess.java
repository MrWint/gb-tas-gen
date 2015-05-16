package mrwint.gbtasgen.metric.pokemon.gen1;

import static mrwint.gbtasgen.state.Gameboy.curGb;
import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.util.Util;

public class CheckEscapeSuccess implements StateResettingMetric {

	int startMove;
	public CheckEscapeSuccess(int startMove) {
		this.startMove = startMove;
	}

	@Override
	public int getMetricInternal() {
		int add = Util.runToAddressNoLimit(0, startMove, 0x3cb81, curGb.pokemon.printLetterDelayJoypadAddress); // got away safely text
		return add != curGb.pokemon.printLetterDelayJoypadAddress ? 1 : 0;
	}

}
