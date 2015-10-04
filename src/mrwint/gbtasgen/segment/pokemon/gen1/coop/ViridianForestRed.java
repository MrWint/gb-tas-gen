package mrwint.gbtasgen.segment.pokemon.gen1.coop;

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
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflEncounterSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.util.EflUtil.PressMetric;

public class ViridianForestRed extends SeqSegment {

	@Override
	public void execute() {
    seqEflButton(Move.A); // continue game
    seqEflButton(Move.START);
    seqEflButton(Move.A);
    seqEflButton(Move.START);
    seqEflButton(Move.A);

    seq(new EflWalkToSegment(4, 6)); // leave center
    seq(new EflWalkToSegment(4, 8, false)); // leave center

    seq(new EflWalkToSegment(29, 19)); // enter mart

    seq(new EflWalkToSegment(3, 5)); // counter
    seq(new EflWalkToSegment(2, 5)); // counter
    seqMove(new EflOverworldInteract(1)); // shopkeep
    {
      seq(new EflSkipTextsSegment(1, true)); // buy
      seq(new EflTextSegment(Move.B));
      {
        seqEflButton(Move.A); // poke ball
        seqEflScrollA(-14); // buy x15
        seq(new EflSkipTextsSegment(1)); // confirmation text
        seq(new EflSkipTextsSegment(1, true)); // "yes"
        seq(new EflSkipTextsSegment(1)); // thank you text
      }
      seqEflButton(Move.B, PressMetric.PRESSED); // cancel
      seq(new EflSkipTextsSegment(2)); // cancel + bye
    }
    seq(new EflWalkToSegment(3,8,false)); // leave mart


    seqMetric(new OutputParty());

		seq(new EflWalkToSegment(18, -1)); // leave viridian
		seq(new EflWalkToSegment(3, 43)); // enter viridian forest house
		seq(new EflWalkToSegment(5, 0)); // enter viridian forest

    save("vf1");
    load("vf1");

    seqUnbounded(new EflWalkToSegment(18, 12)); // go in grass
    seq(new EflEncounterSegment(new CheckEncounterMetric(0x70, 5), Move.DOWN)); // Weedle
    seq(new EflCatchMonSegment(0).withBufferSize(0));
    seqUnbounded(new EflWalkToSegment(8, 5)); // go in grass
    seq(new EflEncounterSegment(0x7b, Move.DOWN)); // Caterpie
    seq(new EflCatchMonSegment(0));

    save("vf2");
    load("vf2");

		seq(new EflWalkToSegment(2, 19)); // walk up to trainer
		seq(new MoveSegment(new EflOverworldInteract(4))); // talk to trainer

		seq(new EflInitFightSegment(1)); // start fight
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
			kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 81)}; // string shot
      kems.attackCount[0][0] = 1; // 1x cut
      kems.numExpGainers = 2; // boosted
			seq(kems); // Weedle
		}
		seq(new EflEndFightSegment(2)); // player defeated enemy

		seqUnbounded(new EflWalkToSegment(1, -1, false)); // leave forest
		seqUnbounded(new EflWalkToSegment(5, 0)); // leave forest house

		seq(new EflWalkToSegment(8, -1)); // enter pewter city
	}
}
