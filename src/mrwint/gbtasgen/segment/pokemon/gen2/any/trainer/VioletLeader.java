package mrwint.gbtasgen.segment.pokemon.gen2.any.trainer;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.TextSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.InitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.NewEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment.EnemyMoveDesc;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.state.StateBuffer;


public class VioletLeader extends Segment {

	SequenceSegment sequence;
	
	public VioletLeader() {
		List<Segment> segments = new ArrayList<Segment>();

		segments.add(new InitFightSegment(9, 33 /*tackle*/));
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment(); // pidgey
			kems.enemyMoveDesc = new EnemyMoveDesc[] {EnemyMoveDesc.hitWith(33, false, false, 1)};
			kems.maxOwnDamage = 6;
			kems.rageInitialVal = 1;
			kems.attackCount[2][0] = 3; // 3x rage
			kems.numExpGainers = 2; // level up to 10
			kems.onlyPrintInfo = false;
			segments.add(kems);
		}

		segments.add(NewEnemyMonSegment.any(16)); // next mon
//		segments.add(new NewEnemyMonSegment(33)); // next mon

		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment(); // pidgeotto
			kems.enemyMoveDesc = new EnemyMoveDesc[] {EnemyMoveDesc.hitWith(16, false, false, 1)};
//			kems.enemyMoveDesc = new EnemyMoveDesc[] {EnemyMoveDesc.missWith(33 /*tackle*/)};
			kems.maxOwnDamage = 5;
			kems.rageInitialVal = 4;
			kems.attackCount[2][1] = 1; // 1x rage crit
			kems.numExpGainers = 2; // level up to 11
			kems.onlyPrintInfo = false;
			segments.add(kems);
		}

		segments.add(new EndFightSegment(5));
		
		segments.add(new TextSegment());
		segments.add(new SkipTextsSegment(17));

		sequence = new SequenceSegment(segments.toArray(new Segment[0]));
	}
	
	@Override
	public StateBuffer execute(StateBuffer in) {
		return sequence.execute(in);
	}
}
