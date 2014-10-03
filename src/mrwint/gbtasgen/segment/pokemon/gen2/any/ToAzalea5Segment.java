package mrwint.gbtasgen.segment.pokemon.gen2.any;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.metric.pokemon.CheckCatchMonMetric;
import mrwint.gbtasgen.metric.pokemon.CheckEncounterMetric;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.move.SkipInput;
import mrwint.gbtasgen.move.Wait;
import mrwint.gbtasgen.move.pokemon.gen2.OverworldInteract;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.TextSegment;
import mrwint.gbtasgen.segment.pokemon.WalkToSegment;
import mrwint.gbtasgen.segment.util.CheckMetricSegment;
import mrwint.gbtasgen.segment.util.DelayMoveSegment;
import mrwint.gbtasgen.segment.util.DelayMoveSegment.PressButtonFactory;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.state.StateBuffer;
import mrwint.gbtasgen.util.pokemon.PokemonUtil;

public class ToAzalea5Segment extends Segment {

	SequenceSegment sequence;

	public ToAzalea5Segment() {
		List<Segment> segments = new ArrayList<Segment>();

		{
			segments.add(new WalkToSegment(15, 13));	// align
			segments.add(new WalkToSegment(15, 12));	// step into grass
			segments.add(new DelayMoveSegment(
					new PressButtonFactory(Move.UP),
					new CheckMetricSegment(new CheckEncounterMetric(79 /*slowpoke*/, 0).withStartMove(Move.UP))));

			segments.add(new SkipTextsSegment(2));
			segments.add(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY))); // pack
			segments.add(new MoveSegment(new PressButton(Move.A))); // pack
			segments.add(new MoveSegment(new SkipInput(2))); // balls
			segments.add(new MoveSegment(new PressButton(Move.RIGHT, Metric.PRESSED_JOY))); // balls
			segments.add(new MoveSegment(new SkipInput(2))); // use
			segments.add(new MoveSegment(new PressButton(Move.A, Metric.PRESSED_JOY))); // use
			segments.add(new DelayMoveSegment(new PressButtonFactory(Move.A, Metric.PRESSED_JOY), new Segment() {

				@Override
				public StateBuffer execute(StateBuffer in) {
					in = new TextSegment(Move.A, false, 0).execute(in);
					in = new CheckMetricSegment(new CheckCatchMonMetric()).execute(in);
					in = new MoveSegment(new Wait(1), 0, 0).execute(in);
					return in;
				}
			}));
			segments.add(new SkipTextsSegment(3));					// skip caughttext
			segments.add(new MoveSegment(new PressButton(Move.A))); // dex
			segments.add(new MoveSegment(new PressButton(Move.B))); // dex
			segments.add(new SkipTextsSegment(1));					// no nick
		}

		if(PokemonUtil.isCrystal())
			segments.add(new WalkToSegment(15, 10, false));			// engage rocket
		else {
			segments.add(new WalkToSegment(14, 10));				// align
			segments.add(new WalkToSegment(14, 9));					// face rocket
			segments.add(new MoveSegment(new OverworldInteract()));	// engage rocket
		}

		sequence = new SequenceSegment(segments.toArray(new Segment[0]));
	}

	@Override
	public StateBuffer execute(StateBuffer in) {
		return sequence.execute(in);
	}
}
