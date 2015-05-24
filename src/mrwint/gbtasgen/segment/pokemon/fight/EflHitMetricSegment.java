package mrwint.gbtasgen.segment.pokemon.fight;

import static mrwint.gbtasgen.metric.comparator.Comparator.GREATER_EQUAL;
import mrwint.gbtasgen.move.EflPressButton;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.pokemon.TextSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment.EflCheckMoveDamage;
import mrwint.gbtasgen.segment.util.CheckMetricSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.state.StateBuffer;

public class EflHitMetricSegment extends EflAttackActionSegment {

	final private boolean isCrit;
	final private boolean effectMiss;
	final private boolean player;
	final private boolean noCheckAdditionalTexts;
	final private int numEndTexts;
	final private boolean ignoreDamage;
	final private int thrashAdditionalTurns;

	public EflHitMetricSegment(boolean isCrit, boolean effectMiss, boolean isEffective, boolean player, int numEndTexts, boolean noCheckAdditionalTexts, boolean ignoreDamage) {
		this(isCrit, effectMiss, isEffective, player, numEndTexts, noCheckAdditionalTexts, ignoreDamage, 0);
	}
	public EflHitMetricSegment(boolean isCrit, boolean effectMiss, boolean isEffective, boolean player, int numEndTexts, boolean noCheckAdditionalTexts, boolean ignoreDamage, int thrashAdditionalTurns) {
		this.isCrit = isCrit;
		this.effectMiss = effectMiss;
		this.numEndTexts = numEndTexts + (isCrit ? 1 : 0) + (isEffective ? 1 : 0);
		this.player = player;
		this.noCheckAdditionalTexts = noCheckAdditionalTexts;
		this.ignoreDamage = ignoreDamage;
		this.thrashAdditionalTurns = thrashAdditionalTurns;
	}

	@Override
	public Segment getFinishSegment() {
		if(numEndTexts == 0)
			return null;
		if(noCheckAdditionalTexts)
	    return new MoveSegment(new EflPressButton(Move.B));
		else
		  return new SeqSegment() {
        @Override
        protected void execute() {
          seqEflButtonUnboundedNoDelay(Move.B);
          seqMetric(new EflKillEnemyMonSegment.EflCheckAdditionalTexts());
        }
      };
	}

	@Override
	public StateBuffer executeInternal(StateBuffer sb, int minValue) {
		sb = new EflTextSegment(Move.A, 0).execute(sb); // player mon uses attack
//    System.out.println("EflHitMetricSegment: size=" + sb.size());
		sb = new CheckMetricSegment(new EflCheckMoveDamage(isCrit, effectMiss, numEndTexts == 0, false, !player, ignoreDamage, thrashAdditionalTurns),GREATER_EQUAL,ignoreDamage ? 0 : minValue,player ? KillEnemyMonSegment.PLAYER_ATTRIBUTE : KillEnemyMonSegment.ENEMY_ATTRIBUTE).execute(sb);
//		sb = new MoveSegment(new Wait(1), 0, 0).execute(sb); // skip last frame of text box

    sb = new EflSkipTextsSegment(numEndTexts - 1).execute(sb); // skip critical hit! messages
		if(numEndTexts > 0) // skip last message if any
			sb = new EflTextSegment().execute(sb); // critical hit! or very/not effective message

		return sb;
	}
}
