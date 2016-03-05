package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import static mrwint.gbtasgen.move.Move.A;
import static mrwint.gbtasgen.move.Move.B;
import static mrwint.gbtasgen.move.Move.DOWN;
import static mrwint.gbtasgen.move.Move.LEFT;
import static mrwint.gbtasgen.move.Move.RIGHT;
import static mrwint.gbtasgen.move.Move.SELECT;
import static mrwint.gbtasgen.move.Move.START;
import static mrwint.gbtasgen.move.Move.UP;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.*;
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

public class PrepEndTrade3Red extends SeqSegment {

	@Override
	public void execute() {
    seqEflButton(Move.A); // continue game
    seqEflButton(Move.START);
    seqEflButton(Move.A);
    seqEflButton(Move.START);
    seqEflButton(Move.A);

    seqMetric(new OutputParty());
//    seqMetric(new OutputBoxMons());

	  {
      seq(new EflWalkToSegment(13, 4)); // PC
      seq(new EflWalkToSegment(13, 3, false)); // PC

      seq(new EflSelectItemSegment(RARE_CANDY).fromOverworld().andUse());
      seq(new EflSelectMonSegment(DRAGONAIR));
      seqEflButton(B); // lvlup to 52
      seqEflButton(A); // cancel stats
      seq(new EflSelectItemSegment(RARE_CANDY).andUse());
      seq(new EflSelectMonSegment(DRAGONAIR));
      seqEflButton(B); // lvlup to 53
      seqEflButton(A); // cancel stats
      seq(new EflSelectItemSegment(RARE_CANDY).andUse());
      seq(new EflSelectMonSegment(DRAGONAIR));
      seqEflButton(B); // lvlup to 54
      seqEflButton(A); // cancel stats
      seq(new EflSelectItemSegment(RARE_CANDY).andUse());
      seq(new EflSelectMonSegment(DRAGONAIR));
      seqEflButton(B); // lvlup to 55
      seqEflButton(A); // cancel stats
      seq(new EflCancelMoveLearnSegment()); // Hyper Beam
      seq(new EflEvolutionSegment()); // Dragonite
      seqEflButton(B); // cancel
      seqEflButton(START); // cancel
      seqEflSkipInput(1);
      {
        seqEflButton(A); // use PC
        seq(new EflSkipTextsSegment(1)); // turned on
        seqEflButton(A); // someone's PC
        seq(new EflSkipTextsSegment(2)); // accessed, mon storage
        seq(new EflDepositMonSegment(VILEPLUME));
        seq(new EflDepositMonSegment(KABUTOPS));
        seq(new EflDepositMonSegment(SANDSLASH));
        seq(new EflDepositMonSegment(RHYDON));
        seq(new EflDepositMonSegment(HYPNO));
        seq(new EflWithdrawMonSegment(SHELLDER));
        seq(new EflWithdrawMonSegment(PIKACHU));
        seq(new EflWithdrawMonSegment(MAGNEMITE));
        seq(new EflWithdrawMonSegment(LICKITUNG));
        seq(new EflChangeMonBoxSegment(2)); // box 3
        seq(new EflWithdrawMonSegment(AERODACTYL));
//        seq(new EflWithdrawMonSegment(JYNX));
        seqEflButton(B, MENU); // cancel
        seqEflButton(B, PRESSED); // cancel
      }
      seqMetric(new OutputBoxMons());
      seqMetric(new OutputParty());
      seqMetric(new OutputItems());

//      seqEflSkipInput(1);
//      seq(new EflSelectItemSegment(FIRE_STONE).fromOverworld().andUse());
//      seq(new EflSelectMonSegment(GROWLITHE));
//      seq(new EflEvolutionSegment()); // Arcanine
//      seqEflSkipInput(1);
//      seq(new EflSelectItemSegment(LEAF_STONE).andUse());
//      seq(new EflSelectMonSegment(GLOOM));
//      seq(new EflEvolutionSegment()); // Vileplume
//      seqEflSkipInput(1);
//      seq(new EflSelectItemSegment(THUNDER_STONE).andUse());
//      seq(new EflSelectMonSegment(PIKACHU));
//      seq(new EflEvolutionSegment()); // Raichu
//      seqEflButton(B); // cancel
//      seqEflButton(START); // cancel
//      seqEflSkipInput(1);
//      {
//        seqEflButton(A); // use PC
//        seq(new EflSkipTextsSegment(1)); // turned on
//        seqEflButton(A); // someone's PC
//        seq(new EflSkipTextsSegment(2)); // accessed, mon storage
//        seq(new EflChangeMonBoxSegment(4)); // box 5
//        seq(new EflDepositMonSegment(RAICHU));
//        seq(new EflDepositMonSegment(VILEPLUME));
//        seq(new EflDepositMonSegment(ARCANINE));
//        seq(new EflWithdrawMonSegment(GRAVELER));
//        seq(new EflWithdrawMonSegment(MACHOKE));
//        seq(new EflWithdrawMonSegment(ARBOK));
//        seqEflButton(B, MENU); // cancel
//        seqEflButton(B, PRESSED); // cancel
//      }

      seq(new EflWalkToSegment(12, 4)); // trade
      seqEflSkipInput(50);
      seq(new EflWalkToSegment(11, 4)); // trade
      seq(new EflWalkToSegment(11, 3)); // trade
    }
  }
}
