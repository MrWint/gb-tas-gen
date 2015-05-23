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


public class GoldenrodSuperNerdEric implements Segment {

	SequenceSegment sequence;

	public GoldenrodSuperNerdEric() {
		List<Segment> segments = new ArrayList<Segment>();

		segments.add(new InitFightSegment(4));
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment(); // grimer
			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith()}; // any move
			kems.attackCount[0][1] = 1; // 1x cut crit
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			segments.add(kems);
		}
		segments.add(NewEnemyMonSegment.any());
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment(); // grimer
			kems.enemyMoveDesc = new EnemyMoveDesc[] {EnemyMoveDesc.missWith()}; // any
			kems.attackCount[0][1] = 1; // 1x cut crit
			kems.numExpGainers = 1; // no level up
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
