package mrwint.gbtasgen.segment.gen1.old;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.move.gen1.OverworldInteract;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.TextSegment;
import mrwint.gbtasgen.segment.WalkToSegment;
import mrwint.gbtasgen.segment.fight.EndFightSegment;
import mrwint.gbtasgen.segment.fight.InitFightSegment;
import mrwint.gbtasgen.segment.fight.KillEnemyMonSegment;
import mrwint.gbtasgen.segment.fight.KillEnemyMonSegment.EnemyMoveDesc;
import mrwint.gbtasgen.segment.fight.NewEnemyMonSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.state.StateBuffer;

public class NuggetBridgeSegment_1 extends Segment {

	SequenceSegment sequence;
	
	public NuggetBridgeSegment_1() {
		List<Segment> segments = new ArrayList<Segment>();
		segments.add(new WalkToSegment(20,6)); // go to rival fight

		segments.add(new InitFightSegment(8)); // start fight
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(16)}; // gust
			kems.attackCount[3][1] = 3; // 3x mega punch crit
			kems.numExpGainers = 2; // level up to 15
			kems.onlyPrintInfo = false;
			segments.add(kems); // pidgeotto
		}
		{ // cancel learning leer
			segments.add(new SkipTextsSegment(6));
			segments.add(new SkipTextsSegment(1, true));
			segments.add(new SkipTextsSegment(2));
		}
		segments.add(NewEnemyMonSegment.any()); // next mon
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(Metric.TRUE)}; // tackle
			kems.attackCount[3][1] = 1; // 1x mega punch crit
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			segments.add(kems); // abra
		}
		segments.add(NewEnemyMonSegment.any()); // next mon
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(33)}; // tackle
			kems.attackCount[2][0] = 2; // 2x ember (better: 2x mega punch (-4f))
			kems.numExpGainers = 2; // level up to 16
			kems.onlyPrintInfo = false;
			segments.add(kems); // rattata
		}
		segments.add(NewEnemyMonSegment.any()); // next mon
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(33)}; // tackle
			kems.attackCount[3][1] = 2; // 2x mega punch crit
//			kems.attackCount[3][0] = 2; // 2x mega punch
//			kems.attackCount[3][1] = 1; // 1x mega punch crit (1 extra turn)
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			segments.add(kems); // squirtle
		}
		segments.add(new EndFightSegment(2)); // player defeated enemy

		segments.add(new TextSegment()); // evolution
		segments.add(new TextSegment()); // evolution

		segments.add(new SkipTextsSegment(14)); // after rival battle texts

		segments.add(new WalkToSegment(21, -1)); // walk up
		segments.add(new WalkToSegment(11, 32)); // walk up to trainer
		segments.add(new MoveSegment(new OverworldInteract(7))); // talk to trainer 1

		sequence = new SequenceSegment(segments.toArray(new Segment[0]));
	}
	
	@Override
	public StateBuffer execute(StateBuffer in) {
		return sequence.execute(in);
	}
}
