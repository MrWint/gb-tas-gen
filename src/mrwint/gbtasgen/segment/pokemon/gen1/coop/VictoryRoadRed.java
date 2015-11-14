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
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.BELLSPROUT;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.CLOYSTER;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DEWGONG;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DRAGONAIR;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.EKANS;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.ESCAPE_ROPE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.EXEGGCUTE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.EXEGGUTOR;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.FARFETCHD;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.FISSURE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.GEODUDE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.GOLBAT;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.GOLDEEN;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.GOLDUCK;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.GRIMER;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.GROWL;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.GROWLITHE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.GYARADOS;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.HM03;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.HM04;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.HORSEA;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.KABUTO;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.KINGLER;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.KOFFING;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.LEAF_STONE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.LEER;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.MAGIKARP;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.MAGMAR;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.MANKEY;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.MUK;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.NIDORINA;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.NIDORINO;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.OMANYTE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.OMASTAR;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PIDGEOTTO;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PIDGEY;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PINSIR;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.POISON_STING;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.POLIWAG;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.POLIWHIRL;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.POLIWRATH;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PONYTA;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PSYDUCK;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.RARE_CANDY;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.RATTATA;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.ROAR;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SEADRA;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SEAKING;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SEEL;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SHELLDER;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SLOWBRO;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SLOWPOKE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.STARMIE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.STARYU;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TACKLE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TAIL_WHIP;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TANGELA;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TENTACOOL;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TENTACRUEL;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.WARTORTLE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.WATER_STONE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.WEEZING;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.WHIRLWIND;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.X_SPEED;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.MENU;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.PRESSED;

import java.util.Collections;

import com.sun.org.apache.xpath.internal.operations.Equals;

import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.metric.comparator.Comparator;
import mrwint.gbtasgen.metric.pokemon.CheckEncounterMetric;
import mrwint.gbtasgen.metric.pokemon.CheckFissureMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckNoAIMoveNew;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckSwitchAndTeleportEffectUsed;
import mrwint.gbtasgen.metric.pokemon.gen1.Gen1CheckDVMetric;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputBoxMons;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputDaycareMon;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputItems;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputParty;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.pokemon.gen1.EflOverworldInteract;
import mrwint.gbtasgen.move.pokemon.gen1.EflWalkStep;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.EflCatchMonSegment;
import mrwint.gbtasgen.segment.pokemon.EflEvolutionSegment;
import mrwint.gbtasgen.segment.pokemon.EflLearnTMSegment;
import mrwint.gbtasgen.segment.pokemon.EflOverrideMoveSegment;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.pokemon.EflWalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflCheckMoveOrderMetric;
import mrwint.gbtasgen.segment.pokemon.fight.EflEndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflHitMetricSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflInitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment.EflEnemyMoveDesc;
import mrwint.gbtasgen.segment.pokemon.fight.EflNewEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflSwitchPokemonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflUseBattleItemSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.Constants;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflBuyItemSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflCancelMoveLearnSegment;
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
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.state.StateBuffer;
import mrwint.gbtasgen.util.EflUtil.PressMetric;

public class VictoryRoadRed extends SeqSegment {

