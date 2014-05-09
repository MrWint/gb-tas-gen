package mrwint.gbtasgen.segment.fight;

import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.metric.comparator.GreaterEqual;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.move.Wait;
import mrwint.gbtasgen.segment.TextSegment;
import mrwint.gbtasgen.segment.fight.KillEnemyMonSegment.CheckAdditionalTexts;
import mrwint.gbtasgen.segment.fight.KillEnemyMonSegment.CheckMoveDamage;
import mrwint.gbtasgen.segment.util.CheckMetricSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.DelayMoveSegment.DelayUntilFactory;
import mrwint.gbtasgen.segment.util.DelayMoveSegment.DelayableMoveFactory;
import mrwint.gbtasgen.segment.util.DelayMoveSegment.PressButtonFactory;
import mrwint.gbtasgen.state.StateBuffer;

public class HitMetricSegment extends AttackActionSegment {
	
	final private boolean isCrit;
	final private boolean effectMiss;
	final private boolean player;
	final private boolean noCheckAdditionalTexts;
	final private int numEndTexts;
	final private boolean ignoreDamage;
	final private int thrashAdditionalTurns;

	public HitMetricSegment(boolean isCrit, boolean effectMiss, boolean isEffective, boolean player, int numEndTexts, boolean noCheckAdditionalTexts, boolean ignoreDamage) {
		this(isCrit, effectMiss, isEffective, player, numEndTexts, noCheckAdditionalTexts, ignoreDamage, 0);
	}
	public HitMetricSegment(boolean isCrit, boolean effectMiss, boolean isEffective, boolean player, int numEndTexts, boolean noCheckAdditionalTexts, boolean ignoreDamage, int thrashAdditionalTurns) {
		this.isCrit = isCrit;
		this.effectMiss = effectMiss;
		this.numEndTexts = numEndTexts + (isCrit ? 1 : 0) + (isEffective ? 1 : 0);
		this.player = player;
		this.noCheckAdditionalTexts = noCheckAdditionalTexts;
		this.ignoreDamage = ignoreDamage;
		this.thrashAdditionalTurns = thrashAdditionalTurns;
	}
	
	@Override
	public DelayableMoveFactory getFinishMove() {
		if(numEndTexts == 0)
			return null;
		if(noCheckAdditionalTexts)
			return new PressButtonFactory(Move.B, Metric.PRESSED_JOY);
		else
			return new DelayUntilFactory(new PressButtonFactory(Move.B, Metric.PRESSED_JOY), new CheckAdditionalTexts(Move.B,true));
	}
	
	@Override
	public StateBuffer executeInternal(StateBuffer sb, int minValue) {
		sb = new TextSegment(Move.A,false,0).execute(sb); // player mon uses attack
		sb = new CheckMetricSegment(new CheckMoveDamage(isCrit, effectMiss, numEndTexts == 0, false, !player, ignoreDamage, thrashAdditionalTurns),new GreaterEqual(),ignoreDamage ? 0 : minValue,player ? KillEnemyMonSegment.PLAYER_ATTRIBUTE : KillEnemyMonSegment.ENEMY_ATTRIBUTE).execute(sb);
		sb = new MoveSegment(new Wait(1), 0, 0).execute(sb); // skip last frame of text box
		
		for(int i=0;i<numEndTexts-1;i++) { // skip messages
			sb = new TextSegment().execute(sb); // critical hit! message
			sb = new MoveSegment(new PressButton(Move.B)).execute(sb); // close message
		}
		if(numEndTexts > 0) // skip last message if any
			sb = new TextSegment().execute(sb); // critical hit! or very/not effective message

		return sb;
	}
}
