package mrwint.gbtasgen.segment.pokemon.gen2.any.trainer;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.fight.EndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.InitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.NewEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment.EnemyMoveDesc;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.state.StateBuffer;


public class Route30YoungsterSegment implements Segment {

	SequenceSegment sequence;

	public Route30YoungsterSegment() {
		List<Segment> segments = new ArrayList<Segment>();

		segments.add(new InitFightSegment(2)); // any move
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith()}; // any move
			kems.attackCount[0][1] = 1; // 1x scratch crit
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			segments.add(kems);
		}

		segments.add(NewEnemyMonSegment.any(33)); // tackle
//		segments.add(new NewEnemyMonSegment(39)); // tail whip

		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment(); // any move
//			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.hitWith(39, false, false, 1, true)}; // any move
			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(33)}; // any move
			kems.attackCount[0][0] = 1; // 1x scratch
			kems.attackCount[0][1] = 1; // 1x scratch crit
			kems.numExpGainers = 3; // level up to 7, learn rage
			kems.onlyPrintInfo = false;
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