	@Override
	public void execute() {
//    seqMetric(new OutputParty());
//    seqMetric(new OutputItems());
//    seqMetric(new OutputBoxMons());
    seqMetric(new OutputDaycareMon());
//
//    seqUnbounded(new EflSelectMonSegment(FARFETCHD).fromOverworld().andFlyTo(-3)); // Cerulean
//    seqEflSkipInput(1);
//
//    {
//      seqUnbounded(new EflWalkToSegment(19, 17)); // enter center
//      seqUnbounded(new EflWalkToSegment(13, 5)); // PC
//      seq(new EflWalkToSegment(13, 4)); // PC
//      {
//        seqEflButton(A); // use PC
//        seq(new EflSkipTextsSegment(1)); // turned on
//        seqEflButton(A); // someone's PC
//        seq(new EflSkipTextsSegment(2)); // accessed, mon storage
//        seq(new EflDepositMonSegment(EXEGGUTOR));
//        seqEflButtonUnboundedNoDelay(B, MENU); // cancel
//        seqEflButtonUnboundedNoDelay(B, PRESSED); // cancel
//      }
//      seqMetric(new OutputBoxMons());
//
//      seqUnbounded(new EflWalkToSegment(4, 6)); // leave center
//      seq(new EflWalkToSegment(4, 8, false)); // leave center
//    }
//
//    save("vr0");
//    load("vr0");
//
//    seq(new EflUseBikeSegment().fromOverworld());
//    seq(new EflWalkToSegment(19, 26)); // bush
//    seq(new EflWalkToSegment(19, 27)); // bush
//    seq(new EflSelectMonSegment(FARFETCHD).fromOverworld().andCut());
//    seq(new EflWalkToSegment(19, 33, false)); // ledge
//    seq(new EflWalkToSegment(19, 36)); // Route 5
//    seq(new EflWalkToSegment(9, 3, false)); // ledge
//    seq(new EflWalkToSegment(9, 7, false)); // ledge
//    seq(new EflWalkToSegment(9, 11, false)); // ledge
//    seq(new EflWalkToSegment(9, 15, false)); // ledge
//    {
//      seq(new EflWalkToSegment(6, 22)); // corner
//      seq(new EflWalkToSegment(13, 22)); // corner
//      seq(new EflWalkToSegment(6, 22)); // corner
//      seq(new EflWalkToSegment(13, 22)); // corner
//      seq(new EflWalkToSegment(6, 22)); // corner
//    }
//    seq(new EflWalkToSegment(10, 21)); // Daycare
//    seq(new EflWalkToSegment(2, 4)); // man
//    seqMove(new EflOverworldInteract(1)); // man
//    seq(new EflSkipTextsSegment(4)); // take back
//    seq(new EflSkipTextsSegment(1, true)); // Magikarp
//    seq(new EflSkipTextsSegment(2)); // gat Magikarp back
//    seq(new EflWalkToSegment(2, 8, false)); // leave house
//
//    seqMetric(new OutputParty()); // needs 4619 exp
//
//    seq(new EflSelectMonSegment(FARFETCHD).fromOverworld().andFlyTo(-1)); // Viridian
//    seqEflSkipInput(1);
//
//    save("vr1");
//    load("vr1");
//
//    seqMetric(new OutputParty());
//
//    seq(new EflUseBikeSegment().fromOverworld());
//    seq(new EflWalkToSegment(18, -1)); // Route 2
//    seq(new EflWalkToSegment(11, 60)); // bush
//    seq(new EflSelectMonSegment(FARFETCHD).fromOverworld().andCut());
//    seq(new EflWalkToSegment(13, 55)); // Moon Stone
//    seqMove(new EflOverworldInteract(1)); // Moon Stone
//    seq(new EflTextSegment()); // Moon Stone
//    seq(new EflWalkToSegment(8, 72)); // Viridian
//    seq(new EflWalkToSegment(32, 7)); // enter gym
//
//    seq(new EflWalkToSegment(15, 5)); // engage
//    seq(new EflInitFightSegment(1)); // start fight
//    seq(new EflSwitchPokemonSegment(TENTACOOL, EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), TAIL_WHIP)));
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[2][0] = 1; // Water Gun
//      kems.numExpGainers = 5; // Omanyte, boosted lvlup to 40, Tentacool, lvlup to 31
//      seq(kems); // rhyhorn
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//    seq(new EflEvolutionSegment()); // Omastar
//    seq(new EflEvolutionSegment()); // Tentacruel
//
//    save("vr2");
//    load("vr2");
//
//    seq(new EflSelectMonSegment(OMASTAR).fromOverworld().andSwitchWith(KABUTO));
//    seqEflButton(B);
//    seqEflButton(START);
//    seq(new EflWalkToSegment(10, 4)); // engage
//    seq(new EflInitFightSegment(2)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), LEER)};
//      kems.attackCount[2][1] = 2; // surf crit
//      seq(kems); // machoke
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), LEER)};
//      kems.attackCount[2][0] = 2; // surf
//      seq(kems); // machop
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), LEER)};
//      kems.attackCount[2][1] = 2; // surf crit
//      kems.numExpGainers = 2; // kabuto lvlup to 32
//      seq(kems); // machoke
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//
//    save("vr3");
//    load("vr3");
//
//    seq(new EflSelectMonSegment(KABUTO).fromOverworld().andSwitchWith(MAGIKARP));
//    seqEflButton(B);
//    seqEflButton(START);
//
//    seq(new EflWalkToSegment(17, 18, false)); // leave gym
//    seq(new EflWalkToSegment(32, 7)); // enter gym
//    seq(new EflWalkToSegment(15, 5)); // engage
//
//    seq(new EflWalkToSegment(2, 3)); // engage
//    seq(new EflWalkToSegment(2, 2)); // engage giovanni
//    seqMove(new EflOverworldInteract(1));
//    seq(new EflInitFightSegment(10)); // start fight
//    seq(new EflSwitchPokemonSegment(KABUTO, EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), TAIL_WHIP)));
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[2][0] = 1; // surf
//      kems.numExpGainers = 3; // magikarp, lvlup to 16, kabuto
//      seq(kems); // rhyhorn
//    }
//    save("tmp");
//    load("tmp");
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      seqEflButton(A, PRESSED);
//      seqEflSkipInput(1);
//      delayEfl(new SeqSegment() {
//        @Override
//        protected void execute() {
//          seqEflButtonUnbounded(A); // surf
//          seqMetric(new EflCheckMoveOrderMetric(false)); // slower, any move
//          seqMetric(new CheckNoAIMoveNew(), Comparator.EQUAL, 0); // use Guard spec
//        }
//      });
//      seq(new EflSkipTextsSegment(1)); // use Guard spec
//      seq(new EflTextSegment()); // on Dugtrio
//      delayEfl(new SeqSegment() {
//        @Override
//        protected void execute() {
//          EflHitMetricSegment hit = new EflHitMetricSegment(true, false, true, true, 0, false, false);
//
//          seqEflButtonUnbounded(B); // close text
//          seq(new Segment() {
//            @Override
//            public StateBuffer execute(StateBuffer in) {
//              return hit.execute(in, 88);
//            }
//          });
//          if (hit.getFinishSegment() != null)
//            seq(hit.getFinishSegment());
//        }
//      });
//      seq(new EflSkipTextsSegment(2)); // fainted, xp
//    }
//    save("tmp2");
//    load("tmp2");
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), TAIL_WHIP)};
//      kems.attackCount[2][0] = 1; // surf
//      kems.attackCount[2][1] = 1; // surf crit
//      kems.numExpGainers = 2; // kabuto, lvlup to 29
//      seq(kems); // nidoqueen
//    }
//    save("tmp3");
//    load("tmp3");
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    seq(new EflSwitchPokemonSegment(MAGIKARP, EflEnemyMoveDesc.missWith(TACKLE)));
//    seq(new EflSwitchPokemonSegment(KABUTO, EflEnemyMoveDesc.missWith(TACKLE)));
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(TACKLE)};
//      kems.attackCount[2][0] = 1; // surf
//      kems.attackCount[2][1] = 1; // surf crit
//      kems.numExpGainers = 3; // magikarp, lvlup to 17, kabuto
//      seq(kems); // nidoking
//    }
//    save("tmp4");
//    load("tmp4");
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    seq(new EflSwitchPokemonSegment(MAGIKARP, EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), TAIL_WHIP)));
//    save("tmp5");
//    load("tmp5");
//    seq(new EflSwitchPokemonSegment(KABUTO, EflEnemyMoveDesc.missWith(new CheckFissureMisses(), FISSURE)));
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(FISSURE)};
//      kems.attackCount[2][1] = 1; // surf crit
//      kems.numExpGainers = 3; // magikarp, lvlup to 18, kabuto
//      seq(kems); // rhydon
//    }
//
//    save("vr4");
//    load("vr4");
//
//    seq(new EflSkipTextsSegment(1 + 4)); // defeat, texts
//    seq(new EflTextSegment()); // earth badge
//    seq(new EflSkipTextsSegment(1)); // got money
//    seq(new EflSkipTextsSegment(14)); // after battle texts
//
//    seq(new EflWalkToSegment(15, 5)); // avoid spins
//    seq(new EflWalkToSegment(17, 16)); // leave gym
//    seq(new EflWalkToSegment(17, 18, false)); // leave gym
//
////    seqMetric(new OutputParty());
////    seqMetric(new OutputItems());
////    seqMetric(new OutputBoxMons());
//
//    seq(new EflUseBikeSegment().fromOverworld());
//    seq(new EflWalkToSegment(32, 9, false)); // ledge
//    seqUnbounded(new EflWalkToSegment(23, 25)); // center
//    seqUnbounded(new EflWalkToSegment(13, 5)); // PC
//    seq(new EflWalkToSegment(13, 4)); // PC
//
//    save("vr5");
//    load("vr5");
//
//    seq(new EflSelectItemSegment(RARE_CANDY).fromOverworld().andUse());
//    seq(new EflSelectMonSegment(MAGIKARP));
//    seqEflButton(B); // grew to 19 (no delay)?
//    seqEflButton(A); // grew to 19 stats
//    seq(new EflSelectItemSegment(RARE_CANDY).andUse());
//    seq(new EflSelectMonSegment(MAGIKARP));
//    seqEflButton(B); // grew to 20 (no delay)?
//    seqEflButton(A); // grew to 20 stats
//    seq(new EflEvolutionSegment()); // Gyarados
//    seq(new EflSkipTextsSegment(1, true)); // learned bite
//    seqEflButton(B);
//    seqEflButton(START);
//
//    seqMetric(new OutputParty());
//    seqMetric(new OutputBoxMons());
//    {
//      seqEflButton(A); // use PC
//      seq(new EflSkipTextsSegment(1)); // turned on
//      seqEflButton(A); // someone's PC
//      seq(new EflSkipTextsSegment(2)); // accessed, mon storage
//      seq(new EflDepositMonSegment(GYARADOS));
//      seq(new EflDepositMonSegment(TENTACRUEL));
//      seq(new EflWithdrawMonSegment(STARYU));
//      seq(new EflWithdrawMonSegment(SHELLDER));
//      seqEflButtonUnboundedNoDelay(B, MENU); // cancel
//      seqEflButtonUnboundedNoDelay(B, PRESSED); // cancel
//    }
//    seqEflSkipInput(1);
//    seq(new EflSelectItemSegment(WATER_STONE).fromOverworld().andUse());
//    seq(new EflSelectMonSegment(SHELLDER));
//    seq(new EflEvolutionSegment()); // Cloyster
//    seq(new EflSelectItemSegment(WATER_STONE).andUse());
//    seq(new EflSelectMonSegment(STARYU));
//    seq(new EflEvolutionSegment()); // Starmie
//    seqEflButton(B);
//    seqEflButton(START);
//    {
//      seqEflButton(A); // use PC
//      seq(new EflSkipTextsSegment(1)); // turned on
//      seqEflButton(A); // someone's PC
//      seq(new EflSkipTextsSegment(2)); // accessed, mon storage
//      seq(new EflDepositMonSegment(STARMIE));
//      seq(new EflDepositMonSegment(CLOYSTER));
//      seq(new EflWithdrawMonSegment(GYARADOS));
//      seq(new EflChangeMonBoxSegment(2)); // box 3
//      seq(new EflWithdrawMonSegment(BELLSPROUT));
//      seq(new EflChangeMonBoxSegment(4)); // box 5
//      seqEflButtonUnboundedNoDelay(B, MENU); // cancel
//      seqEflButtonUnboundedNoDelay(B, PRESSED); // cancel
//    }
//
//    seqUnbounded(new EflWalkToSegment(4, 6)); // leave center
//    seq(new EflWalkToSegment(4, 8, false)); // leave center
//
//    save("vr6");
//    load("vr6");
//
//    seqMetric(new OutputParty());
//    seqMetric(new OutputBoxMons());
//
//    seq(new EflSelectMonSegment(FARFETCHD).fromOverworld().andSwitchWith(BELLSPROUT));
//    seqEflButton(B);
//    seq(new EflUseBikeSegment().fromMainMenu());
//
//    seq(new EflWalkToSegment(14, 26)); // water
//    seq(new EflFishSegment(POLIWAG));
//    seq(new EflCatchMonSegment());
//
//    seqUnbounded(new EflWalkToSegment(-1, 17)); // ledge
//
//    seqUnbounded(new EflWalkToSegment(31, 12)); // align
//    seqUnbounded(new EflWalkToSegment(31, 11)); // align
//    seq(new EflEncounterSegment(RATTATA, UP));
//    seq(new EflCatchMonSegment().noNew()); // TODO: remove noNew
//
//    save("vr7");
//    load("vr7");
//
//    seq(new EflWalkToSegment(29, 5)); // engage
//    seq(new EflInitFightSegment(10)); // start fight
//    seq(new EflSwitchPokemonSegment(PONYTA, EflEnemyMoveDesc.missWith(Metric.TRUE, WHIRLWIND)));
//    save("tmp");
//    load("tmp");
//    seq(new EflUseBattleItemSegment(X_SPEED, EflEnemyMoveDesc.missWith(Metric.TRUE, WHIRLWIND)));
//    save("tmp2");
//    load("tmp2");
//    seqEflButton(UP | A, PRESSED); // fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.skipFirstMainBattleMenu = true;
//      kems.attackCount[0][1] = 1; // horn drill
//      kems.numExpGainers = 5; // bellsprout, boosted, lvlup to 23, ponyta, lvlup to 38
//      seq(kems); // pidgeot
//    }
//    save("tmp3");
//    load("tmp3");
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[0][1] = 1; // horn drill
//      seq(kems); // rhyhorn
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[0][1] = 1; // horn drill
//      seq(kems); // exeggcute
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[0][1] = 1; // horn drill
//      kems.numExpGainers = 2; // ponyta, lvlup to 39
//      seq(kems); // gyarados
//    }
//    seq(new EflCancelMoveLearnSegment()); // Fire Spin
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[2][0] = 2; // Stomp
//      kems.attackCount[2][1] = 1; // Stomp crit
////      kems.attackCount[2][1] = 2; // Stomp crit // needs 11+ Atk
//      seq(kems); // alakazam
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[0][1] = 1; // horn drill
//      kems.numExpGainers = 2; // ponyta, lvlup to 40
//      seq(kems); // rapidash
//    }
//    seq(new EflEndFightSegment(2)); // player defeated enemy
//
//    save("vr8");
//    load("vr8");
//
//    seq(new EflEvolutionSegment()); // Weepinbell
//    seq(new EflEvolutionSegment()); // Rapidash
//
//    seq(new EflSkipTextsSegment(5)); // after battle text
//
//    seqMetric(new OutputParty());
//
//    seq(new EflWalkToSegment(21, 7, false)); // jump ledge
//    seq(new EflWalkToSegment(8, 5)); // enter house
//    seq(new EflWalkToSegment(4, 2)); // badge check
//    seq(new EflSkipTextsSegment(2)); // badge check
//    seq(new EflWalkToSegment(4, 0)); // leave house
//    seq(new EflUseBikeSegment().fromOverworld());
//    seq(new EflWalkToSegment(7, 136)); // badge check
//    seq(new EflSkipTextsSegment(4)); // badge check
//    seq(new EflWalkToSegment(9, 119)); // badge check
//    seq(new EflSkipTextsSegment(4)); // badge check
//    seq(new EflWalkToSegment(10, 105)); // badge check
//    seq(new EflSkipTextsSegment(4)); // badge check
//    seq(new EflWalkToSegment(10, 104)); // water
//    seq(new EflFishSegment(SEAKING));
//    seq(new EflCatchMonSegment());
//    seq(new EflFishSegment(KINGLER));
//    seq(new EflCatchMonSegment());
//    seq(new EflSelectMonSegment(OMASTAR).fromOverworld().andSurf());
//    seqEflSkipInput(1);
//
//    save("vr9");
//    load("vr9");
//
//    for(int i=0;i<7;i++)
//      seqMove(new EflWalkStep(Move.UP, true));
//    seq(new EflSkipTextsSegment(4)); // badge check
//    for(int i=0;i<3;i++)
//      seqMove(new EflWalkStep(Move.UP, true));
//    for(int i=0;i<3;i++)
//      seqMove(new EflWalkStep(Move.LEFT, true));
//    for(int i=0;i<8;i++)
//      seqMove(new EflWalkStep(Move.UP, true));
//    seq(new EflSkipTextsSegment(4)); // badge check
//    for(int i=0;i<10;i++)
//      seqMoveUnboundedNoDelay(new EflWalkStep(Move.UP, true));
//    seqMoveUnboundedNoDelay(new EflWalkStep(Move.RIGHT, true));
//    for(int i=0;i<4;i++)
//      seqMoveUnboundedNoDelay(new EflWalkStep(Move.UP, true));
//    seq(new EflUseBikeSegment().fromOverworld());
//    seq(new EflWalkToSegment(12, 57)); // badge check
//    seq(new EflWalkToSegment(12, 56)); // badge check
//    seq(new EflSkipTextsSegment(4)); // badge check
//    seq(new EflWalkToSegment(7, 35)); // badge check
//    seq(new EflSkipTextsSegment(4)); // badge check
//    seq(new EflWalkToSegment(4, 31)); // enter victory road
//
//    save("vr10");
//    load("vr10");
//
//    seq(new EflSelectItemSegment(HM04).fromOverworld().andUse());
//    seq(new EflLearnTMSegment(GYARADOS)); // learn Strength
//    seqEflButton(B);
//    seq(new EflSelectMonSegment(GYARADOS).fromMainMenu().andStrength());
//
//    seqUnbounded(new EflWalkToSegment(5, 14));
//    seqUnbounded(new EflWalkToSegment(5, 15, false));
//    seqUnbounded(new EflWalkToSegment(5, 15, false));
//    seqUnbounded(new EflWalkToSegment(4, 16));
//    seqUnbounded(new EflWalkToSegment(5, 16, false));
//    seqUnbounded(new EflWalkToSegment(5, 16, false));
//    seqUnbounded(new EflWalkToSegment(6, 16, false));
//    seqUnbounded(new EflWalkToSegment(7, 16, false));
//    seqUnbounded(new EflWalkToSegment(8, 16, false));
//    seqUnbounded(new EflWalkToSegment(9, 17));
//    seqUnbounded(new EflWalkToSegment(9, 16, false));
//    seqUnbounded(new EflWalkToSegment(9, 16, false));
//    seqUnbounded(new EflWalkToSegment(9, 15, false));
//    seqUnbounded(new EflWalkToSegment(8, 14));
//    seqUnbounded(new EflWalkToSegment(9, 14, false));
//    seqUnbounded(new EflWalkToSegment(9, 14, false));
//    seqUnbounded(new EflWalkToSegment(10, 14, false));
//    seqUnbounded(new EflWalkToSegment(11, 14, false));
//    seqUnbounded(new EflWalkToSegment(12, 14, false));
//    seqUnbounded(new EflWalkToSegment(13, 14, false));
//    seqUnbounded(new EflWalkToSegment(14, 14, false));
//    seqUnbounded(new EflWalkToSegment(15, 14, false));
//    seqUnbounded(new EflWalkToSegment(16, 15));
//    seqUnbounded(new EflWalkToSegment(16, 14, false));
//    seqUnbounded(new EflWalkToSegment(16, 14, false));
//    seqUnbounded(new EflWalkToSegment(16, 13, false));
//    seqUnbounded(new EflWalkToSegment(14, 14));
//    seqUnbounded(new EflWalkToSegment(15, 12));
//    seqUnbounded(new EflWalkToSegment(16, 12, false));
//    seqUnbounded(new EflWalkToSegment(17, 11));
//    seqUnbounded(new EflWalkToSegment(17, 12, false));
//    seqUnbounded(new EflWalkToSegment(17, 12, false));
//
//    seqUnbounded(new EflWalkToSegment(13, 14)); // align
//    save("tmp");
//    load("tmp");
//    seq(new EflEncounterSegment(Constants.ONIX, LEFT));
//    save("tmp2");
//    load("tmp2");
//    seq(new EflCatchMonSegment().withBufferSize(0));
//    seqUnbounded(new EflWalkToSegment(10, 14));
//    seq(new EflEncounterSegment(Constants.GEODUDE, LEFT));
//    save("tmp3");
//    load("tmp3");
//    seq(new EflCatchMonSegment().withBufferSize(0));
//    seqUnbounded(new EflWalkToSegment(9, 15));
//    seqUnbounded(new EflWalkToSegment(8, 15));
//    seq(new EflEncounterSegment(Constants.MACHOKE, LEFT));
//    save("tmp4");
//    load("tmp4");
//    seq(new EflCatchMonSegment().withBufferSize(0));
//    seqUnbounded(new EflWalkToSegment(5, 16));
//    seqUnbounded(new EflWalkToSegment(5, 15));
//    seqUnbounded(new EflWalkToSegment(5, 14));
//    seq(new EflEncounterSegment(Constants.ZUBAT, UP));
//    save("tmp5");
//    load("tmp5");
//    seq(new EflCatchMonSegment().withBufferSize(0));
//    seqUnbounded(new EflWalkToSegment(6, 12));
//    seq(new EflEncounterSegment(Constants.MACHOP, RIGHT));
//    save("tmp6");
//    load("tmp6");
//    seq(new EflCatchMonSegment().withBufferSize(0));
//    seqUnbounded(new EflWalkToSegment(17, 2));
//    seqUnbounded(new EflWalkToSegment(16, 2));
//    seq(new EflEncounterSegment(Constants.MAROWAK, LEFT));
//    save("tmp7");
//    load("tmp7");
//    seq(new EflCatchMonSegment());
//    seq(new EflWalkToSegment(14, 2, false));
//    seq(new EflWalkToSegment(14, 2, false));
//    seq(new EflWalkToSegment(13, 2, false));
//    seq(new EflWalkToSegment(12, 2, false));
//    seqUnbounded(new EflWalkToSegment(11, 3));
//    seqUnbounded(new EflWalkToSegment(11, 2, false));
//    seqUnbounded(new EflWalkToSegment(11, 2, false));
//    seqUnbounded(new EflWalkToSegment(10, 2));
//    seqMoveUnbounded(new EflOverworldInteract(4)); // Rare Candy
//    seqUnbounded(new EflTextSegment()); // Rare Candy
//    seqUnbounded(new EflWalkToSegment(14, 2));
//    seqUnbounded(new EflWalkToSegment(2, 6));
//    seqUnbounded(new EflWalkToSegment(2, 5));
//    seq(new EflEncounterSegment(Constants.GRAVELER, UP));
//    save("tmp8");
//    load("tmp8");
//    seq(new EflCatchMonSegment());
//    seq(new EflWalkToSegment(1, 1)); // ladder up
//
//    save("vr11");
//    load("vr11");
//
//    seq(new EflSelectMonSegment(GYARADOS).fromOverworld().andStrength());
//    seq(new EflWalkToSegment(5, 14));
//    seq(new EflWalkToSegment(4, 14, false));
//    seq(new EflWalkToSegment(4, 14, false));
//    seq(new EflWalkToSegment(3, 13));
//    seq(new EflWalkToSegment(3, 14, false));
//    seq(new EflWalkToSegment(3, 14, false));
//    seq(new EflWalkToSegment(3, 15, false));
//    seq(new EflWalkToSegment(4, 16));
//    seq(new EflWalkToSegment(3, 16, false));
//    seq(new EflWalkToSegment(3, 16, false));
//    seq(new EflWalkToSegment(2, 16, false));
//    seq(new EflWalkToSegment(23, 7)); // ladder up
//    seq(new EflWalkToSegment(2, 0)); // ladder down
//    seq(new EflWalkToSegment(11, 3)); // moltres
//    seq(new EflWalkToSegment(11, 4)); // moltres
//    seqMove(new EflOverworldInteract(6)); // moltres
//    seq(new EflSkipTextsSegment(1));
//    seq(new EflCatchMonSegment()); // moltres
//
//    save("vr12");
    load("vr12");
