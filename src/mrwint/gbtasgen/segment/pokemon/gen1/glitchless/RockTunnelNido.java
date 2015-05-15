package mrwint.gbtasgen.segment.pokemon.gen1.glitchless;

import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.metric.comparator.Comparator;
import mrwint.gbtasgen.metric.pokemon.CheckEncounterMetric;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckDisableEffectMisses;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.move.pokemon.gen1.OverworldInteract;
import mrwint.gbtasgen.move.pokemon.gen1.WalkStep;
import mrwint.gbtasgen.rom.RomInfo;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.BallSuccessSegment;
import mrwint.gbtasgen.segment.pokemon.CatchMonSegment;
import mrwint.gbtasgen.segment.pokemon.TextSegment;
import mrwint.gbtasgen.segment.pokemon.WalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.InitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.NewEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment.EnemyMoveDesc;
import mrwint.gbtasgen.segment.pokemon.fight.ThrashEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EncounterAndCatchSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.SwapWithSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.UseBikeSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.util.Util;
import mrwint.gbtasgen.util.pokemon.map.Gen1Map;

public class RockTunnelNido extends SeqSegment {

	@Override
	public void execute() {
//    seq(new WalkToSegment(15, 19)); // go to bush
//    {
//      seq(Segment.repress(Move.START));
//      seq(Segment.scrollA(1)); // mon
//      seq(Segment.scrollAF(3)); // paras
//      seq(Segment.repress(Move.A)); // cut
//      seqButton(Move.B); // hacked away (to text scroll)?
//    }
//    seq(new WalkToSegment(9, 13)); // enter fan club
//    seq(new WalkToSegment(2, 1)); // go to leader
//    seqMove(new OverworldInteract(5)); // talk to leader
//    seq(new SkipTextsSegment(6));
//    seq(new SkipTextsSegment(1, true)); // hear about mon
//    seq(new SkipTextsSegment(25));
//    {
//      seq(Segment.repress(Move.START));
//      seq(Segment.scrollA(1)); // items
//      seq(Segment.scrollFastAF(2)); // escape rope
//      seq(Segment.repress(Move.A)); // use
//    }
//    seqSkipInput(2);
//    seq(new WalkToSegment(13, 25)); // enter bike shop
//    seq(new WalkToSegment(6, 3, false)); // walk to counter // TODO: fix
//    seqMove(new OverworldInteract(1)); // talk to owner
//    seq(new SkipTextsSegment(5)); // get bike
//    seq(new WalkToSegment(3, 8, false)); // leave shop
//    {
//      seq(Segment.repress(Move.START));
//      seqButton(Move.A); // items
//      seq(new SwapWithSegment(9));
//      seq(Segment.scrollFastA(-9));
//      seq(new SkipTextsSegment(1)); // got on bike
//    }
//    seq(new WalkToSegment(19, 26)); // go to bush
//    seq(new WalkToSegment(19, 27)); // go to bush
//    {
//      seq(Segment.repress(Move.START));
//      seq(Segment.scrollA(-1)); // mon
//      seq(Segment.repress(Move.A)); // sandshrew
//      seq(Segment.repress(Move.A)); // cut
//      seqButton(Move.B); // hacked away (to text scroll)?
//    }
//    seq(new WalkToSegment(40, 17)); // leave cerulean
//    seq(new WalkToSegment(4, 8)); // go to bush
//    {
//      seq(Segment.repress(Move.START));
//      seqButton(Move.A); // mon
//      seq(Segment.repress(Move.A)); // sandshrew
//      seq(Segment.repress(Move.A)); // cut
//      seqButton(Move.B); // hacked away (to text scroll)?
//    }
//    seq(new WalkToSegment(13, 8)); // go to trainer
//    seq(new WalkToSegment(13, 9)); // go to trainer
//    seqMove(new OverworldInteract(1)); // talk to trainer
//		seq(new InitFightSegment(2)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[1][0] = 1; // thrash
//      kems.thrashNumAdditionalTurns = 3;
//			seq(kems); // oddish
//		}
//    seq(new ThrashEnemyMonSegment(48, false).withAdditionalTexts(1)); // bellsprout, L27
//    seq(new ThrashEnemyMonSegment(47, false)); // oddish
//    seq(new ThrashEnemyMonSegment(48, false)); // bellsprout
//    seqButton(Move.B);
//		seq(new EndFightSegment(1)); // player defeated enemy
//
//	  save("rt1");
	  load("rt1");

    seq(new WalkToSegment(12, 11, false)); // jump ledge
    seq(new WalkToSegment(40, 10)); // go to trainer
    seq(new WalkToSegment(40, 9)); // go to trainer
    seqMove(new OverworldInteract(9)); // talk to trainer
    seq(new InitFightSegment(1)); // start fight
    {
      KillEnemyMonSegment kems = new KillEnemyMonSegment();
      kems.attackCount[1][0] = 1; // thrash
      kems.thrashNumAdditionalTurns = 2;
      seq(kems); // caterpie
    }
    seq(new ThrashEnemyMonSegment(49, false)); // weedle
    seq(new ThrashEnemyMonSegment(57, false)); // venonat
    seqButton(Move.B);
    seq(new EndFightSegment(1)); // player defeated enemy
    seq(new WalkToSegment(51, 5, false)); // jump ledge
    seq(new WalkToSegment(60, 8)); // route 10
    seq(new WalkToSegment(8, 17)); // enter rock tunnel
    seq(new WalkToSegment(23, 6)); // engage trainer
    seq(new WalkToSegment(23, 7)); // engage trainer
    seqMove(new OverworldInteract(4)); // talk to trainer

		seq(new InitFightSegment(1)); // start fight
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.attackCount[0][0] = 1; // bubblebeam
			kems.numExpGainers = 2; // L28
			seq(kems); // cubone
		}
    seq(NewEnemyMonSegment.any()); // next mon
    {
      KillEnemyMonSegment kems = new KillEnemyMonSegment();
      kems.attackCount[1][1] = 1; // thrash crit
      kems.thrashNumAdditionalTurns = 0;
      seq(kems); // slowpoke
    }
    seqButton(Move.B);
		seq(new EndFightSegment(1)); // player defeated enemy
    seq(new WalkToSegment(37, 3)); // ladder
    seq(new WalkToSegment(27, 30)); // engage trainer
    seqMove(new OverworldInteract(8)); // talk to trainer

