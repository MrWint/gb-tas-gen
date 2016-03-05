package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import static mrwint.gbtasgen.metric.comparator.Comparator.GREATER_EQUAL;
import static mrwint.gbtasgen.move.Move.A;
import static mrwint.gbtasgen.move.Move.B;
import static mrwint.gbtasgen.move.Move.DOWN;
import static mrwint.gbtasgen.move.Move.SELECT;
import static mrwint.gbtasgen.move.Move.START;
import static mrwint.gbtasgen.move.Move.UP;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.BULBASAUR;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DISABLE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DRAGON_RAGE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DRATINI;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.GROWL;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.HM01;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.HM02;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.LEER;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.LICK;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PIDGEY;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.ROAR;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SAND_ATTACK;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SCREECH;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SMOG;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SQUIRTLE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TACKLE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TAIL_WHIP;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TM09;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TM13;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TM24;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.PRESSED;

import mrwint.gbtasgen.metric.comparator.Comparator;
import mrwint.gbtasgen.metric.pokemon.CheckAttackMisses;
import mrwint.gbtasgen.metric.pokemon.CheckEncounterMetric;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckDisableEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckSwitchAndTeleportEffectUsed;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputItems;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputParty;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.pokemon.gen1.EflOverworldInteract;
import mrwint.gbtasgen.segment.pokemon.EflCatchMonSegment;
import mrwint.gbtasgen.segment.pokemon.EflEvolutionSegment;
import mrwint.gbtasgen.segment.pokemon.EflLearnTMSegment;
import mrwint.gbtasgen.segment.pokemon.EflOverrideMoveSegment;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.pokemon.EflWalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflCheckAdditionalTexts;
import mrwint.gbtasgen.segment.pokemon.fight.EflCheckMoveDamage;
import mrwint.gbtasgen.segment.pokemon.fight.EflCheckMoveOrderMetric;
import mrwint.gbtasgen.segment.pokemon.fight.EflEndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflInitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment.EflEnemyMoveDesc;
import mrwint.gbtasgen.segment.pokemon.fight.EflNewEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.Constants;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflEncounterSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSelectItemSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSelectMonSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;

public class TowerBlue extends SeqSegment {

