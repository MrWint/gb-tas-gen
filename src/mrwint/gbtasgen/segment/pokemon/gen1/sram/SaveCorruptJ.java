package mrwint.gbtasgen.segment.pokemon.gen1.sram;

import mrwint.gbtasgen.move.HardResetMove;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.Wait;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.TextSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;

public class SaveCorruptJ extends SeqSegment {

	SequenceSegment sequence;

	@Override
	public void execute() {
		seqButton(Move.START);
		seq(Segment.scrollA(-3)); // save
		seq(new SkipTextsSegment(1, true)); // save
		seq(new TextSegment()); // saving
		seqMove(new Wait(7));
		seqMove(new HardResetMove());
		seqButton(Move.A);
		seqButton(Move.START);
		seqButton(Move.A);
		seqButton(Move.START);
		seqButton(Move.A);
	}
}
