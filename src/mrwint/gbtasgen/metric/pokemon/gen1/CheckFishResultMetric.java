package mrwint.gbtasgen.metric.pokemon.gen1;

import static mrwint.gbtasgen.state.Gameboy.curGb;
import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.state.Register;
import mrwint.gbtasgen.util.Util;

public class CheckFishResultMetric implements StateResettingMetric {

	int mon;
	int lvl;

	public CheckFishResultMetric() {
		this(0, 0);
	}
	public CheckFishResultMetric(int mon, int lvl) {
		this.mon = mon;
		this.lvl = lvl;
	}

	@Override
	public int getMetricInternal() {
		Util.runToAddressNoLimit(0, 0, 0xe28d); // RodResponse

		boolean bite = (curGb.getRegister(Register.DE) & 0xFF) == 1;
		if (!bite)
			return 0;
		int bc = curGb.getRegister(Register.BC);
		int curLvl = (bc >> 8) & 0xFF;
		int curMon = bc & 0xFF;
		if((lvl > 0 && lvl != curLvl) || (mon > 0 && mon != curMon))
			return 0;
		return 1;
	}
}