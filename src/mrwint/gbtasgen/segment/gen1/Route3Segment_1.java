package mrwint.gbtasgen.segment.gen1;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.move.Gen1OverworldInteract;
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

public class Route3Segment_1 extends Segment {

	SequenceSegment sequence;
	
	public Route3Segment_1() {
		List<Segment> segments = new ArrayList<Segment>();
		segments.add(new WalkToSegment(11, 6)); // walk up to trainer

		segments.add(new InitFightSegment(1)); // start fight
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(33)}; // tackle
			kems.attackCount[0][1] = 4; // 4x scratch crit
//			kems.attackCount[0][0] = 2; // 2x scratch
//			kems.attackCount[0][1] = 3; // 3x scratch crit (1 additional turn)
			kems.numExpGainers = 2; // level up to 8
			kems.onlyPrintInfo = false;
			segments.add(kems); // Caterpie
		}
		segments.add(NewEnemyMonSegment.any()); // next mon
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(40)}; // poison sting
			kems.attackCount[0][1] = 3; // 3x scratch crit
//			kems.attackCount[0][0] = 2; // 2x scratch
//			kems.attackCount[0][1] = 2; // 2x scratch crit (1 additional turn)
			kems.numExpGainers = 3; // level up to 9, learns ember
			kems.onlyPrintInfo = false;
			segments.add(kems); // Weedle
		}
		segments.add(NewEnemyMonSegment.any()); // next mon
//		segments.add(new MoveSegment(new MenuInteraction(Move.A))); // fight
//		segments.add(new MoveSegment(new MenuInteraction(Move.SELECT))); // SCRATCH
//		segments.add(new MoveSegment(new MenuInteraction(Move.UP)));
//		segments.add(new MoveSegment(new MenuInteraction(Move.SELECT))); // EMBER
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.skipFirstMainBattleMenu = true;
			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(33)}; // tackle
			kems.attackCount[2][1] = 1; // 1x ember crit
			kems.numExpGainers = 2; // level up to 10
			kems.onlyPrintInfo = false;
			segments.add(kems); // Caterpie
		}
		segments.add(new EndFightSegment(1)); // player defeated enemy
		
		segments.add(new WalkToSegment(12, 4)); // walk up to shorts guy
		segments.add(new WalkToSegment(13, 4)); // walk up to shorts guy
		segments.add(new MoveSegment(new Gen1OverworldInteract(3))); // talk to shorts guy

		sequence = new SequenceSegment(segments.toArray(new Segment[0]));
	}
	
	@Override
	public StateBuffer execute(StateBuffer in) throws Throwable {
		return sequence.execute(in);
	}
}
