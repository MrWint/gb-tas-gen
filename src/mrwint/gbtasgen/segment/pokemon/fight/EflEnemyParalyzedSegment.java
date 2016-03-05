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

public class EflEnemyParalyzedSegment extends EflAttackActionSegment {

  private class EnemyParalyzedMetric implements StateResettingMetric {
    @Override
    public int getMetricInternal() {
      if (EflUtil.runToAddressLimit(0, 0, 500, 0x3e9bf) == 0) // run to start of paralyze check
        return 0;
      if (EflUtil.runToAddressLimit(0, 0, 500, 0x3e9cd, curGb.pokemon.printLetterDelayJoypadAddress) != 0x3e9cd) // is paralyzed
        return 0;
      return 1;
    }
  }

	@Override
	public void executeInternal(int minValue) {
    seqMetric(new EnemyParalyzedMetric());
		seq(new EflTextSegment()); // enemy is paralyzed

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
