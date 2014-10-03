package mrwint.gbtasgen.segment.pokemon.gen1.sram;

import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.move.HardResetMove;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.move.Wait;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;

public class SaveCorruptRed extends SeqSegment {

	SequenceSegment sequence;

	@Override
	public void execute() {
//		seq(Move.DOWN);
		seq(Move.START);
		seq(Segment.scrollA(-3)); // save
//		seq(new MoveSegment(new PressButton(Move.A, Metric.MENU_JOY, Move.B)));
		seq(new SkipTextsSegment(1, true)); // save
		seq(new Wait(21));
		seq(new HardResetMove());
		seq(Move.A);
		seq(Move.START);
		seq(Move.A);
		seq(Move.START);
		seq(Move.A);
	}
}
