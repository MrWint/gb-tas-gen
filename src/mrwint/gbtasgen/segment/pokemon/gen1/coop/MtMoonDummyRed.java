package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import static mrwint.gbtasgen.move.Move.B;
import static mrwint.gbtasgen.move.Move.START;
import static mrwint.gbtasgen.move.Move.UP;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.CHARMANDER;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DISABLE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.POUND;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SCREECH;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SMOG;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TACKLE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TAIL_WHIP;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TM01;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckDisableEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.move.pokemon.gen1.EflOverworldInteract;
import mrwint.gbtasgen.segment.pokemon.EflEvolutionSegment;
import mrwint.gbtasgen.segment.pokemon.EflLearnTMSegment;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.pokemon.EflWalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflEndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflInitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment.EflEnemyMoveDesc;
import mrwint.gbtasgen.segment.pokemon.fight.EflNewEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.Constants;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSelectItemSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;

public class MtMoonDummyRed extends SeqSegment {

	@Override
	public void execute() {
//		seq(new EflWalkToSegment(18,5));
//
//    seq(new EflWalkToSegment(33, 31).setBlockAllWarps(true)); // Rare Candy
//    seq(new EflWalkToSegment(34, 31)); // Rare Candy
//    seqMove(new EflOverworldInteract(10)); // collect Rare Candy
//    seq(new EflTextSegment()); // found Rare Candy
//
//    seq(new EflWalkToSegment(34, 23)); // Escape Rope
//    seq(new EflWalkToSegment(35, 23)); // Escape Rope
//    seqMove(new EflOverworldInteract(11)); // collect Escape Rope
//    seq(new EflTextSegment()); // found Escape Rope
//
//		seq(new EflWalkToSegment(17,11,false).setBlockAllWarps(true)); // l2
//		seq(new EflWalkToSegment(17,11)); // l3
//
//		seq(new EflWalkToSegment(29,6));
//		seqEflButton(UP); // face item
//		seq(new MoveSegment(new EflOverworldInteract(9))); // collect TM01
//		seq(new EflTextSegment()); // found TM01
//
//    seqEflSkipInput(1);
//    seq(new EflSelectItemSegment(TM01).fromOverworld().andUse());
//    seq(new EflLearnTMSegment(CHARMANDER, 3)); // leer -> mega punch
//    seqEflButton(B);
//    seqEflButton(START);
//		seq(new EflWalkToSegment(25,9));
//		seq(new EflWalkToSegment(25,9));
//
//    seq(new EflWalkToSegment(5, 5).setBlockAllWarps(true)); // go to MtMoon2
//		seq(new EflWalkToSegment(21,17)); // go to MtMoon3
//
//    save("mmd1");
//    load("mmd1");
//
//		seq(new EflWalkToSegment(11, 16, false)); // go to rocket
//		seqMove(new EflOverworldInteract(2)); // talk to rocket
//
//		seq(new EflInitFightSegment(3)); // start fight
//		{
//		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), TAIL_WHIP)};
//			kems.attackCount[0][1] = 1; // ember crit
//			seq(kems); // Rattata
//		}
//		seq(EflNewEnemyMonSegment.any());
//		{
//		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//			kems.attackCount[3][1] = 1; // mega punch crit
//			kems.numExpGainers = 2; // Charmander, level up to 16
//			seq(kems); // Zubat
//		}
//		seq(new EflEndFightSegment(1)); // player defeated enemy
//
//    save("mmd2");
//    load("mmd2");
//
//    seq(new EflEvolutionSegment(true));
//
//		seq(new EflWalkToSegment(13,8));
//		seq(new EflInitFightSegment(3)); // start fight
//		{
//		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(POUND)};
//      kems.attackCount[3][0] = 2; // mega punch
//			seq(kems); // Grimer
//		}
//		seq(EflNewEnemyMonSegment.any());
//    save("tmp1");
//    load("tmp1");
//		{
//		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), SCREECH)};
//			kems.attackCount[3][1] = 1; // mega punch crit
//      kems.numExpGainers = 2; // Charmander, level up to 17
//			seq(kems); // Voltorb
//		}
//		seq(EflNewEnemyMonSegment.any());
//    save("tmp2");
//    load("tmp2");
//		{
//		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(TACKLE, SMOG)};
////      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(TACKLE)};
////      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(SMOG)};
//      kems.attackCount[3][0] = 1; // mega punch
//      kems.attackCount[0][1] = 1; // ember crit
//			seq(kems); // Koffing
//		}
//		seq(new EflEndFightSegment(1)); // player defeated enemy
//
//		save("mmd3b");
		load("mmd3b");

    seq(new EflEvolutionSegment(true));

		seq(new EflWalkToSegment(13,7)); // go to fossil
		seqMove(new EflOverworldInteract(7)); // grab fossil
		seq(new EflSkipTextsSegment(1, true)); // grab fossil
		seq(new EflSkipTextsSegment(1)); // got fossil
		seq(new EflTextSegment()); // put fossil in bag
		seq(new EflWalkToSegment(13, 6)); // go upwards (avoid running into moved nerd)

		seq(new EflWalkToSegment(5, 7)); // go to MtMoon2
		seq(new EflWalkToSegment(27, 3)); // leave MtMoon

    seq(new EflWalkToSegment(76, 9, false)); // hop into grass

		seq(new EflWalkToSegment(90, 10)); // enter Cerulean
	}
}
