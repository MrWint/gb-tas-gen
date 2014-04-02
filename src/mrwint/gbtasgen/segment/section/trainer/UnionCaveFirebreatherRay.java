package mrwint.gbtasgen.segment.section.trainer;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.fight.EndFightSegment;
import mrwint.gbtasgen.segment.fight.InitFightSegment;
import mrwint.gbtasgen.segment.fight.KillEnemyMonSegment;
import mrwint.gbtasgen.segment.fight.KillEnemyMonSegment.EnemyMoveDesc;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.state.StateBuffer;


public class UnionCaveFirebreatherRay extends Segment {

	SequenceSegment sequence;
	
	public UnionCaveFirebreatherRay() {
		List<Segment> segments = new ArrayList<Segment>();
		
		segments.add(new InitFightSegment(3)); // any move
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.enemyMoveDesc = new EnemyMoveDesc[]{}; // tail whip
			kems.attackCount[3][1] = 1; // 1x water gun crit
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			segments.add(kems);
		}

		segments.add(new EndFightSegment(1));
		
		sequence = new SequenceSegment(segments.toArray(new Segment[0]));
	}
	
	@Override
	public StateBuffer execute(StateBuffer in) throws Throwable {
		return sequence.execute(in);
	}
}
