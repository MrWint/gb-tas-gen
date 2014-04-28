package mrwint.gbtasgen.metric;

import java.util.Arrays;

import mrwint.gbtasgen.main.RomInfo;
import mrwint.gbtasgen.state.Register;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.util.Util;

public class Gen1CheckDVMetric extends Metric {
	private int initialMove;
	private int minAtkDV;
	private int minDefDV;
	private int minSpdDV;
	private int minSpcDV;
	private Integer[] whitelist;

	public Gen1CheckDVMetric(int initialMove, int minAtkDV, int minDefDV, int minSpdDV, int minSpcDV, Integer... whitelist) {
		this.initialMove = initialMove;
		this.minAtkDV = minAtkDV;
		this.minDefDV = minDefDV;
		this.minSpdDV = minSpdDV;
		this.minSpcDV = minSpcDV;
		this.whitelist = whitelist;
	}
	
	@Override
	public int getMetric() {
		State s = new State();
		if(initialMove != 0)
			Util.runToNextInputFrame();
		Util.runToAddress(0, initialMove, RomInfo.rom.afterDVGenerationAddress);
		int a = State.getRegister(Register.AF) >> 8;
		int b = State.getRegister(Register.BC) >> 8;
		int ab = ((a & 0xFF) << 8) | (b & 0xFF);

		int atk = (a >> 4) & 0xF;
		int def = (a >> 0) & 0xF;
		int spd = (b >> 4) & 0xF;
		int spc = (b >> 0) & 0xF;
		
//		System.out.println("DVs: atk "+atk+", def "+def+", spd "+spd+", spc "+spc);
		
		s.restore();
		if(atk < minAtkDV) return 0;
		if(def < minDefDV) return 0;
		if(spd < minSpdDV) return 0;
		if(spc < minSpcDV) return 0;
		
		if(whitelist.length > 0 && !Arrays.asList(whitelist).contains(ab)) return 0;
		
		System.out.println("DVs: " + Util.toHex(ab, 4));
		
		return 1;
	}
}