package mrwint.gbtasgen.segment;

import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.state.StateBuffer;

public abstract class Segment {

	public abstract StateBuffer execute(StateBuffer in) throws Throwable;

	
	public static MoveSegment press(int move) {
		return new MoveSegment(new PressButton(move, Metric.DOWN_JOY));
	}
	
	public static MoveSegment menu(int move) {
		return new MoveSegment(new PressButton(move, Metric.MENU_JOY));
	}
}
