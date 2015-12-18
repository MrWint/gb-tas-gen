package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TAIL_WHIP;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.segment.pokemon.fight.EflEndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflInitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment.EflEnemyMoveDesc;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;

public class Rival1FightDummyRed extends SeqSegment {

	@Override
  public void execute() {
		seq(new EflInitFightSegment(0));
		{
			EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
			kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), TAIL_WHIP)};
			kems.attackCount[0][0] = 2; // scratch
			kems.attackCount[0][1] = 2; // scratch crit
			kems.numExpGainers = 2; // Charmander, level up to 6
			seq(kems); // Squirtle
		}

		seq(new EflEndFightSegment(3)); // player defeated enemy
		seq(new EflSkipTextsSegment(4)); // rival after battle texts
	}
}
