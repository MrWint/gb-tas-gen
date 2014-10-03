package mrwint.gbtasgen.segment.pokemon.gen2.any.trainer;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.fight.EndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.InitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment.EnemyMoveDesc;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.state.StateBuffer;


public class CherrygroveRivalSegment extends Segment {

	SequenceSegment sequence;
	
	public CherrygroveRivalSegment() {
		List<Segment> segments = new ArrayList<Segment>();
		
		segments.add(new InitFightSegment(7, 33 /*GROWL*/));
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.hitWith(45, false, false, 1, true), EnemyMoveDesc.hitWith(33, false, false)}; // any move
//			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(45), EnemyMoveDesc.hitWith(33, false, false)}; // any move
			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(33), EnemyMoveDesc.missWith(33)}; // any move
//			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.hitWith(45, false, false, 1, true), EnemyMoveDesc.hitWith(45, false, false, 1, true)}; // any move
//			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(33), EnemyMoveDesc.hitWith(45, false, false, 1, true)}; // any move
//			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.hitWith(45, false, false, 1, true), EnemyMoveDesc.missWith(33)}; // any move
//			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(45), EnemyMoveDesc.missWith(45)}; // any move
			kems.maxOwnDamage = 0;
			kems.attackCount[0][0] = 1; // 1x scratch
			kems.attackCount[0][1] = 2; // 2x scratch crit
//			kems.nonCritsFirst = true;
			kems.numExpGainers = 2; // level up to 6
			kems.onlyPrintInfo = false;
//			kems.numEndOfAttackTexts = new int[]{1,1};
//			kems.numEndOfTurnTexts = new int[]{2};
			segments.add(kems);
		}
		segments.add(new EndFightSegment(1));

		sequence = new SequenceSegment(segments.toArray(new Segment[0]));
	}
	
	@Override
	public StateBuffer execute(StateBuffer in) {
		return sequence.execute(in);
	}
}
