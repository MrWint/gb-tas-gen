package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import static mrwint.gbtasgen.move.Move.DOWN;
import static mrwint.gbtasgen.move.Move.RIGHT;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.JIGGLYPUFF;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.LEER;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SPEAROW;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.STRING_SHOT;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TAIL_WHIP;
import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.move.pokemon.gen1.EflOverworldInteract;
import mrwint.gbtasgen.segment.pokemon.EflCatchMonSegment;
import mrwint.gbtasgen.segment.pokemon.EflWalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflEndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflInitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment.EflEnemyMoveDesc;
import mrwint.gbtasgen.segment.pokemon.fight.EflNewEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflEncounterSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;

public class Route3Blue extends SeqSegment {

	@Override
	public void execute() {
		seq(new EflWalkToSegment(11, 6)); // walk up to trainer

		seq(new EflInitFightSegment(1)); // start fight
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
			kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), STRING_SHOT)};
      kems.attackCount[2][0] = 2; // bubble
      kems.attackCount[2][1] = 1; // bubble crit
			kems.numExpGainers = 2; // Squirtle, level up to 12
			seq(kems); // Caterpie
		}
		seq(EflNewEnemyMonSegment.any()); // next mon
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
			kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), STRING_SHOT)};
			kems.attackCount[2][1] = 2; // bubble crit
			seq(kems); // Weedle
		}
		seq(EflNewEnemyMonSegment.any()); // next mon
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), STRING_SHOT)};
      kems.attackCount[2][1] = 2; // bubble crit
			seq(kems); // Caterpie
		}
    save("r31");
    load("r31");
		seq(new EflEndFightSegment(1)); // player defeated enemy

		seq(new EflWalkToSegment(12, 4)); // walk up to shorts guy
		seq(new EflWalkToSegment(13, 4)); // walk up to shorts guy
		seqMove(new EflOverworldInteract(3)); // talk to shorts guy
		seq(new EflInitFightSegment(2)); // start fight
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), TAIL_WHIP)};
      kems.attackCount[0][0] = 2; // tackle
      kems.attackCount[0][1] = 1; // tackle crit
			kems.numExpGainers = 2; // Squirtle, level up to 13
			seq(kems); // Rattata
		}
		seq(EflNewEnemyMonSegment.any()); // next mon
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
			kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), LEER)};
      kems.attackCount[0][0] = 2; // tackle
      kems.attackCount[0][1] = 1; // tackle crit
			seq(kems); // Ekans
		}
		seq(new EflEndFightSegment(1)); // player defeated enemy

    save("r32");
    load("r32");

    seq(new EflWalkToSegment(18, 5)); // walk up to trainer
		seqMove(new EflOverworldInteract(5)); // talk to trainer

		seq(new EflInitFightSegment(1)); // start fight
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), STRING_SHOT)};
      kems.attackCount[0][0] = 1; // tackle
      kems.attackCount[0][1] = 1; // tackle crit
			seq(kems); // Weedle
		}
		seq(EflNewEnemyMonSegment.any()); // next mon
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(Metric.TRUE, 106)}; // harden
      kems.attackCount[2][0] = 1; // bubble
      kems.attackCount[2][1] = 1; // bubble crit
			kems.numExpGainers = 2; // Squirtle, level up to 14
			seq(kems); // Kakuna
		}
		seq(EflNewEnemyMonSegment.any()); // next mon
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 81)}; // string shot
      kems.attackCount[2][0] = 1; // bubble
      kems.attackCount[2][1] = 1; // bubble crit
			seq(kems); // Caterpie
		}
		seq(EflNewEnemyMonSegment.any()); // next mon
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(Metric.TRUE, 106)}; // harden
      kems.attackCount[2][0] = 1; // bubble
      kems.attackCount[2][1] = 1; // bubble crit
			seq(kems); // Metapod
		}
    seq(new EflEndFightSegment(2)); // player defeated enemy

    save("r33");
    load("r33");

		seq(new EflWalkToSegment(24, 6, false)); // walk up to trainer
		seqMove(new EflOverworldInteract(8)); // talk to trainer
		seq(new EflInitFightSegment(1)); // start fight
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 81)}; // string shot
      kems.attackCount[2][1] = 2; // bubble crit
      kems.numExpGainers = 3; // Squirtle, level up to 15, learn water gun
			seq(kems); // Caterpie
		}
		seq(EflNewEnemyMonSegment.any()); // next mon
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(Metric.TRUE, 106)}; // harden
      kems.attackCount[3][1] = 1; // water gun crit
			kems.numExpGainers = 1; // no level up
			seq(kems); // Metapod
		}
    save("r34");
    load("r34");
		seqUnbounded(new EflEndFightSegment(1)); // player defeated enemy
    save("tmp");
    load("tmp");
    seqUnbounded(new EflWalkToSegment(28, 9)); // go in grass
    seqUnbounded(new EflWalkToSegment(28, 10)); // go in grass
    seq(new EflEncounterSegment(JIGGLYPUFF, DOWN));
    save("tmp2");
    load("tmp2");
    seq(new EflCatchMonSegment().withBufferSize(0));
    seqUnbounded(new EflWalkToSegment(33, 11)); // go in grass
    seq(new EflEncounterSegment(SPEAROW, RIGHT));
    seq(new EflCatchMonSegment());
		seq(new EflWalkToSegment(59, -1, false)); // leave route 3
	}
}
