package mrwint.gbtasgen.segment.pokemon.fight;

import static mrwint.gbtasgen.metric.comparator.Comparator.GREATER_EQUAL;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.Wait;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.TextSegment;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment.CheckMoveDamage;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment.CheckMoveOrderMetric;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;

public class ThrashEnemyMonSegment extends SeqSegment {

	int minDmg;
	boolean crit;
	public ThrashEnemyMonSegment() {
		this(1, false);
	}
	public ThrashEnemyMonSegment(int minDmg, boolean crit) {
		this.minDmg = minDmg;
		this.crit = crit;
	}

	@Override
	public void execute() {
		delay(new SeqSegment() {
			@Override
			protected void execute() {
				seq(Move.B); // continue text
				seq(new TextSegment(Move.A, false, 0)); // sent out new mon
				seq(new CheckMoveOrderMetric(true, new int[0], 0));
				seq(new MoveSegment(new Wait(1), 0, 0)); // finish text frame
				seq(new TextSegment(Move.A, false, 0)); // thrashing about
				seq(new CheckMoveDamage(crit, false, !crit, false, false, false, 0), GREATER_EQUAL, minDmg);
				seq(Segment.wait(1)); // finish text frame
			}
		});
		if (crit)
			seq(new SkipTextsSegment(1)); // critical hit
		seq(new SkipTextsSegment(1)); // mon fainted
		seq(new TextSegment()); // gained exp
	}
}
