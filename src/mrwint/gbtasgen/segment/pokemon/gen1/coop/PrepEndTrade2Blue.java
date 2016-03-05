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

public class PrepEndTrade2Blue extends SeqSegment {

	@Override
	public void execute() {
    seqEflButton(Move.A); // continue game
    seqEflButton(Move.START);
    seqEflButton(Move.A);
    seqEflButton(Move.START);
    seqEflButton(Move.A);

    seqMetric(new OutputParty());

	  {
      seq(new EflWalkToSegment(13, 4)); // PC
      seq(new EflWalkToSegment(13, 3, false)); // PC

      seq(new EflSelectItemSegment(RARE_CANDY).fromOverworld().andUse());
      seq(new EflSelectMonSegment(DRAGONAIR));
//      seqEflButton(B); // lvlup to 44
//      seqEflButton(A); // cancel stats
//      seq(new EflSelectItemSegment(RARE_CANDY).andUse());
//      seq(new EflSelectMonSegment(DRAGONAIR));
      seqEflButton(B); // lvlup to 45
      seqEflButton(A); // cancel stats
      seq(new EflCancelMoveLearnSegment()); // Dragon Rage
      seq(new EflSelectItemSegment(RARE_CANDY).andUse());
      seq(new EflSelectMonSegment(DRAGONAIR));
      seqEflButton(B); // lvlup to 46
      seqEflButton(A); // cancel stats
      seq(new EflSelectItemSegment(RARE_CANDY).andUse());
      seq(new EflSelectMonSegment(DRAGONAIR));
      seqEflButton(B); // lvlup to 47
      seqEflButton(A); // cancel stats
      seq(new EflSelectItemSegment(RARE_CANDY).andUse());
      seq(new EflSelectMonSegment(DRAGONAIR));
      seqEflButton(B); // lvlup to 48
      seqEflButton(A); // cancel stats
      seq(new EflSelectItemSegment(RARE_CANDY).andUse());
      seq(new EflSelectMonSegment(DRAGONAIR));
      seqEflButton(B); // lvlup to 49
      seqEflButton(A); // cancel stats
      seq(new EflSelectItemSegment(RARE_CANDY).andUse());
      seq(new EflSelectMonSegment(DRAGONAIR));
      seqEflButton(B); // lvlup to 50
      seqEflButton(A); // cancel stats
      seq(new EflSelectItemSegment(RARE_CANDY).andUse());
      seq(new EflSelectMonSegment(DRAGONAIR));
      seqEflButton(B); // lvlup to 51
      seqEflButton(A); // cancel stats
      seq(new EflSelectItemSegment(RARE_CANDY).andUse());
      seq(new EflSelectMonSegment(KABUTO));
      seqEflButton(B); // lvlup to 40
      seqEflButton(A); // cancel stats
      seq(new EflEvolutionSegment()); // Kabutops
      seqEflButton(B); // cancel
      seqEflButton(START); // cancel
      seqEflSkipInput(1);
      {
        seqEflButton(A); // use PC
        seq(new EflSkipTextsSegment(1)); // turned on
        seqEflButton(A); // someone's PC
        seq(new EflSkipTextsSegment(2)); // accessed, mon storage
//        seq(new EflChangeMonBoxSegment(1)); // box 2
        seq(new EflDepositMonSegment(RAPIDASH));
        seq(new EflDepositMonSegment(OMASTAR));
        seq(new EflDepositMonSegment(ARBOK));
        seq(new EflDepositMonSegment(JOLTEON));
//        seq(new EflDepositMonSegment(KABUTOPS));
        seq(new EflWithdrawMonSegment(SANDSLASH));
        seq(new EflWithdrawMonSegment(RHYDON));
        seq(new EflWithdrawMonSegment(KADABRA));
        seq(new EflWithdrawMonSegment(HYPNO));
//        seq(new EflWithdrawMonSegment(DODRIO));
        seqEflButton(B, MENU); // cancel
        seqEflButton(B, PRESSED); // cancel
      }
      seqMetric(new OutputBoxMons());
      seqMetric(new OutputParty());
      seqMetric(new OutputItems());

//      seqEflSkipInput(1);
//      seq(new EflSelectItemSegment(FIRE_STONE).fromOverworld().andUse());
//      seq(new EflSelectMonSegment(VULPIX));
//      seq(new EflEvolutionSegment()); // Ninetales
//      seq(new EflSelectItemSegment(WATER_STONE).andUse());
//      seq(new EflSelectMonSegment(POLIWHIRL));
//      seq(new EflEvolutionSegment()); // Poliwrath
//      seq(new EflSelectItemSegment(LEAF_STONE).andUse());
//      seq(new EflSelectMonSegment(WEEPINBELL));
//      seq(new EflEvolutionSegment()); // Victreebel
//      seq(new EflSelectItemSegment(WATER_STONE).andUse());
//      seq(new EflSelectMonSegment(EEVEE));
//      seq(new EflEvolutionSegment()); // Vaporeon
//      seqEflButton(B); // cancel
//      seqEflButton(START); // cancel
//      seqEflSkipInput(1);
//      {
//        seqEflButton(A); // use PC
//        seq(new EflSkipTextsSegment(1)); // turned on
//        seqEflButton(A); // someone's PC
//        seq(new EflSkipTextsSegment(2)); // accessed, mon storage
//        seq(new EflDepositMonSegment(VICTREEBEL));
//        seq(new EflWithdrawMonSegment(HITMONLEE));
//        seq(new EflChangeMonBoxSegment(4)); // box 5
//        seq(new EflDepositMonSegment(POLIWRATH));
//        seq(new EflDepositMonSegment(NINETALES));
//        seq(new EflWithdrawMonSegment(PORYGON));
//        seq(new EflWithdrawMonSegment(MEWTWO));
//        seqEflButton(B, MENU); // cancel
//        seqEflButton(B, PRESSED); // cancel
//      }

      seq(new EflWalkToSegment(11, 4)); // trade
      seq(new EflWalkToSegment(11, 3)); // trade
    }
  }
}
