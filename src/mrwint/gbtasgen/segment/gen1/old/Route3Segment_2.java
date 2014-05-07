package mrwint.gbtasgen.segment.gen1.old;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.move.gen1.OverworldInteract;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.WalkToSegment;
import mrwint.gbtasgen.segment.fight.EndFightSegment;
import mrwint.gbtasgen.segment.fight.InitFightSegment;
import mrwint.gbtasgen.segment.fight.KillEnemyMonSegment;
import mrwint.gbtasgen.segment.fight.KillEnemyMonSegment.EnemyMoveDesc;
import mrwint.gbtasgen.segment.fight.NewEnemyMonSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.state.StateBuffer;

public class Route3Segment_2 extends Segment {

	SequenceSegment sequence;
	
	public Route3Segment_2() {
		List<Segment> segments = new ArrayList<Segment>();

		segments.add(new InitFightSegment(2)); // start fight
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(33)}; // tackle
			kems.attackCount[2][0] = 1; // 1x ember
			kems.attackCount[2][1] = 1; // 1x ember crit
			kems.numExpGainers = 2; // level up to 11
			kems.onlyPrintInfo = false;
			segments.add(kems); // Rattata
		}
		segments.add(NewEnemyMonSegment.any()); // next mon
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(35)}; // wrap
			kems.attackCount[2][0] = 1; // 1x ember
			kems.attackCount[2][1] = 1; // 1x ember crit
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			segments.add(kems); // Ekans
		}
		segments.add(new EndFightSegment(1)); // player defeated enemy
		
		segments.add(new WalkToSegment(18, 5)); // walk up to trainer
		segments.add(new MoveSegment(new OverworldInteract(5))); // talk to trainer

		sequence = new SequenceSegment(segments.toArray(new Segment[0]));
	}
	
	@Override
	public StateBuffer execute(StateBuffer in) {
		return sequence.execute(in);
	}
}
