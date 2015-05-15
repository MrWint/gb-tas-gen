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

public class SaveCorrupt extends SeqSegment {

	SequenceSegment sequence;

	@Override
	public void execute() {
		seqButton(Move.START);
		seq(Segment.scrollA(-3)); // save
//		seq(new MoveSegment(new PressButton(Move.A, Metric.MENU_JOY, Move.B)));
		seq(new SkipTextsSegment(1, true)); // save
		seqMove(new Wait(30));
		seqMove(new HardResetMove());
		seqButton(Move.A);
		seqButton(Move.START);
		seqButton(Move.A);
		seqButton(Move.START);
		seqButton(Move.A);
	}
}
