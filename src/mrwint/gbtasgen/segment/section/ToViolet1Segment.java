package mrwint.gbtasgen.segment.section;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.OverworldInteract;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.move.SkipInput;
import mrwint.gbtasgen.segment.NamingSegment;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.TextSegment;
import mrwint.gbtasgen.segment.WalkToSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.state.StateBuffer;
import mrwint.gbtasgen.util.Util;

public class ToViolet1Segment extends Segment {

	SequenceSegment sequence;
	
	public ToViolet1Segment() {
		List<Segment> segments = new ArrayList<Segment>();
		
		segments.add(new SkipTextsSegment(5));			// rival speech
		segments.add(new WalkToSegment(40, 7));			// cherrygrove -> route 29
		segments.add(new WalkToSegment(14, 13, false));	// jump ledge
		segments.add(new WalkToSegment(42, 9));			// align ledge
		segments.add(new WalkToSegment(43, 9, false));	// jump ledge
		segments.add(new WalkToSegment(60, 9));			// route 29 -> new bark
		segments.add(new WalkToSegment(6, 3, false));	// enter lab
		segments.add(new WalkToSegment(4, 5, false));	// enter cutscene trigger
		segments.add(new SkipTextsSegment(8));			// police speech
		segments.add(new NamingSegment("B"));			// name the rival "B"
		segments.add(new SkipTextsSegment(2));			// police speech
		segments.add(new WalkToSegment(5, 3));			// go to elm
		segments.add(new MoveSegment(new PressButton(Move.UP))); // face elm
		segments.add(new MoveSegment(new PressButton(Move.UP))); // face elm
		segments.add(new MoveSegment(new OverworldInteract())); // talk to elm
		segments.add(new SkipTextsSegment(27));			// elm speech
		
		segments.add(new WalkToSegment(4, 7));			// align for cutscene
		segments.add(new WalkToSegment(4, 8, false));	// trigger cutscene
		segments.add(new SkipTextsSegment(2));			// assistant speech
		segments.add(new TextSegment());				// got pokeballs
		segments.add(new SkipTextsSegment(6));			// assistant speech
		segments.add(new WalkToSegment(4, 12, false));	// leave lab

		segments.add(new MoveSegment(new SkipInput(2)));
		segments.add(new WalkToSegment(-1, 9));						// New Bark -> route 29
		segments.add(new WalkToSegment(53, 9, false));				// trigger cutscene
		segments.add(new SkipTextsSegment(7));						// tutorial speech
		segments.add(new WalkToSegment(8, 6, false));				// jump ledge
		segments.add(new WalkToSegment(-1, 6));						// route 29 -> cherrygrove
		segments.add(new WalkToSegment(17, -1));					// cherrygrove -> route 30

		segments.add(new WalkToSegment(5, 24, false));		// engage youngster mikey

		sequence = new SequenceSegment(segments.toArray(new Segment[0]));
	}
	
	@Override
	public StateBuffer execute(StateBuffer in) throws Throwable {
		return sequence.execute(in);
	}
}
