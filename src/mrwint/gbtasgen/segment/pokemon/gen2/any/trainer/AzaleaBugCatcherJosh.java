package mrwint.gbtasgen.segment.pokemon.gen2.any.trainer;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.move.pokemon.gen2.OverworldInteract;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.WalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.InitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment.EnemyMoveDesc;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.state.StateBuffer;

// around 400 frames faster than Al
public class AzaleaBugCatcherJosh extends Segment {

	SequenceSegment sequence;
	
	public AzaleaBugCatcherJosh() {
		List<Segment> segments = new ArrayList<Segment>();
		
		// segments.add(new MoveSegment(new Wait(500))); // used for testing to avoid getting captured after the fight

		segments.add(new WalkToSegment(0, 5, false));	// engage bug catcher josh

		segments.add(new InitFightSegment(4, 77, 78));
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment(); // paras
			kems.enemyMoveDesc = new EnemyMoveDesc[] {EnemyMoveDesc.missWith(77, 78)}; // stun spore, poisonpowder
			kems.maxOwnDamage = 0;
			kems.attackCount[0][1] = 2; // 2x scratch crit
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
