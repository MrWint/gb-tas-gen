package mrwint.gbtasgen.segment.pokemon.gen1.common;

import static mrwint.gbtasgen.move.Move.START;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.PRESSED;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckFishResultMetric;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.util.EflUtil;

public class EflFishSegment extends SeqSegment {

	int mon, itemOffset, mainMenuOffset;

	public EflFishSegment(int mon, int itemOffset) {
		this(mon, itemOffset, 2);
	}

	public EflFishSegment(int mon, int itemOffset, int mainMenuOffset) {
    this.mon = mon;
		this.itemOffset = itemOffset;
    this.mainMenuOffset = mainMenuOffset;

	   EflUtil.assertEfl();
	}

	@Override
	public void execute() {
		seqEflButton(START, PRESSED); // menu
		seqEflScrollA(mainMenuOffset); // items
		seqEflScrollFastAF(itemOffset); // rod
		seqEflSkipInput(1); // skip repress delay frame
		delayEfl(new SeqSegment() {
			@Override
			protected void execute() {
				seqEflButton(Move.A);
				seqUnbounded(new EflTextSegment(Move.A)); // used rod
				seqMetric(new CheckFishResultMetric(mon, 0)); // mon
			}
		});
		seq(new EflSkipTextsSegment(2)); // bite!, hooked
	}
}
