package mrwint.gbtasgen.segment.util;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.segment.TextSegment;

public class SkipTextsSegment extends SeqSegment {

	int numTexts;
	boolean confirmWithA;
	
	public SkipTextsSegment(int numTexts) {
		this(numTexts,false);
	}

	public SkipTextsSegment(int numTexts, boolean confirmWithA) {
		this.numTexts = numTexts;
		this.confirmWithA = confirmWithA;
	}
	
	@Override
	protected void execute() {
		for(int i=0;i<numTexts;i++) {
			seq(new TextSegment(confirmWithA ? Move.B : Move.A));
			seq(new PressButton(confirmWithA ? Move.A : Move.B));
		}
	}
}
