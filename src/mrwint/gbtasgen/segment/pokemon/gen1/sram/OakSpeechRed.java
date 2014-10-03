package mrwint.gbtasgen.segment.pokemon.gen1.sram;

import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.TextSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;

public class OakSpeechRed extends SeqSegment {

	SequenceSegment sequence;

	@Override
	public void execute() {
		seq(new SkipTextsSegment(13));

		seq(Segment.scrollAF(1)); // name select "RED"

		seq(new SkipTextsSegment(5));

		seq(Segment.scrollAF(3));

		seq(new SkipTextsSegment(7));
		seq(new TextSegment());
	}
}
