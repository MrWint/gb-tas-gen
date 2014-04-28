package mrwint.gbtasgen.segment.gen1;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.fight.EndFightSegment;
import mrwint.gbtasgen.segment.fight.InitFightSegment;
import mrwint.gbtasgen.segment.fight.KillEnemyMonSegment;
import mrwint.gbtasgen.segment.fight.KillEnemyMonSegment.EnemyMoveDesc;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.state.StateBuffer;

public class RivalFightSegment extends Segment {

	SequenceSegment sequence;
	
	public RivalFightSegment() {
		List<Segment> segments = new ArrayList<Segment>();
		segments.add(new InitFightSegment(0));
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(33)}; // tackle
			kems.attackCount[0][0] = 0; // 2x scratch
			kems.attackCount[0][1] = 4; // 2x scratch crit
			kems.numExpGainers = 2; // level up to 6
			kems.onlyPrintInfo = false;
			segments.add(kems); // Squirtle
		}
		
		segments.add(new EndFightSegment(3)); // player defeated enemy
		segments.add(new SkipTextsSegment(4)); // rival after battle texts
		
		sequence = new SequenceSegment(segments.toArray(new Segment[0]));
	}
	
	@Override
	public StateBuffer execute(StateBuffer in) throws Throwable {
		return sequence.execute(in);
	}
}
