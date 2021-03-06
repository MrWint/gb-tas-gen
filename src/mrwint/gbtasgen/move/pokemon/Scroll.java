package mrwint.gbtasgen.move.pokemon;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.move.SequenceMove;
import mrwint.gbtasgen.move.SkipInput;
import mrwint.gbtasgen.util.EflUtil;

public class Scroll {
	public static SequenceMove slow(int num, int withMove) {
    EflUtil.assertNoEfl();

		List<Move> moves = new ArrayList<Move>();
		int move = num > 0 ? Move.DOWN: Move.UP;
		if (num < 0)
			num = -num;
		if(num > 0)
			moves.add(new PressButton(move | (num == 1 ? withMove : 0)));
		for (int i=1; i<num; i++) {
			moves.add(new SkipInput(1));
			moves.add(new PressButton(move | (num == i+1 ? withMove : 0)));
		}
		return new SequenceMove(moves.toArray(new Move[0]));
	}

	public static SequenceMove fast(int num, int withMove) {
    EflUtil.assertNoEfl();

		List<Move> moves = new ArrayList<Move>();
		int move = num > 0 ? Move.DOWN: Move.UP;
		if (num < 0)
			num = -num;
		if(num > 0)
			moves.add(new PressButton(move | (num == 1 ? withMove : 0)));
		for (int i=1; i<num; i++)
			moves.add(new PressButton(move | ((i&1) == 0 ? Move.LEFT : Move.RIGHT) | (num == i+1 ? withMove : 0)));
		return new SequenceMove(moves.toArray(new Move[0]));
	}
}
