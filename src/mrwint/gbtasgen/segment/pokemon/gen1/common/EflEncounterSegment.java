package mrwint.gbtasgen.segment.pokemon.gen1.common;

import mrwint.gbtasgen.metric.pokemon.CheckEncounterMetric;
import mrwint.gbtasgen.move.pokemon.gen1.EflWalkStep;
import mrwint.gbtasgen.segment.util.SeqSegment;

public class EflEncounterSegment extends SeqSegment {

	int move;
	CheckEncounterMetric metric;

  public EflEncounterSegment(int mon, int move) {
    this(new CheckEncounterMetric(mon), move);
  }
	public EflEncounterSegment(CheckEncounterMetric metric, int move) {
    this.metric = metric;
    this.move = move;
	}

	@Override
	public void execute() {
		delayEfl(new SeqSegment() {
			@Override
			protected void execute() {
				if(move != 0)
					seqMoveUnboundedNoDelay(new EflWalkStep(move, false));
				seqMetric(metric);
			}
		});
	}
}
