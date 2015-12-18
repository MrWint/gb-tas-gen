package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import static mrwint.gbtasgen.move.Move.A;
import static mrwint.gbtasgen.move.Move.B;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.CHARMANDER;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.DISABLE;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.RARE_CANDY;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.TACKLE;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckDisableEffectMisses;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.pokemon.gen1.EflOverworldInteract;
import mrwint.gbtasgen.segment.pokemon.EflEvolutionSegment;
import mrwint.gbtasgen.segment.pokemon.EflLearnTMSegment;
import mrwint.gbtasgen.segment.pokemon.EflOverrideMoveSegment;
import mrwint.gbtasgen.segment.pokemon.EflWalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflEndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflInitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment.EflEnemyMoveDesc;
import mrwint.gbtasgen.segment.pokemon.fight.EflNewEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSelectItemSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSelectMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSwapWithSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflUseBikeSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.util.EflUtil.PressMetric;

public class RockTunnelDummyRed extends SeqSegment {

	@Override
	public void execute() {
    seqUnbounded(new EflWalkToSegment(13, 25)); // enter bike shop
    seqUnbounded(new EflWalkToSegment(6, 5)); // walk to counter // TODO: fix
    seq(new EflWalkToSegment(6, 4)); // walk to counter // TODO: fix
    seqMove(new EflOverworldInteract(1)); // talk to owner
    seq(new EflSkipTextsSegment(5)); // get bike
    seq(new EflWalkToSegment(3, 8, false)); // leave shop
    {
      seqEflButton(Move.START, PressMetric.PRESSED);
      seqEflScrollA(2); // items
      seqEflScrollFastAF(8); // HM01
      seqEflSkipInput(1);
      seqEflButton(Move.A); // use
      seq(new EflLearnTMSegment(CHARMANDER, 0)); // scratch -> cut
      seqEflScrollFast(1);
      seq(new EflSwapWithSegment(-8));
      seqEflButton(Move.A); // use bike
      seq(new EflSkipTextsSegment(1)); // got on bike
    }
    seq(new EflWalkToSegment(19, 26)); // go to bush
    seq(new EflWalkToSegment(19, 27)); // go to bush
    seq(new EflSelectMonSegment(CHARMANDER).fromOverworld().andCut());
    seq(new EflWalkToSegment(40, 17)); // leave cerulean
    seq(new EflWalkToSegment(4, 8)); // go to bush
    seq(new EflSelectMonSegment(CHARMANDER).fromOverworld().andCut());
    seq(new EflWalkToSegment(13, 8)); // go to trainer
    seq(new EflWalkToSegment(13, 9)); // go to trainer
    seqMove(new EflOverworldInteract(1)); // talk to trainer
		seq(new EflInitFightSegment(2)); // start fight
		{
		  EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[2][1] = 1; // ember crit
			seq(kems); // oddish
		}
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[2][1] = 1; // ember crit
//      kems.attackCount[0][1] = 1; // cut crit
      seq(kems); // bellsprout
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[2][1] = 1; // ember crit
      seq(kems); // oddish
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[2][1] = 1; // ember crit
//      kems.attackCount[0][1] = 1; // cut crit
      kems.numExpGainers = 2; // Charmander, level up to 27
      seq(kems); // bellsprout
    }
		seq(new EflEndFightSegment(1)); // player defeated enemy

		seq(new EflEvolutionSegment(true));

		save("rtd1");
	  load("rtd1");

    seq(new EflSelectItemSegment(RARE_CANDY).fromOverworld().andUse());
    seq(new EflSelectMonSegment(CHARMANDER));
    seqEflButton(B); // grew to 28
    seqEflButton(A); // close stats
    seq(new EflEvolutionSegment(true));
    seq(new EflSelectItemSegment(RARE_CANDY).andUse());
    seq(new EflSelectMonSegment(CHARMANDER));
    seqEflButton(B); // grew to 29
    seqEflButton(A); // close stats
    seq(new EflEvolutionSegment(true, true));
    seqEflButton(Move.B); // cancel
    seqEflButton(Move.START); // cancel

    seq(new EflWalkToSegment(12, 11, false)); // jump ledge
    seq(new EflWalkToSegment(40, 10)); // go to trainer
    seq(new EflWalkToSegment(40, 9)); // go to trainer
    seqMove(new EflOverworldInteract(9)); // talk to trainer
    seq(new EflInitFightSegment(1)); // start fight
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[2][0] = 1; // ember
//      kems.attackCount[3][0] = 1; // mega punch
      seq(kems); // caterpie
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[2][0] = 1; // ember
//      kems.attackCount[3][0] = 1; // mega punch
      seq(kems); // weedle
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[2][0] = 1; // ember
      seq(kems); // venonat
    }
    seq(new EflEndFightSegment(1)); // player defeated enemy
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
      kems.attackCount[2][0] = 1; // ember
