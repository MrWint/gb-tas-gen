package mrwint.gbtasgen.metric.pokemon.gen1;

import static mrwint.gbtasgen.state.Gameboy.curGb;

import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.util.pokemon.PokemonUtil;

public class OutputBoxMons implements StateResettingMetric {

	@Override
	public int getMetricInternal() {

	  int curBox = curGb.readMemory(curGb.pokemon.curMonBoxAddress) & 0xf;

	  int numBoxMon = curGb.readMemory(0xDA80);

	  System.out.println("Box #" + (curBox + 1));
	  for (int i = 0; i < numBoxMon; i++) {
	    int mon = curGb.readMemory(0xDA80 + i + 1);
	    System.out.println("#" + (i+1) + ": " + PokemonUtil.getMonName(mon));
	  }
		return 1;
	}
}