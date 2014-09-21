package mrwint.gbtasgen.move.gen2;

import mrwint.gbtasgen.main.RomInfo;
import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.move.DelayUntil;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.move.WithMetric;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.util.Util;

public class OverworldInteract extends DelayUntil {

	public OverworldInteract() {
		super(new WithMetric(new PressButton(Move.A, Metric.DOWN_JOY), true, new OverworldInteractMetric()));
	}

	public static class OverworldInteractMetric implements StateResettingMetric {
		@Override
		public int getMetricInternal() {
			int add = State.step(Move.A,RomInfo.rom.owPlayerInputCheckAAddress);
			if(add == 0) {
				System.out.println("OverworldInteract: 0x477 not found");
				return 0;
			}
			add = Util.runToAddress2(0,Move.A,RomInfo.rom.owPlayerInputNoActionAddress,RomInfo.rom.owPlayerInputActionAddress);
			if(add != RomInfo.rom.owPlayerInputActionAddress) {
				System.out.println("INFO: talking to overworld entity failed");
				return 0;
			}
			return 1;
		}
	}
}
