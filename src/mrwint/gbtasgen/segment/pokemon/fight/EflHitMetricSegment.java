package mrwint.gbtasgen.segment.pokemon.fight;

import static mrwint.gbtasgen.metric.comparator.Comparator.GREATER_EQUAL;
import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.util.CheckMetricSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.util.EflUtil;

public class EflHitMetricSegment extends EflAttackActionSegment {

	final private boolean isCrit;
	final private boolean effectMiss;
	final private boolean player;
	final private boolean noCheckAdditionalTexts;
	final private boolean noCheckObedience;
	final private int numEndTexts;
	final private int thrashAdditionalTurns;

	public EflHitMetricSegment(boolean isCrit, boolean effectMiss, boolean isEffective, boolean player, int numEndTexts, boolean noCheckAdditionalTexts, boolean noCheckObedience) {
		this(isCrit, effectMiss, isEffective, player, numEndTexts, noCheckAdditionalTexts, noCheckObedience, 0);
	}
	public EflHitMetricSegment(boolean isCrit, boolean effectMiss, boolean isEffective, boolean player, int numEndTexts, boolean noCheckAdditionalTexts, boolean noCheckObedience, int thrashAdditionalTurns) {
		this.isCrit = isCrit;
		this.effectMiss = effectMiss;
    this.thrashAdditionalTurns = thrashAdditionalTurns;
		this.numEndTexts = numEndTexts + (isCrit ? 1 : 0) + (isEffective ? 1 : 0);
		this.player = player;
		this.noCheckAdditionalTexts = noCheckAdditionalTexts;
		this.noCheckObedience = noCheckObedience;
	}

	@Override
	public Segment getFinishSegment() {
		if(numEndTexts == 0)
			return null;
		if(noCheckAdditionalTexts)
	    return new SeqSegment() {
        @Override
        protected void execute() {
          seqEflButtonUnboundedNoDelay(Move.B);
        }
      };
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
	public void executeInternal(int minValue) {
	  if (player && !noCheckObedience)
	    seqMetric(new EflCheckObedience());
		seqUnbounded(new EflTextSegment(Move.A)); // player mon uses attack
//    System.out.println("EflHitMetricSegment: size=" + sb.size());
		seq(new CheckMetricSegment(new EflCheckMoveDamage(isCrit, effectMiss, thrashAdditionalTurns, minValue, Integer.MAX_VALUE, !player),GREATER_EQUAL, minValue, player ? KillEnemyMonSegment.PLAYER_ATTRIBUTE : KillEnemyMonSegment.ENEMY_ATTRIBUTE));
		if (numEndTexts == 0 && !noCheckAdditionalTexts) // check for additional texts
		  seqMetric(new StateResettingMetric() {
        @Override
        public int getMetricInternal() {
          EflUtil.runFor(5, 0, 0);
          return new EflCheckAdditionalTexts().getMetricInternal();
        }
      });

    seq(new EflSkipTextsSegment(numEndTexts - 1)); // skip critical hit! messages
		if(numEndTexts > 0) // skip last message if any
			seq(new EflTextSegment()); // critical hit! or very/not effective message
	}
}
