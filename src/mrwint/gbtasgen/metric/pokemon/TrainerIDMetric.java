package mrwint.gbtasgen.metric.pokemon;

import static mrwint.gbtasgen.state.Gameboy.curGb;

import java.util.Arrays;

import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.util.Util;

public class TrainerIDMetric implements StateResettingMetric {

	Integer[] goalID;

	public TrainerIDMetric(Integer... goalID) {
		this.goalID = goalID;
	}

	@Override
	public int getMetricInternal() {
		Util.runToAddressNoLimit(0, 0, curGb.pokemon.afterTrainerIDGenerationAddress);
		int id = Util.getMemoryWordBE(curGb.pokemon.trainerIDAddress);

		if(Arrays.asList(goalID).contains(id))
			System.out.println(Util.toHex(id,4));
		return (Arrays.asList(goalID).contains(id) ? 1 : 0);
	}

}
