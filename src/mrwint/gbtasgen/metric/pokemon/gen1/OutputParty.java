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
      int exp = (curGb.readMemory(baseAdd + 14) << 16) + (curGb.readMemory(baseAdd + 15) << 8) + curGb.readMemory(baseAdd + 16);
      int hpEV = (curGb.readMemory(baseAdd + 17) << 8) + curGb.readMemory(baseAdd + 18);
      int atkEV = (curGb.readMemory(baseAdd + 19) << 8) + curGb.readMemory(baseAdd + 20);
      int defEV = (curGb.readMemory(baseAdd + 21) << 8) + curGb.readMemory(baseAdd + 22);
      int spdEV = (curGb.readMemory(baseAdd + 23) << 8) + curGb.readMemory(baseAdd + 24);
      int spcEV = (curGb.readMemory(baseAdd + 25) << 8) + curGb.readMemory(baseAdd + 26);
      int hp = (curGb.readMemory(baseAdd + 34) << 8) + curGb.readMemory(baseAdd + 35);
      int atk = (curGb.readMemory(baseAdd + 36) << 8) + curGb.readMemory(baseAdd + 37);
      int def = (curGb.readMemory(baseAdd + 38) << 8) + curGb.readMemory(baseAdd + 39);
      int spd = (curGb.readMemory(baseAdd + 40) << 8) + curGb.readMemory(baseAdd + 41);
      int spc = (curGb.readMemory(baseAdd + 42) << 8) + curGb.readMemory(baseAdd + 43);
	    System.out.println("#" + (i+1) + ": " + PokemonUtil.getMonName(mon) + " Lvl " + curGb.readMemory(baseAdd + 33) + " Exp " + exp);
      System.out.println("Stats: HP " + hp + " Atk " + atk + " def " + def + " spd " + spd + " spc " + spc);
	    System.out.println("EVs: HP " + hpEV + " Atk " + atkEV + " def " + defEV + " spd " + spdEV + " spc " + spcEV);

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