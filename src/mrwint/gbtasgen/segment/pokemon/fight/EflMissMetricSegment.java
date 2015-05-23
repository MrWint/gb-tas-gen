package mrwint.gbtasgen.segment.pokemon.fight;

import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.metric.pokemon.CheckAttackMisses;
import mrwint.gbtasgen.move.EflPressButton;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.pokemon.TextSegment;
import mrwint.gbtasgen.segment.util.CheckMetricSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
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
	public StateBuffer executeInternal(StateBuffer sb, int minValue) {
		sb = new EflTextSegment(Move.A, 0).execute(sb); // enemy uses attack (unbounded buffer)
		sb = new CheckMetricSegment(moveMetric).execute(sb);
//		sb = new MoveSegment(new Wait(1)).execute(sb); // skip last frame of text box
		sb = new TextSegment().execute(sb); // "but it misses"

		for(State s : sb.getStates())
			s.setAttributeInt(player ? KillEnemyMonSegment.PLAYER_ATTRIBUTE : KillEnemyMonSegment.ENEMY_ATTRIBUTE, 0); // set 0 damage

		return sb;
	}

	@Override
	public Segment getFinishSegment() {
		return new MoveSegment(new EflPressButton(Move.B));
	}

}
