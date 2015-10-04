package mrwint.gbtasgen.metric.pokemon.gen1.slots;

import static mrwint.gbtasgen.state.Gameboy.curGb;
import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.util.EflUtil;

public class SlotsWinModeMetric implements StateResettingMetric {

	@Override
	public int getMetricInternal() {
	  if (EflUtil.runToAddressLimit(0, 0, 100, 0x3740d) == 0) // after SlotMachine_37480
	    return 0;
	  return curGb.readMemory(0xcd4c) >= 0x80 ? 1 : 0;
	}
}
