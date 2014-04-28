package mrwint.gbtasgen.segment;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.move.Gen1OverworldInteract;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.state.StateBuffer;

public class PokecenterSegment extends Segment {

	SequenceSegment sequence;
	
	public PokecenterSegment(boolean firstVisit) {
		List<Segment> segments = new ArrayList<Segment>();
		segments.add(new WalkToSegment(3, 3)); // walk to counter
		segments.add(new MoveSegment(new Gen1OverworldInteract(1))); // talk to nurse
		segments.add(new SkipTextsSegment(3));
		if(firstVisit)
			segments.add(new TextSegment(Move.B)); // extra line on first visit
		segments.add(Segment.press(Move.A)); // confirm healing mons
		segments.add(new TextSegment()); // need your mons
		segments.add(new SkipTextsSegment(3));
		segments.add(new WalkToSegment(3, 8, false)); // leave center

		sequence = new SequenceSegment(segments.toArray(new Segment[0]));
	}
	
	@Override
	public StateBuffer execute(StateBuffer in) throws Throwable {
		return sequence.execute(in);
	}
}
