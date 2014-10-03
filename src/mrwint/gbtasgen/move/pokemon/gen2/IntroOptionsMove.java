package mrwint.gbtasgen.move.pokemon.gen2;

import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.move.DelayableMove;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.move.SequenceMove;
import mrwint.gbtasgen.move.SkipInput;

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
	
	private Move[] prepareMoves = {
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
	
	private void prepareMoveNoCache(int skips) {
		int additionalx4 = 0;
		while(skips>9) {
			additionalx4++;
			skips-=4;
		}
		prepareMoves[skips].prepare(additionalx4);
	}

	@Override
	public void prepareInternal(int skips, boolean assumeOnSkip) {
		if(skips > 0)
			skips += 2;
		if(skips > 4)
			skips += 1;
		prepareMoveNoCache(skips);
	}

	@Override
	public boolean doMove() {
		return new PressButton(Move.A,Metric.PRESSED_JOY).doMove();
	}

	@Override
	public int getInitialKey() {
		return Move.DOWN;
	}
}
