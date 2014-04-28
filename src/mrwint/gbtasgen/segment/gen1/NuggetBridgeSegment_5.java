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

public class NuggetBridgeSegment_5 extends Segment {

	SequenceSegment sequence;
	
	public NuggetBridgeSegment_5() {
		List<Segment> segments = new ArrayList<Segment>();

		segments.add(new InitFightSegment(1)); // start fight
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(33)}; // tackle
			kems.attackCount[3][1] = 1; // 1x mega punch crit (alt: 1x ember crit + burn)
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			segments.add(kems); // pidgey
		}
		segments.add(NewEnemyMonSegment.any()); // next mon
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(33)}; // tackle
			kems.attackCount[2][0] = 1; // 1x ember
			kems.attackCount[2][1] = 1; // 1x ember crit (alt: 1xmega punch crit, 1xmega punch (+3f))
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			segments.add(kems); // nidoF
		}
		segments.add(new EndFightSegment(1)); // player defeated enemy

		segments.add(new WalkToSegment(11, 20)); // walk up to trainer
		segments.add(new MoveSegment(new Gen1OverworldInteract(3))); // talk to trainer 5
		
		sequence = new SequenceSegment(segments.toArray(new Segment[0]));
	}
	
	@Override
	public StateBuffer execute(StateBuffer in) throws Throwable {
		return sequence.execute(in);
	}
}
