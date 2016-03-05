package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import static mrwint.gbtasgen.move.Move.B;
import static mrwint.gbtasgen.move.Move.DOWN;
import static mrwint.gbtasgen.move.Move.LEFT;
import static mrwint.gbtasgen.move.Move.RIGHT;
import static mrwint.gbtasgen.move.Move.UP;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.CATERPIE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.PIKACHU;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.WEEDLE;
import mrwint.gbtasgen.metric.pokemon.CheckEncounterMetric;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputParty;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.pokemon.gen1.EflOverworldInteract;
import mrwint.gbtasgen.segment.pokemon.EflCatchMonSegment;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.pokemon.EflWalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflEndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflInitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment.EflEnemyMoveDesc;
import mrwint.gbtasgen.segment.pokemon.gen1.common.Constants;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflBuyItemSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflEncounterSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.util.EflUtil.PressMetric;

public class ViridianForestRed extends SeqSegment {

	@Override
	public void execute() {
//    seqEflButton(Move.A); // continue game
//    seqEflButton(Move.START);
//    seqEflButton(Move.A);
//    seqEflButton(Move.START);
//    seqEflButton(Move.A);
//
//    seq(new EflWalkToSegment(4, 6)); // leave center
//    seq(new EflWalkToSegment(4, 8, false)); // leave center
//
//    seqMetric(new OutputParty());
//
//    save("vf1");
//    load("vf1");
//
//		seq(new EflWalkToSegment(18, -1)); // leave viridian
//		seqUnbounded(new EflWalkToSegment(3, 43)); // enter viridian forest house
//		seqUnbounded(new EflWalkToSegment(5, 0)); // enter viridian forest
//
//    save("tmp");
//    load("tmp");
//
//    seqUnbounded(new EflWalkToSegment(20, 41)); // go in grass
//    seqUnbounded(new EflWalkToSegment(21, 41)); // go in grass
//    seq(new EflEncounterSegment(new CheckEncounterMetric(WEEDLE, 5), RIGHT));
//    save("tmp2");
//    load("tmp2");
//    seq(new EflCatchMonSegment().withBufferSize(0));
//    seqUnbounded(new EflWalkToSegment(25, 40)); // go in grass
////    seqUnbounded(new EflWalkToSegment(25, 36)); // go in grass
//    seq(new EflEncounterSegment(PIKACHU, UP));
//    save("tmp3");
    load("tmp3");
    seq(new EflCatchMonSegment().withBufferSize(0));
    seqUnbounded(new EflWalkToSegment(25, 28)); // go in grass
//    seqUnbounded(new EflWalkToSegment(11, 11)); // go in grass
    seq(new EflEncounterSegment(CATERPIE, UP));
    seq(new EflCatchMonSegment());

    save("vf2");
    load("vf2");
    
    seqMetric(new OutputParty());

		seq(new EflWalkToSegment(2, 19)); // walk up to trainer
		seq(new MoveSegment(new EflOverworldInteract(4))); // talk to trainer

		seq(new EflInitFightSegment(1)); // start fight
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
			kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 81)}; // string shot
      kems.attackCount[2][0] = 1; // 1x ember
      kems.numExpGainers = 2; // boosted
			seq(kems); // Weedle
		}
		seq(new EflEndFightSegment(2)); // player defeated enemy
    
    seqMetric(new OutputParty());

		seqUnbounded(new EflWalkToSegment(1, -1, false)); // leave forest
		seqUnbounded(new EflWalkToSegment(5, 0)); // leave forest house

		seq(new EflWalkToSegment(8, -1)); // enter pewter city
	}
}
