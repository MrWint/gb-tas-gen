package mrwint.gbtasgen.segment.pokemon.fight;

import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.metric.pokemon.CheckAttackMisses;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.state.StateBuffer;

public class EflMissMetricSegment extends EflAttackActionSegment {

	private final Metric moveMetric;
	private final boolean player;

	public EflMissMetricSegment(boolean player) {
		this(player, new CheckAttackMisses());
	}

	public EflMissMetricSegment(boolean player, Metric enemyMoveMetric) {
		this.moveMetric = enemyMoveMetric;
		this.player = player;
	}

	@Override
	public void executeInternal(int minValue) {
		seqUnbounded(new EflTextSegment(Move.A)); // enemy uses attack (unbounded buffer)
		seqMetric(moveMetric);
		seq(new EflTextSegment()); // "but it misses"

		seq(new Segment() {

      @Override
      public StateBuffer execute(StateBuffer sb) {
        for(State s : sb.getStates())
          s.setAttributeInt(player ? KillEnemyMonSegment.PLAYER_ATTRIBUTE : KillEnemyMonSegment.ENEMY_ATTRIBUTE, 0); // set 0 damage
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
