package mrwint.gbtasgen.segment.pokemon.gen2.any;

import static mrwint.gbtasgen.metric.comparator.Comparator.EQUAL;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.metric.pokemon.CheckEncounterMetric;
import mrwint.gbtasgen.move.DelayUntil;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.move.SkipInput;
import mrwint.gbtasgen.move.WithMetric;
import mrwint.gbtasgen.move.pokemon.gen2.OverworldInteract;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.TextSegment;
import mrwint.gbtasgen.segment.pokemon.WalkToSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.state.StateBuffer;

public class ToGoldenrod1Segment extends Segment {

	SequenceSegment sequence;

	public ToGoldenrod1Segment() {
		List<Segment> segments = new ArrayList<Segment>();

		segments.add(new WalkToSegment(1, 11, false));	// azalea -> gate
		segments.add(new WalkToSegment(-1, 5, false));	// gate -> ilex forest

		segments.add(new WalkToSegment(14, 32));		// face farfetch'd
		segments.add(new MoveSegment(new OverworldInteract()));	// scare farfetch'd
		segments.add(new SkipTextsSegment(2));	// scare farfetch'd

		segments.add(new WalkToSegment(15, 24));		// face farfetch'd
		segments.add(new MoveSegment(new OverworldInteract()));	// scare farfetch'd
		segments.add(new SkipTextsSegment(1));	// scare farfetch'd

		segments.add(new WalkToSegment(15, 28));		// face farfetch'd
		segments.add(new MoveSegment(new OverworldInteract()));	// scare farfetch'd
		segments.add(new SkipTextsSegment(1));	// scare farfetch'd

		segments.add(new WalkToSegment(12, 35));		// align
		segments.add(new WalkToSegment(11, 35));		// face farfetch'd
		segments.add(new MoveSegment(new OverworldInteract()));	// scare farfetch'd
		segments.add(new SkipTextsSegment(1));	// scare farfetch'd

		segments.add(new WalkToSegment(5, 29));			// align
		segments.add(new MoveSegment(new PressButton(Move.UP)));	// face
		segments.add(new MoveSegment(new DelayUntil(new WithMetric(new PressButton(Move.UP), true, new CheckEncounterMetric().withStartMove(Move.UP), EQUAL, 0))));	// face
		segments.add(new MoveSegment(new OverworldInteract()));	// talk
		segments.add(new SkipTextsSegment(14));			// talk

		segments.add(new WalkToSegment(8, 29));			// align
		segments.add(new WalkToSegment(8, 26));			// face tree
		segments.add(new MoveSegment(new PressButton(Move.START)));	// open menu
		segments.add(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY)));	// select pack
		segments.add(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY)));	// select pack
		segments.add(new MoveSegment(new PressButton(Move.A, Metric.PRESSED_JOY)));		// select pack
		segments.add(new MoveSegment(new SkipInput(3)));
		segments.add(new MoveSegment(new PressButton(Move.LEFT, Metric.PRESSED_JOY)));	// select TMs/HMs
		segments.add(new MoveSegment(new SkipInput(2)));
		segments.add(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY)));	// select HM1
		segments.add(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY)));	// select HM1
		segments.add(new MoveSegment(new PressButton(Move.A, Metric.PRESSED_JOY)));		// select HM1
		segments.add(new MoveSegment(new PressButton(Move.A, Metric.PRESSED_JOY)));		// use
		segments.add(new SkipTextsSegment(2));
		segments.add(new SkipTextsSegment(1, true)); // teach HM
//		segments.add(new MoveSegment(new PressButton(Move.UP, Metric.PRESSED_JOY)));	// select Bellsprout
//		segments.add(new MoveSegment(new PressButton(Move.UP, Metric.PRESSED_JOY)));	// select Bellsprout
		segments.add(new MoveSegment(new PressButton(Move.A, Metric.PRESSED_JOY)));		// Croconaw
//		segments.add(new SkipTextsSegment(1));											// Bellsprout learned CUT
		{ // overwrite scratch with cut
			segments.add(new SkipTextsSegment(5));
			segments.add(new SkipTextsSegment(1, true)); // overwrite
			segments.add(new TextSegment(Move.B));
			segments.add(new MoveSegment(new PressButton(Move.A)));	// scratch
			segments.add(new TextSegment()); // 1,2, and poof
			segments.add(new MoveSegment(new SkipInput(8)));
			segments.add(new MoveSegment(new PressButton(Move.B))); // confirm
			segments.add(new SkipTextsSegment(3));
		}
		segments.add(new MoveSegment(new PressButton(Move.B, Metric.PRESSED_JOY)));		// leave
		{ // use cut from menu
			segments.add(new MoveSegment(new SkipInput(2)));
			segments.add(new MoveSegment(new PressButton(Move.UP, Metric.PRESSED_JOY)));	// select mon
			segments.add(new MoveSegment(new PressButton(Move.A, Metric.PRESSED_JOY)));		// select mon

			segments.add(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY)));	// select egg
			segments.add(new MoveSegment(new PressButton(Move.A, Metric.PRESSED_JOY)));		// select egg
			segments.add(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY)));	// switch
			segments.add(new MoveSegment(new PressButton(Move.A, Metric.PRESSED_JOY)));		// switch
			segments.add(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY)));	// select rattata
			segments.add(new MoveSegment(new PressButton(Move.A, Metric.PRESSED_JOY)));		// select rattata

			segments.add(new MoveSegment(new PressButton(Move.UP, Metric.PRESSED_JOY)));	// select rattata
			segments.add(new MoveSegment(new PressButton(Move.A, Metric.PRESSED_JOY)));		// select rattata
			segments.add(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY)));	// select move
			segments.add(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY)));	// select move
			segments.add(new MoveSegment(new PressButton(Move.A, Metric.PRESSED_JOY)));		// select move
			segments.add(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY)));	// select tackle
			segments.add(new MoveSegment(new PressButton(Move.A, Metric.PRESSED_JOY)));		// select tackle
			segments.add(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY)));	// select growl
			segments.add(new MoveSegment(new PressButton(Move.A, Metric.PRESSED_JOY)));		// select growl
			segments.add(new MoveSegment(new PressButton(Move.B, Metric.PRESSED_JOY)));		// leave
//
			segments.add(new MoveSegment(new PressButton(Move.UP, Metric.PRESSED_JOY)));	// select Croconaw
//			segments.add(new MoveSegment(new PressButton(Move.UP, Metric.PRESSED_JOY)));	// select Bellsprout
			segments.add(new MoveSegment(new PressButton(Move.A, Metric.PRESSED_JOY)));		// select Croconaw
			segments.add(new MoveSegment(new PressButton(Move.A, Metric.PRESSED_JOY)));		// select cut
			segments.add(new SkipTextsSegment(1));
		}
		segments.add(new WalkToSegment(1, 5, false));	// ilex -> gate
		segments.add(new WalkToSegment(4, 0, false));	// gate -> route34
		segments.add(new WalkToSegment(8, -1));	// route34 -> goldenrod

		sequence = new SequenceSegment(segments.toArray(new Segment[0]));
	}

	@Override
	public StateBuffer execute(StateBuffer in) {
		return sequence.execute(in);
	}
}
