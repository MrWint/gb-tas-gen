package mrwint.gbtasgen.segment.gen2.any;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.move.SkipInput;
import mrwint.gbtasgen.move.gen2.OverworldInteract;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.WalkToSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.state.StateBuffer;

public class ToAzalea4Segment extends Segment {

	SequenceSegment sequence;
	
	public ToAzalea4Segment() {
		List<Segment> segments = new ArrayList<Segment>();
		
		segments.add(new WalkToSegment(17, 30));				// align
		segments.add(new WalkToSegment(17, 32, false));			// union cave -> route33

		segments.add(new WalkToSegment(8, 11, false));			// jump ledge
		segments.add(new WalkToSegment(-1, 14));				// route33 -> azalea

		segments.add(new WalkToSegment(9, 5, false));			// enter kurt's house
		segments.add(new WalkToSegment(3, 3));					// walk up to kurt
		segments.add(new MoveSegment(new OverworldInteract()));	// talk to kurt
		segments.add(new SkipTextsSegment(16));					// skip text
		segments.add(new WalkToSegment(3, 8, false));			// leave kurt's house
		segments.add(new MoveSegment(new SkipInput(2)));
		segments.add(new WalkToSegment(31, 7, false));			// enter slowpoke well
		
		sequence = new SequenceSegment(segments.toArray(new Segment[0]));
	}
	
	@Override
	public StateBuffer execute(StateBuffer in) {
		return sequence.execute(in);
	}
}
