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
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.CLOYSTER;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DEWGONG;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DODRIO;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DRAGONAIR;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.EKANS;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.ELECTRODE;
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
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.HYPNO;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.IVYSAUR;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.JYNX;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.KABUTO;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.KADABRA;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.KINGLER;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.KOFFING;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.KRABBY;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.LEAF_STONE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.LEER;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.MAGMAR;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.MAGNETON;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.MANKEY;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.MAROWAK;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.MOON_STONE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.MUK;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.NIDOKING;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.NIDOQUEEN;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.NIDORINA;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.NIDORINO;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.OMANYTE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PERSIAN;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PIDGEOT;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PIDGEOTTO;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PIDGEY;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PINSIR;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.POLIWAG;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.POLIWHIRL;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.POLIWRATH;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PONYTA;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PSYDUCK;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.RAICHU;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.RHYDON;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.ROAR;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SANDSLASH;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SEADRA;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SEAKING;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SEEL;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SHELLDER;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SLOWBRO;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SLOWPOKE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.STARMIE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.STARYU;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TAIL_WHIP;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TANGELA;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TENTACOOL;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.VILEPLUME;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.WARTORTLE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.WATER_STONE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.WEEZING;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.WIGGLYTUFF;
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

public class CeruleanCaveBlue extends SeqSegment {

