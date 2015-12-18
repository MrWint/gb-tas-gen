package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.STRING_SHOT;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.move.pokemon.gen1.EflOverworldInteract;
import mrwint.gbtasgen.segment.pokemon.EflWalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflEndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflInitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment.EflEnemyMoveDesc;
import mrwint.gbtasgen.segment.pokemon.fight.EflNewEnemyMonSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;

public class ViridianForestDummyRed extends SeqSegment {

	@Override
	public void execute() {
		seq(new EflWalkToSegment(18, -1)); // leave viridian
		seq(new EflWalkToSegment(3, 43)); // enter viridian forest house
		seq(new EflWalkToSegment(5, 0)); // enter viridian forest

    seq(new EflWalkToSegment(30, 33, false)); // walk up to trainer
    seq(new MoveSegment(new EflOverworldInteract(2))); // talk to trainer

    seq(new EflInitFightSegment(2)); // start fight
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), STRING_SHOT)};
      kems.attackCount[0][0] = 1; // scratch
      kems.attackCount[0][1] = 2; // scratch crit
      kems.numExpGainers = 2; // Charmander, level up to 7
      seq(kems); // Weedle
    }
    save("tmp");
    load("tmp");
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), STRING_SHOT)};
      kems.attackCount[0][0] = 1; // scratch
      kems.attackCount[0][1] = 2; // scratch crit
      kems.numExpGainers = 2; // Charmander, level up to 8
      seq(kems); // Caterpie
    }
    seq(new EflEndFightSegment(2)); // player defeated enemy
    save("vf1");
    load("vf1");

		seq(new EflWalkToSegment(2, 19)); // walk up to trainer
		seq(new MoveSegment(new EflOverworldInteract(4))); // talk to trainer

		seq(new EflInitFightSegment(1)); // start fight
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
			kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), STRING_SHOT)};
      kems.attackCount[0][0] = 1; // scratch
      kems.attackCount[0][1] = 2; // scratch crit
			kems.numExpGainers = 3; // Charmander, level up to 9, learn ember
			seq(kems); // Weedle
		}
		seq(new EflEndFightSegment(2)); // player defeated enemy

		seq(new EflWalkToSegment(1, -1, false)); // leave forest
		seq(new EflWalkToSegment(5, 0)); // leave forest house
		seq(new EflWalkToSegment(8, -1)); // enter pewter city
	}
}
