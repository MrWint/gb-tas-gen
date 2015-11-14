package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import static mrwint.gbtasgen.move.Move.A;
import static mrwint.gbtasgen.move.Move.B;
import static mrwint.gbtasgen.move.Move.DOWN;
import static mrwint.gbtasgen.move.Move.LEFT;
import static mrwint.gbtasgen.move.Move.RIGHT;
import static mrwint.gbtasgen.move.Move.SELECT;
import static mrwint.gbtasgen.move.Move.START;
import static mrwint.gbtasgen.move.Move.UP;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.BELLSPROUT;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.CHARMELEON;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.COMET_PUNCH;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.CONFUSION;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DISABLE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DITTO;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DOUBLE_KICK;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DOUBLE_SLAP;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DRAGONAIR;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DRAGON_RAGE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.ELECTABUZZ;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.ELIXER;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.ESCAPE_ROPE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.EXEGGUTOR;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.FARFETCHD;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.FIRE_PUNCH;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.FURY_ATTACK;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.GLOOM;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.HM04;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.IVYSAUR;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.KABUTO;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.LEER;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.MAGNEMITE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.MAGNETON;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.OMANYTE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PIDGEOTTO;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PIKACHU;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.POISON_POWDER;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PONYTA;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PSYBEAM;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PSYCHIC;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.REFLECT;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.ROLLING_KICK;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SAND_ATTACK;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.STUN_SPORE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TAIL_WHIP;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TM07;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.VOLTORB;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.MENU;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.PRESSED;
import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.metric.pokemon.CheckEncounterMetric;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckDisableEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckNoAIMoveNew;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckParalyzeEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckPoisonEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckSwitchAndTeleportEffectUsed;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputBoxMons;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputDaycareMon;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputItems;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputParty;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.pokemon.gen1.EflOverworldInteract;
import mrwint.gbtasgen.move.pokemon.gen1.EflWalkStep;
import mrwint.gbtasgen.move.pokemon.gen1.OverworldInteract;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.EflCatchMonSegment;
import mrwint.gbtasgen.segment.pokemon.EflCatchSafariMonSegment;
import mrwint.gbtasgen.segment.pokemon.EflEvolutionSegment;
import mrwint.gbtasgen.segment.pokemon.EflLearnTMSegment;
import mrwint.gbtasgen.segment.pokemon.EflOverrideMoveSegment;
import mrwint.gbtasgen.segment.pokemon.EflPokecenterSegment;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.pokemon.EflWalkToSegment;
import mrwint.gbtasgen.segment.pokemon.TextSegment;
import mrwint.gbtasgen.segment.pokemon.WalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflCheckMoveOrderMetric;
import mrwint.gbtasgen.segment.pokemon.fight.EflEndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflInitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.InitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.NewEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment.EflEnemyMoveDesc;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment.EnemyMoveDesc;
import mrwint.gbtasgen.segment.pokemon.fight.EflNewEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflSwitchPokemonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.CancelMoveLearnSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.Constants;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflBuyItemSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflCancelMoveLearnSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflDepositMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflEncounterSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflFishSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSelectItemSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSelectMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSellItemSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflUseBikeSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflUseBikeSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflWithdrawMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.UseBikeSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.state.StateBuffer;
import mrwint.gbtasgen.util.EflUtil.PressMetric;

public class PowerplantRed extends SeqSegment {

	@Override
	public void execute() {
////    seqMetric(new OutputParty());
////    seqMetric(new OutputItems());
////    seqMetric(new OutputBoxMons());
//
//    seq(new EflSelectMonSegment(FARFETCHD).fromOverworld().andFlyTo(-3)); // Cerulean
//    seqEflSkipInput(1);
//
//    seq(new EflUseBikeSegment().fromOverworld());
//    seq(new EflWalkToSegment(19, 26)); // go to bush
//    seq(new EflWalkToSegment(19, 27)); // go to bush
//
//    seq(new EflSelectMonSegment(FARFETCHD).fromOverworld().andCut());
//
//    seq(new EflWalkToSegment(40, 17)); // leave cerulean
//    seq(new EflWalkToSegment(4, 8)); // go to bush
//    seq(new EflSelectMonSegment(FARFETCHD).fromOverworld().andCut());
//    seq(new EflWalkToSegment(10, 11, false)); // jump ledge
//    seq(new EflWalkToSegment(51, 5, false)); // jump ledge
//    seq(new EflWalkToSegment(60, 8)); // route 10
//    seq(new EflWalkToSegment(15, 4)); // water
//    seq(new EflSelectMonSegment(OMANYTE).fromOverworld().andSurf());
//    for(int i=0;i<26;i++)
//      seqMoveUnbounded(new EflWalkStep(DOWN, true));
//    seqMoveUnbounded(new EflWalkStep(LEFT, true));
//
//    save("pp0");
//    load("pp0");
//
//    seqUnbounded(new EflUseBikeSegment().fromOverworld());
//    seqUnbounded(new EflWalkToSegment(6, 39)); // enter powerplant

//    seqUnbounded(new EflWalkToSegment(4, 33)); // align
//    seq(new EflEncounterSegment(MAGNEMITE, UP));
//    save("tmp");
//    load("tmp");
//    seq(new EflCatchMonSegment().withBufferSize(0));
//
//    seqUnbounded(new EflWalkToSegment(4, 30)); // align
//    seq(new EflEncounterSegment(ELECTABUZZ, UP));
//    save("tmp2");
//    load("tmp2");
//    seq(new EflCatchMonSegment().withBufferSize(0));
//
//    seqUnbounded(new EflWalkToSegment(4, 27)); // align
//    seq(new EflEncounterSegment(VOLTORB, UP));
//    save("tmp3");
//    load("tmp3");
//    seq(new EflCatchMonSegment().withBufferSize(0));
//
//    seqUnbounded(new EflWalkToSegment(5, 17)); // align
//    seq(new EflEncounterSegment(MAGNETON, UP));
//    save("tmp4");
//    load("tmp4");
//    seq(new EflCatchMonSegment().withBufferSize(0));
//
//    seqUnbounded(new EflWalkToSegment(8, 16)); // align
//    seq(new EflEncounterSegment(PIKACHU, RIGHT));
//    save("tmp5");
//    load("tmp5");
//    seq(new EflCatchMonSegment()); // .noNew()
//
//    seq(new EflWalkToSegment(34, 3, false)); // rare candy
//    seqMove(new EflOverworldInteract(12));
//    seq(new EflTextSegment()); // rare candy
//
//    seq(new EflWalkToSegment(20, 14)); // electrode
//    seqMove(new EflOverworldInteract(7)); // electrode
//    seq(new EflSkipTextsSegment(1)); // startled
//    seq(new EflCatchMonSegment());
//
//    seq(new EflWalkToSegment(4, 7)); // zapdos
//    seq(new EflWalkToSegment(4, 8)); // zapdos
//    seqMove(new EflOverworldInteract(9)); // zapdos
//    seq(new EflSkipTextsSegment(1)); // Gyaa
//    seq(new EflCatchMonSegment());
//
//    seq(new EflWalkToSegment(-1, 11, false)); // leave
  }
}
