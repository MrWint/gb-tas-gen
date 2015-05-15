package mrwint.gbtasgen.segment.pokemon.gen1.glitchless;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.util.SeqSegment;

public class Intro extends SeqSegment {

	@Override
	protected void execute() {
    seqButton(Move.START);
    seqButton(Move.A);
    seqButton(Move.START);
    seqButton(Move.A);
	}
}
