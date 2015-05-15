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

public class NuggetBridgeSquirtleAlt extends SeqSegment {

	@Override
	public void execute() {
//    seq(new WalkToSegment(30, 19)); // enter gym
//    seq(new WalkToSegment(5, 3)); // engage
//    seq(new InitFightSegment(2)); // start fight
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 39)}; // tail whip
//      kems.attackCount[0][1] = 2; // mega punch crit
//      seq(kems); // Goldeen
//    }
//    seq(new EndFightSegment(1)); // player defeated enemy
//
//    seq(new WalkToSegment(4, 2, false)); // walk up to misty
//    seqMove(new OverworldInteract(1)); // talk to misty
//    seq(new InitFightSegment(9)); // start fight
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(33)}; // tackle
//      kems.attackCount[0][0] = 1; // mega punch
//      kems.attackCount[0][1] = 1; // mega punch crit
//      kems.numExpGainers = 2; // level up to 18
//      seq(kems); // staryu
//    }
//    seq(NewEnemyMonSegment.any()); // next mon
//    {
//      KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(33)}; // tackle
//      kems.attackCount[0][1] = 3; // mega punch crit
//      kems.numExpGainers = 2; // level up to 19
//      seq(kems); // starmie
//    }
//    seq(new EndFightSegment(4)); // player defeated enemy
//    seq(new SkipTextsSegment(9)); // after battle speech
//    seq(new WalkToSegment(5, 14, false)); // leave gym
//    save("nba1");
//    load("nba1");
//    {
//      seq(Segment.repress(Move.START));
//      seq(Segment.scroll(2));
//      seq(Segment.press(Move.A)); // items
//      { // learn TM11
//        seq(Segment.scrollFastAF(4));
//        seq(Segment.repress(Move.A)); // use
//        {
//          seq(new SkipTextsSegment(2)); // booted up TM, contains xyz
//          seq(new SkipTextsSegment(1, true)); // learn
//          seq(Segment.repress(Move.A)); // select wartortle
//          seq(new SkipTextsSegment(5)); // learned TM
//          seq(new SkipTextsSegment(1, true)); // yes, overwrite
//          seq(new TextSegment());
//          seq(Segment.scrollAF(3)); // water gun
//          seq(new TextSegment(Move.A, false)); // 1,2 and
//          seqButton(Move.B); // skip 30f
//          seqButton(Move.B);
//          seq(new TextSegment()); // poof
//          seqButton(Move.A); // skip 30f
//          seqButton(Move.B);
//          seq(new SkipTextsSegment(3)); // learned TM
//        }
//      }
//      seq(Segment.repress(Move.B));
//      seqButton(Move.START);
//    }
//    seq(new WalkToSegment(19,17)); // enter Center
//    seq(new PokecenterSegment(true)); // set warp point in center
//    seq(new WalkToSegment(20,6)); // go to rival fight
//		seq(new InitFightSegment(8)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 28)}; // sand attack
//      kems.attackCount[3][0] = 2; // bubblebeam crit
//      kems.numExpGainers = 2; // level up to 20
//			seq(kems); // pidgeotto
//		}
//		seq(NewEnemyMonSegment.any()); // next mon
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(Metric.TRUE)}; // tackle
//			kems.attackCount[0][0] = 1; // mega punch
//			seq(kems); // abra
//		}
//		seq(NewEnemyMonSegment.any()); // next mon
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[0][0] = 1; // mega punch
////			kems.attackCount[3][0] = 1; // bubblebeam
//			seq(kems); // rattata
//		}
//		seq(NewEnemyMonSegment.any()); // next mon
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 45)}; // growl
//			kems.attackCount[0][1] = 1; // mega punch crit
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
//		save("nba2");
////		load("nba2");
//
//		seq(new InitFightSegment(4)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[0][0] = 1; // mega punch
////      kems.attackCount[3][0] = 1; // bubblebeam
//			seq(kems); // caterpie
//		}
//		seq(NewEnemyMonSegment.any()); // next mon
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[3][0] = 1; // bubblebeam
//      kems.numExpGainers = 2; // level up to 21
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
//      kems.attackCount[3][0] = 1; // bubblebeam
//			seq(kems); // pidgey
//		}
//		seq(NewEnemyMonSegment.any()); // next mon
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[3][0] = 1; // bubblebeam
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
//      kems.attackCount[3][0] = 1; // bubblebeam
//			seq(kems); // rattata
//		}
//		seq(NewEnemyMonSegment.any()); // next mon
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[3][0] = 1; // bubblebeam
//			seq(kems); // ekans
//		}
//		seq(NewEnemyMonSegment.any()); // next mon
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[3][0] = 1; // bubblebeam
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
//      kems.attackCount[3][0] = 1; // bubblebeam
//			seq(kems); // pidgey
//		}
//		seq(NewEnemyMonSegment.any()); // next mon
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[3][1] = 1; // bubblebeam crit
//      kems.numExpGainers = 2; // level up to 22
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
//      kems.attackCount[3][0] = 1; // bubblebeam
//			seq(kems); // Ekans
//		}
//		seq(NewEnemyMonSegment.any()); // next mon
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//      kems.attackCount[3][0] = 1; // bubblebeam
//			seq(kems); // Zubat
//		}
//		seq(new EndFightSegment(1)); // player defeated enemy
//
//
//		save("nba3");
		load("nba3");

		seq(new SkipTextsSegment(3)); // after rocket battle texts

		seq(new WalkToSegment(20, 9)); // enter route 25
		seq(new WalkToSegment(14, 7)); // engage hiker
		seq(new InitFightSegment(2)); // start fight
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.attackCount[2][0] = 1; // 1x bubble
			seq(kems); // Onix
		}
		seq(new EndFightSegment(1)); // player defeated enemy

		seq(new WalkToSegment(18,7)); // walk up to trainer 2
		seq(new WalkToSegment(18,8,false)); // walk up to trainer 2
		seqMove(new OverworldInteract(4)); // talk to trainer 2

		seq(new InitFightSegment(1)); // start fight
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
      kems.attackCount[3][0] = 1; // bubblebeam
      kems.numExpGainers = 2; // level up to 23
			seq(kems); // nidoM
		}
		seq(NewEnemyMonSegment.any()); // next mon
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
      kems.attackCount[3][0] = 1; // bubblebeam
			seq(kems); // nidoF
		}
		seq(new EndFightSegment(1)); // player defeated enemy

		seq(new WalkToSegment(24,6)); // walk up to trainer 3

		seq(new InitFightSegment(2)); // start fight
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
      kems.attackCount[2][1] = 1; // 1x bubble crit
			seq(kems); // rattata
		}
		seq(NewEnemyMonSegment.any()); // next mon
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
      kems.attackCount[3][0] = 1; // bubblebeam
			seq(kems); // ekans
		}
		seq(new EndFightSegment(1)); // player defeated enemy

		seq(new WalkToSegment(35,4)); // walk up to trainer 4
		seq(new WalkToSegment(36,4)); // walk up to trainer 4
		seqMove(new OverworldInteract(6)); // talk to trainer 4

		seq(new InitFightSegment(2)); // start fight
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.attackCount[0][0] = 1; // 1x mega punch
			seq(kems); // oddish
		}
		seq(NewEnemyMonSegment.any()); // next mon
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
      kems.attackCount[2][1] = 1; // 1x bubble crit
			seq(kems); // pidgey
		}
		seq(NewEnemyMonSegment.any()); // next mon
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.attackCount[0][0] = 1; // 1x mega punch
			seq(kems); // oddish
		}
		seq(new EndFightSegment(1)); // player defeated enemy

		seq(new WalkToSegment(45,3)); // enter bills house

		seq(new WalkToSegment(4,5)); // walk up to bill
		seq(new WalkToSegment(5,5)); // walk up to bill
		seqMove(new OverworldInteract(1)); // talk to bill

		seq(new SkipTextsSegment(10));
		seq(new SkipTextsSegment(1, true)); // help
		seq(new SkipTextsSegment(4));

		seq(new WalkToSegment(1,4,false)); // walk up to desk
		seqButton(Move.A); // talk to desk
		seq(new SkipTextsSegment(2));

		seq(new WalkToSegment(4,5)); // walk up to bill
		seq(new WalkToSegment(4,4,false)); // walk up to bill
		seqMove(new OverworldInteract(2)); // talk to bill
		seq(new SkipTextsSegment(18));

		seq(Segment.repress(Move.START));
		seq(Segment.scrollA(2)); // items
		seq(Segment.scrollFastAF(1)); // escape rope
		seq(Segment.repress(Move.A));

		seq(Segment.skip(2));
    seq(new WalkToSegment(27, 11)); // enter dig house
	}
}