	@Override
	public void execute() {

//    seq(new EflSelectMonSegment(PIDGEOT).fromOverworld().andFlyTo(-3)); // Cerulean
//    seqEflSkipInput(1);
//
//    seqMetric(new OutputParty());
//    seqMetric(new OutputItems());
//    seqMetric(new OutputBoxMons());
//
//    seq(new EflWalkToSegment(19, 17)); // center
//    {
//      seq(new EflWalkToSegment(13, 4)); // PC // TODO
//      seq(new EflWalkToSegment(13, 3, false)); // PC
//
//      {
//        seqEflButton(A); // use PC
//        seq(new EflSkipTextsSegment(1)); // turned on
//        seqEflButton(A); // someone's PC
//        seq(new EflSkipTextsSegment(2)); // accessed, mon storage
//        seq(new EflDepositMonSegment(IVYSAUR));
//        seq(new EflDepositMonSegment(VILEPLUME));
//        seq(new EflWithdrawMonSegment(SHELLDER));
//        seq(new EflWithdrawMonSegment(STARYU));
//        seqEflButton(B, MENU); // cancel
//        seqEflButton(B, PRESSED); // cancel
//      }
//
//      seqEflSkipInput(1);
//      seq(new EflSelectItemSegment(WATER_STONE).fromOverworld().andUse());
//      seq(new EflSelectMonSegment(STARYU));
//      seq(new EflEvolutionSegment()); // Starmie
//      seq(new EflSelectItemSegment(WATER_STONE).andUse());
//      seq(new EflSelectMonSegment(SHELLDER));
//      seq(new EflEvolutionSegment()); // Cloyster
//      seqEflButton(B);
//      seqEflButton(START);
//
//      {
//        seqEflButton(A); // use PC
//        seq(new EflSkipTextsSegment(1)); // turned on
//        seqEflButton(A); // someone's PC
//        seq(new EflSkipTextsSegment(2)); // accessed, mon storage
//        seq(new EflDepositMonSegment(CLOYSTER));
//        seq(new EflDepositMonSegment(STARMIE));
//        seq(new EflWithdrawMonSegment(IVYSAUR));
//        seq(new EflChangeMonBoxSegment(4)); // box 5
//        seqEflButton(B, MENU); // cancel
//        seqEflButton(B, PRESSED); // cancel
//      }
//    }
//
//    seq(new EflWalkToSegment(4, 6));
//    seq(new EflWalkToSegment(4, 8, false));
//
//    save("cc0");
//    load("cc0");
//
//    seq(new EflUseBikeSegment().fromOverworld());
//    seq(new EflWalkToSegment(20, -1)); // Route 24
//    seq(new EflWalkToSegment(4, 31)); // water
//    seqUnbounded(new EflSelectMonSegment(DRAGONAIR).fromOverworld().andSurf());
//
//    for(int i=0;i<8;i++)
//      seqMoveUnbounded(new EflWalkStep(DOWN, true));
//    for(int i=0;i<8;i++)
//      seqMoveUnbounded(new EflWalkStep(LEFT, true));
//    for(int i=0;i<8;i++)
//      seqMoveUnbounded(new EflWalkStep(DOWN, true));
//    seqMoveUnbounded(new EflWalkStep(LEFT, true));
//
//    save("tmp");
//    load("tmp");
//
//    seqUnbounded(new EflUseBikeSegment().fromOverworld());
//    seqUnbounded(new EflWalkToSegment(4, 11)); // enter Cerulean Cave
//    seqUnbounded(new EflWalkToSegment(21, 15));
//    seq(new EflEncounterSegment(RAICHU, UP));
//    save("tmp2");
//    load("tmp2");
//    seq(new EflCatchMonSegment().withBufferSize(0));
//    seqUnbounded(new EflWalkToSegment(21, 12));
//    seq(new EflEncounterSegment(DODRIO, UP));
//    save("tmp3");
//    load("tmp3");
//    seq(new EflCatchMonSegment().withBufferSize(0));
//    seqUnbounded(new EflWalkToSegment(21, 9));
//    seq(new EflEncounterSegment(HYPNO, UP));
//    save("tmp4");
//    load("tmp4");
//    seq(new EflCatchMonSegment().withBufferSize(0));
//    seqUnbounded(new EflWalkToSegment(25, 9)); // water
//    seqUnbounded(new EflSelectMonSegment(DRAGONAIR).fromOverworld().andSurf());
//    for(int i=0;i<3;i++)
//      seqMoveUnbounded(new EflWalkStep(RIGHT, true));
//    for(int i=0;i<5;i++)
//      seqMoveUnbounded(new EflWalkStep(UP, true));
//    for(int i=0;i<14;i++)
//      seqMoveUnbounded(new EflWalkStep(LEFT, true));
//    for(int i=0;i<2;i++)
//      seqMoveUnbounded(new EflWalkStep(DOWN, true));
//    for(int i=0;i<5;i++)
//      seqMoveUnbounded(new EflWalkStep(LEFT, true));
//    for(int i=0;i<7;i++)
//      seqMoveUnbounded(new EflWalkStep(DOWN, true));
//    for(int i=0;i<2;i++)
//      seqMoveUnbounded(new EflWalkStep(RIGHT, true));
//    seqMoveUnbounded(new EflWalkStep(UP, true));
//
//    seqUnbounded(new EflUseBikeSegment().fromOverworld());
//    seq(new EflEncounterSegment(KADABRA, UP));
//    save("tmp5");
//    load("tmp5");
//    seq(new EflCatchMonSegment().withBufferSize(0));
//    seqUnbounded(new EflWalkToSegment(13, 12));
//    seq(new EflEncounterSegment(MAGNETON, RIGHT));
//    save("tmp6");
//    load("tmp6");
//    seq(new EflCatchMonSegment().withBufferSize(0));
//    seqEflButtonUnboundedNoDelay(UP);
//    seqEflButtonUnboundedNoDelay(A);
//    seqUnbounded(new EflTextSegment()); // Rare Candy
//    seqUnbounded(new EflWalkToSegment(12, 17));
//    seq(new EflEncounterSegment(SANDSLASH, LEFT));
//    save("tmp");
//    load("tmp");
//    seq(new EflCatchMonSegment().withBufferSize(0));
//    seqUnbounded(new EflWalkToSegment(3, 11)); // 2f
//    seqUnbounded(new EflWalkToSegment(3, 10));
//    seq(new EflEncounterSegment(WIGGLYTUFF, UP));
//    save("tmp2");
//    load("tmp2");
//    seq(new EflCatchMonSegment().withBufferSize(0));
//    seqUnbounded(new EflWalkToSegment(2, 15));
//    seq(new EflEncounterSegment(RHYDON, UP));
//    save("tmp3");
//    load("tmp3");
//    seq(new EflCatchMonSegment().withBufferSize(0));
//    seqUnbounded(new EflWalkToSegment(0, 4));
//    seq(new EflEncounterSegment(ELECTRODE, UP));
//    save("tmp4");
//    load("tmp4");
//    seq(new EflCatchMonSegment().withBufferSize(0));
//    seqUnbounded(new EflWalkToSegment(1, 3)); // 1f
//    seqUnbounded(new EflWalkToSegment(0, 6)); // b1f
//    seqUnbounded(new EflWalkToSegment(9, 5));
//    seq(new EflEncounterSegment(MAROWAK, LEFT));
//    save("tmp5");
    load("tmp5");
    seq(new EflCatchMonSegment());
    seq(new EflWalkToSegment(13, 13)); // water
    seq(new EflFishSegment(SEADRA));
    seq(new EflCatchMonSegment());
    seq(new EflFishSegment(SEAKING));
    seq(new EflCatchMonSegment());
    seq(new EflSelectMonSegment(DRAGONAIR).fromOverworld().andSurf());
    for(int i=0;i<2;i++)
      seqMove(new EflWalkStep(RIGHT, true));
    for(int i=0;i<2;i++)
      seqMove(new EflWalkStep(DOWN, true));
    for(int i=0;i<4;i++)
      seqMove(new EflWalkStep(RIGHT, true));
    for(int i=0;i<5;i++)
      seqMove(new EflWalkStep(UP, true));
    seq(new EflUseBikeSegment().fromOverworld());
    seq(new EflWalkToSegment(27, 7)); // water
    seq(new EflSelectMonSegment(DRAGONAIR).fromOverworld().andSurf());
    for(int i=0;i<3;i++)
      seqMove(new EflWalkStep(DOWN, true));
    for(int i=0;i<4;i++)
      seqMove(new EflWalkStep(LEFT, true));
    for(int i=0;i<5;i++)
      seqMove(new EflWalkStep(DOWN, true));
    for(int i=0;i<4;i++)
      seqMove(new EflWalkStep(RIGHT, true));
    seqMove(new EflWalkStep(UP, true));
    seq(new EflWalkToSegment(27, 14)); // Mewtwo
    seqMove(new EflOverworldInteract(1)); // Mewtwo
    seq(new EflSkipTextsSegment(1)); // Mewtwo
    seq(new EflCatchMonSegment()); // Mewtwo

    seq(new EflSelectItemSegment(ESCAPE_ROPE).fromOverworld().andUse());
    seqEflSkipInput(2);
  }
}
