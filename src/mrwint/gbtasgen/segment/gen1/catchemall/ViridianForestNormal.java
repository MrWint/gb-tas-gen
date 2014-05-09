package mrwint.gbtasgen.segment.gen1.catchemall;

import mrwint.gbtasgen.metric.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.move.gen1.OverworldInteract;
import mrwint.gbtasgen.segment.WalkToSegment;
import mrwint.gbtasgen.segment.fight.EndFightSegment;
import mrwint.gbtasgen.segment.fight.InitFightSegment;
import mrwint.gbtasgen.segment.fight.KillEnemyMonSegment;
import mrwint.gbtasgen.segment.fight.KillEnemyMonSegment.EnemyMoveDesc;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;

public class ViridianForestNormal extends SeqSegment {
	
	@Override
	public void execute() {
		seq(new WalkToSegment(18, -1)); // leave viridian
		seq(new WalkToSegment(3, 43)); // enter viridian forest house
		seq(new WalkToSegment(5, 0)); // enter viridian forest
		seq(new WalkToSegment(2, 19)); // walk up to trainer
		seq(new MoveSegment(new OverworldInteract(4))); // talk to trainer

		seq(new InitFightSegment(1)); // start fight
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 81)}; // string shot
			kems.attackCount[0][1] = 4; // 4x scratch crit
			kems.numExpGainers = 2; // level up to 7
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
