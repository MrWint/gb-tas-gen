package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.pokemon.gen1.EflOverworldInteract;
import mrwint.gbtasgen.segment.pokemon.EflCatchMonSegment;
import mrwint.gbtasgen.segment.pokemon.EflEvolutionSegment;
import mrwint.gbtasgen.segment.pokemon.EflWalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflEndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflInitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment.EflEnemyMoveDesc;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflEncounterSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflNewEnemyMonSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;

public class Route3Red extends SeqSegment {

	@Override
	public void execute() {
		seq(new EflWalkToSegment(11, 6)); // walk up to trainer

		seq(new EflInitFightSegment(1)); // start fight
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
			kems.attackCount[2][0] = 1; // ember
      kems.numExpGainers = 2; // boosted
			seq(kems); // Caterpie
		}
		seq(EflNewEnemyMonSegment.any()); // next mon
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
			kems.attackCount[2][0] = 1; // ember
      kems.numExpGainers = 2; // boosted
			seq(kems); // Weedle
		}
		seq(EflNewEnemyMonSegment.any()); // next mon
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[2][0] = 1; // ember
			kems.numExpGainers = 2; // boosted
			seq(kems); // Caterpie
		}
		seq(new EflEndFightSegment(1)); // player defeated enemy

    save("r31");
    load("r31");

		seq(new EflWalkToSegment(12, 4)); // walk up to shorts guy
		seq(new EflWalkToSegment(13, 4)); // walk up to shorts guy
		seq(new MoveSegment(new EflOverworldInteract(3))); // talk to shorts guy

		seq(new EflInitFightSegment(2)); // start fight
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
			kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 39)}; // tail whip
      kems.attackCount[2][0] = 1; // ember
      kems.numExpGainers = 2; // boosted
			seq(kems); // Rattata
		}
		seq(EflNewEnemyMonSegment.any()); // next mon
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
			kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 43)}; // leer
      kems.attackCount[2][0] = 1; // ember
      kems.numExpGainers = 3; // boosted, lvlup to 32
			seq(kems); // Ekans
		}
		seq(new EflEndFightSegment(1)); // player defeated enemy
		
		seq(new EflEvolutionSegment());

    save("r32");
    load("r32");

    seq(new EflWalkToSegment(19, 4)); // walk up to trainer

    seq(new EflInitFightSegment(2)); // start fight
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[2][0] = 1; // ember
      kems.numExpGainers = 2; // boosted
      seq(kems); // Rattata
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[2][0] = 1; // ember
      kems.numExpGainers = 2; // boosted
      seq(kems); // NidoranM
    }
    seq(new EflEndFightSegment(1)); // player defeated enemy
    seq(new EflWalkToSegment(-1, 9)); // leave
    seq(new EflWalkToSegment(40, 17)); // re-enter

//    seq(new EflWalkToSegment(18, 5)); // walk up to trainer
//    seq(new MoveSegment(new EflOverworldInteract(5))); // talk to trainer
//
//    seq(new EflInitFightSegment(1)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[0][0] = 1; // cut
//      kems.numExpGainers = 2; // boosted
//      seq(kems); // Weedle
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[0][0] = 1; // cut
//      kems.numExpGainers = 2; // boosted
//      seq(kems); // Kakuna
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[0][0] = 1; // cut
//      kems.numExpGainers = 2; // boosted
//      seq(kems); // Caterpie
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[0][0] = 1; // cut
//      kems.numExpGainers = 2; // boosted
//      seq(kems); // Metapod
//    }
//    seq(new EflEndFightSegment(2)); // player defeated enemy

		seq(new EflWalkToSegment(24, 6, false)); // walk up to trainer
		seq(new MoveSegment(new EflOverworldInteract(8))); // talk to trainer
		seq(new EflInitFightSegment(1)); // start fight
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[2][0] = 1; // ember
      kems.numExpGainers = 2; // boosted
			seq(kems); // Caterpie
		}
		seq(EflNewEnemyMonSegment.any()); // next mon
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[2][0] = 1; // ember
      kems.numExpGainers = 2; // boosted
			seq(kems); // Metapod
		}
		seq(new EflEndFightSegment(1)); // player defeated enemy

		save("r33");
		load("r33");

		seqUnbounded(new EflWalkToSegment(27, 9)); // go in grass
    seq(new EflEncounterSegment(0x24, Move.DOWN)); // Pidgey
    seq(new EflCatchMonSegment(0).withBufferSize(0));
    seqUnbounded(new EflWalkToSegment(36, 11)); // go in grass
    seq(new EflEncounterSegment(0x64, Move.RIGHT)); // Jigglypuff
    seq(new EflCatchMonSegment(0));
		seq(new EflWalkToSegment(59, -1, false)); // leave route 3
	}
}
