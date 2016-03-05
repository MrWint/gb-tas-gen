package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import static mrwint.gbtasgen.move.Move.B;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SCREECH;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TACKLE;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputItems;
import mrwint.gbtasgen.move.pokemon.gen1.EflOverworldInteract;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.pokemon.EflWalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflEndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflInitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment.EflEnemyMoveDesc;
import mrwint.gbtasgen.segment.pokemon.fight.EflNewEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflBuyItemSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;

public class PewterBlue extends SeqSegment {

	@Override
	public void execute() {
//    seq(new EflWalkToSegment(16, 17)); // enter gym
//    seq(new EflWalkToSegment(4, 3));
//    seq(new EflWalkToSegment(4, 2));
//    seqMove(new EflOverworldInteract(1));
//
//    seq(new EflInitFightSegment(9)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(TACKLE)};
//      kems.attackCount[2][0] = 2; // bubble
//      kems.numExpGainers = 2; // Squirtle, level up to 9
//      kems.onlyPrintInfo = false;
//      seq(kems); // Geodude
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), SCREECH)};
//      kems.attackCount[2][0] = 2; // bubble
//      kems.numExpGainers = 2; // Squirtle, level up to 11
//      kems.onlyPrintInfo = false;
//      seq(kems); // Onix
//    }
//    seq(new EflEndFightSegment(10)); // player defeated enemy
//    seq(new EflSkipTextsSegment(14)); // after battle talk
//    seq(new EflWalkToSegment(4, 14, false)); // exit gym
//
//    save("pe1");
    load("pe1");

    seqMetric(new OutputItems());

    {
      seq(new EflWalkToSegment(23, 17)); // enter pewter mart

      seq(new EflWalkToSegment(3, 5));
      seq(new EflWalkToSegment(2, 5));
      seqMove(new EflOverworldInteract(1));
      {
        seq(new EflSkipTextsSegment(1, true)); // buy
        seq(new EflTextSegment(B)); // what to buy?
        seq(new EflBuyItemSegment(0, 10)); // 10x poke ball
        seq(new EflBuyItemSegment(2, 4, true)); // 4x escape rope
        seqEflButton(B); // cancel
        seq(new EflSkipTextsSegment(2)); // cancel + bye
      }
      seq(new EflWalkToSegment(3, 6)); // leave mart
      seq(new EflWalkToSegment(3, 8, false)); // leave mart
    }
    seq(new EflWalkToSegment(40, 17)); // leave pewter
	}
}
