package mrwint.gbtasgen.segment.pokemon.gen2.any;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.WalkToSegment;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.state.StateBuffer;
import mrwint.gbtasgen.util.pokemon.PokemonUtil;


public class ToAzalea3Segment extends Segment {

	SequenceSegment sequence;

	public ToAzalea3Segment() {
		List<Segment> segments = new ArrayList<Segment>();

//		{
//			segments.add(new MoveSegment(new SkipInput(1)));
//			segments.add(new MoveSegment(new PressButton(Move.START)));	// open menu
//			segments.add(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY)));	// select mons
//			segments.add(new MoveSegment(new PressButton(Move.A, Metric.PRESSED_JOY)));		// select mons
//			segments.add(new MoveSegment(new PressButton(Move.A, Metric.PRESSED_JOY)));		// select toto
//			segments.add(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY)));	// switch
//			segments.add(new MoveSegment(new PressButton(Move.A, Metric.PRESSED_JOY)));		// switch
//			segments.add(new MoveSegment(new PressButton(Move.UP, Metric.PRESSED_JOY)));	// bellsprout
//			segments.add(new MoveSegment(new PressButton(Move.A, Metric.PRESSED_JOY)));		// bellsprout
//			segments.add(new MoveSegment(new PressButton(Move.B, Metric.PRESSED_JOY)));		// leave
//			segments.add(new MoveSegment(new PressButton(Move.START, Metric.PRESSED_JOY)));	// leave
//		}
//		segments.add(new WalkToSegment(13, 9));					// align
//
//		segments.add(new WalkToSegment(16, 11));	// align
//		segments.add(new WalkToSegment(16, 12));	// step into grass
//		segments.add(new DelayMoveSegment(
//				new PressButtonFactory(Move.DOWN),
//				new CheckMetricSegment(new CheckEncounterMetric(41 /*zubat*/, 7, null, Move.DOWN, new Integer[]{9,10,11}, new Integer[]{18,19,20,21,22})))); // alt: 11 def; 22- hp
////				new CheckMetricSegment(new CheckEncounterMetric(41 /*zubat*/, 7, null, Move.DOWN, new Integer[]{9,10}, null)))); // alt: 11 def; 22- hp
//		segments.add(new SkipTextsSegment(2));
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.enemyMoveDesc = new EnemyMoveDesc[]{};
//			kems.attackCount[0][1] = 1; // 1x scratch crit
//			kems.numExpGainers = 1; // no level up
//			kems.onlyPrintInfo = false;
//			segments.add(kems);
//		}

		if(!PokemonUtil.isCrystal())
			segments.add(new WalkToSegment(16, 27, false));		// engage firebreather ray

		sequence = new SequenceSegment(segments.toArray(new Segment[0]));
	}

	@Override
	public StateBuffer execute(StateBuffer in) {
		return sequence.execute(in);
	}
}
