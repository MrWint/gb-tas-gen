package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import static mrwint.gbtasgen.move.Move.A;
import static mrwint.gbtasgen.move.Move.B;
import static mrwint.gbtasgen.move.Move.LEFT;
import static mrwint.gbtasgen.move.Move.RIGHT;
import static mrwint.gbtasgen.move.Move.START;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.ABRA;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.BEEDRILL;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.BUTTERFREE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DIGLETT;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DRATINI;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DROWZEE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DUGTRIO;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.EEVEE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.ESCAPE_ROPE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.FARFETCHD;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.MEOWTH;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SQUIRTLE;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.MENU;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.PRESSED;

import mrwint.gbtasgen.metric.pokemon.gen1.OutputBoxMons;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputItems;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputParty;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.pokemon.gen1.EflOverworldInteract;
import mrwint.gbtasgen.segment.pokemon.EflCatchMonSegment;
import mrwint.gbtasgen.segment.pokemon.EflEvolutionSegment;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.pokemon.EflWalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflEndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflInitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflNewEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.Constants;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflBuyItemSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflCancelMoveLearnSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflDepositMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflEncounterSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSelectItemSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSelectMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSwapWithSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflWithdrawMonSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.util.EflUtil.PressMetric;

public class PreRockTunnelBlue extends SeqSegment {

	@Override
	public void execute() {
//    seqEflButton(A); // continue game
//    seqEflButton(START);
//    seqEflButton(A);
//    seqEflButton(START);
//    seqEflButton(A);
//
//    seq(new EflWalkToSegment(4, 6)); // leave center
//    seq(new EflWalkToSegment(4, 8, false)); // leave center
//
//    seqMetric(new OutputItems());
//
//    {
//      seq(new EflWalkToSegment(23, 13)); // enter mart
//
//      seq(new EflWalkToSegment(3, 5));
//      seq(new EflWalkToSegment(2, 5));
//      seq(new MoveSegment(new EflOverworldInteract(1)));
//      {
//        seq(new EflSkipTextsSegment(1, true)); // buy
//        seq(new EflTextSegment(B));
//        seq(new EflBuyItemSegment(0, -40, true)); // 59 balls
//        seqEflButton(B); // cancel
//        seq(new EflSkipTextsSegment(1)); // cancel
//        seqUnbounded(new EflSkipTextsSegment(1)); // bye
//      }
//      seqUnbounded(new EflWalkToSegment(3, 8, false)); // leave mart
//    }
//
//    seqUnbounded(new EflWalkToSegment(40, 14)); // route 11
//
//    seqUnbounded(new EflWalkToSegment(10, 6)); // grass
//    seqUnbounded(new EflWalkToSegment(11, 6)); // grass
//
//    seqUnbounded(new EflWalkToSegment(14, 6)); // grass
//    seqUnbounded(new EflWalkToSegment(13, 6)); // grass
//
//    seq(new EflEncounterSegment(DROWZEE, LEFT));
//    save("tmp");
//    load("tmp1");
//    seq(new EflCatchMonSegment().withBufferSize(0).withExtraSkips(4));
//
//    seqUnbounded(new EflWalkToSegment(4, 5)); // diglett's cave
//    seqUnbounded(new EflWalkToSegment(4, 4)); // diglett's cave
//    seqUnbounded(new EflWalkToSegment(36, 31)); // cave
//
//    seq(new EflEncounterSegment(DUGTRIO, LEFT));
//    save("tmp2");
//    load("tmp2");
//    seq(new EflCatchMonSegment().withBufferSize(0));
//
//    seqUnbounded(new EflWalkToSegment(35, 32)); // cave
//    seqUnbounded(new EflWalkToSegment(36, 32)); // cave
//
//    seq(new EflEncounterSegment(DIGLETT, Move.RIGHT));
//    seq(new EflCatchMonSegment());
//
//    save("prt1");
//    load("prt1");
//
//    seq(new EflSelectItemSegment(ESCAPE_ROPE).fromOverworld().andUse());
//    seqEflSkipInput(2);
//
//    seqUnbounded(new EflWalkToSegment(13, 25)); // enter bike shop
//    seqUnbounded(new EflWalkToSegment(6, 5)); // walk to counter
//    seq(new EflWalkToSegment(6, 4)); // walk to counter
//    seqMove(new EflOverworldInteract(1)); // talk to owner
//    seq(new EflSkipTextsSegment(5)); // get bike
//    seq(new EflWalkToSegment(3, 8, false)); // leave shop
//    {
//      seqMetric(new OutputItems());
//      seqEflButton(START, PRESSED);
//      seqEflButton(A); // items
//      seq(new EflSwapWithSegment(11));
//      seqEflScrollFastA(-11);
//      seq(new EflSkipTextsSegment(1)); // got on bike
//    }
//    seq(new EflWalkToSegment(19, 26)); // go to bush
//    seq(new EflWalkToSegment(19, 27)); // go to bush
//
//    save("tmp");
//    load("tmp");
//
//    seq(new EflSelectMonSegment(ABRA).fromOverworld().andSwitchWith(SQUIRTLE));
//    seqEflSkipInput(0);
//    seq(new EflSelectMonSegment(FARFETCHD).andCut());
//    seq(new EflWalkToSegment(40, 17)); // leave cerulean
//    seq(new EflWalkToSegment(4, 8)); // go to bush
//    seq(new EflSelectMonSegment(FARFETCHD).fromOverworld().andCut());
//    seq(new EflWalkToSegment(13, 8)); // go to trainer
//    seq(new EflWalkToSegment(13, 9)); // go to trainer
//    seqMove(new EflOverworldInteract(1)); // talk to trainer
//    seq(new EflInitFightSegment(2)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[0][1] = 1; // mega punch crit
//      seq(kems); // oddish
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[1][1] = 1; // bite crit
//      seq(kems); // bellsprout
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[0][1] = 1; // mega punch crit
//      kems.numExpGainers = 2; // Squirtle, level up to 27
//      seq(kems); // oddish
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[1][1] = 1; // bite crit
//      seq(kems); // bellsprout
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//
//    save("prt2");
//    load("prt2");
//
//    seq(new EflEvolutionSegment(true));
//    seq(new EflWalkToSegment(12, 11, false)); // jump ledge
//
//    seq(new EflWalkToSegment(40, 10)); // go to trainer
//    seq(new EflWalkToSegment(40, 9)); // go to trainer
//    seqMove(new EflOverworldInteract(9)); // talk to trainer
//    seq(new EflInitFightSegment(1)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[1][1] = 1; // bite crit
//      seq(kems); // caterpie
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[1][1] = 1; // bite crit
//      seq(kems); // weedle
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[0][1] = 1; // mega punch crit
////      kems.numExpGainers = 2; // lvlup to 28
//      seq(kems); // venonat
//    }
////    seq(new EflCancelMoveLearnSegment());
//    seq(new EflEndFightSegment(1)); // player defeated enemy
////    seq(new EflEvolutionSegment(true));
//    seq(new EflWalkToSegment(51, 5, false)); // jump ledge
//    seq(new EflWalkToSegment(60, 8)); // route 10
//
//    save("prt3");
    load("prt3");

    seq(new EflWalkToSegment(11, 19)); // enter center
    seqMetric(new OutputParty());
    seq(new EflWalkToSegment(11, 4)); // cable club
    seq(new EflWalkToSegment(11, 3)); // cable club

	}
}
