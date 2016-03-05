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
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PONYTA;
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
import mrwint.gbtasgen.segment.pokemon.gen1.common.NamingSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;

public class CinnabarBlue extends SeqSegment {

	@Override
	public void execute() {

//    seqMetric(new OutputParty());
//    seqMetric(new OutputItems());
//    seq(new EflSelectItemSegment(ELIXER).fromOverworld().andUse());
//    seq(new EflSelectMonSegment(DRAGONAIR));
//    seq(new EflSkipTextsSegment(1, true)); // PP restored
//    seq(new EflSelectItemSegment(HM03).andUse());
//    seq(new EflLearnTMSegment(DRAGONAIR, 3)); // Dragonair Take Down -> Surf
//    seq(new EflSelectItemSegment(TM48).andUse());
//    seq(new EflLearnTMSegment(MANKEY)); // Rock Slide
//    seqEflSkipInput(0);
//    seq(new EflSelectItemSegment(TM28).andUse());
//    seq(new EflLearnTMSegment(MANKEY, 0)); // Mankey Scratch -> Dig
//    seqEflButton(B); // cancel
//    seq(new EflSelectMonSegment(PIDGEY).fromMainMenu().andFlyTo(0)); // Pallet
//    seqEflSkipInput(1);
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
//    load("tmp");
//    seq(new EflCatchMonSegment().withBufferSize(0).withName("P"));
//
//    seqUnbounded(new EflWalkToSegment(8, 6));
//    seq(new EflEncounterSegment(RATICATE, DOWN));
//    save("tmp2");
//    load("tmp2");
//    seq(new EflCatchMonSegment().withBufferSize(0));
//
//    seqUnbounded(new EflWalkToSegment(8, 6));
//    seqUnbounded(new EflWalkToSegment(8, 8));
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
//    for(int i=0;i<5;i++)
//      seqMoveUnbounded(new EflWalkStep(LEFT, true));
//    for(int i=0;i<22;i++)
//      seqMoveUnbounded(new EflWalkStep(DOWN, true));
//    seq(new EflEncounterSegment(new CheckEncounterMetric(TENTACOOL, 30).withSpcDV(5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15), DOWN));
////    seq(new EflEncounterSegment(new CheckEncounterMetric(0x18, 30).withSpcDV(14, 15), DOWN)); // Tentacool
//    seq(new EflCatchMonSegment());
//    for(int i=0;i<57;i++)
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
////        seqMetric(new Gen1CheckDVMetric(0, 0, 12, 15, 15));
//        seqMetric(new Gen1CheckDVMetric(0, 0, 15, 15, 15));
//      }
//    });
//    seq(new EflTextSegment()); // got Omanyte
//    save("tmp");
//    load("tmp");
//    seq(new EflSkipTextsSegment(1)); // nick
//    seq(new EflSkipTextsSegment(1, true)); // nick
//    seq(new NamingSegment("O"));
//    seqEflButton(START);
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
//      seq(new EflBuyItemSegment(4, 5, true)); // Escape Rope x5
//      seqEflButton(B); // cancel
//      seqUnbounded(new EflSkipTextsSegment(2)); // cancel + bye
//    }
//    seqUnbounded(new EflWalkToSegment(3, 6)); // leave mart
//    seqUnbounded(new EflWalkToSegment(3, 8, false)); // leave mart
//    
//    seqMetric(new OutputItems());
//
//    seqUnbounded(new EflUseBikeSegment().fromOverworld());
//    seqUnbounded(new EflWalkToSegment(17, 5)); // enter mansion
//    seqUnbounded(new EflWalkToSegment(6, 3)); // enter mansion
//
//    save("tmp");
//    load("tmp");
//
//    seqUnbounded(new EflWalkToSegment(5, 26)); // align
//    seq(new EflEncounterSegment(GRIMER, UP));
//    save("tmp1");
//    load("tmp1");
//    seq(new EflCatchMonSegment().withBufferSize(0));
//
//    seqUnbounded(new EflWalkToSegment(5, 23)); // align
//    seq(new EflEncounterSegment(PONYTA, UP));
//    save("tmp2");
//    load("tmp2");
//    seq(new EflCatchMonSegment().withBufferSize(0));
//
//    seqUnbounded(new EflWalkToSegment(6, 16)); // align
//    seqUnbounded(new EflWalkToSegment(7, 16)); // align
//
//    seqEflButtonUnboundedNoDelay(A);
//    seqUnbounded(new EflTextSegment()); // Moon Stone
//
//    seqUnbounded(new EflWalkToSegment(5, 10)); // l2
//
//    seq(new EflEncounterSegment(MUK, RIGHT));
//    save("tmp3");
//    load("tmp3");
//    seq(new EflCatchMonSegment().withBufferSize(0));
//
//    seqUnbounded(new EflWalkToSegment(10, 5).setBlockAllWarps(true)); // align
//    seqUnbounded(new EflEncounterSegment(VULPIX, LEFT));
//    save("tmp4");
//    load("tmp4");
//    seq(new EflCatchMonSegment().withBufferSize(0).noNew());
//
//    seqUnbounded(new EflWalkToSegment(6, 1)); // l3
//    seqUnbounded(new EflWalkToSegment(7, 2)); // align
//    seqUnbounded(new EflEncounterSegment(KOFFING, RIGHT));
//    save("tmp5");
//    load("tmp5");
//    seq(new EflCatchMonSegment().withBufferSize(0));
//
//    seqUnbounded(new EflWalkToSegment(10, 6)); // button
//    seqUnbounded(new EflWalkToSegment(10, 5, false).setBlockAllWarps(true)); // button
//    seqEflButton(Move.A);
//    seqUnbounded(new EflSkipTextsSegment(1));
//    seqUnbounded(new EflSkipTextsSegment(1, true)); // press button
//    seqUnbounded(new EflSkipTextsSegment(1));
//    seqUnbounded(new EflWalkToSegment(11, 6)); // align
//    seqUnbounded(new EflEncounterSegment(MAGMAR, RIGHT));
//    save("tmp6");
//    load("tmp6");
//    seq(new EflCatchMonSegment().withBufferSize(0));
//
//    seqUnbounded(new EflWalkToSegment(15, 11)); // align
//    seqUnbounded(new EflEncounterSegment(WEEZING, RIGHT));
//    save("tmp7");
//    load("tmp7");
//    seq(new EflCatchMonSegment());
//
//    seq(new EflWalkToSegment(16, 14)); // l1
//    seq(new EflWalkToSegment(21, 23)); // l0
//    seq(new EflWalkToSegment(18, 26)); // button
//    seq(new EflWalkToSegment(18, 25, false).setBlockAllWarps(true)); // button
//    seqEflButton(Move.A);
//    seq(new EflSkipTextsSegment(1));
//    seq(new EflSkipTextsSegment(1, true)); // press button
//    seq(new EflSkipTextsSegment(1));
////    seq(new EflWalkToSegment(12, 15)); // align
////    seq(new EflEncounterSegment(PONYTA, RIGHT));
////    seq(new EflCatchMonSegment());
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

    seq(new EflSelectMonSegment(PIDGEY).fromOverworld().andFlyTo(1)); // Cinnabar
    seqEflSkipInput(1);

    seqUnbounded(new EflWalkToSegment(11, 11));
//    seqUnbounded(new EflWalkToSegment(19, 17));
    seqUnbounded(new EflWalkToSegment(13, 5));
    seq(new EflWalkToSegment(13, 4));
    {
      seqEflButton(A); // PC
      seq(new EflSkipTextsSegment(1)); // turned on
      seqEflButton(A); // someone's PC
      seq(new EflSkipTextsSegment(2)); // accessed, mon storage
      seqMetric(new OutputParty());
      seqMetric(new OutputBoxMons());
      seq(new EflDepositMonSegment(DRAGONAIR));
      seq(new EflDepositMonSegment(BULBASAUR));
      seq(new EflDepositMonSegment(PIDGEY));
      seq(new EflDepositMonSegment(MANKEY));
      seq(new EflWithdrawMonSegment(MAGMAR));
      seq(new EflChangeMonBoxSegment(1)); // change box to 2
      seqMetric(new OutputBoxMons());
      seq(new EflWithdrawMonSegment(OMANYTE));
      seq(new EflWithdrawMonSegment(PINSIR));
      seqEflButtonUnboundedNoDelay(B, MENU); // cancel
      seqEflButtonUnboundedNoDelay(B, MENU); // cancel
      seqMetric(new OutputParty());
      seqMetric(new OutputBoxMons());
    }
    seqEflSkipInput(1);
//    seq(new EflSelectItemSegment(THUNDER_STONE).fromOverworld().andUse());
//    seq(new EflSelectMonSegment(EEVEE));
//    seq(new EflEvolutionSegment()); // Jolteon
    seq(new EflSelectItemSegment(HM03).andUse().fromOverworld().andUse());
    seq(new EflLearnTMSegment(OMANYTE));
    seqEflButton(B);
    seqEflButton(START);
    seq(new EflWalkToSegment(11, 4)); // cable club
    seq(new EflWalkToSegment(11, 3)); // cable club
  }
}
