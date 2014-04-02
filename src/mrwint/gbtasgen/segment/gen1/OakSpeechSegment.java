package mrwint.gbtasgen.segment.gen1;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.NamingSegment;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.TextSegment;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.state.StateBuffer;

public class OakSpeechSegment extends Segment {

	SequenceSegment sequence;
	
	public OakSpeechSegment(String ownName, String rivalName) {
		List<Segment> segments = new ArrayList<Segment>();
		
		segments.add(new SkipTextsSegment(13));
		
		if(ownName == null)
			segments.add(Segment.press(Move.DOWN));
		segments.add(Segment.press(Move.A)); // name select
		if(ownName != null) {
			segments.add(new NamingSegment(ownName));
			segments.add(Segment.press(Move.START));
		}

		segments.add(new SkipTextsSegment(5));

		if(rivalName == null)
			segments.add(Segment.press(Move.DOWN));
		segments.add(Segment.press(Move.A)); // name select
		if(rivalName != null) {
			segments.add(new NamingSegment(rivalName));
			segments.add(Segment.press(Move.START));
		}

		segments.add(new SkipTextsSegment(7));

		segments.add(new TextSegment());

		sequence = new SequenceSegment(segments.toArray(new Segment[0]));
	}
	
	@Override
	public StateBuffer execute(StateBuffer in) throws Throwable {
		return sequence.execute(in);
	}
}
