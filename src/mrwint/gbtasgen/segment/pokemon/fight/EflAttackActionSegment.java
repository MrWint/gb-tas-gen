package mrwint.gbtasgen.segment.pokemon.fight;

import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.util.DelayMoveSegment.DelayableMoveFactory;
import mrwint.gbtasgen.state.StateBuffer;
import mrwint.gbtasgen.util.EflUtil;

public abstract class EflAttackActionSegment {

  public EflAttackActionSegment() {
    EflUtil.assertEfl();
  }

  public Segment prefixSegment = Segment.NULL;
  public Segment suffixSegment = Segment.NULL;

	public abstract StateBuffer executeInternal(StateBuffer sb, int minValue);

	public abstract Segment getFinishSegment();

	public StateBuffer execute(StateBuffer in, int minValue) {
	  in = prefixSegment.execute(in);
		StateBuffer ret = executeInternal(in, minValue);
    in = suffixSegment.execute(in);
		return ret;
	}
}
