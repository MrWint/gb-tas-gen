package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import static mrwint.gbtasgen.move.Move.A;
import static mrwint.gbtasgen.move.Move.B;
import static mrwint.gbtasgen.move.Move.DOWN;
import static mrwint.gbtasgen.move.Move.LEFT;
import static mrwint.gbtasgen.move.Move.RIGHT;
import static mrwint.gbtasgen.move.Move.START;
import static mrwint.gbtasgen.move.Move.UP;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.MENU;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.PRESSED;
import mrwint.gbtasgen.metric.pokemon.CheckEncounterMetric;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckDisableEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckNoAIMoveNew;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckPoisonEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputBoxMons;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputItems;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputParty;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.pokemon.gen1.EflOverworldInteract;
import mrwint.gbtasgen.move.pokemon.gen1.OverworldInteract;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.EflCatchMonSegment;
import mrwint.gbtasgen.segment.pokemon.EflCatchSafariMonSegment;
import mrwint.gbtasgen.segment.pokemon.EflEvolutionSegment;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.pokemon.EflWalkToSegment;
import mrwint.gbtasgen.segment.pokemon.TextSegment;
import mrwint.gbtasgen.segment.pokemon.WalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflCheckMoveOrderMetric;
import mrwint.gbtasgen.segment.pokemon.fight.EflEndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflInitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment.EflEnemyMoveDesc;
import mrwint.gbtasgen.segment.pokemon.fight.EflNewEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflSwitchPokemonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflDepositMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflEncounterSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflUseBikeSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflWithdrawMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.UseBikeSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.state.StateBuffer;
import mrwint.gbtasgen.util.EflUtil.PressMetric;

