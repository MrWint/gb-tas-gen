package mrwint.gbtasgen.segment.pokemon.gen1.old;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.metric.pokemon.CheckEncounterMetric;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.CatchMonSegment;
import mrwint.gbtasgen.segment.pokemon.WalkToSegment;
import mrwint.gbtasgen.segment.util.CheckMetricSegment;
import mrwint.gbtasgen.segment.util.DelayMoveSegment;
import mrwint.gbtasgen.segment.util.DelayMoveSegment.PressButtonFactory;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.state.StateBuffer;

public class CatchAbraSegment implements Segment {

	SequenceSegment sequence;

	public CatchAbraSegment() {
		List<Segment> segments = new ArrayList<Segment>();

		segments.add(new WalkToSegment(4,17));

		segments.add(new DelayMoveSegment(new PressButtonFactory(Move.DOWN), new CheckMetricSegment(new CheckEncounterMetric(148, 0))));
		segments.add(new CatchMonSegment(0)); // ball in 1st slot

		sequence = new SequenceSegment(segments.toArray(new Segment[0]));
	}

	@Override
	public StateBuffer execute(StateBuffer in) {
		return sequence.execute(in);
	}
}
