package mrwint.gbtasgen.segment.pokemon.gen2.any;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.WalkToSegment;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.state.StateBuffer;

public class ToViolet15Segment extends Segment {

	SequenceSegment sequence;
	
	public ToViolet15Segment() {
		List<Segment> segments = new ArrayList<Segment>();
		
//		segments.add(new WalkToSegment(1, 12));	// align
//		segments.add(new WalkToSegment(1, 11));	// step into grass
//		segments.add(new DelayMoveSegment(
//				new PressButtonFactory(Move.UP),
//				new CheckMetricSegment(new CheckEncounterMetric(10 /*caterpie*/, 3, null, Move.UP, new Integer[]{7},null))));
//		
//		segments.add(new SkipTextsSegment(2));
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.enemyMoveDesc = new EnemyMoveDesc[]{};
//			kems.attackCount[0][1] = 1; // 1x scratch crit
//			kems.numExpGainers = 1; // no level up
//			kems.onlyPrintInfo = false;
//			segments.add(kems);
//		}
		
		segments.add(new WalkToSegment(6, -1, false));	// route 30 -> route 31

		sequence = new SequenceSegment(segments.toArray(new Segment[0]));
	}
	
	@Override
	public StateBuffer execute(StateBuffer in) {
		return sequence.execute(in);
	}
}
