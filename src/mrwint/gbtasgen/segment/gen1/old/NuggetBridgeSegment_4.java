package mrwint.gbtasgen.segment.gen1.old;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.move.gen1.OverworldInteract;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.WalkToSegment;
import mrwint.gbtasgen.segment.fight.EndFightSegment;
import mrwint.gbtasgen.segment.fight.InitFightSegment;
import mrwint.gbtasgen.segment.fight.KillEnemyMonSegment;
import mrwint.gbtasgen.segment.fight.NewEnemyMonSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.state.StateBuffer;

public class NuggetBridgeSegment_4 extends Segment {

	SequenceSegment sequence;
	
	public NuggetBridgeSegment_4() {
		List<Segment> segments = new ArrayList<Segment>();

		segments.add(new InitFightSegment(1)); // start fight
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(33)}; // tackle
			kems.attackCount[2][1] = 1; // 1x ember crit (better: 1x mega punch crit (-9f))
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			segments.add(kems); // rattata
		}
		segments.add(NewEnemyMonSegment.any()); // next mon
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(33)}; // tackle
			kems.attackCount[3][1] = 1; // 1x mega punch crit (alt: 1x ember crit (15 spc needed!))
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			segments.add(kems); // ekans
		}
		segments.add(NewEnemyMonSegment.any()); // next mon
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(33)}; // tackle
			kems.attackCount[3][1] = 1; // 1x mega punch crit (alt: 1x ember crit+burn (15 spc needed!))
			kems.numExpGainers = 2; // level up to 18
			kems.onlyPrintInfo = false;
			segments.add(kems); // zubat
		}
		segments.add(new EndFightSegment(1)); // player defeated enemy

		segments.add(new WalkToSegment(10, 23)); // walk up to trainer
		segments.add(new MoveSegment(new OverworldInteract(4))); // talk to trainer 4
		
		sequence = new SequenceSegment(segments.toArray(new Segment[0]));
	}
	
	@Override
	public StateBuffer execute(StateBuffer in) {
		return sequence.execute(in);
	}
}
