package mrwint.gbtasgen.metric;

import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.main.RomInfo;
import mrwint.gbtasgen.util.Util;

public class CheckAttackMisses implements StateResettingMetric {
	@Override
	public int getMetricInternal() {
		Util.runToAddress(0, 0, RomInfo.rom.fightBattleCommand0a);
		int miss = Gb.readMemory(RomInfo.rom.fightAttackMissedAddress);
		return (miss != 0 ? 1 : 0);
	}
}