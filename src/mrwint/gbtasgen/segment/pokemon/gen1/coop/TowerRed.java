package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import static mrwint.gbtasgen.move.Move.A;
import static mrwint.gbtasgen.move.Move.B;
import static mrwint.gbtasgen.move.Move.DOWN;
import static mrwint.gbtasgen.move.Move.RIGHT;
import static mrwint.gbtasgen.move.Move.START;
import static mrwint.gbtasgen.move.Move.UP;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.BUTTERFREE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.CATERPIE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.CHARMELEON;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.CLEFABLE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.CUBONE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DISABLE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.FARFETCHD;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.GASTLY;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.GROWL;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.HAUNTER;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.LEER;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.MEOWTH;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.METAPOD;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.NINETALES;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SAND_ATTACK;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SCREECH;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SMOG;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TACKLE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TAIL_WHIP;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.WARTORTLE;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.MENU;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.PRESSED;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckDisableEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputBoxMons;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputItems;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputParty;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.pokemon.gen1.EflOverworldInteract;
import mrwint.gbtasgen.segment.pokemon.EflCatchMonSegment;
import mrwint.gbtasgen.segment.pokemon.EflEvolutionSegment;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.pokemon.EflWalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflEndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflInitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment.EflEnemyMoveDesc;
import mrwint.gbtasgen.segment.pokemon.fight.EflNewEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflSwitchPokemonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.Constants;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflCancelMoveLearnSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflDepositMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflEncounterSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSelectMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflUseBikeSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflWithdrawMonSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;

public class TowerRed extends SeqSegment {

