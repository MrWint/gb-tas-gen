package mrwint.gbtasgen.segment.gen1.sram;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.TextSegment;
import mrwint.gbtasgen.segment.gen1.common.NamingSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;

public class OakSpeechJ extends SeqSegment {

	SequenceSegment sequence;

	@Override
	public void execute() {
		seq(new SkipTextsSegment(11));

		seq(Move.DOWN);
		seq(Move.A); // name select "RED"

		seq(new SkipTextsSegment(4));

		seq(Move.A);
		seq(Move.SELECT);
		seq(Move.A);
		seq(Move.LEFT);
		seq(Move.A);
		seq(Move.LEFT);
		seq(Move.A);
		seq(Move.LEFT);
		seq(Move.A);
		seq(Move.START);

		seq(new SkipTextsSegment(4));
		seq(new TextSegment());
	}
}
