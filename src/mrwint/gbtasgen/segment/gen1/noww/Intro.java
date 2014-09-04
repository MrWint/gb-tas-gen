package mrwint.gbtasgen.segment.gen1.noww;

import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.move.DelayableMove;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.util.FindShortestSequenceSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;

public class Intro extends SeqSegment {

	@Override
	protected void execute() {
		seq(new FindShortestSequenceSegment(new DelayableMove[]{
			Move.press(Move.START),
			Move.press(Move.A),
			Move.press(Move.START),
			Move.press(Move.A)
		}, Metric.TRUE));
	}
}
