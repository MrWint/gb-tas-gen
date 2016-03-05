package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import static mrwint.gbtasgen.move.Move.A;
import static mrwint.gbtasgen.move.Move.DOWN;
import static mrwint.gbtasgen.move.Move.LEFT;
import static mrwint.gbtasgen.move.Move.START;
import static mrwint.gbtasgen.move.Move.UP;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.BELLSPROUT;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.BONE_CLUB;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DISABLE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.GROWL;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.MEOWTH;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TACKLE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.VULPIX;
import mrwint.gbtasgen.metric.pokemon.CheckEncounterMetric;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckDisableEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputParty;
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
import mrwint.gbtasgen.segment.pokemon.gen1.common.Constants;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflCancelMoveLearnSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflEncounterSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflUseBikeSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;

public class RockTunnelBlue extends SeqSegment {

	@Override
	public void execute() {

//    seqEflButton(A); // continue game
//    seqEflButton(START);
//    seqEflButton(A);
//    seqEflButton(START);
//    seqEflButton(A);
//
//    seq(new EflWalkToSegment(4, 6)); // leave center
//    seq(new EflWalkToSegment(4, 8, false)); // leave center
//    seq(new EflUseBikeSegment().fromOverworld());
//
//    seq(new EflWalkToSegment(8, 17)); // enter rock tunnel
//    seq(new EflWalkToSegment(23, 6)); // engage trainer
//    seq(new EflWalkToSegment(23, 7)); // engage trainer
//    seqMove(new EflOverworldInteract(4)); // talk to trainer
//
//		seq(new EflInitFightSegment(1)); // start fight
//		{
//		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(BONE_CLUB)};
//      kems.attackCount[3][0] = 1; // bubblebeam
//			seq(kems); // cubone
//		}
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckDisableEffectMisses(), DISABLE)};
////      kems.attackCount[1][1] = 1; // bite crit
//      kems.attackCount[0][1] = 1; // mega punch crit
//      kems.attackCount[3][1] = 1; // bubblebeam crit
//      kems.numExpGainers = 2; // Squirtle, lvlup to 28
//      seq(kems); // slowpoke
//    }
//    seq(new EflCancelMoveLearnSegment()); // Withdraw
//		seq(new EflEndFightSegment(1)); // player defeated enemy
//
//    save("rt1");
//    load("rt1");
//
//    seq(new EflEvolutionSegment(true));
//
//    seq(new EflWalkToSegment(37, 3)); // ladder
//    seq(new EflWalkToSegment(27, 30)); // engage trainer
//    seqMove(new EflOverworldInteract(8)); // talk to trainer
//
//    seq(new EflInitFightSegment(1)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckDisableEffectMisses(), DISABLE)};
//      kems.attackCount[0][1] = 2; // mega punch crit
//      seq(kems); // slowpoke
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//
//    save("rt2");
//    load("rt2");
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
//      seq(kems); // bulbasaur
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//
//    save("rt3");
//    load("rt3");
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
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(TACKLE)};
//      kems.attackCount[2][0] = 1; // bubble
//      seq(kems); // geodude
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(TACKLE)};
//      kems.attackCount[2][0] = 1; // bubble
//      kems.numExpGainers = 2; // lvlup to 29
//      seq(kems); // geodude
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(TACKLE)};
//      kems.attackCount[2][0] = 1; // bubble
//      seq(kems); // graveler
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//
//    save("rt4");
//    load("rt4");
//
//    seq(new EflEvolutionSegment(true));
//
//    seq(new EflWalkToSegment(3, 3)); // ladder
//    seq(new EflWalkToSegment(24, 24)); // engage trainer
//    seq(new EflWalkToSegment(23, 24)); // engage trainer
//    seqMove(new EflOverworldInteract(6)); // talk to trainer
//
//    seq(new EflInitFightSegment(1)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), GROWL)};
//      kems.attackCount[1][1] = 1; // bite crit
//      seq(kems); // meowth
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[0][1] = 1; // mega punch crit
//      seq(kems); // oddish
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[1][1] = 1; // bite crit
////      kems.numExpGainers = 2; // level up to 30
//      seq(kems); // pidgey
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//
//    save("rt5");
//    load("rt5");
////
////    seq(new EflEvolutionSegment(true));
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
//      kems.attackCount[3][0] = 1; // bubblebeam
//      seq(kems); // growlithe
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[3][0] = 1; // bubblebeam
//      seq(kems); // vulpix
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//
//    save("rt6");
//    load("rt6");
//
//    seqUnbounded(new EflWalkToSegment(13, 3)); // enter passage
//    seqUnbounded(new EflWalkToSegment(4, 4)); // enter passage
//
//    seqUnbounded(new EflUseBikeSegment().fromOverworld());
//    {
//      seqUnbounded(new EflWalkToSegment(23, 5)); // walk passage
//      seqUnbounded(new EflWalkToSegment(22, 5)); // walk passage
//      seqEflButtonUnboundedNoDelay(A);
//      seqUnbounded(new EflTextSegment()); // got elixer
//    }
//    {
//      seqUnbounded(new EflWalkToSegment(12, 4)); // walk passage
//      seqUnbounded(new EflWalkToSegment(12, 3)); // walk passage
//      seqEflButtonUnboundedNoDelay(A);
//      seqUnbounded(new EflTextSegment()); // got nugget
//    }
//    seqUnbounded(new EflWalkToSegment(2, 5)); // walk passage
//    seqUnbounded(new EflWalkToSegment(4, 8, false)); // exit passage
//    seqUnbounded(new EflUseBikeSegment().fromOverworld());
//
//    save("tmpa");
//    load("tmpa");
//
//    seqUnbounded(new EflWalkToSegment(8, 6)); // grass
//    seq(new EflEncounterSegment(new CheckEncounterMetric(BELLSPROUT, 22), UP));
//    save("tmp1");
//    load("tmp1");
//    seq(new EflCatchMonSegment().withBufferSize(0).withExtraSkips(2));
//
////    seqUnbounded(new EflWalkToSegment(11, 6)); // grass
////    seqUnbounded(new EflWalkToSegment(11, 4)); // grass
////    seqUnbounded(new EflWalkToSegment(11, 3)); // grass
//    seqUnbounded(new EflWalkToSegment(11, 4)); // grass
//    seq(new EflEncounterSegment(new CheckEncounterMetric(MEOWTH, 20).withAtkDV(13,14,15).withSpcDV(12,13,14,15), DOWN));
////    seq(new EflEncounterSegment(VULPIX, LEFT));
//    save("tmp2a");
    load("tmp2a");

    seq(new EflCatchMonSegment().withBufferSize(0).withName("O").withExtraSkips(14));

    seqUnbounded(new EflWalkToSegment(9, 5)); // grass
//    seqUnbounded(new EflWalkToSegment(9, 3)); // grass
//    seqUnbounded(new EflWalkToSegment(9, 4)); // grass
//    seq(new EflEncounterSegment(new CheckEncounterMetric(MEOWTH, 20).withAtkDV(13,14,15).withSpcDV(12,13,14,15), DOWN));
    seq(new EflEncounterSegment(VULPIX, LEFT));
    seq(new EflCatchMonSegment());

    seq(new EflWalkToSegment(-1, 3)); // enter celadon
	}
}
