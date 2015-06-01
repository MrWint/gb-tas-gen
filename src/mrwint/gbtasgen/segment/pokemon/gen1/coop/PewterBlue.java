package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.pokemon.gen1.EflOverworldInteract;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.pokemon.EflWalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflEndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflInitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment.EflEnemyMoveDesc;
import mrwint.gbtasgen.segment.pokemon.fight.EflNewEnemyMonSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.util.EflUtil.PressMetric;

public class PewterBlue extends SeqSegment {

	@Override
	public void execute() {
    seq(new EflWalkToSegment(16, 17)); // enter gym
    seq(new EflWalkToSegment(4, 3));
    seq(new EflWalkToSegment(4, 2));
    seq(new MoveSegment(new EflOverworldInteract(1)));

    seq(new EflInitFightSegment(9)); // start fight
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(33)}; // tackle
      kems.attackCount[2][0] = 2; // 2x bubble
      kems.numExpGainers = 2; // level up to 9
      kems.onlyPrintInfo = false;
      seq(kems); // Geodude
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 103)}; // screech
      kems.attackCount[2][0] = 2; // 2x bubble
      kems.numExpGainers = 2; // level up to 11
      kems.onlyPrintInfo = false;
      seq(kems); // Onix
    }
    seq(new EflEndFightSegment(10)); // player defeated enemy
    seq(new EflSkipTextsSegment(14)); // after battle talk
    seq(new EflWalkToSegment(4, 14, false)); // exit gym

    save("tmp");
    load("tmp");

    {
      seq(new EflWalkToSegment(23, 17)); // enter pewter mart

      seq(new EflWalkToSegment(3, 5));
      seq(new EflWalkToSegment(2, 5));
      seq(new MoveSegment(new EflOverworldInteract(1)));
      {
        seq(new EflSkipTextsSegment(1, true)); // buy
        seq(new EflTextSegment(Move.B));
        {
          seqEflButton(Move.A); // poke ball
          seqEflScrollA(-11); // buy x12
          seq(new EflSkipTextsSegment(1)); // confirmation text
          seq(new EflSkipTextsSegment(1, true)); // "yes"
          seq(new EflSkipTextsSegment(1)); // thank you text
        }
        {
          seqEflScrollFastAF(2); // escape rope
          seqEflScrollA(-3); // buy x4
          seq(new EflSkipTextsSegment(1)); // confirmation text
          save("tmp2");
          load("tmp2");
          seq(new EflSkipTextsSegment(1, true)); // "yes"
          seq(new EflSkipTextsSegment(1, true)); // thank you text
        }
        seqEflButton(Move.B, PressMetric.PRESSED); // cancel
        seq(new EflSkipTextsSegment(2)); // cancel + bye
      }
      seq(new EflWalkToSegment(3,8,false)); // leave mart
    }
    seq(new EflWalkToSegment(40, 17)); // leave pewter
	}
}
