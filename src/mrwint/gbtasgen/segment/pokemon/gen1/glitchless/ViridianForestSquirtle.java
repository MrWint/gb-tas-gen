package mrwint.gbtasgen.segment.pokemon.gen1.glitchless;

import mrwint.gbtasgen.metric.pokemon.CheckEncounterMetric;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.pokemon.gen1.OverworldInteract;
import mrwint.gbtasgen.segment.pokemon.TextSegment;
import mrwint.gbtasgen.segment.pokemon.WalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.InitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment.EnemyMoveDesc;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;

public class ViridianForestSquirtle extends SeqSegment {

	@Override
	public void execute() {
		seq(new WalkToSegment(18, -1)); // leave viridian
    seq(new WalkToSegment(4, 51).setMaxBufferSize(0)); // walk up to encounter
    seq(new WalkToSegment(6, 51).setMaxBufferSize(0)); // walk up to encounter
    delay(new SeqSegment() {
      @Override
      protected void execute() {
        seqButton(Move.RIGHT);
        seqMetric(new CheckEncounterMetric(165 /* Rattata */, 2));
      }
    });
    seq(new SkipTextsSegment(1)); // wild rattata
    seq(new TextSegment()); // go
    {
      KillEnemyMonSegment kems = new KillEnemyMonSegment();
      kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 39)}; // tail whip
      kems.attackCount[0][0] = 2; // 2x tackle
      kems.numExpGainers = 1; // no level up
      kems.onlyPrintInfo = false;
      seq(kems); // Rattata
    }

		seq(new WalkToSegment(3, 43)); // enter viridian forest house
		seq(new WalkToSegment(5, 0)); // enter viridian forest

		seq(new WalkToSegment(2, 19)); // walk up to trainer
		seq(new MoveSegment(new OverworldInteract(4))); // talk to trainer

		seq(new InitFightSegment(1)); // start fight
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 81)}; // string shot
      kems.attackCount[0][0] = 3; // 3x tackle
      kems.attackCount[0][1] = 2; // 2x tackle crit
			kems.numExpGainers = 3; // level up to 8, learn bubble
			kems.onlyPrintInfo = false;
			seq(kems); // Weedle
		}
		seq(new EndFightSegment(2)); // player defeated enemy

		/*{ // collect hidden potion
			seq(new WalkToSegment(1, 19)); // walk up to hidden item
			seq(new MoveSegment(new WaitUntilNextInputFrame()));
			seq(new MoveSegment(new Wait(1)));
			seq(new MoveSegment(new PressButton(Move.UP)));
			seq(new MoveSegment(new CollectHiddenItem()));
			seq(new TextSegment());
		}*/

		seq(new WalkToSegment(1, -1, false)); // leave forest
		seq(new WalkToSegment(5, 0)); // leave forest house
		seq(new WalkToSegment(8, -1)); // enter pewter city
	}
}
