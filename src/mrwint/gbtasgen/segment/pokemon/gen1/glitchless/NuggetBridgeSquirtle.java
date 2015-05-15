package mrwint.gbtasgen.segment.pokemon.gen1.glitchless;

import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.metric.pokemon.CheckEncounterMetric;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.move.WriteMemory;
import mrwint.gbtasgen.move.pokemon.gen1.OverworldInteract;
import mrwint.gbtasgen.move.pokemon.gen1.WalkStep;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.BallSuccessSegment;
import mrwint.gbtasgen.segment.pokemon.CatchMonSegment;
import mrwint.gbtasgen.segment.pokemon.PokecenterSegment;
import mrwint.gbtasgen.segment.pokemon.TextSegment;
import mrwint.gbtasgen.segment.pokemon.WalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.InitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.NewEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment.EnemyMoveDesc;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EncounterAndCatchSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;

public class NuggetBridgeSquirtle extends SeqSegment {

	@Override
	public void execute() {
//    seq(new WalkToSegment(19,17).setMaxBufferSize(0)); // enter Center
//    seq(new PokecenterSegment(true)); // set warp point in center
//    seq(new WalkToSegment(20,6)); // go to rival fight
//		seq(new InitFightSegment(8)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 28)}; // sand attack
//      kems.attackCount[0][1] = 2; // 2x mega punch crit
//			seq(kems); // pidgeotto
//		}
//		seq(NewEnemyMonSegment.any()); // next mon
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(Metric.TRUE)}; // tackle
//			kems.attackCount[0][0] = 1; // 1x mega punch
//      kems.numExpGainers = 2; // level up to 18
//			seq(kems); // abra
//		}
//		seq(NewEnemyMonSegment.any()); // next mon
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[3][1] = 1; // 1x water gun crit
//			seq(kems); // rattata
//		}
//		seq(NewEnemyMonSegment.any()); // next mon
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 45)}; // growl
//			kems.attackCount[0][0] = 2; // 2x mega punch
//			seq(kems); // squirtle
//		}
//		seq(new EndFightSegment(2)); // player defeated enemy
//
//		seq(new SkipTextsSegment(14)); // after rival battle texts
//
//		seq(new WalkToSegment(21, -1)); // walk up
//		seq(new WalkToSegment(11, 32)); // walk up to trainer
//		seqMove(new OverworldInteract(7)); // talk to trainer 1
//
//		save("nb1");
////		load("nb1");
//
//		seq(new InitFightSegment(4)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[3][1] = 1; // 1x water gun crit
//      kems.numExpGainers = 2; // level up to 19
//			seq(kems); // caterpie
//		}
//		seq(NewEnemyMonSegment.any()); // next mon
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[3][1] = 1; // 1x water gun crit
////			kems.attackCount[0][0] = 1; // 1x mega punch
//			seq(kems); // weedle
//		}
//		seq(new EndFightSegment(1)); // player defeated enemy
//
//		seq(new WalkToSegment(10, 29)); // walk up to trainer
//		seqMove(new OverworldInteract(6)); // talk to trainer 2
//
//		seq(new InitFightSegment(1)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[3][1] = 1; // 1x water gun crit
//			seq(kems); // pidgey
//		}
//		seq(NewEnemyMonSegment.any()); // next mon
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[3][1] = 1; // 1x water gun crit
//			seq(kems); // nidoF
//		}
//		seq(new EndFightSegment(1)); // player defeated enemy
//
//		seq(new WalkToSegment(11, 26)); // walk up to trainer
//		seqMove(new OverworldInteract(5)); // talk to trainer 3
//
//		seq(new InitFightSegment(1)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[3][1] = 1; // 1x water gun crit
////			kems.attackCount[0][0] = 1; // 1x mega punch
//			seq(kems); // rattata
//		}
//		seq(NewEnemyMonSegment.any()); // next mon
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[3][1] = 1; // 1x water gun crit
//			seq(kems); // ekans
//		}
//		seq(NewEnemyMonSegment.any()); // next mon
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[3][1] = 1; // 1x water gun crit
//      kems.numExpGainers = 2; // level up to 20
//			seq(kems); // zubat
//		}
//		seq(new EndFightSegment(1)); // player defeated enemy
//
//		seq(new WalkToSegment(10, 23)); // walk up to trainer
//		seqMove(new OverworldInteract(4)); // talk to trainer 4
//
//		seq(new InitFightSegment(1)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[3][1] = 1; // 1x water gun crit
//			seq(kems); // pidgey
//		}
//		seq(NewEnemyMonSegment.any()); // next mon
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[0][1] = 1; // 1x mega punch crit
//			seq(kems); // nidoF
//		}
//		seq(new EndFightSegment(1)); // player defeated enemy
//
//		seq(new WalkToSegment(11, 20)); // walk up to trainer
//		seqMove(new OverworldInteract(3)); // talk to trainer 5
//
//		seq(new InitFightSegment(1)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[0][1] = 1; // 1x mega punch crit
//			seq(kems); // mankey
//		}
//		seq(new EndFightSegment(1)); // player defeated enemy
//
//		seq(new WalkToSegment(10, 15)); // walk up to rocket
//
//		seq(new InitFightSegment(15)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[3][1] = 1; // 1x water gun crit
//      kems.numExpGainers = 2; // level up to 21
//			seq(kems); // Rattata
//		}
//		seq(NewEnemyMonSegment.any()); // next mon
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[3][1] = 1; // 1x water gun crit
////      kems.attackCount[0][0] = 1; // 1x mega punch
//			seq(kems); // Zubat
//		}
//		seq(new EndFightSegment(1)); // player defeated enemy
//
//
//		save("nb2");
//		load("nb2");
//
//		seq(new SkipTextsSegment(3)); // after rocket battle texts
//
//		seq(new WalkToSegment(20, 9)); // enter route 25
//		seq(new WalkToSegment(14, 7)); // engage hiker
//		seq(new InitFightSegment(2)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[2][0] = 1; // 1x bubble
//			seq(kems); // Onix
//		}
//		seq(new EndFightSegment(1)); // player defeated enemy
//
//		seq(new WalkToSegment(18,7)); // walk up to trainer 2
//		seq(new WalkToSegment(18,8,false)); // walk up to trainer 2
//		seqMove(new OverworldInteract(4)); // talk to trainer 2
//
//		seq(new InitFightSegment(1)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[3][1] = 1; // 1x water gun crit
//			seq(kems); // nidoM
//		}
//		seq(NewEnemyMonSegment.any()); // next mon
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[3][1] = 1; // 1x water gun crit
//			seq(kems); // nidoF
//		}
//		seq(new EndFightSegment(1)); // player defeated enemy
//
//		seq(new WalkToSegment(24,6)); // walk up to trainer 3
//
//    save("tmp");
//    load("tmp");
//
//		seq(new InitFightSegment(2)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[3][0] = 1; // 1x water gun
//      kems.numExpGainers = 2; // level up to 22
//			seq(kems); // rattata
//		}
//		seq(NewEnemyMonSegment.any()); // next mon
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[3][1] = 1; // 1x water gun crit
////			kems.attackCount[0][0] = 1; // 1x mega punch
//			seq(kems); // ekans
//		}
//		seq(new EndFightSegment(1)); // player defeated enemy
//
//		seq(new WalkToSegment(35,4)); // walk up to trainer 4
//		seq(new WalkToSegment(36,4)); // walk up to trainer 4
//		seqMove(new OverworldInteract(6)); // talk to trainer 4
//
//		seq(new InitFightSegment(2)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[0][0] = 1; // 1x mega punch
//			seq(kems); // oddish
//		}
//		seq(NewEnemyMonSegment.any()); // next mon
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[0][0] = 1; // 1x mega punch
//			seq(kems); // pidgey
//		}
//		seq(NewEnemyMonSegment.any()); // next mon
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[0][0] = 1; // 1x mega punch
//			seq(kems); // oddish
//		}
//		seq(new EndFightSegment(1)); // player defeated enemy
//
//		seq(new WalkToSegment(45,3)); // enter bills house
//
//		seq(new WalkToSegment(4,5)); // walk up to bill
//		seq(new WalkToSegment(5,5)); // walk up to bill
//		seqMove(new OverworldInteract(1)); // talk to bill
//
//		seq(new SkipTextsSegment(10));
//		seq(new SkipTextsSegment(1, true)); // help
//		seq(new SkipTextsSegment(4));
//
//		seq(new WalkToSegment(1,4,false)); // walk up to desk
//		seqButton(Move.A); // talk to desk
//		seq(new SkipTextsSegment(2));
//
//		seq(new WalkToSegment(4,5)); // walk up to bill
//		seq(new WalkToSegment(4,4,false)); // walk up to bill
//		seqMove(new OverworldInteract(2)); // talk to bill
//		seq(new SkipTextsSegment(18));
//
//		save("nb3");
////		load("nb3");
//
//		seq(Segment.repress(Move.START));
//		seq(Segment.scrollA(2)); // items
//		seq(Segment.scrollFastAF(2)); // escape rope
//		seq(Segment.repress(Move.A));
//
//		seq(Segment.skip(2));
//		seq(new WalkToSegment(30, 19)); // enter gym
//		seq(new WalkToSegment(5, 3)); // engage
//    seq(new InitFightSegment(2)); // start fight
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 39)}; // tail whip
//      kems.attackCount[0][0] = 2; // 2x mega punch
//      seq(kems); // Goldeen
//    }
//    seq(new EndFightSegment(1)); // player defeated enemy
//
//    save("nb4");
////    load("nb4");
//
//    seq(new WalkToSegment(4, 2, false)); // walk up to misty
//    seqMove(new OverworldInteract(1)); // talk to misty
//    seq(new InitFightSegment(9)); // start fight
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[0][1] = 1; // 1x mega punch crit
//      kems.numExpGainers = 2; // level up to 23
//      seq(kems); // staryu
//    }
//    seq(NewEnemyMonSegment.any()); // next mon
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(33)}; // tackle
//      kems.attackCount[0][1] = 2; // 2x mega punch crit
//      seq(kems); // starmie
//    }
//    save("nb5");
////    load("nb5");
//    seq(new EndFightSegment(4)); // player defeated enemy
//    seq(new SkipTextsSegment(9)); // after battle speech
//    seq(new WalkToSegment(5, 14, false)); // leave gym
//    seq(new WalkToSegment(27, 11)); // enter dig house
	}
}
