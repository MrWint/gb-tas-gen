package mrwint.gbtasgen.segment.pokemon.gen1.ace;

import mrwint.gbtasgen.move.HardResetMove;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.Wait;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SequenceSegment;

public class SaveCorrupt extends SeqSegment {

	SequenceSegment sequence;

	@Override
	public void execute() {
		seqEflButton(Move.START);
		seqEflScrollA(-3); // save
		seq(new EflSkipTextsSegment(1, true)); // save
		seqMove(new Wait(32));
		seqMove(new HardResetMove());
		seqEflButton(Move.A);
		seqEflButton(Move.START);
		seqEflButton(Move.A);
		seqEflButton(Move.START);
		seqEflButton(Move.A);
	}
}
