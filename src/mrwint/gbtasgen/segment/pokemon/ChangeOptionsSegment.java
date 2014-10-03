package mrwint.gbtasgen.segment.pokemon;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.state.StateBuffer;

public class ChangeOptionsSegment extends Segment{
	
	SequenceSegment sequence;

	public ChangeOptionsSegment(boolean inMainMenu, int maxDelay) {
		List<Segment> segments = new ArrayList<Segment>();
		
		if(inMainMenu) {
			segments.add(new MoveSegment(new PressButton(Move.DOWN,Metric.PRESSED_JOY), maxDelay));
			segments.add(new MoveSegment(new PressButton(Move.A,Metric.PRESSED_JOY), maxDelay)); // options

			segments.add(new MoveSegment(new PressButton(Move.LEFT,Metric.PRESSED_JOY), maxDelay)); // text speed
			segments.add(new MoveSegment(new PressButton(Move.DOWN,Metric.PRESSED_JOY), maxDelay));
			segments.add(new MoveSegment(new PressButton(Move.LEFT,Metric.PRESSED_JOY), maxDelay)); // battle scene
			segments.add(new MoveSegment(new PressButton(Move.DOWN,Metric.PRESSED_JOY), maxDelay));
			segments.add(new MoveSegment(new PressButton(Move.LEFT,Metric.PRESSED_JOY), maxDelay)); // battle style

			segments.add(new MoveSegment(new PressButton(Move.B,Metric.PRESSED_JOY), maxDelay));
		} else {
			segments.add(new MoveSegment(new PressButton(Move.START), maxDelay));
			segments.add(new MoveSegment(new PressButton(Move.UP,Metric.PRESSED_JOY), maxDelay));
			segments.add(new MoveSegment(new PressButton(Move.UP,Metric.PRESSED_JOY), maxDelay));
			segments.add(new MoveSegment(new PressButton(Move.A,Metric.PRESSED_JOY), maxDelay)); // options

			segments.add(new MoveSegment(new PressButton(Move.LEFT,Metric.PRESSED_JOY), maxDelay)); // text speed
			segments.add(new MoveSegment(new PressButton(Move.DOWN,Metric.PRESSED_JOY), maxDelay));
			segments.add(new MoveSegment(new PressButton(Move.LEFT,Metric.PRESSED_JOY), maxDelay)); // battle scene
			segments.add(new MoveSegment(new PressButton(Move.DOWN,Metric.PRESSED_JOY), maxDelay));
			segments.add(new MoveSegment(new PressButton(Move.LEFT,Metric.PRESSED_JOY), maxDelay)); // battle style

			segments.add(new MoveSegment(new PressButton(Move.B,Metric.PRESSED_JOY), maxDelay));
			segments.add(new MoveSegment(new PressButton(Move.START,Metric.PRESSED_JOY), maxDelay));
		}
		
		sequence = new SequenceSegment(segments.toArray(new Segment[0]));
	}
	
	@Override
	public StateBuffer execute(StateBuffer in) {
		return sequence.execute(in);
	}
}
