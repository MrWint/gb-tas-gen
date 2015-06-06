package mrwint.gbtasgen.segment.pokemon;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;


public class EflLearnTMSegment extends SeqSegment {

  int override;
  int monScroll;

  public EflLearnTMSegment(int monScroll) {
    this(monScroll, -1);
  }
  public EflLearnTMSegment(int monScroll, int override) {
    this.monScroll = monScroll;
	  this.override = override;
	}

	@Override
	public void execute() {
    seq(new EflSkipTextsSegment(2)); // booted up TM, contains xyz
    seq(new EflSkipTextsSegment(1, true)); // learn
    if (monScroll == 0) {
      seqEflSkipInput(1);
      seqEflButton(Move.A); // select mon
    } else
      if (monScroll == 1 || monScroll == -1)
        seqEflSkipInput(1);
      seqEflScrollAF(monScroll); // select mon
    if (override == -1) {
      seq(new EflSkipTextsSegment(1, true)); // learned!
    } else {
      seq(new EflOverrideMoveSegment(override));
    }
	}
}
