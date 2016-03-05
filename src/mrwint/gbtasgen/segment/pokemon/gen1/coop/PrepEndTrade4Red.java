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

public class PrepEndTrade4Red extends SeqSegment {

	@Override
	public void execute() {
//    seqEflButton(Move.A); // continue game
//    seqEflButton(Move.START);
//    seqEflButton(Move.A);
//    seqEflButton(Move.START);
//    seqEflButton(Move.A);
//
//    seqMetric(new OutputParty());
//    seqMetric(new OutputBoxMons());
//
//    seq(new EflWalkToSegment(13, 4)); // PC
//    seq(new EflWalkToSegment(13, 3, false)); // PC
//
//    seq(new EflSelectItemSegment(WATER_STONE).fromOverworld().andUse());
//    seq(new EflSelectMonSegment(SHELLDER));
//    seq(new EflEvolutionSegment()); // Cloyster
//    seqEflSkipInput(0);
//    seq(new EflSelectItemSegment(THUNDER_STONE).andUse());
//    seq(new EflSelectMonSegment(PIKACHU));
//    seq(new EflEvolutionSegment()); // Raichu
//    seqEflSkipInput(0);
//    seq(new EflSelectItemSegment(RARE_CANDY).andUse());
//    seq(new EflSelectMonSegment(IVYSAUR));
//    seqEflButton(B); // lvlup to 30
//    seqEflButton(A); // cancel stats
//    seq(new EflCancelMoveLearnSegment()); // Razor Leaf
//    seq(new EflSelectItemSegment(RARE_CANDY).andUse());
//    seq(new EflSelectMonSegment(IVYSAUR));
//    seqEflButton(B); // lvlup to 31
//    seqEflButton(A); // cancel stats
//    seq(new EflSelectItemSegment(RARE_CANDY).andUse());
//    seq(new EflSelectMonSegment(IVYSAUR));
//    seqEflButton(B); // lvlup to 32
//    seqEflButton(A); // cancel stats
//    seq(new EflEvolutionSegment()); // Venusaur
//    seqEflButton(B); // cancel
//    seqEflButton(START); // cancel
//    seqEflSkipInput(1);
//    {
//      seqEflButton(A); // use PC
//      seq(new EflSkipTextsSegment(1)); // turned on
//      seqEflButton(A); // someone's PC
//      seq(new EflSkipTextsSegment(2)); // accessed, mon storage
//      seq(new EflDepositMonSegment(CLOYSTER));
//      seq(new EflDepositMonSegment(RAICHU));
//      seq(new EflDepositMonSegment(MACHAMP));
//      seq(new EflDepositMonSegment(DODRIO));
//      seq(new EflDepositMonSegment(HITMONLEE));
//      seq(new EflWithdrawMonSegment(JYNX));
//      seq(new EflChangeMonBoxSegment(1)); // box 2
//      seq(new EflWithdrawMonSegment(SCYTHER));
//      seq(new EflWithdrawMonSegment(POLIWHIRL));
//      seqEflButton(B, MENU); // cancel
//      seqEflButton(B, PRESSED); // cancel
//    }
//    seqMetric(new OutputBoxMons());
//    seqMetric(new OutputParty());
//    seqMetric(new OutputItems());
//
//    seqEflSkipInput(1);
//    seq(new EflSelectItemSegment(WATER_STONE).fromOverworld().andUse());
//    seq(new EflSelectMonSegment(POLIWHIRL));
//    seq(new EflEvolutionSegment()); // Poliwrath
//    seqEflButton(B); // cancel
//    seqEflButton(START); // cancel
//    seqEflSkipInput(1);
//    {
//      seqEflButton(A); // use PC
//      seq(new EflSkipTextsSegment(1)); // turned on
//      seqEflButton(A); // someone's PC
//      seq(new EflSkipTextsSegment(2)); // accessed, mon storage
//      seq(new EflChangeMonBoxSegment(0)); // box 1
//      seq(new EflDepositMonSegment(POLIWRATH));
//      seq(new EflWithdrawMonSegment(GASTLY));
//      seq(new EflWithdrawMonSegment(HAUNTER));
//      seq(new EflWithdrawMonSegment(GYARADOS));
//      seqEflButton(B, MENU); // cancel
//      seqEflButton(B, PRESSED); // cancel
//    }
//    
//    save("pet41");
//    load("pet41");
//    
    {
//      seqUnbounded(new EflWalkToSegment(4, 6));
//      seqUnbounded(new EflWalkToSegment(4, 8, false));
//      seqUnbounded(new EflUseBikeSegment().fromOverworld());
//      seqUnbounded(new EflWalkToSegment(50, 10));
//      seqUnbounded(new EflWalkToSegment(12, 10, false));
//      seqUnbounded(new EflWalkToSegment(6, 4, false));
//      seqUnbounded(new EflWalkToSegment(20, 10));
//      seqUnbounded(new EflWalkToSegment(3, 18));
//      seqUnbounded(new EflWalkToSegment(3, 17));
//
//      save("tmp");
//      load("tmp");
//      
//      StateBuffer.pushBufferSize(256);
//      for (int i = 0; i < 3; i++)
//        delayEfl(new SeqSegment() {
//          @Override
//          protected void execute() {
//            seqMetric(Metric.forAddress(0xc186), Comparator.LESSER_EQUAL, 0x7c);
//            seqMoveUnboundedNoDelay(new EflWalkStep(LEFT, true));
//            seqEflSkipInput(1);
////            seqMetric(Metric.forAddress(0xc288), Comparator.LESSER_EQUAL, 0x30);
//          }
//        });
//      delayEfl(new SeqSegment() {
//        @Override
//        protected void execute() {
//          seqMetric(Metric.forAddress(0xc186), Comparator.LESSER_EQUAL, 0x78);
//          seqMoveUnboundedNoDelay(new EflWalkStep(LEFT, true));
//        }
//      });
//      StateBuffer.popBufferSize();
//
//      save("tmp2");
      load("tmp2");
      
      seqEflSkipInput(50); // ???
      seq(new EflWalkToSegment(20, 6));
      seq(new EflWalkToSegment(2, 14));
      seqEflSkipInput(50); // ???
      
//      seq(new EflWalkToSegment(20, 9));
      seq(new EflWalkToSegment(0, 18));
      seq(new EflWalkToSegment(-1, 18));
      seq(new EflWalkToSegment(17, 10, false));
      seq(new EflWalkToSegment(-1, 4, false));
      seq(new EflUseBikeSegment().fromOverworld());
      seq(new EflWalkToSegment(-1, 3));
      seq(new EflWalkToSegment(41, 9));
    }
      
      
    seq(new EflWalkToSegment(11, 4)); // trade
    seq(new EflWalkToSegment(11, 3)); // trade
//    seq(new EflWalkToSegment(11, 2, false)); // trade
  }
}
