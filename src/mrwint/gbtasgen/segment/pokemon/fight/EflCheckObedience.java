package mrwint.gbtasgen.segment.pokemon.fight;

import static mrwint.gbtasgen.state.Gameboy.curGb;

import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.util.EflUtil;

// Checks whether mon obeys orders.
public class EflCheckObedience implements StateResettingMetric {

	@Override
	public int getMetricInternal() {
    EflUtil.runToAddressNoLimit(0, 0, curGb.pokemon.fightObedienceBefore);
    int add = EflUtil.runToAddressNoLimit(0, 0, curGb.pokemon.fightObedienceAfter, curGb.pokemon.printLetterDelayJoypadAddress);
    if (add != curGb.pokemon.fightObedienceAfter || curGb.readMemory(curGb.pokemon.fightMonIsDisobedient) != 0) {
      System.out.println("Obedience failed!");
      return 0;
    }
    return 1;
	}
}