package mrwint.gbtasgen.segment.pokemon;

import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.metric.pokemon.CheckCatchMonMetric;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.util.DelayMoveSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;

public class BallSuccessSegment extends DelayMoveSegment {
	
	public BallSuccessSegment() {
		super(new PressButtonFactory(Move.A, Metric.PRESSED_JOY), new SeqSegment() {
			@Override
			public void execute() {
				seqUnbounded(new TextSegment(Move.A, false)); // generate some entropy (infinite size)
				seqMetric(new CheckCatchMonMetric());
				seqWait(1); // skip last frame of text box
			}
		},0,1);
	}
}
