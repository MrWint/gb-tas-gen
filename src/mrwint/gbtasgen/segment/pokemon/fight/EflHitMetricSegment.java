package mrwint.gbtasgen.segment.pokemon.fight;

import static mrwint.gbtasgen.metric.comparator.Comparator.GREATER_EQUAL;
import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.move.EflPressButton;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.util.CheckMetricSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.state.StateBuffer;
import mrwint.gbtasgen.util.EflUtil;

public class EflHitMetricSegment extends EflAttackActionSegment {

	final private boolean isCrit;
	final private boolean effectMiss;
	final private boolean player;
	final private boolean noCheckAdditionalTexts;
	final private int numEndTexts;
	final private int thrashAdditionalTurns;

	public EflHitMetricSegment(boolean isCrit, boolean effectMiss, boolean isEffective, boolean player, int numEndTexts, boolean noCheckAdditionalTexts) {
		this(isCrit, effectMiss, isEffective, player, numEndTexts, noCheckAdditionalTexts, 0);
	}
	public EflHitMetricSegment(boolean isCrit, boolean effectMiss, boolean isEffective, boolean player, int numEndTexts, boolean noCheckAdditionalTexts, int thrashAdditionalTurns) {
		this.isCrit = isCrit;
		this.effectMiss = effectMiss;
    this.thrashAdditionalTurns = thrashAdditionalTurns;
		this.numEndTexts = numEndTexts + (isCrit ? 1 : 0) + (isEffective ? 1 : 0);
		this.player = player;
		this.noCheckAdditionalTexts = noCheckAdditionalTexts;
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
          seqMetric(new EflCheckAdditionalTexts());
        }
      };
	}

	@Override
	public StateBuffer executeInternal(StateBuffer sb, int minValue) {
		sb = new EflTextSegment(Move.A, 0).execute(sb); // player mon uses attack
//    System.out.println("EflHitMetricSegment: size=" + sb.size());
		sb = new CheckMetricSegment(new EflCheckMoveDamage(isCrit, effectMiss, thrashAdditionalTurns, minValue, Integer.MAX_VALUE, !player),GREATER_EQUAL, minValue, player ? KillEnemyMonSegment.PLAYER_ATTRIBUTE : KillEnemyMonSegment.ENEMY_ATTRIBUTE).execute(sb);
		if (numEndTexts == 0 && !noCheckAdditionalTexts) // check for additional texts
		  sb = new CheckMetricSegment(new StateResettingMetric() {
        @Override
        public int getMetricInternal() {
          EflUtil.runFor(5, 0, 0);
          return new EflCheckAdditionalTexts().getMetricInternal();
        }
      }).execute(sb);

    sb = new EflSkipTextsSegment(numEndTexts - 1).execute(sb); // skip critical hit! messages
		if(numEndTexts > 0) // skip last message if any
			sb = new EflTextSegment().execute(sb); // critical hit! or very/not effective message

		return sb;
	}
}