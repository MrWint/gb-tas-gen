package mrwint.gbtasgen.segment.pokemon.fight;

import static mrwint.gbtasgen.state.Gameboy.curGb;

import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.state.StateBuffer;
import mrwint.gbtasgen.util.EflUtil;

public class EflEnemyFlinchSegment extends EflAttackActionSegment {

  private class EnemyFlinchMetric implements StateResettingMetric {
    @Override
    public int getMetricInternal() {
      EflUtil.runToAddressNoLimit(0, 0, 0x3e8e7); // run to start of flinch check
      int enemyStatus = curGb.readMemory(0xd067); // W_ENEMYBATTSTATUS1
      if ((enemyStatus & 0b1000) != 0)
        return 1;
      return 0;
    }
  }

	@Override
	public void executeInternal(int minValue) {
    seqMetric(new EnemyFlinchMetric());
		seq(new EflTextSegment()); // enemy flinched

		seq(new Segment() {
      @Override
      public StateBuffer execute(StateBuffer sb) {
        for(State s : sb.getStates())
          s.setAttributeInt(KillEnemyMonSegment.ENEMY_ATTRIBUTE, 0); // set 0 damage
        return sb;
      }
    });
	}

	@Override
	public Segment getFinishSegment() {
		return new SeqSegment() {
      @Override
      protected void execute() {
        seqEflButtonUnboundedNoDelay(Move.B);
      }
    };
	}
}
