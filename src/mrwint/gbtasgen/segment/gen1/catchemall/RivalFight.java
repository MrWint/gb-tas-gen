package mrwint.gbtasgen.segment.gen1.catchemall;

import mrwint.gbtasgen.metric.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.segment.fight.EndFightSegment;
import mrwint.gbtasgen.segment.fight.InitFightSegment;
import mrwint.gbtasgen.segment.fight.KillEnemyMonSegment;
import mrwint.gbtasgen.segment.fight.KillEnemyMonSegment.EnemyMoveDesc;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;

public class RivalFight extends SeqSegment {

	public void execute() {
		seq(new InitFightSegment(0));
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 39)}; // tail whip
			kems.attackCount[0][0] = 2; // 2x scratch
			kems.attackCount[0][1] = 2; // 2x scratch crit
			kems.numExpGainers = 2; // level up to 6
			kems.onlyPrintInfo = false;
			seq(kems); // Squirtle
		}
		
		seq(new EndFightSegment(3)); // player defeated enemy
		seq(new SkipTextsSegment(4)); // rival after battle texts
	}
}
