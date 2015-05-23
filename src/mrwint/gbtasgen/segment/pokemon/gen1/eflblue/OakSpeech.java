package mrwint.gbtasgen.segment.pokemon.gen1.eflblue;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.pokemon.TextSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.NamingSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;

public class OakSpeech extends SeqSegment {

	SequenceSegment sequence;

	@Override
	public void execute() {
    seq(new EflSkipTextsSegment(13));

    seqEflButton(Move.A);
    seq(new NamingSegment("I", true));
    seqEflButton(Move.START);

		seq(new EflSkipTextsSegment(5));

		seqEflButton(Move.A);
		seq(new NamingSegment("B", true));
		seqEflButton(Move.START);

		seq(new EflSkipTextsSegment(7));
		seq(new EflTextSegment());
	}
}
