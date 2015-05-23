package mrwint.gbtasgen.segment.util;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;

public class EflSkipTextsSegment extends SeqSegment {

	int numTexts;
	boolean confirmWithA;

	public EflSkipTextsSegment(int numTexts) {
		this(numTexts,false);
	}

	public EflSkipTextsSegment(int numTexts, boolean confirmWithA) {
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
