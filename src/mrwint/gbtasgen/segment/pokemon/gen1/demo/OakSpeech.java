package mrwint.gbtasgen.segment.pokemon.gen1.demo;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.pokemon.TextSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;

public class OakSpeech extends SeqSegment {

	SequenceSegment sequence;

	@Override
	public void execute() {
		seq(new SkipTextsSegment(13));
		
		seqButton(Move.DOWN);
		seqButton(Move.A); // name select "RED"

		seq(new SkipTextsSegment(5));

		seqButton(Move.DOWN);
		seqButton(Move.A); // name select "BLUE"

		seq(new SkipTextsSegment(7));
		seq(new TextSegment());
	}
}
