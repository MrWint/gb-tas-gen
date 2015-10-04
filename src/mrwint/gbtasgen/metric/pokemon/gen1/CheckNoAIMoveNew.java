package mrwint.gbtasgen.metric.pokemon.gen1;

import static mrwint.gbtasgen.state.Gameboy.curGb;
import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.util.EflUtil;
import mrwint.gbtasgen.util.Util;

public class CheckNoAIMoveNew implements StateResettingMetric {

	@Override
	public int getMetricInternal() {
    EflUtil.runToAddressNoLimit(0, 0, curGb.pokemon.fightTrainerAIBefore);
    int add = EflUtil.runToAddressNoLimit(0, 0, Util.arrayAppend(curGb.pokemon.fightTrainerAIAfter, curGb.pokemon.printLetterDelayJoypadAddress));
    if (add == curGb.pokemon.printLetterDelayJoypadAddress) {
      System.out.println("TrainerAI failed!");
      return 0;
    }
    return 1;
	}
}