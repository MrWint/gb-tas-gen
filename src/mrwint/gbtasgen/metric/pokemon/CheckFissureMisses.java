package mrwint.gbtasgen.metric.pokemon;

import static mrwint.gbtasgen.state.Gameboy.curGb;
import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.util.EflUtil;

public class CheckFissureMisses implements StateResettingMetric {
	@Override
	public int getMetricInternal() {
	  EflUtil.runFor(10, 0, 0);
	  if (EflUtil.runToAddressLimit(0, 0,100, curGb.pokemon.printLetterDelayJoypadAddress) == 0)
	    return 0;
		int miss = curGb.readMemory(curGb.pokemon.fightAttackMissedAddress);
		return (miss != 0 ? 1 : 0);
	}
}