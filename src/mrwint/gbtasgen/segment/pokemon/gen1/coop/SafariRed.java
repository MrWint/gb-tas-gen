package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import static mrwint.gbtasgen.move.Move.A;
import static mrwint.gbtasgen.move.Move.B;
import static mrwint.gbtasgen.move.Move.DOWN;
import static mrwint.gbtasgen.move.Move.LEFT;
import static mrwint.gbtasgen.move.Move.RIGHT;
import static mrwint.gbtasgen.move.Move.START;
import static mrwint.gbtasgen.move.Move.UP;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.BELLSPROUT;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.BIND;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.CHANSEY;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DISABLE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DODUO;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DRATINI;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.ESCAPE_ROPE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.EXEGGCUTE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.FARFETCHD;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.KANGASKHAN;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.MAGMAR;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.NIDORANF;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.NIDORANM;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.NIDORINA;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.NIDORINO;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.OMANYTE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PARAS;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PARASECT;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.POISON_GAS;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.POISON_POWDER;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.POKE_FLUTE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.POLIWHIRL;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.RAZOR_LEAF;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.RHYHORN;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SCYTHER;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SELF_DESTRUCT;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SMOKESCREEN;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TAUROS;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TM06;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TM13;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TM21;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.VENOMOTH;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.VENONAT;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.WARTORTLE;
import static mrwint.gbtasgen.state.Gameboy.curGb;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.MENU;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.PRESSED;
import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckDisableEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckNoAIMoveNew;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckPoisonEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputBoxMons;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputItems;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputParty;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.pokemon.gen1.EflOverworldInteract;
import mrwint.gbtasgen.move.pokemon.gen1.EflWalkStep;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.EflCatchMonSegment;
import mrwint.gbtasgen.segment.pokemon.EflCatchSafariMonSegment;
import mrwint.gbtasgen.segment.pokemon.EflEvolutionSegment;
import mrwint.gbtasgen.segment.pokemon.EflLearnTMSegment;
import mrwint.gbtasgen.segment.pokemon.EflOverrideMoveSegment;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.pokemon.EflWalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflCheckMoveOrderMetric;
import mrwint.gbtasgen.segment.pokemon.fight.EflEndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflInitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment.EflEnemyMoveDesc;
import mrwint.gbtasgen.segment.pokemon.fight.EflNewEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflSwitchPokemonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.Constants;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflBuyItemSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflCancelMoveLearnSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflChangeMonBoxSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflDepositMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflEncounterSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflFishSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSelectItemSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSelectMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSellItemSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflUseBikeSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflWithdrawMonSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.state.Gameboy;
import mrwint.gbtasgen.state.StateBuffer;
import mrwint.gbtasgen.util.EflUtil;

public class SafariRed extends SeqSegment {

