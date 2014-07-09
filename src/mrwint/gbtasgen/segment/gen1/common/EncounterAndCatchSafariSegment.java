package mrwint.gbtasgen.segment.gen1.common;

import mrwint.gbtasgen.metric.CheckEncounterMetric;
import mrwint.gbtasgen.move.gen1.WalkStep;
import mrwint.gbtasgen.segment.CatchSafariMonSegment;
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
					seq(new WalkStep(move, false));
				seq(metric);
			}
		});
		seq(new CatchSafariMonSegment().withExtraSkips(extraSkips));
	}
}
