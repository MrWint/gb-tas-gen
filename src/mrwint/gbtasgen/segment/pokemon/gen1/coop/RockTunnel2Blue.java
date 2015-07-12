package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import mrwint.gbtasgen.metric.pokemon.gen1.CheckDisableEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.pokemon.gen1.EflOverworldInteract;
import mrwint.gbtasgen.segment.pokemon.EflEvolutionSegment;
import mrwint.gbtasgen.segment.pokemon.EflOverrideMoveSegment;
import mrwint.gbtasgen.segment.pokemon.EflWalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflEndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflInitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment.EflEnemyMoveDesc;
import mrwint.gbtasgen.segment.pokemon.fight.EflNewEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflCancelMoveLearnSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSwapWithSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflUseBikeSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.util.EflUtil.PressMetric;

public class RockTunnel2Blue extends SeqSegment {

	@Override
	public void execute() {

//    seqEflButton(Move.A); // continue game
//    seqEflButton(Move.START);
//    seqEflButton(Move.A);
//    seqEflButton(Move.START);
//    seqEflButton(Move.A);
//	  
//    seq(new EflWalkToSegment(4, 6)); // leave center
//    seq(new EflWalkToSegment(4, 8, false)); // leave center
//
//    seq(new EflWalkToSegment(13, 25)); // enter bike shop
//    seq(new EflWalkToSegment(6, 3, false)); // walk to counter // TODO: fix
//    seqMove(new EflOverworldInteract(1)); // talk to owner
//    seq(new EflSkipTextsSegment(5)); // get bike
//    seq(new EflWalkToSegment(3, 8, false)); // leave shop
//    {
//      seqEflButton(Move.START, PressMetric.PRESSED);
//      seqEflScrollA(2); // mon
//      seq(new EflSwapWithSegment(11));
//      seqEflScrollFastA(-11);
//      seq(new EflSkipTextsSegment(1)); // got on bike
//    }
//    seq(new EflWalkToSegment(19, 26)); // go to bush
//    seq(new EflWalkToSegment(19, 27)); // go to bush
//    {
//      seqEflButton(Move.START, PressMetric.PRESSED);
//      seqEflScrollA(-1); // mon
//      seqEflSkipInput(1);
//      seqEflScrollAF(3); // dux
//      seqEflSkipInput(1);
//      seqEflButton(Move.A); // cut
//      seqEflButton(Move.B); // hacked away (to text scroll)?
//    }
//    seq(new EflWalkToSegment(40, 17)); // leave cerulean
//    seq(new EflWalkToSegment(4, 8)); // go to bush
//    {
//      seqEflButton(Move.START, PressMetric.PRESSED);
//      seqEflButton(Move.A); // mon
//      seqEflSkipInput(1);
//      seqEflButton(Move.A); // dux
//      seqEflSkipInput(1);
//      seqEflButton(Move.A); // cut
//      seqEflButton(Move.B); // hacked away (to text scroll)?
//    }
//    seq(new EflWalkToSegment(13, 8)); // go to trainer
//    seq(new EflWalkToSegment(13, 9)); // go to trainer
//    seqMove(new EflOverworldInteract(1)); // talk to trainer
//		seq(new EflInitFightSegment(2)); // start fight
//		{
//		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[0][1] = 1; // mega punch crit
//			seq(kems); // oddish
//		}
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[1][1] = 1; // bite crit
//      seq(kems); // bellsprout
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[0][1] = 1; // mega punch crit
//      kems.numExpGainers = 2; // level up to 27
//      seq(kems); // oddish
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[1][1] = 1; // bite crit
//      seq(kems); // bellsprout
//    }
//		seq(new EflEndFightSegment(1)); // player defeated enemy
//
//		seq(new EflEvolutionSegment(true));
//
//		save("rt1");
//	  load("rt1");
//
//    seq(new EflWalkToSegment(12, 11, false)); // jump ledge
//    seq(new EflWalkToSegment(40, 10)); // go to trainer
//    seq(new EflWalkToSegment(40, 9)); // go to trainer
//    seqMove(new EflOverworldInteract(9)); // talk to trainer
//    seq(new EflInitFightSegment(1)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[1][1] = 1; // bite crit
//      seq(kems); // caterpie
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[1][1] = 1; // bite crit
//      seq(kems); // weedle
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[3][1] = 1; // bubblebeam crit
//      seq(kems); // venonat
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//    seq(new EflWalkToSegment(51, 5, false)); // jump ledge
//    seq(new EflWalkToSegment(60, 8)); // route 10
//
//    save("rt2");
    load("rt2");

//    seq(new EflWalkToSegment(11, 19)); // enter center

    seq(new EflWalkToSegment(8, 17)); // enter rock tunnel
    seq(new EflWalkToSegment(23, 6)); // engage trainer
    seq(new EflWalkToSegment(23, 7)); // engage trainer
    seqMove(new EflOverworldInteract(4)); // talk to trainer

		seq(new EflInitFightSegment(1)); // start fight
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(125)}; // bone club
      kems.attackCount[3][0] = 1; // bubblebeam
			seq(kems); // cubone
		}
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckDisableEffectMisses(), 50)}; // disable
      kems.attackCount[3][1] = 2; // mega punch crit
      kems.numExpGainers = 2; // level up to 28
      seq(kems); // slowpoke
    }
    seq(new EflCancelMoveLearnSegment()); // Withdraw
		seq(new EflEndFightSegment(1)); // player defeated enemy
		
		seq(new EflEvolutionSegment(true)); // cancel evo
		
    seq(new EflWalkToSegment(37, 3)); // ladder
    seq(new EflWalkToSegment(27, 30)); // engage trainer
    seqMove(new EflOverworldInteract(8)); // talk to trainer

    seq(new EflInitFightSegment(1)); // start fight
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckDisableEffectMisses(), 50)}; // disable
      kems.attackCount[3][1] = 2; // mega punch crit
      seq(kems); // slowpoke
    }
    seq(new EflEndFightSegment(1)); // player defeated enemy

    save("tmp");
    load("tmp");

    seq(new EflWalkToSegment(14, 30)); // engage trainer
    seq(new EflWalkToSegment(14, 29)); // engage trainer
    seqMove(new EflOverworldInteract(6)); // talk to trainer

    seq(new EflInitFightSegment(2)); // start fight
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[1][0] = 1; // bite
      kems.attackCount[1][1] = 1; // bite crit
      seq(kems); // oddish
    }
    seq(new EflOverrideMoveSegment(1)); // Slash for rage
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[0][1] = 1; // mega punch crit
      seq(kems); // bulbasaur
    }
    seq(new EflEndFightSegment(1)); // player defeated enemy

    seq(new EflWalkToSegment(27, 3)); // ladder

    save("rt3");
