package mrwint.gbtasgen.segment.pokemon.gen2.any.trainer;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.move.pokemon.gen2.OverworldInteract;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.WalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.InitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.NewEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment.EnemyMoveDesc;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.state.StateBuffer;

// SLOWER, use Josh instead
public class AzaleaBugCatcherAl implements Segment {

	SequenceSegment sequence;

	public AzaleaBugCatcherAl() {
		List<Segment> segments = new ArrayList<Segment>();

		segments.add(new WalkToSegment(8, 10, false));	// engage bug catcher josh

		segments.add(new InitFightSegment(2));
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment(); // caterpie
			kems.enemyMoveDesc = new EnemyMoveDesc[] {EnemyMoveDesc.missWith()}; // any
			kems.maxOwnDamage = 0;
			kems.attackCount[3][1] = 1; // 1x water gun crit
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			segments.add(kems);
		}
		segments.add(NewEnemyMonSegment.any());
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment(); // weedle
			kems.enemyMoveDesc = new EnemyMoveDesc[] {EnemyMoveDesc.missWith()}; // any
			kems.maxOwnDamage = 0;
			kems.attackCount[3][1] = 1; // 1x water gun crit
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			segments.add(kems);
		}
		segments.add(new EndFightSegment(1));

		segments.add(new WalkToSegment(4, 7));					// face leader
		segments.add(new MoveSegment(new OverworldInteract()));	// engage leader

		sequence = new SequenceSegment(segments.toArray(new Segment[0]));
	}

	@Override
	public StateBuffer execute(StateBuffer in) {
		return sequence.execute(in);
	}
}
