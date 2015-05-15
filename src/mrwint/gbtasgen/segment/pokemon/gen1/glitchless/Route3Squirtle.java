package mrwint.gbtasgen.segment.pokemon.gen1.glitchless;

import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.pokemon.gen1.OverworldInteract;
import mrwint.gbtasgen.segment.pokemon.WalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.InitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment.EnemyMoveDesc;
import mrwint.gbtasgen.segment.pokemon.fight.NewEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EncounterAndCatchSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;

public class Route3Squirtle extends SeqSegment {

	@Override
	public void execute() {
		seq(new WalkToSegment(11, 6)); // walk up to trainer

		seq(new InitFightSegment(1)); // start fight
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 81)}; // string shot
      kems.attackCount[2][0] = 2; // 2x bubble
      kems.attackCount[2][1] = 1; // 1x bubble crit
			kems.numExpGainers = 2; // level up to 12
			kems.onlyPrintInfo = false;
			seq(kems); // Caterpie
		}
		seq(NewEnemyMonSegment.any()); // next mon
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 81)}; // string shot
			kems.attackCount[2][1] = 2; // 2x bubble crit
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			seq(kems); // Weedle
		}
		seq(NewEnemyMonSegment.any()); // next mon
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
      kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 81)}; // string shot
      kems.attackCount[2][1] = 2; // 2x bubble crit
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			seq(kems); // Caterpie
		}
    save("r31");
//    load("r31");
		seq(new EndFightSegment(1)); // player defeated enemy

		seq(new WalkToSegment(12, 4)); // walk up to shorts guy
		seq(new WalkToSegment(13, 4)); // walk up to shorts guy
		seq(new MoveSegment(new OverworldInteract(3))); // talk to shorts guy
		seq(new InitFightSegment(2)); // start fight
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
      kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 39)}; // tail whip
      kems.attackCount[0][0] = 2; // 2x tackle
      kems.attackCount[0][1] = 1; // 1x tackle crit
			kems.numExpGainers = 2; // level up to 13
			kems.onlyPrintInfo = false;
			seq(kems); // Rattata
		}
		seq(NewEnemyMonSegment.any()); // next mon
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 43)}; // leer
      kems.attackCount[0][0] = 2; // 2x tackle
      kems.attackCount[0][1] = 1; // 1x tackle crit
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			seq(kems); // Ekans
		}
		seq(new EndFightSegment(1)); // player defeated enemy

    save("r32");
//    load("r32");

    seq(new WalkToSegment(18, 5)); // walk up to trainer
		seq(new MoveSegment(new OverworldInteract(5))); // talk to trainer

		seq(new InitFightSegment(1)); // start fight
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
      kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 81)}; // string shot
      kems.attackCount[0][0] = 1; // 1x tackle
      kems.attackCount[0][1] = 1; // 1x tackle crit
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			seq(kems); // Weedle
		}
		seq(NewEnemyMonSegment.any()); // next mon
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
      kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(Metric.TRUE, 106)}; // harden
      kems.attackCount[2][0] = 1; // bubble
      kems.attackCount[2][1] = 1; // bubble crit
			kems.numExpGainers = 2; // level up to 14
			kems.onlyPrintInfo = false;
			seq(kems); // Kakuna
		}
		seq(NewEnemyMonSegment.any()); // next mon
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
      kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 81)}; // string shot
      kems.attackCount[2][0] = 1; // bubble
      kems.attackCount[2][1] = 1; // bubble crit
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			seq(kems); // Caterpie
		}
		seq(NewEnemyMonSegment.any()); // next mon
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
      kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(Metric.TRUE, 106)}; // harden
      kems.attackCount[2][0] = 1; // bubble
      kems.attackCount[2][1] = 1; // bubble crit
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			seq(kems); // Metapod
		}
    seq(new EndFightSegment(2)); // player defeated enemy

		seq(new WalkToSegment(24, 6, false)); // walk up to trainer
		seq(new MoveSegment(new OverworldInteract(8))); // talk to trainer
		seq(new InitFightSegment(1)); // start fight
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
      kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 81)}; // string shot
      kems.attackCount[2][1] = 2; // bubble crit
      kems.numExpGainers = 3; // level up to 15, learn water gun
			kems.onlyPrintInfo = false;
			seq(kems); // Caterpie
		}
		seq(NewEnemyMonSegment.any()); // next mon
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
      kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(Metric.TRUE, 106)}; // harden
      kems.attackCount[3][1] = 1; // water gun crit
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			seq(kems); // Metapod
		}
		seq(new EndFightSegment(1)); // player defeated enemy
    save("r36");
//    load("r36");
    seq(new WalkToSegment(35, 10)); // go in grass
    seq(new WalkToSegment(36, 10)); // go in grass
    EncounterAndCatchSegment.defaultBallIndex = 2; // TODO: fix to 1
    seq(new EncounterAndCatchSegment(5, Move.RIGHT)); // Spearow
		seq(new WalkToSegment(59, -1, false)); // leave route 3
	}
}
