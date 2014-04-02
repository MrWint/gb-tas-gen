package mrwint.gbtasgen.segment.gen2.any;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.move.OverworldInteract;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.WalkToSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.state.StateBuffer;

public class GetCoincase1Segment extends Segment {

	SequenceSegment sequence;
	
	public GetCoincase1Segment() {
		List<Segment> segments = new ArrayList<Segment>();

		segments.add(new WalkToSegment(11, 29, false));			// enter gate
		segments.add(new WalkToSegment(5, 25, false));			// enter underground

		segments.add(new WalkToSegment(5, 33));					// align
		segments.add(new WalkToSegment(5, 32));					// face super nerd eric
		segments.add(new MoveSegment(new OverworldInteract()));	// engage super nerd eric
		
		sequence = new SequenceSegment(segments.toArray(new Segment[0]));
	}
	
	@Override
	public StateBuffer execute(StateBuffer in) throws Throwable {
		return sequence.execute(in);
	}
}
