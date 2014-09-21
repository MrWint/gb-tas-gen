package mrwint.gbtasgen.metric;

import java.util.Arrays;

import mrwint.gbtasgen.main.RomInfo;
import mrwint.gbtasgen.util.Util;

public class TrainerIDMetric implements StateResettingMetric {

	Integer[] goalID;
	
	public TrainerIDMetric(Integer... goalID) {
		this.goalID = goalID;
	}
	
	@Override
	public int getMetricInternal() {
		Util.runToAddress(0, 0, RomInfo.rom.afterTrainerIDGenerationAddress);
		int id = Util.getMemoryBigEndian(RomInfo.rom.trainerIDAddress);
		
		if(Arrays.asList(goalID).contains(id))
			System.out.println(Util.toHex(id,4));
		return (Arrays.asList(goalID).contains(id) ? 1 : 0);
	}

}
