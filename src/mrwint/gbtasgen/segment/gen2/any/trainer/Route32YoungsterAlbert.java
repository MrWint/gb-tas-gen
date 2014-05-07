package mrwint.gbtasgen.segment.gen2.any.trainer;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.fight.EndFightSegment;
import mrwint.gbtasgen.segment.fight.InitFightSegment;
import mrwint.gbtasgen.segment.fight.KillEnemyMonSegment;
import mrwint.gbtasgen.segment.fight.NewEnemyMonSegment;
import mrwint.gbtasgen.segment.fight.KillEnemyMonSegment.EnemyMoveDesc;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.state.StateBuffer;


public class Route32YoungsterAlbert extends Segment {

	SequenceSegment sequence;
	
	public Route32YoungsterAlbert() {
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

		segments.add(NewEnemyMonSegment.any(48)); // next mon

		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment(); // any move
			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(48)}; // supersonic
			kems.attackCount[0][0] = 1; // 1x scratch
			kems.attackCount[0][1] = 1; // 1x scratch crit
			kems.numExpGainers = 2; // level up to 12
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
