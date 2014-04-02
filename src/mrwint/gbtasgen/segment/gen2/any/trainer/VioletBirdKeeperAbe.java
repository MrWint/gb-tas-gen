package mrwint.gbtasgen.segment.gen2.any.trainer;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.WalkToSegment;
import mrwint.gbtasgen.segment.fight.EndFightSegment;
import mrwint.gbtasgen.segment.fight.InitFightSegment;
import mrwint.gbtasgen.segment.fight.KillEnemyMonSegment;
import mrwint.gbtasgen.segment.fight.KillEnemyMonSegment.EnemyMoveDesc;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.state.StateBuffer;


public class VioletBirdKeeperAbe extends Segment {

	SequenceSegment sequence;
	
	public VioletBirdKeeperAbe() {
		List<Segment> segments = new ArrayList<Segment>();
		
		segments.add(new InitFightSegment(2, 64));
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.enemyMoveDesc = new EnemyMoveDesc[] {EnemyMoveDesc.hitWith(64, false, false)}; //peck
//			kems.numEndOfTurnTexts = new int[]{0,2};
			kems.maxOwnDamage = 15; // 3*5
			kems.attackCount[0][1] = 3; // 3x scratch crit
			kems.numExpGainers = 2; // level up to 8
			kems.onlyPrintInfo = false;
			segments.add(kems);
		}
		segments.add(new EndFightSegment(1));

		segments.add(new WalkToSegment(4, 6, false));	// engage bird keeper rod

		sequence = new SequenceSegment(segments.toArray(new Segment[0]));
	}
	
	@Override
	public StateBuffer execute(StateBuffer in) throws Throwable {
		return sequence.execute(in);
	}
}
