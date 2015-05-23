package mrwint.gbtasgen.segment.pokemon.gen2.any.trainer;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.WalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.InitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.NewEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment.EnemyMoveDesc;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.state.StateBuffer;


public class AzaleaRivalSegment implements Segment {

	SequenceSegment sequence;

	public AzaleaRivalSegment() {
		List<Segment> segments = new ArrayList<Segment>();

		segments.add(new WalkToSegment(5, 11, false)); // engage rival

		segments.add(new InitFightSegment(7));
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith()}; // any move
			kems.attackCount[3][1] = 1; // 1x water gun crit
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			segments.add(kems);
		}
		segments.add(NewEnemyMonSegment.any(77));
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment(); // bayleef
//			kems.enemyMoveDesc = new EnemyMoveDesc[] {EnemyMoveDesc.hitWith(45, false, false, 1, true)}; // poisonpowder
			kems.enemyMoveDesc = new EnemyMoveDesc[] {EnemyMoveDesc.missWith(77)}; // poisonpowder
			kems.maxOwnDamage = 0;
			kems.attackCount[0][1] = 3; // 3x scratch crit
			kems.numExpGainers = 2; // level up to 19
			kems.onlyPrintInfo = false;
			segments.add(kems);
		}
		segments.add(NewEnemyMonSegment.any());
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment(); // zubat
			kems.enemyMoveDesc = new EnemyMoveDesc[] {EnemyMoveDesc.missWith()};
			kems.maxOwnDamage = 0;
			kems.attackCount[3][1] = 1; // 1x water gun
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			segments.add(kems);
		}
		segments.add(new EndFightSegment(3));
		segments.add(new SkipTextsSegment(12));

		sequence = new SequenceSegment(segments.toArray(new Segment[0]));
	}

	@Override
	public StateBuffer execute(StateBuffer in) {
		return sequence.execute(in);
	}
}
