package mrwint.gbtasgen.segment.gen1.noww;

import mrwint.gbtasgen.metric.CheckEncounterMetric;
import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.metric.comparator.Equal;
import mrwint.gbtasgen.metric.comparator.GreaterEqual;
import mrwint.gbtasgen.metric.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.metric.gen1.CheckPoisonEffectMisses;
import mrwint.gbtasgen.metric.gen1.CheckSleepEffectMisses;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.SelectMoveInList;
import mrwint.gbtasgen.move.Wait;
import mrwint.gbtasgen.move.WriteMemory;
import mrwint.gbtasgen.move.gen1.OverworldInteract;
import mrwint.gbtasgen.segment.BallSuccessSegment;
import mrwint.gbtasgen.segment.CatchMonSegment;
import mrwint.gbtasgen.segment.PokecenterSegment;
import mrwint.gbtasgen.segment.ResetAndContinueSegment;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.TextSegment;
import mrwint.gbtasgen.segment.WalkToSegment;
import mrwint.gbtasgen.segment.fight.EndFightSegment;
import mrwint.gbtasgen.segment.fight.InitFightSegment;
import mrwint.gbtasgen.segment.fight.KillEnemyMonSegment;
import mrwint.gbtasgen.segment.fight.KillEnemyMonSegment.CheckMoveDamage;
import mrwint.gbtasgen.segment.fight.KillEnemyMonSegment.CheckMoveOrderMetric;
import mrwint.gbtasgen.segment.fight.KillEnemyMonSegment.EnemyMoveDesc;
import mrwint.gbtasgen.segment.fight.NewEnemyMonSegment;
import mrwint.gbtasgen.segment.fight.ThrashEnemyMonSegment;
import mrwint.gbtasgen.segment.util.DelayMoveSegment;
import mrwint.gbtasgen.segment.util.DelayMoveSegment.PressButtonFactory;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;

public class EliteFourTM extends SeqSegment {

