package mrwint.gbtasgen.segment.pokemon;

import mrwint.gbtasgen.metric.pokemon.CheckCatchMonMetric;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.util.EflDelayMoveSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.util.EflUtil.PressMetric;

public class EflBallSuccessSegment extends EflDelayMoveSegment {

	public EflBallSuccessSegment() {
		super(new SeqSegment() {
      @Override
      protected void execute() {
        seqEflButtonUnboundedNoDelay(Move.A, PressMetric.PRESSED); // use ball
        seqUnbounded(new EflTextSegment(Move.A)); // used ball
        seqMetric(new CheckCatchMonMetric()); // check catch
      }
    });
	}
}
