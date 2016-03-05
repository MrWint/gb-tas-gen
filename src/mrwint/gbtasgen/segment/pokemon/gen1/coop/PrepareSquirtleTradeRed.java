package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import static mrwint.gbtasgen.move.Move.A;
import static mrwint.gbtasgen.move.Move.B;
import static mrwint.gbtasgen.move.Move.DOWN;
import static mrwint.gbtasgen.move.Move.START;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.CATERPIE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.CHARMELEON;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.CLEFAIRY;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DUGTRIO;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.EEVEE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.FARFETCHD;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.GROWLITHE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.MANKEY;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.MEOWTH;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.ODDISH;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.MENU;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.PRESSED;
import mrwint.gbtasgen.metric.pokemon.CheckEncounterMetric;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckDisableEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.Gen1CheckDVMetric;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputBoxMons;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputItems;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputParty;
import mrwint.gbtasgen.metric.pokemon.gen1.slots.SlotsWheel1Metric;
import mrwint.gbtasgen.metric.pokemon.gen1.slots.SlotsWheel2Metric;
import mrwint.gbtasgen.metric.pokemon.gen1.slots.SlotsWheel3Metric;
import mrwint.gbtasgen.metric.pokemon.gen1.slots.SlotsWin300Metric;
import mrwint.gbtasgen.metric.pokemon.gen1.slots.SlotsWinModeMetric;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.pokemon.gen1.EflOverworldInteract;
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
import mrwint.gbtasgen.segment.pokemon.gen1.common.Constants;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflBuyItemSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflDepositMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflEncounterSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflUseBikeSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflWithdrawMonSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.state.StateBuffer;
import mrwint.gbtasgen.util.EflUtil.PressMetric;

public class PrepareSquirtleTradeRed extends SeqSegment {

