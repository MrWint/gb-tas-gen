package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.segment.pokemon.fight.EflEndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflInitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment.EflEnemyMoveDesc;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;

public class Rival1FightBlue extends SeqSegment {

	@Override
  public void execute() {

	  seq(new EflInitFightSegment(0));
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 45)}; // growl
      kems.attackCount[0][0] = 4; // 4x tackle
      kems.numExpGainers = 2; // level up to 6
      seq(kems); // Bulbasaur
    }

		seq(new EflEndFightSegment(3)); // player defeated enemy
		seq(new EflSkipTextsSegment(4)); // rival after battle texts
	}
}
