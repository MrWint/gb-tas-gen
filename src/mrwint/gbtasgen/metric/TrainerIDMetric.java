package mrwint.gbtasgen.metric;

import java.util.Arrays;

import mrwint.gbtasgen.main.RomInfo;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.util.Util;

public class TrainerIDMetric extends Metric {

	Integer[] goalID;
	
	public TrainerIDMetric(Integer... goalID) {
		this.goalID = goalID;
	}
	
	@Override
	public int getMetric() {
		State initial = new State();
		
		Util.runToAddress(0, 0, RomInfo.rom.afterTrainerIDGenerationAddress);
		int id = Util.getMemoryBigEndian(RomInfo.rom.trainerIDAddress);
		initial.restore();
		
		if(Arrays.asList(goalID).contains(id))
			System.out.println(Util.toHex(id,4));
//		return 1;
		return (Arrays.asList(goalID).contains(id) ? 1 : 0);
	}

}
