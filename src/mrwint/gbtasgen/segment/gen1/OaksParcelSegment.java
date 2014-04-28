package mrwint.gbtasgen.segment.gen1;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.move.Gen1OverworldInteract;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.TextSegment;
import mrwint.gbtasgen.segment.WalkToSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.state.StateBuffer;

public class OaksParcelSegment extends Segment {

	SequenceSegment sequence;
	
	public OaksParcelSegment() {
		List<Segment> segments = new ArrayList<Segment>();
		segments.add(new WalkToSegment(5, 12, false)); // leave oaks lab
		segments.add(new WalkToSegment(10, -1)); // walk out of town
		segments.add(new WalkToSegment(10, -1)); // walk out to viridian
		segments.add(new WalkToSegment(29, 19)); // walk into mart
		segments.add(new SkipTextsSegment(5)); // take parcel
		segments.add(new WalkToSegment(3, 8, false)); // walk outside
		segments.add(new WalkToSegment(26, 27, false)); // viridian ledge
		segments.add(new WalkToSegment(21, 36)); // leave viridian
		segments.add(new WalkToSegment(14, 19, false)); // ledge jump 1
		segments.add(new WalkToSegment(11, 27, false)); // ledge jump 2
		segments.add(new WalkToSegment(11, 36)); // enter pallet town
		segments.add(new WalkToSegment(12, 11)); // enter oaks lab
		segments.add(new WalkToSegment(4, 2)); // stand left of oak
		segments.add(new WalkToSegment(5, 2, false)); // face towards oak
		//segments.add(new WalkToSegment(5, 3)); // stand in front of oak
		segments.add(new MoveSegment(new Gen1OverworldInteract(5))); // talk to oak
		segments.add(new SkipTextsSegment(41)); // oak parcel speech
		segments.add(new WalkToSegment(4, 12, false)); // leave oaks lab
		//segments.add(new WalkToSegment(5, 12, false)); // leave oaks lab
		segments.add(new WalkToSegment(10, -1)); // leave pallet town
		segments.add(new WalkToSegment(10, -1)); // enter viridian
		segments.add(new WalkToSegment(18, -1)); // leave viridian
		segments.add(new WalkToSegment(3, 43)); // enter viridian forest house
		segments.add(new WalkToSegment(5, 0)); // enter viridian forest

		sequence = new SequenceSegment(segments.toArray(new Segment[0]));
	}
	
	@Override
	public StateBuffer execute(StateBuffer in) throws Throwable {
		return sequence.execute(in);
	}
}
