package mrwint.gbtasgen.metric.pokemon.gen1.slots;

import static mrwint.gbtasgen.state.Gameboy.curGb;
import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.util.EflUtil;

public class SlotsWheel1Metric implements StateResettingMetric {

	@Override
	public int getMetricInternal() {
//	  System.out.println("SW1");
	  do {
	    EflUtil.runToAddressNoLimit(0, 0, 0x374cc); // after SlotMachine_374df
      EflUtil.runToAddressNoLimit(0, 0, 0x374c9); // before SlotMachine_374df
      if (curGb.readMemory(0xcd3d) != 1) // wrong wheel
        return 0;
//	    System.out.println(curGb.readMemory(0xcd4d));
	  } while (curGb.readMemory(0xcd4d) != 0);
	  int wheelPos = curGb.readMemory(0xcd3e);
//    System.out.println(curGb.readMemory(0xcd3e));
    if (wheelPos == 0x10 || wheelPos == 0x1a || wheelPos == 0x6 || wheelPos == 0x11 || wheelPos == 0x1b || wheelPos == 0x7) // top
      return 1;
    if (wheelPos == 0x12 || wheelPos == 0x1c || wheelPos == 0x8 || wheelPos == 0x13 || wheelPos == 0x1d || wheelPos == 0x9) // middle
      return 1;
    if (wheelPos == 0x14 || wheelPos == 0x0 || wheelPos == 0xa || wheelPos == 0x15 || wheelPos == 0x1 || wheelPos == 0xb) // bottom
      return 1;
    return 0;
	}
}
