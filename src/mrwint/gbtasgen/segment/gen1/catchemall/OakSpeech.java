package mrwint.gbtasgen.segment.gen1.catchemall;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.TextSegment;
import mrwint.gbtasgen.segment.gen1.common.NamingSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;

public class OakSpeech extends SeqSegment {

	SequenceSegment sequence;

	@Override
	public void execute() {
		seq(new SkipTextsSegment(13));
		
		seq(new PressButton(Move.A)); // name select
		
		seq(new NamingSegment("BCVXZQr"));
		seq(Segment.press(Move.START));

		seq(new SkipTextsSegment(5));

		seq(Segment.press(Move.DOWN));
		seq(Segment.press(Move.A)); // name select "BLUE"

		seq(new SkipTextsSegment(7));
		seq(new TextSegment());
	}
}
