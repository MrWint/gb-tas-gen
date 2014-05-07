package mrwint.gbtasgen.metric.gen1;

import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.main.RomInfo;
import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.util.Util;

public class CheckEnemyAttackPoison extends StateResettingMetric {
	
	private boolean criticalHit;
	
	public CheckEnemyAttackPoison(boolean criticalHit) {
		this.criticalHit = criticalHit;
	}
	
	@Override
	public int getMetricInternal() {
		Util.runToAddress(0, 0, RomInfo.rom.fightBattleCommand0a);
		int crit = Gb.readMemory(RomInfo.rom.fightCriticalHitAddress);
		int missed = Gb.readMemory(RomInfo.rom.fightAttackMissedAddress);
		boolean failure = missed != 0 || criticalHit != (crit != 0);
		if (failure)
			return Integer.MIN_VALUE;
		int dmg = Util.getMemoryBigEndian(RomInfo.rom.fightCurDamageAddress);
		int add = Util.runToAddress2(0, 0, RomInfo.rom.fightEndTurnAddresses);
		if (add != RomInfo.rom.printLetterDelayJoypadAddress)
			return Integer.MIN_VALUE;
		return dmg;
	}
}