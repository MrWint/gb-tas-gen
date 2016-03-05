package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import static mrwint.gbtasgen.move.Move.A;
import static mrwint.gbtasgen.move.Move.B;
import static mrwint.gbtasgen.move.Move.DOWN;
import static mrwint.gbtasgen.move.Move.START;
import static mrwint.gbtasgen.move.Move.UP;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.CHARMELEON;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.FARFETCHD;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.GROWLITHE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.MAGIKARP;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.MANKEY;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.MENU_ITEM;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.MEOWTH;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.ODDISH;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TM11;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TM24;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.PRESSED;
import mrwint.gbtasgen.metric.pokemon.CheckEncounterMetric;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckDisableEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputItems;
import mrwint.gbtasgen.metric.pokemon.gen1.OutputParty;
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
import mrwint.gbtasgen.segment.pokemon.gen1.common.Constants;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflBuyItemSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflCancelMoveLearnSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflEncounterSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSelectItemSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSelectMainMenuSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSelectMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSwapWithSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflUseBikeSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.util.EflUtil.PressMetric;

public class RockTunnelRed extends SeqSegment {

	@Override
	public void execute() {
    seq(new EflSelectMonSegment(FARFETCHD).fromOverworld().andFlyTo(2)); // Cerulean
    seqEflSkipInput(1);

    seqUnbounded(new EflWalkToSegment(13, 25)); // enter bike shop
    seqUnbounded(new EflWalkToSegment(6, 5)); // walk to counter
    seq(new EflWalkToSegment(6, 4)); // walk to counter
    seqMove(new EflOverworldInteract(1)); // talk to owner
    seq(new EflSkipTextsSegment(5)); // get bike
    seq(new EflWalkToSegment(3, 8, false)); // leave shop

    seq(new EflSelectMainMenuSegment(MENU_ITEM).fromOverworld()); // open item menu
    seqEflButton(DOWN, PRESSED); // TM34
    seq(new EflSwapWithSegment(5));
    seqEflButton(UP | A, PRESSED); // TM34
    seqEflButton(A, PRESSED); // use
    seq(new EflLearnTMSegment(MEOWTH, 3)); // replace Pay Day with Thunderbolt
    seqEflSkipInput(0);
    seq(new EflUseBikeSegment());

    seq(new EflWalkToSegment(19, 26)); // go to bush
    seq(new EflWalkToSegment(19, 27)); // go to bush

    save("tmp");
    load("tmp");

    seq(new EflSelectMonSegment(FARFETCHD).fromOverworld().andCut());

    seq(new EflWalkToSegment(40, 17)); // leave cerulean
    seq(new EflWalkToSegment(4, 8)); // go to bush
    seq(new EflSelectMonSegment(FARFETCHD).fromOverworld().andCut());
    seq(new EflWalkToSegment(13, 8)); // go to trainer
    seq(new EflWalkToSegment(13, 9)); // go to trainer
    seqMove(new EflOverworldInteract(1)); // talk to trainer
    seq(new EflInitFightSegment(2)); // start fight
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[0][0] = 2; // bite
      kems.numExpGainers = 2; // boosted
      seq(kems); // oddish
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[0][1] = 1; // bite crit
      kems.numExpGainers = 3; // boosted lvlup to 22
      seq(kems); // bellsprout
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[0][0] = 2; // bite
      kems.numExpGainers = 2; // boosted
      seq(kems); // oddish
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[0][1] = 1; // bite crit
      kems.numExpGainers = 2; // boosted
      seq(kems); // bellsprout
    }
    seq(new EflEndFightSegment(1)); // player defeated enemy

    save("rt1");
    load("rt1");

    seq(new EflWalkToSegment(12, 11, false)); // jump ledge
    seq(new EflWalkToSegment(40, 10)); // go to trainer
    seq(new EflWalkToSegment(40, 9)); // go to trainer
    seqMove(new EflOverworldInteract(9)); // talk to trainer
    seq(new EflInitFightSegment(1)); // start fight
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[0][1] = 1; // bite crit
      kems.numExpGainers = 3; // boosted lvlup to 23
      seq(kems); // caterpie
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[0][1] = 1; // bite crit
      kems.numExpGainers = 2; // boosted
      seq(kems); // weedle
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckDisableEffectMisses(), 50)}; // disable
      kems.attackCount[0][0] = 1; // bite
      kems.attackCount[0][1] = 1; // bite crit
