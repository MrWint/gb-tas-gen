package mrwint.gbtasgen.segment.pokemon.gen1.common;

import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SUPER_ROD;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckFishResultMetric;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.util.EflUtil;

public class EflFishSegment extends SeqSegment {

	int mon;

	public EflFishSegment(int mon) {
    this.mon = mon;

	   EflUtil.assertEfl();
	}

	@Override
	public void execute() {
	  seq(new EflSelectItemSegment(SUPER_ROD).fromOverworld());
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