	@Override
	public void execute() {
//		seq(new WalkToSegment(4, 2)); // walk up to trainer
//		seq(new WalkToSegment(5, 2, false)); // walk up to trainer
//		seq(new MoveSegment(new OverworldInteract(1))); // talk to trainer
//
//		seq(new InitFightSegment(9)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[1][1] = 1; // 1x horn attack crit
//			kems.numExpGainers = 1; // no level up
//			kems.onlyPrintInfo = false;
//			seq(kems); // Dewgong
//		}
//		seq(NewEnemyMonSegment.any());
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[3][1] = 1; // 1x thrash crit
//			kems.numExpGainers = 1; // no level up
//			kems.onlyPrintInfo = false;
//			kems.thrashNumAdditionalTurns = 3;
//			seq(kems); // Cloyster
//		}
//		seq(new ThrashEnemyMonSegment(175, true)); // Slowbro
//		seq(new ThrashEnemyMonSegment(147, false)); // Jynx
//		seq(new ThrashEnemyMonSegment(220, true)); // Lapras
//		seq(Move.B);
//		seq(new EndFightSegment(5)); // player defeated enemy
//		seq(new WalkToSegment(4, -1, false));
//		
//		save("tmp");
//		load("tmp");
//
//		seq(new WalkToSegment(4, 2)); // walk up to trainer
//		seq(new WalkToSegment(5, 2, false)); // walk up to trainer
//		seq(new MoveSegment(new OverworldInteract(1))); // talk to trainer
//		seq(new InitFightSegment(10)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
////			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(21)}; // slam
//			kems.attackCount[0][0] = 1; // 1x water gun
//			kems.numExpGainers = 1; // no level up
//			kems.onlyPrintInfo = false;
//			seq(kems); // Onix
//		}
//		seq(NewEnemyMonSegment.any());
//		save("tmp2");
//		load("tmp2");
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[1][1] = 1; // 1x horn attack crit
//			kems.numExpGainers = 1; // no level up
//			kems.onlyPrintInfo = false;
//			seq(kems); // Hitmonchan
//		}
//		seq(NewEnemyMonSegment.any());
//		save("tmp3");
//		load("tmp3");
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[1][0] = 1; // 1x horn attack
//			kems.numExpGainers = 1; // no level up
//			kems.onlyPrintInfo = false;
//			seq(kems); // Hitmonlee
//		}
//		seq(NewEnemyMonSegment.any());
//		save("tmp4");
//		load("tmp4");
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[0][0] = 1; // 1x water gun
////			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(21)}; // slam
//			kems.numExpGainers = 1; // no level up
//			kems.onlyPrintInfo = false;
//			seq(kems); // Onix
//		}
//		seq(NewEnemyMonSegment.any());
//		save("tmp5");
//		load("tmp5");
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[1][1] = 1; // 1x horn attack
//			kems.numExpGainers = 1; // no level up
//			kems.onlyPrintInfo = false;
//			seq(kems); // Machamp
//		}
//		seq(new EndFightSegment(3)); // player defeated enemy
//		seq(new WalkToSegment(4, -1, false));
//		
//		save("tmp2");
//		load("tmp2");
//
//		seq(new WriteMemory(0xd196, 186));
//		
//		seq(new WalkToSegment(4, 2)); // walk up to trainer
//		seq(new WalkToSegment(5, 2, false)); // walk up to trainer
//		seq(new MoveSegment(new OverworldInteract(1))); // talk to trainer
//		seq(new InitFightSegment(12)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckSleepEffectMisses(), 95)}; // hypnosis
//			kems.attackCount[0][1] = 2; // 2x water gun crit
//			kems.numExpGainers = 1; // no level up
//			kems.onlyPrintInfo = false;
//			seq(kems); // Gengar
//		}
//		seq(NewEnemyMonSegment.any());
//		save("tmp3");
//		load("tmp3");
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[1][1] = 1; // 1x horn attack crit
//			kems.numExpGainers = 1; // no level up
//			kems.onlyPrintInfo = false;
//			seq(kems); // Golbat
//		}
//		seq(NewEnemyMonSegment.any());
//		save("tmp4");
//		load("tmp4");
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckSleepEffectMisses(), 95)}; // hypnosis
//			kems.attackCount[0][0] = 1; // 1x water gun
//			kems.attackCount[0][1] = 1; // 1x water gun crit
//			kems.numExpGainers = 1; // no level up
//			kems.onlyPrintInfo = false;
//			seq(kems); // Haunter
//		}
//		seq(NewEnemyMonSegment.any());
//		save("tmp5");
//		load("tmp5");
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[1][1] = 1; // 1x horn attack crit
//			kems.numExpGainers = 1; // no level up
//			kems.onlyPrintInfo = false;
//			seq(kems); // Arbok
//		}
//		seq(NewEnemyMonSegment.any());
//		save("tmp6");
//		load("tmp6");
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckPoisonEffectMisses(), 92)}; // toxic
//			kems.attackCount[0][0] = 1; // 1x water gun
//			kems.attackCount[0][1] = 2; // 2x water gun crit
//			kems.numExpGainers = 1; // no level up
//			kems.onlyPrintInfo = false;
//			seq(kems); // Gengar
//		}
//		save("tmp7");
//		load("tmp7");
//		seq(new EndFightSegment(6)); // player defeated enemy
//		seq(new WalkToSegment(4, -1, false));
//		
//		save("tmp3");
//		load("tmp3");
//
//		seq(new WalkToSegment(5, 11)); // walk up to trainer
//		seq(new WalkToSegment(5, 1)); // walk up to trainer
//		seq(new InitFightSegment(13)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[1][1] = 1; // 1x horn attack crit
//			kems.numExpGainers = 1; // no level up
//			kems.onlyPrintInfo = false;
//			seq(kems); // Gyarados
//		}
//		seq(NewEnemyMonSegment.any());
//		save("tmp4");
//		load("tmp4");
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[3][0] = 1; // 1x thrash
//			kems.thrashNumAdditionalTurns = 3;
//			kems.numExpGainers = 1; // no level up
//			kems.onlyPrintInfo = false;
//			seq(kems); // Dragonair
//		}
//		seq(new ThrashEnemyMonSegment(143, false)); // Dragonair
//		seq(new ThrashEnemyMonSegment(175, true)); // Aerodactyl
//		seq(Move.B);
//		seq(new TextSegment()); // not effective
//		seq(new ThrashEnemyMonSegment(194, true)); // Dragonite
//		seq(Move.B);
//		seq(new EndFightSegment(17)); // player defeated enemy
//		seq(new WalkToSegment(5, -1, false));
//
//		save("tmp6");
		load("tmp6");

		seq(new InitFightSegment(18)); // start fight
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.attackCount[1][1] = 1; // 1x horn attack crit
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			seq(kems); // Pidgeot
		}
		seq(NewEnemyMonSegment.any());
		save("tmp7");
		load("tmp7");
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.attackCount[1][0] = 1; // 1x horn attack
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			seq(kems); // Alakazam
		}
		seq(NewEnemyMonSegment.any());
		save("tmp8");
		load("tmp8");
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 39)}; // tail whip
			kems.attackCount[0][0] = 1; // 1x water gun
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			seq(kems); // Rhydon
		}
		seq(NewEnemyMonSegment.any());
		save("tmp9");
		load("tmp9");
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.attackCount[3][1] = 1; // 1x thrash crit
			kems.thrashNumAdditionalTurns = 2;
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			seq(kems); // Arcanine
		}
		save("tmp10");
		load("tmp10");
		seq(new ThrashEnemyMonSegment(202, true)); // Exeggutor
		save("tmp11");
		load("tmp11");
		seq(new ThrashEnemyMonSegment(188, true)); // Blastoise
		seq(Move.B);
		seq(new EndFightSegment(6)); // player defeated enemy
		seq(new SkipTextsSegment(6+28+15)); // Oak HoF speech
		seq(new TextSegment());
		seq(new TextSegment());
		seq(new SkipTextsSegment(2));
	}
}
