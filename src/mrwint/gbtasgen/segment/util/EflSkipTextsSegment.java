package mrwint.gbtasgen.segment.util;

import mrwint.gbtasgen.move.EflPressButton;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.state.StateBuffer;
import mrwint.gbtasgen.util.EflUtil;

public class EflSkipTextsSegment extends SeqSegment {

	int numTexts;
	boolean confirmWithA;
	int bufferSize = StateBuffer.MAX_BUFFER_SIZE;

	public EflSkipTextsSegment(int numTexts) {
		this(numTexts,false);
	}

	public EflSkipTextsSegment(int numTexts, boolean confirmWithA) {
    EflUtil.assertEfl();

		this.numTexts = numTexts;
		this.confirmWithA = confirmWithA;
	}

	public EflSkipTextsSegment withBufferSize(int bufferSize) {
	  this.bufferSize = bufferSize;
	  return this;
	}

	@Override
	protected void execute() {
		for(int i=0;i<numTexts;i++) {
			seq(new EflTextSegment(confirmWithA ? Move.B : Move.A, bufferSize));
			seq(new MoveSegment(new EflPressButton(confirmWithA ? Move.A : Move.B), MoveSegment.MAX_DELAY, bufferSize));
		}
	}
}
