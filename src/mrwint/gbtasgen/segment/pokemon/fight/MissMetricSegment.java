package mrwint.gbtasgen.segment.pokemon.fight;

import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.metric.pokemon.CheckAttackMisses;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.Wait;
import mrwint.gbtasgen.segment.pokemon.TextSegment;
import mrwint.gbtasgen.segment.util.CheckMetricSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.DelayMoveSegment.DelayableMoveFactory;
import mrwint.gbtasgen.segment.util.DelayMoveSegment.PressButtonFactory;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.state.StateBuffer;

public class MissMetricSegment extends AttackActionSegment {

	private final Metric moveMetric;
	private final boolean player;
	
	public MissMetricSegment(boolean player) {
		this(player, new CheckAttackMisses());
	}
	
	public MissMetricSegment(boolean player, Metric enemyMoveMetric) {
		this.moveMetric = enemyMoveMetric;
		this.player = player;
	}
	
	@Override
	public StateBuffer executeInternal(StateBuffer sb, int minValue) {
		sb = new TextSegment(Move.A,false,0).execute(sb); // enemy uses attack (unbounded buffer)
		sb = new CheckMetricSegment(moveMetric).execute(sb);
		sb = new MoveSegment(new Wait(1)).execute(sb); // skip last frame of text box
		sb = new TextSegment().execute(sb); // "but it misses"
		
		for(State s : sb.getStates())
			s.setAttributeInt(player ? KillEnemyMonSegment.PLAYER_ATTRIBUTE : KillEnemyMonSegment.ENEMY_ATTRIBUTE, 0); // set 0 damage
		
		return sb;
	}

	private final static DelayableMoveFactory factory = new PressButtonFactory(Move.B);
	
	@Override
	public DelayableMoveFactory getFinishMove() {
		return factory;
	}

}