	@Override
	public void execute() {
//    seqEflButton(A); // continue game
//    seqEflButton(START);
//    seqEflButton(A);
//    seqEflButton(START);
//    seqEflButton(A);
//
//    seq(new EflWalkToSegment(4, 6)); // leave center
//    seq(new EflWalkToSegment(4, 8, false)); // leave center
//
//    seqMetric(new OutputParty());
//    seqMetric(new OutputItems());
//    seq(new EflSelectItemSegment(HM01).fromOverworld().andUse());
//    seq(new EflLearnTMSegment(BULBASAUR)); // Cut
//    seq(new EflSelectItemSegment(HM02).andUse());
//    seq(new EflLearnTMSegment(PIDGEY)); // Fly
//    seq(new EflSelectItemSegment(TM13).andUse());
//    seq(new EflLearnTMSegment(DRATINI, 1)); // leer -> Ice Beam
//    seqEflSkipInput(0);
//    seq(new EflSelectItemSegment(TM24).andUse());
//    seq(new EflLearnTMSegment(DRATINI, 0)); // wrap -> Thunderbolt
//    seqEflSkipInput(0);
//    seq(new EflSelectItemSegment(TM09).andUse());
//    seq(new EflLearnTMSegment(DRATINI, 3)); // agility -> Take Down
//    seqEflButton(B); // cancel
//    seq(new EflSelectMonSegment(SQUIRTLE).fromMainMenu().andSwitchWith(DRATINI));
//    seqEflSkipInput(1);
//    seq(new EflSelectMonSegment(PIDGEY).andFlyTo(3)); // Lavender
//    seqEflSkipInput(1);
//
//    seq(new EflWalkToSegment(14, 5)); // enter tower
//    seq(new EflWalkToSegment(18, 9)); // l2
//    seq(new EflWalkToSegment(15, 5)); // engage rival
//
//    save("t0");
//    load("t0");
//
//    seq(new EflInitFightSegment(6)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), SAND_ATTACK)};
//      kems.attackCount[0][1] = 1; // Thunderbolt crit
//      seq(kems); // pidgeotto
//    }
//    save("tmp");
//    load("tmp");
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(DRAGON_RAGE)};
////	    kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), LEER)};
//      kems.attackCount[0][1] = 1; // Thunderbolt crit
//      seq(kems); // gyarados
//    }
//    save("tmp2");
//    load("tmp2");
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckSwitchAndTeleportEffectUsed(), ROAR)};
//      kems.attackCount[0][0] = 1; // Thunderbolt
//      kems.attackCount[0][1] = 1; // Thunderbolt crit
//      seq(kems); // growlithe
//    }
//    save("tmp3");
//    load("tmp3");
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckDisableEffectMisses(), DISABLE)};
//      kems.attackCount[3][1] = 1; // Take Down crit
//      kems.numExpGainers = 2; // dratini, l25
//      seq(kems); // kadabra
//    }
//    save("tmp4");
//    load("tmp4");
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), GROWL)};
//      kems.attackCount[1][0] = 2; // Ice beam
//      seq(kems); // ivysaur
//    }
//    seq(new EflEndFightSegment(2)); // player defeated enemy
//    seq(new EflSkipTextsSegment(10)); // after battle texts
//
//    save("t1");
//	  load("t1");
//
//	  seqMetric(new OutputParty());
//
//    seq(new EflWalkToSegment(3, 9)); // l3
//    seq(new EflWalkToSegment(18, 9)); // l4
//
//    seq(new EflWalkToSegment(17, 7)); // engage
//    seq(new EflWalkToSegment(16, 7)); // engage
//    seqMove(new EflOverworldInteract(2)); // talk to trainer
//    seq(new EflInitFightSegment(1)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(LICK)};
//      kems.attackCount[0][1] = 2; // Thunderbolt crit
//      seq(kems); // gastly
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    save("tmp");
//    load("tmp");
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(LICK)};
//      kems.attackCount[0][1] = 2; // Thunderbolt crit
//      seq(kems); // gastly
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//
//    save("t2");
//    load("t2");
//
//    {
//      seq(new EflWalkToSegment(13, 10)); // elixer
//      seqMove(new EflOverworldInteract(4)); // elixer
//      seq(new EflTextSegment());
//    }
//    seq(new EflWalkToSegment(3, 9)); // l5
//    {
//      seq(new EflWalkToSegment(4, 11)); // elixer
//      seqEflButton(A); // elixer
//      seq(new EflTextSegment());
//    }
//
//    seq(new EflWalkToSegment(11, 9)); // heal
//    seq(new EflSkipTextsSegment(2)); // healed
//
//    save("t3");
//    load("t3");
//    seq(new EflWalkToSegment(18, 9)); // l6
//    seq(new EflWalkToSegment(15, 5)); // engage
//    seq(new EflInitFightSegment(1)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(LICK)};
//      kems.attackCount[0][1] = 2; // Thunderbolt crit
//      kems.numExpGainers = 2; // dratini, l26
//      seq(kems); // gastly
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//    seq(new EflWalkToSegment(11, 5)); // engage
//    seq(new EflWalkToSegment(10, 5)); // engage
//    seqMove(new EflOverworldInteract(2)); // talk to trainer
//    seq(new EflInitFightSegment(1)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(LICK)};
//      kems.attackCount[0][1] = 2; // Thunderbolt crit
//      seq(kems); // gastly
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//
//    save("t4");
//    load("t4");
//
//    seq(new EflWalkToSegment(6, 6)); // rare candy
//    seq(new EflWalkToSegment(6, 7)); // rare candy
//    seqMove(new EflOverworldInteract(4)); // rare candy
//    seq(new EflTextSegment());
//    seq(new EflWalkToSegment(10, 16)); // marowak
//    seq(new EflSkipTextsSegment(1)); // begone
//    {
//      seq(new EflSkipTextsSegment(2)); // ghost appeared
//      seq(new EflTextSegment()); // go
//      seqEflButton(Move.DOWN); // items
//      seqEflButton(Move.A); // items
//      seqEflScrollFastAF(13+1); // poke doll
//      seq(new EflSkipTextsSegment(1)); // used poke doll
//    }
//    seq(new EflSkipTextsSegment(4)); // ghost gone
//    seq(new EflWalkToSegment(9, 16)); // l7
//
//    save("t5");
//    load("t5");
//
//
//    seq(new EflWalkToSegment(10, 11, false)); // engage trainer
//
//    seq(new EflInitFightSegment(1)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[0][0] = 1; // Thunderbolt
//      seq(kems); // zubat
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[0][0] = 1; // Thunderbolt
//      seq(kems); // zubat
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), SCREECH)};
//      kems.attackCount[0][1] = 1; // Thunderbolt crit
//      seq(kems); // golbat
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//    seq(new EflSkipTextsSegment(1)); // no forget this
//
//    save("t6");
//    load("t6");
//
//    seq(new EflWalkToSegment(10, 9, false)); // engage trainer
//    seq(new EflInitFightSegment(4)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(TACKLE, SMOG)};
//      kems.attackCount[1][0] = 1; // Ice Beam
//      kems.attackCount[1][1] = 1; // Ice Beam crit
//      kems.numExpGainers = 2; // dratini, lvl27
//      seq(kems); // koffing
//    }
//    save("tmp");
//    load("tmp");
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckDisableEffectMisses(), DISABLE)};
//      kems.attackCount[3][0] = 1; // Take Down
//      kems.attackCount[1][1] = 1; // Ice Beam crit
//      kems.lastAttack = 0; // wrap
//      seq(kems); // drowzee
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//    seq(new EflSkipTextsSegment(3)); // no forget this
//
//    save("t7");
    load("t7");

    seq(new EflWalkToSegment(10, 7, false)); // engage trainer
    seq(new EflInitFightSegment(1)); // start fight
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[0][0] = 1; // Thunderbolt
      seq(kems); // zubat
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[0][1] = 1; // Thunderbolt crit
      seq(kems); // rattata
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), TAIL_WHIP)};
      kems.attackCount[0][1] = 1; // Thunderbolt crit
      seq(kems); // raticate
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[0][0] = 1; // Thunderbolt
      seq(kems); // zubat
    }
    seq(new EflEndFightSegment(1)); // player defeated enemy
    seq(new EflSkipTextsSegment(1)); // no forget this
    seq(new EflWalkToSegment(10, 5)); // engage old man
    seq(new EflWalkToSegment(10, 4)); // engage old man
    seqMove(new EflOverworldInteract(4)); // talk to old man
    seq(new EflSkipTextsSegment(12));

    save("t8");
    load("t8");

    seq(new EflWalkToSegment(2, 1)); // engage old man
    seq(new EflWalkToSegment(3, 1, false)); // engage old man
    seqMove(new EflOverworldInteract(5)); // talk to old man
    seq(new EflSkipTextsSegment(10)); // get flute
    seq(new EflWalkToSegment(2, 8, false)); // leave house
  }
}
