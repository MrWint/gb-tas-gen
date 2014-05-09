package mrwint.gbtasgen.segment.gen2.any;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.metric.CheckCatchMonMetric;
import mrwint.gbtasgen.metric.CheckEncounterMetric;
import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.move.SkipInput;
import mrwint.gbtasgen.move.Wait;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.TextSegment;
import mrwint.gbtasgen.segment.WalkToSegment;
import mrwint.gbtasgen.segment.util.CheckMetricSegment;
import mrwint.gbtasgen.segment.util.DelayMoveSegment;
import mrwint.gbtasgen.segment.util.DelayMoveSegment.PressButtonFactory;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.state.StateBuffer;


public class ToAzalea21Segment extends Segment {

	SequenceSegment sequence;
	
	public ToAzalea21Segment() {
		List<Segment> segments = new ArrayList<Segment>();

		segments.add(new WalkToSegment(5, 25));	// align
		segments.add(new WalkToSegment(5, 26));	// step into grass
		segments.add(new DelayMoveSegment(
				new PressButtonFactory(Move.DOWN),
				new CheckMetricSegment(new CheckEncounterMetric(19 /*rattata*/, 8).withStartMove(Move.DOWN))));
		
		segments.add(new SkipTextsSegment(2));
		segments.add(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY))); // pack
		segments.add(new MoveSegment(new PressButton(Move.A))); // pack
		segments.add(new MoveSegment(new SkipInput(2))); // balls
		segments.add(new MoveSegment(new PressButton(Move.RIGHT, Metric.PRESSED_JOY))); // balls
		segments.add(new MoveSegment(new SkipInput(2))); // use
		segments.add(new MoveSegment(new PressButton(Move.A, Metric.PRESSED_JOY))); // use
		segments.add(new DelayMoveSegment(new PressButtonFactory(Move.A, Metric.PRESSED_JOY), new Segment() {
			
			@Override
			public StateBuffer execute(StateBuffer in) {
				in = new TextSegment(Move.A, false, 0).execute(in);
				in = new CheckMetricSegment(new CheckCatchMonMetric()).execute(in);
				in = new MoveSegment(new Wait(1), 0, 0).execute(in);
				return in;
			}
		}));
		segments.add(new SkipTextsSegment(3));					// skip caughttext
		segments.add(new MoveSegment(new PressButton(Move.A))); // dex
		segments.add(new MoveSegment(new PressButton(Move.B))); // dex
		segments.add(new SkipTextsSegment(1));					// no nick
		
		sequence = new SequenceSegment(segments.toArray(new Segment[0]));
	}
	
	@Override
	public StateBuffer execute(StateBuffer in) {
		return sequence.execute(in);
	}
}