//      kems.attackCount[3][0] = 1; // mega punch
      kems.attackCount[2][1] = 1; // ember crit
			seq(kems); // cubone
		}
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckDisableEffectMisses(), DISABLE)};
      kems.attackCount[3][0] = 1; // mega punch
      kems.attackCount[3][1] = 1; // mega punch crit
      seq(kems); // slowpoke
    }
		seq(new EflEndFightSegment(1)); // player defeated enemy
    seq(new EflWalkToSegment(37, 3)); // ladder
    seq(new EflWalkToSegment(27, 30)); // engage trainer
    seqMove(new EflOverworldInteract(8)); // talk to trainer

    save("tmp");
    load("tmp");

    seq(new EflInitFightSegment(1)); // start fight
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(new CheckDisableEffectMisses(), DISABLE)};
      kems.attackCount[3][1] = 2; // mega punch crit
      seq(kems); // slowpoke
    }
    seq(new EflEndFightSegment(1)); // player defeated enemy

    seq(new EflWalkToSegment(14, 30)); // engage trainer
    seq(new EflWalkToSegment(14, 29)); // engage trainer
    seqMove(new EflOverworldInteract(6)); // talk to trainer

    seq(new EflInitFightSegment(2)); // start fight
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[2][1] = 1; // ember crit
      kems.numExpGainers = 2; // Charmander, level up to 30
      seq(kems); // oddish
    }
    seq(new EflOverrideMoveSegment(1)); // Rage -> Slash
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[2][1] = 1; // ember crit
//      kems.attackCount[1][1] = 1; // slash crit
      seq(kems); // bulbasaur
    }
    seq(new EflEndFightSegment(1)); // player defeated enemy

    seq(new EflEvolutionSegment(true));

    seq(new EflWalkToSegment(27, 3)); // ladder

    save("rtd2");
    load("rtd2");

    seq(new EflWalkToSegment(17, 11)); // ladder
    seq(new EflWalkToSegment(8, 10)); // engage trainer
    seq(new EflWalkToSegment(7, 10)); // engage trainer
    seqMove(new EflOverworldInteract(2)); // talk to trainer

    seq(new EflInitFightSegment(1)); // start fight
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(TACKLE)};
      kems.attackCount[2][0] = 1; // ember
//      kems.attackCount[0][1] = 1; // cut crit
      kems.attackCount[2][1] = 1; // ember crit
      seq(kems); // geodude
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(TACKLE)};
      kems.attackCount[2][0] = 1; // ember
      kems.attackCount[2][1] = 1; // ember crit
      seq(kems); // geodude
    }

    save("tmp");
    load("tmp");

    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.enemyMoveDesc = new EflEnemyMoveDesc[]{EflEnemyMoveDesc.missWith(TACKLE)};
      kems.attackCount[2][0] = 2; // ember
      kems.attackCount[2][1] = 1; // ember crit
      seq(kems); // graveler
    }
    seq(new EflEndFightSegment(1)); // player defeated enemy

    save("tmp2");
    load("tmp2");

    seq(new EflWalkToSegment(3, 3)); // ladder
    seq(new EflWalkToSegment(24, 24)); // engage trainer
    seq(new EflWalkToSegment(23, 24)); // engage trainer
    seqMove(new EflOverworldInteract(6)); // talk to trainer

    seq(new EflInitFightSegment(1)); // start fight
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[2][1] = 1; // ember crit
//      kems.attackCount[0][1] = 1; // cut crit
      seq(kems); // meowth
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[2][1] = 1; // ember crit
//      kems.attackCount[1][1] = 1; // slash crit
      kems.numExpGainers = 2; // Charmander, level up to 31
      seq(kems); // oddish
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[2][1] = 1; // ember crit
//      kems.attackCount[0][1] = 1; // cut crit
      seq(kems); // pidgey
    }
    seq(new EflEndFightSegment(1)); // player defeated enemy

    seq(new EflEvolutionSegment(true));

    seq(new EflWalkToSegment(15, 33)); // leave rock tunnel

    save("rtd3");
    load("rtd3");

    seq(new EflWalkToSegment(15, 61, false)); // jump ledge
    seq(new EflWalkToSegment(9, 72)); // enter lavender
    seq(new EflWalkToSegment(-1, 8)); // leave lavender
    seq(new EflWalkToSegment(47, 13)); // engage trainer
    seqMove(new EflOverworldInteract(8)); // talk to trainer

    seq(new EflInitFightSegment(1)); // start fight
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[3][1] = 1; // mega punch crit
      seq(kems); // growlithe
    }
    seq(EflNewEnemyMonSegment.any()); // next mon
    {
      EflKillEnemyMonSegment kems = new EflKillEnemyMonSegment();
      kems.attackCount[1][1] = 1; // slash crit
      seq(kems); // vulpix
    }
    seq(new EflEndFightSegment(1)); // player defeated enemy
    seq(new EflWalkToSegment(13, 3)); // enter passage
    seq(new EflWalkToSegment(4, 4)); // enter passage

    seq(new EflUseBikeSegment().fromOverworld());
    seq(new EflWalkToSegment(2, 5)); // walk passage
    seq(new EflWalkToSegment(4, 8, false)); // exit passage
    seq(new EflUseBikeSegment().fromOverworld());
    seq(new EflWalkToSegment(-1, 3)); // enter celadon
	}
}
