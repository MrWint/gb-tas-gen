package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import static mrwint.gbtasgen.metric.comparator.Comparator.GREATER_EQUAL;
import static mrwint.gbtasgen.move.Move.A;
import static mrwint.gbtasgen.move.Move.B;
import static mrwint.gbtasgen.move.Move.DOWN;
import static mrwint.gbtasgen.move.Move.RIGHT;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.JIGGLYPUFF;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.LEER;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SAND_ATTACK;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SPEAROW;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.STRING_SHOT;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TAIL_WHIP;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.PRESSED;

import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.metric.comparator.Comparator;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.move.pokemon.EflSelectMoveInList;
import mrwint.gbtasgen.move.pokemon.gen1.EflOverworldInteract;
import mrwint.gbtasgen.segment.pokemon.EflCatchMonSegment;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.pokemon.EflWalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflCheckAdditionalTexts;
import mrwint.gbtasgen.segment.pokemon.fight.EflCheckMoveDamage;
import mrwint.gbtasgen.segment.pokemon.fight.EflCheckMoveOrderMetric;
import mrwint.gbtasgen.segment.pokemon.fight.EflEndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflInitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment.EflEnemyMoveDesc;
import mrwint.gbtasgen.segment.pokemon.fight.EflNewEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflEncounterSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;

public class Route3Blue extends SeqSegment {

