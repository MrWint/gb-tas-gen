package mrwint.gbtasgen.segment.pokemon.fight;

import static mrwint.gbtasgen.state.Gameboy.curGb;
import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.util.EflUtil;

public class EflCheckAdditionalTexts implements StateResettingMetric {
		@Override
		public int getMetricInternal() {
			int add = EflUtil.runToAddressNoLimit(0, 0, curGb.pokemon.fightEndTurnAddresses);
			if(add == curGb.pokemon.printLetterDelayJoypadAddress) {
				System.out.println("EflCheckAdditionalTexts: found additional PrintText!");
//				try { Thread.sleep(200); } catch (InterruptedException e) { }
//				for(int i=0;i<40;i++) {
//					curGb.step();
//					try { Thread.sleep(10); } catch (InterruptedException e) { }
//				}
//				try { Thread.sleep(200); } catch (InterruptedException e) { }
				return 0;
			}
			return 1;
		}
	}