	@Override
	public void execute() {
//    seq(new EflSelectMonSegment(FARFETCHD).fromOverworld().andFlyTo(3)); // lavender
//    seqEflSkipInput(1);
//
//    seq(new EflSelectMonSegment(WARTORTLE).fromOverworld().andSwitchWith(CHARMELEON));
//    seqEflButton(B); // cancel
//    seq(new EflUseBikeSegment().fromMainMenu());
//    seqMetric(new OutputParty());
//
//    seq(new EflWalkToSegment(14, 5)); // enter tower
//    seq(new EflWalkToSegment(18, 9)); // l2
//
//    save("t0");
//    load("t0");
//
//    seq(new EflWalkToSegment(15, 5)); // engage rival
//    seq(new EflInitFightSegment(6)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), SAND_ATTACK)};
//      kems.attackCount[1][1] = 1; // Slash crit
//      kems.numExpGainers = 2; // Charmeleon, boosted;
//      seq(kems); // pidgeotto
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[2][0] = 1; // Ember
//      kems.numExpGainers = 3; // Charmeleon, boosted, lvl up 34;
//      seq(kems); // Exeggcute
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    seq(new EflSwitchPokemonSegment(CATERPIE, EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), LEER)));
//    seq(new EflSwitchPokemonSegment(MEOWTH, EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), LEER)));
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[0][1] = 1; // Thunderbolt crit
//      kems.numExpGainers = 7; // Charmeleon, boosted, Meowth, boosted, lvlup to 27, caterpie, lvlup
//      seq(kems); // gyarados
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckDisableEffectMisses(), DISABLE)};
//      kems.attackCount[2][1] = 1; // bite crit
//      kems.numExpGainers = 2; // Meowth, boosted;
//      seq(kems); // kadabra
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), LEER)};
//      kems.attackCount[2][0] = 1; // bite
//      kems.attackCount[2][1] = 1; // bite crit
//      kems.numExpGainers = 3; // Meowth, boosted, l28;
//      seq(kems); // Charmeleon
//    }
//    seq(new EflEndFightSegment(2)); // player defeated enemy
//    seq(new EflEvolutionSegment()); // persian
//    seq(new EflEvolutionSegment()); // metapod
//    seq(new EflSkipTextsSegment(10)); // after battle texts
//
//    save("t1");
//	  load("t1");
//
//    seq(new EflSelectMonSegment(CHARMELEON).fromOverworld().andSwitchWith(WARTORTLE));
//    seqEflButton(B); // cancel
//    seqEflButton(START); // cancel
//    seqMetric(new OutputParty());
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
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(122)}; // lick
//      kems.attackCount[3][1] = 1; // Bubblebeam
//      kems.numExpGainers = 2; // boosted
//      seq(kems); // gastly
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(122)}; // lick
//      kems.attackCount[3][1] = 1; // Bubblebeam
//      kems.numExpGainers = 3; // boosted; lvlup to 34
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
//      seqEflButton(Move.A); // elixer
//      seqUnbounded(new EflTextSegment());
//    }
//    {
//      save("tmp");
//      load("tmp");
//      seqUnbounded(new EflWalkToSegment(4, 10)); // align
//      seq(new EflEncounterSegment(GASTLY, UP));
//      save("tmp2");
//      load("tmp2");
//      seq(new EflCatchMonSegment().withBufferSize(0));
//      seqUnbounded(new EflWalkToSegment(8, 6)); // align
//      seq(new EflEncounterSegment(CUBONE, RIGHT));
//      save("tmp3");
//      load("tmp3");
//      seq(new EflCatchMonSegment());
//      seq(new EflWalkToSegment(11, 9)); // heal
//      seq(new EflSkipTextsSegment(1)); // healed
//      seqUnbounded(new EflSkipTextsSegment(1)); // healed
//      seqUnbounded(new EflWalkToSegment(7, 14)); // nugget
//      seqMoveUnboundedNoDelay(new EflOverworldInteract(6)); // nugget
//      seqUnbounded(new EflTextSegment()); // nugget
//      seqUnbounded(new EflWalkToSegment(8, 14)); // align
//      seq(new EflEncounterSegment(HAUNTER, RIGHT));
//      seq(new EflCatchMonSegment());
//    }
//    seq(new EflWalkToSegment(18, 9)); // l6
//
//    save("t3");
//    load("t3");
//
//    seq(new EflWalkToSegment(15, 5)); // engage
//    seq(new EflInitFightSegment(1)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(122)}; // lick
//      kems.attackCount[3][1] = 1; // Bubblebeam crit
//      kems.numExpGainers = 2; // boosted
//      seq(kems); // gastly
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//
//    save("t4");
//    load("t4");
//
//    seq(new EflWalkToSegment(11, 5)); // engage
//    seq(new EflWalkToSegment(10, 5)); // engage
//
//    seqMove(new EflOverworldInteract(2)); // talk to trainer
//    seq(new EflInitFightSegment(1)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(122)}; // lick
//      kems.attackCount[3][1] = 1; // Bubblebeam crit
//      kems.numExpGainers = 2; // boosted;
//      seq(kems); // gastly
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//
//    save("t5");
//    load("t5");
//
//    seq(new EflSelectMonSegment(WARTORTLE).fromOverworld().andSwitchWith(CHARMELEON));
//    seqEflButton(B); // cancel
//    seqEflButton(START); // cancel
//
//    seq(new EflWalkToSegment(6, 6)); // rare candy
//    seq(new EflWalkToSegment(6, 7)); // rare candy
//    seqMove(new EflOverworldInteract(4)); // rare candy
//    seq(new EflTextSegment());
//    seq(new EflWalkToSegment(10, 16)); // marowak
//    seq(new EflSkipTextsSegment(1)); // begone
//    {
//      seq(new EflSkipTextsSegment(4)); // ghost appeared, unveiled, marowak
//      seq(new EflTextSegment()); // go
//      {
//        EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//        kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), GROWL, LEER)};
//        kems.attackCount[2][1] = 2; // Ember crit
////        kems.attackCount[3][0] = 1; // Bubblebeam
//        seq(kems); // marowak
//      }
//    }
//    seq(new EflSkipTextsSegment(1)); // charmeleon, boosted
//    seq(new EflSkipTextsSegment(4)); // ghost gone
//    seq(new EflWalkToSegment(9, 16)); // l7
//
//    save("t6");
//    load("t6");
//
//    seq(new EflWalkToSegment(10, 11, false)); // engage trainer
//
//    seq(new EflInitFightSegment(1)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[2][1] = 1; // Ember crit
//      kems.numExpGainers = 2; // Charmeleon, boosted
//      seq(kems); // zubat
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[2][1] = 1; // Ember crit
//      kems.numExpGainers = 2; // Charmeleon, boosted
//      seq(kems); // zubat
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), SCREECH)};
//      kems.attackCount[2][0] = 1; // Ember crit
//      kems.attackCount[2][1] = 1; // Ember crit
//      kems.numExpGainers = 3; // Charmeleon, boosted, lvlup to 35
//      seq(kems); // golbat
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//    seq(new EflSkipTextsSegment(1)); // no forget this
//
//    save("t7");
//    load("t7");
//
//    seq(new EflSelectMonSegment(CHARMELEON).fromOverworld().andSwitchWith(WARTORTLE));
//    seqEflButton(B); // cancel
//    seqEflButton(START); // cancel
//
//    seq(new EflWalkToSegment(10, 9, false)); // engage trainer
//    seq(new EflInitFightSegment(4)); // start fight
////    seq(new EflSwitchPokemonSegment(CHARMELEON, EflEnemyMoveDesc.missWith(TACKLE, SMOG)));
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(TACKLE, SMOG)};
//      kems.attackCount[3][1] = 1; // Bubblebeam crit
//      kems.numExpGainers = 2; // wartortle, boosted
//      seq(kems); // koffing
//    }
//    save("tmp");
//    load("tmp");
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckDisableEffectMisses(), DISABLE)};
//      kems.attackCount[1][1] = 1; // Bite crit
//      kems.numExpGainers = 3; // wartortle, boosted, lvlup to 35
//      seq(kems); // drowzee
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//
//    save("t8");
    load("t8");

    seq(new EflSkipTextsSegment(3)); // no forget this

    seq(new EflSelectMonSegment(WARTORTLE).fromOverworld().andSwitchWith(CHARMELEON));
    seqEflButton(B); // cancel
    seqEflButton(START); // cancel

    seq(new EflWalkToSegment(10, 7, false)); // engage trainer

    seq(new EflInitFightSegment(1)); // start fight
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[2][1] = 1; // Ember
      kems.numExpGainers = 2; // boosted
      seq(kems); // zubat
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[2][0] = 1; // Ember crit
      kems.numExpGainers = 2; // boosted
      seq(kems); // rattata
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), TAIL_WHIP)};
      kems.attackCount[2][1] = 1; // Ember crit
      kems.numExpGainers = 2; // boosted
      seq(kems); // raticate
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[2][1] = 1; // Ember crit
      kems.numExpGainers = 2; // boosted
      seq(kems); // zubat
    }
    seq(new EflEndFightSegment(1)); // player defeated enemy
    seq(new EflSkipTextsSegment(1)); // no forget this
    seq(new EflWalkToSegment(10, 5)); // engage old man
    seq(new EflWalkToSegment(10, 4)); // engage old man
    seqMove(new EflOverworldInteract(4)); // talk to old man
    seq(new EflSkipTextsSegment(12));

    save("t9");
    load("t9");

    seq(new EflWalkToSegment(2, 1)); // engage old man
    seq(new EflWalkToSegment(3, 1, false)); // engage old man
    seqMove(new EflOverworldInteract(5)); // talk to old man
    seq(new EflSkipTextsSegment(10)); // get flute
    seq(new EflWalkToSegment(2, 8, false)); // leave house

    seqMetric(new OutputParty());

  }
}
