package mrwint.gbtasgen.segment.pokemon.gen1.common;

import mrwint.gbtasgen.metric.pokemon.CheckEncounterMetric;
import mrwint.gbtasgen.move.pokemon.gen1.WalkStep;
import mrwint.gbtasgen.segment.pokemon.CatchSafariMonSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;

public class EncounterAndCatchSafariSegment extends SeqSegment {
	
	public static int defaultBallIndex = 0;

	int move;	
	CheckEncounterMetric metric;
	int extraSkips = 0;

	public EncounterAndCatchSafariSegment(CheckEncounterMetric metric, int move) {
		this.metric = metric;
		this.move = move;
	}
	public EncounterAndCatchSafariSegment(int mon, int move) {
		this.metric = new CheckEncounterMetric(mon);
		this.move = move;
	}
	
	public EncounterAndCatchSafariSegment withExtraSkips(int extraSkips) {
		this.extraSkips = extraSkips;
		return this;
	}

	@Override
	public void execute() {
		delay(new SeqSegment() {
			@Override
			protected void execute() {
				if(move != 0)
					seqMove(new WalkStep(move, false));
				seqMetric(metric);
			}
		});
		seq(new CatchSafariMonSegment().withExtraSkips(extraSkips));
	}
}
