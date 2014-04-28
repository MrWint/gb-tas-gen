package mrwint.gbtasgen.segment.gen1;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.main.RomInfo;
import mrwint.gbtasgen.metric.Gen1CheckDVMetric;
import mrwint.gbtasgen.move.Gen1OverworldInteract;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.TextSegment;
import mrwint.gbtasgen.segment.WalkToSegment;
import mrwint.gbtasgen.segment.util.CheckMetricSegment;
import mrwint.gbtasgen.segment.util.DelayMoveSegment;
import mrwint.gbtasgen.segment.util.DelayMoveSegment.PressButtonFactory;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.state.StateBuffer;

public class ChooseStarterSegment extends Segment {

	SequenceSegment sequence;
	
	public ChooseStarterSegment() {
		List<Segment> segments = new ArrayList<Segment>();

		segments.add(new TextSegment()); // OAK: don't go out
		// oak in grass
		segments.add(new SkipTextsSegment(6));
		// go to oak's house
		segments.add(new SkipTextsSegment(18));

		segments.add(new MoveSegment(RomInfo.rom.getWalkStep(Move.RIGHT,false,true),0));
		segments.add(new MoveSegment(new Gen1OverworldInteract(2)));

		segments.add(Segment.press(Move.B)); // cancel dex
		segments.add(Segment.press(Move.A)); // cancel dex

		// chose mon text
		segments.add(new SkipTextsSegment(1)); // do you want?
		segments.add(new SkipTextsSegment(1, true)); // want!
		segments.add(new SkipTextsSegment(1)); // energetic

		segments.add(new TextSegment(Move.B));
		segments.add(new SkipTextsSegment(1)); // want to give a nick
		segments.add(new TextSegment(Move.B, true, 0)); // to charmander?
		segments.add(Segment.press(Move.A, 0)); // (yes)
//		segments.add(new SkipTextsSegment(1, true)); // to charmander (yes)
		segments.add(Segment.press(Move.A, 0)); // "A"
//		segments.add(Segment.press(Move.START)); // name it "A"
		segments.add(new DelayMoveSegment(new PressButtonFactory(Move.START), new CheckMetricSegment(new Gen1CheckDVMetric(0, 15, 0, 15, 15))));

		segments.add(new SkipTextsSegment(2)); // rival choose mon
		segments.add(new WalkToSegment(5, 6)); // try leaving
		segments.add(new SkipTextsSegment(4)); // rival challenges you
		

		sequence = new SequenceSegment(segments.toArray(new Segment[0]));
	}
	
	@Override
	public StateBuffer execute(StateBuffer in) throws Throwable {
		return sequence.execute(in);
	}
}
