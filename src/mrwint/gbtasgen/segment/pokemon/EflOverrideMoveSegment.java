package mrwint.gbtasgen.segment.pokemon;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;


public class EflOverrideMoveSegment extends SeqSegment {

  int override;

  public EflOverrideMoveSegment(int override) {
    this.override = override;
  }

	@Override
	public void execute() {
    seq(new EflSkipTextsSegment(5)); // learned move
    seq(new EflSkipTextsSegment(1, true)); // yes, overwrite
    seq(new EflTextSegment(Move.B));
    if (override == 0)
      seqEflButton(Move.A); // override move
    else
      seqEflScrollAF(override); // override move
    seq(new EflTextSegment()); // 1,2 and
    seqEflButton(Move.B); // skip 30f
    seq(new EflTextSegment()); // poof
    seqEflButton(Move.A); // skip 30f
    seqEflButton(Move.B);
    seq(new EflSkipTextsSegment(2)); // learned move
    seq(new EflSkipTextsSegment(1, true)); // learned move
	}
}
