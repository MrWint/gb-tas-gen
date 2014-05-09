package mrwint.gbtasgen.segment.fight;

import mrwint.gbtasgen.metric.comparator.GreaterEqual;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.TextSegment;
import mrwint.gbtasgen.segment.fight.KillEnemyMonSegment.CheckMoveDamage;
import mrwint.gbtasgen.segment.fight.KillEnemyMonSegment.CheckMoveOrderMetric;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;

public class ThrashEnemyMonSegment extends SeqSegment {
	
	@Override
	public void execute() {
		delay(new SeqSegment() {
			@Override
			protected void execute() {
				seq(Move.B); // continue text
				seq(new TextSegment(Move.A, false)); // sent out new mon
				seq(new CheckMoveOrderMetric(true, new int[0], 0));
				seq(Segment.wait(1)); // finish text frame
				seq(new TextSegment(Move.A, false)); // thrashing about
				seq(new CheckMoveDamage(false, false, true, false, false, false, 0), new GreaterEqual(), 1);
				seq(Segment.wait(1)); // finish text frame
			}
		});
		seq(new SkipTextsSegment(1)); // mon fainted
		seq(new TextSegment()); // gained exp
	}
}
