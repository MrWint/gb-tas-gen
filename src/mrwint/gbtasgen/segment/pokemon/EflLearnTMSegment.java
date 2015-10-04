package mrwint.gbtasgen.segment.pokemon;

import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSelectMonSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;


public class EflLearnTMSegment extends SeqSegment {

  int override;
  int mon;

  public EflLearnTMSegment(int mon) {
    this(mon, -1);
  }
  public EflLearnTMSegment(int mon, int override) {
    this.mon = mon;
	  this.override = override;
	}

	@Override
	public void execute() {
    seq(new EflSkipTextsSegment(2)); // booted up TM, contains xyz
    seq(new EflSkipTextsSegment(1, true)); // learn
    seq(new EflSelectMonSegment(mon));
    if (override == -1) {
      seq(new EflSkipTextsSegment(1, true)); // learned!
    } else {
      seq(new EflOverrideMoveSegment(override));
    }
	}
}
