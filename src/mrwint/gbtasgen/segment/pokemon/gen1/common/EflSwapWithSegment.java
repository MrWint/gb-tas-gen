package mrwint.gbtasgen.segment.pokemon.gen1.common;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.util.EflUtil.PressMetric;

public class EflSwapWithSegment extends SeqSegment {

	int scroll;
	public EflSwapWithSegment(int offset) {
		this.scroll = offset;
	}

	@Override
	public void execute() {
	  seqEflButton(Move.SELECT, PressMetric.PRESSED);
		seqEflScrollFast(scroll);
    seqEflButton(Move.SELECT, PressMetric.PRESSED);
	}
}