	@Override
	public void execute() {

    seqMetric(new OutputParty());
    seqMetric(new OutputBoxMons());
//	  {
//	    seq(new EflWalkToSegment(25, 4, false)); // house
//	    seq(new EflWalkToSegment(2, 1)); // house
//	    seq(new EflWalkToSegment(4, 1)); // house
//	    seq(new EflWalkToSegment(2, 1)); // house
//	    seq(new EflWalkToSegment(2, 7)); // house
//	    seq(new EflWalkToSegment(4, 3, false)); // eevee ball
//	    seqMove(new EflOverworldInteract(2)); // eevee ball
//	    seq(new EflTextSegment()); // got eevee
//      seq(new EflSkipTextsSegment(2)); // no nick
//      seq(new EflSkipTextsSegment(4)); // no room, to box
//	    seq(new EflWalkToSegment(3, 6)); // leave
//	    seq(new EflWalkToSegment(3, 8, false)); // leave
//	    seq(new EflWalkToSegment(2, 1)); // house
//	    seq(new EflWalkToSegment(4, 1)); // house
//	    seq(new EflWalkToSegment(2, 1)); // house
//	    seq(new EflWalkToSegment(4, 0)); // house
//	    seq(new EflUseBikeSegment().fromOverworld());
//	  }

//	  seq(new EflWalkToSegment(10, 13)); // enter mart
//    seq(new EflWalkToSegment(12, 1)); // 2nd floor
//    seq(new EflWalkToSegment(16, 1)); // 3rd floor
//    seq(new EflWalkToSegment(12, 1)); // 4th floor
//    seq(new EflWalkToSegment(16, 1)); // 5th floor
//    seq(new EflWalkToSegment(12, 1)); // roof
//    seq(new EflWalkToSegment(12, 2, false)); // go to vending machine
//    seqMove(new EflOverworldInteract(5));
//    seq(new EflSkipTextsSegment(1)); // vending machine
//    seqEflButton(A); // buy fresh water
//    seq(new EflSkipTextsSegment(1)); // popped out
//    seqMove(new EflOverworldInteract(5));
//    seq(new EflSkipTextsSegment(1)); // vending machine
//    seqEflButton(A); // buy fresh water
//    seqUnbounded(new EflSkipTextsSegment(1)); // popped out
//    seqUnbounded(new EflWalkToSegment(7, 4)); // TODO: optimize position
//    seqUnbounded(new EflWalkToSegment(6, 4, false)); // TODO: optimize position
//    delayEfl(new SeqSegment() {
//      @Override
//      protected void execute() {
//        seqEflButtonUnboundedNoDelay(A);
//        seqMetric(new EflOverworldInteract.OverworldInteractMetric(2));
//      }
//    });
//
//    seq(new EflSkipTextsSegment(2));
//    seq(new EflSkipTextsSegment(1, true)); // give drink
//    seqEflButton(A, PRESSED); // fresh water
//    seq(new EflSkipTextsSegment(7)); // get ice beam
//    seq(new EflWalkToSegment(15, 2)); // 5th
////    {
////      seq(new EflWalkToSegment(5, 4, false)); // left cashier
////      seqMove(new EflOverworldInteract(3));
////      seq(new EflSkipTextsSegment(1, true)); // buy
////      seq(new EflTextSegment());
////      {
////        seq(new EflBuyItemSegment(5, 8)); // X Speed x8
////        seq(new EflBuyItemSegment(0, 4, true)); // X Atk x4
////      }
////      seqEflButton(B); // cancel
////      seq(new EflSkipTextsSegment(2)); // cancel + bye
////    }
//    seq(new EflWalkToSegment(16,1)); // 4th
//
//    save("tmp");
//    load("tmp");
//
//    {
//      seq(new EflWalkToSegment(5, 6, false)); // left cashier
//      seqMove(new EflOverworldInteract(1));
////      {
////        seq(new EflTextSegment(B));
////        seqEflButton(DOWN | A); // sell
////        seq(new EflTextSegment(B)); // what to sell
////
////        seqMetric(new OutputItems());
////      }
//
//
//      seq(new EflSkipTextsSegment(1, true)); // buy
//      seq(new EflTextSegment(B));
//      {
//        seq(new EflBuyItemSegment(1, 3)); // Fire Stone x3
//        seq(new EflBuyItemSegment(0, 1, true)); // Poke Doll x1
////        seq(new EflBuyItemSegment(4, 3)); // Leaf Stone x3
////        seq(new EflBuyItemSegment(0, 2)); // Thunder Stone x2
////        seq(new EflBuyItemSegment(1, 3, true)); // Water Stone x3
//      }
//      seqEflButton(B); // cancel
//      seq(new EflSkipTextsSegment(2)); // cancel + bye
//    }
//    seq(new EflWalkToSegment(12,1)); // 3rd
//    seq(new EflWalkToSegment(16,1)); // 2nd
////    {
////      seq(new EflWalkToSegment(9,3)); // right cashier
////      seq(new EflWalkToSegment(8,3)); // right cashier
////      seqMove(new EflOverworldInteract(2));
////      seq(new EflSkipTextsSegment(1, true)); // buy
////      seq(new EflTextSegment());
////      {
////        seq(new EflBuyItemSegment(3, 1, true)); // TM07
////      }
////      seqEflButton(B); // cancel
////      seq(new EflSkipTextsSegment(2)); // cancel + bye
////    }
//    seq(new EflWalkToSegment(12,1)); // 1st
//    seq(new EflWalkToSegment(16,8,false)); // out
//
//    seqMetric(new OutputItems());
//
//    save("pst1");
//    load("pst1");
//
//    {
//      seqEflButton(START, PRESSED);
//      seqEflScrollA(-1); // mon
//      seqEflScrollAF(2); // dux
//      seqEflSkipInput(1);
//      seqEflScrollAF(1); // Fly
//      seqEflSkipInput(1);
//      seqEflScrollA(3);
//      seqEflSkipInput(1);
//    }
//
//  seq(new EflWalkToSegment(3, 5)); // enter center
    seqUnbounded(new EflWalkToSegment(41, 9)); // enter center

//    seqUnbounded(new EflWalkToSegment(13, 5)); // PC
    seq(new EflWalkToSegment(13, 4)); // PC
    seq(new EflWalkToSegment(13, 3, false)); // PC
    {
      seqEflButton(A); // use PC
      seq(new EflSkipTextsSegment(1)); // turned on
      seqEflButton(A); // someone's PC
      seq(new EflSkipTextsSegment(2)); // accessed, mon storage
      seqMetric(new OutputParty());
      seqMetric(new OutputBoxMons());
      seq(new EflDepositMonSegment(CHARMELEON));
      seq(new EflDepositMonSegment(CATERPIE));
      seq(new EflDepositMonSegment(DUGTRIO));
//      seq(new EflDepositMonSegment(MEOWTH));
//      seq(new EflWithdrawMonSegment(EEVEE));
      seq(new EflWithdrawMonSegment(MANKEY));
      seq(new EflWithdrawMonSegment(GROWLITHE));
//      seq(new EflWithdrawMonSegment(ODDISH));
      seq(new EflWithdrawMonSegment(CLEFAIRY));
      seqEflButton(B, MENU); // cancel
      seqEflButton(B, MENU); // cancel
    }

//    {
//      seqMetric(new OutputItems());
//      seqEflSkipInput(1);
//      seqEflButton(START, PRESSED);
//      seqEflScrollA(1); // items
//      seqEflScrollFastAF(10+1); // Fire Stone
//      seqEflButton(A, PRESSED); // use
//      seqEflScrollAF(2); // Eevee
//      seq(new EflEvolutionSegment()); // Flareon
//      seqEflButton(B);
//      seqEflButton(START);
//    }

    seqMetric(new OutputParty());

    seq(new EflWalkToSegment(11, 4)); // cable club
    seq(new EflWalkToSegment(11, 3)); // cable club
  }
}
