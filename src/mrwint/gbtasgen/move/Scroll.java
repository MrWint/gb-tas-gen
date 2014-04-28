package mrwint.gbtasgen.move;

import java.util.ArrayList;
import java.util.List;

public class Scroll {
	public static SequenceMove slow(int num) {
		List<Move> moves = new ArrayList<Move>();
		int move = num > 0 ? Move.DOWN: Move.UP;
		if (num < 0)
			num = -num;
		if(num > 0)
			moves.add(new PressButton(move));
		for (int i=1; i<num; i++) {
			moves.add(new SkipInput(1));
			moves.add(new PressButton(move));
		}
		return new SequenceMove(moves.toArray(new Move[0]));
	}

	public static SequenceMove fast(int num) {
		List<Move> moves = new ArrayList<Move>();
		int move = num > 0 ? Move.DOWN: Move.UP;
		if (num < 0)
			num = -num;
		if(num > 0)
			moves.add(new PressButton(move));
		for (int i=1; i<num; i++)
			moves.add(new PressButton(move | ((i&1) == 0 ? Move.LEFT : Move.RIGHT)));
		return new SequenceMove(moves.toArray(new Move[0]));
	}
}
