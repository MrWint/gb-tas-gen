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

public class PrepEndTrade4Blue extends SeqSegment {

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

      {
        seqEflButton(A); // use PC
        seq(new EflSkipTextsSegment(1)); // turned on
        seqEflButton(A); // someone's PC
        seq(new EflSkipTextsSegment(2)); // accessed, mon storage
        seq(new EflDepositMonSegment(VICTREEBEL));
        seq(new EflDepositMonSegment(DRAGONITE));
        seq(new EflDepositMonSegment(MAGNEMITE));
        seq(new EflDepositMonSegment(LICKITUNG));
        seq(new EflDepositMonSegment(AERODACTYL));
        seq(new EflWithdrawMonSegment(POLIWHIRL));
        seq(new EflWithdrawMonSegment(VULPIX));
        seq(new EflWithdrawMonSegment(BLASTOISE));
        seq(new EflWithdrawMonSegment(TENTACRUEL));
        seq(new EflChangeMonBoxSegment(1)); // box 2
        seq(new EflDepositMonSegment(VAPOREON));
        seq(new EflDepositMonSegment(BLASTOISE));
        seq(new EflWithdrawMonSegment(NIDORINO));
        seq(new EflWithdrawMonSegment(NIDORINA));
        seq(new EflWithdrawMonSegment(EXEGGCUTE));
        seqEflButton(B, MENU); // cancel
        seqEflButton(B, PRESSED); // cancel
      }
      seqMetric(new OutputBoxMons());
      seqMetric(new OutputItems());

      seqEflSkipInput(1);
      seq(new EflSelectItemSegment(WATER_STONE).fromOverworld().andUse());
      seq(new EflSelectMonSegment(POLIWHIRL));
      seq(new EflEvolutionSegment()); // Poliwrath
      seqEflSkipInput(0);
      seq(new EflSelectItemSegment(FIRE_STONE).andUse());
      seq(new EflSelectMonSegment(VULPIX));
      seq(new EflEvolutionSegment()); // Ninetales
      seqEflSkipInput(0);
      seq(new EflSelectItemSegment(MOON_STONE).andUse());
      seq(new EflSelectMonSegment(NIDORINO));
      seq(new EflEvolutionSegment()); // Ninetales
      seqEflSkipInput(0);
      seq(new EflSelectItemSegment(MOON_STONE).andUse());
      seq(new EflSelectMonSegment(NIDORINA));
      seq(new EflEvolutionSegment()); // Arcanine
      seqEflSkipInput(0);
      seq(new EflSelectItemSegment(LEAF_STONE).andUse());
      seq(new EflSelectMonSegment(EXEGGCUTE));
      seq(new EflEvolutionSegment()); // Exeggutor
      seqEflButton(B); // cancel
      seqEflButton(START); // cancel
      seqEflSkipInput(1);
      {
        seqEflButton(A); // use PC
        seq(new EflSkipTextsSegment(1)); // turned on
        seqEflButton(A); // someone's PC
        seq(new EflSkipTextsSegment(2)); // accessed, mon storage
        seq(new EflDepositMonSegment(POLIWRATH));
        seq(new EflDepositMonSegment(NINETALES));
        seq(new EflWithdrawMonSegment(VAPOREON));
        seq(new EflWithdrawMonSegment(BLASTOISE));
        seq(new EflChangeMonBoxSegment(0)); // box 1
        seq(new EflDepositMonSegment(NIDOKING));
        seq(new EflDepositMonSegment(NIDOQUEEN));
        seq(new EflDepositMonSegment(EXEGGUTOR));
        seq(new EflWithdrawMonSegment(BUTTERFREE));
        seq(new EflChangeMonBoxSegment(3)); // box 4
        seq(new EflWithdrawMonSegment(MOLTRES));
        seq(new EflWithdrawMonSegment(GRAVELER));
        seqEflButtonUnbounded(B, MENU); // cancel
        seqEflButtonUnbounded(B, PRESSED); // cancel
      }
//
//      seqEflSkipInput(1);
//      seq(new EflSelectItemSegment(WATER_STONE).fromOverworld().andUse());
//      seq(new EflSelectMonSegment(EEVEE));
//      seq(new EflEvolutionSegment()); // Vaporeon
//      seqEflButton(B); // cancel
//      seqEflButton(START); // cancel

      seq(new EflWalkToSegment(11, 4)); // trade
      seq(new EflWalkToSegment(11, 3)); // trade

      seqMetric(new OutputParty());
	  }
  }
}
