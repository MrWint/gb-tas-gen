package mrwint.gbtasgen.metric.gen1;

import mrwint.gbtasgen.main.RomInfo;
import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.state.Register;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.util.Util;

public class CheckNoAIMove extends StateResettingMetric {
	
	int initialMove;
	
	public CheckNoAIMove(int initialMove) {
		this.initialMove = initialMove;
	}
	
	@Override
	public int getMetricInternal() {
		int add = Util.runToAddress2(0,initialMove,RomInfo.rom.fightAIMoveCheck); // Check for AI moves (item uses etc.)
//		return (add == RomInfo.rom.fightAIExecuteMove) ? 1 : 0;
		return (add == RomInfo.rom.printLetterDelayJoypadAddress) ? 1 : 0;
//		if (add == RomInfo.rom.printLetterDelayJoypadAddress)
//			return 0;
//		int f = State.getRegister(Register.AF) & 0x10;
//		System.out.println(f);
//		return (f == 0) ? 1 : 0;
	}
}