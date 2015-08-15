package mrwint.gbtasgen.segment.pokemon.fight;

import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.util.CheckMetricSegment;
import mrwint.gbtasgen.state.StateBuffer;
import mrwint.gbtasgen.util.EflUtil;

public abstract class EflAttackActionSegment {

  public EflAttackActionSegment() {
    EflUtil.assertEfl();
  }

  public Segment prefixSegment = Segment.NULL;
  public Segment suffixSegment = Segment.NULL;

	public abstract void executeInternal(int minValue);

	public abstract Segment getFinishSegment();
	
	
	
  private StateBuffer in;

  public void seq(Segment s) {
	  in = s.execute(in);
	}
  public void seqUnbounded(Segment s) {
    StateBuffer.pushBufferSize(0);
    in = s.execute(in);
    StateBuffer.popBufferSize();
  }
  public void seqMetric(Metric m) {
    seq(new CheckMetricSegment(m));
  }

	public StateBuffer execute(StateBuffer in, int minValue) {
	  this.in = prefixSegment.execute(in);
		executeInternal(minValue);
    this.in = suffixSegment.execute(this.in);
		return this.in;
	}
}
