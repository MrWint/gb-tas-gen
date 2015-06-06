package mrwint.gbtasgen.segment.pokemon;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;


public class EflEvolutionSegment extends SeqSegment {

  boolean cancel;
  boolean closeWithA;

  public EflEvolutionSegment() {
    this(false);
  }
  public EflEvolutionSegment(boolean cancel) {
    this(cancel, false);
  }
  public EflEvolutionSegment(boolean cancel, boolean closeWithA) {
    this.cancel = cancel;
    this.closeWithA = closeWithA;
  }

	@Override
	public void execute() {
    seq(new EflTextSegment()); // is evolving
    seqEflButton(cancel ? Move.B : 0); // maybe cancel
    if (cancel)
      seq(new EflSkipTextsSegment(1, closeWithA)); // cancelled
    else
      seq(new EflTextSegment()); // evolved
	}
}
