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

public class MtMoonRed extends SeqSegment {

	@Override
	public void execute() {
		seq(new EflWalkToSegment(18,5));

    seq(new EflWalkToSegment(33, 31, false).setBlockAllWarps(true)); // Rare Candy
    seq(new EflWalkToSegment(34, 31)); // Rare Candy
    seq(new MoveSegment(new EflOverworldInteract(10))); // collect Rare Candy
    seq(new EflTextSegment()); // found Rare Candy

    seq(new EflWalkToSegment(34, 23)); // Escape Rope
    seq(new EflWalkToSegment(35, 23)); // Escape Rope
    seq(new MoveSegment(new EflOverworldInteract(11))); // collect Escape Rope
    seq(new EflTextSegment()); // found Escape Rope

		seq(new EflWalkToSegment(17,11,false).setBlockAllWarps(true)); // l2
		seq(new EflWalkToSegment(17,11)); // l3

		seq(new EflWalkToSegment(29,6));
		seqEflButton(Move.UP); // face item
		seq(new MoveSegment(new EflOverworldInteract(9))); // collect TM01
		seq(new EflTextSegment()); // found TM01

    seqEflSkipInput(1);
		seqEflButton(Move.START);
		seqEflScroll(2);
		seqEflButton(Move.A); // items
		{ // learn TM01
			seqEflScrollAF(4);
			seqEflSkipInput(1);
			seqEflButton(Move.A); // use
			seq(new EflLearnTMSegment(0, 3)); // leer for mega punch
		}
    seqEflButton(Move.B);
    seqEflButton(Move.START);
		seq(new EflWalkToSegment(25,9));
		seq(new EflWalkToSegment(25,9));

    seq(new EflWalkToSegment(5, 5).setBlockAllWarps(true)); // go to MtMoon2
		seq(new EflWalkToSegment(21,17)); // go to MtMoon3

    save("mm1");
    load("mm1");

		seq(new EflWalkToSegment(11, 16, false)); // go to rocket
		seqMove(new EflOverworldInteract(2)); // talk to rocket

		seq(new EflInitFightSegment(3)); // start fight
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 39)}; // tail whip
			kems.attackCount[2][1] = 1; // 1x ember crit
			seq(kems); // Rattata
		}
		seq(EflNewEnemyMonSegment.any());
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
			kems.attackCount[3][1] = 1; // 1x mega punch crit
			kems.numExpGainers = 2; // level up to 16
			seq(kems); // Zubat
		}
		seq(new EflEndFightSegment(1)); // player defeated enemy

    save("mm2");
    load("mm2");

    seq(new EflEvolutionSegment(true));

		seq(new EflWalkToSegment(13,8));
		seq(new EflInitFightSegment(3)); // start fight
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(1)}; // pound
			kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckDisableEffectMisses(), 50)}; // disable
      kems.attackCount[3][0] = 2; // mega punch
			seq(kems); // Grimer
		}
		seq(EflNewEnemyMonSegment.any());
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 103)}; // screech
			kems.attackCount[3][1] = 1; // 1x mega punch crit
      kems.numExpGainers = 2; // level up to 17
			seq(kems); // Voltorb
		}
		seq(EflNewEnemyMonSegment.any());
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
			kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(123)}; // smog
      kems.attackCount[3][0] = 1; // mega punch
      kems.attackCount[2][1] = 1; // ember crit
			seq(kems); // Koffing
		}
		seq(new EflEndFightSegment(1)); // player defeated enemy

		save("mm3");
		load("mm3");

    seq(new EflEvolutionSegment(true));

		seq(new EflWalkToSegment(13,7)); // go to fossil
		seq(new MoveSegment(new EflOverworldInteract(7))); // grab fossil
		seq(new EflSkipTextsSegment(1, true)); // grab fossil
		seq(new EflSkipTextsSegment(1)); // got fossil
		seq(new EflTextSegment()); // put fossil in bag
		seq(new EflWalkToSegment(13,6)); // go upwards (avoid running into moved nerd)

		seq(new EflWalkToSegment(5,7)); // go to MtMoon2
		seq(new EflWalkToSegment(27,3)); // leave MtMoon

    seq(new EflWalkToSegment(76, 9, false)); // hop into grass

		seq(new EflWalkToSegment(90,10)); // enter Cerulean
	}
}
