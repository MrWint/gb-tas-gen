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
import mrwint.gbtasgen.state.StateBuffer;


public class SlowpokeWellRocket3 extends Segment {

	SequenceSegment sequence;
	
	public SlowpokeWellRocket3() {
		List<Segment> segments = new ArrayList<Segment>();

		segments.add(new InitFightSegment(3));
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment(); // rattata
			kems.enemyMoveDesc = new EnemyMoveDesc[] {EnemyMoveDesc.missWith()}; // any
			kems.maxOwnDamage = 0;
			kems.attackCount[0][1] = 1; // 1x scratch crit
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			segments.add(kems);
		}
		segments.add(NewEnemyMonSegment.any());
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment(); // zubat
			kems.enemyMoveDesc = new EnemyMoveDesc[] {EnemyMoveDesc.missWith()};
			kems.maxOwnDamage = 0;
			kems.attackCount[0][1] = 1; // 1x scratch crit
			kems.numExpGainers = 2; // level up to 15
			kems.onlyPrintInfo = false;
			segments.add(kems);
		}
		segments.add(NewEnemyMonSegment.any());
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment(); // zubat
			kems.enemyMoveDesc = new EnemyMoveDesc[] {EnemyMoveDesc.missWith()};
			kems.maxOwnDamage = 0;
			kems.attackCount[0][1] = 1; // 1x scratch crit
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			segments.add(kems);
		}
		segments.add(new EndFightSegment(1));
		segments.add(new WalkToSegment(5, 4));			// align
		segments.add(new WalkToSegment(5, 3, false));	// engage rocket 4
		
		sequence = new SequenceSegment(segments.toArray(new Segment[0]));
	}
	
	@Override
	public StateBuffer execute(StateBuffer in) {
		return sequence.execute(in);
	}
}
