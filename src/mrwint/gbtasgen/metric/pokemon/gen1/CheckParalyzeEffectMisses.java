package mrwint.gbtasgen.metric.pokemon.gen1;

import static mrwint.gbtasgen.state.Gameboy.curGb;
import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.util.Util;

public class CheckParalyzeEffectMisses implements StateResettingMetric {
	@Override
	public int getMetricInternal() {
		if(Util.runToAddressLimit(0, 0, 100, 0x52601) == 0) // start of paralyze effect handler
			return 0;
		Util.runToAddressNoLimit(0, 0, 0x52633); // after MoveHitTest
    int miss = curGb.readMemory(curGb.pokemon.fightAttackMissedAddress);
    return (miss != 0 ? 1 : 0);
	}
}