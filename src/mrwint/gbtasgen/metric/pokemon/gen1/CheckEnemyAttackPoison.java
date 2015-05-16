package mrwint.gbtasgen.metric.pokemon.gen1;

import static mrwint.gbtasgen.state.Gameboy.curGb;
import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.util.Util;

public class CheckEnemyAttackPoison implements StateResettingMetric {

	private boolean criticalHit;

	public CheckEnemyAttackPoison(boolean criticalHit) {
		this.criticalHit = criticalHit;
	}

	@Override
	public int getMetricInternal() {
		Util.runToAddressNoLimit(0, 0, curGb.pokemon.fightBattleCommand0a);
		int crit = curGb.readMemory(curGb.pokemon.fightCriticalHitAddress);
		int missed = curGb.readMemory(curGb.pokemon.fightAttackMissedAddress);
		boolean failure = missed != 0 || criticalHit != (crit != 0);
		if (failure)
			return Integer.MIN_VALUE;
		int dmg = Util.getMemoryWordBE(curGb.pokemon.fightCurDamageAddress);
		int add = Util.runToAddressNoLimit(0, 0, curGb.pokemon.fightEndTurnAddresses);
		if (add != curGb.pokemon.printLetterDelayJoypadAddress)
			return Integer.MIN_VALUE;
		return dmg;
	}
}