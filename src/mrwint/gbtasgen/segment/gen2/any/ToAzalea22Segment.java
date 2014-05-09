package mrwint.gbtasgen.segment.gen2.any;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.metric.CheckEncounterMetric;
import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.WalkToSegment;
import mrwint.gbtasgen.segment.util.CheckMetricSegment;
import mrwint.gbtasgen.segment.util.DelayMoveSegment;
import mrwint.gbtasgen.segment.util.DelayMoveSegment.PressButtonFactory;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.state.StateBuffer;
import mrwint.gbtasgen.util.Util;


public class ToAzalea22Segment extends Segment {

	SequenceSegment sequence;
	
	public ToAzalea22Segment() {
		List<Segment> segments = new ArrayList<Segment>();

		segments.add(new WalkToSegment(3, 31));	// align
		segments.add(new WalkToSegment(3, 32));	// step into grass
		segments.add(new DelayMoveSegment(
				new PressButtonFactory(Move.DOWN),
				new CheckMetricSegment(new CheckEncounterMetric(69 /*bellsprout*/, 0).withStartMove(Move.DOWN))));
		
		segments.add(new SkipTextsSegment(2));
		segments.add(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY))); // run
		segments.add(new MoveSegment(new PressButton(Move.RIGHT, Metric.PRESSED_JOY))); // run
		segments.add(new MoveSegment(new PressButton(Move.A))); // run
		segments.add(new SkipTextsSegment(1));

		segments.add(new WalkToSegment(7, 71, false));			// trigger text
		segments.add(new SkipTextsSegment(5));					// skip text
		segments.add(new WalkToSegment(6, 79, false));			// route32 -> union cave
		
		if(!Util.isCrystal())
			segments.add(new WalkToSegment(13, 8, false));		// engage hiker russell
		
		sequence = new SequenceSegment(segments.toArray(new Segment[0]));
	}
	
	@Override
	public StateBuffer execute(StateBuffer in) {
		return sequence.execute(in);
	}
}
