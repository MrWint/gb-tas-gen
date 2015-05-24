package mrwint.gbtasgen.segment.util;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.util.EflUtil;

public class EflSkipTextsSegment extends SeqSegment {

	int numTexts;
	boolean confirmWithA;

	public EflSkipTextsSegment(int numTexts) {
		this(numTexts,false);
	}

	public EflSkipTextsSegment(int numTexts, boolean confirmWithA) {
    EflUtil.assertEfl();

		this.numTexts = numTexts;
		this.confirmWithA = confirmWithA;
	}

	@Override
	protected void execute() {
		for(int i=0;i<numTexts;i++) {
			seq(new EflTextSegment(confirmWithA ? Move.B : Move.A));
			seqEflButton(confirmWithA ? Move.A : Move.B);
		}
	}
}
