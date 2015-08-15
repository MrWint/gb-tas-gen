package mrwint.gbtasgen.metric.pokemon;

import static mrwint.gbtasgen.state.Gameboy.curGb;
import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.util.EflUtil;

public class CheckAttackMisses implements StateResettingMetric {
	@Override
	public int getMetricInternal() {
	  if (EflUtil.runToAddressLimit(0, 0,100, curGb.pokemon.fightBattleCommand0a) == 0)
	    return 0;
		int miss = curGb.readMemory(curGb.pokemon.fightAttackMissedAddress);
		return (miss != 0 ? 1 : 0);
	}
}