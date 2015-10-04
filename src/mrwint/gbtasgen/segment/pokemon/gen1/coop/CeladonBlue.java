package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import static mrwint.gbtasgen.move.Move.A;
import static mrwint.gbtasgen.move.Move.B;
import static mrwint.gbtasgen.move.Move.DOWN;
import static mrwint.gbtasgen.move.Move.START;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.MENU;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.PRESSED;
import mrwint.gbtasgen.metric.pokemon.gen1.Gen1CheckDVMetric;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputItems;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputParty;
import mrwint.gbtasgen.metric.pokemon.gen1.slots.SlotsWheel1Metric;
import mrwint.gbtasgen.metric.pokemon.gen1.slots.SlotsWheel2Metric;
import mrwint.gbtasgen.metric.pokemon.gen1.slots.SlotsWheel3Metric;
import mrwint.gbtasgen.metric.pokemon.gen1.slots.SlotsWin300Metric;
import mrwint.gbtasgen.metric.pokemon.gen1.slots.SlotsWinModeMetric;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.pokemon.gen1.EflOverworldInteract;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.pokemon.EflWalkToSegment;import mrwint.gbtasgen.segment.pokemon.gen1.common.BuyItemSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflBuyItemSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflDepositMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflUseBikeSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflWithdrawMonSegment;
import mrwint.gbtasgen.segment.util.CheckMetricSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.state.StateBuffer;
import mrwint.gbtasgen.util.EflUtil.PressMetric;

public class CeladonBlue extends SeqSegment {

