package mrwint.gbtasgen.metric.pokemon.gen1;

import static mrwint.gbtasgen.state.Gameboy.curGb;

import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.util.pokemon.PokemonUtil;

public class OutputItems implements StateResettingMetric {

	@Override
	public int getMetricInternal() {

	  int numItems = curGb.readMemory(curGb.pokemon.numItemsAddress);

	  System.out.println("Money: " + ((curGb.readMemory(0xd347) >> 4) * 100000
        + (curGb.readMemory(0xd347) & 0xf) * 10000
        + (curGb.readMemory(0xd348) >> 4) * 1000
        + (curGb.readMemory(0xd348) & 0xf) * 100
        + (curGb.readMemory(0xd349) >> 4) * 10
        + (curGb.readMemory(0xd349) & 0xf) * 1));
	  for (int i = 0; i < numItems; i++) {
      int item = curGb.readMemory(curGb.pokemon.numItemsAddress + 2 * i + 1);
      int count = curGb.readMemory(curGb.pokemon.numItemsAddress + 2 * i + 2);
	    System.out.println("#" + (i+1) + ": " + PokemonUtil.getItemName(item) + " x" + count);
	  }
		return 1;
	}
}