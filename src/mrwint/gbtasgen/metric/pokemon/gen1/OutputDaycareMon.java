package mrwint.gbtasgen.metric.pokemon.gen1;

import static mrwint.gbtasgen.state.Gameboy.curGb;

import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.util.pokemon.PokemonUtil;

public class OutputDaycareMon implements StateResettingMetric {

	@Override
	public int getMetricInternal() {

	  boolean hasDaycareMon = curGb.readMemory(0xda48) == 1;
	  if (!hasDaycareMon)
	    System.out.println("No Mon in Daycare");
	  else {
      int baseAdd = 0xda5f;
      int mon = curGb.readMemory(baseAdd);
      int exp = (curGb.readMemory(baseAdd + 14) << 16) + (curGb.readMemory(baseAdd + 15) << 8) + curGb.readMemory(baseAdd + 16);
      System.out.println("Daycare Mon: " + PokemonUtil.getMonName(mon) + " Lvl " + curGb.readMemory(baseAdd + 3) + " Exp " + exp);

      for (int j = 0; j < 4; j++) {
        int move = curGb.readMemory(baseAdd + 8 + j);
        if (move != 0) {
          System.out.println( "Move #" + (j+1) + ": " + PokemonUtil.getMoveName(move) + " - " + curGb.readMemory(baseAdd + 29 + j) + " PP left");
        }
      }
	  }
    System.out.println();
		return 1;
	}
}