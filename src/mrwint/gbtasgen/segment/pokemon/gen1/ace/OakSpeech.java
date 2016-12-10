package mrwint.gbtasgen.segment.pokemon.gen1.ace;

import static mrwint.gbtasgen.state.Gameboy.curGb;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.NamingSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.state.Gameboy;
import sun.util.resources.en.CurrencyNames_en_GB;

public class OakSpeech extends SeqSegment {

	SequenceSegment sequence;

	@Override
	public void execute() {
    seq(new EflSkipTextsSegment(13));

		seqEflButton(Move.DOWN);
		seqEflButton(Move.A); // name select "RED"

		seq(new EflSkipTextsSegment(5));

		seqEflButton(Move.A); // custom name
		seq(new NamingSegment(" ♀π"));
		seqEflButton(Move.START); // choose

		seq(new EflSkipTextsSegment(7));
		seq(new EflTextSegment());
	}
}
