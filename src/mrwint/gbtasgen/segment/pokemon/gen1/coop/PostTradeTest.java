package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import mrwint.gbtasgen.metric.pokemon.CheckEncounterMetric;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.pokemon.EflWalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment.EflEnemyMoveDesc;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;

public class PostTradeTest extends SeqSegment {

	@Override
	public void execute() {
    seqEflButton(Move.A);
    seqEflButton(Move.START);
    seqEflButton(Move.A);
    seqEflButton(Move.START);
    seqEflButton(Move.A);

    seq(new EflWalkToSegment(4, 8, false)); // leave center
    seqUnbounded(new EflWalkToSegment(18, -1)); // leave viridian
    seqUnbounded(new EflWalkToSegment(8, 50)); // walk up to encounter
    seqUnbounded(new EflWalkToSegment(8, 49)); // walk up to encounter
    delayEfl(new SeqSegment() {
      @Override
      protected void execute() {
        seqEflButtonUnboundedNoDelay(Move.UP);
        seqMetric(new CheckEncounterMetric(165 /* Rattata */, 2));
      }
    });
    seq(new EflSkipTextsSegment(1)); // wild rattata
    seq(new EflTextSegment()); // go
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 39)}; // tail whip
      kems.attackCount[0][0] = 2; // 2x tackle
      kems.numExpGainers = 2; // boosted xp
      kems.onlyPrintInfo = false;
      seq(kems); // Rattata
    }

    seq(new EflWalkToSegment(3, 43)); // enter viridian forest house
    seq(new EflWalkToSegment(5, 0)); // enter viridian forest
}
}
