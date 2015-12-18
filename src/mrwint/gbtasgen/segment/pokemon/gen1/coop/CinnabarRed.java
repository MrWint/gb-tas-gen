package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import static mrwint.gbtasgen.move.Move.A;
import static mrwint.gbtasgen.move.Move.B;
import static mrwint.gbtasgen.move.Move.DOWN;
import static mrwint.gbtasgen.move.Move.LEFT;
import static mrwint.gbtasgen.move.Move.RIGHT;
import static mrwint.gbtasgen.move.Move.SELECT;
import static mrwint.gbtasgen.move.Move.START;
import static mrwint.gbtasgen.move.Move.UP;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.AERODACTYL;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.AGILITY;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DRAGONAIR;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.EKANS;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.ESCAPE_ROPE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.EXEGGCUTE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.EXEGGUTOR;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.FARFETCHD;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.GRIMER;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.GROWL;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.GROWLITHE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.HM03;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.JOLTEON;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.JYNX;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.KABUTO;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.KOFFING;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.LEAF_STONE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.LEER;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.MAGMAR;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.MANKEY;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.MOON_STONE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.MUK;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.NIDOQUEEN;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.NIDORINA;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.NIDORINO;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.OMANYTE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PIDGEOTTO;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PIDGEY;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PINSIR;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.POLIWHIRL;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.POLIWRATH;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PONYTA;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.RATICATE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.ROAR;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SANDSHREW;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TAIL_WHIP;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TANGELA;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TENTACOOL;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TM21;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.WARTORTLE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.WATER_STONE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.WEEZING;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.MENU;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.PRESSED;
import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.metric.pokemon.CheckEncounterMetric;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckSwitchAndTeleportEffectUsed;
import mrwint.gbtasgen.metric.pokemon.gen1.Gen1CheckDVMetric;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputBoxMons;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputItems;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputParty;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.pokemon.gen1.EflOverworldInteract;
import mrwint.gbtasgen.move.pokemon.gen1.EflWalkStep;
import mrwint.gbtasgen.segment.pokemon.EflCatchMonSegment;
import mrwint.gbtasgen.segment.pokemon.EflEvolutionSegment;
import mrwint.gbtasgen.segment.pokemon.EflLearnTMSegment;
import mrwint.gbtasgen.segment.pokemon.EflOverrideMoveSegment;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.pokemon.EflWalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflEndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflInitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment.EflEnemyMoveDesc;
import mrwint.gbtasgen.segment.pokemon.fight.EflNewEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflSwitchPokemonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.Constants;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflBuyItemSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflChangeMonBoxSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflDepositMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflEncounterSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflReleaseMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSelectItemSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSelectMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflUseBikeSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflWithdrawMonSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;

public class CinnabarRed extends SeqSegment {

