package mrwint.gbtasgen.move.pokemon.gen2;

import static mrwint.gbtasgen.state.Gameboy.curGb;
import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.move.DelayUntil;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.move.WithMetric;
import mrwint.gbtasgen.util.Util;

public class OverworldInteract extends DelayUntil {

	public OverworldInteract() {
		super(new WithMetric(new PressButton(Move.A, Metric.DOWN_JOY), true, new OverworldInteractMetric()));
	}

	public static class OverworldInteractMetric implements StateResettingMetric {
		@Override
		public int getMetricInternal() {

			int add = curGb.step(Move.A,curGb.pokemon.owPlayerInputCheckAAddress);
			if(add == 0) {
				System.out.println("OverworldInteract: 0x477 not found");
				return 0;
			}
			add = Util.runToAddressLimit(0, Move.A, 500, curGb.pokemon.owPlayerInputNoActionAddress, curGb.pokemon.owPlayerInputActionAddress);
			if(add != curGb.pokemon.owPlayerInputActionAddress) {
				System.out.println("INFO: talking to overworld entity failed");
				return 0;
			}
			return 1;
		}
	}
}
