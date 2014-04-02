package mrwint.gbtasgen.move;

import mrwint.gbtasgen.state.State;

public abstract class DelayableMove extends Move {

	public int delayMove = 0;
	
	@Override
	public int execute() throws Throwable {
		return execute(0);
	}
	
	public int execute(int delay) throws Throwable {
		int steps = prepareMove(delay);
		steps += doMove();
		return steps;
	}
	
	State cachedState = null;
	int cachedSkips = -1;
	
	public void clearCache() {
		cachedState = null;
		cachedSkips = -1;
	}
	
	public int prepareMove(int skips) throws Throwable {
		return prepareMove(skips,false);
	}
	public int prepareMove(int skips, boolean useCache) throws Throwable {
		int skipsLeft = skips;
		boolean useCached = false;
		if(!useCache || cachedSkips > skips) 
			clearCache();
		else if(cachedState != null) {
			cachedState.restore();
			skipsLeft -= cachedSkips;
			useCached = true;
		}
		int ret = prepareMoveInternal(skipsLeft,useCached);
		cachedState = new State();
		cachedSkips = skips;
		return ret;
	}
	public abstract int prepareMoveInternal(int skips, boolean assumeOnSkip) throws Throwable;
	public abstract int doMove() throws Throwable;
}
