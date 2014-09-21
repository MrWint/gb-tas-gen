package mrwint.gbtasgen.move.gen1;

import mrwint.gbtasgen.Gb;
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

	public OverworldInteract(int textID) {
		super(new WithMetric(new PressButton(Move.A, Metric.PRESSED_JOY), true, new OverworldInteractMetric(textID)));
	}

	public static class OverworldInteractMetric implements StateResettingMetric {
		private int textID;
		public OverworldInteractMetric(int textID) {
			this.textID = textID;
		}
		@Override
		public int getMetricInternal() {
			int add = State.step(Move.A,RomInfo.rom.owPlayerInputCheckAAddress); // after IsSpriteOrSignInFrontOfPlayer call
			if(add == 0) {
				System.out.println("OverworldInteract: IsSpriteOrSignInFrontOfPlayer call not found");
				return 0;
			}
			int id = Gb.readMemory(RomInfo.rom.owInteractionTargetAddress); // text ID of entity talked to
			if(textID != -1 && textID != id) {
				System.out.println("WARNING: text ID "+id+" does not match expected ID "+textID);
				return 0;
			}
			add = Util.runToAddress2(0,Move.A,RomInfo.rom.owInteractionSuccessfulAddress,RomInfo.rom.owLoopAddress); // before DisplayTextID call
			//add = State.step(Move.A,0x496); // before DisplayTextID call
			if(add != RomInfo.rom.owInteractionSuccessfulAddress) {
				System.out.println("ERROR: talking to "+textID+" failed");
				return 0;
			}
			return 1;
		}
	}
}