//    seq(new EflSelectItemSegment(ESCAPE_ROPE).fromOverworld().andUse());
    {
      seq(new EflSelectMonSegment(GYARADOS).fromOverworld().andStrength());
      seq(new EflWalkToSegment(5, 5, false)); // boulder
      seq(new EflWalkToSegment(5, 5));
      seq(new EflWalkToSegment(0, 8)); // ladder down

      seq(new EflSelectMonSegment(GYARADOS).fromOverworld().andStrength());
      seq(new EflWalkToSegment(2, 10, false)); // boulder
      seq(new EflWalkToSegment(2, 10, false)); // boulder
      seq(new EflWalkToSegment(2, 10));
      seq(new EflWalkToSegment(2, 11, false)); // boulder
      seq(new EflWalkToSegment(2, 12, false)); // boulder
      seq(new EflWalkToSegment(8, 16));
      seq(new EflWalkToSegment(8, 18, false)); // out
    }
//  }
//
//  save("vr8");

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
////    load("tmp3");
//    seq(new EflCatchMonSegment().withBufferSize(0).withExtraSkips(30));
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
////    load("tmp6");
//    seq(new EflCatchMonSegment().withBufferSize(0));
//    seqUnbounded(new EflWalkToSegment(25, 11)); // b2f
//    seqUnbounded(new EflWalkToSegment(25, 14)); // b3f
//    seqUnbounded(new EflWalkToSegment(25, 10));
//    seq(new EflEncounterSegment(DEWGONG, UP));
//    save("tmp");
//    load("tmp");
//    seq(new EflCatchMonSegment().withBufferSize(0).withExtraSkips(40));
//    seqUnbounded(new EflWalkToSegment(24, 8));
//    seq(new EflEncounterSegment(SHELLDER, UP));
//    save("tmp2");
////    load("tmp2");
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
//    load("tmp5");
//    seq(new EflCatchMonSegment());
//
//    seq(new EflSelectMonSegment(EXEGGUTOR).fromOverworld().andStrength());
//    seq(new EflWalkToSegment(7, 15));
//    seq(new EflWalkToSegment(6, 14));
//    seq(new EflWalkToSegment(5, 14, false));
//    seq(new EflWalkToSegment(5, 14, false));
//    seq(new EflWalkToSegment(5, 14, false));
//    seq(new EflWalkToSegment(4, 14, false));
//    seq(new EflWalkToSegment(3, 14, false));
//    seq(new EflWalkToSegment(3, 15, false));
//    seq(new EflWalkToSegment(5, 14, false));
//
//    seq(new EflWalkToSegment(9, 14, false));
//    seq(new EflWalkToSegment(9, 14, false));
//    seq(new EflWalkToSegment(9, 14, false));
//    seq(new EflWalkToSegment(9, 13, false));
//    seq(new EflWalkToSegment(8, 13));
//    seq(new EflWalkToSegment(8, 14, false));
//    seq(new EflWalkToSegment(8, 14, false));
//    seq(new EflWalkToSegment(9, 14, false));
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
//    load("sf1");
//    for(int i=0;i<10;i++)
//      seqMove(new EflWalkStep(UP, true));
//    for(int i=0;i<2;i++)
//      seqMove(new EflWalkStep(RIGHT, true));
//    seqMove(new EflWalkStep(UP, true));
//    seqMove(new EflWalkStep(DOWN, false));
//
//    seq(new EflFishSegment(GOLDEEN));
//    seq(new EflCatchMonSegment());
//    seq(new EflFishSegment(STARYU));
//    seq(new EflCatchMonSegment().withExtraSkips(10));
//
//    seq(new EflWalkToSegment(6, 1, false));
//    seqMove(new EflOverworldInteract(3)); // Articuno
//    seq(new EflSkipTextsSegment(1)); // Gyaa
//    seq(new EflCatchMonSegment());
//
//    save("sf2");
//    load("sf2");
//
//    seq(new EflSelectItemSegment(ESCAPE_ROPE).fromOverworld().andUse());
//    seqEflSkipInput(2);
  }
}
