package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import static mrwint.gbtasgen.move.Move.A;
import static mrwint.gbtasgen.move.Move.B;
import static mrwint.gbtasgen.move.Move.START;
import static mrwint.gbtasgen.state.Gameboy.curGb;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.MENU;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.PRESSED;
import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputBoxMons;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputItems;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputParty;
import mrwint.gbtasgen.move.EflSkipInput;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.pokemon.gen1.EflOverworldInteract;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.EflCatchMonSegment;
import mrwint.gbtasgen.segment.pokemon.EflEvolutionSegment;
import mrwint.gbtasgen.segment.pokemon.EflLearnTMSegment;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.pokemon.EflWalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflEndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflInitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment.EflEnemyMoveDesc;
import mrwint.gbtasgen.segment.pokemon.fight.EflNewEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflDepositMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflUseBikeSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflWithdrawMonSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.util.EflUtil;

public class PrepareSquirtleTradeBlue extends SeqSegment {

	@Override
	public void execute() {
    {
//      seqMetric(new OutputParty());
//      seqMetric(new OutputItems());
//      seqEflButton(START, PRESSED);
//      seqEflScrollA(1); // mon
//      seqEflScrollAF(3); // pidgey
//      seqEflButton(A, PRESSED); // Fly
//      seqEflScrollA(1); // celadon
//      seqEflSkipInput(1);
//      seq(new EflUseBikeSegment(1, 0));
//      seq(new EflWalkToSegment(-1, 18)); // leave celadon
//      seq(new EflWalkToSegment(27, 10)); // Snorlax
//      {
//        seqEflButton(Move.START, PRESSED);
//        seqEflButton(A, PRESSED); // items
//        seqEflScrollFastAF(18+1); // poke flute
//        seqEflButton(Move.A, PRESSED); // use
//      }
//      seq(new EflSkipTextsSegment(1)); // playing flute
//      seq(new EflSkipTextsSegment(2)); // snorlax attacks
//      seq(new EflCatchMonSegment(2));
//      seq(new EflWalkToSegment(23, 10, false)); // enter house
//      seq(new EflWalkToSegment(-1, 8, false)); // leave house
//
//      save("pst1");
      load("pst1");

      seq(new EflWalkToSegment(11, 17)); // Route 17
      seqEflSkipInput(1); // TODO: fix
      seq(new EflWalkToSegment(11, 18)); // Route 17
      seq(new EflWalkToSegment(15, 7)); // above grass


      for (int i = 0; i < 6; i++) {
        delayEfl(new SeqSegment() {
          @Override
          protected void execute() {
            seqEflSkipInput(1);
            seqMetric(new StateResettingMetric() {
              @Override
              public int getMetricInternal() {
                int add = EflUtil.runToAddressLimit(0, 0, 100, curGb.pokemon.doWalkPreEncounterCheckAddress);
                if(add == 0) {
                  System.out.println("ERROR: didn't find doWalkPreEncounterCheckAddress");
                  return 0;
                } else {
                  add = EflUtil.runToAddressLimit(0, 0, 100, curGb.pokemon.doWalkPostEncounterCheckAddress, curGb.pokemon.encounterCheckMainFuncEncounterAddress);
                  if(add == 0) {
                    System.out.println("ERROR: didn't find doWalkPostEncounterCheckAddress or encounterCheckMainFuncEncounterAddress");
                    return 0;
                  }
                  if(add == curGb.pokemon.doWalkPostEncounterCheckAddress) {
                    return 1;
                  }
                  return 0;
                }
              }
            });
          }
        });
      }
      seqEflButtonUnboundedNoDelay(A); // rare candy
      seq(new EflTextSegment()); // get rare candy
      for (int i = 0; i < 4; i++)
        seqEflButtonUnboundedNoDelay(Move.RIGHT);
//      for (int i = 0; i < 2; i++) {
//        delayEfl(new SeqSegment() {
//          @Override
//          protected void execute() {
//            seqEflButtonUnboundedNoDelay(Move.RIGHT);
//            seqMetric(new StateResettingMetric() {
//              @Override
//              public int getMetricInternal() {
//                int add = EflUtil.runToAddressLimit(0, 0, 100, curGb.pokemon.doWalkPreEncounterCheckAddress);
//                if(add == 0) {
//                  System.out.println("ERROR: didn't find doWalkPreEncounterCheckAddress");
//                  return 0;
//                } else {
//                  add = EflUtil.runToAddressLimit(0, 0, 100, curGb.pokemon.doWalkPostEncounterCheckAddress, curGb.pokemon.encounterCheckMainFuncEncounterAddress);
//                  if(add == 0) {
//                    System.out.println("ERROR: didn't find doWalkPostEncounterCheckAddress or encounterCheckMainFuncEncounterAddress");
//                    return 0;
//                  }
//                  if(add == curGb.pokemon.doWalkPostEncounterCheckAddress) {
//                    return 1;
//                  }
//                  return 0;
//                }
//              }
//            });
//          }
//        });
//      }

      seq(new EflWalkToSegment(13, 143)); // ledge
      seq(new EflWalkToSegment(34, 8, false)); // enter house
      seq(new EflWalkToSegment(8, 4, false)); // leave house
      seq(new EflUseBikeSegment(2, 0));
      seq(new EflWalkToSegment(50, 8)); // Fuchsia

      save("pst2");
      load("pst2");

      seqMetric(new OutputParty());

      seqMetric(new OutputItems());
      seqMetric(new OutputBoxMons());

      seq(new EflWalkToSegment(19, 27)); // enter center

      seq(new EflWalkToSegment(13, 4)); // PC
      seq(new EflWalkToSegment(13, 3, false)); // PC // TODO: fix
      {
        seqEflButton(A); // use PC
        seq(new EflSkipTextsSegment(1)); // turned on
        seqEflButton(A); // someone's PC
        seq(new EflSkipTextsSegment(2)); // accessed, mon storage
        seqMetric(new OutputParty());
        seqMetric(new OutputBoxMons());
        seq(new EflDepositMonSegment(1, 1)); // Bulbasaur
        seq(new EflDepositMonSegment(0, 2)); // Pidgey
        seq(new EflDepositMonSegment(0, 2)); // Rattata
        seq(new EflDepositMonSegment(0, 2)); // Ekans
        seq(new EflWithdrawMonSegment(-1, 2)); // Cubone
        seq(new EflWithdrawMonSegment(0, 2)); // Gastly
        seq(new EflWithdrawMonSegment(0, 2)); // Bellsprout
        seq(new EflWithdrawMonSegment(0, 2)); // Vulpix
        seqEflButton(B, MENU); // cancel
        seqEflButton(B, MENU); // cancel
      }

      seq(new EflWalkToSegment(11, 4)); // cable club
      seq(new EflWalkToSegment(11, 3)); // cable club
    }
  }
}
