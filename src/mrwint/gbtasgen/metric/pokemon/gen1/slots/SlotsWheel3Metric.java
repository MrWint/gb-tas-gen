package mrwint.gbtasgen.metric.pokemon.gen1.slots;

import static mrwint.gbtasgen.state.Gameboy.curGb;
import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.state.Register;
import mrwint.gbtasgen.util.EflUtil;

public class SlotsWheel3Metric implements StateResettingMetric {

	@Override
	public int getMetricInternal() {
    int add = EflUtil.runToAddressLimit(0, 0, 100, 0x375e8, 0x37615); // lose, win+1
    if (add != 0x37615 || (curGb.getRegister(Register.AF) >> 8) != 2)
      return 0;
    return 1;
	}
}
