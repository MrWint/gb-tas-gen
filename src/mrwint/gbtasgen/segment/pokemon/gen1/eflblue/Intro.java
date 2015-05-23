package mrwint.gbtasgen.segment.pokemon.gen1.eflblue;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.util.SeqSegment;

public class Intro extends SeqSegment {

	@Override
	protected void execute() {
    seqEflButton(Move.START);
    seqEflButton(Move.A);
    seqEflButton(Move.START);
    seqEflButton(Move.A);
	}
}