	@Override
	public void execute() {
//    seqEflButton(A); // continue game
//    seqEflButton(START);
//    seqEflButton(A);
//    seqEflButton(START);
//    seqEflButton(A);
//
//    {
//      seq(new EflWalkToSegment(13, 4)); // PC
//      seq(new EflWalkToSegment(13, 3, false)); // PC
//      seqMetric(new OutputItems());
//      seqMetric(new OutputParty());
//      seqMetric(new OutputBoxMons());
//      {
//        seqEflButton(A); // use PC
//        seq(new EflSkipTextsSegment(1)); // turned on
//        seqEflButton(A); // someone's PC
//        seq(new EflSkipTextsSegment(2)); // accessed, mon storage
//        seq(new EflChangeMonBoxSegment(1)); // box 2
//        seqEflButton(B, MENU); // cancel
//        seqEflButton(B, MENU); // cancel
//        seqMetric(new OutputParty());
//        seqMetric(new OutputBoxMons());
//      }
//    }
//
//    seq(new EflWalkToSegment(4, 6)); // leave center
//    seq(new EflWalkToSegment(4, 8, false)); // leave center
//
//    seq(new EflSelectMonSegment(FARFETCHD).fromOverworld().andSwitchWith(OMANYTE));
//    seq(new EflSelectMonSegment(FARFETCHD).andFlyTo(1)); // Celadon
//    seqEflSkipInput(1);
//    seq(new EflUseBikeSegment().fromOverworld());
//    seq(new EflWalkToSegment(10, 13)); // mart
//
//    save("sa1");
//    load("sa1");
//
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
//    {
//      seq(new EflWalkToSegment(5, 4, false)); // left cashier
//      seqMove(new EflOverworldInteract(3));
//      seq(new EflSkipTextsSegment(1, true)); // buy
//      seq(new EflTextSegment());
//      {
//        seq(new EflBuyItemSegment(5, 1, true)); // X Speed x1
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
//        seqMetric(new OutputItems());
//
//        seq(new EflSellItemSegment(4, 0)); // TM34 x1
//        seq(new EflSellItemSegment(6, 0, true)); // Nugget x3
//        seqEflButton(B);
//      }
//
//
//      seq(new EflSkipTextsSegment(1, true)); // buy
//      seq(new EflTextSegment(B));
//      {
//        seq(new EflBuyItemSegment(4, 3)); // Leaf Stone x3
//        seq(new EflBuyItemSegment(0, 1)); // Thunder Stone x1
//        seq(new EflBuyItemSegment(1, 4)); // Water Stone x3
//        seq(new EflBuyItemSegment(-1, 3, true)); // Fire Stone x3
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
//
//    save("sa2");
//    load("sa2");
//
//    seqMetric(new OutputItems());
//    seqMetric(new OutputParty());
//    seq(new EflSelectItemSegment(TM13).fromOverworld().andUse());
//    seq(new EflLearnTMSegment(OMANYTE));
//    seqEflSkipInput(0);
//    seq(new EflUseBikeSegment());
//
//    seq(new EflWalkToSegment(-1, 18)); // leave celadon
//    seq(new EflWalkToSegment(27, 10)); // Snorlax
//    seq(new EflSelectItemSegment(POKE_FLUTE).fromOverworld().andUse());
//    seq(new EflSkipTextsSegment(1)); // playing flute
//    seq(new EflSkipTextsSegment(2)); // snorlax attacks
//    seq(new EflCatchMonSegment());
//    seq(new EflWalkToSegment(23, 10, false)); // enter house
//    seq(new EflWalkToSegment(-1, 8, false)); // leave house
//
//    save("sa3");
//    load("sa3");
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
//    seq(new EflUseBikeSegment().fromOverworld());
//    seq(new EflWalkToSegment(50, 8)); // Fuchsia
//
//    save("sa4");
//    load("sa4");
//
//    seq(new EflWalkToSegment(5, 27)); // enter gym
//    seq(new EflWalkToSegment(7, 8, false)); // engage
//    seqMove(new EflOverworldInteract(3));
//    seq(new EflInitFightSegment(2)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckDisableEffectMisses(), 50)}; // disable
//      kems.attackCount[2][0] = 2; // surf
//      kems.numExpGainers = 2; // omanyte, boosted
//      seq(kems); // drowzee
//    }
//    save("tmp");
//    load("tmp");
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckDisableEffectMisses(), 50)}; // disable
//      kems.attackCount[2][0] = 2; // surf
//      kems.numExpGainers = 2; // omanyte, boosted
//      seq(kems); // drowzee
//    }
//    save("tmp2");
//    load("tmp2");
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckDisableEffectMisses(), 50)}; // disable
//      kems.attackCount[2][0] = 1; // surf
//      kems.attackCount[2][1] = 1; // surf
//      kems.numExpGainers = 3; // omanyte, boosted, lvlup to 31
//      seq(kems); // kadabra
//    }
//    save("tmp3");
//    load("tmp3");
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckDisableEffectMisses(), 50)}; // disable
//      kems.attackCount[2][0] = 2; // surf
//      kems.numExpGainers = 2; // omanyte, boosted
//      seq(kems); // drowzee
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//
//    save("sa5");
//    load("sa5");
//
////    seqMetric(new OutputParty());
////    seq(new EflSelectMonSegment(WARTORTLE).fromOverworld().andSwitchWith(OMANYTE));
////    seqEflButton(B);
////    seq(new EflSelectItemSegment(TM13).fromMainMenu().andUse());
////    seq(new EflLearnTMSegment(OMANYTE));
////    seqEflButton(B);
////    seqEflButton(START);
//
//    seq(new EflWalkToSegment(1, 7)); // engage
//    seq(new EflInitFightSegment(3)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckPoisonEffectMisses(), 139)}; // poison gas
//      kems.attackCount[3][1] = 2; // ice beam crit
//      kems.numExpGainers = 2; // omanyte, boosted
//      seq(kems); // drowzee
//    }
//    save("tmp");
//    load("tmp");
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckDisableEffectMisses(), 50)}; // disable
//      kems.attackCount[2][1] = 2; // surf crit
//      kems.numExpGainers = 3; // omanyte, boosted, lvlup to 32
//      seq(kems); // hypno
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//
//    save("sa6");
//    load("sa6");
//
//    seq(new EflWalkToSegment(4, 9)); // engage koga
//    seq(new EflWalkToSegment(4, 10, false)); // engage koga
//    seqMove(new EflOverworldInteract(1));
//    seq(new EflInitFightSegment(9)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), SMOKESCREEN)};
//      kems.attackCount[2][1] = 1; // surf crit
//      kems.numExpGainers = 2; // omanyte, boosted
//      seq(kems); // koffing
//    }
//    save("tmp");
//    load("tmp");
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckPoisonEffectMisses(), POISON_GAS)};
////      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckDisableEffectMisses(), DISABLE)};
//      kems.attackCount[2][1] = 1; // surf crit
//      kems.attackCount[3][1] = 1; // ice beam crit
//      kems.numExpGainers = 3; // omanyte, boosted, lvlup to 33
//      seq(kems); // muk
//    }
//
//    save("tmp2");
//    load("tmp2");
//
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), SMOKESCREEN)};
//      kems.attackCount[2][1] = 1; // surf crit
//      kems.numExpGainers = 2; // omanyte, boosted
//      seq(kems); // koffing
//    }
//
//    save("sa7");
//    load("sa7");
//
//    seq(EflNewEnemyMonSegment.any()); // next mon
//
//    {
//      EflEnemyMoveDesc enemyMoveDesc = EflEnemyMoveDesc.missWith(SELF_DESTRUCT);
//
//      seqEflButton(A); // Fight
//      seqEflSkipInput(1);
//      delayEfl(new SeqSegment() {
//        @Override
//        protected void execute() {
//          seqEflButtonUnbounded(Move.A); // any move
//          seqMetric(new EflCheckMoveOrderMetric(false, enemyMoveDesc.move));
//          seqMetric(new CheckNoAIMoveNew());
//          seq(new Segment() {
//            @Override
//            public StateBuffer execute(StateBuffer in) {
//              return enemyMoveDesc.segment.execute(in, 0);
//            }
//          }); // use and miss
//        }
//      });
//      if (enemyMoveDesc.segment.getFinishSegment() != null)
//        seq(enemyMoveDesc.segment.getFinishSegment());
//    }
//    seq(new EflSkipTextsSegment(4)); // fainted, omanyte, boosted xp, lvl to 33
//    seq(new EflOverrideMoveSegment(1)); // Leer -> Horn Attack
//
//    seq(new EflEndFightSegment(3)); // player defeated enemy
//    seq(new EflSkipTextsSegment(11)); // after battle texts
//    seq(new EflWalkToSegment(5, 16, false)); // leave gym
//    seq(new EflWalkToSegment(5, 18, false)); // leave gym
//
//    save("sa8");
//    load("sa8");
//
//    seq(new EflSelectMonSegment(FARFETCHD).fromOverworld().andFlyTo(4)); // Lavender
//    seqEflSkipInput(1);
//    seq(new EflUseBikeSegment().fromOverworld());
//    seq(new EflWalkToSegment(9, 18)); // leave
//    seq(new EflWalkToSegment(10, 16, false)); // house
//    seq(new EflWalkToSegment(4, 8, false)); // leave house
//    seq(new EflWalkToSegment(11, 22)); // water
//    seq(new EflSelectMonSegment(OMANYTE).fromOverworld().andSurf());
//    for(int i = 0; i < 4; i++)
//      seqMove(new EflWalkStep(RIGHT, true));
//    for(int i = 0; i < 56; i++)
//      seqMove(new EflWalkStep(DOWN, true));
//    seqMove(new EflWalkStep(LEFT, true));
//    seq(new EflWalkToSegment(11, 77)); // enter fishing house
//    seq(new EflWalkToSegment(2, 5)); // fisher
//    seqMove(new EflOverworldInteract(1));
//    seq(new EflSkipTextsSegment(2));
//    seq(new EflSkipTextsSegment(1, true)); // I like to fish
//    seq(new EflSkipTextsSegment(7)); // got super rod
//    seq(new EflWalkToSegment(2, 8, false)); // leave
//    seq(new EflSelectMonSegment(FARFETCHD).fromOverworld().andFlyTo(2)); // Celadon
//    seqEflSkipInput(1);
//
////    seqMetric(new OutputItems());
//    seqMetric(new OutputParty());
//    seqMetric(new OutputBoxMons());
//
//    seq(new EflUseBikeSegment().fromOverworld());
//
//    {
//      seq(new EflWalkToSegment(24, 22)); // water
//      seq(new EflFishSegment(POLIWHIRL)); // Poliwhirl
//      seq(new EflCatchMonSegment());
//      seq(new EflFishSegment(POLIWHIRL)); // Poliwhirl
//      seq(new EflCatchMonSegment().noNew());
//    }
//
//    seq(new EflWalkToSegment(35, 30)); // bush
//    seq(new EflWalkToSegment(35, 31)); // bush
//    seq(new EflSelectMonSegment(FARFETCHD).fromOverworld().andCut());
//    seq(new EflWalkToSegment(12, 27)); // enter gym
//    seq(new EflWalkToSegment(1, 4)); // bush
//    seq(new EflSelectMonSegment(FARFETCHD).fromOverworld().andCut());
//    seq(new EflWalkToSegment(3, 4)); // engage
//    seq(new EflInitFightSegment(2)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[3][0] = 1; // Ice Beam
//      kems.numExpGainers = 2; // omanyte, boosted
//      seq(kems); // exeggcute
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//
//    save("sa9");
//    load("sa9");
//
//    seq(new EflWalkToSegment(4, 3, false)); // engage erika
//    seqMove(new EflOverworldInteract(1)); // engage erika
//    seq(new EflInitFightSegment(13)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(RAZOR_LEAF)};
////      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckPoisonEffectMisses(), POISON_POWDER)};
//      kems.attackCount[3][1] = 1; // Ice Beam
//      kems.numExpGainers = 3; // omanyte, boosted, lvlup to 35
//      seq(kems); // victreebel
//    }
//    save("tmp");
//    load("tmp");
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(BIND)};
//      kems.attackCount[3][0] = 1; // Ice Beam
//      kems.numExpGainers = 2; // omanyte, boosted
//      seq(kems); // tangela
//    }
//    save("tmp2");
//    load("tmp2");
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
////      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(72)}; // Mega Drain
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckPoisonEffectMisses(), 77)}; // Poisonpowder
//      kems.attackCount[3][1] = 1; // Ice Beam
//      kems.numExpGainers = 3; // omanyte, boosted, lvlup to 36
//      seq(kems); // vileplume
//    }
//    seq(new EflEndFightSegment(3)); // player defeated enemy
//
//    save("sa10");
//    load("sa10");
//
//    seq(new EflSkipTextsSegment(11)); // after battle texts
//    seq(new EflWalkToSegment(5, 5)); // bush
//    seq(new EflWalkToSegment(5, 6)); // bush
//    seq(new EflSelectMonSegment(FARFETCHD).fromOverworld().andCut());
//    seq(new EflWalkToSegment(5, 18, false)); // leave gym
//
//    seq(new EflSelectMonSegment(FARFETCHD).fromOverworld().andFlyTo(1)); // Fuchsia
//    seqEflSkipInput(1);
//
//    seq(new EflUseBikeSegment().fromOverworld());
//    seq(new EflWalkToSegment(23, 28, false)); // ledge
//
//    seq(new EflWalkToSegment(18, 3)); // enter safari house
//    seq(new EflWalkToSegment(3, 2)); // go pay
//    seq(new EflSkipTextsSegment(4)); // welcome to safari
//    seq(new EflSkipTextsSegment(1, true)); // yes, go on safari
//    seq(new EflSkipTextsSegment(7)); // welcome to safari
//    seqUnbounded(new EflUseBikeSegment().fromOverworld());
//
//    seqUnbounded(new EflWalkToSegment(19, 23));
//    seq(new EflEncounterSegment(SCYTHER, RIGHT));
//
//    save("tmp");
//  //  load("tmp");
//    seq(new EflCatchSafariMonSegment().withBufferSize(0));
//
//    seqUnbounded(new EflWalkToSegment(22, 23));
//    seq(new EflEncounterSegment(RHYHORN, UP));
//    save("tmp2");
//  //load("tmp2");
//    seq(new EflCatchSafariMonSegment().withBufferSize(0));
//
//    seqUnbounded(new EflWalkToSegment(23, 21));
//    seq(new EflEncounterSegment(EXEGGCUTE, UP));
//    save("tmp3");
////    load("tmp3");
//    seq(new EflCatchSafariMonSegment().withBufferSize(0));
//
//    seqUnbounded(new EflWalkToSegment(26, 20));
//    seq(new EflEncounterSegment(NIDORANM, UP));
//    save("tmp4");
//  //load("tmp4");
//    seq(new EflCatchSafariMonSegment().withBufferSize(0));
//
//    seqUnbounded(new EflWalkToSegment(26, 17));
//    seq(new EflEncounterSegment(VENONAT, UP));
//    save("tmp5");
//  //load("tmp5");
//    seq(new EflCatchSafariMonSegment().withBufferSize(0));
//
//    seqUnbounded(new EflWalkToSegment(30, 11, false)); // east
//
//    save("sa11");
//    load("sa11");
//
//    seqUnbounded(new EflWalkToSegment(13, 24));
//    seq(new EflEncounterSegment(KANGASKHAN, RIGHT));
//    save("tmp");
//  //  load("tmp");
//    seq(new EflCatchSafariMonSegment().withBufferSize(0));
//
//    seqUnbounded(new EflWalkToSegment(16, 24));
//    seq(new EflEncounterSegment(PARAS, RIGHT));
//    save("tmp2");
////    load("tmp2");
//    seq(new EflCatchSafariMonSegment().withBufferSize(0).withExtraSkips(80));
//
//    seqUnbounded(new EflWalkToSegment(19, 24));
//    seq(new EflEncounterSegment(PARASECT, RIGHT));
//    save("tmp3");
////    load("tmp3");
//    seq(new EflCatchSafariMonSegment().withBufferSize(0));
//
//    seqUnbounded(new EflWalkToSegment(9, 15));
//    seq(new EflEncounterSegment(NIDORANF, UP));
//    save("tmp4");
////    load("tmp4");
//    seq(new EflCatchSafariMonSegment().withBufferSize(0));
//
//    seqUnbounded(new EflWalkToSegment(9, 10));
//    seq(new EflEncounterSegment(DODUO, UP));
//    save("tmp5");
////    load("tmp5");
//    seq(new EflCatchSafariMonSegment().withBufferSize(0));
//
////    seqUnbounded(new EflWalkToSegment(20, 4));
//    seqUnbounded(new EflWalkToSegment(10, 3));
//    seq(new EflEncounterSegment(NIDORINO, LEFT));
//    save("tmp6");
////    load("tmp6");
//    seq(new EflCatchSafariMonSegment().withBufferSize(0));
//
//    seqUnbounded(new EflWalkToSegment(-1, 5, false)); // east
//
//    save("sa12");
////    load("sa12");
//
//    seqUnbounded(new EflWalkToSegment(24, 25));
//    seq(new EflEncounterSegment(TAUROS, UP));
//    save("tmp");
////    load("tmp");
//    seq(new EflCatchSafariMonSegment().withBufferSize(0).withExtraSkips(10));
//
//    seqUnbounded(new EflWalkToSegment(13, 27));
//    seq(new EflEncounterSegment(CHANSEY, UP));
//    save("tmp2");
//  //  load("tmp2");
//    seq(new EflCatchSafariMonSegment().withBufferSize(0));
//
//    seqUnbounded(new EflWalkToSegment(15, 12));
//    seq(new EflEncounterSegment(VENOMOTH, UP));
//    save("tmp3");
//  //  load("tmp3");
//    seq(new EflCatchSafariMonSegment().withBufferSize(0));
//
//    seqUnbounded(new EflWalkToSegment(27, 6));
//    seq(new EflEncounterSegment(NIDORINA, RIGHT));
//    save("tmp4");
////    load("tmp4");
//    seq(new EflCatchSafariMonSegment());
//
//    seq(new EflWalkToSegment(4, 12)); // water
//    seq(new EflWalkToSegment(4, 13)); // water
//    {
//      seq(new EflFishSegment(DRATINI));
//      seq(new EflCatchSafariMonSegment());
//    }
//
//    seq(new EflWalkToSegment(3, 36, false)); // east
//
//    save("sa13");
    load("sa13");

    seqMetric(new OutputItems());
    seq(new EflSelectItemSegment(TM06).fromOverworld().andToss());
    seq(new EflSelectItemSegment(TM21).andToss());
    seqEflButton(B);
    seqEflButton(START);

    seq(new EflWalkToSegment(19, 5)); // gold teeth
    seq(new EflWalkToSegment(19, 6)); // gold teeth
    seqMove(new EflOverworldInteract(4)); // pick up gold teeth
    seq(new EflTextSegment());
    seq(new EflWalkToSegment(3, 3)); // enter surf house
    seq(new EflWalkToSegment(3, 5)); // surf guy
    seq(new EflWalkToSegment(3, 4)); // surf guy
    seqMove(new EflOverworldInteract(1)); // surf guy
    seq(new EflSkipTextsSegment(8)); // get surf
    seq(new EflWalkToSegment(3, 8, false)); // leave surf house

    seq(new EflSelectItemSegment(ESCAPE_ROPE).fromOverworld().andUse());
    seqEflSkipInput(2);
  }
}
