package mrwint.gbtasgen.segment.gen1.sram;

import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.metric.TrainerIDMetric;
import mrwint.gbtasgen.move.DelayableMove;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.util.FindShortestSequenceSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;

public class Intro extends SeqSegment {

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
		}, new TrainerIDMetric(0x6411, 0x6413, 0x6415, 0x6416, 0x6417, 0x641A, 0x641B, 0x641D, 0x645A, 0x6471, 0x6476, 0x64CF, 0x64D0, 0x64EA)));
	}
}
