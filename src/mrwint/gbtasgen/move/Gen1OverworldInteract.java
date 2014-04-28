package mrwint.gbtasgen.move;

import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.main.RomInfo;
import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.util.Util;

public class Gen1OverworldInteract extends DelayableMove {

	private int textID;

	public Gen1OverworldInteract(int textID) {
		this.textID = textID;
	}

	@Override
	public int prepareMoveInternal(int skips, boolean assumeOnSkip) {
		int steps = 0;
		while(true) {
			steps += Util.runToFirstDifference(0, Move.A, Metric.PRESSED_JOY);
			State s = new State();
			int add = State.step(Move.A,RomInfo.rom.owPlayerInputCheckAAddress); // after IsSpriteOrSignInFrontOfPlayer call
			if(add == 0) {
				System.out.println("OverworldInteract: IsSpriteOrSignInFrontOfPlayer call not found ("+steps+")");
				s.restore();
				State.step();
				steps++;
				continue;
			}
			int id = Gb.readMemory(RomInfo.rom.owInteractionTargetAddress); // text ID of entity talked to
			if(textID != -1 && textID != id) {
				System.out.println("WARNING: text ID "+id+" does not match expected ID "+textID+" ("+steps+")");
				s.restore();
				State.step();
				steps++;
				continue;
			}
			add = Util.runToAddress2(0,Move.A,RomInfo.rom.owInteractionSuccessfulAddress,RomInfo.rom.owLoopAddress); // before DisplayTextID call
			//add = State.step(Move.A,0x496); // before DisplayTextID call
			if(add != RomInfo.rom.owInteractionSuccessfulAddress) {
				System.out.println("ERROR: talking to "+textID+" failed"+" ("+steps+")");
				s.restore();
				State.step();
				steps++;
				continue;
			}
			s.restore();
			if(skips-- <= 0)
				return steps;
			State.step(); // skip input
			++steps;
		}
	}

	@Override
	public int getInitialKey() {
		return Move.A;
	}

	@Override
	public int doMove() throws Throwable {
		State.step(Move.A);
		return 1;
	}
}
