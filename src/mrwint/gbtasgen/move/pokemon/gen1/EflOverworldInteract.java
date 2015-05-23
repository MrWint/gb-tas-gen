package mrwint.gbtasgen.move.pokemon.gen1;

import static mrwint.gbtasgen.state.Gameboy.curGb;
import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.move.DelayUntil;
import mrwint.gbtasgen.move.EflPressButton;
import mrwint.gbtasgen.move.EflWithMetric;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.util.EflUtil;
import mrwint.gbtasgen.util.EflUtil.PressMetric;

public class EflOverworldInteract extends DelayUntil {

	public EflOverworldInteract(int textID) {
		super(new EflWithMetric(new EflPressButton(Move.A, PressMetric.PRESSED), new OverworldInteractMetric(textID)));
	}

	public static class OverworldInteractMetric implements StateResettingMetric {
		private int textID;
		public OverworldInteractMetric(int textID) {
			this.textID = textID;
		}
		@Override
		public int getMetricInternal() {
			int add = EflUtil.runToAddressLimit(0, 0, 10, curGb.pokemon.owPlayerInputCheckAAddress); // after IsSpriteOrSignInFrontOfPlayer call
			if(add == 0) {
				System.out.println("OverworldInteract: IsSpriteOrSignInFrontOfPlayer call not found");
				return 0;
			}
			int id = curGb.readMemory(curGb.pokemon.owInteractionTargetAddress); // text ID of entity talked to
			if(textID != -1 && textID != id) {
				System.out.println("WARNING: text ID "+id+" does not match expected ID "+textID);
				return 0;
			}
			add = EflUtil.runToAddressLimit(0, 0, 10, curGb.pokemon.owInteractionSuccessfulAddress, curGb.pokemon.owLoopAddress); // before DisplayTextID call
			if(add != curGb.pokemon.owInteractionSuccessfulAddress) {
				System.out.println("ERROR: talking to "+textID+" failed");
				return 0;
			}
			return 1;
		}
	}
}
