package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.NamingSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;

public class OakSpeechBlue extends SeqSegment {

	@Override
	public void execute() {
    seq(new EflSkipTextsSegment(13));

    seqEflButton(Move.A);
    seq(new NamingSegment("B", true));
    seqEflButton(Move.START);

		seq(new EflSkipTextsSegment(5));

		seqEflButton(Move.A);
		seq(new NamingSegment("b", true));
		seqEflButton(Move.START);

		seq(new EflSkipTextsSegment(7));
		seq(new EflTextSegment());
	}
}
