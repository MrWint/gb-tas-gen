package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import static mrwint.gbtasgen.move.Move.A;
import static mrwint.gbtasgen.move.Move.B;
import static mrwint.gbtasgen.move.Move.DOWN;
import static mrwint.gbtasgen.move.Move.LEFT;
import static mrwint.gbtasgen.move.Move.RIGHT;
import static mrwint.gbtasgen.move.Move.START;
import static mrwint.gbtasgen.move.Move.UP;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.CLEFAIRY;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DISABLE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.POUND;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SANDSHREW;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SCREECH;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SMOG;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SQUIRTLE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TACKLE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TAIL_WHIP;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TM01;
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
import mrwint.gbtasgen.segment.pokemon.gen1.common.Constants;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflEncounterSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSelectItemSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;

public class MtMoonBlue extends SeqSegment {

	@Override
	public void execute() {
//	  {
//	    seqUnbounded(new EflWalkToSegment(11, 5)); // enter Center
//	    seqUnbounded(new EflWalkToSegment(9, 6, false)); // Man
////	    seqUnbounded(new EflWalkToSegment(9, 6, false)); // Man
//      delayEfl(new SeqSegment() {
//        @Override
//        protected void execute() {
//          seqEflButtonUnboundedNoDelay(A);
//          seqMetric(new EflOverworldInteract.OverworldInteractMetric(4));
//        }
//      });
//	    seq(new EflSkipTextsSegment(4));
//	    seq(new EflSkipTextsSegment(1, true)); // take Magikarp
//	    seq(new EflTextSegment()); // got
//	    seq(new EflSkipTextsSegment(2)); // no nickname
//	    seq(new EflWalkToSegment(4, 6)); // leave
//	    seq(new EflWalkToSegment(4, 8, false)); // leave
//	  }
//
//	  seq(new EflWalkToSegment(18, 5)); // enter Mt. Moon
//
//    save("mm0a");
//    load("mm0a");
//
//    seq(new EflWalkToSegment(33, 31).setBlockAllWarps(true)); // Rare Candy
//    seq(new EflWalkToSegment(34, 31)); // Rare Candy
//    seqMove(new EflOverworldInteract(10)); // collect Rare Candy
//    seq(new EflTextSegment()); // found Rare Candy
//
//		seq(new EflWalkToSegment(17, 11, false).setBlockAllWarps(true)); // l2
//		seq(new EflWalkToSegment(17, 11)); // l3
//
//		seq(new EflWalkToSegment(29,6));
//		seqEflButton(UP); // face item
//		seqMove(new EflOverworldInteract(9)); // collect TM01
//		seq(new EflTextSegment()); // found TM01
//
//    seqEflSkipInput(1);
//    seq(new EflSelectItemSegment(TM01).fromOverworld().andUse());
//    seq(new EflLearnTMSegment(SQUIRTLE, 0)); // tackle -> mega punch
//    seqEflButton(B);
//    seqEflButton(START);
//		seq(new EflWalkToSegment(25,9));
//		seq(new EflWalkToSegment(25,9));
//
//    seq(new EflWalkToSegment(4, 2).setBlockAllWarps(true)); // Moon Stone
//    seq(new EflWalkToSegment(3, 2)); // Moon Stone
//    seqMove(new EflOverworldInteract(9)); // Moon Stone
//    seq(new EflTextSegment()); // Moon Stone
//
//		seq(new EflWalkToSegment(5, 5)); // go to MtMoon2
//		seq(new EflWalkToSegment(21, 17)); // go to MtMoon3
//
//    save("mm1");
    load("mm1");

    seqUnbounded(new EflWalkToSegment(34, 23)); // go to encounter

    seq(new EflEncounterSegment(CLEFAIRY, LEFT));
    seq(new EflCatchMonSegment());

		seq(new EflWalkToSegment(11, 16, false)); // go to rocket
		seqMove(new EflOverworldInteract(2)); // talk to rocket

		seq(new EflInitFightSegment(3)); // start fight
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), TAIL_WHIP)};
			kems.attackCount[0][1] = 1; // mega punch crit
			seq(kems); // Rattata
		}
		seq(EflNewEnemyMonSegment.any());
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
			kems.attackCount[0][1] = 1; // mega punch crit
			kems.numExpGainers = 2; // Squirtle, level up to 16
			seq(kems); // Zubat
		}
		seq(new EflEndFightSegment(1)); // player defeated enemy

    save("mm2");
    load("mm2");

    seq(new EflEvolutionSegment(true));

    seq(new EflWalkToSegment(17, 12)); // Moon Stone
    seqEflButton(A); // collect Moon Stone
    seq(new EflTextSegment()); // found Moon Stone

		seq(new EflWalkToSegment(13, 8));
		seq(new EflInitFightSegment(3)); // start fight
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(POUND)};
//			kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckDisableEffectMisses(), DISABLE)};
      kems.attackCount[0][1] = 1; // mega punch crit
      kems.attackCount[2][0] = 1; // bubble
			seq(kems); // Grimer
		}
		seq(EflNewEnemyMonSegment.any());
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), SCREECH)};
			kems.attackCount[0][1] = 1; // mega punch crit
			seq(kems); // Voltorb
		}
		seq(EflNewEnemyMonSegment.any());
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(TACKLE, SMOG)};
//			kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(SMOG)};
			kems.attackCount[0][1] = 1; // mega punch crit
      kems.attackCount[2][1] = 1; // bubble crit
			kems.numExpGainers = 2; // Squirtle, level up to 17
			seq(kems); // Koffing
		}
		seq(new EflEndFightSegment(1)); // player defeated enemy

		save("mm3");
		load("mm3");

    seq(new EflEvolutionSegment(true));

		seq(new EflWalkToSegment(13, 7)); // go to fossil
		seq(new MoveSegment(new EflOverworldInteract(7))); // grab fossil
		seq(new EflSkipTextsSegment(1, true)); // grab fossil
		seq(new EflSkipTextsSegment(1)); // got fossil
		seqUnbounded(new EflTextSegment()); // put fossil in bag
		seqUnbounded(new EflWalkToSegment(13, 6)); // go upwards (avoid running into moved nerd)

		seqUnbounded(new EflWalkToSegment(5, 7)); // go to MtMoon2
		seqUnbounded(new EflWalkToSegment(27, 3)); // leave MtMoon

//    seq(new EflWalkToSegment(76, 9, false)); // hop into grass

    seqUnbounded(new EflWalkToSegment(64, 9, false)); // hop into grass
//    seqUnbounded(new EflWalkToSegment(72, 14)); // go in grass
    seq(new EflEncounterSegment(SANDSHREW, DOWN));
    seq(new EflCatchMonSegment());

		seq(new EflWalkToSegment(90, 11)); // enter Cerulean
	}
}
