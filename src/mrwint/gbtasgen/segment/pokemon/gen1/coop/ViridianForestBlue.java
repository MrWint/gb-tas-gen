package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import static mrwint.gbtasgen.move.Move.UP;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.RATTATA;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.STRING_SHOT;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TAIL_WHIP;
import mrwint.gbtasgen.metric.pokemon.CheckEncounterMetric;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.move.pokemon.gen1.EflOverworldInteract;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.pokemon.EflWalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflEndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflInitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment.EflEnemyMoveDesc;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflEncounterSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;

public class ViridianForestBlue extends SeqSegment {

	@Override
	public void execute() {
//		seqUnbounded(new EflWalkToSegment(18, -1)); // leave viridian
//    save("tmp");
    load("tmp");
    seqUnbounded(new EflWalkToSegment(5, 51)); // walk up to encounter
    seqUnbounded(new EflWalkToSegment(5, 50)); // walk up to encounter
//    seqUnbounded(new EflWalkToSegment(8, 50)); // walk up to encounter
//		seqUnbounded(new EflWalkToSegment(8, 49)); // walk up to encounter
		seq(new EflEncounterSegment(new CheckEncounterMetric(RATTATA, 2), UP));
    save("tmp2");
    load("tmp2");
    seq(new EflSkipTextsSegment(1)); // wild rattata
    seq(new EflTextSegment()); // go
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), TAIL_WHIP)};
      kems.attackCount[0][0] = 2; // tackle
      seq(kems); // Rattata
    }
    save("vf1");
    load("vf1");

		seq(new EflWalkToSegment(3, 43)); // enter viridian forest house
		seq(new EflWalkToSegment(5, 0)); // enter viridian forest

		seq(new EflWalkToSegment(2, 19)); // walk up to trainer
		seqMove(new EflOverworldInteract(4)); // talk to trainer

		seq(new EflInitFightSegment(1)); // start fight
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
			kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), STRING_SHOT)};
      kems.attackCount[0][0] = 3; // tackle
      kems.attackCount[0][1] = 2; // tackle crit
			kems.numExpGainers = 3; // Squirtle, level up to 8, learn bubble
			seq(kems); // Weedle
		}
		seq(new EflEndFightSegment(2)); // player defeated enemy

		seq(new EflWalkToSegment(1, -1, false)); // leave forest
		seq(new EflWalkToSegment(5, 0)); // leave forest house
		seq(new EflWalkToSegment(8, -1)); // enter pewter city
	}
}
