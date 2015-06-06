package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.pokemon.gen1.EflOverworldInteract;
import mrwint.gbtasgen.segment.pokemon.EflCatchMonSegment;
import mrwint.gbtasgen.segment.pokemon.EflWalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflEndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflInitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment.EflEnemyMoveDesc;
import mrwint.gbtasgen.segment.pokemon.fight.EflNewEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflEncounterSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;

public class Route3Blue extends SeqSegment {

	@Override
	public void execute() {
		seq(new EflWalkToSegment(11, 6)); // walk up to trainer

		seq(new EflInitFightSegment(1)); // start fight
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
			kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 81)}; // string shot
      kems.attackCount[2][0] = 2; // 2x bubble
      kems.attackCount[2][1] = 1; // 1x bubble crit
			kems.numExpGainers = 2; // level up to 12
			kems.onlyPrintInfo = false;
			seq(kems); // Caterpie
		}
		seq(EflNewEnemyMonSegment.any()); // next mon
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
			kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 81)}; // string shot
			kems.attackCount[2][1] = 2; // 2x bubble crit
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			seq(kems); // Weedle
		}
		seq(EflNewEnemyMonSegment.any()); // next mon
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 81)}; // string shot
      kems.attackCount[2][1] = 2; // 2x bubble crit
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			seq(kems); // Caterpie
		}
    save("r31");
    load("r31");
		seq(new EflEndFightSegment(1)); // player defeated enemy

		seq(new EflWalkToSegment(12, 4)); // walk up to shorts guy
		seq(new EflWalkToSegment(13, 4)); // walk up to shorts guy
		seq(new MoveSegment(new EflOverworldInteract(3))); // talk to shorts guy
		seq(new EflInitFightSegment(2)); // start fight
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 39)}; // tail whip
      kems.attackCount[0][0] = 2; // 2x tackle
      kems.attackCount[0][1] = 1; // 1x tackle crit
			kems.numExpGainers = 2; // level up to 13
			kems.onlyPrintInfo = false;
			seq(kems); // Rattata
		}
		seq(EflNewEnemyMonSegment.any()); // next mon
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
			kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 43)}; // leer
      kems.attackCount[0][0] = 2; // 2x tackle
      kems.attackCount[0][1] = 1; // 1x tackle crit
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			seq(kems); // Ekans
		}
		seq(new EflEndFightSegment(1)); // player defeated enemy

    save("r32");
    load("r32");

    seq(new EflWalkToSegment(18, 5)); // walk up to trainer
		seq(new MoveSegment(new EflOverworldInteract(5))); // talk to trainer

		seq(new EflInitFightSegment(1)); // start fight
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 81)}; // string shot
      kems.attackCount[0][0] = 1; // 1x tackle
      kems.attackCount[0][1] = 1; // 1x tackle crit
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			seq(kems); // Weedle
		}
		seq(EflNewEnemyMonSegment.any()); // next mon
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(Metric.TRUE, 106)}; // harden
      kems.attackCount[2][0] = 1; // bubble
      kems.attackCount[2][1] = 1; // bubble crit
			kems.numExpGainers = 2; // level up to 14
			kems.onlyPrintInfo = false;
			seq(kems); // Kakuna
		}
		seq(EflNewEnemyMonSegment.any()); // next mon
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 81)}; // string shot
      kems.attackCount[2][0] = 1; // bubble
      kems.attackCount[2][1] = 1; // bubble crit
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			seq(kems); // Caterpie
		}
		seq(EflNewEnemyMonSegment.any()); // next mon
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(Metric.TRUE, 106)}; // harden
      kems.attackCount[2][0] = 1; // bubble
      kems.attackCount[2][1] = 1; // bubble crit
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			seq(kems); // Metapod
		}
    seq(new EflEndFightSegment(2)); // player defeated enemy

		seq(new EflWalkToSegment(24, 6, false)); // walk up to trainer
		seq(new MoveSegment(new EflOverworldInteract(8))); // talk to trainer
		seq(new EflInitFightSegment(1)); // start fight
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 81)}; // string shot
      kems.attackCount[2][1] = 2; // bubble crit
      kems.numExpGainers = 3; // level up to 15, learn water gun
			kems.onlyPrintInfo = false;
			seq(kems); // Caterpie
		}
		seq(EflNewEnemyMonSegment.any()); // next mon
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(Metric.TRUE, 106)}; // harden
      kems.attackCount[3][1] = 1; // water gun crit
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			seq(kems); // Metapod
		}
		seq(new EflEndFightSegment(1)); // player defeated enemy
    save("r36");
    load("r36");
    seq(new EflWalkToSegment(27, 9).setMaxBufferSize(0)); // go in grass
    seq(new EflEncounterSegment(5, Move.DOWN)); // Spearow
    seq(new EflCatchMonSegment(2));
    seq(new EflWalkToSegment(29, 10)); // go in grass
    seq(new EflEncounterSegment(0x64, Move.RIGHT)); // Jigglypuff
    seq(new EflCatchMonSegment(2));
		seq(new EflWalkToSegment(59, -1, false)); // leave route 3
	}
}
