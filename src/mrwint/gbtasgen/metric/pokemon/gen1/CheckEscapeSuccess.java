package mrwint.gbtasgen.metric.pokemon.gen1;

import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.rom.RomInfo;
import mrwint.gbtasgen.util.Util;

public class CheckEscapeSuccess implements StateResettingMetric {

	int startMove;
	public CheckEscapeSuccess(int startMove) {
		this.startMove = startMove;
	}

	@Override
	public int getMetricInternal() {
		int add = Util.runToAddressNoLimit(0, startMove, 0x3cb81, RomInfo.pokemon.printLetterDelayJoypadAddress); // got away safely text
		return add != RomInfo.pokemon.printLetterDelayJoypadAddress ? 1 : 0;
	}

}
