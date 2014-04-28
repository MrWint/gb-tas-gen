package mrwint.gbtasgen.move;

import mrwint.gbtasgen.main.RomInfo;
import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.util.Util;

public class Gen2OverworldInteract extends DelayableMove {

	@Override
	public int prepareMoveInternal(int skips, boolean assumeOnSkip)
			throws Throwable {
		int steps = 0;
		while(true) {
			steps += Util.runToFirstDifference(0, Move.A, Metric.DOWN_JOY);
			State s = new State();
			int add = State.step(Move.A,RomInfo.rom.owPlayerInputCheckAAddress);
			if(add == 0) {
				System.out.println("OverworldInteract: 0x477 not found ("+steps+")");
				s.restore();
				State.step();
				steps++;
				continue;
			}
			add = Util.runToAddress2(0,Move.A,RomInfo.rom.owPlayerInputNoActionAddress,RomInfo.rom.owPlayerInputActionAddress);
			if(add != RomInfo.rom.owPlayerInputActionAddress) {
				System.out.println("INFO: talking to overworld entity failed"+" ("+steps+")");
				s.restore();
				State.step();
				steps++;
				continue;
			}
			s.restore();
			if(skips-- <= 0)
				return steps;
			State.step();
			steps++;
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
