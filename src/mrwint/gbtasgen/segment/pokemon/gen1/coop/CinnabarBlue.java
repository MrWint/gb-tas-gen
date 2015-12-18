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
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.BULBASAUR;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DRAGONAIR;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.EEVEE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.EKANS;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.ELIXER;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.ESCAPE_ROPE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.GRIMER;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.GROWLITHE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.HM02;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.HM03;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.IVYSAUR;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.KOFFING;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.MAGMAR;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.MANKEY;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.MUK;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.NIDORINA;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.NIDORINO;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.OMANYTE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PIDGEOTTO;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PIDGEY;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PINSIR;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.RATICATE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TANGELA;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TENTACOOL;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.THUNDER_STONE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TM28;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TM48;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.VULPIX;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.WEEZING;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.MENU;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.PRESSED;
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

public class CinnabarBlue extends SeqSegment {

	@Override
	public void execute() {

//	  seqMetric(new OutputItems());
//    seq(new EflSelectItemSegment(ELIXER).fromOverworld().andUse());
//    seq(new EflSelectMonSegment(DRAGONAIR));
//    seq(new EflSkipTextsSegment(1, true)); // PP restored
//    seq(new EflSelectItemSegment(HM03).andUse());
//    seq(new EflLearnTMSegment(DRAGONAIR, 2)); // Dragonair Thunder Wave -> Surf
//    seq(new EflSelectItemSegment(TM48).andUse());
//    seq(new EflLearnTMSegment(MANKEY)); // Rock Slide
//    seqEflSkipInput(0);
//    seq(new EflSelectItemSegment(TM28).andUse());
//    seq(new EflLearnTMSegment(MANKEY, 0)); // Mankey Scratch -> Dig
//    seqEflButton(B); // cancel
//    seq(new EflSelectMonSegment(PIDGEY).fromMainMenu().andFlyTo(0)); // Pallet
//    seqEflSkipInput(1);
//
////    {
////      seq(new EflUseBikeSegment().fromOverworld());
////      seq(new EflWalkToSegment(27, 4)); // bush
////      seq(new EflSelectMonSegment(IVYSAUR).fromOverworld().andCut());
////      seq(new EflWalkToSegment(19, 5)); // enter
////      seq(new EflWalkToSegment(15, 4)); // amber
////      seq(new EflWalkToSegment(15, 3)); // amber
////      seqMove(new EflOverworldInteract(3)); // talk
////      seq(new EflSkipTextsSegment(11)); // get amber
////      seq(new EflWalkToSegment(16, 6)); // leave
////      seq(new EflWalkToSegment(16, 8, false)); // leave
////      seq(new EflSelectMonSegment(PIDGEY).fromOverworld().andFlyTo(0)); // Pallet
////      seqEflSkipInput(1);
////    }
//
//    save("ci1");
//    load("ci1");
//
//    seqUnbounded(new EflWalkToSegment(7, 12)); // water
//    seqUnbounded(new EflWalkToSegment(7, 13)); // water
//    seqUnbounded(new EflSelectMonSegment(DRAGONAIR).fromOverworld().andSurf());
////    seqEflSkipInput(50); // extra skips
//
//    for(int i=0;i<5;i++)
//      seqMoveUnbounded(new EflWalkStep(Move.DOWN, true));
//    seqMoveUnbounded(new EflWalkStep(Move.RIGHT, true));
//
//    seqUnbounded(new EflWalkToSegment(8, 3));
//    seq(new EflEncounterSegment(new CheckEncounterMetric(PIDGEOTTO, 32).withAtkDV(11,12,13,14,15).withSpdDV(8,9,10,11,12,13,14,15), DOWN)); // Pidgeotto TODO: DV15 for Vileplume
//    save("tmp");
////    load("tmp");
//    seq(new EflCatchMonSegment().withBufferSize(0));
//
//    seqUnbounded(new EflWalkToSegment(8, 6));
//    seq(new EflEncounterSegment(RATICATE, DOWN));
//    save("tmp2");
////    load("tmp2");
//    seq(new EflCatchMonSegment().withBufferSize(0));
//
//    seqUnbounded(new EflWalkToSegment(9, 7));
//    seqUnbounded(new EflWalkToSegment(9, 8));
//    seq(new EflEncounterSegment(TANGELA, DOWN));
//    seq(new EflCatchMonSegment());
//
//    save("ci2a");
//    load("ci2a");
//
//    seqMetric(new OutputBoxMons());
//
//    seqUnbounded(new EflSelectMonSegment(DRAGONAIR).fromOverworld().andSurf());
//    for(int i=0;i<10;i++)
//      seqMoveUnbounded(new EflWalkStep(DOWN, true));
//    for(int i=0;i<6;i++)
//      seqMoveUnbounded(new EflWalkStep(LEFT, true));
//    for(int i=0;i<19;i++)
//      seqMoveUnbounded(new EflWalkStep(DOWN, true));
//    seq(new EflEncounterSegment(new CheckEncounterMetric(TENTACOOL, 30).withSpcDV(5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15), DOWN));
////    seq(new EflEncounterSegment(new CheckEncounterMetric(0x18, 30).withSpcDV(14, 15), DOWN)); // Tentacool
//    seq(new EflCatchMonSegment());
//    for(int i=0;i<60;i++)
//      seqMove(new EflWalkStep(DOWN, true));
//    seqMove(new EflWalkStep(RIGHT, true));
//
//    save("ci3");
//    load("ci3");
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
//    seqEflButton(Move.A); // Helix Fossil
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
//        seqMetric(new Gen1CheckDVMetric(0, 0, 12, 15, 15));
////        seq(new CheckMetricSegment(new Gen1CheckDVMetric(15, 0, 15, 15, 15)));
//      }
//    });
//    seq(new EflTextSegment()); // got Omanyte
//    seq(new EflSkipTextsSegment(2)); // no nick
////    seq(new EflSkipTextsSegment(3)); // no room
////    seqUnbounded(new EflSkipTextsSegment(1)); // no room
////    delayEfl(new SeqSegment() {
////      @Override
////      protected void execute() {
////        seqEflButtonUnboundedNoDelay(A);
////        seqMetric(new EflOverworldInteract.OverworldInteractMetric(1));
////      }
////    });
////    seq(new EflSkipTextsSegment(4));
////    seqEflButton(Move.A); // Old Amber
////    seq(new EflSkipTextsSegment(6));
////    seq(new EflSkipTextsSegment(1, true)); // ress
////    seq(new EflSkipTextsSegment(4));
////    seq(new EflWalkToSegment(3, 8, false));
////    seqUnbounded(new EflWalkToSegment(3, 8, false));
////    seqUnbounded(new EflWalkToSegment(6, 9)); // enter lab
////    seqUnbounded(new EflWalkToSegment(16, 4)); // room
////    seqUnbounded(new EflWalkToSegment(4, 3)); // room
////    delayEfl(new SeqSegment() {
////      @Override
////      protected void execute() {
////        seqEflButtonUnboundedNoDelay(A);
////        seqMetric(new EflOverworldInteract.OverworldInteractMetric(1));
////      }
////    });
////    seq(new EflSkipTextsSegment(3));
////    seq(new EflTextSegment()); // got Aerodactyl
////    seq(new EflSkipTextsSegment(2)); // no nick
//    seq(new EflSkipTextsSegment(4)); // no room
//    seq(new EflWalkToSegment(3, 8, false));
//    seq(new EflWalkToSegment(3, 8, false));
//
//    save("ci4");
//    load("ci4");
//
//    seqMetric(new OutputBoxMons());
//    seqMetric(new OutputParty());
//
//    seqUnbounded(new EflWalkToSegment(11, 11));
//    seqUnbounded(new EflWalkToSegment(13, 5));
//    seq(new EflWalkToSegment(13, 4));
//    {
//      seqEflButton(A); // PC
//      seq(new EflSkipTextsSegment(1)); // turned on
//      seqEflButton(A); // someone's PC
//      seq(new EflSkipTextsSegment(2)); // accessed, mon storage
////      seq(new EflReleaseMonSegment(TANGELA));
////      seq(new EflDepositMonSegment(GROWLITHE));
////      seq(new EflWithdrawMonSegment(TENTACOOL));
////      seq(new EflDepositMonSegment(PIDGEY));
////      seq(new EflWithdrawMonSegment(PIDGEOTTO));
////      seqMetric(new OutputBoxMons());
//      seq(new EflChangeMonBoxSegment(2)); // Box 3
//      seqEflButton(B, MENU); // cancel
//      seqEflButton(B, MENU); // cancel
//      seqMetric(new OutputParty());
//      seqMetric(new OutputBoxMons());
//    }
//    seq(new EflWalkToSegment(4, 6));
//    seq(new EflWalkToSegment(4, 8, false));
//
//    save("ci5");
//    load("ci5");
//
//    seqUnbounded(new EflWalkToSegment(15, 11)); // enter mart
//    seqUnbounded(new EflWalkToSegment(3, 5)); // shopkeep
//    seqUnbounded(new EflWalkToSegment(2, 5)); // shopkeep
//    seqMove(new EflOverworldInteract(1));
//    {
//      seq(new EflSkipTextsSegment(1, true)); // buy
//      seq(new EflTextSegment(B));
//      seq(new EflBuyItemSegment(4, 6, true)); // Escape Rope x6
//      seqEflButton(B); // cancel
//      seq(new EflSkipTextsSegment(2)); // cancel + bye
//    }
//    seq(new EflWalkToSegment(3, 6)); // leave mart
//    seq(new EflWalkToSegment(3, 8, false)); // leave mart
//
//    seqUnbounded(new EflUseBikeSegment().fromOverworld());
//    seqUnbounded(new EflWalkToSegment(17, 5)); // enter mansion
//    seqUnbounded(new EflWalkToSegment(6, 3)); // enter mansion
//
//    seqUnbounded(new EflWalkToSegment(5, 26)); // align
//    seq(new EflEncounterSegment(VULPIX, UP));
//    save("tmp");
//    load("tmp");
//    seq(new EflCatchMonSegment().noNew().withBufferSize(0).withExtraSkips(20));
//
//    seqUnbounded(new EflWalkToSegment(5, 21)); // align
//    seq(new EflEncounterSegment(WEEZING, UP));
//    save("tmp2");
////    load("tmp2");
//    seq(new EflCatchMonSegment().withBufferSize(0).withExtraSkips(160));
//
//    seqUnbounded(new EflWalkToSegment(5, 18)); // align
//    seq(new EflEncounterSegment(KOFFING, UP));
//    save("tmp3");
////    load("tmp3");
//    seq(new EflCatchMonSegment().withBufferSize(0));
//
//    seqUnbounded(new EflWalkToSegment(5, 16)); // align
//    seqUnbounded(new EflWalkToSegment(6, 16)); // align
//    seq(new EflEncounterSegment(GRIMER, RIGHT));
//    save("tmp4");
////    load("tmp4");
//    seq(new EflCatchMonSegment().withBufferSize(0));
//
//    seqEflButtonUnboundedNoDelay(A);
//    seqUnbounded(new EflTextSegment()); // Moon Stone
//
//    seqUnbounded(new EflWalkToSegment(7, 10)); // align
//    seq(new EflEncounterSegment(MUK, LEFT));
//    seq(new EflCatchMonSegment());
//
//    seq(new EflWalkToSegment(5, 10)); // l2
//
//    save("ci6");
//    load("ci6");
//
//    seq(new EflWalkToSegment(6, 1, false).setBlockAllWarps(true)); // l3
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
//    {
//      seqUnbounded(new EflWalkToSegment(26, 11)); // align
//      seq(new EflEncounterSegment(0x33, UP)); // Magmar
//      save("tmp");
////      load("tmp");
//      seq(new EflCatchMonSegment());
//    }
//    save("tmp2");
//    load("tmp2");
//    seq(new EflWalkToSegment(20, 4)); // button
//    seq(new EflWalkToSegment(20, 3, false).setBlockAllWarps(true)); // button
//    seqEflButton(Move.A);
//    seqUnbounded(new EflSkipTextsSegment(1));
//    seqUnbounded(new EflSkipTextsSegment(1, true)); // press button
//    seqUnbounded(new EflSkipTextsSegment(1));
//    {
//      seqUnbounded(new EflWalkToSegment(13, 6)); // align
//      seq(new EflEncounterSegment(0xA3, LEFT)); // Ponyta
//      seq(new EflCatchMonSegment());
//    }
//    save("tmp3");
//    load("tmp3");
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
//    seq(new EflTextSegment());
//    // maybe switch nugget <-> escape ropes
//    seqEflSkipInput(1);
////    seq(new EflSelectItemSegment(HM02).fromOverworld().andUse());
////    seq(new EflLearnTMSegment(PIDGEOTTO, 0)); // Pidgeotto Sand-Attack -> Fly
//    seq(new EflSelectItemSegment(ESCAPE_ROPE).fromOverworld().andUse());
//    seqEflSkipInput(2);
//
//    save("ci7");
    load("ci7");

    seq(new EflWalkToSegment(19, 17));
    seq(new EflWalkToSegment(13, 4));
    seq(new EflWalkToSegment(13, 3, false)); // TODO: optimize
    {
      seqEflButton(A); // PC
      seq(new EflSkipTextsSegment(1)); // turned on
      seqEflButton(A); // someone's PC
      seq(new EflSkipTextsSegment(2)); // accessed, mon storage
      seqMetric(new OutputParty());
      seqMetric(new OutputBoxMons());
      seq(new EflDepositMonSegment(IVYSAUR));
      seq(new EflDepositMonSegment(DRAGONAIR));
      seq(new EflDepositMonSegment(PIDGEY));
      seq(new EflDepositMonSegment(MANKEY));
      seq(new EflWithdrawMonSegment(MAGMAR));
      seq(new EflChangeMonBoxSegment(1)); // change box to 2
      seqMetric(new OutputBoxMons());
      seq(new EflWithdrawMonSegment(OMANYTE));
      seq(new EflWithdrawMonSegment(NIDORINA));
      seq(new EflChangeMonBoxSegment(0)); // change box to 1
      seqMetric(new OutputBoxMons());
      seq(new EflWithdrawMonSegment(EEVEE));
      seqEflButtonUnboundedNoDelay(B, MENU); // cancel
      seqEflButtonUnboundedNoDelay(B, MENU); // cancel
      seqMetric(new OutputParty());
      seqMetric(new OutputBoxMons());
    }
    seqEflSkipInput(1);
    seq(new EflSelectItemSegment(THUNDER_STONE).fromOverworld().andUse());
    seq(new EflSelectMonSegment(EEVEE));
    seq(new EflEvolutionSegment()); // Jolteon
    seq(new EflSelectItemSegment(HM03).andUse());
    seq(new EflLearnTMSegment(OMANYTE));
    seqEflButton(B);
    seqEflButton(START);
    seq(new EflWalkToSegment(11, 4)); // cable club
    seq(new EflWalkToSegment(11, 3)); // cable club
  }
}
