package mrwint.gbtasgen.segment.gen1.common;

import mrwint.gbtasgen.metric.CheckEncounterMetric;
import mrwint.gbtasgen.metric.CheckFishResultMetric;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.gen1.WalkStep;
import mrwint.gbtasgen.segment.CatchMonSegment;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.TextSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;

public class FishAndCatchSegment extends SeqSegment {
	
	public static int defaultBallIndex = 0;

	int mon, itemOffset, mainMenuOffset;
	
	public FishAndCatchSegment(int mon, int itemOffset) {
		this(mon, itemOffset, 2);
	}
	
	public FishAndCatchSegment(int mon, int itemOffset, int mainMenuOffset) {
		this.itemOffset = itemOffset;
		this.mon = mon;
		this.mainMenuOffset = mainMenuOffset;
	}
	
	@Override
	public void execute() {
		seq(Segment.repress(Move.START));
		seq(Segment.scrollA(mainMenuOffset)); // items
		seq(Segment.scrollA(itemOffset)); // rod
		seq(Segment.skip(1)); // skip repress delay frame
		delay(new SeqSegment() {
			@Override
			protected void execute() {
				seq(Move.A);
				seq(new TextSegment(Move.A, false)); // used rod
				seq(new CheckFishResultMetric(mon, 0)); // mon
				seq(Segment.wait(1)); // finish text box
			}
		});
		seq(new SkipTextsSegment(2)); // bite!, hooked
		seq(new CatchMonSegment(defaultBallIndex));
	}
}
