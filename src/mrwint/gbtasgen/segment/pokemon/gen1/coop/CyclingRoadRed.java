package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import static mrwint.gbtasgen.move.Move.A;
import static mrwint.gbtasgen.move.Move.B;
import static mrwint.gbtasgen.move.Move.DOWN;
import static mrwint.gbtasgen.move.Move.LEFT;
import static mrwint.gbtasgen.move.Move.RIGHT;
import static mrwint.gbtasgen.move.Move.START;
import static mrwint.gbtasgen.move.Move.UP;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.BELLSPROUT;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.BUTTERFREE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.CUBONE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.FARFETCHD;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.GASTLY;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.METAPOD;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.ODDISH;
import static mrwint.gbtasgen.state.Gameboy.curGb;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.MENU;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.PRESSED;
import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.metric.pokemon.CheckEncounterMetric;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckDisableEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckPoisonEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputBoxMons;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputItems;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputParty;
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
import mrwint.gbtasgen.segment.pokemon.fight.EflSwitchPokemonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment.EflEnemyMoveDesc;
import mrwint.gbtasgen.segment.pokemon.fight.EflNewEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.Constants;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflBuyItemSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflCancelMoveLearnSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflChangeMonBoxSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflDepositMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflEncounterSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflFishSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSellItemSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflUseBikeSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflWithdrawMonSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.util.EflUtil;

public class CyclingRoadRed extends SeqSegment {

