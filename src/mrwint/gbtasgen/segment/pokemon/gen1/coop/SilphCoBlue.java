package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import static mrwint.gbtasgen.metric.comparator.Comparator.GREATER_EQUAL;
import static mrwint.gbtasgen.move.Move.A;
import static mrwint.gbtasgen.move.Move.B;
import static mrwint.gbtasgen.move.Move.DOWN;
import static mrwint.gbtasgen.move.Move.LEFT;
import static mrwint.gbtasgen.move.Move.RIGHT;
import static mrwint.gbtasgen.move.Move.START;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.BLASTOISE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.CHARMELEON;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.COMET_PUNCH;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.CONFUSION;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.CUBONE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DISABLE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DITTO;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DOUBLE_KICK;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DOUBLE_SLAP;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DRAGONAIR;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DRAGON_RAGE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.ELIXER;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.ESCAPE_ROPE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.FIRE_PUNCH;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.FURY_ATTACK;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.GROWL;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.HM02;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.HM04;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.IVYSAUR;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.JYNX;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.LEER;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.MANKEY;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.MOON_STONE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.NIDOKING;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.NIDOQUEEN;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.NIDORINA;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.NIDORINO;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.ODDISH;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PERSIAN;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PIDGEOTTO;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.POISON_GAS;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.POISON_POWDER;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.POLIWHIRL;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PSYBEAM;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.REFLECT;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.ROAR;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.ROLLING_KICK;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SAND_ATTACK;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TAIL_WHIP;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TENTACOOL;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.WARTORTLE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.WEEPINBELL;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.MENU;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.PRESSED;
import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckDisableEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckPoisonEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckSwitchAndTeleportEffectUsed;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputBoxMons;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputItems;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputParty;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.pokemon.gen1.EflOverworldInteract;
import mrwint.gbtasgen.move.pokemon.gen1.EflWalkStep;
import mrwint.gbtasgen.segment.pokemon.EflCatchMonSegment;
import mrwint.gbtasgen.segment.pokemon.EflEvolutionSegment;
import mrwint.gbtasgen.segment.pokemon.EflLearnTMSegment;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.pokemon.EflWalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflCheckMoveDamage;
import mrwint.gbtasgen.segment.pokemon.fight.EflCheckMoveOrderMetric;
import mrwint.gbtasgen.segment.pokemon.fight.EflEndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflInitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EnemyFlinchSegment;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment.EflEnemyMoveDesc;
import mrwint.gbtasgen.segment.pokemon.fight.EflNewEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflSwitchPokemonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.Constants;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflBuyItemSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflCancelMoveLearnSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflChangeMonBoxSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflDepositMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflEncounterSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflFishSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSelectItemSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSelectMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSellItemSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflUseBikeSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflWithdrawMonSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;

public class SilphCoBlue extends SeqSegment {

