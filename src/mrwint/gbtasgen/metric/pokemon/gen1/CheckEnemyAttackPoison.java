package mrwint.gbtasgen.metric.pokemon.gen1;

import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.rom.RomInfo;
import mrwint.gbtasgen.util.Util;

public class CheckEnemyAttackPoison implements StateResettingMetric {

	private boolean criticalHit;

	public CheckEnemyAttackPoison(boolean criticalHit) {
		this.criticalHit = criticalHit;
	}

	@Override
	public int getMetricInternal() {
		Util.runToAddressNoLimit(0, 0, RomInfo.pokemon.fightBattleCommand0a);
		int crit = Gb.readMemory(RomInfo.pokemon.fightCriticalHitAddress);
		int missed = Gb.readMemory(RomInfo.pokemon.fightAttackMissedAddress);
		boolean failure = missed != 0 || criticalHit != (crit != 0);
		if (failure)
			return Integer.MIN_VALUE;
		int dmg = Util.getMemoryWordBE(RomInfo.pokemon.fightCurDamageAddress);
		int add = Util.runToAddressNoLimit(0, 0, RomInfo.pokemon.fightEndTurnAddresses);
		if (add != RomInfo.pokemon.printLetterDelayJoypadAddress)
			return Integer.MIN_VALUE;
		return dmg;
	}
}