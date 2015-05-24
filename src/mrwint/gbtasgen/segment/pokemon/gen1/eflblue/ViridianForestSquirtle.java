package mrwint.gbtasgen.segment.pokemon.gen1.eflblue;

import mrwint.gbtasgen.metric.pokemon.CheckEncounterMetric;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.pokemon.gen1.EflOverworldInteract;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.pokemon.EflWalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflEndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflInitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment.EflEnemyMoveDesc;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;

public class ViridianForestSquirtle extends SeqSegment {

	@Override
	public void execute() {
//		seq(new EflWalkToSegment(18, -1)); // leave viridian
//    seq(new EflWalkToSegment(4, 51).setMaxBufferSize(0)); // walk up to encounter
//    seq(new EflWalkToSegment(7, 51).setMaxBufferSize(0)); // walk up to encounter
//    delayEfl(new SeqSegment() {
//      @Override
//      protected void execute() {
//        seqEflButtonUnboundedNoDelay(Move.RIGHT);
//        seqMetric(new CheckEncounterMetric(165 /* Rattata */, 2));
//      }
//    });
//    seq(new EflSkipTextsSegment(1)); // wild rattata
//    seq(new EflTextSegment()); // go
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 39)}; // tail whip
//      kems.attackCount[0][0] = 2; // 2x tackle
//      kems.numExpGainers = 1; // no level up
//      kems.onlyPrintInfo = false;
//      seq(kems); // Rattata
//    }
//    save("vf1");
    load("vf1");

		seq(new EflWalkToSegment(3, 43)); // enter viridian forest house
		seq(new EflWalkToSegment(5, 0)); // enter viridian forest

		seq(new EflWalkToSegment(2, 19)); // walk up to trainer
		seq(new MoveSegment(new EflOverworldInteract(4))); // talk to trainer

//		{
//	    seq(new EflSkipTextsSegment(1)); // trainer pre-text
//	    seq(new EflSkipTextsSegment(1)); // trainer wants to fight
//	    seq(new EflTextSegment()); // trainer sent out mon
//	    seq(new EflTextSegment()); // Go! mon!
//		}
		seq(new EflInitFightSegment(1)); // start fight
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
			kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 81)}; // string shot
      kems.attackCount[0][0] = 3; // 3x tackle
      kems.attackCount[0][1] = 2; // 2x tackle crit
			kems.numExpGainers = 3; // level up to 8, learn bubble
			kems.onlyPrintInfo = false;
			seq(kems); // Weedle
		}
		seq(new EflEndFightSegment(2)); // player defeated enemy

		/*{ // collect hidden potion
			seq(new WalkToSegment(1, 19)); // walk up to hidden item
			seq(new MoveSegment(new WaitUntilNextInputFrame()));
			seq(new MoveSegment(new Wait(1)));
			seq(new MoveSegment(new PressButton(Move.UP)));
			seq(new MoveSegment(new CollectHiddenItem()));
			seq(new TextSegment());
		}*/

		seq(new EflWalkToSegment(1, -1, false)); // leave forest
		seq(new EflWalkToSegment(5, 0)); // leave forest house
		seq(new EflWalkToSegment(8, -1)); // enter pewter city
	}
}
