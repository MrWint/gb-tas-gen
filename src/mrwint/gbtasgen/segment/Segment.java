package mrwint.gbtasgen.segment;

import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.move.SkipInput;
import mrwint.gbtasgen.move.Wait;
import mrwint.gbtasgen.move.pokemon.Scroll;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.state.StateBuffer;

/**
 * An operation executed on a {@link StateBuffer}, resulting in a new {@link StateBuffer}.
 */
public interface Segment {

	StateBuffer execute(StateBuffer in);

	/** Identity, does nothing. */
	public static final Segment NULL = (in) -> in;


	public static MoveSegment press(int move) {
		return new MoveSegment(new PressButton(move, Metric.DOWN_JOY));
	}
	public static MoveSegment repress(int move) {
		return new MoveSegment(new PressButton(move, Metric.PRESSED_JOY));
	}
	public static MoveSegment menu(int move) {
		return new MoveSegment(new PressButton(move, Metric.MENU_JOY));
	}
	public static MoveSegment wait(int frames) {
		return new MoveSegment(new Wait(frames));
	}
	public static MoveSegment skip(int num) {
		return new MoveSegment(new SkipInput(num));
	}
	public static MoveSegment scroll(int num) {
		return new MoveSegment(Scroll.slow(num, 0));
	}
	public static MoveSegment scrollFast(int num) {
		return new MoveSegment(Scroll.fast(num, 0));
	}
	public static SeqSegment scroll(final int num, final int move) {
		return new SeqSegment() {
			@Override
			protected void execute() {
				seq(scroll(num));
				seqButton(move);
			}
		};
	}
	public static SeqSegment scrollA(final int num) {
		return scroll(num, Move.A);
	}
	public static MoveSegment scrollAF(final int num) {
		return new MoveSegment(Scroll.slow(num, Move.A));
	}
	public static SeqSegment scrollFast(final int num, final int move) {
		return new SeqSegment() {
			@Override
			protected void execute() {
				seq(scrollFast(num));
				seqButton(move);
			}
		};
	}
	public static SeqSegment scrollFastA(final int num) {
		return scrollFast(num, Move.A);
	}
	public static MoveSegment scrollFastAF(final int num) {
		return new MoveSegment(Scroll.fast(num, Move.A));
	}
}
