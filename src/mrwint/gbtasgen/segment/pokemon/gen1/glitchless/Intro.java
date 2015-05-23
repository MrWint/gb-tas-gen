package mrwint.gbtasgen.segment.pokemon.gen1.glitchless;

import static mrwint.gbtasgen.state.Gameboy.curGb;
import mrwint.gbtasgen.move.EflPressButton;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PrepMove;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.util.Util;

public class Intro extends SeqSegment {

	@Override
	protected void execute() {
    seqButton(Move.START);
    seqButton(Move.A);
    seqButton(Move.START);
    seqButton(Move.A);
	}
}
