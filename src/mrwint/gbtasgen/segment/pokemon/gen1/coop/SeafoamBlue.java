package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import static mrwint.gbtasgen.move.Move.A;
import static mrwint.gbtasgen.move.Move.B;
import static mrwint.gbtasgen.move.Move.DOWN;
import static mrwint.gbtasgen.move.Move.LEFT;
import static mrwint.gbtasgen.move.Move.RIGHT;
import static mrwint.gbtasgen.move.Move.SELECT;
import static mrwint.gbtasgen.move.Move.START;
import static mrwint.gbtasgen.move.Move.UP;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.AGILITY;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.CHARIZARD;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DEWGONG;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DRAGONAIR;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.EKANS;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.ESCAPE_ROPE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.EXEGGCUTE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.EXEGGUTOR;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.FARFETCHD;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.GLOOM;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.GOLBAT;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.GOLDEEN;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.GOLDUCK;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.GRIMER;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.GROWL;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.GROWLITHE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.HM03;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.HORSEA;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.KABUTO;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.KINGLER;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.KOFFING;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.KRABBY;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.LEAF_STONE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.LEER;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.MAGMAR;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.MANKEY;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.MUK;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.NIDORINA;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.NIDORINO;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.OMANYTE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PIDGEOT;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PIDGEOTTO;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PIDGEY;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PINSIR;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.POLIWAG;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.POLIWHIRL;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.POLIWRATH;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PONYTA;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PRIMEAPE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PSYDUCK;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.ROAR;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SEADRA;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SEEL;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SHELLDER;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SLOWBRO;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SLOWPOKE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.STARYU;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TAIL_WHIP;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TANGELA;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TENTACOOL;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.WARTORTLE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.WATER_STONE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.WEEZING;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.ZUBAT;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.MENU;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.PRESSED;
import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.metric.pokemon.CheckEncounterMetric;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckSwitchAndTeleportEffectUsed;
import mrwint.gbtasgen.metric.pokemon.gen1.Gen1CheckDVMetric;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputBoxMons;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputItems;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputParty;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.pokemon.gen1.EflOverworldInteract;
import mrwint.gbtasgen.move.pokemon.gen1.EflWalkStep;
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
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflBuyItemSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflChangeMonBoxSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflDepositMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflEncounterSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflFishSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflReleaseMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSelectItemSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSelectMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSellItemSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflUseBikeSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflWithdrawMonSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;

public class SeafoamBlue extends SeqSegment {

