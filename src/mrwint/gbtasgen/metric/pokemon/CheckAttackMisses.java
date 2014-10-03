package mrwint.gbtasgen.metric.pokemon;

import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.rom.RomInfo;
import mrwint.gbtasgen.util.Util;

public class CheckAttackMisses implements StateResettingMetric {
	@Override
	public int getMetricInternal() {
		Util.runToAddressNoLimit(0, 0, RomInfo.pokemon.fightBattleCommand0a);
		int miss = Gb.readMemory(RomInfo.pokemon.fightAttackMissedAddress);
		return (miss != 0 ? 1 : 0);
	}
}