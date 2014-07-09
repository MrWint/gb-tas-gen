package mrwint.gbtasgen.segment.gen1.demo;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.TextSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;

public class OakSpeech extends SeqSegment {

	SequenceSegment sequence;

	@Override
	public void execute() {
		seq(new SkipTextsSegment(13));
		
		seq(Move.DOWN);
		seq(Move.A); // name select "RED"

		seq(new SkipTextsSegment(5));

		seq(Move.DOWN);
		seq(Move.A); // name select "BLUE"

		seq(new SkipTextsSegment(7));
		seq(new TextSegment());
	}
}
