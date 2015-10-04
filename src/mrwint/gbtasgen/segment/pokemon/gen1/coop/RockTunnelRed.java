package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import static mrwint.gbtasgen.move.Move.A;
import static mrwint.gbtasgen.move.Move.B;
import static mrwint.gbtasgen.move.Move.START;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.PRESSED;
import mrwint.gbtasgen.metric.pokemon.CheckEncounterMetric;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckDisableEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputItems;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.pokemon.gen1.EflOverworldInteract;
import mrwint.gbtasgen.segment.pokemon.EflCatchMonSegment;
import mrwint.gbtasgen.segment.pokemon.EflLearnTMSegment;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.pokemon.EflWalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflEndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflInitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment.EflEnemyMoveDesc;
import mrwint.gbtasgen.segment.pokemon.fight.EflNewEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflCancelMoveLearnSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflEncounterSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSwapWithSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflUseBikeSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.util.EflUtil.PressMetric;

public class RockTunnelRed extends SeqSegment {

	@Override
	public void execute() {
//    {
//      seqEflButton(START, PRESSED);
//      seqEflScrollA(2); // items
//      seqEflScrollFastAF(8+1); // TM11
//      seqEflButton(A, PRESSED); // use
//      seq(new EflLearnTMSegment(0, 0)); // replace Scratch with Bubblebeam
//      seqEflScrollFastAF(5+1); // TM24
//      seqEflButton(A, PRESSED); // use
//      seq(new EflLearnTMSegment(0, 1)); // replace Growl with Thunderbolt
//      seqEflButton(B); // cancel
//      seqEflScrollA(-1); // mon
//      seqEflScrollAF(3); // dux
//      seqEflSkipInput(1);
//      seqEflScrollAF(1); // fly
//      seqEflSkipInput(1);
//      seqEflScrollA(2); // cerulean
//      seqEflSkipInput(1);
//    }
//
//    seq(new EflWalkToSegment(13, 25)); // enter bike shop
//    seq(new EflWalkToSegment(6, 3, false)); // walk to counter // TODO: fix
//    seqMove(new EflOverworldInteract(1)); // talk to owner
//    seq(new EflSkipTextsSegment(5)); // get bike
//    seq(new EflWalkToSegment(3, 8, false)); // leave shop
//    {
//      seqEflButton(START, PRESSED);
//      seqEflScrollA(1); // items
//      seqMetric(new OutputItems());
//      seqEflButton(Move.DOWN);
//      seq(new EflSwapWithSegment(5));
//      seqEflScrollFastA(-5);
//      seq(new EflSkipTextsSegment(1)); // got on bike
//    }
//    seq(new EflWalkToSegment(19, 26)); // go to bush
//    seq(new EflWalkToSegment(19, 27)); // go to bush
//
//    save("tmp");
//    load("tmp");
//
//    {
//      seqEflButton(START, PRESSED);
//      seqEflScrollA(-1); // mon
//      seqEflButton(A, PRESSED); // dux
//      seqEflSkipInput(1);
//      seqEflButton(A); // cut
//      seqEflButton(B); // hacked away (to text scroll)?
//    }
//
//    {
//      seq(new EflWalkToSegment(19, 33, false)); // ledge
//      seq(new EflWalkToSegment(19, 36)); // R5
//      seq(new EflWalkToSegment(9, 3, false)); // ledge
//      seq(new EflWalkToSegment(9, 7, false)); // ledge
//      seq(new EflWalkToSegment(9, 11, false)); // ledge
//      seq(new EflWalkToSegment(9, 15, false)); // ledge
//      seq(new EflWalkToSegment(10, 21)); // Pension
//      seq(new EflWalkToSegment(2, 4)); // man
//      seqMove(new EflOverworldInteract(1)); // man
//      seq(new EflSkipTextsSegment(2)); // raise one
//      seq(new EflSkipTextsSegment(1, true)); // of your mon
//      seq(new EflSkipTextsSegment(1)); // which one
//      seqEflScrollAF(-1); // Magikarp
//      seq(new EflSkipTextsSegment(3)); // look after magikarp for a while, come back
//      seq(new EflWalkToSegment(2, 8, false)); // leave house
//      seq(new EflUseBikeSegment(1, 0));
//      seq(new EflWalkToSegment(10, 23, false)); // ledge
//      seq(new EflWalkToSegment(15, -1)); // cerulean
//    }
//
//
//    seq(new EflWalkToSegment(40, 17)); // leave cerulean
//    seq(new EflWalkToSegment(4, 8)); // go to bush
//    {
//      seqEflButton(Move.START, PressMetric.PRESSED);
//      seqEflScrollA(-1); // mon
//      seqEflScrollAF(-2); // dux
////      seqEflSkipInput(1);
////      seqEflButton(Move.A); // dux
//      seqEflSkipInput(1);
//      seqEflButton(Move.A); // cut
//      seqEflButton(Move.B); // hacked away (to text scroll)?
//    }
//    seq(new EflWalkToSegment(13, 8)); // go to trainer
//    seq(new EflWalkToSegment(13, 9)); // go to trainer
//    seqMove(new EflOverworldInteract(1)); // talk to trainer
//    seq(new EflInitFightSegment(2)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[2][0] = 2; // bite
//      kems.numExpGainers = 2; // boosted
//      seq(kems); // oddish
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[2][1] = 1; // bite crit
//      kems.numExpGainers = 3; // boosted lvlup to 22
//      seq(kems); // bellsprout
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[2][0] = 2; // bite
//      kems.numExpGainers = 2; // boosted
//      seq(kems); // oddish
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[2][1] = 1; // bite crit
//      kems.numExpGainers = 2; // boosted
//      seq(kems); // bellsprout
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//
//    save("rt1");
//    load("rt1");
//
//    seq(new EflWalkToSegment(12, 11, false)); // jump ledge
//    seq(new EflWalkToSegment(40, 10)); // go to trainer
//    seq(new EflWalkToSegment(40, 9)); // go to trainer
//    seqMove(new EflOverworldInteract(9)); // talk to trainer
//    seq(new EflInitFightSegment(1)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[2][1] = 1; // bite crit
//      kems.numExpGainers = 3; // boosted lvlup to 23
//      seq(kems); // caterpie
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[2][1] = 1; // bite crit
//      kems.numExpGainers = 2; // boosted
//      seq(kems); // weedle
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckDisableEffectMisses(), 50)}; // disable
//      kems.attackCount[2][0] = 1; // bite
//      kems.attackCount[1][1] = 1; // bubblebeam crit
//      kems.numExpGainers = 2; // boosted
//      seq(kems); // venonat
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//    seq(new EflWalkToSegment(51, 5, false)); // jump ledge
//    seq(new EflWalkToSegment(60, 8)); // route 10
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
//      kems.attackCount[1][1] = 1; // Bubblebeam
//      kems.numExpGainers = 2; // boosted
//			seq(kems); // cubone
//		}
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckDisableEffectMisses(), 50)}; // disable
//      kems.attackCount[0][1] = 1; // Thundershock
//      kems.numExpGainers = 3; // boosted lvlup to 24
//      seq(kems); // slowpoke
//    }
//    seq(new EflCancelMoveLearnSegment()); // screech
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
//      kems.attackCount[0][1] = 1; // Thundershock
//      kems.numExpGainers = 2; // boosted
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
//      kems.attackCount[2][0] = 1; // bite
//      kems.attackCount[2][1] = 1; // bite crit
//      kems.numExpGainers = 3; // boosted, lvlup 25
//      seq(kems); // oddish
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[2][0] = 2; // bite
//      kems.numExpGainers = 2; // boosted
//      seq(kems); // bulbasaur
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
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
//      kems.attackCount[1][0] = 1; // bubblebeam
//      kems.numExpGainers = 2; // boosted
//      seq(kems); // geodude
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(33)}; // tackle
//      kems.attackCount[1][0] = 1; // bubblebeam
//      kems.numExpGainers = 2; // boosted
//      seq(kems); // geodude
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(33)}; // tackle
//      kems.attackCount[1][0] = 1; // bubblebeam
//      kems.numExpGainers = 3; // boosted, lvlup 26
//      seq(kems); // graveler
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//
//    save("rt3");
//    load("rt3");
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
//      kems.attackCount[2][1] = 1; // bite crit
//      kems.numExpGainers = 2; // boosted
//      seq(kems); // meowth
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[2][1] = 1; // bite crit
//      kems.numExpGainers = 2; // boosted
//      seq(kems); // oddish
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[2][1] = 1; // bite crit
//      kems.numExpGainers = 2; // boosted
//      seq(kems); // pidgey
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//
//    seq(new EflWalkToSegment(15, 33)); // leave rock tunnel
//
//    seq(new EflWalkToSegment(15, 61, false)); // jump ledge
//    seq(new EflWalkToSegment(9, 72)); // enter lavender
//    seq(new EflWalkToSegment(-1, 8)); // leave lavender
//    seq(new EflWalkToSegment(47, 13)); // engage trainer
//
//    save("rt4");
//    load("rt4");
//
//    {
//      seqEflButton(START, PRESSED);
//      seqEflScrollA(1); // mon
//      seqEflButton(A, PRESSED); // meowth
//      seqEflSkipInput(1);
//      seqEflScrollAF(1); // switch
//      seqEflScrollAF(-2); // charmeleon
//      seqEflButton(B);
//      seqEflButton(START);
//    }
//
//    seqMove(new EflOverworldInteract(8)); // talk to trainer
//    seq(new EflInitFightSegment(1)); // start fight
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[1][1] = 1; // slash crit
//      kems.numExpGainers = 2; // boosted
//      seq(kems); // growlithe
//    }
//    seq(EflNewEnemyMonSegment.any()); // next mon
//    {
//      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
//      kems.attackCount[0][1] = 1; // cut crit
//      kems.numExpGainers = 2; // boosted
//      seq(kems); // vulpix
//    }
//    seq(new EflEndFightSegment(1)); // player defeated enemy
//
//    save("rt5");
//    load("rt5");
//
//    seq(new EflWalkToSegment(13, 3)); // enter passage
//    seq(new EflWalkToSegment(4, 4)); // enter passage
//
//    seq(new EflUseBikeSegment(2, 1));
//    {
//      seq(new EflWalkToSegment(23, 5)); // walk passage
//      seq(new EflWalkToSegment(22, 5)); // walk passage
//      seqEflButton(Move.A);
//      seqUnbounded(new EflTextSegment()); // got elixer
//    }
//    {
//      seqUnbounded(new EflWalkToSegment(12, 4)); // walk passage
//      seqUnbounded(new EflWalkToSegment(12, 3)); // walk passage
//      seqEflButtonUnboundedNoDelay(Move.A);
//      seqUnbounded(new EflTextSegment()); // got nugget
//    }
//    seqUnbounded(new EflWalkToSegment(2, 5)); // walk passage
//    seqUnbounded(new EflWalkToSegment(4, 8, false)); // exit passage
//    seqUnbounded(new EflUseBikeSegment(0, 0));
//
//    seqUnbounded(new EflWalkToSegment(8, 6)); // grass
//    seq(new EflEncounterSegment(0x21, Move.UP)); // Growlithe
//    save("tmp");
////    load("tmp");
//    seq(new EflCatchMonSegment(0).withBufferSize(0).withExtraSkips(30));
//
////    seqUnbounded(new EflWalkToSegment(15, 2)); // grass
//    seqUnbounded(new EflWalkToSegment(8, 3)); // grass
//    seq(new EflEncounterSegment(new CheckEncounterMetric(0xb9, 22), Move.UP)); // Oddish
//    save("tmp2");
    load("tmp2");
    seq(new EflCatchMonSegment(0).withBufferSize(0).withExtraSkips(20));

    seqUnbounded(new EflWalkToSegment(8, 4)); // grass
    seq(new EflEncounterSegment(new CheckEncounterMetric(0x39, 20).withAtkDV(15), Move.DOWN)); // Mankey
    seq(new EflCatchMonSegment(0));

    seq(new EflWalkToSegment(-1, 3)); // enter celadon
	}
}
