package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import static mrwint.gbtasgen.move.Move.A;
import static mrwint.gbtasgen.move.Move.B;
import static mrwint.gbtasgen.move.Move.START;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.BELLSPROUT;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.BULBASAUR;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.CUBONE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DISABLE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DRATINI;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.EKANS;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.GASTLY;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PIDGEY;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PIKACHU;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.POISON_GAS;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.POKE_FLUTE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.RATTATA;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SELF_DESTRUCT;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SLUDGE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SMOG;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SMOKESCREEN;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SUPER_POTION;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TM09;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.VULPIX;
import static mrwint.gbtasgen.state.Gameboy.curGb;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.MENU;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.PRESSED;
import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckDisableEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckPoisonEffectMisses;
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
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflDepositMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSelectItemSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSelectMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflUseBikeSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflWithdrawMonSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.util.EflUtil;

public class PrepareSquirtleTradeBlue extends SeqSegment {

	@Override
	public void execute() {
//    seqMetric(new OutputParty());
//    seqMetric(new OutputItems());
//
//    seq(new EflSelectMonSegment(PIDGEY).fromOverworld().andFlyTo(1)); // celadon
//    seqEflSkipInput(1);
//    seq(new EflSelectItemSegment(TM09).fromOverworld().andUse());
//    seq(new EflLearnTMSegment(DRATINI, 0)); // wrap -> take down
//    seqEflSkipInput(0);
//    seq(new EflUseBikeSegment());
//    seq(new EflWalkToSegment(-1, 18)); // leave celadon
//    seq(new EflWalkToSegment(27, 10)); // Snorlax
//    seq(new EflSelectItemSegment(POKE_FLUTE).fromOverworld().andUse());
//    seq(new EflSkipTextsSegment(1)); // playing flute
//    seq(new EflSkipTextsSegment(2)); // snorlax attacks
//    seq(new EflCatchMonSegment());
//    seq(new EflWalkToSegment(23, 10, false)); // enter house
//    seq(new EflWalkToSegment(-1, 8, false)); // leave house
//
//    save("pst1");
//    load("pst1");
//
//    seq(new EflWalkToSegment(11, 17)); // Route 17
//    seqEflSkipInput(0); // TODO: fix
//    seq(new EflWalkToSegment(11, 18)); // Route 17
//    seq(new EflWalkToSegment(15, 7)); // above grass
//
//
//    for (int i = 0; i < 6; i++) {
//      delayEfl(new SeqSegment() {
//        @Override
//        protected void execute() {
//          seqEflSkipInput(1);
//          seqMetric(new StateResettingMetric() {
//            @Override
//            public int getMetricInternal() {
//              int add = EflUtil.runToAddressLimit(0, 0, 100, curGb.pokemon.doWalkPreEncounterCheckAddress);
//              if(add == 0) {
//                System.out.println("ERROR: didn't find doWalkPreEncounterCheckAddress");
//                return 0;
//              } else {
//                add = EflUtil.runToAddressLimit(0, 0, 100, curGb.pokemon.doWalkPostEncounterCheckAddress, curGb.pokemon.encounterCheckMainFuncEncounterAddress);
//                if(add == 0) {
//                  System.out.println("ERROR: didn't find doWalkPostEncounterCheckAddress or encounterCheckMainFuncEncounterAddress");
//                  return 0;
//                }
//                if(add == curGb.pokemon.doWalkPostEncounterCheckAddress) {
//                  return 1;
//                }
//                return 0;
//              }
//            }
//          });
//        }
//      });
//    }
//    seqEflButtonUnboundedNoDelay(A); // rare candy
//    seq(new EflTextSegment()); // get rare candy
//    for (int i = 0; i < 4; i++)
//      seqEflButtonUnboundedNoDelay(Move.RIGHT);
////      for (int i = 0; i < 2; i++) {
////        delayEfl(new SeqSegment() {
////          @Override
////          protected void execute() {
////            seqEflButtonUnboundedNoDelay(Move.RIGHT);
////            seqMetric(new StateResettingMetric() {
////              @Override
////              public int getMetricInternal() {
////                int add = EflUtil.runToAddressLimit(0, 0, 100, curGb.pokemon.doWalkPreEncounterCheckAddress);
////                if(add == 0) {
////                  System.out.println("ERROR: didn't find doWalkPreEncounterCheckAddress");
////                  return 0;
////                } else {
////                  add = EflUtil.runToAddressLimit(0, 0, 100, curGb.pokemon.doWalkPostEncounterCheckAddress, curGb.pokemon.encounterCheckMainFuncEncounterAddress);
////                  if(add == 0) {
////                    System.out.println("ERROR: didn't find doWalkPostEncounterCheckAddress or encounterCheckMainFuncEncounterAddress");
////                    return 0;
////                  }
////                  if(add == curGb.pokemon.doWalkPostEncounterCheckAddress) {
////                    return 1;
////                  }
////                  return 0;
////                }
////              }
////            });
////          }
////        });
////      }
//
//    seq(new EflWalkToSegment(13, 143)); // ledge
//    seq(new EflWalkToSegment(34, 8, false)); // enter house
//    seq(new EflWalkToSegment(8, 4, false)); // leave house
//    seq(new EflUseBikeSegment().fromOverworld());
//    seq(new EflWalkToSegment(50, 8)); // Fuchsia
//
//    save("pst2");
//    load("pst2");
//
//    seqMetric(new OutputParty());
//
//    seqMetric(new OutputItems());
//    seqMetric(new OutputBoxMons());
//
//    seq(new EflWalkToSegment(5, 27)); // enter gym
//    seq(new EflWalkToSegment(7, 8, false)); // engage
//    seqMove(new EflOverworldInteract(3));
//    seq(new EflInitFightSegment(2)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckDisableEffectMisses(), DISABLE)};
//      kems.attackCount[1][1] = 1; // Ice Beam crit
//      kems.attackCount[0][1] = 1; // Take Down crit
//      seq(kems); // drowzee
//    }
//    save("tmp");
//    load("tmp");
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckDisableEffectMisses(), DISABLE)};
//      kems.attackCount[1][1] = 1; // Ice Beam crit
//      kems.attackCount[0][1] = 1; // Take Down crit
//      kems.numExpGainers = 2; // dratini, lvlup to 28
//      seq(kems); // drowzee
//    }
//    save("tmp2");
//    load("tmp2");
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckDisableEffectMisses(), DISABLE)};
//      kems.attackCount[0][1] = 1; // Take Down crit
//      seq(kems); // kadabra
//    }
//    save("tmp3");
//    load("tmp3");
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckDisableEffectMisses(), DISABLE)};
//      kems.attackCount[1][1] = 1; // Ice Beam crit
//      kems.attackCount[0][1] = 1; // Take Down crit
//      seq(kems); // drowzee
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//
//    save("pst3");
//    load("pst3");
//
//    seq(new EflSelectItemSegment(SUPER_POTION).fromOverworld().andUse());
//    seq(new EflSelectMonSegment(DRATINI));
//    seqEflButton(B, PRESSED); // restored
//    seqEflButton(B, PRESSED); // cancel
//    seqEflButton(START); // cancel
//
//    seq(new EflWalkToSegment(1, 7)); // engage
//
//    seq(new EflInitFightSegment(3)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckPoisonEffectMisses(), POISON_GAS)};
//      kems.attackCount[0][1] = 2; // Take Down crit
//      seq(kems); // drowzee
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckDisableEffectMisses(), DISABLE)};
//      kems.attackCount[0][1] = 3; // Take Down crit
//      kems.numExpGainers = 2; // dratini, lvlup to 29
//      seq(kems); // hypno
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//
//    save("pst4");
//    load("pst4");
//
//    seqMetric(new OutputParty());
//
//    seq(new EflSelectItemSegment(SUPER_POTION).fromOverworld().andUse());
//    seq(new EflSelectMonSegment(DRATINI));
//    seqEflButton(B, PRESSED); // restored
//    seqEflButton(B, PRESSED); // cancel
//    seq(new EflSelectMonSegment(DRATINI).fromMainMenu().andSwitchWith(BULBASAUR));
//    seqEflButton(B);
//    seqEflButton(START);
//
//    seq(new EflWalkToSegment(4, 9)); // engage koga
//    seq(new EflWalkToSegment(4, 10, false)); // engage koga
//    seqMove(new EflOverworldInteract(1));
//    seq(new EflInitFightSegment(9)); // start fight
//    seq(new EflSwitchPokemonSegment(DRATINI, EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), SMOKESCREEN)));
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), SMOKESCREEN)};
//      kems.attackCount[3][0] = 2; // thunderbolt
//      kems.attackCount[3][1] = 1; // thunderbolt crit
//      kems.numExpGainers = 4; // bulbasaur, boosted; lvl11, dratini
//      seq(kems); // koffing
//    }
//
//    save("tmp");
//    load("tmp");
//
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckDisableEffectMisses(), DISABLE)};
//      kems.attackCount[0][1] = 3; // Take Down crit
//      kems.attackCount[3][1] = 1; // Thunderbolt crit
//      seq(kems); // muk
//    }
//
//    save("tmp2");
//    load("tmp2");
//
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    seq(new EflSwitchPokemonSegment(BULBASAUR, EflEnemyMoveDesc.missWith(SMOG, SLUDGE)));
//    seq(new EflSwitchPokemonSegment(DRATINI, EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), SMOKESCREEN)));
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), SMOKESCREEN)};
//      kems.attackCount[1][0] = 2; // ice beam
//      kems.attackCount[1][1] = 1; // ice beam crit
//      kems.numExpGainers = 6; // bulbasaur, boosted; lvl13, learned vine whip, dratini, lvl30
//      seq(kems); // koffing
//    }
//
//    save("pst5");
//    load("pst5");
//
//    seq(new EflOverrideMoveSegment(0)); // take down -> slam
//
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    seq(new EflSwitchPokemonSegment(BULBASAUR, EflEnemyMoveDesc.missWith(SMOG, SLUDGE)));
//    save("tmp");
//    load("tmp");
//    seq(new EflSwitchPokemonSegment(DRATINI, EflEnemyMoveDesc.missWith(SELF_DESTRUCT)));
//    save("tmp2");
//    load("tmp2");
//    seq(new EflSkipTextsSegment(5)); // fainted, got xp, bulbasaur, boosted xp, lvl to 16
//
//    seq(new EflEndFightSegment(3)); // player defeated enemy
//    seq(new EflEvolutionSegment()); // Ivysaur
//    seq(new EflEvolutionSegment()); // Dragonair
//    seq(new EflSkipTextsSegment(11)); // after battle texts
//    seq(new EflWalkToSegment(5, 16, false)); // leave gym
//    seq(new EflWalkToSegment(5, 18, false)); // leave gym
//
//    save("pst6");
    load("pst6");

    seq(new EflUseBikeSegment().fromOverworld());
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
//      seq(new EflDepositMonSegment(BULBASAUR));
//      seq(new EflDepositMonSegment(PIDGEY));
      seq(new EflDepositMonSegment(PIKACHU));
      seq(new EflDepositMonSegment(EKANS));
//      seq(new EflWithdrawMonSegment(CUBONE));
//      seq(new EflWithdrawMonSegment(GASTLY));
      seq(new EflWithdrawMonSegment(BELLSPROUT));
      seq(new EflWithdrawMonSegment(VULPIX));
      seqEflButton(B, MENU); // cancel
      seqEflButton(B, MENU); // cancel
    }

    seq(new EflWalkToSegment(11, 4)); // cable club
    seq(new EflWalkToSegment(11, 3)); // cable club
  }
}
