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
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DEWGONG;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DRAGONAIR;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.EKANS;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.ESCAPE_ROPE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.EXEGGCUTE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.EXEGGUTOR;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.FARFETCHD;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.GOLBAT;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.GOLDEEN;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.GOLDUCK;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.GRIMER;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.GROWL;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.GROWLITHE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.HM03;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.HORSEA;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.KABUTO;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.KOFFING;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.LEAF_STONE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.LEER;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.MAGMAR;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.MANKEY;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.MUK;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.NIDORINA;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.NIDORINO;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.OMANYTE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PIDGEOTTO;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PIDGEY;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PINSIR;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.POLIWHIRL;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.POLIWRATH;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PONYTA;
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

public class SeafoamRed extends SeqSegment {

	@Override
	public void execute() {
//    seqMetric(new OutputParty());
//    seqMetric(new OutputItems());
//    seqMetric(new OutputBoxMons());
//
//    seq(new EflSelectMonSegment(FARFETCHD).fromOverworld().andFlyTo(2)); // Cinnabar
//    seqEflSkipInput(1);
//
////    {
////      seq(new EflWalkToSegment(11, 11)); // enter center
////      seq(new EflWalkToSegment(13, 5)); // PC
////      seq(new EflWalkToSegment(13, 4)); // PC
////      {
////        seqEflButton(A); // use PC
////        seq(new EflSkipTextsSegment(1)); // turned on
////        seqEflButton(A); // someone's PC
////        seq(new EflSkipTextsSegment(2)); // accessed, mon storage
////        seqUnbounded(new EflChangeMonBoxSegment(3)); // box 4
////        seqEflButtonUnboundedNoDelay(B, MENU); // cancel
////        seqEflButtonUnboundedNoDelay(B, PRESSED); // cancel
////      }
////      seqMetric(new OutputBoxMons());
////
////      seqUnbounded(new EflWalkToSegment(4, 6)); // leave center
////      seq(new EflWalkToSegment(4, 8, false)); // leave center
////    }
//
//    seq(new EflWalkToSegment(19, 12)); // water
//
//    save("sf0");
//    load("sf0");
//
//    seqUnbounded(new EflSelectMonSegment(OMANYTE).fromOverworld().andSurf());
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
//    seqUnbounded(new EflWalkToSegment(58, 9)); // enter Seafoam Islands
//
//    save("tmp");
////    load("tmp");
//
//    seqUnbounded(new EflWalkToSegment(26, 16));
//    seq(new EflEncounterSegment(GOLDUCK, UP));
//    save("tmp2");
////    load("tmp2");
//    seq(new EflCatchMonSegment().withBufferSize(0));
//    seqUnbounded(new EflUseBikeSegment().fromOverworld());
//    seqUnbounded(new EflWalkToSegment(26, 13));
//    seq(new EflEncounterSegment(SLOWPOKE, LEFT));
//    save("tmp3");
//    load("tmp3");
//    seq(new EflCatchMonSegment().withBufferSize(0).withExtraSkips(60));
//    seqUnbounded(new EflWalkToSegment(23, 13));
//    seq(new EflEncounterSegment(PSYDUCK, DOWN));
//    save("tmp4");
////    load("tmp4");
//    seq(new EflCatchMonSegment().withBufferSize(0));
//    seqUnbounded(new EflWalkToSegment(23, 15)); // b1f
//    seqUnbounded(new EflWalkToSegment(25, 15));
//    seq(new EflEncounterSegment(HORSEA, RIGHT));
//    save("tmp5");
////    load("tmp5");
//    seq(new EflCatchMonSegment().withBufferSize(0));
//    seqUnbounded(new EflWalkToSegment(26, 12));
//    seq(new EflEncounterSegment(SEEL, UP));
//    save("tmp6");
//    load("tmp6");
//    seq(new EflCatchMonSegment().withBufferSize(0).withExtraSkips(7));
//    seqUnbounded(new EflWalkToSegment(25, 11)); // b2f
//    seqUnbounded(new EflWalkToSegment(25, 14)); // b3f
//    seqUnbounded(new EflWalkToSegment(25, 10));
//    seq(new EflEncounterSegment(DEWGONG, UP));
//    save("tmp");
//    load("tmp");
//    seq(new EflCatchMonSegment().withBufferSize(0).withExtraSkips(40));
//    seqUnbounded(new EflWalkToSegment(24, 8));
//    seq(new EflEncounterSegment(SHELLDER, LEFT));
//    save("tmp2");
//    load("tmp2");
//    seq(new EflCatchMonSegment());
//    seq(new EflWalkToSegment(23, 9));
//    seqUnbounded(new EflSelectMonSegment(KABUTO).fromOverworld().andSurf());
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
//    seqUnbounded(new EflWalkToSegment(22, 4));
//    seq(new EflEncounterSegment(GOLBAT, LEFT));
//    save("tmp3");
////    load("tmp3");
//    seq(new EflCatchMonSegment().withBufferSize(0));
//    seqUnbounded(new EflUseBikeSegment().fromOverworld());
//    seqUnbounded(new EflWalkToSegment(14, 7));
//    seqUnbounded(new EflWalkToSegment(13, 7));
//    seq(new EflEncounterSegment(SLOWBRO, LEFT));
//    save("tmp4");
////    load("tmp4");
//    seq(new EflCatchMonSegment().withBufferSize(0));
//    seqUnbounded(new EflWalkToSegment(11, 7)); // b3f
//    seqUnbounded(new EflWalkToSegment(11, 11));
//    seq(new EflEncounterSegment(SEADRA, DOWN));
//    save("tmp5");
    load("tmp5");
    seq(new EflCatchMonSegment());

    seq(new EflSelectMonSegment(EXEGGUTOR).fromOverworld().andStrength());
    seq(new EflWalkToSegment(7, 15));
    seq(new EflWalkToSegment(6, 14));
    seq(new EflWalkToSegment(5, 14, false));
    seq(new EflWalkToSegment(5, 14, false));
    seq(new EflWalkToSegment(5, 14, false));
    seq(new EflWalkToSegment(4, 14, false));
    seq(new EflWalkToSegment(3, 14, false));
    seq(new EflWalkToSegment(3, 15, false));
    seq(new EflWalkToSegment(5, 14, false));

    seq(new EflWalkToSegment(9, 14, false));
    seq(new EflWalkToSegment(9, 14, false));
    seq(new EflWalkToSegment(9, 14, false));
    seq(new EflWalkToSegment(9, 13, false));
    seq(new EflWalkToSegment(8, 13));
    seq(new EflWalkToSegment(8, 14, false));
    seq(new EflWalkToSegment(8, 14, false));
    seq(new EflWalkToSegment(9, 14));
    seq(new EflWalkToSegment(9, 15));
    seq(new EflWalkToSegment(8, 15, false));
    seq(new EflWalkToSegment(8, 15, false));
    seq(new EflWalkToSegment(7, 15, false));
    seq(new EflWalkToSegment(7, 17));
    seq(new EflWalkToSegment(5, 17));
    seq(new EflWalkToSegment(5, 14));
    seq(new EflWalkToSegment(6, 14));
    seq(new EflWalkToSegment(6, 15, false));
    seq(new EflWalkToSegment(6, 15, false));
    seq(new EflWalkToSegment(6, 16));

    save("sf1");
    load("sf1");
    for(int i=0;i<10;i++)
      seqMove(new EflWalkStep(UP, true));
    for(int i=0;i<2;i++)
      seqMove(new EflWalkStep(RIGHT, true));
    seqMove(new EflWalkStep(UP, true));
    seqMove(new EflWalkStep(DOWN, false));

    seq(new EflFishSegment(GOLDEEN));
    seq(new EflCatchMonSegment());
    seq(new EflFishSegment(STARYU));
    seq(new EflCatchMonSegment().withExtraSkips(10));

    seq(new EflWalkToSegment(6, 1, false));
    seqMove(new EflOverworldInteract(3)); // Articuno
    seq(new EflSkipTextsSegment(1)); // Gyaa
    seq(new EflCatchMonSegment());

    save("sf2");
    load("sf2");

    seq(new EflSelectItemSegment(ESCAPE_ROPE).fromOverworld().andUse());
    seqEflSkipInput(2);
  }
}
