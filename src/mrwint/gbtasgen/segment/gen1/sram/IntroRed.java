package mrwint.gbtasgen.segment.gen1.sram;

import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.metric.TrainerIDMetric;
import mrwint.gbtasgen.move.DelayableMove;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.util.FindShortestSequenceSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;

public class IntroRed extends SeqSegment {

	@Override
	protected void execute() {
//		seq(Move.START);
//		seq(Move.A);
//		seq(Move.START);
//		seq(Move.A);
		seq(new FindShortestSequenceSegment(new DelayableMove[]{
			Move.press(Move.A),
			Move.press(Move.START),
			Move.press(Move.A),
			Move.press(Move.START),
		}, new TrainerIDMetric(0x64bb, 0x64bd, 0x64be, 0x64c1, 0x64c2, 0x64c3, 0x64c6)));
	}
}
