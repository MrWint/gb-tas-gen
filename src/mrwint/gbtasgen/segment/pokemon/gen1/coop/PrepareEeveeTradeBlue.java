package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import mrwint.gbtasgen.metric.pokemon.gen1.CheckDisableEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.pokemon.gen1.EflOverworldInteract;
import mrwint.gbtasgen.segment.pokemon.EflEvolutionSegment;
import mrwint.gbtasgen.segment.pokemon.EflOverrideMoveSegment;
import mrwint.gbtasgen.segment.pokemon.EflWalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflEndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflInitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment.EflEnemyMoveDesc;
import mrwint.gbtasgen.segment.pokemon.fight.EflNewEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSwapWithSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflUseBikeSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.util.EflUtil.PressMetric;

public class PrepareEeveeTradeBlue extends SeqSegment {

	@Override
	public void execute() {
    seq(new EflWalkToSegment(15, 19)); // go to bush
    {
      seqEflButton(Move.START, PressMetric.PRESSED);
      seqEflScrollA(1); // mon
      seqEflSkipInput(1);
      seqEflScrollAF(-1); // dux
      seqEflSkipInput(1);
      seqEflButton(Move.A); // cut
      seqEflButton(Move.B); // hacked away (to text scroll)?
    }

    seq(new EflWalkToSegment(9, 13)); // enter fan club
    seq(new EflWalkToSegment(2, 1)); // go to leader
    seqMove(new EflOverworldInteract(5)); // talk to leader
    seq(new EflSkipTextsSegment(6));
    seq(new EflSkipTextsSegment(1, true)); // hear about mon
    seq(new EflSkipTextsSegment(25));
//    {
//      seqEflButton(Move.START, PressMetric.PRESSED);
//      seqEflScrollA(1); // items
//      seqEflScrollFastAF(2); // escape rope
//      seqEflSkipInput(1);
//      seqEflButton(Move.A); // use
//    }
//    seqEflSkipInput(2);
    seq(new EflWalkToSegment(2, 6));
    seq(new EflWalkToSegment(2, 8, false)); // leave house

    seq(new EflWalkToSegment(11, 3)); // enter center
//    seq(new EflWalkToSegment(19, 17)); // enter center
    
    { // TODO: inline
      seqEflButton(Move.START, PressMetric.PRESSED);
      seqEflScrollA(1); // items
      seqEflScrollAF(4+1); // moon stone
      seqEflSkipInput(1);
      seqEflButton(Move.A); // use
      seqEflScrollAF(-3); // clefairy
      seq(new EflEvolutionSegment());
      seqEflButton(Move.B); // close
      seqEflButton(Move.START); // close
    }
    
    seq(new EflWalkToSegment(11, 4)); // cable club
    seq(new EflWalkToSegment(11, 3)); // cable club
	}
}