	@Override
	public void execute() {
//		seq(new EflWalkToSegment(11, 6)); // walk up to trainer
//
//		seq(new EflInitFightSegment(1)); // start fight
//		{
//		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//			kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), STRING_SHOT)};
//      kems.attackCount[2][0] = 2; // bubble
//      kems.attackCount[2][1] = 1; // bubble crit
//			kems.numExpGainers = 2; // Squirtle, level up to 12
//			seq(kems); // Caterpie
//		}
//		seq(EflNewEnemyMonSegment.any()); // next mon
//		{
//		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//			kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), STRING_SHOT)};
//			kems.attackCount[2][1] = 2; // bubble crit
//			seq(kems); // Weedle
//		}
//		seq(EflNewEnemyMonSegment.any()); // next mon
//		{
//		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), STRING_SHOT)};
//      kems.attackCount[2][1] = 2; // bubble crit
//			seq(kems); // Caterpie
//		}
//    save("r31");
//    load("r31");
//		seq(new EflEndFightSegment(1)); // player defeated enemy
//
//		seq(new EflWalkToSegment(12, 4)); // walk up to shorts guy
//		seq(new EflWalkToSegment(13, 4)); // walk up to shorts guy
//		seqMove(new EflOverworldInteract(3)); // talk to shorts guy
//		seq(new EflInitFightSegment(2)); // start fight
//		{
//      {
//        seqEflButton(A, PRESSED); // fight
//        seqMove(new EflSelectMoveInList(2, 3));
//        delayEfl(new SeqSegment() {
//          @Override
//          protected void execute() {
//            seqEflButtonUnboundedNoDelay(A); // ember
//            seqMetric(new EflCheckMoveOrderMetric(false, TAIL_WHIP));
//            seqUnbounded(new EflTextSegment()); // rattata uses tail whip
//            seqMetric(new CheckLowerStatEffectMisses());
//            seq(new EflTextSegment()); // but it failed
//          }
//        });
//        delayEfl(new SeqSegment() {
//          @Override
//          protected void execute() {
//            seqEflButtonUnboundedNoDelay(B); // skip text
//            seqUnbounded(new EflTextSegment()); // uses bubble
//            seqMetric(new EflCheckMoveDamage(true, true, 0, 12, 12, false),GREATER_EQUAL, 12);
//          }
//        });
//        seqUnbounded(new EflTextSegment()); // critical hit
//        delayEfl(new SeqSegment() {
//          @Override
//          protected void execute() {
//            seqEflButtonUnboundedNoDelay(B); // skip text
//            seqMetric(new EflCheckAdditionalTexts(), Comparator.EQUAL, 0);
//          }          
//        });
//        seq(new EflSkipTextsSegment(1)); // rattata's speed fell
//      }
//		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), TAIL_WHIP)};
//      kems.attackCount[2][0] = 2; // bubble
////      kems.attackCount[2][1] = 1; // bubble crit
//			kems.numExpGainers = 2; // Squirtle, level up to 13
//			seq(kems); // Rattata
//		}
//		seq(EflNewEnemyMonSegment.any()); // next mon
//    save("tmp1a");
//    load("tmp1a");
//		{
//		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//			kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), LEER)};
//      kems.attackCount[2][0] = 2; // bubble
//      kems.attackCount[2][1] = 1; // bubble crit
//			seq(kems); // Ekans
//		}
//		seq(new EflEndFightSegment(1)); // player defeated enemy
//
//    save("r32");
//    load("r32");
//
//    seq(new EflWalkToSegment(18, 5)); // walk up to trainer
//		seqMove(new EflOverworldInteract(5)); // talk to trainer
//
//		seq(new EflInitFightSegment(1)); // start fight
//		{
//		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), STRING_SHOT)};
//      kems.attackCount[2][0] = 1; // bubble
//      kems.attackCount[2][1] = 1; // bubble crit
//			seq(kems); // Weedle
//		}
//		seq(EflNewEnemyMonSegment.any()); // next mon
//		{
//		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(Metric.TRUE, 106)}; // harden
//      kems.attackCount[2][0] = 1; // bubble
//      kems.attackCount[2][1] = 1; // bubble crit
//			kems.numExpGainers = 2; // Squirtle, level up to 14
//			seq(kems); // Kakuna
//		}
//		seq(EflNewEnemyMonSegment.any()); // next mon
//		{
//		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 81)}; // string shot
//      kems.attackCount[2][0] = 1; // bubble
//      kems.attackCount[2][1] = 1; // bubble crit
//			seq(kems); // Caterpie
//		}
//		seq(EflNewEnemyMonSegment.any()); // next mon
//		{
//		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(Metric.TRUE, 106)}; // harden
//      kems.attackCount[2][0] = 1; // bubble
//      kems.attackCount[2][1] = 1; // bubble crit
//			seq(kems); // Metapod
//		}
//    seq(new EflEndFightSegment(2)); // player defeated enemy
//
//    save("r33");
//    load("r33");
//
//		seq(new EflWalkToSegment(24, 6, false)); // walk up to trainer
//		seqMove(new EflOverworldInteract(8)); // talk to trainer
//		seq(new EflInitFightSegment(1)); // start fight
//		{
//		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 81)}; // string shot
//      kems.attackCount[2][1] = 2; // bubble crit
//      kems.numExpGainers = 3; // Squirtle, level up to 15, learn water gun
//			seq(kems); // Caterpie
//		}
//		seq(EflNewEnemyMonSegment.any()); // next mon
//		{
//		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(Metric.TRUE, 106)}; // harden
//      kems.attackCount[3][1] = 1; // water gun crit
//			kems.numExpGainers = 1; // no level up
//			seq(kems); // Metapod
//		}
//    save("r34");
//    load("r34");
//		seqUnbounded(new EflEndFightSegment(1)); // player defeated enemy
//    save("tmp");
    load("tmp");
//    seqUnbounded(new EflWalkToSegment(27, 10)); // go in grass
//    seqUnbounded(new EflWalkToSegment(29, 8)); // go in grass
    seqUnbounded(new EflWalkToSegment(28, 9)); // go in grass
//    seqUnbounded(new EflWalkToSegment(28, 10)); // go in grass
    seq(new EflEncounterSegment(SPEAROW, DOWN));
    save("tmp2");
    load("tmp2");
    seq(new EflCatchMonSegment().withBufferSize(0));
    seqUnbounded(new EflWalkToSegment(29, 11)); // go in grass
    seq(new EflEncounterSegment(JIGGLYPUFF, RIGHT));
    seq(new EflCatchMonSegment());
		seq(new EflWalkToSegment(59, -1, false)); // leave route 3
	}
}
