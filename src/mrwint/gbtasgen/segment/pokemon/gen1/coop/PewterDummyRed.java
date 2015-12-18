package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SCREECH;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TACKLE;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.move.pokemon.gen1.EflOverworldInteract;
import mrwint.gbtasgen.segment.pokemon.EflWalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflEndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflInitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment.EflEnemyMoveDesc;
import mrwint.gbtasgen.segment.pokemon.fight.EflNewEnemyMonSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;

public class PewterDummyRed extends SeqSegment {

	@Override
	public void execute() {
    seq(new EflWalkToSegment(16, 17)); // enter gym
    seq(new EflWalkToSegment(4, 3));
    seq(new EflWalkToSegment(4, 2));
    seq(new MoveSegment(new EflOverworldInteract(1)));

    seq(new EflInitFightSegment(9)); // start fight
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(TACKLE)};
      kems.attackCount[2][1] = 5; // ember crit
      kems.numExpGainers = 2; // Charmander, level up to 10
      seq(kems); // Geodude
    }
    save("tmp");
    load("tmp");
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), SCREECH)};
      kems.attackCount[2][0] = 1; // ember
      kems.attackCount[2][1] = 4; // ember crit
      kems.numExpGainers = 2; // Charmander, level up to 12
      seq(kems); // Onix
    }
    save("pd1");
    load("pd1");
    seq(new EflEndFightSegment(10)); // player defeated enemy
    seq(new EflSkipTextsSegment(14)); // after battle talk
    seq(new EflWalkToSegment(4, 14, false)); // exit gym

    seq(new EflWalkToSegment(40, 17)); // leave pewter
	}
}
