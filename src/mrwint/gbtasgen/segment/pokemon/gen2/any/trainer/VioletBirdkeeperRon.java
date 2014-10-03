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


public class VioletBirdkeeperRon extends Segment {

	SequenceSegment sequence;
	
	public VioletBirdkeeperRon() {
		List<Segment> segments = new ArrayList<Segment>();
		
		segments.add(new InitFightSegment(4, 33));
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment(); // pidgey
			kems.enemyMoveDesc = new EnemyMoveDesc[] {EnemyMoveDesc.missWith(33)}; // tackle
			kems.maxOwnDamage = 0;
			kems.numEndOfTurnTexts = new int[]{2};
			kems.attackCount[0][1] = 2; // 2x scratch crit
			kems.numExpGainers = 2; // level up to 9
			kems.onlyPrintInfo = false;
			segments.add(kems);
		}

		segments.add(NewEnemyMonSegment.any(33)); // next mon

		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment(); // pidgey
			kems.enemyMoveDesc = new EnemyMoveDesc[] {EnemyMoveDesc.missWith(33)}; // tackle
			kems.maxOwnDamage = 0;
			kems.attackCount[0][1] = 2; // 1x scratch crit
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			segments.add(kems);
		}

		segments.add(new EndFightSegment(1));

		segments.add(new WalkToSegment(4, 5));	// align leader
		segments.add(new WalkToSegment(5, 3));	// align leader
		segments.add(new WalkToSegment(5, 2));	// engage leader
		segments.add(new MoveSegment(new OverworldInteract()));	// engage leader

		sequence = new SequenceSegment(segments.toArray(new Segment[0]));
	}
	
	@Override
	public StateBuffer execute(StateBuffer in) {
		return sequence.execute(in);
	}
}
