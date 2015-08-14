package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import mrwint.gbtasgen.metric.pokemon.CheckEncounterMetric;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.pokemon.gen1.EflOverworldInteract;
import mrwint.gbtasgen.segment.pokemon.EflCatchMonSegment;
import mrwint.gbtasgen.segment.pokemon.EflEvolutionSegment;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.pokemon.EflWalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflEndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflInitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment.EflEnemyMoveDesc;
import mrwint.gbtasgen.segment.pokemon.fight.EflNewEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflEncounterSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflUseBikeSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;

public class RockTunnelBlue extends SeqSegment {

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
//    seq(new EflUseBikeSegment(2, 0));
//
//    seq(new EflWalkToSegment(8, 17)); // enter rock tunnel
//    seq(new EflWalkToSegment(23, 6)); // engage trainer
//    seq(new EflWalkToSegment(23, 7)); // engage trainer
//    seqMove(new EflOverworldInteract(4)); // talk to trainer
//
//		seq(new EflInitFightSegment(1)); // start fight
//		{
//		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(125)}; // bone club
//      kems.attackCount[1][0] = 1; // bite
//      kems.attackCount[2][1] = 1; // bubble crit
////      kems.attackCount[3][0] = 1; // bubblebeam // no pp
//			seq(kems); // cubone
//		}
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckDisableEffectMisses(), 50)}; // disable
//      kems.attackCount[1][1] = 1; // bite crit
//      kems.attackCount[0][1] = 1; // mega punch crit
//      seq(kems); // slowpoke
//    }
//		seq(new EflEndFightSegment(1)); // player defeated enemy
//		
//    seq(new EflWalkToSegment(37, 3)); // ladder
//    seq(new EflWalkToSegment(27, 30)); // engage trainer
//    seqMove(new EflOverworldInteract(8)); // talk to trainer
//
//    seq(new EflInitFightSegment(1)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckDisableEffectMisses(), 50)}; // disable
//      kems.attackCount[0][1] = 2; // mega punch crit
//      seq(kems); // slowpoke
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//    
//    save("rt1");
//    load("rt1");
//
//    seq(new EflWalkToSegment(14, 30)); // engage trainer
//    seq(new EflWalkToSegment(14, 29)); // engage trainer
//    seqMove(new EflOverworldInteract(6)); // talk to trainer
//
//    seq(new EflInitFightSegment(2)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[1][0] = 1; // bite
//      kems.attackCount[1][1] = 1; // bite crit
//      seq(kems); // oddish
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[0][1] = 1; // mega punch crit
//      kems.numExpGainers = 2; // lvlup to 29
//      seq(kems); // bulbasaur
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//    
//    seq(new EflEvolutionSegment(true));
//
//    seq(new EflWalkToSegment(27, 3)); // ladder
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
//      kems.attackCount[2][0] = 1; // bubble
//      seq(kems); // geodude
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(33)}; // tackle
//      kems.attackCount[2][0] = 1; // bubble
//      seq(kems); // geodude
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(33)}; // tackle
//      kems.attackCount[2][0] = 1; // bubble
//      seq(kems); // graveler
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//
//    seqMetric(new OutputParty());
//
//    save("rt2");
//    load("rt2");
//
//    seq(new EflWalkToSegment(3, 3)); // ladder
//    seq(new EflWalkToSegment(24, 24)); // engage trainer
//    seq(new EflWalkToSegment(23, 24)); // engage trainer
//    seqMove(new EflOverworldInteract(6)); // talk to trainer
//
//    seq(new EflInitFightSegment(1)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 45)}; // growl
//      kems.attackCount[1][1] = 1; // bite crit
//      seq(kems); // meowth
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[1][0] = 2; // bite
////      kems.attackCount[0][1] = 1; // mega punch crit // no pp
//      seq(kems); // oddish
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[1][1] = 1; // bite crit
//      kems.numExpGainers = 2; // level up to 30
//      seq(kems); // pidgey
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//
//    seq(new EflEvolutionSegment(true));
//
//    seq(new EflWalkToSegment(15, 33)); // leave rock tunnel
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
//      kems.attackCount[0][1] = 1; // mega punch crit
//      seq(kems); // growlithe
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[1][0] = 2; // bite
////      kems.attackCount[0][1] = 1; // mega punch crit // no PP
//      seq(kems); // vulpix
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//
//    save("rt3");
    load("rt3");

    seq(new EflWalkToSegment(13, 3)); // enter passage
    seq(new EflWalkToSegment(4, 4)); // enter passage

    seq(new EflUseBikeSegment(2, 0));
    {
      seq(new EflWalkToSegment(23, 5)); // walk passage
      seq(new EflWalkToSegment(22, 5)); // walk passage
      seqEflButton(Move.A);
      seq(new EflTextSegment()); // got elixer
    }
    seqUnbounded(new EflWalkToSegment(2, 5)); // walk passage
    seqUnbounded(new EflWalkToSegment(4, 8, false)); // exit passage
    seqUnbounded(new EflUseBikeSegment(0, 0));

    seqUnbounded(new EflWalkToSegment(8, 6)); // grass
    seq(new EflEncounterSegment(new CheckEncounterMetric(0x4d, 20), Move.UP)); // Meowth
    seq(new EflCatchMonSegment(2));

    seq(new EflWalkToSegment(-1, 3)); // enter celadon
	}
}
