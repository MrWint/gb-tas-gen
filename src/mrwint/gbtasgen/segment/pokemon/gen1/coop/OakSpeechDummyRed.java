package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import static mrwint.gbtasgen.move.Move.A;
import static mrwint.gbtasgen.move.Move.START;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.NamingSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;

public class OakSpeechDummyRed extends SeqSegment {

	@Override
	public void execute() {
    seqEflButton(START); // logo
    seqEflButton(A); // intro cutscene
    seqEflButton(START); // title screen
    seqEflButton(A); // new game

    seq(new EflSkipTextsSegment(13));

    seqEflButton(A);
    seq(new NamingSegment("R"));
    seqEflButton(START);

		seq(new EflSkipTextsSegment(5));

		seqEflButton(A);
		seq(new NamingSegment("r"));
		seqEflButton(START);

		seq(new EflSkipTextsSegment(7));
		seq(new EflTextSegment());
	}
}