	@Override
	public void execute() {
//    seqMetric(new OutputParty());
//    seqMetric(new OutputItems());
//    seqMetric(new OutputBoxMons());
//
//    seq(new EflWalkToSegment(13, 25)); // enter center
//    {
//      seq(new EflWalkToSegment(13, 5)); // PC
//      seq(new EflWalkToSegment(13, 4)); // PC
//      seqMetric(new OutputItems());
//      seqMetric(new OutputParty());
//      seqMetric(new OutputBoxMons());
//      {
//        seqEflButton(A); // use PC
//        seq(new EflSkipTextsSegment(1)); // turned on
//        seqEflButton(A); // someone's PC
//        seq(new EflSkipTextsSegment(2)); // accessed, mon storage
//        seq(new EflDepositMonSegment(SANDSHREW));
//        seq(new EflWithdrawMonSegment(NIDORINA));
//        seq(new EflDepositMonSegment(PINSIR));
//        seq(new EflWithdrawMonSegment(NIDORINO));
//        seq(new EflDepositMonSegment(MAGMAR));
//        seq(new EflWithdrawMonSegment(EXEGGCUTE));
//        seq(new EflDepositMonSegment(JOLTEON));
//        seq(new EflWithdrawMonSegment(POLIWHIRL));
//        seq(new EflChangeMonBoxSegment(2)); // box 3
//        seqEflButton(B, MENU); // cancel
//        seqEflButton(B, MENU); // cancel
//      }
//      seq(new EflWalkToSegment(4, 6)); // leave center
//      seq(new EflWalkToSegment(4, 8, false)); // leave center
//    }
//
//    {
//      seq(new EflSelectItemSegment(MOON_STONE).fromOverworld().andUse());
//      seq(new EflSelectMonSegment(NIDORINA));
//      seq(new EflEvolutionSegment()); // Nidoqueen
//      seqEflSkipInput(0);
//      seq(new EflUseBikeSegment());
//      seq(new EflWalkToSegment(23, 17)); // mart
//      seq(new EflWalkToSegment(3, 5)); // shopkeep
//      seq(new EflWalkToSegment(2, 5)); // shopkeep
//      seqMove(new EflOverworldInteract(1));
//      {
//        seq(new EflSkipTextsSegment(1, true)); // buy
//        seq(new EflTextSegment(Move.B));
//        seq(new EflBuyItemSegment(0, -14, true)); // Poke Ball x85
//        seqEflButton(Move.B); // cancel
//        seq(new EflSkipTextsSegment(2)); // cancel + bye
//        seqMetric(new OutputItems());
//      }
//      seq(new EflWalkToSegment(3, 6)); // leave mart
//      seq(new EflWalkToSegment(3, 8, false)); // leave mart
//      seq(new EflUseBikeSegment().fromOverworld());
//      seq(new EflWalkToSegment(27, 4)); // bush
//      seq(new EflSelectMonSegment(FARFETCHD).fromOverworld().andCut());
//      seq(new EflWalkToSegment(19, 5)); // enter
//      seq(new EflWalkToSegment(15, 4)); // amber
//      seq(new EflWalkToSegment(15, 3)); // amber
//      seqMove(new EflOverworldInteract(3)); // talk
//      seq(new EflSkipTextsSegment(11)); // get amber
//      seq(new EflWalkToSegment(16, 6)); // leave
//      seq(new EflWalkToSegment(16, 8, false)); // leave
//    }
//
//    save("ci0");
//    load("ci0");
//
//    seq(new EflSelectMonSegment(FARFETCHD).fromOverworld().andFlyTo(-3)); // Cerulean
//    seqEflSkipInput(1);
//
//    seq(new EflWalkToSegment(13, 15)); // house
//    seq(new EflWalkToSegment(1, 4)); // trade
//    seq(new EflWalkToSegment(1, 3)); // trade
//    seqMove(new EflOverworldInteract(2));
//    seq(new EflSkipTextsSegment(1));
//    seq(new EflSkipTextsSegment(1, true)); // trade
//    seq(new EflSelectMonSegment(POLIWHIRL));
//    seq(new EflSkipTextsSegment(1)); // connect cables
//    seq(new EflSkipTextsSegment(3)); // traded x for y, thanks
//    seq(new EflWalkToSegment(2, 6)); // leave
//    seq(new EflWalkToSegment(2, 8, false)); // leave
//
//    seq(new EflSelectMonSegment(FARFETCHD).fromOverworld().andFlyTo(0)); // Pallet
//    seqEflSkipInput(1);
//
//    seq(new EflWalkToSegment(7, 12)); // water
//    seq(new EflWalkToSegment(7, 13)); // water
//    seqUnbounded(new EflSelectMonSegment(OMANYTE).fromOverworld().andSurf());
//
//    for(int i=0;i<5;i++)
//      seqMoveUnbounded(new EflWalkStep(Move.DOWN, true));
//    seqMoveUnbounded(new EflWalkStep(Move.RIGHT, true));
//
//    save("tmp");
//    //load("tmp");
//    seqUnbounded(new EflWalkToSegment(8, 3));
//    seq(new EflEncounterSegment(PIDGEOTTO, DOWN));
//    save("tmp2");
//    //load("tmp2");
//    seq(new EflCatchMonSegment().withBufferSize(0));
//
//    seqUnbounded(new EflWalkToSegment(8, 6));
//    seq(new EflEncounterSegment(RATICATE, DOWN));
//    save("tmp3");
//    //load("tmp3");
//    seq(new EflCatchMonSegment().withBufferSize(0));
//
//    seqUnbounded(new EflWalkToSegment(9, 7));
//    seqUnbounded(new EflWalkToSegment(9, 8));
//    seq(new EflEncounterSegment(TANGELA, DOWN));
//    seq(new EflCatchMonSegment());
//
//    save("ci1");
//    load("ci1");
//
//    seqUnbounded(new EflSelectMonSegment(OMANYTE).fromOverworld().andSurf());
//    for(int i=0;i<10;i++)
//      seqMoveUnbounded(new EflWalkStep(DOWN, true));
//    for(int i=0;i<6;i++)
//      seqMoveUnbounded(new EflWalkStep(LEFT, true));
//    for(int i=0;i<19;i++)
//      seqMoveUnbounded(new EflWalkStep(DOWN, true));
//    seq(new EflEncounterSegment(new CheckEncounterMetric(TENTACOOL, 30).withSpcDV(5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15), DOWN));
//    seq(new EflCatchMonSegment());
//    for(int i=0;i<60;i++)
//      seqMove(new EflWalkStep(DOWN, true));
//    seqMove(new EflWalkStep(RIGHT, true));
//
//    save("ci2");
//    load("ci2");
//
//    seqUnbounded(new EflWalkToSegment(6, 9)); // lab
//    seqUnbounded(new EflWalkToSegment(16, 4)); // room
//    seqUnbounded(new EflWalkToSegment(4, 3)); // room
//    delayEfl(new SeqSegment() {
//      @Override
//      protected void execute() {
//        seqEflButtonUnboundedNoDelay(A);
//        seqMetric(new EflOverworldInteract.OverworldInteractMetric(1));
//      }
//    });
//    seq(new EflSkipTextsSegment(4));
//    seqEflButton(Move.A); // Dome Fossil
//    seq(new EflSkipTextsSegment(6));
//    seq(new EflSkipTextsSegment(1, true)); // ress
//    seq(new EflSkipTextsSegment(4));
//    seq(new EflWalkToSegment(3, 8, false));
//    seqUnbounded(new EflWalkToSegment(3, 8, false));
//    seqUnbounded(new EflWalkToSegment(6, 9)); // enter lab
//    seqUnbounded(new EflWalkToSegment(16, 4)); // room
//    seqUnbounded(new EflWalkToSegment(4, 3)); // room
//    delayEfl(new SeqSegment() {
//      @Override
//      protected void execute() {
//        seqEflButtonUnboundedNoDelay(A);
//        seqMetric(new EflOverworldInteract.OverworldInteractMetric(1));
//      }
//    });
//    seqUnbounded(new EflSkipTextsSegment(2));
//    seqUnbounded(new EflTextSegment());
//    delayEfl(new SeqSegment() {
//      @Override
//      protected void execute() {
//        seqEflButtonUnbounded(B); // yes
//        seqMetric(new Gen1CheckDVMetric(15, 0, 12, 15, 15));
//      }
//    });
//    seq(new EflTextSegment()); // got Kabuto
//    seq(new EflSkipTextsSegment(2)); // no nick
//    seq(new EflSkipTextsSegment(3)); // no room
//    seqUnbounded(new EflSkipTextsSegment(1)); // no room
//    delayEfl(new SeqSegment() {
//      @Override
//      protected void execute() {
//        seqEflButtonUnboundedNoDelay(A);
//        seqMetric(new EflOverworldInteract.OverworldInteractMetric(1));
//      }
//    });
//    seq(new EflSkipTextsSegment(4));
//    seqEflButton(Move.A); // Old Amber
//    seq(new EflSkipTextsSegment(6));
//    seq(new EflSkipTextsSegment(1, true)); // ress
//    seq(new EflSkipTextsSegment(4));
//    seq(new EflWalkToSegment(3, 8, false));
//    seqUnbounded(new EflWalkToSegment(3, 8, false));
//    seqUnbounded(new EflWalkToSegment(6, 9)); // enter lab
//    seqUnbounded(new EflWalkToSegment(16, 4)); // room
//    seqUnbounded(new EflWalkToSegment(4, 3)); // room
//    delayEfl(new SeqSegment() {
//      @Override
//      protected void execute() {
//        seqEflButtonUnboundedNoDelay(A);
//        seqMetric(new EflOverworldInteract.OverworldInteractMetric(1));
//      }
//    });
//    seq(new EflSkipTextsSegment(3));
//    seq(new EflTextSegment()); // got Aerodactyl
//    seq(new EflSkipTextsSegment(2)); // no nick
//    seq(new EflSkipTextsSegment(4)); // no room
//    seq(new EflWalkToSegment(3, 8, false));
//    seq(new EflWalkToSegment(3, 8, false));
//
//    save("ci3");
//    load("ci3");
//
//    seqUnbounded(new EflWalkToSegment(11, 11));
//    seqUnbounded(new EflWalkToSegment(13, 5));
//    seq(new EflWalkToSegment(13, 4));
//    {
//      seqMetric(new OutputParty());
//      seqMetric(new OutputItems());
//      seqMetric(new OutputBoxMons());
//      seqEflButton(A); // PC
//      seq(new EflSkipTextsSegment(1)); // turned on
//      seqEflButton(A); // someone's PC
//      seq(new EflSkipTextsSegment(2)); // accessed, mon storage
//      seq(new EflDepositMonSegment(NIDOQUEEN));
//      seqUnbounded(new EflWithdrawMonSegment(KABUTO));
//      seqEflButtonUnboundedNoDelay(B, MENU); // cancel
//      seqEflButtonUnboundedNoDelay(B, MENU); // cancel
//      seqMetric(new OutputBoxMons());
//    }
//    seqUnbounded(new EflWalkToSegment(4, 6));
//    seqUnbounded(new EflWalkToSegment(4, 8, false));
//
//    save("ci4");
//    load("ci4");
//
//    seqUnbounded(new EflUseBikeSegment().fromOverworld());
//    seqUnbounded(new EflWalkToSegment(17, 5)); // enter mansion
//    seqUnbounded(new EflWalkToSegment(6, 3)); // enter mansion
//    save("tmp");
////    load("tmp");
//
//    seqUnbounded(new EflWalkToSegment(5, 26)); // align
//    seq(new EflEncounterSegment(MUK, UP));
////    seq(new EflEncounterSegment(WEEZING, UP));
//    save("tmp2");
////    load("tmp2");
//    seq(new EflCatchMonSegment().withBufferSize(0));
//
//    seqUnbounded(new EflWalkToSegment(5, 23)); // align
//    seq(new EflEncounterSegment(KOFFING, UP));
//    save("tmp3");
////    load("tmp3");
//    seq(new EflCatchMonSegment().withBufferSize(0));
//
//    seqUnbounded(new EflWalkToSegment(5, 20)); // align
//    seq(new EflEncounterSegment(WEEZING, UP));
//    save("tmp4");
////    load("tmp4");
//    seq(new EflCatchMonSegment().withBufferSize(0));
//
//    seqUnbounded(new EflWalkToSegment(5, 16)); // align
//    seqUnbounded(new EflWalkToSegment(7, 16)); // align
//
//    seqEflButtonUnboundedNoDelay(A);
//    seqUnbounded(new EflTextSegment()); // Moon Stone
//
//    seq(new EflEncounterSegment(GRIMER, UP));
//    save("tmp5");
////    load("tmp5");
//    seq(new EflCatchMonSegment().withBufferSize(0));
//
//    seqUnbounded(new EflWalkToSegment(5, 10)); // l2
//
//    save("ci5");
//    load("ci5");
//
//    seqUnbounded(new EflWalkToSegment(8, 1).setBlockAllWarps(true)); // align
//    seq(new EflEncounterSegment(GROWLITHE, LEFT));
//    save("tmp6");
//    load("tmp6");
//    seq(new EflCatchMonSegment().noNew().withBufferSize(0));
//
//    seqUnbounded(new EflWalkToSegment(6, 1)); // l3
////    {
////      seq(new EflWalkToSegment(10, 6)); // button
////      seq(new EflWalkToSegment(10, 5, false)); // button
////      seqEflButton(Move.A);
////      seqUnbounded(new EflSkipTextsSegment(1));
////      seqUnbounded(new EflSkipTextsSegment(1, true)); // press button
////      seqUnbounded(new EflSkipTextsSegment(1));
////    }
//    seqUnbounded(new EflWalkToSegment(7, 2)); // align
//    seqUnbounded(new EflWalkToSegment(8, 2)); // align
////    seqUnbounded(new EflWalkToSegment(13, 10)); // align
////    seqUnbounded(new EflWalkToSegment(14, 10)); // align
//    seq(new EflEncounterSegment(new CheckEncounterMetric(PONYTA, 36).withSpdDV(15), RIGHT)); // .withAtkDV(11, 12, 13, 14, 15) // for -1 turn
//    save("tmp");
//    load("tmp");
//    seq(new EflCatchMonSegment());
//    seq(new EflWalkToSegment(10, 6)); // button
//    seq(new EflWalkToSegment(10, 5, false).setBlockAllWarps(true)); // button
//    seqEflButton(Move.A);
//    seq(new EflSkipTextsSegment(1));
//    seq(new EflSkipTextsSegment(1, true)); // press button
//    seq(new EflSkipTextsSegment(1));
//    seq(new EflWalkToSegment(16, 14)); // l1
//    seq(new EflWalkToSegment(21, 23)); // l0
//    seq(new EflWalkToSegment(18, 26)); // button
//    seq(new EflWalkToSegment(18, 25, false).setBlockAllWarps(true)); // button
//    seqEflButton(Move.A);
//    seq(new EflSkipTextsSegment(1));
//    seq(new EflSkipTextsSegment(1, true)); // press button
//    seq(new EflSkipTextsSegment(1));
////    {
////      seqUnbounded(new EflWalkToSegment(24, 7)); // align
////      seqUnbounded(new EflWalkToSegment(24, 6)); // align
////      seq(new EflEncounterSegment(WEEZING, UP));
////      save("tmp2");
//////      load("tmp2");
////      seq(new EflCatchMonSegment());
////    }
//    seq(new EflWalkToSegment(20, 4)); // button
//    seq(new EflWalkToSegment(20, 3, false).setBlockAllWarps(true)); // button
//    seqEflButton(Move.A);
//    seq(new EflSkipTextsSegment(1));
//    seq(new EflSkipTextsSegment(1, true)); // press button
//    seq(new EflSkipTextsSegment(1));
//    {
//      seq(new EflWalkToSegment(12, 2)); // rare candy
//      seq(new EflWalkToSegment(11, 2)); // rare candy
//      seqMove(new EflOverworldInteract(3)); // rare candy
//      seq(new EflTextSegment()); // rare candy
//    }
//    {
//      seq(new EflWalkToSegment(2, 9)); // rare candy
//      seqEflButton(Move.A);
//      seq(new EflTextSegment()); // rare candy
//    }
//    seq(new EflWalkToSegment(5, 11)); // secret key
//    seq(new EflWalkToSegment(5, 12)); // secret key
//    seqMove(new EflOverworldInteract(8));
//    seq(new EflTextSegment()); // secret key
//    seqEflSkipInput(1);
//    seqMetric(new OutputItems());
//    seq(new EflSelectItemSegment(ESCAPE_ROPE).fromOverworld().andUse());
//    seqEflSkipInput(2);
//
//    save("ci6");
//    load("ci6");
//
//    seqMetric(new OutputParty());
//    seq(new EflSelectItemSegment(HM03).fromOverworld().andUse());
//    seq(new EflLearnTMSegment(KABUTO));
//    seqEflButton(B);
//    seq(new EflSelectMonSegment(KABUTO).fromMainMenu().andSwitchWith(OMANYTE));
//    seqEflSkipInput(1);
//    seq(new EflSelectMonSegment(FARFETCHD).andFlyTo(1)); // Cinnabar
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
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(Metric.TRUE, AGILITY).withSuffix(new SeqSegment() {
//        @Override
//        protected void execute() { // greatly rose
//          seqEflButton(B);
//          seq(new EflTextSegment());
//        }
//      })};
////      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), LEER)};
//      kems.attackCount[2][1] = 1; // surf crit
//      seq(kems); // growlithe
//    }
//    save("tmp2");
//    load("tmp2");
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), TAIL_WHIP, GROWL)};
//      kems.attackCount[2][1] = 1; // surf crit
//      seq(kems); // ponyta
//    }
//    save("tmp3");
//    load("tmp3");
//    seq(EflNewEnemyMonSegment.any()); // next mon
////    seq(new EflSwitchPokemonSegment(TENTACOOL, EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), TAIL_WHIP, GROWL)));
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment(); // TODO: consider blaine super potion
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), TAIL_WHIP, GROWL)};
//      kems.attackCount[2][0] = 1; // surf
//      kems.attackCount[2][1] = 1; // surf crit
//      kems.numExpGainers = 2; // Kabuto, lvlup to 31
//      seq(kems); // rapidash
//    }
//    save("tmp4");
//    load("tmp4");
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckSwitchAndTeleportEffectUsed(), ROAR)};
//      kems.attackCount[2][1] = 2; // surf crit
//      kems.numExpGainers = 2; // Kabuto, lvlup to 32
//      seq(kems); // arcanine
//    }
//    seq(new EflEndFightSegment(2)); // player defeated enemy
//
//    save("ci7");
    load("ci7");

    seq(new EflSkipTextsSegment(6)); // after battle texts (no room)
//    seq(new EflSkipTextsSegment(6+4)); // after battle texts (with room)

    seq(new EflSelectItemSegment(LEAF_STONE).fromOverworld().andUse());
    seq(new EflSelectMonSegment(EXEGGCUTE));
    seq(new EflEvolutionSegment()); // EXEGGUTOR
    seqEflSkipInput(0);
    seq(new EflSelectItemSegment(MOON_STONE).andUse());
    seq(new EflSelectMonSegment(NIDORINO));
    seq(new EflEvolutionSegment()); // Nidoking
    seqEflSkipInput(0);
    seq(new EflSelectItemSegment(ESCAPE_ROPE).andUse());
    seqEflSkipInput(2);
  }
}
