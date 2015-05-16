package mrwint.gbtasgen.move;

import static mrwint.gbtasgen.state.Gameboy.curGb;
import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.state.State;


public abstract class Move {
	public static final int A      = 0x01;
	public static final int B      = 0x02;
	public static final int SELECT = 0x04;
	public static final int START  = 0x08;

	public static final int RIGHT  = 0x10;
	public static final int LEFT   = 0x20;
	public static final int UP     = 0x40;
	public static final int DOWN   = 0x80;

	public static final int RESET  = 0x800;

	public static PressButton press(int move) {
		return new PressButton(move, Metric.DOWN_JOY);
	}
	public static PressButton menu(int move) {
		return new PressButton(move, Metric.MENU_JOY);
	}


	State cachedState = null;
	int cachedSkips = -1;
	public void clearCache() {
		cachedState = null;
		cachedSkips = -1;
	}

	public boolean isDelayable() {
		return false;
	}
	public boolean isCachable() {
		return false;
	}
	public boolean isStateAltering() {
		return true;
	}
	public boolean execute() {
		return execute(0);
	}
	public boolean execute(int skips) {
		prepare(skips);
		return doMove();
	}
	public void prepare(int skips) {
		prepare(skips, false);
	}
	public void prepare(int skips, boolean useCache) {
		int skipsLeft = skips;
		boolean useCached = false;
		if(!isCachable() || !useCache || cachedSkips > skips)
			clearCache();
		else if(cachedState != null) {
		  curGb.restore(cachedState);
			skipsLeft -= cachedSkips;
			useCached = true;
		}
		prepareInternal(skipsLeft,useCached);
		if (isCachable()) {
			cachedState = curGb.newState();
			cachedSkips = skips;
		}
	}

	public void prepareInternal(int skips, boolean assumeOnSkip) {};
	public abstract boolean doMove();
	public abstract int getInitialKey();
}