  @Override
  public void execute() {
//    seq(new EflWalkToSegment(-1, 18)); // leave celadon
//    seq(new EflWalkToSegment(34, 9, false)); // to bush
//    seqMetric(new OutputParty());
//    {
//      seqEflButton(START, PRESSED);
//      seqEflScrollA(1); // mon
//      seqEflScrollAF(3); // dux
//      seqEflButton(A, PRESSED); // cut
//      seqEflButton(B); // hacked away (no text scroll)?
//    }
//    seq(new EflWalkToSegment(23, 4, false)); // house
//    seq(new EflWalkToSegment(-1, 2, false)); // leave house
//    seq(new EflWalkToSegment(7, 5)); // fly house
//    seq(new EflWalkToSegment(2, 4)); // fly girl
//    seqMove(new EflOverworldInteract(1));
//    seq(new EflSkipTextsSegment(5)); // get fly
//    seq(new EflWalkToSegment(2, 8, false)); // leave house
//    seq(new EflWalkToSegment(18, 5, false)); // house
//    seq(new EflWalkToSegment(8, 2, false)); // leave house
//    seq(new EflUseBikeSegment(1, 0));
//    seq(new EflWalkToSegment(34, 7)); // to bush
//    seq(new EflWalkToSegment(34, 8)); // to bush
//    {
//      seqEflButton(START, PRESSED);
//      seqEflButton(A); // items
//      seqEflScrollFastAF(13 + 1); // HM02
//      seqEflButton(A, PRESSED); // use
//      seq(new EflSkipTextsSegment(2)); // booted up HM, contains xyz
//      seq(new EflSkipTextsSegment(1, true)); // learn
//      seqEflSkipInput(1);
//      seqEflButton(A); // dux
//      seq(new EflSkipTextsSegment(1)); // learned HM
//
//      seqEflButton(B, PRESSED); // exit item menu
//      seqEflScrollA(-1); // mon
//      seqEflSkipInput(1);
//      seqEflButton(A); // dux
//      seqEflButton(A, PRESSED); // cut
//      seqEflButton(B); // hacked away (no text scroll)?
//    }
//    seq(new EflWalkToSegment(40, 10)); // enter celadon
//
//    save("ce1");
//    load("ce1");
//
//    seq(new EflWalkToSegment(10, 13)); // enter mart
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
//    seq(new EflSkipTextsSegment(1)); // popped out
//    seqMove(new EflOverworldInteract(5));
//    seq(new EflSkipTextsSegment(1)); // vending machine
//    seqEflButton(DOWN | A); // buy soda pop
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
//    seq(new EflSkipTextsSegment(6)); // get ice beam
//    seqUnbounded(new EflSkipTextsSegment(1)); // get ice beam
//    delayEfl(new SeqSegment() {
//      @Override
//      protected void execute() {
//        seqEflButtonUnboundedNoDelay(A);
//        seqMetric(new EflOverworldInteract.OverworldInteractMetric(2));
//      }
//    });
////    seqMove(new EflOverworldInteract(2));
//    seq(new EflSkipTextsSegment(2));
//    seq(new EflSkipTextsSegment(1, true)); // give drink
//    seqEflButton(DOWN | A, PRESSED); // fresh water
//    seq(new EflSkipTextsSegment(7)); // get rock slide
//    seq(new EflWalkToSegment(15,2)); // 5th
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
////    {
////      seq(new EflWalkToSegment(5, 6, false)); // left cashier
////      seqMove(new EflOverworldInteract(1));
////      {
////        seq(new EflTextSegment(B));
////        seqEflButton(DOWN | A); // sell
////        seq(new EflTextSegment(B)); // what to sell
////
////        seqMetric(new OutputItems());
////      }
////
////
////      seq(new EflSkipTextsSegment(1, true)); // buy
////      seq(new EflTextSegment(B));
////      {
////        seq(new EflBuyItemSegment(4, 3)); // Leaf Stone x3
////        seq(new EflBuyItemSegment(0, 2)); // Thunder Stone x2
////        seq(new EflBuyItemSegment(1, 3, true)); // Water Stone x3
////      }
////      seqEflButton(B); // cancel
////      seq(new EflSkipTextsSegment(2)); // cancel + bye
////    }
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
//    save("ce2");
//    load("ce2");
//
//    seq(new EflUseBikeSegment(1, 0));
//
//    {
//      seq(new EflWalkToSegment(31, 27)); // enter
//      seq(new EflWalkToSegment(1, 1)); // coin case
//      seqMove(new EflOverworldInteract(5));
//      seq(new EflSkipTextsSegment(7));
//      seq(new EflWalkToSegment(3,  8, false)); // leave
//      seq(new EflUseBikeSegment(0, 0));
//      seq(new EflWalkToSegment(28, 19)); // enter
//
//        seq(new EflWalkToSegment(15, 9));
//        seqEflButton(A);
//        seq(new EflSkipTextsSegment(1));
//        seq(new EflWalkToSegment(15, 10));
//        seq(new EflWalkToSegment(14, 10));
//        seqEflButton(A);
//
//        save("tmp");
//        load("tmp");
//
//        seqUnbounded(new EflSkipTextsSegment(1, true)); // plays
//        seqEflSkipInput(1);
//        delayEfl(new SeqSegment() {
//          @Override
//          protected void execute() {
//            seqEflButtonUnboundedNoDelay(A);
//            seqMetric(new SlotsWinModeMetric());
//          }
//        });
//        save("tmp2");
//        load("tmp2");
//        StateBuffer.pushBufferSize(10);
//        delayEfl(new SeqSegment() {
//          @Override
//          protected void execute() {
//            seqEflButtonUnboundedNoDelay(A, MENU);
//            seqMetric(new SlotsWheel1Metric());
//          }
//        });
//        delayEfl(new SeqSegment() {
//          @Override
//          protected void execute() {
//            seqEflButtonUnboundedNoDelay(A, MENU);
//            seqMetric(new SlotsWheel2Metric());
//          }
//        });
//        delayEfl(new SeqSegment() {
//          @Override
//          protected void execute() {
//            seqEflButtonUnboundedNoDelay(A, MENU);
//            seqMetric(new SlotsWheel3Metric());
//            seqMetric(new SlotsWin300Metric());
//          }
//        });
//        seqEflSkipInput(1);
//        seqEflButton(Move.A); // save 30f
//        seqEflButton(Move.B); // skip 300 points text
//        seqEflButton(Move.A); // again?
//        for (int i = 18 -2; i > 0; i--)
//        {
//          seq(new EflTextSegment(Move.B)); // how many?
//          delayEfl(new SeqSegment() {
//            @Override
//            protected void execute() {
//              seqEflButtonUnboundedNoDelay(A);
//              seqMetric(new SlotsWinModeMetric());
//            }
//          });
//          delayEfl(new SeqSegment() {
//            @Override
//            protected void execute() {
//              seqEflButtonUnboundedNoDelay(A, MENU);
//              seqMetric(new SlotsWheel1Metric());
//            }
//          });
//          delayEfl(new SeqSegment() {
//            @Override
//            protected void execute() {
//              seqEflButtonUnboundedNoDelay(A, MENU);
//              seqMetric(new SlotsWheel2Metric());
//            }
//          });
//          delayEfl(new SeqSegment() {
//            @Override
//            protected void execute() {
//              seqEflButtonUnboundedNoDelay(A, MENU);
//              seqMetric(new SlotsWheel3Metric());
//              seqUnbounded(new EflTextSegment(Move.A)); // Yeah!
//              seqEflButtonUnboundedNoDelay(Move.B);
//              seqMetric(new SlotsWin300Metric());
//            }
//          });
//          if (i != 1) {
//            seq(new EflSkipTextsSegment(1)); // lined up
//            seq(new EflSkipTextsSegment(1, true)); // one more?
//          }
//        }
//        seq(new EflSkipTextsSegment(1, true)); // lined up
//        seq(new EflSkipTextsSegment(1)); // one more?
//        StateBuffer.popBufferSize();
//
////      {
////        seq(new EflWalkToSegment(5,  7, false));
////        seqMove(new EflOverworldInteract(2));
////        seq(new EflSkipTextsSegment(3));
////        seq(new EflSkipTextsSegment(1, true));
////        seq(new EflSkipTextsSegment(1));
////        seqMove(new EflOverworldInteract(2));
////        seq(new EflSkipTextsSegment(3));
////        seq(new EflSkipTextsSegment(1, true));
////        seq(new EflSkipTextsSegment(1));
////        seqMove(new EflOverworldInteract(2));
////        seq(new EflSkipTextsSegment(3));
////        seq(new EflSkipTextsSegment(1, true));
////        seq(new EflSkipTextsSegment(1));
////        seqMove(new EflOverworldInteract(2));
////        seq(new EflSkipTextsSegment(3));
////        seq(new EflSkipTextsSegment(1, true));
////        seq(new EflSkipTextsSegment(1));
////        seqMove(new EflOverworldInteract(2));
////        seq(new EflSkipTextsSegment(3));
////        seq(new EflSkipTextsSegment(1, true));
////        seq(new EflSkipTextsSegment(1));
////        seqMove(new EflOverworldInteract(2));
////        seq(new EflSkipTextsSegment(3));
////        seq(new EflSkipTextsSegment(1, true));
////        seq(new EflSkipTextsSegment(1));
////        seqMove(new EflOverworldInteract(2));
////        seq(new EflSkipTextsSegment(3));
////        seq(new EflSkipTextsSegment(1, true));
////        seq(new EflSkipTextsSegment(1));
////      }
//
//      save("ce3");
//      load("ce3");
//
//      seqUnbounded(new EflWalkToSegment(15, 18, false)); // leave
//      seqUnbounded(new EflWalkToSegment(33, 19)); // enter
//      seqUnbounded(new EflWalkToSegment(4, 3)); // counter
//      seqMoveUnbounded(new EflOverworldInteract(4));
//      seqEflButtonUnbounded(B); // exchange
//      seqEflButtonUnbounded(DOWN | A); // dratini
//      seqEflSkipInput(1);
//      delayEfl(new SeqSegment() {
//        @Override
//        protected void execute() {
//          seqEflButtonUnbounded(A); // yes
//          seqMetric(new Gen1CheckDVMetric(15, 0, 12, 15, 15));
////          seq(new CheckMetricSegment(new Gen1CheckDVMetric(15, 0, 15, 15, 15)));
//        }
//      });
//      seq(new EflTextSegment()); // got dratini
//      seq(new EflSkipTextsSegment(1)); // want to give a nick
//      seq(new EflTextSegment(Move.B)); // to Dratini?
//      seqEflButton(Move.A); // (yes)
//      seqEflButton(Move.A, PressMetric.PRESSED); // "A"
//      seqEflButton(Move.START); // Done
//      seq(new EflSkipTextsSegment(4)); // no room, set to box
//      seq(new EflWalkToSegment(4, 8, false)); // leave
//      seq(new EflUseBikeSegment(0, 0));
//	  }
//
//    save("tmp");
    load("tmp");

    seq(new EflWalkToSegment(41, 9)); // enter center
    seq(new EflWalkToSegment(13, 4)); // PC // TODO: optimize
    seq(new EflWalkToSegment(13, 3, false)); // PC
    {
      seqEflButton(A); // use PC
      seq(new EflSkipTextsSegment(1)); // turned on
      seqEflButton(A); // someone's PC
      seq(new EflSkipTextsSegment(2)); // accessed, mon storage
      seqMetric(new OutputParty());
      seq(new EflDepositMonSegment(1, 1)); // butterfree
      seq(new EflDepositMonSegment(0, 1)); // beedrill
      seq(new EflDepositMonSegment(0, 2)); // eevee
      seq(new EflWithdrawMonSegment(-1, 0)); // dratini
      seq(new EflWithdrawMonSegment(0, 0)); // meowth
      seq(new EflWithdrawMonSegment(0, 6+1)); // abra
      seqEflButton(B, MENU); // cancel
      seqEflButton(B, MENU); // cancel
    }

    seqMetric(new OutputParty());

    seq(new EflWalkToSegment(11, 4)); // cable club
    seq(new EflWalkToSegment(11, 3)); // cable club
	}
}