	@Override
	public void execute() {

//    seqEflButton(A); // continue game
//    seqEflButton(START);
//    seqEflButton(A);
//    seqEflButton(START);
//    seqEflButton(A);
//
//    {
//      seq(new EflWalkToSegment(13, 4)); // PC
//      seq(new EflWalkToSegment(13, 3, false)); // PC
//      seq(new EflSelectItemSegment(MOON_STONE).fromOverworld().andUse());
//      seq(new EflSelectMonSegment(NIDORINA));
//      seq(new EflEvolutionSegment()); // Nidoqueen
//      seqEflButton(B);
//      seqEflButton(START);
//
//      {
//        seqEflButton(A); // use PC
//        seq(new EflSkipTextsSegment(1)); // turned on
//        seqEflButton(A); // someone's PC
//        seq(new EflSkipTextsSegment(2)); // accessed, mon storage
//        seq(new EflDepositMonSegment(NIDOQUEEN));
//        seq(new EflDepositMonSegment(PERSIAN));
//        seq(new EflDepositMonSegment(CUBONE));
//        seq(new EflChangeMonBoxSegment(1)); // box 2
//        seq(new EflWithdrawMonSegment(TENTACOOL));
//        seq(new EflWithdrawMonSegment(PIDGEOTTO));
//        seq(new EflChangeMonBoxSegment(2)); // box 3
//        seq(new EflDepositMonSegment(ODDISH));
//        seq(new EflWithdrawMonSegment(DRAGONAIR));
//        seq(new EflWithdrawMonSegment(IVYSAUR));
//        seqEflButton(B, MENU); // cancel
//        seqEflButton(B, PRESSED); // cancel
//      }
//    }
//    seqMetric(new OutputParty());
//    seqMetric(new OutputItems());
//
//    seqUnbounded(new EflWalkToSegment(4, 6)); // leave center
//    seq(new EflWalkToSegment(4, 8, false)); // leave center
//
//    seq(new EflSelectItemSegment(HM02).fromOverworld().andUse());
//    seq(new EflLearnTMSegment(PIDGEOTTO, 0)); // sand-attack -> Fly
//    seqEflButton(B); // cancel
//    seq(new EflSelectMonSegment(IVYSAUR).fromMainMenu().andSwitchWith(CHARMELEON));
//    seqEflSkipInput(0);
//    seq(new EflSelectMonSegment(PIDGEOTTO).andFlyTo(1)); // Cinnabar
//    seqEflSkipInput(1);
//    seq(new EflWalkToSegment(18, 3)); // enter gym
//
//    seq(new EflWalkToSegment(15, 9));
//    seq(new EflWalkToSegment(15, 8));
//    seqEflButton(Move.A);
//    seq(new EflSkipTextsSegment(8));
//    seq(new EflSkipTextsSegment(1, true)); // yes
//    seq(new EflSkipTextsSegment(2));
//
//    seq(new EflWalkToSegment(10, 2));
//    seq(new EflWalkToSegment(10, 1, false));
//    seqEflButton(Move.A);
//    seq(new EflSkipTextsSegment(9));
//    seq(new EflSkipTextsSegment(1, false)); // no
//    seq(new EflSkipTextsSegment(2));
//
//    seq(new EflWalkToSegment(9, 8));
//    seq(new EflWalkToSegment(9, 7, false));
//    seqEflButton(Move.A);
//    seq(new EflSkipTextsSegment(8));
//    seq(new EflSkipTextsSegment(1, false)); // no
//    seq(new EflSkipTextsSegment(2));
//
//    seq(new EflWalkToSegment(9, 14));
//    seq(new EflWalkToSegment(9, 13, false));
//    seqEflButton(Move.A);
//    seq(new EflSkipTextsSegment(10));
//    seq(new EflSkipTextsSegment(1, false)); // no
//    seq(new EflSkipTextsSegment(2));
//
//    seq(new EflWalkToSegment(1, 15));
//    seq(new EflWalkToSegment(1, 14));
//    seqEflButton(Move.A);
//    seq(new EflSkipTextsSegment(10));
//    seq(new EflSkipTextsSegment(1, true)); // yes
//    seq(new EflSkipTextsSegment(2));
//
//    seq(new EflWalkToSegment(1, 9));
//    seq(new EflWalkToSegment(1, 8));
//    seqEflButton(Move.A);
//    seq(new EflSkipTextsSegment(8));
//    seq(new EflSkipTextsSegment(1, false)); // no
//    seq(new EflSkipTextsSegment(2));
//
//    seq(new EflWalkToSegment(3, 5)); // blaine
//    seq(new EflWalkToSegment(3, 4)); // blaine
//    seqMove(new EflOverworldInteract(1));
//    seq(new EflInitFightSegment(6)); // start fight
//    save("tmp");
//    load("tmp");
//    seqMetric(new OutputParty());
//    seq(new EflSwitchPokemonSegment(WARTORTLE, EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), LEER)));
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[3][1] = 1; // Bubblebeam crit
//      kems.numExpGainers = 4; // Bulbasaur boosted, lvlup to 17
//      seq(kems); // growlithe
//    }
//    save("tmp2");
//    load("tmp2");
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), TAIL_WHIP, GROWL)};
//      kems.attackCount[3][1] = 1; // Bubblebeam crit
//      seq(kems); // ponyta
//    }
//    save("tmp3");
//    load("tmp3");
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    seq(new EflSwitchPokemonSegment(TENTACOOL, EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), TAIL_WHIP, GROWL)));
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment(); // TODO: consider blaine super potion
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), TAIL_WHIP, GROWL)};
//      kems.attackCount[2][1] = 2; // Water Gun crit
//      kems.numExpGainers = 2; // Wartortle, Tentacool
//      seq(kems); // rapidash
//    }
//    save("tmp4");
//    load("tmp4");
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckSwitchAndTeleportEffectUsed(), ROAR)};
//      kems.attackCount[2][1] = 3; // Water Gun crit
//      seq(kems); // arcanine
//    }
//    seq(new EflEndFightSegment(2)); // player defeated enemy
//    seq(new EflSkipTextsSegment(6+4)); // after battle texts (with room)
//
//    save("si0");
//    load("si0");
//    seq(new EflSelectItemSegment(ESCAPE_ROPE).fromOverworld().andUse());
//    seqEflSkipInput(2);
//    seq(new EflSelectMonSegment(PIDGEOTTO).fromOverworld().andFlyTo(-4)); // Lavender
//    seqEflSkipInput(1);
//	  seq(new EflUseBikeSegment().fromOverworld());
//    seq(new EflWalkToSegment(9, 18)); // leave
//    seq(new EflWalkToSegment(10, 16, false)); // house
//    seq(new EflWalkToSegment(4, 8, false)); // leave house
//    seq(new EflWalkToSegment(11, 22)); // water
//    seq(new EflSelectMonSegment(DRAGONAIR).fromOverworld().andSurf());
//    for(int i = 0; i < 4; i++)
//      seqMove(new EflWalkStep(RIGHT, true));
//    for(int i = 0; i < 56; i++)
//      seqMove(new EflWalkStep(DOWN, true));
//    seqMove(new EflWalkStep(LEFT, true));
//    seq(new EflWalkToSegment(11, 77)); // enter fishing house
//    seq(new EflWalkToSegment(2, 5)); // fisher
//    seqMove(new EflOverworldInteract(1));
//    seq(new EflSkipTextsSegment(2));
//    seq(new EflSkipTextsSegment(1, true)); // I like to fish
//    seq(new EflSkipTextsSegment(7)); // got super rod
//    seq(new EflWalkToSegment(2, 8, false)); // leave
//    seq(new EflSelectMonSegment(PIDGEOTTO).fromOverworld().andFlyTo(3)); // Celadon
//    seqEflSkipInput(1);
//
//    save("si1");
//    load("si1");
//
//    seq(new EflUseBikeSegment().fromOverworld());
//    seq(new EflWalkToSegment(10, 13)); // enter mart
//    seq(new EflWalkToSegment(12, 1)); // 2nd floor
//    seq(new EflWalkToSegment(16, 1)); // 3rd floor
//    seq(new EflWalkToSegment(12, 1)); // 4th floor
//    seq(new EflWalkToSegment(16, 1)); // 5th floor
//    {
//      seq(new EflWalkToSegment(5, 4, false)); // left cashier
//      seqMove(new EflOverworldInteract(3));
//      seq(new EflSkipTextsSegment(1, true)); // buy
//      seq(new EflTextSegment());
//      {
//        seq(new EflBuyItemSegment(5, 8)); // X Speed x8
//        seq(new EflBuyItemSegment(0, 4, true)); // X Atk x4
//      }
//      seqEflButton(B); // cancel
//      seq(new EflSkipTextsSegment(2)); // cancel + bye
//    }
//    seq(new EflWalkToSegment(16,1)); // 4th
//
//    save("tmp");
//    load("tmp");
//
//    {
//      seq(new EflWalkToSegment(5, 6, false)); // left cashier
//      seqMove(new EflOverworldInteract(1));
//      {
//        seq(new EflTextSegment(B));
//        seqEflButton(DOWN | A); // sell
//        seq(new EflTextSegment(B)); // what to sell
//
//        seqMetric(new OutputItems());
//        seq(new EflSellItemSegment(10, 0)); // Nugget
//        seq(new EflSellItemSegment(10, 0)); // TM06
//        seq(new EflSellItemSegment(13, 0)); // TM38
//        seqEflButton(B);
//      }
//
//
//      seq(new EflSkipTextsSegment(1, true)); // buy
//      seq(new EflTextSegment(B));
//      {
//        seq(new EflBuyItemSegment(4, 3)); // Leaf Stone x3
////        seq(new EflBuyItemSegment(0, 2)); // Thunder Stone x2
//        seq(new EflBuyItemSegment(1, 4)); // Water Stone x4
//        seq(new EflBuyItemSegment(-1, 2, true)); // Fire Stone x2
//      }
//      seqEflButton(B); // cancel
//      seq(new EflSkipTextsSegment(2)); // cancel + bye
//    }
//    seq(new EflWalkToSegment(12,1)); // 3rd
//    seq(new EflWalkToSegment(16,1)); // 2nd
//    {
//      seq(new EflWalkToSegment(9,3)); // right cashier
//      seq(new EflWalkToSegment(8,3)); // right cashier
//      seqMove(new EflOverworldInteract(2));
//      seq(new EflSkipTextsSegment(1, true)); // buy
//      seq(new EflTextSegment());
//      {
//        seq(new EflBuyItemSegment(3, 1, true)); // TM07
//      }
//      seqEflButton(B); // cancel
//      seq(new EflSkipTextsSegment(2)); // cancel + bye
//    }
//    seq(new EflWalkToSegment(12,1)); // 1st
//    seq(new EflWalkToSegment(16,8,false)); // out
//
//    seqMetric(new OutputItems());
//
//    save("si2");
//    load("si2");
//
//    seq(new EflUseBikeSegment().fromOverworld());
//    seq(new EflWalkToSegment(21, 21, false)); // water
//
//    {
//      seqMetric(new OutputItems());
//      seq(new EflFishSegment(POLIWHIRL)); // Poliwhirl
//      seq(new EflCatchMonSegment());
//    }
//
//    seq(new EflWalkToSegment(35, 30)); // bush
//    seq(new EflWalkToSegment(35, 31)); // bush
//    seq(new EflSelectMonSegment(IVYSAUR).fromOverworld().andCut());
//    seq(new EflWalkToSegment(12, 27)); // enter gym
//    seq(new EflWalkToSegment(1, 4)); // bush
//    seqMetric(new OutputParty());
//    seq(new EflSelectMonSegment(IVYSAUR).fromOverworld().andSwitchWith(DRAGONAIR));
//    seq(new EflSelectMonSegment(IVYSAUR).andCut());
//    seq(new EflWalkToSegment(3, 4)); // engage
//    seq(new EflInitFightSegment(2)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[1][0] = 1; // ice beam
////      kems.numExpGainers = 2; // dragonair lvlup to 33
//      seq(kems); // exeggcute
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//
//    save("si3");
//    load("si3");
//
//    seq(new EflSelectMonSegment(DRAGONAIR).fromOverworld().andSwitchWith(PIDGEOTTO));
//    seqEflButton(B); // cancel
//    seqEflButton(START); // cancel
//
//    seq(new EflWalkToSegment(4, 3, false)); // engage erika
//    seqMove(new EflOverworldInteract(1)); // engage erika
//    seq(new EflInitFightSegment(13)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckPoisonEffectMisses(), POISON_POWDER)};
//      kems.attackCount[0][1] = 1; // fly crit
//      kems.keepLastEnemyAttack = true;
//      seq(kems); // victreebel
//    }
//    save("tmp");
//    load("tmp");
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(20)}; // Bind
//      kems.attackCount[0][1] = 1; // fly crit
//      kems.keepLastEnemyAttack = true;
//      seq(kems); // tangela
//    }
//    save("tmp2");
//    load("tmp2");
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckPoisonEffectMisses(), POISON_POWDER)};
//      kems.attackCount[0][1] = 1; // fly crit
//      kems.keepLastEnemyAttack = true;
//      kems.numExpGainers = 2; // L33
//      seq(kems); // vileplume
//    }
//    seq(new EflEndFightSegment(3)); // player defeated enemy
//
//    save("si4");
//    load("si4");
//
//    seqMetric(new OutputParty());
//
//    seq(new EflSkipTextsSegment(7)); // after battle texts (no room)
////    seq(new EflSkipTextsSegment(11)); // after battle texts
//    seq(new EflWalkToSegment(5, 5)); // bush
//    seq(new EflWalkToSegment(5, 6)); // bush
//    seq(new EflSelectMonSegment(IVYSAUR).fromOverworld().andCut());
//    seq(new EflWalkToSegment(5, 18, false)); // leave gym
//    seq(new EflSelectMonSegment(PIDGEOTTO).fromOverworld().andFlyTo(2)); // Fuchsia
//    seqEflSkipInput(1);
//    seq(new EflUseBikeSegment().fromOverworld());
//    seq(new EflWalkToSegment(23, 28, false)); // ledge
//    seq(new EflWalkToSegment(27, 27)); // warden house
//    seq(new EflWalkToSegment(2, 5)); // warden
//    seq(new EflWalkToSegment(2, 4)); // warden
//    seqMove(new EflOverworldInteract(1)); //warden
//    seq(new EflSkipTextsSegment(11)); // get HM04
//    seq(new EflSelectItemSegment(HM04).fromOverworld().andUse());
//    seq(new EflLearnTMSegment(CHARMELEON, 3)); // Charmeleon Mega Punch -> Strength
//    seqEflButton(B); // cancel
//    seq(new EflSelectMonSegment(CHARMELEON).fromMainMenu().andStrength());
//    seq(new EflWalkToSegment(8, 4, false)); // boulder
//    seq(new EflWalkToSegment(8, 4, false)); // boulder
//    seq(new EflWalkToSegment(8, 3, false)); // boulder
//    seqMove(new EflOverworldInteract(2)); // Rare Candy
//    seq(new EflTextSegment()); // Rare Candy
//    seq(new EflWalkToSegment(5, 6)); // leave
//    seq(new EflWalkToSegment(5, 8, false)); // leave
//
//    save("si5");
//    load("si5");
//
//    seqUnbounded(new EflUseBikeSegment().fromOverworld());
//    seqUnbounded(new EflWalkToSegment(40, 17)); // leave fuchsia
//    seqUnbounded(new EflWalkToSegment(8, 9, false)); // enter house
//    seqUnbounded(new EflWalkToSegment(8, 5, false)); // leave house
//    save("tmp");
//    load("tmp");
//    seqUnbounded(new EflWalkToSegment(17, 9)); // grass
//    seq(new EflEncounterSegment(WEEPINBELL, RIGHT));
//    save("tmp2");
////    load("tmp2");
//    seq(new EflCatchMonSegment().withBufferSize(0));
//
//    seqUnbounded(new EflWalkToSegment(20, 9)); // grass
//    seq(new EflEncounterSegment(DITTO, RIGHT));
//    save("tmp3");
////    load("tmp3");
//    seq(new EflCatchMonSegment());
//
//    seq(new EflSelectMonSegment(PIDGEOTTO).fromOverworld().andFlyTo(3)); // Celadon
//    seqEflSkipInput(1);
//
//    save("si6");
//    load("si6");
//
//    seq(new EflUseBikeSegment().fromOverworld());
//    {
//      seq(new EflWalkToSegment(25, 4, false)); // house
//      seq(new EflWalkToSegment(2, 1)); // house
//      seq(new EflWalkToSegment(4, 1)); // house
//      seq(new EflWalkToSegment(2, 1)); // house
//      seq(new EflWalkToSegment(2, 7)); // house
//      seq(new EflWalkToSegment(4, 3, false)); // eevee ball
//      seqMove(new EflOverworldInteract(2)); // eevee ball
//      seq(new EflTextSegment()); // got eevee
//      seq(new EflSkipTextsSegment(2)); // no nick
//      seq(new EflSkipTextsSegment(4)); // no room, to box
//      seq(new EflWalkToSegment(3, 6)); // leave
//      seq(new EflWalkToSegment(3, 8, false)); // leave
//      seq(new EflWalkToSegment(2, 1)); // house
//      seq(new EflWalkToSegment(4, 1)); // house
//      seq(new EflWalkToSegment(2, 1)); // house
//      seq(new EflWalkToSegment(4, 0)); // house
//      seq(new EflUseBikeSegment().fromOverworld());
//    }
//    seq(new EflWalkToSegment(50, 10)); // leave celadon
//    seq(new EflWalkToSegment(12, 10, false)); // enter house
//
//    seq(new EflWalkToSegment(3, 4)); // talk to guard
//    seq(new EflSkipTextsSegment(15)); // give water
//    seq(new EflWalkToSegment(6, 4, false)); // leave house
//    seq(new EflUseBikeSegment().fromOverworld());
//    seq(new EflWalkToSegment(20, 10)); // enter saffron
//    seq(new EflWalkToSegment(18, 21)); // enter silph co
//
//    save("si7");
//    load("si7");
//
//    {
//      seq(new EflWalkToSegment(26, 0)); // 2f
//      seq(new EflWalkToSegment(26, 0)); // 3f
//      seq(new EflWalkToSegment(24, 0)); // 4f
//      seq(new EflWalkToSegment(26, 0)); // 5f
//    }
////    seq(new EflWalkToSegment(20, 0)); // elevator
////    seq(new EflWalkToSegment(3, 2)); // elevator
////    seq(new EflWalkToSegment(3, 1)); // elevator
////    seqMove(new EflOverworldInteract(1));
////    seq(new EflTextSegment());
////    seqEflScrollFastA(9); // 10F
////    seq(new EflWalkToSegment(2, 2)); // elevator
////    seq(new EflWalkToSegment(2, 4, false)); // elevator
////
////    seq(new EflWalkToSegment(4, 9)); // rocket
////
////    seq(new EflInitFightSegment(2)); // start fight
////    {
////      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
////      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), LEER)};
////      kems.attackCount[0][1] = 1; // fly crit
////      kems.keepLastEnemyAttack = true;
////      seq(kems); // machoke
////    }
////    seq(new EflEndFightSegment(1)); // player defeated enemy
////
//////    save("si8a");
//////    load("si8a");
////
////    seq(new EflSelectMonSegment(PIDGEOTTO).fromOverworld().andSwitchWith(IVYSAUR));
////    seqEflButton(B);
////    seqEflButton(START);
////
////    seq(new EflWalkToSegment(4, 14, false)); // rare candy
////    seqMove(new EflOverworldInteract(5)); // rare candy
////    seq(new EflTextSegment()); // rare candy
////
////    seq(new EflWalkToSegment(8, 0)); // 9f
////    seq(new EflWalkToSegment(16, 0)); // 8f
////    seq(new EflWalkToSegment(14, 0)); // 7f
////    seq(new EflWalkToSegment(22, 0)); // 6f
////    seq(new EflWalkToSegment(14, 0)); // 5f
//    {
//      seq(new EflWalkToSegment(14, 3)); // elixer
//      seq(new EflWalkToSegment(13, 3)); // elixer
//      seqEflButton(Move.A);
//      seq(new EflTextSegment()); // elixer
//    }
//    seq(new EflWalkToSegment(8, 15).setBlockAllWarps(true));
//    seqMove(new EflOverworldInteract(2));
//    seq(new EflInitFightSegment(1)); // start fight
////    seq(new EflSwitchPokemonSegment(DRAGONAIR, EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), LEER))); // dragonair
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), LEER)};
//      kems.attackCount[0][1] = 1; // Fly crit
//      kems.attackCount[3][1] = 1; // Wing Attack crit
//      kems.keepLastEnemyAttack = true;
////      kems.numExpGainers = 4; // ivysaur boosted, lvlup
//      seq(kems); // arbok
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//    seq(new EflWalkToSegment(9, 15)); // warp
//    seq(new EflWalkToSegment(16, 15));
//    seq(new EflWalkToSegment(17, 15)); // warp back
//    seq(new EflWalkToSegment(20, 16));
//    seqMove(new EflOverworldInteract(8)); // card key
//    seq(new EflTextSegment());
//
//    save("si9");
//    load("si9");
//
//    seqEflSkipInput(1);
//    seq(new EflSelectMonSegment(PIDGEOTTO).fromOverworld().andSwitchWith(IVYSAUR));
//    seqEflButton(B);
//    seqEflButton(START);
//
//    seq(new EflWalkToSegment(9, 15)); // warp
//    seq(new EflWalkToSegment(16, 15));
//    seq(new EflWalkToSegment(17, 15)); // warp back
//    seq(new EflWalkToSegment(9, 13));
//    seq(new EflWalkToSegment(8, 13));
//    seqEflButton(Move.A); // use card key
//    seq(new EflTextSegment());
//    seq(new EflSkipTextsSegment(2)); // open door
//    seq(new EflWalkToSegment(3, 15)); // warp
//    seq(new EflWalkToSegment(18, 9));
//    seq(new EflWalkToSegment(17, 9, false));
//    seqEflButton(Move.A); // use card key
//    seq(new EflTextSegment());
//    seq(new EflSkipTextsSegment(2)); // open door
//    seq(new EflWalkToSegment(11, 11)); // warp
//    seq(new EflWalkToSegment(4, 2));
//    seq(new EflWalkToSegment(3, 2)); // engage rival
//
//    seq(new EflInitFightSegment(10)); // start fight
//    seq(new EflSwitchPokemonSegment(DRAGONAIR, EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), SAND_ATTACK)));
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), SAND_ATTACK)};
//      kems.attackCount[0][0] = 1; // slam crit
//      kems.attackCount[3][1] = 1; // thunderbolt crit
//      kems.numExpGainers = 4; // ivysaur boosted, lvlup to 19
//      seq(kems); // pidgeot
//    }
//    save("tmp");
//    load("tmp");
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    seq(new EflSwitchPokemonSegment(IVYSAUR, EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), LEER)));
//    seq(new EflSwitchPokemonSegment(DRAGONAIR, EflEnemyMoveDesc.missWith(DRAGON_RAGE)));
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(DRAGON_RAGE)};
//      kems.attackCount[3][1] = 1; // thunderbolt crit
//      kems.numExpGainers = 4; // ivysaur, boosted, lvlup 20, dragonair
//      seq(kems); // gyarados
//    }
//    save("tmp2");
//    load("tmp2");
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[2][1] = 1; // surf crit
//      kems.numExpGainers = 2; // Dragonair, lvlup to 31
//      seq(kems); // growlithe
//    }
//    save("tmp3");
//    load("tmp3");
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    seq(new EflSwitchPokemonSegment(IVYSAUR, EflEnemyMoveDesc.missWith(PSYBEAM, CONFUSION)));
//    seq(new EflSwitchPokemonSegment(DRAGONAIR, EflEnemyMoveDesc.missWith(new CheckDisableEffectMisses(), DISABLE)));
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[0][0] = 1; // slam
//      kems.attackCount[0][1] = 1; // slam crit
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckDisableEffectMisses(), DISABLE)};
//      kems.numExpGainers = 4; // ivysaur boosted, lvlup to 21
//      seq(kems); // alakazam
//    }
//    save("tmp4");
//    load("tmp4");
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    seq(new EflSwitchPokemonSegment(IVYSAUR, EflEnemyMoveDesc.missWith(new CheckPoisonEffectMisses(), POISON_POWDER)));
//    seq(new EflSwitchPokemonSegment(DRAGONAIR, EflEnemyMoveDesc.missWith(new CheckPoisonEffectMisses(), POISON_POWDER)));
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckPoisonEffectMisses(), POISON_POWDER)};
//      kems.attackCount[1][1] = 2; // ice beam crit
//      kems.numExpGainers = 3; // ivysaur, boosted, lvlup to 22
//      seq(kems); // venusaur
//    }
//
//    save("si10");
//    load("si10");
//    seq(new EflCancelMoveLearnSegment()); //
//    seq(new EflSkipTextsSegment(1)); // dragonair exp
//    seq(new EflEndFightSegment(2)); // player defeated enemy
//    seq(new EflSkipTextsSegment(14)); // after battle texts
//
//    seqMetric(new OutputParty());
//
//    seq(new EflSelectMonSegment(IVYSAUR).fromOverworld().andSwitchWith(WARTORTLE));
//    seqEflButton(B);
//    seqEflButton(START);
//
//    seq(new EflWalkToSegment(3, 5)); // lapras
//    seq(new EflWalkToSegment(2, 5)); // lapras
//    seqMove(new EflOverworldInteract(1)); // lapras
//    seq(new EflSkipTextsSegment(5));
//    seq(new EflTextSegment()); // get lapras
//    seq(new EflSkipTextsSegment(6)); // no nickname, no room
//    seq(new EflSkipTextsSegment(8)); // intelligent, bla bla
//
//    seq(new EflWalkToSegment(5, 7)); // warp
//    seq(new EflWalkToSegment(3, 16, false)); // engage
//    seqMove(new EflOverworldInteract(4));
//    seq(new EflInitFightSegment(1)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[3][0] = 1; // Bubblebeam
//      seq(kems); // cubone
//    }
//    save("tmp");
//    load("tmp");
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      seqEflButton(A, PRESSED); // fight
//      seqEflScroll(-2); // bite
//      delayEfl(new SeqSegment() {
//        @Override
//        protected void execute() {
//          seqEflButtonUnboundedNoDelay(A); // use move
//          seqMetric(new EflCheckMoveOrderMetric(true));
//          seqUnbounded(new EflTextSegment()); // used bite
//          seqMetric(new EflCheckMoveDamage(false, false, 0, 38, Integer.MAX_VALUE, false),GREATER_EQUAL, 38);
//          seqMetric(new EnemyFlinchSegment.EnemyFlinchMetric());
//        }
//      });
//      seq(new EflSkipTextsSegment(1)); // enemy flinched
//    }
//    seq(new EflSwitchPokemonSegment(DRAGONAIR, EflEnemyMoveDesc.missWith(new CheckPoisonEffectMisses(), POISON_GAS)));
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[0][0] = 1; // slam
//      kems.numExpGainers = 3; // wartortle, lvlup to 36, dragonair
//      seq(kems); // drowzee
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[1][0] = 1; // ice beam
//      seq(kems); // marowak
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//    seq(new EflEvolutionSegment()); // Blastoise
//
//    save("si11");
//    load("si11");
//
//    seq(new EflSelectMonSegment(BLASTOISE).fromOverworld().andSwitchWith(IVYSAUR));
//    seqEflButton(B);
//    seqEflButton(START);
//
//    seq(new EflWalkToSegment(6, 15));
//    seq(new EflWalkToSegment(6, 14));
//    seqEflButton(Move.A); // use card key
//    seq(new EflTextSegment());
//    seq(new EflSkipTextsSegment(2)); // open door
//    seq(new EflWalkToSegment(6, 13));
//    seq(new EflInitFightSegment(7)); // start fight
//    seq(new EflSwitchPokemonSegment(DRAGONAIR, EflEnemyMoveDesc.missWith(FURY_ATTACK)));
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(FURY_ATTACK)};
//      kems.attackCount[3][0] = 1; // thunderbolt
//      kems.attackCount[3][1] = 1; // thunderbolt crit
//      kems.numExpGainers = 3; // dragonair, ivysaur boosted
//      seq(kems); // nidorino
//    }
//    save("tmp");
//    load("tmp");
//    seqMetric(new OutputParty());
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    seq(new EflSwitchPokemonSegment(IVYSAUR, EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), TAIL_WHIP)));
//    seq(new EflSwitchPokemonSegment(DRAGONAIR, EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), TAIL_WHIP)));
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), TAIL_WHIP)};
//      kems.attackCount[3][0] = 1; // thunderbolt
//      kems.attackCount[3][1] = 1; // thunderbolt crit
//      kems.numExpGainers = 5; // ivysaur, boosted, lvlup, dragonair, lvlup to 32
//      seq(kems); // kangaskhan
//    }
//    save("tmp2");
//    load("tmp2");
//
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    seq(new EflSwitchPokemonSegment(IVYSAUR, EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), TAIL_WHIP)));
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), TAIL_WHIP)};
//      kems.attackCount[3][1] = 1; // vine whip crit
//      kems.numExpGainers = 4; // ivysaur, boosted, lvlup to 24, dragonair
//      seq(kems); // rhyhorn
//    }
//    save("tmp3");
//    load("tmp3");
//    seqMetric(new OutputParty());
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    seq(new EflSwitchPokemonSegment(DRAGONAIR, EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), TAIL_WHIP)));
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), TAIL_WHIP)};
//      kems.attackCount[1][0] = 1; // ice beam
//      kems.attackCount[1][1] = 1; // ice beam crit
//      kems.numExpGainers = 4; // ivysaur, boosted, lvlup to 25, dragonair
//      seq(kems); // nidoqueen
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//    seq(new EflSkipTextsSegment(7)); // after battle texts
//
//    save("si12");
//    load("si12");
//
//    seqMetric(new OutputParty());
//    seq(new EflSelectItemSegment(ESCAPE_ROPE).fromOverworld().andUse());
//    seqEflSkipInput(2);
//    seq(new EflSelectMonSegment(IVYSAUR).fromOverworld().andSwitchWith(PIDGEOTTO));
//    seqEflSkipInput(0);
//    seq(new EflSelectMonSegment(PIDGEOTTO).andFlyTo(1)); // Saffron
//    seqEflSkipInput(1);
//    seq(new EflUseBikeSegment().fromOverworld());
//
//    seq(new EflWalkToSegment(26, 3)); // dojo
//    seq(new EflWalkToSegment(2, 4)); // blackbelt
//    seqMove(new EflOverworldInteract(2)); // blackbelt
//
//    seq(new EflInitFightSegment(1)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[3][1] = 1; // wing attack crit
//      seq(kems); // mankey
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[3][1] = 1; // wing attack crit
//      seq(kems); // mankey
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), LEER)};
//      kems.attackCount[0][1] = 1; // fly crit
//      kems.numExpGainers = 2; // lvlup to 34
//      seq(kems); // primeape
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//
//    save("si13");
//    load("si13");
//
//    seq(new EflWalkToSegment(4, 3)); // blackbelt
//    seq(new EflInitFightSegment(6)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(DOUBLE_KICK, ROLLING_KICK)};
//      kems.attackCount[0][1] = 1; // fly crit
//      seq(kems); // hitmonlee
//    }
//    save("tmp2");
//    load("tmp2");
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(COMET_PUNCH, FIRE_PUNCH)};
//      kems.attackCount[0][1] = 1; // fly crit
//      kems.keepLastEnemyAttack = true;
//      seq(kems); // hitmonchan
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//
//    save("si14");
//    load("si14");
//
//    seq(new EflSkipTextsSegment(7)); // after battle texts
//    seq(new EflWalkToSegment(4, 2)); // hitmonchan
//    seqMove(new EflOverworldInteract(6)); // hitmonlee
//    seqEflButton(B); // skip dex
//    seqEflButton(A); // skip dex
//    seq(new EflSkipTextsSegment(1)); // do you want?
//    seq(new EflSkipTextsSegment(1, true)); // want!
//    seq(new EflTextSegment()); // got hitmonlee
//    seq(new EflSkipTextsSegment(6)); // no nickname, no room
//    seq(new EflWalkToSegment(4, 12, false)); // leave
//
//    seq(new EflWalkToSegment(34, 3)); // gym
//    seq(new EflWalkToSegment(11, 15)); // warp
//    seq(new EflWalkToSegment(15, 15)); // warp
//    seq(new EflWalkToSegment(15, 5)); // warp
//    seq(new EflWalkToSegment(1, 5)); // warp
//    seq(new EflWalkToSegment(9, 10));
//    seq(new EflWalkToSegment(9, 9));
//    seqMove(new EflOverworldInteract(1)); // talk to sabrina
//
//    seq(new EflInitFightSegment(8)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckDisableEffectMisses(), DISABLE)};
//      kems.attackCount[0][1] = 1; // fly crit
//      kems.numExpGainers = 2; // lvlup to 35
//      seq(kems); // kadabra
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    save("tmp1");
//    load("tmp1");
//    seqMetric(new OutputParty());
//    seq(new EflSwitchPokemonSegment(DRAGONAIR, EflEnemyMoveDesc.missWith(CONFUSION, DOUBLE_SLAP)));
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(CONFUSION, DOUBLE_SLAP)};
//      kems.attackCount[0][1] = 1; // slam crit
//      kems.attackCount[3][1] = 1; // thunderbolt crit
//      kems.numExpGainers = 2; // pidgeotto
//      seq(kems); // mr.mime
//    }
//    save("tmp2");
//    load("tmp2");
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckPoisonEffectMisses(), POISON_POWDER)};
//      kems.attackCount[0][1] = 2; // slam crit
//      seq(kems); // venomoth
//    }
//    save("tmp3");
//    load("tmp3");
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    seqMetric(new OutputParty());
//    seq(new EflSwitchPokemonSegment(IVYSAUR, EflEnemyMoveDesc.missWith(Metric.TRUE, REFLECT)));
//    seq(new EflSwitchPokemonSegment(CHARMELEON, EflEnemyMoveDesc.missWith(Metric.TRUE, REFLECT)));
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(Metric.TRUE, REFLECT)};
//      kems.attackCount[1][1] = 2; // slash crit
//      kems.numExpGainers = 7; // dragonair, lvlup, ivysaur, boosted, charmeleon, boosted, lvlup to 36
//      seq(kems); // alakazam
//    }
//    save("tmp4");
    load("tmp4");
    seq(new EflEndFightSegment(6)); // player defeated enemy
    seq(new EflEvolutionSegment()); // Charizard
    seq(new EflSkipTextsSegment(9)); // after battle texts (no room)

    save("si15");
    load("si15");

    seq(new EflWalkToSegment(11, 11)); // warp

    seq(new EflSelectItemSegment(ESCAPE_ROPE).fromOverworld().andUse());
    seqEflSkipInput(2);
    seqMetric(new OutputBoxMons());
  }
}
