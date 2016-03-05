package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import static mrwint.gbtasgen.metric.comparator.Comparator.GREATER_EQUAL;
import static mrwint.gbtasgen.move.Move.A;
import static mrwint.gbtasgen.move.Move.B;
import static mrwint.gbtasgen.move.Move.LEFT;
import static mrwint.gbtasgen.move.Move.RIGHT;
import static mrwint.gbtasgen.move.Move.START;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.ABRA;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.CATERPIE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.CLEFABLE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.CLEFAIRY;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.ESCAPE_ROPE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.GROWL;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.JIGGLYPUFF;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.LEER;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.MAGIKARP;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.METAPOD;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.MOON_STONE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SANDSHREW;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SAND_ATTACK;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SELF_DESTRUCT;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.SQUIRTLE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TACKLE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TAIL_WHIP;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TM11;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.WEEDLE;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.MENU;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.PRESSED;
import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.metric.comparator.Comparator;
import mrwint.gbtasgen.metric.pokemon.CheckAttackMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputBoxMons;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputParty;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.pokemon.EflSelectMoveInList;
import mrwint.gbtasgen.move.pokemon.gen1.EflOverworldInteract;
import mrwint.gbtasgen.segment.pokemon.EflCatchMonSegment;
import mrwint.gbtasgen.segment.pokemon.EflEvolutionSegment;
import mrwint.gbtasgen.segment.pokemon.EflLearnTMSegment;
import mrwint.gbtasgen.segment.pokemon.EflOverrideMoveSegment;
import mrwint.gbtasgen.segment.pokemon.EflPokecenterSegment;
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
import mrwint.gbtasgen.segment.pokemon.gen1.common.Constants;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflDepositMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflEncounterSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSelectItemSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSelectMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflWithdrawMonSegment;
import mrwint.gbtasgen.segment.util.CheckMetricSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.util.EflUtil.PressMetric;

public class NuggetBridgeBlue extends SeqSegment {

