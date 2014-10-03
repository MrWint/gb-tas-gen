package mrwint.gbtasgen.segment.pokemon.gen2.any.trainer;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.EvolutionSegment;
import mrwint.gbtasgen.segment.pokemon.TextSegment;
import mrwint.gbtasgen.segment.pokemon.WalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.InitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.NewEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment.EnemyMoveDesc;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.state.StateBuffer;


public class AzaleaLeader extends Segment {

	SequenceSegment sequence;
	
	public AzaleaLeader() {
		List<Segment> segments = new ArrayList<Segment>();

		segments.add(new InitFightSegment(6, 33));
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment(); // metapod
			kems.enemyMoveDesc = new EnemyMoveDesc[] {EnemyMoveDesc.hitWith(33, false, false, 1)}; // tackle
			kems.maxOwnDamage = 6;
			kems.rageInitialVal = 1;
			kems.attackCount[2][0] = 2; // 2x rage
			kems.attackCount[2][1] = 1; // 1x rage crit
			kems.numExpGainers = 2; // level up to 17
			kems.onlyPrintInfo = false;
			segments.add(kems);
		}
		segments.add(NewEnemyMonSegment.any(40));
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment(); // kakuna
			kems.enemyMoveDesc = new EnemyMoveDesc[] {EnemyMoveDesc.hitWith(40, false, true, false, 1)}; // poison sting
			kems.rageInitialVal = 3;
			kems.maxOwnDamage = 3;
			kems.attackCount[2][0] = 2; // 2x rage
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			segments.add(kems);
		}
		segments.add(NewEnemyMonSegment.any(210));
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment(); // scyther
			kems.enemyMoveDesc = new EnemyMoveDesc[] {EnemyMoveDesc.hitWith(210, false, false, 1), EnemyMoveDesc.missWith(210)}; // fury cutter
			kems.maxOwnDamage = 5;
			kems.rageInitialVal = 5;
			kems.rageMaxVal = 5;
			kems.attackCount[2][0] = 2; // 2x rage
			kems.numExpGainers = 2; // level up to 18
			kems.onlyPrintInfo = false;
			segments.add(kems);
		}
		segments.add(new EndFightSegment(4));
		segments.add(new EvolutionSegment());
		segments.add(new TextSegment());		// I received badge
		segments.add(new SkipTextsSegment(16));
		segments.add(new WalkToSegment(4, 16, false)); // leave arena		

		sequence = new SequenceSegment(segments.toArray(new Segment[0]));
	}
	
	@Override
	public StateBuffer execute(StateBuffer in) {
		return sequence.execute(in);
	}
}