    seq(new InitFightSegment(1)); // start fight
    {
      KillEnemyMonSegment kems = new KillEnemyMonSegment();
      kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(93)}; // confusion
      kems.attackCount[2][1] = 2; // horn crit
      seq(kems); // slowpoke
    }
    seq(new EndFightSegment(1)); // player defeated enemy
    seq(new WalkToSegment(14, 30)); // engage trainer
    seq(new WalkToSegment(14, 29)); // engage trainer
    seqMove(new OverworldInteract(6)); // talk to trainer

    seq(new InitFightSegment(2)); // start fight
    {
      KillEnemyMonSegment kems = new KillEnemyMonSegment();
      kems.attackCount[1][0] = 1; // thrash
      kems.thrashNumAdditionalTurns = 1;
      seq(kems); // oddish
    }
    seq(new ThrashEnemyMonSegment(55, false)); // bulbasaur
    seqButton(Move.B);
    seq(new EndFightSegment(1)); // player defeated enemy
    seq(new WalkToSegment(27, 3)); // ladder

    save("rt2");
//    load("rt2");

    seq(new WalkToSegment(17, 11)); // ladder
    seq(new WalkToSegment(8, 10)); // engage trainer
    seq(new WalkToSegment(7, 10)); // engage trainer
    seqMove(new OverworldInteract(2)); // talk to trainer

    seq(new InitFightSegment(1)); // start fight
    {
      KillEnemyMonSegment kems = new KillEnemyMonSegment();
      kems.attackCount[0][0] = 1; // bubble
      kems.numExpGainers = 2; // L29
      seq(kems); // geodude
    }
    seq(NewEnemyMonSegment.any()); // next mon
    {
      KillEnemyMonSegment kems = new KillEnemyMonSegment();
      kems.attackCount[0][0] = 1; // bubble
      seq(kems); // geodude
    }
    seq(NewEnemyMonSegment.any()); // next mon
    {
      KillEnemyMonSegment kems = new KillEnemyMonSegment();
      kems.attackCount[0][0] = 1; // bubble
      seq(kems); // graveler
    }
    seq(new EndFightSegment(1)); // player defeated enemy
    seq(new WalkToSegment(3, 3)); // ladder
    seq(new WalkToSegment(24, 24)); // engage trainer
    seq(new WalkToSegment(23, 24)); // engage trainer
    seqMove(new OverworldInteract(6)); // talk to trainer

    seq(new InitFightSegment(1)); // start fight
    {
      KillEnemyMonSegment kems = new KillEnemyMonSegment();
      kems.attackCount[1][0] = 1; // thrash
      kems.thrashNumAdditionalTurns = 2;
      seq(kems); // meowth
    }
    seq(new ThrashEnemyMonSegment(51, false)); // oddish
    seq(new ThrashEnemyMonSegment(49, false)); // pidgey
    seqButton(Move.B);
    seq(new EndFightSegment(1)); // player defeated enemy
    seq(new WalkToSegment(15, 33)); // leave rock tunnel

    save("rt3");
//    load("rt3");

    seq(new WalkToSegment(15, 61, false)); // jump ledge
    seq(new WalkToSegment(9, 72)); // enter lavender
    seq(new WalkToSegment(-1, 8)); // leave lavender
    seq(new WalkToSegment(47, 13)); // engage trainer
    seqMove(new OverworldInteract(8)); // talk to trainer

    seq(new InitFightSegment(1)); // start fight
    {
      KillEnemyMonSegment kems = new KillEnemyMonSegment();
      kems.attackCount[1][1] = 1; // thrash crit
      kems.thrashNumAdditionalTurns = 1;
      seq(kems); // growlithe
    }
    seq(new ThrashEnemyMonSegment(56, false).withAdditionalTexts(1)); // vulpix; L30
    seqButton(Move.B);
    seq(new EndFightSegment(1)); // player defeated enemy
    seq(new WalkToSegment(13, 3)); // enter passage
    seq(new WalkToSegment(4, 4)); // enter passage
    seq(new UseBikeSegment(2, 0));
    seq(new WalkToSegment(2, 5)); // walk passage
    seq(new WalkToSegment(4, 8, false)); // exit passage
    seq(new UseBikeSegment(0, 0));
    seq(new WalkToSegment(-1, 3)); // enter celadon
	}
}
