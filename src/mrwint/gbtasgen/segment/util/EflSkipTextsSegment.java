package mrwint.gbtasgen.segment.util;

import mrwint.gbtasgen.move.EflPressButton;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.util.EflUtil;

public class EflSkipTextsSegment extends SeqSegment {

	int numTexts;
	int extraWait = 0;
	boolean confirmWithA;

	public EflSkipTextsSegment(int numTexts) {
		this(numTexts,false);
	}

	public EflSkipTextsSegment(int numTexts, boolean confirmWithA) {
    EflUtil.assertEfl();

		this.numTexts = numTexts;
		this.confirmWithA = confirmWithA;
	}
	
	public EflSkipTextsSegment withExtraWait(int extraWait) {
	  this.extraWait = extraWait;
	  return this;
	}

	@Override
	protected void execute() {
		for(int i=0;i<numTexts;i++) {
			seq(new EflTextSegment(confirmWithA ? Move.B : Move.A));
			if (extraWait > 0)
			  seqEflSkipInput(extraWait);
			seq(new MoveSegment(new EflPressButton(confirmWithA ? Move.A : Move.B), MoveSegment.MAX_DELAY));
		}
	}
}
