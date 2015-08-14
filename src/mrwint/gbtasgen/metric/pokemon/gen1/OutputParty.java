package mrwint.gbtasgen.metric.pokemon.gen1;

import static mrwint.gbtasgen.state.Gameboy.curGb;

import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.util.pokemon.PokemonUtil;

public class OutputParty implements StateResettingMetric {

	@Override
	public int getMetricInternal() {
	  
	  int numPartyMon = curGb.readMemory(curGb.pokemon.numPartyMonAddress);
	  
	  for (int i = 0; i < numPartyMon; i++) {
	    int mon = curGb.readMemory(curGb.pokemon.numPartyMonAddress + i + 1);
      int baseAdd = curGb.pokemon.numPartyMonAddress + i * 44 + 8;
	    System.out.println("#" + (i+1) + ": " + PokemonUtil.getMonName(mon) + " Lvl " + curGb.readMemory(baseAdd + 33));
	    
	    for (int j = 0; j < 4; j++) {
	      int move = curGb.readMemory(baseAdd + 8 + j);
	      if (move != 0) {
	        System.out.println( "Move #" + (j+1) + ": " + PokemonUtil.getMoveName(move) + " - " + curGb.readMemory(baseAdd + 29 + j) + " PP left");
	      }
	    }
	    System.out.println();
	  }
		return 1;
	}
}