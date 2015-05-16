package mrwint.gbtasgen.move;

import static mrwint.gbtasgen.state.Gameboy.curGb;
import mrwint.gbtasgen.state.State;

public class DelayUntil extends Move {

	private Move move;

	public DelayUntil(Move move) {
		this.move = move;
		if (!move.isDelayable())
			throw new RuntimeException("move not delayable");
	}

	@Override
	public int getInitialKey() {
		return move.getInitialKey();
	}
	@Override
	public boolean isDelayable() {
		return true;
	}
	@Override
	public boolean isCachable() {
		return move.isCachable();
	}

	@Override
	public void clearCache() {
		move.clearCache();
	}

	@Override
	public void prepareInternal(int skips, boolean assumeOnSkip) {
		//System.out.println("DelayUntil "+skips);

		if(isCachable()) {
			if (!assumeOnSkip) {
				move.prepareInternal(0, false);
				while(true) {
					State s = curGb.newState();
					boolean ret = move.doMove();
					curGb.restore(s);
					if(ret)
						break;
					move.prepareInternal(1, true);
				}
			}
			while(skips-- > 0) {
				boolean ret;
				do {
					move.prepareInternal(1, true);
					State s = curGb.newState();
					ret = move.doMove();
					curGb.restore(s);
				} while(!ret);
			}
		} else {
			State init = curGb.newState();
			int delay = -1;
			do {
				boolean ret;
				do {
					++delay;
					move.prepareInternal(delay, false);
					ret = move.doMove();
					curGb.restore(init);
				} while(!ret);
			}
			while(skips-- > 0);
			move.prepareInternal(delay, false);
		}
		//System.out.println("delayed "+delay+" steps until condition met ("+skips+" skips)");
	}

	@Override
	public boolean doMove() {
		return move.doMove();
	}
}
