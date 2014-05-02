package mrwint.gbtasgen.segment;

import mrwint.gbtasgen.metric.CheckCatchMonMetric;
import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.Wait;
import mrwint.gbtasgen.segment.util.CheckMetricSegment;
import mrwint.gbtasgen.segment.util.DelayMoveSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.state.StateBuffer;

public class BallSuccessSegment extends DelayMoveSegment {
	
	public BallSuccessSegment() {
		super(new PressButtonFactory(Move.A, Metric.PRESSED_JOY), new Segment() {
			@Override
			public StateBuffer execute(StateBuffer sb) throws Throwable {
				sb = new TextSegment(Move.A,false,0).execute(sb); // generate some entropy (infinite size)
				sb = new CheckMetricSegment(new CheckCatchMonMetric()).execute(sb);
				sb = new MoveSegment(new Wait(1),0,0).execute(sb); // skip last frame of text box
				return sb;
			}
		},0,1);
	}
}
