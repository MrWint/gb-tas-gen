package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import static mrwint.gbtasgen.move.Move.A;
import static mrwint.gbtasgen.move.Move.B;
import static mrwint.gbtasgen.move.Move.DOWN;
import static mrwint.gbtasgen.move.Move.START;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.ABRA;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.BEEDRILL;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.BUTTERFREE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DRATINI;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.EEVEE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.FARFETCHD;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.HM02;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.MEOWTH;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SQUIRTLE;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.MENU;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.PRESSED;
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
import mrwint.gbtasgen.segment.pokemon.EflLearnTMSegment;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.pokemon.EflWalkToSegment;import mrwint.gbtasgen.segment.pokemon.gen1.common.BuyItemSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.Constants;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflBuyItemSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflDepositMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSelectItemSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSelectMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSellItemSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflUseBikeSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflWithdrawMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.NamingSegment;
import mrwint.gbtasgen.segment.util.CheckMetricSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.state.StateBuffer;
import mrwint.gbtasgen.util.EflUtil.PressMetric;

public class CeladonBlue extends SeqSegment {

  @Override
  public void execute() {
//    seqMetric(new OutputParty());
//    seqMetric(new OutputItems());
//    
//    seq(new EflWalkToSegment(-1, 18)); // leave celadon
//    seq(new EflWalkToSegment(34, 9, false)); // to bush
//    seqMetric(new OutputParty());
//    seq(new EflSelectMonSegment(FARFETCHD).fromOverworld().andCut());
//    seq(new EflWalkToSegment(23, 4, false)); // house
//    seq(new EflWalkToSegment(-1, 2, false)); // leave house
//    seq(new EflWalkToSegment(7, 5)); // fly house
//    seq(new EflWalkToSegment(2, 4)); // fly girl
//    seqMove(new EflOverworldInteract(1));
//    seq(new EflSkipTextsSegment(5)); // get fly
//    seq(new EflWalkToSegment(2, 8, false)); // leave house
//    seq(new EflWalkToSegment(18, 5, false)); // house
//    seq(new EflWalkToSegment(8, 2, false)); // leave house
//    seq(new EflUseBikeSegment().fromOverworld());
//    seq(new EflWalkToSegment(34, 7)); // to bush
//    seq(new EflWalkToSegment(34, 8)); // to bush
//    seq(new EflSelectItemSegment(HM02).fromOverworld().andUse());
//    seq(new EflLearnTMSegment(FARFETCHD));
//    seqEflButton(B); // exit item menu
//    seq(new EflSelectMonSegment(FARFETCHD).fromMainMenu().andCut());
//    seq(new EflWalkToSegment(40, 10)); // enter celadon
//
//    save("ce1");
    load("ce1");

    seq(new EflWalkToSegment(10, 13)); // enter mart
    seq(new EflWalkToSegment(12, 1)); // 2nd floor
    seq(new EflWalkToSegment(16, 1)); // 3rd floor
    seq(new EflWalkToSegment(12, 1)); // 4th floor
    seq(new EflWalkToSegment(16, 1)); // 5th floor
    seq(new EflWalkToSegment(12, 1)); // roof
    seq(new EflWalkToSegment(12, 2, false)); // go to vending machine
    seqMove(new EflOverworldInteract(5));
    seq(new EflSkipTextsSegment(1)); // vending machine
    seqEflButton(A); // buy fresh water
    seq(new EflSkipTextsSegment(1)); // popped out
    seqMove(new EflOverworldInteract(5));
    seq(new EflSkipTextsSegment(1)); // vending machine
    seqEflButton(A); // buy fresh water
    seq(new EflSkipTextsSegment(1)); // popped out
    seqMove(new EflOverworldInteract(5));
    seq(new EflSkipTextsSegment(1)); // vending machine
    seqEflButton(DOWN | A); // buy soda pop
    seqUnbounded(new EflSkipTextsSegment(1)); // popped out
    seqUnbounded(new EflWalkToSegment(7, 4)); // TODO: optimize position
    seqUnbounded(new EflWalkToSegment(6, 4, false)); // TODO: optimize position
    delayEfl(new SeqSegment() {
      @Override
      protected void execute() {
        seqEflButtonUnboundedNoDelay(A);
        seqMetric(new EflOverworldInteract.OverworldInteractMetric(2));
      }
    });

    seq(new EflSkipTextsSegment(2));
    seq(new EflSkipTextsSegment(1, true)); // give drink
    seqEflButton(A, PRESSED); // fresh water
    seq(new EflSkipTextsSegment(6)); // get ice beam
    seqUnbounded(new EflSkipTextsSegment(1)); // get ice beam
    delayEfl(new SeqSegment() {
      @Override
      protected void execute() {
        seqEflButtonUnboundedNoDelay(A);
        seqMetric(new EflOverworldInteract.OverworldInteractMetric(2));
      }
    });
    seq(new EflSkipTextsSegment(2));
    seq(new EflSkipTextsSegment(1, true)); // give drink
    seqEflButton(DOWN | A, PRESSED); // fresh water
    seq(new EflSkipTextsSegment(7)); // get rock slide
    seq(new EflWalkToSegment(15,2)); // 5th
    seq(new EflWalkToSegment(16,1)); // 4th

    save("tmp");
    load("tmp");

    seqMetric(new OutputItems());
    {
      seq(new EflWalkToSegment(5, 6, false)); // left cashier
      seqMove(new EflOverworldInteract(1));
      {
        seq(new EflTextSegment(B));
        seqEflButton(DOWN | A); // sell
        seq(new EflTextSegment(B)); // what to sell

        seq(new EflSellItemSegment(6, 0)); // nugget x2
        seq(new EflSellItemSegment(10, 0, true)); // tm34 x1
        seqEflButton(B); // cancel

        seqMetric(new OutputItems());
      }
      seq(new EflSkipTextsSegment(1, true)); // buy
      seq(new EflTextSegment(B));
      {
        seq(new EflBuyItemSegment(0, 1)); // Poke Doll x1
        seq(new EflBuyItemSegment(1, 3, true)); // Fire Stone x3
      }
      seqEflButton(B); // cancel
      seq(new EflSkipTextsSegment(2)); // cancel + bye
    }
    seq(new EflWalkToSegment(12,1)); // 3rd
    seq(new EflWalkToSegment(16,1)); // 2nd
    {
      seq(new EflWalkToSegment(9,3)); // right cashier
      seq(new EflWalkToSegment(8,3)); // right cashier
      seqMove(new EflOverworldInteract(2));
      seq(new EflSkipTextsSegment(1, true)); // buy
      seq(new EflTextSegment());
      {
//        seq(new EflBuyItemSegment(3, 1)); // TM07
        seq(new EflBuyItemSegment(7, 1, true)); // TM09
      }
      seqEflButton(B); // cancel
      seq(new EflSkipTextsSegment(2)); // cancel + bye
    }
    {
      seq(new EflWalkToSegment(5, 4, false)); // left cashier
      seqMove(new EflOverworldInteract(1));
      seq(new EflSkipTextsSegment(1, true)); // buy
      seq(new EflTextSegment());
      {
        seq(new EflBuyItemSegment(1, 2, true)); // Super Potion
      }
      seqEflButton(B); // cancel
      seq(new EflSkipTextsSegment(2)); // cancel + bye
    }
    seq(new EflWalkToSegment(12, 1)); // 1st
    seq(new EflWalkToSegment(16, 8, false)); // out

    seqMetric(new OutputItems());

    save("ce2");
    load("ce2");

    seqMetric(new OutputItems());
    seq(new EflUseBikeSegment().fromOverworld());

    {
      seq(new EflWalkToSegment(31, 27)); // enter
      seq(new EflWalkToSegment(1, 1)); // coin case
      seqMove(new EflOverworldInteract(5));
      seq(new EflSkipTextsSegment(7));
      seq(new EflWalkToSegment(3,  8, false)); // leave
      seq(new EflUseBikeSegment().fromOverworld());
      seq(new EflWalkToSegment(28, 19)); // enter

      seq(new EflWalkToSegment(15, 9));
      seqEflButton(A);
      seq(new EflSkipTextsSegment(1));
      seq(new EflWalkToSegment(15, 10));
      seq(new EflWalkToSegment(14, 10));
      seqEflButton(A);

      save("tmp");
      load("tmp");

      seqUnbounded(new EflSkipTextsSegment(1, true)); // plays
      seqEflSkipInput(1);
      delayEfl(new SeqSegment() {
        @Override
        protected void execute() {
          seqEflButtonUnboundedNoDelay(A);
          seqMetric(new SlotsWinModeMetric());
        }
      });
      save("tmp2");
      load("tmp2");
      StateBuffer.pushBufferSize(50);
      delayEfl(new SeqSegment() {
        @Override
        protected void execute() {
          seqEflButtonUnboundedNoDelay(A, MENU);
          seqMetric(new SlotsWheel1Metric().onlyMiddle());
        }
      });
      delayEfl(new SeqSegment() {
        @Override
        protected void execute() {
          seqEflButtonUnboundedNoDelay(A, MENU);
          seqMetric(new SlotsWheel2Metric().onlyMiddle());
        }
      });
      delayEfl(new SeqSegment() {
        @Override
        protected void execute() {
          seqEflButtonUnboundedNoDelay(A, MENU);
          seqMetric(new SlotsWheel3Metric());
          seqMetric(new SlotsWin300Metric());
        }
      });
      seqEflSkipInput(1);
      seqEflButton(Move.A); // save 30f
      seqEflButton(Move.B); // skip 300 points text
      seqEflButton(Move.A); // again?
      for (int i = 15; i > 0; i--) // 16
      {
        final int fi = i;
        seq(new EflTextSegment(Move.B)); // how many?
        delayEfl(new SeqSegment() {
          @Override
          protected void execute() {
            seqEflButtonUnboundedNoDelay(A);
            seqMetric(new SlotsWinModeMetric());
          }
        });
        delayEfl(new SeqSegment() {
          @Override
          protected void execute() {
            seqEflButtonUnboundedNoDelay(A, MENU);
            if ((fi % 3) == 0)
              seqMetric(new SlotsWheel1Metric().onlyUp());
            if ((fi % 3) == 2)
              seqMetric(new SlotsWheel1Metric().onlyDown());
            if ((fi % 3) == 1)
              seqMetric(new SlotsWheel1Metric().onlyMiddle());
          }
        });
        delayEfl(new SeqSegment() {
          @Override
          protected void execute() {
            seqEflButtonUnboundedNoDelay(A, MENU);
//            if ((fi % 3) == 0)
//              seqMetric(new SlotsWheel2Metric().onlyUp());
//            if ((fi % 3) == 2)
//              seqMetric(new SlotsWheel2Metric().onlyDown());
//            if ((fi % 3) == 1)
              seqMetric(new SlotsWheel2Metric().onlyMiddle());
          }
        });
        delayEfl(new SeqSegment() {
          @Override
          protected void execute() {
            seqEflButtonUnboundedNoDelay(A, MENU);
            seqMetric(new SlotsWheel3Metric());
            seqUnbounded(new EflTextSegment(Move.A)); // Yeah!
            seqEflButtonUnboundedNoDelay(Move.B);
            seqMetric(new SlotsWin300Metric());
          }
        });
        if (i != 1) {
          seq(new EflSkipTextsSegment(1)); // lined up
          seq(new EflSkipTextsSegment(1, true)); // one more?
        }
      }
      seq(new EflSkipTextsSegment(1, true)); // lined up
      seq(new EflSkipTextsSegment(1)); // one more? no
      StateBuffer.popBufferSize();
    }

    save("ce3a");
    load("ce3a");

    seqUnbounded(new EflWalkToSegment(15, 18, false)); // leave
    seqUnbounded(new EflWalkToSegment(33, 19)); // enter
    seqUnbounded(new EflWalkToSegment(4, 3)); // counter
    seqMoveUnbounded(new EflOverworldInteract(4));
    seqEflButtonUnbounded(B); // exchange
    seqEflButtonUnbounded(DOWN | A); // dratini
    seqEflSkipInput(1);
    delayEfl(new SeqSegment() {
      @Override
      protected void execute() {
        seqEflButtonUnbounded(A); // yes
//        seqMetric(new Gen1CheckDVMetric(15, 0, 14, 15, 15));
        seqMetric(new Gen1CheckDVMetric(15, 0, 15, 15, 15));
      }
    });
    seq(new EflTextSegment()); // got dratini
    seq(new EflSkipTextsSegment(1)); // want to give a nick
    seq(new EflSkipTextsSegment(1, true)); // to Dratini? (yes)
    seq(new NamingSegment("D"));
    seqEflButton(START); // Done
    seq(new EflSkipTextsSegment(4)); // no room, set to box
    seq(new EflWalkToSegment(4, 8, false)); // leave
    seq(new EflUseBikeSegment().fromOverworld());

    save("ce4");
    load("ce4");

    seqUnbounded(new EflWalkToSegment(41, 9)); // enter center
    seqUnbounded(new EflWalkToSegment(13, 5)); // PC // TODO: optimize
    seq(new EflWalkToSegment(13, 4)); // PC
    {
      seqEflButton(A); // use PC
      seq(new EflSkipTextsSegment(1)); // turned on
      seqEflButton(A); // someone's PC
      seq(new EflSkipTextsSegment(2)); // accessed, mon storage
      seqMetric(new OutputParty());
      seqMetric(new OutputBoxMons());
      seq(new EflDepositMonSegment(BUTTERFREE));
      seq(new EflDepositMonSegment(EEVEE));
      seq(new EflWithdrawMonSegment(DRATINI));
      seq(new EflWithdrawMonSegment(MEOWTH));
      seqEflButton(B, MENU); // cancel
      seqEflButton(B, MENU); // cancel
    }

    seqMetric(new OutputParty());

    seq(new EflWalkToSegment(11, 4)); // cable club
    seq(new EflWalkToSegment(11, 3)); // cable club
	}
}
