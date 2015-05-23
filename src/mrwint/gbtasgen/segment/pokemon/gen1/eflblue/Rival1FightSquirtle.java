package mrwint.gbtasgen.segment.pokemon.gen1.eflblue;

import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.segment.pokemon.fight.EflInitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.InitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment.EnemyMoveDesc;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;

public class Rival1FightSquirtle extends SeqSegment {

	@Override
  public void execute() {
		seq(new EflInitFightSegment(0));
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 45)}; // growl
//			kems.attackCount[0][0] = 4; // 4x tackle
//			kems.numExpGainers = 2; // level up to 6
//			kems.onlyPrintInfo = false;
//			seq(kems); // Bulbasaur
//		}
//
//		seq(new EndFightSegment(3)); // player defeated enemy
//		seq(new SkipTextsSegment(4)); // rival after battle texts
	}
}
