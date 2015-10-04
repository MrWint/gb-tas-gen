package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import static mrwint.gbtasgen.move.Move.A;
import static mrwint.gbtasgen.move.Move.B;
import static mrwint.gbtasgen.move.Move.DOWN;
import static mrwint.gbtasgen.move.Move.LEFT;
import static mrwint.gbtasgen.move.Move.RIGHT;
import static mrwint.gbtasgen.move.Move.SELECT;
import static mrwint.gbtasgen.move.Move.START;
import static mrwint.gbtasgen.move.Move.UP;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.MENU;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.PRESSED;
import mrwint.gbtasgen.metric.pokemon.CheckEncounterMetric;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckSwitchAndTeleportEffectUsed;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputBoxMons;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputItems;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputParty;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.pokemon.gen1.EflOverworldInteract;
import mrwint.gbtasgen.move.pokemon.gen1.EflWalkStep;
import mrwint.gbtasgen.segment.pokemon.EflCatchMonSegment;
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
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflBuyItemSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflDepositMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflEncounterSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflUseBikeSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflWithdrawMonSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;

public class CinnabarBlue extends SeqSegment {

	@Override
	public void execute() {

//    {
//      seqEflButton(START, PRESSED);
//      seqEflButton(A, PRESSED); // item
//      seqMetric(new OutputItems());
//      seqEflScrollFastAF(15+1); // HM03
//      seqEflButton(A, PRESSED); // use
//      seq(new EflLearnTMSegment(-3, 2)); // Dragonair Thunder Wave -> Surf
//      seqEflScrollFastA(-4); // TM48
//      seqEflButton(A, PRESSED); // use
//      seq(new EflLearnTMSegment(-1)); // Mankey Rock Slide
//      seqEflScrollFastAF(6+1); // TM28
//      seqEflButton(A, PRESSED); // use
//      seq(new EflLearnTMSegment(0, 0)); // Mankey Scratch -> Dig
//      seqEflButton(B); // cancel
//      seqEflScrollA(-1); // mon
//      seqEflScrollAF(2); // Pidgey
//      seqEflButton(A, PRESSED); // Fly
//      seqEflButton(A, PRESSED); // Pallet Town
//      seqEflSkipInput(1);
//    }
//
//    save("ci1");
//    load("ci1");
//
//    seqUnbounded(new EflWalkToSegment(7, 12)); // water
//    seqUnbounded(new EflWalkToSegment(7, 13)); // water
//    seqUnbounded(new SeqSegment() {
//      @Override
//      protected void execute() {
//        seqEflButton(START, PRESSED);
//        seqEflButton(A, PRESSED); // mon
//        seqEflSkipInput(1);
//        seqEflScrollAF(-1); // Dragonair
//        seqEflButton(A, PRESSED); // surf
//        seqUnbounded(new EflTextSegment());
//        seqEflSkipInput(50); // extra skips
//        seqEflButtonUnbounded(B);
////        seqUnbounded(new EflSkipTextsSegment(1)); // got on
//      }
//    });
//
//    for(int i=0;i<5;i++)
//      seqMoveUnbounded(new EflWalkStep(Move.DOWN, true));
//    seqMoveUnbounded(new EflWalkStep(Move.RIGHT, true));
//
//    seqUnbounded(new EflWalkToSegment(8, 3));
//    seq(new EflEncounterSegment(new CheckEncounterMetric(0x96, 32).withAtkDV(11,12,13,14,15).withSpdDV(8,9,10,11,12,13,14,15), DOWN)); // Pidgeotto TODO: DV15 for Vileplume
//    save("tmp");
////    load("tmp");
//    seq(new EflCatchMonSegment(2).withBufferSize(0));
//
//    seqUnbounded(new EflWalkToSegment(8, 6));
//    seq(new EflEncounterSegment(0xA6, DOWN)); // Raticate
//    save("tmp2");
////    load("tmp2");
//    seq(new EflCatchMonSegment(2).withBufferSize(0));
//
//    seqUnbounded(new EflWalkToSegment(9, 7));
//    seqUnbounded(new EflWalkToSegment(9, 8));
//    seq(new EflEncounterSegment(0x1E, DOWN)); // Tangela
//    seq(new EflCatchMonSegment(2));
//
//    save("ci2");
//    load("ci2");
//
//    seqUnbounded(new SeqSegment() {
//      @Override
//      protected void execute() {
//        seqEflButton(START, PRESSED);
//        seqEflScrollA(1); // mon
//        seqEflScrollAF(3); // Dragonair
//        seqEflButton(A, PRESSED); // surf
//        seqUnbounded(new EflSkipTextsSegment(1)); // got on
//      }
//    });
//    for(int i=0;i<10;i++)
//      seqMoveUnbounded(new EflWalkStep(DOWN, true));
//    for(int i=0;i<6;i++)
//      seqMoveUnbounded(new EflWalkStep(LEFT, true));
//    for(int i=0;i<19;i++)
//      seqMoveUnbounded(new EflWalkStep(DOWN, true));
//    seq(new EflEncounterSegment(new CheckEncounterMetric(0x18, 30).withSpcDV(14, 15), DOWN)); // Tentacool
//    seq(new EflCatchMonSegment(2));
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
//    seq(new EflSkipTextsSegment(3));
//    seq(new EflTextSegment()); // got Omanyte
//    seq(new EflSkipTextsSegment(2)); // no nick
//    seq(new EflSkipTextsSegment(4)); // no room
//    seq(new EflWalkToSegment(3, 8, false));
//    seq(new EflWalkToSegment(3, 8, false));
//
//    save("ci4");
//    load("ci4");
//
//    seqUnbounded(new EflWalkToSegment(11, 11));
//    seqUnbounded(new EflWalkToSegment(13, 5));
//    seq(new EflWalkToSegment(13, 4));
//    {
//      seqEflButton(A); // PC
//      seq(new EflSkipTextsSegment(1)); // turned on
//      seqEflButton(A); // someone's PC
//      seq(new EflSkipTextsSegment(2)); // accessed, mon storage
//      {
//        seqMetric(new OutputBoxMons());
//        seqEflScrollAF(2); // release
//        seqEflSkipInput(1);
//        seqEflScrollFastAF(2); // Tangela
//        seq(new EflSkipTextsSegment(1)); // release
//        seq(new EflSkipTextsSegment(1, true)); // release
//        seq(new EflSkipTextsSegment(2)); // release
//      }
//      seq(new EflDepositMonSegment(-1, 1)); // Growlithe
//      seq(new EflWithdrawMonSegment(-1, 1)); // Tentacool
//      seq(new EflDepositMonSegment(1, 3+2)); // Pidgey
//      seq(new EflWithdrawMonSegment(-1, 2)); // Pidgeotto
//      seqEflButton(DOWN, MENU);
//      seqEflSkipInput(1);
//      seqEflButton(DOWN);
//      seqEflSkipInput(1);
//      seqEflButton(DOWN | A); // change box
//      seq(new EflSkipTextsSegment(2)); // save game
//      seq(new EflSkipTextsSegment(1, true)); // yes
//      seqEflButtonUnboundedNoDelay(DOWN | A, MENU); // change box to 2
//      seqEflButtonUnboundedNoDelay(B, MENU); // cancel
//      seqEflButtonUnboundedNoDelay(B, MENU); // cancel
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
//      seq(new EflTextSegment(Move.B));
//      seq(new EflBuyItemSegment(4, 6, true)); // Escape Rope x6
//      seqEflButton(Move.B); // cancel
//      seq(new EflSkipTextsSegment(2)); // cancel + bye
//    }
//    seq(new EflWalkToSegment(3, 6)); // leave mart
//    seq(new EflWalkToSegment(3, 8, false)); // leave mart
//
//    seqUnbounded(new EflUseBikeSegment(2, 0));
//    seqUnbounded(new EflWalkToSegment(17, 5)); // enter mansion
//    seqUnbounded(new EflWalkToSegment(6, 3)); // enter mansion
//
//    seqUnbounded(new EflWalkToSegment(5, 26)); // align
//    seq(new EflEncounterSegment(0x52, UP)); // Vulpix
//    save("tmp");
//    load("tmp");
//    seq(new EflCatchMonSegment(2).noNew().withBufferSize(0).withExtraSkips(10));
//
//    seqUnbounded(new EflWalkToSegment(5, 21)); // align
//    seq(new EflEncounterSegment(0x8F, UP)); // Weezing
//    save("tmp2");
//    load("tmp2");
//    seq(new EflCatchMonSegment(2).withBufferSize(0).withExtraSkips(100));
//
//    seqUnbounded(new EflWalkToSegment(5, 18)); // align
//    seq(new EflEncounterSegment(0x37, UP)); // Koffing
//    save("tmp3");
////    load("tmp3");
//    seq(new EflCatchMonSegment(2).withBufferSize(0));
//
//    seqUnbounded(new EflWalkToSegment(5, 16)); // align
//    seqUnbounded(new EflWalkToSegment(6, 16)); // align
//    seq(new EflEncounterSegment(0x0D, RIGHT)); // Grimer
//    save("tmp4");
////    load("tmp4");
//    seq(new EflCatchMonSegment(2).withBufferSize(0));
//
//    seqEflButtonUnboundedNoDelay(A);
//    seqUnbounded(new EflTextSegment()); // Moon Stone
//
//    seqUnbounded(new EflWalkToSegment(7, 10)); // align
//    seq(new EflEncounterSegment(0x88, LEFT)); // Muk
//    seq(new EflCatchMonSegment(2));
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
//      load("tmp");
//      seq(new EflCatchMonSegment(2));
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
//      seq(new EflCatchMonSegment(2));
//    }
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
//    {
//      seqEflSkipInput(1);
//      seqEflButton(START, PRESSED);
//      seqEflScrollA(2); // items
//      seqMetric(new OutputItems());
//      seqMetric(new OutputParty());
//      seqEflSkipInput(1);
//      seqEflScrollFast(4); // nugget
//      seqEflButton(SELECT);
//      seqEflScrollFast(9); // escape rope
//      seqEflButton(SELECT);
//      seqEflScrollFastAF(-6-1); // HM02
//      seqEflButton(A, PRESSED); // use
////      seq(new EflLearnTMSegment(-1, 0)); // Pidgeotto Sand-Attack -> Fly
//      {
//        seq(new EflSkipTextsSegment(2)); // booted up TM, contains xyz
//        seq(new EflSkipTextsSegment(1, true)); // learn
//        seqEflSkipInput(2);
//        seqEflScrollAF(-1); // select mon
//        seq(new EflOverrideMoveSegment(0)); // Pidgeotto Sand-Attack -> Fly
//      }
//      seqEflScrollFastAF(-3-1); // escape rope
//      seqEflButton(A, PRESSED); // use
//      seqEflSkipInput(2);
//    }
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
      seq(new EflDepositMonSegment(1, 1)); // Mankey
      seq(new EflDepositMonSegment(0, 1)); // Dragonair
      seq(new EflDepositMonSegment(0, 1)); // Ekans
      seq(new EflDepositMonSegment(0, 1)); // Tentacool
      seq(new EflDepositMonSegment(0, 1)); // Pidgeotto
      seq(new EflWithdrawMonSegment(-1, 1)); // Magmar
      seqEflButton(DOWN, MENU);
      seqEflSkipInput(1);
      seqEflButton(DOWN);
      seqEflSkipInput(1);
      seqEflButton(DOWN | A); // change box
      seq(new EflSkipTextsSegment(2)); // save game
      seq(new EflSkipTextsSegment(1, true)); // yes
      seqEflButtonUnboundedNoDelay(UP | A, MENU); // change box to 2
      seqEflButton(UP, MENU);
      seqEflSkipInput(1);
      seqMetric(new OutputBoxMons());
      seq(new EflWithdrawMonSegment(-2, 0)); // Omanyte
      seq(new EflWithdrawMonSegment(0, 1)); // Nidorino
      seq(new EflWithdrawMonSegment(0, 4+1)); // Nidorina
      seq(new EflWithdrawMonSegment(0, 13+1)); // Pinsir
      seqEflButtonUnboundedNoDelay(B, MENU); // cancel
      seqEflButtonUnboundedNoDelay(B, MENU); // cancel
      seqMetric(new OutputParty());
      seqMetric(new OutputBoxMons());
    }

    seq(new EflWalkToSegment(11, 4)); // cable club
    seq(new EflWalkToSegment(11, 3)); // cable club

  }
}
