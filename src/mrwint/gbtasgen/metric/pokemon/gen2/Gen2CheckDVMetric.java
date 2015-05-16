package mrwint.gbtasgen.metric.pokemon.gen2;

import static mrwint.gbtasgen.state.Gameboy.curGb;

import java.util.Arrays;

import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.state.Register;
import mrwint.gbtasgen.util.Util;

public class Gen2CheckDVMetric implements StateResettingMetric {
	private int initialMove;
	private int minAtkDV;
	private int minDefDV;
	private int minSpdDV;
	private int minSpcDV;
	private Integer[] whitelist;

	public Gen2CheckDVMetric(int initialMove, int minAtkDV, int minDefDV, int minSpdDV, int minSpcDV, Integer... whitelist) {
		this.initialMove = initialMove;
		this.minAtkDV = minAtkDV;
		this.minDefDV = minDefDV;
		this.minSpdDV = minSpdDV;
		this.minSpcDV = minSpcDV;
		this.whitelist = whitelist;
	}

	@Override
	public int getMetricInternal() {
		if(initialMove != 0)
			Util.runToNextInputFrame();
		Util.runToAddressNoLimit(0, initialMove, curGb.pokemon.afterDVGenerationAddress);
		int bc = curGb.getRegister(Register.BC);
		int atk = (bc >> 12) & 0xF;
		int def = (bc >> 8) & 0xF;
		int spd = (bc >> 4) & 0xF;
		int spc = (bc >> 0) & 0xF;

//		System.out.println("DVs: atk "+atk+", def "+def+", spd "+spd+", spc "+spc);

		if(atk < minAtkDV) return 0;
		if(def < minDefDV) return 0;
		if(spd < minSpdDV) return 0;
		if(spc < minSpcDV) return 0;

		if(whitelist.length > 0 && !Arrays.asList(whitelist).contains(bc)) return 0;

		System.out.println("DVs: " + Util.toHex(bc, 4));

		return 1;
	}
}