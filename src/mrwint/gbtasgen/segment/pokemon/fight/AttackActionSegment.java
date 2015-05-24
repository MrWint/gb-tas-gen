package mrwint.gbtasgen.segment.pokemon.fight;

import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.util.DelayMoveSegment.DelayableMoveFactory;
import mrwint.gbtasgen.state.StateBuffer;
import mrwint.gbtasgen.util.EflUtil;

public abstract class AttackActionSegment {

  public AttackActionSegment() {
    EflUtil.assertNoEfl();
  }

  public Segment appendSegment;

	public abstract StateBuffer executeInternal(StateBuffer sb, int minValue);

	public abstract DelayableMoveFactory getFinishMove();

	public StateBuffer execute(StateBuffer in, int minValue) {
		StateBuffer ret = executeInternal(in, minValue);
		if(appendSegment != null)
			ret = appendSegment.execute(ret);
		return ret;
	}
}
