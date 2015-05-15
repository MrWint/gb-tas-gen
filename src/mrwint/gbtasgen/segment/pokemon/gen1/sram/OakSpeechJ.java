package mrwint.gbtasgen.segment.pokemon.gen1.sram;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.TextSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.NamingSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;

public class OakSpeechJ extends SeqSegment {

	SequenceSegment sequence;

	@Override
	public void execute() {
		seq(new SkipTextsSegment(11));

		seqButton(Move.DOWN);
		seqButton(Move.A); // name select "RED"

		seq(new SkipTextsSegment(4));

		seqButton(Move.A);
		seqButton(Move.SELECT);
		seqButton(Move.A);
		seqButton(Move.LEFT);
		seqButton(Move.A);
		seqButton(Move.LEFT);
		seqButton(Move.A);
		seqButton(Move.LEFT);
		seqButton(Move.A);
		seqButton(Move.START);

		seq(new SkipTextsSegment(4));
		seq(new TextSegment());
	}
}
