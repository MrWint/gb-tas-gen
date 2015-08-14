package mrwint.gbtasgen.metric.pokemon.gen1;

import static mrwint.gbtasgen.state.Gameboy.curGb;

import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.util.pokemon.PokemonUtil;

public class OutputItems implements StateResettingMetric {

	@Override
	public int getMetricInternal() {
	  
	  int numItems = curGb.readMemory(curGb.pokemon.numItemsAddress);
	  
	  for (int i = 0; i < numItems; i++) {
      int item = curGb.readMemory(curGb.pokemon.numItemsAddress + 2 * i + 1);
      int count = curGb.readMemory(curGb.pokemon.numItemsAddress + 2 * i + 2);
	    System.out.println("#" + (i+1) + ": " + PokemonUtil.getItemName(item) + " x" + count);
	  }
		return 1;
	}
}