package mrwint.gbtasgen.metric.pokemon;

import static mrwint.gbtasgen.state.Gameboy.curGb;
import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.util.EflUtil;

public class CheckAttackMisses implements StateResettingMetric {
	@Override
	public int getMetricInternal() {
	  EflUtil.runToAddressNoLimit(0, 0, curGb.pokemon.fightBattleCommand0a);
		int miss = curGb.readMemory(curGb.pokemon.fightAttackMissedAddress);
		return (miss != 0 ? 1 : 0);
	}
}