	@Override
	public void execute() {
    seq(new EflWalkToSegment(3, 5)); // enter center

    seqMetric(new OutputParty());
    seqMetric(new OutputBoxMons());
    seq(new EflWalkToSegment(13, 5));
    seq(new EflWalkToSegment(13, 4)); // TODO: optimize
    {
      seqEflButton(A); // PC
      seq(new EflSkipTextsSegment(1)); // turned on
      seqEflButton(A); // someone's PC
      seq(new EflSkipTextsSegment(2)); // accessed, mon storage
      seqMetric(new OutputParty());
      seqMetric(new OutputBoxMons());
//      seq(new EflDepositMonSegment(FARFETCHD));
      seq(new EflDepositMonSegment(BELLSPROUT));
      seq(new EflDepositMonSegment(METAPOD));
      seq(new EflWithdrawMonSegment(CUBONE));
//      seq(new EflWithdrawMonSegment(GASTLY));
      seq(new EflWithdrawMonSegment(ODDISH));
      seqEflButtonUnboundedNoDelay(B, MENU); // cancel
      seqEflButtonUnboundedNoDelay(B, MENU); // cancel
      seqMetric(new OutputParty());
      seqMetric(new OutputBoxMons());
      seqMetric(new OutputItems());
    }
//    seqEflSkipInput(1);
//    seq(new EflSelectItemSegment(THUNDER_STONE).fromOverworld().andUse());
//    seq(new EflSelectMonSegment(EEVEE));
//    seq(new EflEvolutionSegment()); // Jolteon
//    seq(new EflSelectItemSegment(HM03).andUse());
//    seq(new EflLearnTMSegment(OMANYTE));
//    seqEflButton(B);
//    seqEflButton(START);
    seq(new EflWalkToSegment(11, 4)); // cable club
    seq(new EflWalkToSegment(11, 3)); // cable club


//    seq(new EflWalkToSegment(15, 13)); // enter mart
//    seq(new EflWalkToSegment(3, 5)); // shopkeep
//    seq(new EflWalkToSegment(2, 5)); // shopkeep
//    seqMove(new EflOverworldInteract(1));
//    {
//      seq(new EflSkipTextsSegment(1, true)); // buy
//      seq(new EflTextSegment(Move.B));
//      seq(new EflBuyItemSegment(3, 6, true)); // Escape Rope x6
//      seqEflButton(Move.B); // cancel
//      seq(new EflSkipTextsSegment(2)); // cancel + bye
//    }
//    seq(new EflWalkToSegment(3, 6)); // leave mart
//    seq(new EflWalkToSegment(3, 8, false)); // leave mart
//
//	  seq(new EflUseBikeSegment(2, 1));
//    seq(new EflWalkToSegment(9, 18)); // leave
//    seq(new EflWalkToSegment(10, 16, false)); // house
//    seq(new EflWalkToSegment(4, 8, false)); // leave house
//    seq(new EflUseBikeSegment(0, 0));
//
//    seq(new EflWalkToSegment(14, 30)); // fisherman
//    seqMove(new EflOverworldInteract(2)); // engage
//    seq(new EflInitFightSegment(1)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[1][1] = 1; // Bite crit
//      kems.numExpGainers = 2; // boosted;
//      seq(kems); // goldeen
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[1][1] = 1; // Bite crit
//      kems.numExpGainers = 2; // boosted;
//      seq(kems); // poliwag
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[1][1] = 1; // Bite crit
//      kems.numExpGainers = 2; // boosted;
//      seq(kems); // goldeen
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//
//    save("cr1");
//    load("cr1");
//
//    seq(new EflWalkToSegment(10, 61)); // snorlax
//    {
//      seqMetric(new OutputItems());
//      seqEflButton(Move.START, PRESSED);
//      seqEflScrollA(2); // items
//      seqEflScrollFastAF(11+1); // poke flute
//      seqEflButton(Move.A, PRESSED); // use
//    }
//    seq(new EflSkipTextsSegment(1)); // playing flute
//    seq(new EflSkipTextsSegment(2)); // snorlax attacks
//    seq(new EflSkipTextsSegment(2)); // wild mon, go mon
//    seqEflButton(Move.RIGHT); // mon
//    seqEflButton(Move.DOWN | Move.A); // run
//    seq(new EflSkipTextsSegment(1)); // ran away
//
//    seq(new EflWalkToSegment(13, 73)); // rocker
//    seq(new EflWalkToSegment(14, 73)); // rocker
//
//    seq(new EflInitFightSegment(1)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 103)}; // Screech
//      kems.attackCount[3][1] = 1; // Bubblebeam crit
//      kems.numExpGainers = 2; // boosted;
//      seq(kems); // Voltorb
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 103)}; // Screech
////      kems.attackCount[2][1] = 1; // Bubble crit // if PP needed
//      kems.attackCount[3][0] = 1; // Bubblebeam
//      kems.attackCount[3][1] = 1; // Bubblebeam crit
//      kems.numExpGainers = 3; // boosted; lvlup to 34
//      seq(kems); // Electrode
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//
//    save("cr2");
//    load("cr2");
//
//    seq(new EflWalkToSegment(11, 77)); // enter fishing house
//    seq(new EflWalkToSegment(2, 5)); // fisher
//    seqMove(new EflOverworldInteract(1));
//    seq(new EflSkipTextsSegment(2));
//    seq(new EflSkipTextsSegment(1, true)); // I like to fish
//    seq(new EflSkipTextsSegment(7)); // got super rod
//    seq(new EflWalkToSegment(2, 8, false)); // leave
//    {
//      seqMetric(new OutputParty());
//      seqEflButton(START, PRESSED);
//      seqEflScrollA(1); // mon
//      seqEflButton(A, PRESSED); // Wartortle
//      seqEflSkipInput(1);
//      seqEflScrollAF(1); // switch
//      seqEflSkipInput(1);
//      seqEflScrollAF(2); // Charmeleon
//      seqEflSkipInput(1);
//      seqEflScrollAF(2); // Farfetch'd
//      seqEflSkipInput(1);
//      seqEflScrollAF(1); // Fly
//      seqEflSkipInput(1);
//      seqEflScrollA(1); // Celadon
//      seqEflSkipInput(1);
//    }
//    seq(new EflUseBikeSegment(1, 1));
//
//    seq(new EflWalkToSegment(24, 22)); // water
//    {
//      seq(new EflFishSegment(0x6E, 12+1, 0)); // Poliwhirl
//      seq(new EflCatchMonSegment(0));
//      seqMetric(new OutputItems());
//      seq(new EflFishSegment(0x6E, 12+2, 2)); // Poliwhirl
//      seq(new EflCatchMonSegment(0).noNew());
//    }
//
//    seq(new EflWalkToSegment(35, 30)); // bush
//    seq(new EflWalkToSegment(35, 31)); // bush
//    {
//      seqEflButton(START, PRESSED);
//      seqEflScrollA(1); // mon
//      seqEflScrollAF(-2); // Farfetch'd
//      seqEflButton(A, PRESSED); // cut
//      seqEflButton(Move.B); // hacked away (no text scroll)?
//    }
//    seq(new EflWalkToSegment(12, 27)); // enter gym
//    seq(new EflWalkToSegment(1, 4)); // bush
//    {
//      seqEflButton(START, PRESSED);
//      seqEflButton(A, PRESSED); // mon
//      seqEflButton(A, PRESSED); // Farfetch'd
//      seqEflButton(A, PRESSED); // cut
//      seqEflButton(Move.B); // hacked away (no text scroll)?
//    }
//    seq(new EflWalkToSegment(3, 4)); // engage
//    seq(new EflInitFightSegment(2)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[2][1] = 1; // ember crit
//      kems.numExpGainers = 2; // boosted
//      seq(kems); // exeggcute
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//
//    save("cr3");
//    load("cr3");
//
//    seq(new EflWalkToSegment(4, 3, false)); // engage erika
//    seqMove(new EflOverworldInteract(1)); // engage erika
//    seq(new EflInitFightSegment(13)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckPoisonEffectMisses(), 77)}; // Poisonpowder
//      kems.attackCount[2][0] = 1; // ember
//      kems.attackCount[2][1] = 1; // ember crit
//      kems.numExpGainers = 3; // boosted; lvlup to 35
//      seq(kems); // victreebel
//    }
//    save("tmp");
//    load("tmp");
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(20)}; // Bind
//      kems.attackCount[2][1] = 1; // ember crit
//      kems.numExpGainers = 2; // boosted
//      seq(kems); // tangela
//    }
//    save("tmp2");
//    load("tmp2");
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    seq(new EflSwitchPokemonSegment(2, EflEnemyMoveDesc.missWith(72))); // Wartortle
//    save("tmp3");
//    load("tmp3");
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(72)}; // Mega Drain
////      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckPoisonEffectMisses(), 77)}; // Poisonpowder
//      kems.attackCount[0][1] = 1; // mega punch crit
//      kems.attackCount[1][1] = 1; // bite crit
//      kems.lastAttack = 0; // mega punch
////      kems.attackCount[2][0] = 1; // ember
////      kems.attackCount[2][1] = 1; // ember crit
//      kems.numExpGainers = 4; // boosted; Charmeleon boosted
//      seq(kems); // vileplume
//    }
//    seq(new EflEndFightSegment(3)); // player defeated enemy
//
//    save("cr4");
//    load("cr4");
//
//    seq(new EflSkipTextsSegment(11)); // after battle texts
//    seq(new EflWalkToSegment(5, 5)); // bush
//    seq(new EflWalkToSegment(5, 6)); // bush
//    {
//      seqEflButton(START, PRESSED);
//      seqEflScrollA(1); // mon
//      seqEflScrollAF(-2); // Farfetch'd
//      seqEflButton(A, PRESSED); // cut
//      seqEflButton(B); // hacked away (no text scroll)?
//    }
//    seq(new EflWalkToSegment(5, 18, false)); // leave gym
//    {
//      seqEflButton(START, PRESSED);
//      seqEflButton(A, PRESSED); // mon
//      seqEflButton(A, PRESSED); // Farfetch'd
//      seqEflSkipInput(1);
//      seqEflScrollAF(1); // Fly
//      seqEflScrollA(-3); // Cerulean
//      seqEflSkipInput(1);
//    }
//
//    seq(new EflWalkToSegment(19, 17)); // center
//    seq(new EflWalkToSegment(13, 4)); // pc
//    seq(new EflWalkToSegment(13, 3, false)); // pc // TODO: optimize
//    save("tmp");
//    load("tmp");
//    {
//      seqEflButton(A); // use PC
//      seq(new EflSkipTextsSegment(1)); // turned on
//      seqEflButton(A); // someone's PC
//      seq(new EflSkipTextsSegment(2)); // accessed, mon storage
//      seqMetric(new OutputParty());
//      seqMetric(new OutputBoxMons());
//      seq(new EflDepositMonSegment(1, 3+2)); // Butterfree
//      seq(new EflWithdrawMonSegment(-1, 0)); // Poliwhirl
//      seqEflButton(B, MENU); // cancel
//      seqEflSkipInput(1);
//      seqEflButton(B); // cancel
//      seqMetric(new OutputParty());
//    }
//
//    seq(new EflWalkToSegment(4, 6)); // leave
//    seq(new EflWalkToSegment(4, 8, false)); // leave
//
//    seq(new EflWalkToSegment(13, 15)); // house
//
//    seq(new EflWalkToSegment(1, 4)); // trade
//    seq(new EflWalkToSegment(1, 3)); // trade
//    seqMove(new EflOverworldInteract(2));
//    seq(new EflSkipTextsSegment(1));
//    seq(new EflSkipTextsSegment(1, true)); // trade
//    seqEflSkipInput(1);
//    seqEflScrollAF(-1); // Poliwhirl
//    seq(new EflSkipTextsSegment(1)); // connect cables
//    seq(new EflSkipTextsSegment(3)); // traded x for y, thanks
//    seq(new EflWalkToSegment(2, 6)); // leave
//    seq(new EflWalkToSegment(2, 8, false)); // leave
//
//    save("cr5");
//    load("cr5");
//
//    {
//      seqEflButton(START, PRESSED);
//      seqEflButton(A, PRESSED); // mon
//      seqEflScrollAF(-2); // Farfetch'd
//      seqEflSkipInput(1);
//      seqEflScrollAF(1); // Fly
//      seqEflSkipInput(1);
//      seqEflScrollA(1); // Celadon
//      seqEflSkipInput(1);
//    }
//    seq(new EflUseBikeSegment(1, 1));
//    seqMetric(new OutputItems());
//    seq(new EflWalkToSegment(10, 13)); // enter mart
//    seq(new EflWalkToSegment(12, 1)); // 2nd floor
//    seq(new EflWalkToSegment(16, 1)); // 3rd floor
//    seq(new EflWalkToSegment(12, 1)); // 4th floor
//    seq(new EflWalkToSegment(16, 1)); // 5th floor
//    {
//      seq(new EflWalkToSegment(5, 4, false)); // left cashier
//      seqMove(new EflOverworldInteract(3));
//      seq(new EflSkipTextsSegment(1, true)); // buy
//      seq(new EflTextSegment());
//      {
//        seq(new EflBuyItemSegment(5, 4, true)); // X Speed x4
//      }
//      seqEflButton(B); // cancel
//      seq(new EflSkipTextsSegment(2)); // cancel + bye
//    }
//    seq(new EflWalkToSegment(16,1)); // 4th
//
//    {
//      seq(new EflWalkToSegment(5, 6, false)); // left cashier
//      seqMove(new EflOverworldInteract(1));
//      {
//        seq(new EflTextSegment(B));
//        seqEflButton(DOWN | A); // sell
//        seq(new EflTextSegment(B)); // what to sell
//
//        seq(new EflSellItemSegment(5, 0)); // TM34
//        seq(new EflSellItemSegment(6, 0)); // Nugget
//        seq(new EflSellItemSegment(12, 0)); // TM21
//        seqEflButton(B);
//      }
//
//
//      seq(new EflSkipTextsSegment(1, true)); // buy
//      seq(new EflTextSegment(B));
//      {
//        seq(new EflBuyItemSegment(4, 3)); // Leaf Stone x3
//        seq(new EflBuyItemSegment(0, 1)); // Thunder Stone x1
//        seq(new EflBuyItemSegment(1, 4, true)); // Water Stone x3
//      }
//      seqEflButton(B); // cancel
//      seq(new EflSkipTextsSegment(2)); // cancel + bye
//    }
//    seq(new EflWalkToSegment(12,1)); // 3rd
//    seq(new EflWalkToSegment(16,1)); // 2nd
//    {
//      seq(new EflWalkToSegment(9,3)); // right cashier
//      seq(new EflWalkToSegment(8,3)); // right cashier
//      seqMove(new EflOverworldInteract(2));
//      seq(new EflSkipTextsSegment(1, true)); // buy
//      seq(new EflTextSegment());
//      {
//        seq(new EflBuyItemSegment(3, 1, true)); // TM07
//      }
//      seqEflButton(B); // cancel
//      seq(new EflSkipTextsSegment(2)); // cancel + bye
//    }
//    seq(new EflWalkToSegment(12,1)); // 1st
//    seq(new EflWalkToSegment(16,8,false)); // out
//    seqMetric(new OutputItems());
//
//    seq(new EflUseBikeSegment(0, 1));
//    seq(new EflWalkToSegment(-1, 18)); // leave celadon
//    seq(new EflWalkToSegment(27, 10)); // Snorlax
//    {
//      seqEflButton(Move.START, PRESSED);
//      seqEflButton(A, PRESSED); // items
//      seqEflScrollFastAF(8+1); // poke flute
//      seqEflButton(Move.A, PRESSED); // use
//    }
//    seq(new EflSkipTextsSegment(1)); // playing flute
//    seq(new EflSkipTextsSegment(2)); // snorlax attacks
//    seq(new EflCatchMonSegment(0));
//    seq(new EflWalkToSegment(23, 10, false)); // enter house
//    seq(new EflWalkToSegment(-1, 8, false)); // leave house
//
//    save("cr6");
//    load("cr6");
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
//
//    seq(new EflWalkToSegment(13, 143)); // ledge
//    seq(new EflWalkToSegment(34, 8, false)); // enter house
//    seq(new EflWalkToSegment(8, 4, false)); // leave house
//
//	  save("cr7");
//	  load("cr7");
//
//    seqUnbounded(new EflUseBikeSegment(2, 1));
//    {
//      seqUnbounded(new EflWalkToSegment(41, 11)); // grass
//      seq(new EflEncounterSegment(0x23, DOWN)); // Fearow
//      save("tmp");
//      load("tmp");
//      seq(new EflCatchMonSegment(0).withBufferSize(0));
//
//      seqUnbounded(new EflWalkToSegment(39, 12)); // align
//      seq(new EflEncounterSegment(0x46, LEFT)); // Doduo
//      save("tmp2");
//      load("tmp2");
//      seq(new EflCatchMonSegment(0).withBufferSize(0).withExtraSkips(20));
//
//      seqUnbounded(new EflWalkToSegment(40, 12)); // align
//      seq(new EflEncounterSegment(0xA6, RIGHT)); // Raticate
//      seq(new EflCatchMonSegment(0));
//    }
//    seq(new EflWalkToSegment(50, 8)); // Fuchsia
//    seq(new EflWalkToSegment(19, 27)); // enter Center
//
//    seq(new EflWalkToSegment(11, 4)); // cable club
//    seq(new EflWalkToSegment(11, 3)); // cable club
  }
}
