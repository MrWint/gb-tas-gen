package mrwint.gbtasgen.segment.fight;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.TextSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.state.StateBuffer;

public class EndFightSegment extends Segment {

	SequenceSegment sequence;
	
	public EndFightSegment(int numEndTextScrolls) {
		List<Segment> segments = new ArrayList<Segment>();

		segments.add(new TextSegment()); // player defeated enemy
		segments.add(new MoveSegment(new PressButton(Move.B))); // confirm
		for(int i=0;i<numEndTextScrolls;i++) {
			segments.add(new TextSegment()); // end of fight texts
			segments.add(new MoveSegment(new PressButton(Move.B))); // confirm
		}
		segments.add(new TextSegment()); // player got money for winning
		segments.add(new MoveSegment(new PressButton(Move.B))); // confirm

		sequence = new SequenceSegment(segments.toArray(new Segment[0]));
	}
	
	@Override
	public StateBuffer execute(StateBuffer in) throws Throwable {
		return sequence.execute(in);
	}
}
