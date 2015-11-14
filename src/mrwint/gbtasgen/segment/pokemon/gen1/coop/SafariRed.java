package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import static mrwint.gbtasgen.move.Move.A;
import static mrwint.gbtasgen.move.Move.B;
import static mrwint.gbtasgen.move.Move.LEFT;
import static mrwint.gbtasgen.move.Move.RIGHT;
import static mrwint.gbtasgen.move.Move.START;
import static mrwint.gbtasgen.move.Move.UP;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.BELLSPROUT;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.CHANSEY;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DISABLE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DODUO;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DRATINI;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.ESCAPE_ROPE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.EXEGGCUTE;
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
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.POLIWHIRL;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.RHYHORN;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SCYTHER;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SELF_DESTRUCT;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SMOKESCREEN;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TAUROS;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TM13;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.VENOMOTH;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.VENONAT;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.WARTORTLE;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.MENU;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.PRESSED;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckDisableEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckNoAIMoveNew;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckPoisonEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputBoxMons;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputItems;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputParty;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.pokemon.gen1.EflOverworldInteract;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.EflCatchMonSegment;
import mrwint.gbtasgen.segment.pokemon.EflCatchSafariMonSegment;
import mrwint.gbtasgen.segment.pokemon.EflEvolutionSegment;
import mrwint.gbtasgen.segment.pokemon.EflLearnTMSegment;
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
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflCancelMoveLearnSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflChangeMonBoxSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflDepositMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflEncounterSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflFishSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSelectItemSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSelectMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflUseBikeSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflWithdrawMonSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.state.StateBuffer;

public class SafariRed extends SeqSegment {

	@Override
	public void execute() {
//    seqEflButton(Move.A); // continue game
//    seqEflButton(Move.START);
//    seqEflButton(Move.A);
//    seqEflButton(Move.START);
//    seqEflButton(Move.A);
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
//        seq(new EflDepositMonSegment(MAGMAR));
//        seq(new EflWithdrawMonSegment(POLIWHIRL));
//        seq(new EflChangeMonBoxSegment(1));
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
//    save("sa0");
//    load("sa0");
//
//    seq(new EflSelectMonSegment(BELLSPROUT).fromOverworld().andSwitchWith(WARTORTLE));
//    seqEflButton(B); // cancel
//    seq(new EflUseBikeSegment().fromMainMenu());
//    seq(new EflWalkToSegment(5, 27)); // enter gym
//    seq(new EflWalkToSegment(7, 8, false)); // engage
//    seqMove(new EflOverworldInteract(3));
//    seq(new EflInitFightSegment(2)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckDisableEffectMisses(), 50)}; // disable
//      kems.attackCount[1][0] = 1; // bite
//      kems.attackCount[1][1] = 1; // bite crit
//      kems.numExpGainers = 2; // wartortle, boosted
//      seq(kems); // drowzee
//    }
//    save("tmp");
//    load("tmp");
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckDisableEffectMisses(), 50)}; // disable
//      kems.attackCount[1][0] = 1; // bite
//      kems.attackCount[1][1] = 1; // bite crit
//      kems.numExpGainers = 3; // wartortle, boosted, lvlup to 35
//      seq(kems); // drowzee
//    }
//    save("tmp2");
//    load("tmp2");
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckDisableEffectMisses(), 50)}; // disable
//      kems.attackCount[1][1] = 1; // bite crit
//      kems.numExpGainers = 2; // wartortle, boosted
//      seq(kems); // kadabra
//    }
//    save("tmp3");
//    load("tmp3");
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckDisableEffectMisses(), 50)}; // disable
//      kems.attackCount[0][1] = 1; // mega punch crit
//      kems.numExpGainers = 2; // wartortle, boosted
//      seq(kems); // drowzee
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//
//    save("sa1");
//    load("sa1");
//
//    seqMetric(new OutputParty());
//    seq(new EflSelectMonSegment(WARTORTLE).fromOverworld().andSwitchWith(OMANYTE));
//    seqEflButton(B);
//    seq(new EflSelectItemSegment(TM13).fromMainMenu().andUse());
//    seq(new EflLearnTMSegment(OMANYTE));
//    seqEflButton(B);
//    seqEflButton(START);
//
//    seq(new EflWalkToSegment(1, 7)); // engage
//    seq(new EflInitFightSegment(3)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckPoisonEffectMisses(), 139)}; // poison gas
//      kems.attackCount[3][0] = 1; // ice beam
//      kems.attackCount[2][1] = 1; // surf crit
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
//      kems.numExpGainers = 3; // omanyte, boosted, lvlup to 31
//      seq(kems); // hypno
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//
//    save("sa2");
//    load("sa2");
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
//      kems.numExpGainers = 3; // omanyte, boosted, lvlup to 32
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
//    save("sa3");
//    load("sa3");
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
//
//    seq(new EflEndFightSegment(3)); // player defeated enemy
//    seq(new EflSkipTextsSegment(11)); // after battle texts
//    seq(new EflWalkToSegment(5, 16, false)); // leave gym
//    seq(new EflWalkToSegment(5, 18, false)); // leave gym
//
//    save("sa4");
//    load("sa4");
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
//  //  seq(new EflEncounterSegment(0x0C, UP)); // Exeggcute
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
//    save("sa5");
//    load("sa5");
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
//    load("tmp2");
//    seq(new EflCatchSafariMonSegment().withBufferSize(0).withExtraSkips(80));
//
//    seqUnbounded(new EflWalkToSegment(19, 24));
//    seq(new EflEncounterSegment(PARASECT, RIGHT));
//    save("tmp3");
//    load("tmp3");
//    seq(new EflCatchSafariMonSegment().withBufferSize(0));
//
//    seqUnbounded(new EflWalkToSegment(9, 15));
//    seq(new EflEncounterSegment(NIDORANF, UP));
//    save("tmp4");
//    load("tmp4");
//    seq(new EflCatchSafariMonSegment().withBufferSize(0));
//
//    seqUnbounded(new EflWalkToSegment(9, 10));
//    seq(new EflEncounterSegment(DODUO, UP));
//    save("tmp5");
//    load("tmp5");
//    seq(new EflCatchSafariMonSegment().withBufferSize(0));
//
//    seqUnbounded(new EflWalkToSegment(20, 4));
////    seqUnbounded(new EflWalkToSegment(10, 3));
//    seq(new EflEncounterSegment(NIDORINO, UP));
//    save("tmp6");
//    load("tmp6");
//    seq(new EflCatchSafariMonSegment().withBufferSize(0));
//
//    seqUnbounded(new EflWalkToSegment(-1, 5, false)); // east
//
//    save("sa6");
//    load("sa6");
//
//    seqUnbounded(new EflWalkToSegment(24, 28));
//    seq(new EflEncounterSegment(TAUROS, UP));
//    save("tmp");
//    load("tmp");
//    seq(new EflCatchSafariMonSegment().withBufferSize(0).withExtraSkips(40));
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
//    load("tmp4");
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
//    save("sa7");
    load("sa7");

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
