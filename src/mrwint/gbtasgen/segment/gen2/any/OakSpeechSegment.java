package mrwint.gbtasgen.segment.gen2.any;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.segment.NamingSegment;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.TextSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.state.StateBuffer;
import mrwint.gbtasgen.util.Util;


public class OakSpeechSegment extends Segment {

	SequenceSegment sequence;
	
	public OakSpeechSegment() {
		List<Segment> segments = new ArrayList<Segment>();

		if(Util.isCrystal())
			segments.add(new SkipTextsSegment(1, true)); // pick boy
		segments.add(new SkipTextsSegment(3));
		segments.add(new SkipTextsSegment(4, true)); // 10:00 confirmed
		segments.add(new SkipTextsSegment(18));
		segments.add(new MoveSegment(new PressButton(Move.A))); // choose own name
		segments.add(new NamingSegment("A"));
		segments.add(new SkipTextsSegment(7));
		segments.add(new TextSegment()); // seeing you soon text
		
		sequence = new SequenceSegment(segments.toArray(new Segment[0]));
	}
	
	@Override
	public StateBuffer execute(StateBuffer in) throws Throwable {
		return sequence.execute(in);
	}
}
