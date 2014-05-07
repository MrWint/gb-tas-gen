package mrwint.gbtasgen.segment.gen1.old;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.move.gen1.OverworldInteract;
import mrwint.gbtasgen.segment.ResetAndContinueSegment;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.TextSegment;
import mrwint.gbtasgen.segment.WalkToSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.state.StateBuffer;

public class PewterSegment extends Segment {

	SequenceSegment sequence;
	
	public PewterSegment() {
		List<Segment> segments = new ArrayList<Segment>();
		segments.add(Segment.press(Move.START)); // open menu
		for(int i=0;i<3;i++)
			segments.add(new MoveSegment(new PressButton(Move.UP,Metric.PRESSED_JOY))); // move to "save"
		segments.add(Segment.press(Move.START)); // close menu
		
		segments.add(new WalkToSegment(23, 17)); // enter pewter mart

		segments.add(new WalkToSegment(3, 5));
		segments.add(new WalkToSegment(2, 5));
		segments.add(new MoveSegment(new OverworldInteract(1)));
		{
			segments.add(new SkipTextsSegment(1, true)); // buy
			segments.add(new TextSegment());
			{
				segments.add(Segment.press(Move.DOWN));
				segments.add(Segment.press(Move.DOWN | Move.LEFT)); // escape rope
				segments.add(Segment.press(Move.A)); // escape rope
				segments.add(Segment.repress(Move.A)); // buy
				segments.add(new SkipTextsSegment(1)); // confirmation text
				segments.add(new SkipTextsSegment(1, true)); // "yes"
				segments.add(new SkipTextsSegment(1)); // thank you text
			}
			{
				segments.add(Segment.press(Move.A)); // pokeball
				segments.add(Segment.scroll(-5)); // x6
				segments.add(Segment.press(Move.A)); // buy
				segments.add(new SkipTextsSegment(1)); // confirmation text
				segments.add(new SkipTextsSegment(1, true)); // "yes"
				segments.add(new SkipTextsSegment(1)); // thank you text
			}
			segments.add(Segment.repress(Move.B)); // cancel
			segments.add(new SkipTextsSegment(2)); // cancel + bye
		}

		segments.add(new WalkToSegment(3,8,false)); // leave mart

		segments.add(new WalkToSegment(37, 18)); // walk up to pewter skip
		segments.add(new SkipTextsSegment(4)); // pewter skip text
		segments.add(new MoveSegment(new PressButton(Move.START), 0)); // open menu
		segments.add(Segment.press(Move.A)); // save
		segments.add(new SkipTextsSegment(1, true)); // say "yes"
		segments.add(new ResetAndContinueSegment()); // reset
		segments.add(new SkipTextsSegment(2)); // pewter skip text
		segments.add(new WalkToSegment(40, 18)); // leave pewter

		sequence = new SequenceSegment(segments.toArray(new Segment[0]));
	}
	
	@Override
	public StateBuffer execute(StateBuffer in) {
		return sequence.execute(in);
	}
}
