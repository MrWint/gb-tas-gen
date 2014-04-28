package mrwint.gbtasgen.segment;

import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.move.Scroll;
import mrwint.gbtasgen.move.SkipInput;
import mrwint.gbtasgen.move.Wait;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.state.StateBuffer;

public abstract class Segment {

	public abstract StateBuffer execute(StateBuffer in) throws Throwable;

	
	public static MoveSegment press(int move) {
		return new MoveSegment(new PressButton(move, Metric.DOWN_JOY));
	}
	public static MoveSegment press(int move, int bufferSize) {
		return new MoveSegment(new PressButton(move, Metric.DOWN_JOY), MoveSegment.MAX_DELAY, bufferSize);
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
		return new MoveSegment(Scroll.slow(num));
	}
	public static MoveSegment scrollFast(int num) {
		return new MoveSegment(Scroll.fast(num));
	}
}
