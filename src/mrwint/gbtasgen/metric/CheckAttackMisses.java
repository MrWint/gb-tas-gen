package mrwint.gbtasgen.metric;

import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.main.RomInfo;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.util.Util;

public class CheckAttackMisses extends Metric {
	@Override
	public int getMetric() {
		State s = new State();
		Util.runToAddress(0, 0, RomInfo.rom.fightBattleCommand0a);
		int miss = Gb.readMemory(RomInfo.rom.fightAttackMissedAddress);
		s.restore();
		return (miss != 0 ? 1 : 0);
	}
}