	@Override
	public void execute() {

//    seqEflButton(A); // continue game
//    seqEflButton(START);
//    seqEflButton(A);
//    seqEflButton(START);
//    seqEflButton(A);
//
//    seqMetric(new OutputParty());
//    seqMetric(new OutputItems());
//    seqMetric(new OutputBoxMons());
//
//    seq(new EflSelectMonSegment(PIDGEOT).fromOverworld().andFlyTo(3)); // Cinnabar
//    seqEflSkipInput(1);
//
//    seqUnbounded(new EflWalkToSegment(11, 11)); // center
//    {
//      seqUnbounded(new EflWalkToSegment(13, 5)); // PC // TODO
//      seq(new EflWalkToSegment(13, 4)); // PC
//
//      {
//        seqEflButton(A); // use PC
//        seq(new EflSkipTextsSegment(1)); // turned on
//        seqEflButton(A); // someone's PC
//        seq(new EflSkipTextsSegment(2)); // accessed, mon storage
//        seq(new EflChangeMonBoxSegment(4)); // box 5
//        seqEflButton(B, MENU); // cancel
//        seqEflButton(B, PRESSED); // cancel
//      }
//      seqUnbounded(new EflWalkToSegment(4, 6));
//      seq(new EflWalkToSegment(4, 8, false));
//
//    }
//
//    seq(new EflWalkToSegment(19, 12)); // water
//
//    save("sf0");
//    load("sf0");
//
//    seqUnbounded(new EflSelectMonSegment(DRAGONAIR).fromOverworld().andSurf());
//
//    seqMoveUnbounded(new EflWalkStep(UP, true));
//    for(int i=0;i<42;i++)
//      seqMoveUnbounded(new EflWalkStep(RIGHT, true));
//    for(int i=0;i<4;i++)
//      seqMoveUnbounded(new EflWalkStep(DOWN, true));
//    for(int i=0;i<18;i++)
//      seqMoveUnbounded(new EflWalkStep(RIGHT, true));
//    for(int i=0;i<4;i++)
//      seqMoveUnbounded(new EflWalkStep(UP, true));
//    save("tmp");
//    load("tmp");
//    seqUnbounded(new EflUseBikeSegment().fromOverworld());
//    seqUnbounded(new EflWalkToSegment(58, 9)); // enter Seafoam Islands
//
//
//    seqUnbounded(new EflWalkToSegment(26, 16));
//    seq(new EflEncounterSegment(SLOWBRO, UP));
//    save("tmp2");
//    load("tmp2");
//    seq(new EflCatchMonSegment().withBufferSize(0));
//    seqUnbounded(new EflWalkToSegment(26, 13));
//    seq(new EflEncounterSegment(KRABBY, UP));
//    save("tmp3");
//  //  load("tmp3");
//    seq(new EflCatchMonSegment().withBufferSize(0));
//    seqUnbounded(new EflWalkToSegment(24, 12));
//    seq(new EflEncounterSegment(PSYDUCK, LEFT));
//    save("tmp4");
//    load("tmp4");
//    seq(new EflCatchMonSegment().withBufferSize(0).withExtraSkips(2));
//    seqUnbounded(new EflWalkToSegment(22, 13));
//    seqUnbounded(new EflWalkToSegment(22, 14));
//    seq(new EflEncounterSegment(SLOWPOKE, DOWN));
//    save("tmp5");
//    load("tmp5");
//    seq(new EflCatchMonSegment().withBufferSize(0).withExtraSkips(4));
//    seqUnbounded(new EflWalkToSegment(23, 15)); // b1f
//    seqUnbounded(new EflWalkToSegment(25, 15));
//    seq(new EflEncounterSegment(STARYU, RIGHT));
//    save("tmp7");
////    load("tmp7");
//    seq(new EflCatchMonSegment().withBufferSize(0));
//    seqUnbounded(new EflWalkToSegment(26, 13));
//    seq(new EflEncounterSegment(SHELLDER, UP));
//    save("tmp8");
//    load("tmp8");
//    seq(new EflCatchMonSegment().withBufferSize(0));
//    seqUnbounded(new EflWalkToSegment(25, 11)); // b2f
//    seqUnbounded(new EflWalkToSegment(25, 14)); // b3f
//    seqUnbounded(new EflWalkToSegment(25, 13));
//    seq(new EflEncounterSegment(SEEL, UP));
//    save("tmp");
////    load("tmp");
//    seq(new EflCatchMonSegment().withBufferSize(0));
//    seqUnbounded(new EflWalkToSegment(25, 10));
//    seq(new EflEncounterSegment(DEWGONG, UP));
//    save("tmp2");
////    load("tmp2");
//    seq(new EflCatchMonSegment());
////    seq(new EflWalkToSegment(23, 9));
//    seqUnbounded(new EflWalkToSegment(23, 9));
//    seqUnbounded(new EflSelectMonSegment(DRAGONAIR).fromOverworld().andSurf());
//    for(int i=0;i<2;i++)
//      seqMoveUnbounded(new EflWalkStep(LEFT, true));
//    for(int i=0;i<7;i++)
//      seqMoveUnbounded(new EflWalkStep(DOWN, true));
//    seqEflButtonUnboundedNoDelay(DOWN);
//    for(int i=0;i<5;i++)
//      seqEflButtonUnboundedNoDelay(UP);
//    for(int i=0;i<8;i++)
//      seqMoveUnbounded(new EflWalkStep(UP, true));
//    for(int i=0;i<2;i++)
//      seqMoveUnbounded(new EflWalkStep(RIGHT, true));
//    seqMoveUnbounded(new EflWalkStep(UP, true));
//    save("tmp3");
//    load("tmp3");
//    seqUnbounded(new EflUseBikeSegment().fromOverworld());
//    seqUnbounded(new EflWalkToSegment(16, 5));
//    seq(new EflEncounterSegment(GOLDUCK, DOWN));
//    save("tmp4");
//    load("tmp4");
//    seq(new EflCatchMonSegment().withBufferSize(0));
//    seqUnbounded(new EflWalkToSegment(11, 7)); // b3f
//    seqUnbounded(new EflWalkToSegment(9, 10));
//    seq(new EflEncounterSegment(KINGLER, RIGHT));
//    save("tmp5");
//    load("tmp5");
//    seq(new EflCatchMonSegment());
//
//    seq(new EflSelectMonSegment(PRIMEAPE).fromOverworld().andStrength());
//    save("tmp6");
//    load("tmp6");
//    seq(new EflWalkToSegment(7, 15));
//    seq(new EflWalkToSegment(6, 14));
//    seq(new EflWalkToSegment(5, 14, false));
//    seq(new EflWalkToSegment(5, 14, false));
//    seq(new EflWalkToSegment(5, 14));
//    seq(new EflWalkToSegment(4, 14, false));
//    seq(new EflWalkToSegment(3, 14, false));
//    seq(new EflWalkToSegment(3, 15, false));
//    seq(new EflWalkToSegment(5, 14));
//
//    seq(new EflWalkToSegment(9, 14, false));
//    seq(new EflWalkToSegment(9, 14, false));
//    seq(new EflWalkToSegment(9, 14));
//    seq(new EflWalkToSegment(9, 13, false));
//    seq(new EflWalkToSegment(8, 13));
//    seq(new EflWalkToSegment(8, 14, false));
//    seq(new EflWalkToSegment(8, 14, false));
//    seq(new EflWalkToSegment(9, 14));
//    seq(new EflWalkToSegment(9, 15));
//    seq(new EflWalkToSegment(8, 15, false));
//    seq(new EflWalkToSegment(8, 15, false));
//    seq(new EflWalkToSegment(7, 15, false));
//    seq(new EflWalkToSegment(7, 17));
//    seq(new EflWalkToSegment(5, 17));
//    seq(new EflWalkToSegment(5, 14));
//    seq(new EflWalkToSegment(6, 14));
//    seq(new EflWalkToSegment(6, 15, false));
//    seq(new EflWalkToSegment(6, 15, false));
//    seq(new EflWalkToSegment(6, 16));
//
//    save("sf1");
    load("sf1");
    for(int i=0;i<10;i++)
      seqMove(new EflWalkStep(UP, true));
    for(int i=0;i<2;i++)
      seqMove(new EflWalkStep(RIGHT, true));
    seqMove(new EflWalkStep(UP, true));
    seqMove(new EflWalkStep(DOWN, false));
    seqEflSkipInput(3);

    seq(new EflFishSegment(HORSEA));
    seq(new EflCatchMonSegment());
    seq(new EflFishSegment(GOLDEEN));
    seq(new EflCatchMonSegment());

    seq(new EflWalkToSegment(6, 1, false));
    seqMove(new EflOverworldInteract(3)); // Articuno
    seq(new EflSkipTextsSegment(1)); // Gyaa
    seq(new EflCatchMonSegment());

    save("sf2");
    load("sf2");

    seq(new EflSelectItemSegment(LEAF_STONE).fromOverworld().andUse());
    seq(new EflSelectMonSegment(GLOOM));
    seq(new EflEvolutionSegment()); // Vileplume

    seq(new EflSelectItemSegment(ESCAPE_ROPE).andUse());
    seqEflSkipInput(2);
  }
}
