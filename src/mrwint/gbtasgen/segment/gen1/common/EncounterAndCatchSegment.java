package mrwint.gbtasgen.segment.gen1.common;

import mrwint.gbtasgen.metric.CheckEncounterMetric;
import mrwint.gbtasgen.move.gen1.WalkStep;
import mrwint.gbtasgen.segment.CatchMonSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;

public class EncounterAndCatchSegment extends SeqSegment {
	
	public static int defaultBallIndex = 0;

	int move, ballIndex;
	
	CheckEncounterMetric metric;
	
	public EncounterAndCatchSegment(CheckEncounterMetric metric, int move) {
		this.metric = metric;
		this.move = move;
		this.ballIndex = defaultBallIndex;
	}
	public EncounterAndCatchSegment(int mon, int move) {
		this(mon, 0, move, defaultBallIndex);
	}
	public EncounterAndCatchSegment(int mon, int lvl, int move) {
		this(mon, lvl, move, defaultBallIndex);
	}
	public EncounterAndCatchSegment(int mon, int lvl, int move, int ballIndex) {
		this.metric = new CheckEncounterMetric(mon, lvl);
		this.move = move;
		this.ballIndex = ballIndex;
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
		seq(new CatchMonSegment(ballIndex));
	}
}