//      kems.attackCount[1][1] = 1; // bubblebeam crit
      kems.numExpGainers = 2; // boosted
      seq(kems); // venonat
    }
    seq(new EflEndFightSegment(1)); // player defeated enemy

    save("rt2");
    load("rt2");

    seq(new EflWalkToSegment(51, 5, false)); // jump ledge
    seq(new EflWalkToSegment(60, 8)); // route 10

    seq(new EflWalkToSegment(8, 17)); // enter rock tunnel
    seq(new EflWalkToSegment(23, 6)); // engage trainer
    seq(new EflWalkToSegment(23, 7)); // engage trainer
    seqMove(new EflOverworldInteract(4)); // talk to trainer

		seq(new EflInitFightSegment(1)); // start fight
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(125)}; // bone club
      kems.attackCount[1][1] = 1; // Bubblebeam crit
      kems.numExpGainers = 2; // boosted
			seq(kems); // cubone
		}
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckDisableEffectMisses(), 50)}; // disable
      kems.attackCount[3][1] = 1; // Thundershock crit
      kems.numExpGainers = 3; // boosted lvlup to 24
      seq(kems); // slowpoke
    }
    seq(new EflCancelMoveLearnSegment()); // screech
		seq(new EflEndFightSegment(1)); // player defeated enemy

    save("rt3");
    load("rt3");

    seq(new EflWalkToSegment(37, 3)); // ladder
    seq(new EflWalkToSegment(27, 30)); // engage trainer
    seqMove(new EflOverworldInteract(8)); // talk to trainer

    seq(new EflInitFightSegment(1)); // start fight
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckDisableEffectMisses(), 50)}; // disable
      kems.attackCount[3][1] = 1; // Thundershock crit
      kems.numExpGainers = 2; // boosted
      seq(kems); // slowpoke
    }
    seq(new EflEndFightSegment(1)); // player defeated enemy

    save("rt4");
    load("rt4");

    seq(new EflWalkToSegment(14, 30)); // engage trainer
    seq(new EflWalkToSegment(14, 29)); // engage trainer
    seqMove(new EflOverworldInteract(6)); // talk to trainer

    seq(new EflInitFightSegment(2)); // start fight
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[0][0] = 1; // bite
      kems.attackCount[0][1] = 1; // bite crit
      kems.numExpGainers = 3; // boosted, lvlup 25
      seq(kems); // oddish
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[0][0] = 2; // bite
      kems.numExpGainers = 2; // boosted
      seq(kems); // bulbasaur
    }
    seq(new EflEndFightSegment(1)); // player defeated enemy

    save("rt5");
    load("rt5");

    seq(new EflWalkToSegment(27, 3)); // ladder

    seq(new EflWalkToSegment(17, 11)); // ladder
    seq(new EflWalkToSegment(8, 10)); // engage trainer
    seq(new EflWalkToSegment(7, 10)); // engage trainer
    seqMove(new EflOverworldInteract(2)); // talk to trainer

    seq(new EflInitFightSegment(1)); // start fight
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(33)}; // tackle
      kems.attackCount[1][0] = 1; // bubblebeam
      kems.numExpGainers = 2; // boosted
      seq(kems); // geodude
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(33)}; // tackle
      kems.attackCount[1][0] = 1; // bubblebeam
      kems.numExpGainers = 2; // boosted
      seq(kems); // geodude
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(33)}; // tackle
      kems.attackCount[1][0] = 1; // bubblebeam
      kems.numExpGainers = 3; // boosted, lvlup 26
      seq(kems); // graveler
    }
    seq(new EflEndFightSegment(1)); // player defeated enemy

    save("rt6");
    load("rt6");

    seq(new EflWalkToSegment(3, 3)); // ladder
    seq(new EflWalkToSegment(24, 24)); // engage trainer
    seq(new EflWalkToSegment(23, 24)); // engage trainer
    seqMove(new EflOverworldInteract(6)); // talk to trainer

    seq(new EflInitFightSegment(1)); // start fight
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 45)}; // growl
      kems.attackCount[0][1] = 1; // bite crit
      kems.numExpGainers = 2; // boosted
      seq(kems); // meowth
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[0][1] = 1; // bite crit
      kems.numExpGainers = 2; // boosted
      seq(kems); // oddish
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[0][1] = 1; // bite crit
      kems.numExpGainers = 2; // boosted
      seq(kems); // pidgey
    }
    seq(new EflEndFightSegment(1)); // player defeated enemy

    seq(new EflWalkToSegment(15, 33)); // leave rock tunnel

    save("rt7");
    load("rt7");

    seq(new EflWalkToSegment(15, 61, false)); // jump ledge
    seq(new EflWalkToSegment(9, 72)); // enter lavender
    {
      seq(new EflWalkToSegment(15, 13)); // enter mart
      seq(new EflWalkToSegment(3, 5)); // shopkeep
      seq(new EflWalkToSegment(2, 5)); // shopkeep
      seqMove(new EflOverworldInteract(1));
      {
        seq(new EflSkipTextsSegment(1, true)); // buy
        seq(new EflTextSegment(Move.B));
        seq(new EflBuyItemSegment(3, 8, true)); // Escape Rope x8
        seqEflButton(Move.B); // cancel
        seq(new EflSkipTextsSegment(2)); // cancel + bye
      }
      seq(new EflWalkToSegment(3, 6)); // leave mart
      seq(new EflWalkToSegment(3, 8, false)); // leave mart
      seq(new EflUseBikeSegment().fromOverworld());
    }
    seq(new EflWalkToSegment(-1, 8)); // leave lavender
    seq(new EflWalkToSegment(47, 13)); // engage trainer

    seq(new EflSelectMonSegment(MEOWTH).fromOverworld().andSwitchWith(CHARMELEON));
    seqEflButton(B);
    seqEflButton(START);

    seqMove(new EflOverworldInteract(8)); // talk to trainer
    seq(new EflInitFightSegment(1)); // start fight
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[3][1] = 1; // mega punch crit
      kems.numExpGainers = 2; // boosted
      seq(kems); // growlithe
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[3][0] = 1; // mega punch
      kems.numExpGainers = 2; // boosted
      seq(kems); // vulpix
    }
    seq(new EflEndFightSegment(1)); // player defeated enemy

    save("rt8");
    load("rt8");

    seqUnbounded(new EflWalkToSegment(13, 3)); // enter passage
    seqUnbounded(new EflWalkToSegment(4, 4)); // enter passage

    seqUnbounded(new EflUseBikeSegment().fromOverworld());
    {
      seqUnbounded(new EflWalkToSegment(23, 5)); // walk passage
      seqUnbounded(new EflWalkToSegment(22, 5)); // walk passage
      seqEflButtonUnboundedNoDelay(Move.A);
      seqUnbounded(new EflTextSegment()); // got elixer
    }
    {
      seqUnbounded(new EflWalkToSegment(12, 4)); // walk passage
      seqUnbounded(new EflWalkToSegment(12, 3)); // walk passage
      seqEflButtonUnboundedNoDelay(Move.A);
      seqUnbounded(new EflTextSegment()); // got nugget
    }
    seqUnbounded(new EflWalkToSegment(2, 5)); // walk passage
    seqUnbounded(new EflWalkToSegment(4, 8, false)); // exit passage
    seqUnbounded(new EflUseBikeSegment().fromOverworld());
    save("tmp1");
//    load("tmp1");

    seqUnbounded(new EflWalkToSegment(8, 6)); // grass
    seq(new EflEncounterSegment(GROWLITHE, UP));
    save("tmp2");
//    load("tmp2");
    seq(new EflCatchMonSegment().withBufferSize(0).withExtraSkips(7));

//    seqUnbounded(new EflWalkToSegment(15, 2)); // grass
    seqUnbounded(new EflWalkToSegment(8, 3)); // grass
    seq(new EflEncounterSegment(new CheckEncounterMetric(ODDISH, 22), UP));
    save("tmp3");
//    load("tmp3");
    seq(new EflCatchMonSegment().withBufferSize(0).withExtraSkips(0));

    seqUnbounded(new EflWalkToSegment(8, 4)); // grass
    seq(new EflEncounterSegment(new CheckEncounterMetric(MANKEY, 20).withAtkDV(15), DOWN));
    seq(new EflCatchMonSegment().withName("M"));

    seq(new EflWalkToSegment(-1, 3)); // enter celadon
	}
}
