package mrwint.gbtasgen.segment.pokemon.gen1.common;

import mrwint.gbtasgen.metric.pokemon.CheckEncounterMetric;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckFishResultMetric;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.pokemon.gen1.WalkStep;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.CatchMonSegment;
import mrwint.gbtasgen.segment.pokemon.TextSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.util.EflUtil;

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

		EflUtil.assertNoEfl();
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
				seqButton(Move.A);
				seq(new TextSegment(Move.A, false)); // used rod
				seqMetric(new CheckFishResultMetric(mon, 0)); // mon
				seq(Segment.wait(1)); // finish text box
			}
		});
		seq(new SkipTextsSegment(2)); // bite!, hooked
		seq(new CatchMonSegment(defaultBallIndex));
	}
}
