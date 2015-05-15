package mrwint.gbtasgen.segment.pokemon.gen1.glitchless;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.pokemon.TextSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.NamingSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;

public class OakSpeech extends SeqSegment {

	SequenceSegment sequence;

	@Override
	public void execute() {
		seq(new SkipTextsSegment(13));

    seqButton(Move.A);
    seq(new NamingSegment("I"));
    seqButton(Move.START);

		seq(new SkipTextsSegment(5));

		seqButton(Move.A);
		seq(new NamingSegment("B"));
		seqButton(Move.START);

		seq(new SkipTextsSegment(7));
		seq(new TextSegment());
	}
}
