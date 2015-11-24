package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import static mrwint.gbtasgen.move.Move.A;
import static mrwint.gbtasgen.move.Move.B;
import static mrwint.gbtasgen.move.Move.DOWN;
import static mrwint.gbtasgen.move.Move.START;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.ABRA;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.BEEDRILL;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.BUTTERFREE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DRAGONAIR;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DRATINI;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.EEVEE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.FARFETCHD;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.MEOWTH;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.MOLTRES;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PIDGEOT;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.RAICHU;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.RARE_CANDY;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SANDSHREW;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.WARTORTLE;
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
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.pokemon.EflWalkToSegment;import mrwint.gbtasgen.segment.pokemon.gen1.common.BuyItemSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.Constants;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflBuyItemSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflCancelMoveLearnSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflChangeMonBoxSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflDepositMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflReleaseMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSelectItemSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSelectMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflUseBikeSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflWithdrawMonSegment;
import mrwint.gbtasgen.segment.util.CheckMetricSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.state.StateBuffer;
import mrwint.gbtasgen.util.EflUtil.PressMetric;

public class PorygonBlue extends SeqSegment {

  @Override
  public void execute() {
//    seq(new EflSelectMonSegment(PIDGEOT).fromOverworld().andFlyTo(5)); // Celadon
//    seqEflSkipInput(1);
//    seq(new EflUseBikeSegment().fromOverworld());
//    seq(new EflWalkToSegment(28, 19)); // enter
//
//    seq(new EflWalkToSegment(15, 15));
//    seq(new EflWalkToSegment(14, 15));
//    seqEflButton(A);
//
//    save("tmp");
//    load("tmp");
//
//    seqUnbounded(new EflSkipTextsSegment(1, true)); // plays
//    seqEflSkipInput(1);
//    delayEfl(new SeqSegment() {
//      @Override
//      protected void execute() {
//        seqEflButtonUnboundedNoDelay(A);
//        seqMetric(new SlotsWinModeMetric());
//      }
//    });
//    save("tmp2");
//    load("tmp2");
//    StateBuffer.pushBufferSize(10);
//    delayEfl(new SeqSegment() {
//      @Override
//      protected void execute() {
//        seqEflButtonUnboundedNoDelay(A, MENU);
//        seqMetric(new SlotsWheel1Metric());
//      }
//    });
//    delayEfl(new SeqSegment() {
//      @Override
//      protected void execute() {
//        seqEflButtonUnboundedNoDelay(A, MENU);
//        seqMetric(new SlotsWheel2Metric());
//      }
//    });
//    delayEfl(new SeqSegment() {
//      @Override
//      protected void execute() {
//        seqEflButtonUnboundedNoDelay(A, MENU);
//        seqMetric(new SlotsWheel3Metric());
//        seqMetric(new SlotsWin300Metric());
//      }
//    });
//    seqEflSkipInput(1);
//    seqEflButton(Move.A); // save 30f
//    seqEflButton(Move.B); // skip 300 points text
//    seqEflButton(Move.A); // again?
//    for (int i = 19; i > 0; i--)
//    {
//      seq(new EflTextSegment(Move.B)); // how many?
//      delayEfl(new SeqSegment() {
//        @Override
//        protected void execute() {
//          seqEflButtonUnboundedNoDelay(DOWN | A);
//          seqMetric(new SlotsWinModeMetric());
//        }
//      });
//      delayEfl(new SeqSegment() {
//        @Override
//        protected void execute() {
//          seqEflButtonUnboundedNoDelay(A, MENU);
//          seqMetric(new SlotsWheel1Metric());
//        }
//      });
//      delayEfl(new SeqSegment() {
//        @Override
//        protected void execute() {
//          seqEflButtonUnboundedNoDelay(A, MENU);
//          seqMetric(new SlotsWheel2Metric());
//        }
//      });
//      delayEfl(new SeqSegment() {
//        @Override
//        protected void execute() {
//          seqEflButtonUnboundedNoDelay(A, MENU);
//          seqMetric(new SlotsWheel3Metric());
//          seqUnbounded(new EflTextSegment(Move.A)); // Yeah!
//          seqEflButtonUnboundedNoDelay(Move.B);
//          seqMetric(new SlotsWin300Metric());
//        }
//      });
//      if (i != 1) {
//        seq(new EflSkipTextsSegment(1)); // lined up
//        seq(new EflSkipTextsSegment(1, true)); // one more?
//      }
//    }
//    seq(new EflSkipTextsSegment(1, true)); // lined up
//    seq(new EflSkipTextsSegment(1)); // one more?
//    StateBuffer.popBufferSize();
//
//    seq(new EflWalkToSegment(15, 16)); // leave
//    seq(new EflWalkToSegment(15, 18, false)); // leave
//
//    save("po1");
//    load("po1");
//
//    seq(new EflWalkToSegment(33, 19)); // enter
//    seq(new EflWalkToSegment(4, 3)); // counter
//    seqMove(new EflOverworldInteract(4));
//    seqEflButton(B); // exchange
//    seqEflScrollAF(2); // Porygon
//    seqEflButton(A, PRESSED); // want Porygon
//    seq(new EflTextSegment()); // got Porygon
//    seq(new EflSkipTextsSegment(2)); // no nick
//    seq(new EflSkipTextsSegment(4)); // no room, set to box
//    seq(new EflWalkToSegment(4, 8, false)); // leave
//
//    save("po2");
    load("po2");

    seq(new EflUseBikeSegment().fromOverworld());
    seq(new EflWalkToSegment(41, 9)); // enter center
    seq(new EflWalkToSegment(13, 4)); // PC // TODO: optimize
    seq(new EflWalkToSegment(13, 3, false)); // PC
    {
      seqEflButton(A); // use PC
      seq(new EflSkipTextsSegment(1)); // turned on
      seqEflButton(A); // someone's PC
      seq(new EflSkipTextsSegment(2)); // accessed, mon storage
      seq(new EflChangeMonBoxSegment(2)); // box 3
      seq(new EflReleaseMonSegment(MOLTRES));
      seq(new EflDepositMonSegment(RAICHU));
      seq(new EflWithdrawMonSegment(SANDSHREW));
      seqEflButton(B, MENU); // cancel
      seqEflButton(B, PRESSED); // cancel
    }
    seqMetric(new OutputBoxMons());
    seqMetric(new OutputParty());
    seqMetric(new OutputItems());

    seqEflSkipInput(1);
    seq(new EflSelectItemSegment(RARE_CANDY).fromOverworld().andUse());
    seq(new EflSelectMonSegment(DRAGONAIR));
    seqEflButton(B); // lvlup to 45
    seqEflButton(A); // cancel stats
    seq(new EflCancelMoveLearnSegment()); // dragon rage
    seq(new EflSelectItemSegment(RARE_CANDY).andUse());
    seq(new EflSelectMonSegment(DRAGONAIR));
    seqEflButton(B); // lvlup to 46
    seqEflButton(A); // cancel stats
    seq(new EflSelectItemSegment(RARE_CANDY).andUse());
    seq(new EflSelectMonSegment(DRAGONAIR));
    seqEflButton(B); // lvlup to 47
    seqEflButton(A); // cancel stats
    seq(new EflSelectItemSegment(RARE_CANDY).andUse());
    seq(new EflSelectMonSegment(DRAGONAIR));
    seqEflButton(B); // lvlup to 48
    seqEflButton(A); // cancel stats
    seq(new EflSelectItemSegment(RARE_CANDY).andUse());
    seq(new EflSelectMonSegment(DRAGONAIR));
    seqEflButton(B); // lvlup to 49
    seqEflButton(A); // cancel stats
    seqEflButton(B); // cancel
    seqEflButton(START); // cancel

    seq(new EflWalkToSegment(11, 4)); // cable club
    seq(new EflWalkToSegment(11, 3)); // cable club
	}
}
