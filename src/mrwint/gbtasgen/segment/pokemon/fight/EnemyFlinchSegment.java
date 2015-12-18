package mrwint.gbtasgen.segment.pokemon.fight;

import static mrwint.gbtasgen.state.Gameboy.curGb;
import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.pokemon.TextSegment;
import mrwint.gbtasgen.segment.util.CheckMetricSegment;
import mrwint.gbtasgen.segment.util.DelayMoveSegment.DelayableMoveFactory;
import mrwint.gbtasgen.segment.util.DelayMoveSegment.PressButtonFactory;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.state.StateBuffer;
import mrwint.gbtasgen.util.Util;

public class EnemyFlinchSegment extends AttackActionSegment {

  public static class EnemyFlinchMetric implements StateResettingMetric {
    @Override
    public int getMetricInternal() {
      Util.runToAddressNoLimit(0, 0, 0x3e8e7); // run to start of flinch check
      int enemyStatus = curGb.readMemory(0xd067); // W_ENEMYBATTSTATUS1
      if ((enemyStatus & 0b1000) != 0)
        return 1;
      return 0;
    }
  }

	@Override
	public StateBuffer executeInternal(StateBuffer sb, int minValue) {
    sb = new CheckMetricSegment(new EnemyFlinchMetric()).execute(sb);
		sb = new TextSegment().execute(sb); // enemy flinched

		for(State s : sb.getStates())
			s.setAttributeInt(KillEnemyMonSegment.ENEMY_ATTRIBUTE, 0); // set 0 damage

		return sb;
	}

	private final static DelayableMoveFactory factory = new PressButtonFactory(Move.B);

	@Override
	public DelayableMoveFactory getFinishMove() {
		return factory;
	}

}
