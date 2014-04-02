package mrwint.gbtasgen.move;

import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.state.State;

public class IntroOptionsMove extends DelayableMove {
	public static SequenceMove get(int x3delay, int x4delay) {
		return new SequenceMove(
			new PressButton(Move.DOWN,Metric.PRESSED_JOY),
			new PressButton(Move.A,Metric.PRESSED_JOY), // options

			new PressButton(Move.LEFT,Metric.PRESSED_JOY), // text speed
			new PressButton(Move.DOWN,Metric.PRESSED_JOY),
			new PressButton(Move.LEFT,Metric.PRESSED_JOY), // battle scene
			new PressButton(Move.DOWN,Metric.PRESSED_JOY),
			new PressButton(Move.LEFT,Metric.PRESSED_JOY), // battle style

			new SkipInput(x3delay),
			new PressButton(Move.B,Metric.PRESSED_JOY),

			new SkipInput(x4delay),
			new PressButton(Move.A,Metric.PRESSED_JOY)
			);
	}
	
	private DelayableMove[] prepareMoves = {
			get(0,0),
			null,
			null,
			get(1,0),
			get(0,1),
			null,
			get(2,0),
			get(1,1),
			get(0,2),
			get(3,0),
	};
	
	State[] lastCachedStates = new State[4];
	int[] lastCachedRets = new int[4];
	int lastCachedSkips = Integer.MIN_VALUE;
	
	@Override
	public void clearCache() {
		lastCachedStates = new State[4];
		lastCachedSkips = Integer.MIN_VALUE;
	}

	@Override
	public int prepareMove(int skips, boolean useCache) throws Throwable {
		if(skips > 0)
			skips += 2;
		if(skips > 4)
			skips += 1;
		
		int ret;
//		if(!useCache || skips != lastCachedSkips+1)
//			clearCache();
//		if(lastCachedStates[skips%4] != null) {
//			lastCachedStates[skips%4].restore();
//			ret = lastCachedRets[skips%4];
//			ret += new SkipInput(1).execute();
//		} else {
			ret = prepareMoveNoCache(skips);
//		}
//		lastCachedSkips = skips;
//		lastCachedStates[skips%4] = new State();
//		lastCachedRets[skips%4] = ret;
		return ret;
	}
	
	private int prepareMoveNoCache(int skips) throws Throwable {
		int additionalx4 = 0;
		while(skips>9) {
			additionalx4++;
			skips-=4;
		}
		return prepareMoves[skips].prepareMove(additionalx4);
	}

	@Override
	public int prepareMoveInternal(int skips, boolean assumeOnSkip)
			throws Throwable {
		return 0;
	}

	@Override
	public int doMove() throws Throwable {
		return new PressButton(Move.A,Metric.PRESSED_JOY).doMove();
	}

	@Override
	public int getInitialKey() {
		return Move.DOWN;
	}
}
