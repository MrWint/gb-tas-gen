package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import mrwint.gbtasgen.metric.pokemon.gen1.CheckDisableEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.pokemon.gen1.EflOverworldInteract;
import mrwint.gbtasgen.segment.pokemon.EflCatchMonSegment;
import mrwint.gbtasgen.segment.pokemon.EflEvolutionSegment;
import mrwint.gbtasgen.segment.pokemon.EflLearnTMSegment;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.pokemon.EflWalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflEndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflInitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment.EflEnemyMoveDesc;
import mrwint.gbtasgen.segment.pokemon.fight.EflNewEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflEncounterSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.state.StateBuffer;

public class MtMoonRed extends SeqSegment {

	@Override
	public void execute() {
    seq(new EflWalkToSegment(11,5)); // enter Center
    seq(new EflWalkToSegment(8, 6)); // Man
    seq(new EflWalkToSegment(9, 6, false)); // Man
    seq(new MoveSegment(new EflOverworldInteract(4))); // Man
    seq(new EflSkipTextsSegment(4));
    seq(new EflSkipTextsSegment(1, true)); // take Magikarp
    seq(new EflTextSegment()); // got
    seq(new EflSkipTextsSegment(2)); // no nickname
    seq(new EflWalkToSegment(4, 6)); // leave
    seq(new EflWalkToSegment(4, 8, false)); // leave

    seq(new EflWalkToSegment(18,5)); // enter Mt. Moon

    seq(new EflWalkToSegment(33, 31).setBlockAllWarps(true)); // Rare Candy
    seq(new EflWalkToSegment(34, 31)); // Rare Candy
    seq(new MoveSegment(new EflOverworldInteract(10))); // collect Rare Candy
    seq(new EflTextSegment()); // found Rare Candy

//    seq(new EflWalkToSegment(34, 23)); // Escape Rope
//    seq(new EflWalkToSegment(35, 23)); // Escape Rope
//    seq(new MoveSegment(new EflOverworldInteract(11))); // collect Escape Rope
//    seq(new EflTextSegment()); // found Escape Rope


    seq(new EflWalkToSegment(4, 2).setBlockAllWarps(true)); // Moon Stone
    seq(new EflWalkToSegment(3, 2)); // Moon Stone
    seqMove(new EflOverworldInteract(9)); // Moon Stone
    seq(new EflTextSegment()); // Moon Stone

    seq(new EflWalkToSegment(5, 5).setBlockAllWarps(true)); // go to MtMoon2
		seq(new EflWalkToSegment(21,17)); // go to MtMoon3

    save("mm1");
    load("mm1");

    seqUnbounded(new EflWalkToSegment(35, 14)); // go to encounter
    seq(new EflEncounterSegment(4, Move.RIGHT)); // Clefairy
    seq(new EflCatchMonSegment(0));

		seq(new EflWalkToSegment(11, 16, false)); // go to rocket
		seqMove(new EflOverworldInteract(2)); // talk to rocket

		seq(new EflInitFightSegment(3)); // start fight
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 39)}; // tail whip
			kems.attackCount[2][0] = 1; // ember
      kems.numExpGainers = 2; // boosted
			seq(kems); // Rattata
		}
		seq(EflNewEnemyMonSegment.any());
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[2][0] = 1; // ember
			kems.numExpGainers = 2; // boosted
			seq(kems); // Zubat
		}
		seq(new EflEndFightSegment(1)); // player defeated enemy

    save("mm2");
    load("mm2");

    seq(new EflWalkToSegment(17, 12)); // Moon Stone
    seqEflButton(Move.A); // collect Moon Stone
    seq(new EflTextSegment()); // found Moon Stone

		seq(new EflWalkToSegment(13,8));
		seq(new EflInitFightSegment(3)); // start fight
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(1)}; // pound
			kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckDisableEffectMisses(), 50)}; // disable
      kems.attackCount[2][0] = 1; // ember
      kems.numExpGainers = 2; // boosted
			seq(kems); // Grimer
		}
		seq(EflNewEnemyMonSegment.any());
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 103)}; // screech
      kems.attackCount[2][0] = 1; // ember
      kems.numExpGainers = 2; // boosted
			seq(kems); // Voltorb
		}
		seq(EflNewEnemyMonSegment.any());
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
			kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(123)}; // smog
      kems.attackCount[2][0] = 1; // ember
      kems.numExpGainers = 2; // boosted
			seq(kems); // Koffing
		}
		seq(new EflEndFightSegment(1)); // player defeated enemy

		save("mm3");
		load("mm3");

		seq(new EflWalkToSegment(12, 6, false)); // go to fossil
		seq(new MoveSegment(new EflOverworldInteract(6))); // grab fossil
		seq(new EflSkipTextsSegment(1, true)); // grab fossil
		seq(new EflSkipTextsSegment(1)); // got fossil
		seq(new EflTextSegment()); // put fossil in bag

		seq(new EflWalkToSegment(5,7)); // go to MtMoon2
		seq(new EflWalkToSegment(27,3)); // leave MtMoon

		seqUnbounded(new EflWalkToSegment(64, 9, false)); // hop into grass
    seq(new EflEncounterSegment(0x6c, Move.DOWN)); // Ekans
    seq(new EflCatchMonSegment(0).withBufferSize(0));

    seqUnbounded(new EflWalkToSegment(67, 14)); // go in grass
    seqUnbounded(new EflWalkToSegment(68, 14)); // go in grass
    seq(new EflEncounterSegment(0xa5, Move.RIGHT)); // Rattata
    seq(new EflCatchMonSegment(0).withBufferSize(0));

    seqUnbounded(new EflWalkToSegment(71, 14)); // go in grass
    seqUnbounded(new EflWalkToSegment(72, 14)); // go in grass
    seq(new EflEncounterSegment(0x5, Move.RIGHT)); // Spearow
    seq(new EflCatchMonSegment(0));

		seq(new EflWalkToSegment(90, 11)); // enter Cerulean
	}
}
