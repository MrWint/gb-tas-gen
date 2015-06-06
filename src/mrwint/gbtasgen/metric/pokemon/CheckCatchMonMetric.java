package mrwint.gbtasgen.metric.pokemon;

import static mrwint.gbtasgen.state.Gameboy.curGb;
import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.util.EflUtil;

public class CheckCatchMonMetric implements StateResettingMetric {

	@Override
	public int getMetricInternal() {
		int add = EflUtil.runToAddressNoLimit(0,0, curGb.pokemon.catchSuccessAddress, curGb.pokemon.catchFailureAddress);
		if(add != curGb.pokemon.catchSuccessAddress)
			return 0;
		return 1;
	}
}