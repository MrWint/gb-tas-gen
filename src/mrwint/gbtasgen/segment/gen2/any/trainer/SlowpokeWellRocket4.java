package mrwint.gbtasgen.segment.gen2.any.trainer;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.fight.EndFightSegment;
import mrwint.gbtasgen.segment.fight.InitFightSegment;
import mrwint.gbtasgen.segment.fight.KillEnemyMonSegment;
import mrwint.gbtasgen.segment.fight.KillEnemyMonSegment.EnemyMoveDesc;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.state.StateBuffer;


public class SlowpokeWellRocket4 extends Segment {

	SequenceSegment sequence;
	
	public SlowpokeWellRocket4() {
		List<Segment> segments = new ArrayList<Segment>();

		segments.add(new InitFightSegment(3, 139));
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment(); // koffing
//			kems.enemyMoveDesc = new EnemyMoveDesc[] {EnemyMoveDesc.missWith(123)}; // Smog
			kems.enemyMoveDesc = new EnemyMoveDesc[] {EnemyMoveDesc.hitWith(139, false, false, false, 1, true)}; // Smog
			kems.maxOwnDamage = 0;
			kems.attackCount[3][0] = 1; // 1x water gun
			kems.attackCount[3][1] = 1; // 1x water gun crit
			kems.numExpGainers = 2; // level up to 15
			kems.onlyPrintInfo = false;
			segments.add(kems);
		}
		segments.add(new EndFightSegment(2));
		
		sequence = new SequenceSegment(segments.toArray(new Segment[0]));
	}
	
	@Override
	public StateBuffer execute(StateBuffer in) throws Throwable {
		return sequence.execute(in);
	}
}