public class SafariBlue extends SeqSegment {

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
//      {
//        seqEflButton(A); // use PC
//        seq(new EflSkipTextsSegment(1)); // turned on
//        seqEflButton(A); // someone's PC
//        seq(new EflSkipTextsSegment(2)); // accessed, mon storage
//        seqMetric(new OutputParty());
//        seqMetric(new OutputBoxMons());
//        seq(new EflDepositMonSegment(1, 1)); // MrMime
//        seq(new EflDepositMonSegment(0, 2)); // Flareon
//        seq(new EflDepositMonSegment(0, 3+1)); // Oddish
//        seq(new EflWithdrawMonSegment(-1, 9+1)); // BULBASAUR
//        seq(new EflWithdrawMonSegment(0, 9+1)); // PIDGEY
//        seq(new EflWithdrawMonSegment(0, 10+1)); // EKANS
//        seqEflButton(DOWN, MENU);
//        seqEflSkipInput(1);
//        seqEflButton(DOWN);
//        seqEflSkipInput(1);
//        seqEflButton(DOWN | A); // change box
//        seq(new EflSkipTextsSegment(2)); // save game
//        seq(new EflSkipTextsSegment(1, true)); // yes
//        seqEflButton(DOWN | A, MENU); // change box to 2
//        seqEflButton(B, MENU); // cancel
//        seqMetric(new OutputItems());
//        {
//          seqEflButton(DOWN | A); // items
//          seq(new EflSkipTextsSegment(2)); // accessed, item storage
//          seqEflScrollAF(1); // deposit
//          seqEflSkipInput(1);
//          seqEflScrollFastAF(7+1); // SS Ticket
//          seq(new EflSkipTextsSegment(1)); // stored in box
//          seq(new EflTextSegment()); // what do you want to deposit
//          seqEflScrollFastAF(8+1); // HM01
//          seq(new EflSkipTextsSegment(1)); // stored in box
//          seq(new EflTextSegment()); // what do you want to deposit
////          seqEflScrollFastAF(10+1); // HM02
////          seq(new EflSkipTextsSegment(1)); // stored in box
////          seq(new EflTextSegment()); // what do you want to deposit
////          seqEflScrollFastAF(13+1); // COIN CASE
////          seq(new EflSkipTextsSegment(1)); // stored in box
////          seq(new EflTextSegment()); // what do you want to deposit
//          seqEflScrollFastAF(14+1); // LIFT KEY
//          seq(new EflSkipTextsSegment(1)); // stored in box
//          seq(new EflTextSegment()); // what do you want to deposit
//          seqEflScrollFastAF(14+1); // SILPH SCOPE
//          seq(new EflSkipTextsSegment(1)); // stored in box
//          seq(new EflTextSegment()); // what do you want to deposit
//          seqEflScrollFastAF(14+1); // POKé FLUTE
//          seq(new EflSkipTextsSegment(1)); // stored in box
//          seqUnbounded(new EflTextSegment()); // what do you want to deposit
//          seqEflButtonUnbounded(B, MENU); // cancel deposit
//          seqEflButtonUnbounded(B, MENU); // cancel
//        }
//        seqEflButtonUnbounded(B, MENU); // cancel
//      }
//    }
//
//    seqUnbounded(new EflWalkToSegment(4, 6)); // leave center
//    seq(new EflWalkToSegment(4, 8, false)); // leave center
//
//    save("sa0");
//    load("sa0");
//
//    seq(new EflUseBikeSegment(2, 0));
//    seq(new EflWalkToSegment(5, 27)); // enter gym
//    seq(new EflWalkToSegment(7, 8, false)); // engage
//    seqMove(new EflOverworldInteract(3));
//    seq(new EflInitFightSegment(2)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckDisableEffectMisses(), 50)}; // disable
//      kems.attackCount[0][0] = 2; // slam
//      seq(kems); // drowzee
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckDisableEffectMisses(), 50)}; // disable
//      kems.attackCount[0][0] = 2; // slam
//      seq(kems); // drowzee
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckDisableEffectMisses(), 50)}; // disable
//      kems.attackCount[0][1] = 1; // slam crit
//      seq(kems); // kadabra
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckDisableEffectMisses(), 50)}; // disable
//      kems.attackCount[0][0] = 2; // slam
//      seq(kems); // drowzee
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//    seq(new EflWalkToSegment(1, 7)); // engage
//
//    save("sa1");
//    load("sa1");
//
//    seq(new EflInitFightSegment(3)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckPoisonEffectMisses(), 139)}; // poison gas
//      kems.attackCount[0][0] = 1; // slam
//      kems.attackCount[0][1] = 1; // slam crit
//      kems.numExpGainers = 2; // L31
//      seq(kems); // drowzee
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckDisableEffectMisses(), 50)}; // disable
//      kems.attackCount[0][0] = 2; // slam
//      kems.attackCount[0][1] = 1; // slam crit
//      seq(kems); // hypno
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//
//    save("sa2");
//    load("sa2");
//
//    {
//      seqMetric(new OutputParty());
//      seqEflButton(START, PRESSED);
//      seqEflScrollA(1); // mons
//      seqEflButton(A, PRESSED); // dragonair
//      seqEflSkipInput(1);
//      seqEflScrollAF(1); // switch
//      seqEflScrollAF(-3); // Bulbasaur
//      seqEflButton(B);
//      seqEflButton(START);
//    }
//
//    seq(new EflWalkToSegment(4, 9)); // engage koga
//    seq(new EflWalkToSegment(4, 10, false)); // engage koga
//    seqMove(new EflOverworldInteract(1));
//    seq(new EflInitFightSegment(9)); // start fight
//    seq(new EflSwitchPokemonSegment(3, EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 108))); // dragonair
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 108)}; // smokescreen
//      kems.attackCount[0][1] = 1; // slam crit
//      kems.attackCount[3][1] = 1; // thunderbolt crit
//      kems.numExpGainers = 4; // bulbasaur boosted; lvl11
//      seq(kems); // koffing
//    }
//
//    save("tmp");
//    load("tmp");
//
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckDisableEffectMisses(), 50)}; // disable
//      kems.attackCount[0][1] = 2; // slam crit
//      kems.attackCount[3][1] = 1; // thunderbolt crit
//      seq(kems); // muk
//    }
//
//    save("tmp2");
//    load("tmp2");
//
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    seq(new EflSwitchPokemonSegment(3, EflEnemyMoveDesc.missWith(123, 124))); // bulbasaur (smog, sludge)
//    seq(new EflSwitchPokemonSegment(3, EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 108))); // dragonair
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 108)}; // smokescreen
//      kems.attackCount[0][1] = 1; // slam crit
//      kems.attackCount[3][1] = 1; // thunderbolt crit
//      kems.numExpGainers = 6; // lvl32; bulbasaur boosted; lvl13, learned vine whip
//      seq(kems); // koffing
//    }
//
//    save("sa3");
//    load("sa3");
//
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    seq(new EflSwitchPokemonSegment(3, EflEnemyMoveDesc.missWith(123, 124))); // bulbasaur (smog, sludge)
//    save("tmp");
//    load("tmp");
//    seq(new EflSwitchPokemonSegment(3, EflEnemyMoveDesc.missWith(120))); // dragonair (Selfdestruct)
//    save("tmp2");
//    load("tmp2");
//    seq(new EflSkipTextsSegment(5)); // fainted, got xp, bulbasaur, boosted xp, lvl to 16
//
//    seq(new EflEndFightSegment(3)); // player defeated enemy
//    seq(new EflEvolutionSegment()); // Ivysaur
//    seq(new EflSkipTextsSegment(11)); // after battle texts
//    seq(new EflWalkToSegment(5, 16, false)); // leave gym
//    seq(new EflWalkToSegment(5, 18, false)); // leave gym
//
//    save("sa4");
//    load("sa4");
//
//    {
//      seqEflButton(START, PRESSED);
//      seqEflSkipInput(1);
//      seqEflScrollA(2);
//      seqEflButton(A, PRESSED); // bike
//      seq(new EflSkipTextsSegment(1)); // got on bike
//    }
//    seq(new EflWalkToSegment(23, 28, false)); // ledge
//
//    seq(new EflWalkToSegment(18, 3)); // enter safari house
//    seq(new EflWalkToSegment(3, 2)); // go pay
//    seq(new EflSkipTextsSegment(4)); // welcome to safari
//    seq(new EflSkipTextsSegment(1, true)); // yes, go on safari
//    seq(new EflSkipTextsSegment(7)); // welcome to safari
//    seqUnbounded(new EflUseBikeSegment(0, 0));
//
//    seqUnbounded(new EflWalkToSegment(19, 23));
//    seq(new EflEncounterSegment(0x1D, RIGHT)); // Pinsir
////    seq(new EflEncounterSegment(0x28, RIGHT)); // Chansey
//
//    save("tmp");
////    load("tmp");
//    seq(new EflCatchSafariMonSegment().withBufferSize(0));
//
//    seqUnbounded(new EflWalkToSegment(22, 23));
//    seq(new EflEncounterSegment(0x12, UP)); // Rhyhorn
////    seq(new EflEncounterSegment(0x0C, UP)); // Exeggcute
//    save("tmp2");
////  load("tmp2");
//    seq(new EflCatchSafariMonSegment().withBufferSize(0));
//
//    seqUnbounded(new EflWalkToSegment(23, 21));
//    seq(new EflEncounterSegment(0x0C, UP)); // Exeggcute
//    save("tmp3");
////  load("tmp3");
//    seq(new EflCatchSafariMonSegment().withBufferSize(0));
//
//    seqUnbounded(new EflWalkToSegment(26, 20));
//    seq(new EflEncounterSegment(0x0F, UP)); // NidoF
//    save("tmp4");
////  load("tmp4");
//    seq(new EflCatchSafariMonSegment().withBufferSize(0));
//
//    seqUnbounded(new EflWalkToSegment(26, 17));
//    seq(new EflEncounterSegment(0x41, UP)); // Venonat
//    save("tmp5");
////  load("tmp5");
//    seq(new EflCatchSafariMonSegment().withBufferSize(0));
//
//    seqUnbounded(new EflWalkToSegment(30, 11, false)); // east
//
//    save("sa5");
//    load("sa5");
//
//    seqUnbounded(new EflWalkToSegment(13, 24));
//    seq(new EflEncounterSegment(0x02, RIGHT)); // Kangaskhan
//    save("tmp");
////    load("tmp");
//    seq(new EflCatchSafariMonSegment().withBufferSize(0));
//
//    seqUnbounded(new EflWalkToSegment(16, 24));
//    seq(new EflEncounterSegment(0x6D, RIGHT)); // Paras
//    save("tmp2");
//    load("tmp2");
//    seq(new EflCatchSafariMonSegment().withBufferSize(0).withExtraSkips(80));
//
//    seqUnbounded(new EflWalkToSegment(19, 24));
//    seq(new EflEncounterSegment(0x2E, RIGHT)); // Parasect
//    save("tmp3");
//    load("tmp3");
//    seq(new EflCatchSafariMonSegment().withBufferSize(0));
//
//    seqUnbounded(new EflWalkToSegment(9, 15));
//    seq(new EflEncounterSegment(0x03, UP)); // NidoM
//    save("tmp4");
//    load("tmp4");
//    seq(new EflCatchSafariMonSegment().withBufferSize(0).withExtraSkips(30));
//
//    seqUnbounded(new EflWalkToSegment(9, 10));
//    seq(new EflEncounterSegment(0x46, UP)); // Doduo
//    save("tmp5");
//    load("tmp5");
//    seq(new EflCatchSafariMonSegment().withBufferSize(0));
//
//    seqUnbounded(new EflWalkToSegment(10, 3));
//    seq(new EflEncounterSegment(0xA8, LEFT)); // Nidorina
//    save("tmp6");
//    load("tmp6");
//    seq(new EflCatchSafariMonSegment().withBufferSize(0));
//
//    seqUnbounded(new EflWalkToSegment(-1, 5, false)); // east
//
//    save("sa6");
//    load("sa6");
//
//    seqUnbounded(new EflWalkToSegment(24, 25));
//    seq(new EflEncounterSegment(0x3C, UP)); // Tauros
//    save("tmp");
//    load("tmp");
//    seq(new EflCatchSafariMonSegment().withBufferSize(0).withExtraSkips(10));
//
//    seqUnbounded(new EflWalkToSegment(13, 27));
//    seq(new EflEncounterSegment(0x28, UP)); // Chansey
//    save("tmp2");
////    load("tmp2");
//    seq(new EflCatchSafariMonSegment().withBufferSize(0));
//
//    seqUnbounded(new EflWalkToSegment(15, 12));
//    seq(new EflEncounterSegment(0x77, UP)); // Venomoth
//    save("tmp3");
////    load("tmp3");
//    seq(new EflCatchSafariMonSegment().withBufferSize(0));
//
//    seqUnbounded(new EflWalkToSegment(27, 6));
//    seq(new EflEncounterSegment(0xA7, RIGHT)); // Nidorino
//    save("tmp4");
////    load("tmp4");
//    seq(new EflCatchSafariMonSegment().withBufferSize(0));
//
//    seq(new EflWalkToSegment(3, 36, false)); // east
//
//    save("sa7");
//    load("sa7");
//
//    seq(new EflWalkToSegment(19, 5)); // gold teeth
//    seq(new EflWalkToSegment(19, 6)); // gold teeth
//    seqMove(new EflOverworldInteract(4)); // pick up gold teeth
//    seq(new EflTextSegment());
//    seq(new EflWalkToSegment(3, 3)); // enter surf house
//    seq(new EflWalkToSegment(3, 5)); // surf guy
//    seq(new EflWalkToSegment(3, 4)); // surf guy
//    seqMove(new EflOverworldInteract(1)); // surf guy
//    seq(new EflSkipTextsSegment(8)); // get surf
//    seq(new EflWalkToSegment(3, 8, false)); // leave surf house
//    {
//      seqEflButton(START, PRESSED);
//      seqEflScrollA(2); // items
//      seqEflScrollFastAF(2+1); // escape rope
//      seqEflButton(A, PRESSED); // use
//      seqEflSkipInput(2);
//    }
  }
}
