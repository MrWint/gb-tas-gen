package mrwint.gbtasgen.segment.gen2.any.trainer;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.fight.EndFightSegment;
import mrwint.gbtasgen.segment.fight.InitFightSegment;
import mrwint.gbtasgen.segment.fight.KillEnemyMonSegment;
import mrwint.gbtasgen.segment.fight.NewEnemyMonSegment;
import mrwint.gbtasgen.segment.fight.KillEnemyMonSegment.EnemyMoveDesc;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.state.StateBuffer;


public class UnionCaveHikerRussell extends Segment {

	SequenceSegment sequence;
	
	public UnionCaveHikerRussell() {
		List<Segment> segments = new ArrayList<Segment>();
		
		segments.add(new InitFightSegment(3, 33)); // tackle
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.hitWith(33, false, false, 1)}; // tackle
			kems.rageInitialVal = 1;
			kems.maxOwnDamage = 4;
			kems.attackCount[2][0] = 3; // 3x rage
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			kems.nextMonLevel = 6;
			segments.add(kems);
		}

		segments.add(NewEnemyMonSegment.any(33)); // next mon

		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.hitWith(33, false, false, 1)}; // tackle
			kems.rageInitialVal = 3;
			kems.maxOwnDamage = 2;
			kems.attackCount[2][0] = 1; // 1x rage
			kems.attackCount[2][1] = 1; // 1x rage crit
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			segments.add(kems);
		}

		segments.add(NewEnemyMonSegment.any(33)); // next mon

		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment(); // any move
			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(33)}; // tackle
			kems.rageInitialVal = 4;
			kems.rageMaxVal = 4;
			kems.maxOwnDamage = 0;
			kems.attackCount[2][1] = 2; // 2x rage crit
			kems.numExpGainers = 3; // level up to 13, learn water gun
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
