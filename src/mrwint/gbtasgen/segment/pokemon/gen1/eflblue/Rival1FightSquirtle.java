package mrwint.gbtasgen.segment.pokemon.gen1.eflblue;

import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.move.EflPressButton;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflEndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflInitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment.EflEnemyMoveDesc;
import mrwint.gbtasgen.segment.pokemon.fight.EndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.InitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment.EnemyMoveDesc;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.util.EflUtil.PressMetric;

public class Rival1FightSquirtle extends SeqSegment {

	@Override
  public void execute() {

	  seq(new EflInitFightSegment(0));
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 45)}; // growl
      kems.attackCount[0][0] = 4; // 4x tackle
      kems.numExpGainers = 2; // level up to 6
      kems.onlyPrintInfo = false;
      seq(kems); // Bulbasaur
    }
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 45)}; // growl
//      kems.attackCount[0][0] = 4; // 4x tackle
//      kems.numExpGainers = 2; // level up to 6
//      kems.onlyPrintInfo = false;
//      seq(kems); // Bulbasaur
//    }

		seq(new EflEndFightSegment(3)); // player defeated enemy
		seq(new EflSkipTextsSegment(4)); // rival after battle texts
	}
}