//    load("rt3");
//
//    seq(new EflWalkToSegment(17, 11)); // ladder
//    seq(new EflWalkToSegment(8, 10)); // engage trainer
//    seq(new EflWalkToSegment(7, 10)); // engage trainer
//    seqMove(new EflOverworldInteract(2)); // talk to trainer
//
//    seq(new EflInitFightSegment(1)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(33)}; // tackle
//      kems.attackCount[0][1] = 1; // cut crit
//      kems.attackCount[2][1] = 1; // ember crit
//      seq(kems); // geodude
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(33)}; // tackle
//      kems.attackCount[2][0] = 1; // ember
//      kems.attackCount[2][1] = 1; // ember crit
//      seq(kems); // geodude
//    }
//
//    save("tmp");
//    load("tmp");
//
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(33)}; // tackle
//      kems.attackCount[2][0] = 2; // ember
//      kems.attackCount[2][1] = 1; // ember crit
//      seq(kems); // graveler
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//
//    save("tmp2");
//    load("tmp2");
//
//    seq(new EflWalkToSegment(3, 3)); // ladder
//    seq(new EflWalkToSegment(24, 24)); // engage trainer
//    seq(new EflWalkToSegment(23, 24)); // engage trainer
//    seqMove(new EflOverworldInteract(6)); // talk to trainer
//
//    seq(new EflInitFightSegment(1)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[0][1] = 1; // cut crit
//      seq(kems); // meowth
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[1][1] = 1; // slash crit
//      kems.numExpGainers = 2; // level up to 31
//      seq(kems); // oddish
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[0][1] = 1; // cut crit
//      seq(kems); // pidgey
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//
//    seq(new EflEvolutionSegment(true));
//
//    seq(new EflWalkToSegment(15, 33)); // leave rock tunnel
//
//    save("rt3");
//    load("rt3");
//
//    seq(new EflWalkToSegment(15, 61, false)); // jump ledge
//    seq(new EflWalkToSegment(9, 72)); // enter lavender
//    seq(new EflWalkToSegment(-1, 8)); // leave lavender
//    seq(new EflWalkToSegment(47, 13)); // engage trainer
//    seqMove(new EflOverworldInteract(8)); // talk to trainer
//
//    seq(new EflInitFightSegment(1)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[3][1] = 1; // mega punch crit
//      seq(kems); // growlithe
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[1][1] = 1; // slash crit
//      seq(kems); // vulpix
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//    seq(new EflWalkToSegment(13, 3)); // enter passage
//    seq(new EflWalkToSegment(4, 4)); // enter passage
//
//    seq(new EflUseBikeSegment(2, 0));
//    seq(new EflWalkToSegment(2, 5)); // walk passage
//    seq(new EflWalkToSegment(4, 8, false)); // exit passage
//    seq(new EflUseBikeSegment(0, 0));
//    seq(new EflWalkToSegment(-1, 3)); // enter celadon
	}
}