	@Override
	public void execute() {
//    seq(new EflWalkToSegment(30, 19)); // enter gym
//    seq(new EflWalkToSegment(5, 3)); // engage
//    seq(new EflInitFightSegment(2)); // start fight
//    {
//      {
//        seqEflButton(A, PRESSED); // fight
//        seqMove(new EflSelectMoveInList(2, 4));
//        delayEfl(new SeqSegment() {
//          @Override
//          protected void execute() {
//            seqEflButtonUnboundedNoDelay(A); // ember
//            seqMetric(new EflCheckMoveOrderMetric(false, TAIL_WHIP));
//            seqUnbounded(new EflTextSegment()); // goldeen uses tail whip
//            seqMetric(new CheckLowerStatEffectMisses());
//            seq(new EflTextSegment()); // but it failed
//          }
//        });
//        delayEfl(new SeqSegment() {
//          @Override
//          protected void execute() {
//            seqEflButtonUnboundedNoDelay(B); // skip text
//            seqUnbounded(new EflTextSegment()); // uses bubble
//            seqMetric(new EflCheckMoveDamage(false, true, 0, 3, 3, false),GREATER_EQUAL, 3);
//          }
//        });
//        seqUnbounded(new EflTextSegment()); // not effective
//        delayEfl(new SeqSegment() {
//          @Override
//          protected void execute() {
//            seqEflButtonUnboundedNoDelay(B); // skip text
//            seqMetric(new EflCheckAdditionalTexts(), Comparator.EQUAL, 0);
//          }          
//        });
//        seq(new EflSkipTextsSegment(1)); // goldeen's speed fell
//      }
//      save("tmp");
//      load("tmp");
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), TAIL_WHIP)};
////      kems.attackCount[0][0] = 2; // mega punch
////      kems.attackCount[0][1] = 1; // mega punch crit
//      kems.attackCount[0][1] = 2; // mega punch crit
//      seq(kems); // Goldeen
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//
//    save("nb1");
//    load("nb1");
//
//    seq(new EflWalkToSegment(4, 2, false)); // walk up to misty
//    seqMove(new EflOverworldInteract(1)); // talk to misty
//    seq(new EflInitFightSegment(9)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(TACKLE)};
//      kems.attackCount[0][0] = 1; // mega punch
//      kems.attackCount[0][1] = 1; // mega punch crit
//      kems.numExpGainers = 2; // Squirtle, level up to 18
//      seq(kems); // Staryu
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    save("tmp");
//    load("tmp");
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(TACKLE)};
//      kems.attackCount[0][0] = 2; // mega punch
//      kems.attackCount[0][1] = 2; // mega punch crit
//      kems.numExpGainers = 2; // Squirtle, level up to 19
//      seq(kems); // Starmie
//    }
//    seq(new EflEndFightSegment(4)); // player defeated enemy
//
//    save("nb2");
//    load("nb2");
//
//    seq(new EflEvolutionSegment(true)); // cancel evolution
//
//    seq(new EflSkipTextsSegment(9)); // after battle speech
//    seq(new EflWalkToSegment(5, 14, false)); // leave gym
//
//    seq(new EflSelectItemSegment(MOON_STONE).fromOverworld().andUse());
//    seq(new EflSelectMonSegment(CLEFAIRY));
//    seq(new EflEvolutionSegment()); // Clefable
//    seq(new EflSelectItemSegment(TM11).andUse());
//    seq(new EflLearnTMSegment(SQUIRTLE, 3)); // replace water gun with bubblebeam
//    seqEflButton(B);
//    seqEflButton(START);
//
//    {
//      seqUnbounded(new EflWalkToSegment(19, 17)); // enter Center
//      seqUnbounded(new EflWalkToSegment(13, 4)); // PC
//      seq(new EflWalkToSegment(13, 3, false)); // PC
//
//      seqMetric(new OutputParty());
//      seqMetric(new OutputBoxMons());
//
//      {
//        seqEflButton(A); // use PC
//        seq(new EflSkipTextsSegment(1)); // turned on
//        seqEflButton(A); // someone's PC
//        seq(new EflSkipTextsSegment(2)); // accessed, mon storage
//        seq(new EflDepositMonSegment(JIGGLYPUFF));
//        seq(new EflDepositMonSegment(MAGIKARP));
//        seq(new EflDepositMonSegment(CLEFABLE));
//        seqUnbounded(new EflDepositMonSegment(SANDSHREW));
//        seqEflButtonUnbounded(B, MENU); // cancel
//        seqEflButtonUnbounded(B, PRESSED); // cancel
//      }
//      seqUnbounded(new EflWalkToSegment(3, 4)); // walk to counter
//      seq(new EflPokecenterSegment(true)); // set warp point in center
//    }
//
//    seq(new EflWalkToSegment(9, 11)); // go to house
//    seq(new EflWalkToSegment(2, 0)); // go through house
//    seq(new EflWalkToSegment(13, 8)); // Rare Candy
//    seq(new EflWalkToSegment(14, 8)); // Rare Candy
//    seqEflButton(A); // Rare Candy
//    seq(new EflTextSegment()); // Rare Candy
//    seq(new EflWalkToSegment(9, 10, false)); // go to house
//    seq(new EflWalkToSegment(2, 8, false)); // go through house
//
//    save("nb25");
//    load("nb25");
//
//    seq(new EflWalkToSegment(20,6)); // go to rival fight
//		seq(new EflInitFightSegment(8)); // start fight
//		{
//      {
//        seqEflButton(A, PRESSED); // fight
//        seqMove(new EflSelectMoveInList(3, 4));
//        delayEfl(new SeqSegment() {
//          @Override
//          protected void execute() {
//            seqEflButtonUnboundedNoDelay(A); // ember
//            seqMetric(new EflCheckMoveOrderMetric(false, SAND_ATTACK));
//            seqUnbounded(new EflTextSegment()); // pidgeotto uses sand attack
//            seqMetric(new CheckLowerStatEffectMisses());
//            seq(new EflTextSegment()); // but it failed
//          }
//        });
//        delayEfl(new SeqSegment() {
//          @Override
//          protected void execute() {
//            seqEflButtonUnboundedNoDelay(B); // skip text
//            seqUnbounded(new EflTextSegment()); // uses bubble
//            seqMetric(new EflCheckMoveDamage(true, true, 0, 41, 41, false),GREATER_EQUAL, 41);
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
//        seq(new EflSkipTextsSegment(1)); // pidgeotto's speed fell
//      }
//		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//			kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), SAND_ATTACK)};
//      kems.attackCount[2][1] = 1; // bubble crit
////      kems.attackCount[3][1] = 1; // bubblebeam crit
//      kems.numExpGainers = 2; // Squirtle, level up to 20
//			seq(kems); // pidgeotto
//		}
//		seq(EflNewEnemyMonSegment.any()); // next mon
//    save("tmp");
//    load("tmp");
//		{
//		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//			kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(Metric.TRUE)}; // teleport
//			kems.attackCount[0][0] = 1; // mega punch
//			seq(kems); // abra
//		}
//		seq(EflNewEnemyMonSegment.any()); // next mon
//    save("tmp2");
//    load("tmp2");
//		{
//		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), TAIL_WHIP)};
//			kems.attackCount[3][0] = 1; // bubblebeam
//			seq(kems); // rattata
//		}
//		seq(EflNewEnemyMonSegment.any()); // next mon
//    save("tmp3");
//    load("tmp3");
//		{
//		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//			kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), GROWL)};
//      kems.attackCount[0][0] = 2; // mega punch
//			seq(kems); // squirtle
//		}
//		seq(new EflEndFightSegment(2)); // player defeated enemy
//
//    save("nb3a");
//    load("nb3a");
//
//    seq(new EflEvolutionSegment(true)); // cancel evolution
//
//		seq(new EflSkipTextsSegment(14)); // after rival battle texts
//
//		seq(new EflWalkToSegment(21, -1)); // walk up
//		seq(new EflWalkToSegment(11, 32)); // walk up to trainer
//		seqMove(new EflOverworldInteract(7)); // talk to trainer 1
//
//		seq(new EflInitFightSegment(4)); // start fight
//		{
//		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[3][0] = 1; // bubblebeam
//			seq(kems); // caterpie
//		}
//		seq(EflNewEnemyMonSegment.any()); // next mon
//		{
//		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//			kems.attackCount[3][0] = 1; // bubblebeam
//      kems.numExpGainers = 2; // Squirtle, level up to 21
//			seq(kems); // weedle
//		}
//		seq(new EflEndFightSegment(1)); // player defeated enemy
//
//    save("nb4a");
//    load("nb4a");
//
//    seq(new EflEvolutionSegment(true)); // cancel evolution
//
//		seq(new EflWalkToSegment(10, 29)); // walk up to trainer
//		seqMove(new EflOverworldInteract(6)); // talk to trainer 2
//
//		seq(new EflInitFightSegment(1)); // start fight
//		{
//			EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[3][0] = 1; // bubblebeam
//			seq(kems); // pidgey
//		}
//		seq(EflNewEnemyMonSegment.any()); // next mon
//		{
//		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[3][1] = 1; // bubblebeam crit
//			seq(kems); // nidoF
//		}
//		seq(new EflEndFightSegment(1)); // player defeated enemy
//
//    save("nb5");
//    load("nb5");
//
//		seq(new EflWalkToSegment(11, 26)); // walk up to trainer
//		seqMove(new EflOverworldInteract(5)); // talk to trainer 3
//
//		seq(new EflInitFightSegment(1)); // start fight
//		{
//		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[3][0] = 1; // bubblebeam
//			seq(kems); // rattata
//		}
//		seq(EflNewEnemyMonSegment.any()); // next mon
//		{
//		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[3][0] = 1; // bubblebeam
//			seq(kems); // ekans
//		}
//		seq(EflNewEnemyMonSegment.any()); // next mon
//		{
//		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[3][0] = 1; // bubblebeam
//			seq(kems); // zubat
//		}
//		seq(new EflEndFightSegment(1)); // player defeated enemy
//
//    save("nb6");
//    load("nb6");
//
//		seq(new EflWalkToSegment(10, 23)); // walk up to trainer
//		seqMove(new EflOverworldInteract(4)); // talk to trainer 4
//
//		seq(new EflInitFightSegment(1)); // start fight
//		{
//		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[3][1] = 1; // bubblebeam crit
//			seq(kems); // pidgey
//		}
//		seq(EflNewEnemyMonSegment.any()); // next mon
//		{
//		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[3][1] = 1; // bubblebeam crit
//      kems.numExpGainers = 2; // Squirtle, level up to 22
//			seq(kems); // nidoF
//		}
//
//    save("nb7");
//    load("nb7");
//
//    seq(new EflOverrideMoveSegment(1)); // override leer with bite
//
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//
//    seq(new EflEvolutionSegment(true)); // cancel evolution
//
//		seq(new EflWalkToSegment(11, 20)); // walk up to trainer
//		seqMove(new EflOverworldInteract(3)); // talk to trainer 5
//
//		seq(new EflInitFightSegment(1)); // start fight
//		{
//		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), LEER)};
//			kems.attackCount[0][1] = 1; // mega punch crit
//			seq(kems); // mankey
//		}
//		seq(new EflEndFightSegment(1)); // player defeated enemy
//
//		seq(new EflWalkToSegment(10, 15)); // walk up to rocket
//
//		seq(new EflInitFightSegment(15)); // start fight
//		{
//		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[1][1] = 1; // bite crit
//			seq(kems); // Ekans
//		}
//		seq(EflNewEnemyMonSegment.any()); // next mon
//		{
//		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[1][1] = 1; // bite crit
//			seq(kems); // Zubat
//		}
//		seq(new EflEndFightSegment(1)); // player defeated enemy
//
//		save("nb8");
//		load("nb8");
//
//    seq(new EflSkipTextsSegment(2)); // after rocket battle texts
//    seqUnbounded(new EflSkipTextsSegment(1)); // after rocket battle texts
//
//		seqUnbounded(new EflWalkToSegment(20, 9)); // enter route 25
//
//		seqUnbounded(new EflWalkToSegment(9, 5)); // go in grass
//		seqUnbounded(new EflWalkToSegment(7, 5)); // go in grass
//    seq(new EflEncounterSegment(CATERPIE, LEFT));
//    save("tmp");
//    load("tmp");
//    seq(new EflCatchMonSegment().withBufferSize(0).withExtraSkips(2));
//
//    seqUnbounded(new EflWalkToSegment(4, 5)); // go in grass
////    seqUnbounded(new EflWalkToSegment(7, 4)); // go in grass
////    seqUnbounded(new EflWalkToSegment(6, 4)); // go in grass
//    seq(new EflEncounterSegment(ABRA, LEFT));
//    save("tmp2");
//    load("tmp2");
//    seq(new EflCatchMonSegment().withBufferSize(0).withExtraSkips(4));
//
//    seqUnbounded(new EflWalkToSegment(2, 5)); // go in grass
//    seqUnbounded(new EflWalkToSegment(3, 5)); // go in grass
//    seq(new EflEncounterSegment(METAPOD, RIGHT));
//    save("tmp3");
//    load("tmp3");
//    seq(new EflCatchMonSegment().withBufferSize(0).withExtraSkips(9));
//
//    seqUnbounded(new EflWalkToSegment(6, 5)); // go in grass
////    seqUnbounded(new EflWalkToSegment(6, 5)); // go in grass
//    seq(new EflEncounterSegment(WEEDLE, RIGHT));
//    save("tmp4");
    load("tmp4");
    seq(new EflCatchMonSegment());

    seq(new EflWalkToSegment(14, 7)); // engage hiker

    save("nb9a");
    load("nb9a");

    seqMetric(new OutputParty());

		seq(new EflInitFightSegment(2)); // start fight
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
			kems.attackCount[2][0] = 1; // bubble
			seq(kems); // Onix
		}
		seq(new EflEndFightSegment(1)); // player defeated enemy

		seq(new EflWalkToSegment(18,7)); // walk up to trainer 2
		seq(new EflWalkToSegment(18,8,false)); // walk up to trainer 2
		seqMove(new EflOverworldInteract(4)); // talk to trainer 2

		seq(new EflInitFightSegment(1)); // start fight
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[1][1] = 1; // bite crit
      kems.numExpGainers = 2; // Squirtle, level up to 23
			seq(kems); // nidoM
		}
		seq(EflNewEnemyMonSegment.any()); // next mon
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[3][0] = 1; // bubblebeam // Wartortle
			seq(kems); // nidoF
		}
		seq(new EflEndFightSegment(1)); // player defeated enemy

    save("nb10a");
    load("nb10a");

    seq(new EflEvolutionSegment(true)); // cancel evolution

		seq(new EflWalkToSegment(24, 6)); // walk up to trainer 3

		seq(new EflInitFightSegment(2)); // start fight
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[1][1] = 1; // bite crit
			seq(kems); // rattata
		}
		seq(EflNewEnemyMonSegment.any()); // next mon
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[1][1] = 1; // bite crit
			seq(kems); // ekans
		}
		seq(new EflEndFightSegment(1)); // player defeated enemy

    save("nb11a");
    load("nb11a");

		seq(new EflWalkToSegment(35,4)); // walk up to trainer 4
		seq(new EflWalkToSegment(36,4)); // walk up to trainer 4
		seqMove(new EflOverworldInteract(6)); // talk to trainer 4

		seq(new EflInitFightSegment(2)); // start fight
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[1][1] = 1; // bite crit
			seq(kems); // oddish
		}
		seq(EflNewEnemyMonSegment.any()); // next mon
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[1][1] = 1; // bite crit
			seq(kems); // pidgey
		}
		seq(EflNewEnemyMonSegment.any()); // next mon
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[1][1] = 1; // bite crit
			seq(kems); // oddish
		}
		seq(new EflEndFightSegment(1)); // player defeated enemy

    save("nb12a");
    load("nb12a");

		seq(new EflWalkToSegment(45,3)); // enter bills house

		seq(new EflWalkToSegment(4,5)); // walk up to bill
		seq(new EflWalkToSegment(5,5)); // walk up to bill
		seqMove(new EflOverworldInteract(1)); // talk to bill

		seq(new EflSkipTextsSegment(10));
		seq(new EflSkipTextsSegment(1, true)); // help
		seq(new EflSkipTextsSegment(4));

		seq(new EflWalkToSegment(1,4,false)); // walk up to desk
		seqEflButton(Move.A); // talk to desk
		seq(new EflSkipTextsSegment(2));

		seq(new EflWalkToSegment(4,5)); // walk up to bill
		seq(new EflWalkToSegment(4,4,false)); // walk up to bill
		seqMove(new EflOverworldInteract(2)); // talk to bill
		seq(new EflSkipTextsSegment(18));

		seq(new EflSelectItemSegment(ESCAPE_ROPE).fromOverworld().andUse());
    seqEflSkipInput(2);
	}
}
