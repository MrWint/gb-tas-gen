package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import static mrwint.gbtasgen.metric.comparator.Comparator.GREATER_EQUAL;

import mrwint.gbtasgen.metric.pokemon.CheckAttackMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckDisableEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.pokemon.EflSelectMoveInList;
import mrwint.gbtasgen.move.pokemon.gen1.EflOverworldInteract;
import mrwint.gbtasgen.segment.pokemon.EflEvolutionSegment;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.pokemon.EflWalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflCheckMoveDamage;
import mrwint.gbtasgen.segment.pokemon.fight.EflCheckMoveOrderMetric;
import mrwint.gbtasgen.segment.pokemon.fight.EflEndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflInitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment.EflEnemyMoveDesc;
import mrwint.gbtasgen.segment.pokemon.fight.EflNewEnemyMonSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.util.EflUtil.PressMetric;

public class SurgeRed extends SeqSegment {

	@Override
	public void execute() {
    seq(new EflWalkToSegment(3, 0)); // leave house
    seq(new EflWalkToSegment(30, 9)); // engage rocket
		seq(new EflInitFightSegment(4)); // start fight
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[3][1] = 1; // mega punch crit
			seq(kems); // machop
		}
		seq(EflNewEnemyMonSegment.any()); // next mon
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[3][1] = 1; // mega punch crit
			seq(kems); // drowzee
		}
		seq(new EflEndFightSegment(2)); // player defeated enemy

		seq(new EflSkipTextsSegment(3)); // after rival battle texts

    seq(new EflWalkToSegment(28, 36)); // leave cerulean
    seq(new EflWalkToSegment(17, 27)); // enter passage
    seq(new EflWalkToSegment(4, 4)); // enter passage
    seq(new EflWalkToSegment(2, 41)); // walk passage
    seq(new EflWalkToSegment(4, 8, false)); // leave passage
    seq(new EflWalkToSegment(11, 28)); // engage trainer
    seq(new EflWalkToSegment(11, 29)); // engage trainer
    seqMove(new EflOverworldInteract(5)); // talk to trainer

    save("su1");
    load("su1");

		seq(new EflInitFightSegment(1)); // start fight
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[2][1] = 1; // ember crit
//      kems.attackCount[3][0] = 1; // mega punch
			seq(kems); // pidgey
		}
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[2][1] = 1; // ember crit
//      kems.attackCount[3][0] = 1; // mega punch
      seq(kems); // pidgey
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[2][1] = 1; // ember crit
//      kems.attackCount[3][0] = 1; // mega punch
      seq(kems); // pidgey
    }
		seq(new EflEndFightSegment(1)); // player defeated enemy

		seq(new EflWalkToSegment(10, 31)); // walk up to trainer

		seq(new EflInitFightSegment(1)); // start fight
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[0][1] = 1; // scratch crit
			seq(kems); // spearow
		}
		seq(EflNewEnemyMonSegment.any()); // next mon
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 39)}; // tail whip
			kems.attackCount[3][1] = 1; // mega punch crit
      kems.numExpGainers = 2; // level up to 25
			seq(kems); // raticate
		}
		seq(new EflEndFightSegment(1)); // player defeated enemy

    seq(new EflEvolutionSegment(true));

    save("su2");
    load("su2");

    seq(new EflWalkToSegment(9, 36)); // enter vermilion

    seq(new EflWalkToSegment(9, 13)); // enter fan club
    seq(new EflWalkToSegment(2, 1)); // go to leader
    seqMove(new EflOverworldInteract(5)); // talk to leader
    seq(new EflSkipTextsSegment(6));
    seq(new EflSkipTextsSegment(1, true)); // hear about mon
    seq(new EflSkipTextsSegment(25));
    seq(new EflWalkToSegment(2, 6)); // leave
    seq(new EflWalkToSegment(2, 8, false)); // leave

    seq(new EflWalkToSegment(18, 30)); // ss anne
    seq(new EflSkipTextsSegment(4)); // flash ticket
    seq(new EflWalkToSegment(18, 32, false)); // enter ss anne
    seq(new EflWalkToSegment(14, 3, false)); // enter ss anne
    seq(new EflWalkToSegment(7, 7)); // stairs
    seq(new EflWalkToSegment(2, 6)); // stairs
    seq(new EflWalkToSegment(36, 8, false).setBlockAllWarps(true)); // engage rival

    save("tmp");
    load("tmp");

		seq(new EflInitFightSegment(7)); // start fight
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.customHitWith(16, true, false, 20, 21)}; // gust 21 dmg
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 28)}; // sand attack
      kems.attackCount[2][0] = 1; // ember
      kems.attackCount[2][1] = 1; // ember crit
			seq(kems); // pidgeotto
		}
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 39)}; // tail whip
      kems.attackCount[2][1] = 1; // ember crit
      seq(kems); // raticate
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckDisableEffectMisses(), 50)}; // disable
      kems.attackCount[3][0] = 1; // mega punch
      kems.numExpGainers = 2; // level up to 26
      seq(kems); // kadabra
    }

    save("tmp2");
    load("tmp2");

    seq(EflNewEnemyMonSegment.any()); // next mon
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.customHitWith(55, true, true, 37, 38)}; // water gun 37-38 dmg
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(145)}; // bubble
      kems.attackCount[0][1] = 1; // tackle crit
      kems.attackCount[3][1] = 1; // mega punch crit
			seq(kems); // wartortle
		}
		seq(new EflEndFightSegment(3)); // player defeated enemy

    seq(new EflEvolutionSegment(true)); // Charmander evolution

    seq(new EflSkipTextsSegment(5)); // after battle text
    seq(new EflWalkToSegment(36, 4)); // stairs
    seq(new EflWalkToSegment(4, 4)); // engage captain
    seq(new EflWalkToSegment(4, 3)); // engage captain
    seqMove(new EflOverworldInteract(1)); // talk to captain
    seq(new EflSkipTextsSegment(4)); // captain
    seq(new EflTextSegment()); // rub
    seq(new EflSkipTextsSegment(9)); // captain
    seq(new EflWalkToSegment(0, 7)); // stairs

    save("su3");
    load("su3");

    seq(new EflWalkToSegment(21, 11)); // door
    seq(new EflWalkToSegment(0, 14, false)); // door
    seqMove(new EflOverworldInteract(3)); // trainer
    seq(new EflInitFightSegment(2)); // start fight
    {
      seqEflButton(Move.A, PressMetric.PRESSED); // fight
      seqMove(new EflSelectMoveInList(1, 4)); // rage
      delayEfl(new SeqSegment() {

        @Override
        protected void execute() {
          seqEflButtonUnboundedNoDelay(Move.A, PressMetric.PRESSED);
          seqMetric(new EflCheckMoveOrderMetric(true, 44)); // bite
          seqUnbounded(new EflTextSegment(Move.A)); // use rage
          seqMetric(new CheckAttackMisses()); // miss
        }
      });
      seq(new EflTextSegment(Move.A)); // but it missed
      delayEfl(new SeqSegment() {

        @Override
        protected void execute() {
          seqEflButtonUnboundedNoDelay(Move.B); // close box
          seqUnbounded(new EflTextSegment(Move.A)); // use bite
          seqMetric(new EflCheckMoveDamage(false, false, 0, 8, Integer.MAX_VALUE, false),GREATER_EQUAL, 8); // kill
        }
      });
    }
    save("tmp");
    load("tmp");

    seq(new EflSkipTextsSegment(1)); // a fainted
    seq(new EflSkipTextsSegment(2)); // no usable pokemon, black out


//    seq(new EflWalkToSegment(2, 4, false).setBlockAllWarps(true)); // stairs
//    seq(new EflWalkToSegment(7, 7)); // leave ss.anne
//    seq(new EflWalkToSegment(26, -1, false).setMaxBufferSize(0)); // leave ss.anne
//
//    seqEflSkipInputUnbounded(5); // Watch SS Anne
//
//    {
//      seqEflButton(Move.START, PressMetric.PRESSED);
//      seqEflScrollA(2); // items
//      seqEflScrollFastAF(8 + 1); // HM01
//      seqEflSkipInput(1);
//      seqEflButton(Move.A);
//      seq(new EflSkipTextsSegment(2)); // booted up HM, contains xyz
//      seq(new EflSkipTextsSegment(1, true)); // learn
//      seqEflSkipInput(1);
//      seqEflButton(Move.A); // charmander
//      seq(new EflOverrideMoveSegment(0)); // Tackle to Cut
//      seqEflButton(Move.B); // close menu
//      seqEflButton(Move.START); // close menu
//    }
	}
}
