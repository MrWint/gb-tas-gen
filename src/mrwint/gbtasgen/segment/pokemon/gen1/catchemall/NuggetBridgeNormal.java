package mrwint.gbtasgen.segment.pokemon.gen1.catchemall;

import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.metric.pokemon.CheckEncounterMetric;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.move.pokemon.gen1.OverworldInteract;
import mrwint.gbtasgen.move.pokemon.gen1.WalkStep;
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
import mrwint.gbtasgen.segment.pokemon.gen1.common.EncounterAndCatchSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;

public class NuggetBridgeNormal extends SeqSegment {

	@Override
	public void execute() {
//		seq(new InitFightSegment(8)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 28)}; // sand attack
//			kems.attackCount[3][1] = 2; // 2x mega punch crit
//			kems.numExpGainers = 2; // level up to 17
//			kems.onlyPrintInfo = false;
//			seq(kems); // pidgeotto
//		}
//		seq(NewEnemyMonSegment.any()); // next mon
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(Metric.TRUE)}; // tackle
//			kems.attackCount[3][0] = 1; // 1x mega punch
//			kems.numExpGainers = 1; // no level up
//			kems.onlyPrintInfo = false;
//			seq(kems); // abra
//		}
//		seq(NewEnemyMonSegment.any()); // next mon
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[3][1] = 1; // 1x mega punch crit
//			kems.numExpGainers = 1; // no level up
//			kems.onlyPrintInfo = false;
//			seq(kems); // rattata
//		}
//		seq(NewEnemyMonSegment.any()); // next mon
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 39)}; // tail whip
//			kems.attackCount[3][0] = 1; // 1x mega punch
//			kems.attackCount[3][1] = 1; // 1x mega punch crit
//			kems.numExpGainers = 2; // level up to 18
//			kems.onlyPrintInfo = false;
//			seq(kems); // squirtle
//		}
//		seq(new EndFightSegment(2)); // player defeated enemy
//
//		seq(new SkipTextsSegment(14)); // after rival battle texts
//
//		seq(new WalkToSegment(21, -1)); // walk up
//		seq(new WalkToSegment(11, 32)); // walk up to trainer
//		seq(new OverworldInteract(7)); // talk to trainer 1
//		
//		save("nb1");
////		load("nb1");
//
//		seq(new InitFightSegment(4)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[0][0] = 1; // 1x ember
//			kems.numExpGainers = 1; // no level up
//			kems.onlyPrintInfo = false;
//			seq(kems); // caterpie
//		}
//		seq(NewEnemyMonSegment.any()); // next mon
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[0][0] = 1; // 1x ember
//			kems.numExpGainers = 1; // no level up
//			kems.onlyPrintInfo = false;
//			seq(kems); // weedle
//		}
//		seq(new EndFightSegment(1)); // player defeated enemy
//
//		seq(new WalkToSegment(10, 29)); // walk up to trainer
//		seq(new OverworldInteract(6)); // talk to trainer 2
//
//		seq(new InitFightSegment(1)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[3][1] = 1; // 1x mega punch crit (alt: 1xember crit)
//			kems.numExpGainers = 1; // no level up
//			kems.onlyPrintInfo = false;
//			seq(kems); // pidgey
//		}
//		seq(NewEnemyMonSegment.any()); // next mon
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[3][1] = 1; // 1x mega punch crit
//			kems.numExpGainers = 1; // no level up
//			kems.onlyPrintInfo = false;
//			seq(kems); // nidoF
//		}
//		seq(new EndFightSegment(1)); // player defeated enemy
//
//		seq(new WalkToSegment(11, 26)); // walk up to trainer
//		seq(new OverworldInteract(5)); // talk to trainer 3
//
//		seq(new InitFightSegment(1)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[0][1] = 1; // 1x ember crit
//			kems.numExpGainers = 2; // level up to 19
//			kems.onlyPrintInfo = false;
//			seq(kems); // rattata
//		}
//		seq(NewEnemyMonSegment.any()); // next mon
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[0][1] = 1; // 1x ember crit
//			kems.numExpGainers = 1; // no level up
//			kems.onlyPrintInfo = false;
//			seq(kems); // ekans
//		}
//		seq(NewEnemyMonSegment.any()); // next mon
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[0][1] = 1; // 1x ember crit
//			kems.numExpGainers = 1; // no level up
//			kems.onlyPrintInfo = false;
//			seq(kems); // zubat
//		}
//		seq(new EndFightSegment(1)); // player defeated enemy
//
//		seq(new WalkToSegment(10, 23)); // walk up to trainer
//		seq(new OverworldInteract(4)); // talk to trainer 4
//
//		seq(new InitFightSegment(1)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
////			kems.attackCount[0][1] = 1; // 1x ember crit (alt: 1x mega punch crit)
//			kems.attackCount[3][1] = 1; // 1x mega punch crit
//			kems.numExpGainers = 1; // no level up
//			kems.onlyPrintInfo = false;
//			seq(kems); // pidgey
//		}
//		seq(NewEnemyMonSegment.any()); // next mon
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(33)}; // tackle
//			kems.attackCount[3][0] = 2; // 2x mega punch
//			kems.numExpGainers = 1; // no level up
//			kems.onlyPrintInfo = false;
//			seq(kems); // nidoF
//		}
//		seq(new EndFightSegment(1)); // player defeated enemy
//
//		seq(new WalkToSegment(11, 20)); // walk up to trainer
//		seq(new OverworldInteract(3)); // talk to trainer 5
//
//		seq(new InitFightSegment(1)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[3][1] = 1; // 1x mega punch crit
//			kems.numExpGainers = 2; // level up to 20
//			kems.onlyPrintInfo = false;
//			seq(kems); // mankey
//		}
//		seq(new EndFightSegment(1)); // player defeated enemy
//
//		seq(new WalkToSegment(10, 15)); // walk up to rocket
//
//		seq(new InitFightSegment(15)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[0][1] = 1; // 1x ember crit (alt: 1x mega punch crit)
//			kems.numExpGainers = 1; // no level up
//			kems.onlyPrintInfo = false;
//			seq(kems); // Rattata
//		}
//		seq(NewEnemyMonSegment.any()); // next mon
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[0][1] = 1; // 1x ember crit (alt: 1x mega punch crit)
//			kems.numExpGainers = 1; // no level up
//			kems.onlyPrintInfo = false;
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
//		seq(new WalkToSegment(4, 17)); // walk into grass
//		seq(new EncounterAndCatchSegment(new CheckEncounterMetric(148),  Move.DOWN)); // Abra
//
//		save("nb3a");
////		load("nb3a");
//
//		seq(new WalkToSegment(5, 15)); // prepare tfly
//		seq(new WalkToSegment(5, 16)); // prepare tfly
//		seq(new PressButton(Move.START), 0);
//		seq(Segment.scroll(1));
//		seq(Move.A);
//		seq(Move.UP);
//		seq(Move.A);
//		seq(Segment.repress(Move.A));
//		seq(new TextSegment()); // go to last center
//		seq(new WalkToSegment(20, -1)); // enter route 24
//		seq(new WalkToSegment(20, 9)); // enter route 25
//
//		save("nb4a");
//		load("nb4a");
//
//		seq(new WalkToSegment(8, 5)); // align with grass
//		delay(new SeqSegment() {
//			@Override
//			protected void execute() {
//				seq(new WalkStep(Move.LEFT, false));
//				seq(new CheckEncounterMetric(123, 8).withSpcDV(0,1,2,3,4)); // caterpie
//			}
//		});
//		save("nb45");
//		load("nb45");
//		seq(new CatchMonSegment(0));
//		save("nb5");
//		load("nb5");
//		seq(new WalkStep(Move.DOWN, true));
//		seq(new WalkStep(Move.LEFT, true));
//		seq(new WalkStep(Move.LEFT, true));
//		seq(new WalkStep(Move.LEFT, true));
//		seq(new WalkStep(Move.UP, true));
//		seq(new WalkStep(Move.RIGHT, true));
//		seq(new WalkStep(Move.RIGHT, true));
//		
//		seq(new EncounterAndCatchSegment(new CheckEncounterMetric(112, 8), Move.RIGHT)); // weedle
//
//		save("nb6");
		load("nb6");

		seq(new WalkToSegment(15, 7)); // engage hiker
		seq(new InitFightSegment(2)); // start fight
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 103)}; // screech
			kems.attackCount[0][1] = 2; // 2x ember crit
			kems.numExpGainers = 2; // level up to 21
			kems.onlyPrintInfo = false;
			seq(kems); // Onix
		}
		seq(new EndFightSegment(1)); // player defeated enemy

		seq(new WalkToSegment(18,7)); // walk up to trainer 2
		seq(new WalkToSegment(18,8,false)); // walk up to trainer 2
		seq(new OverworldInteract(4)); // talk to trainer 2

		seq(new InitFightSegment(1)); // start fight
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.attackCount[0][1] = 1; // 1x ember crit
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			seq(kems); // nidoM
		}
		seq(NewEnemyMonSegment.any()); // next mon
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.attackCount[0][1] = 1; // 1x ember crit
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			seq(kems); // nidoF
		}
		seq(new EndFightSegment(1)); // player defeated enemy

		seq(new WalkToSegment(24,6)); // walk up to trainer 3

		seq(new InitFightSegment(2)); // start fight
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.attackCount[0][0] = 1; // 1x ember
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			seq(kems); // rattata
		}
		seq(NewEnemyMonSegment.any()); // next mon
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.attackCount[0][1] = 1; // 1x ember crit
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			seq(kems); // ekans
		}
		seq(new EndFightSegment(1)); // player defeated enemy

		seq(new WalkToSegment(35,4)); // walk up to trainer 4
		seq(new WalkToSegment(36,4)); // walk up to trainer 4
		seq(new OverworldInteract(6)); // talk to trainer 4

		seq(new InitFightSegment(2)); // start fight
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.attackCount[0][0] = 1; // 1x ember
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			seq(kems); // oddish
		}
		seq(NewEnemyMonSegment.any()); // next mon
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.attackCount[0][1] = 1; // 1x ember crit (better(?) 1xmega punch)
			kems.numExpGainers = 2; // level up to 22
			kems.onlyPrintInfo = false;
			seq(kems); // pidgey
		}
		seq(NewEnemyMonSegment.any()); // next mon
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.attackCount[0][0] = 1; // 1x ember
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			seq(kems); // oddish
		}
		seq(new EndFightSegment(1)); // player defeated enemy

		seq(new WalkToSegment(45,3)); // enter bills house

		seq(new WalkToSegment(4,5)); // walk up to bill
		seq(new WalkToSegment(5,5)); // walk up to bill
		seq(new OverworldInteract(1)); // talk to bill

		seq(new SkipTextsSegment(10));
		seq(new SkipTextsSegment(1, true)); // help
		seq(new SkipTextsSegment(4));

		seq(new WalkToSegment(1,4,false)); // walk up to desk
		seq(Move.A); // talk to desk
		seq(new SkipTextsSegment(2));

		seq(new WalkToSegment(4,5)); // walk up to bill
		seq(new WalkToSegment(4,4,false)); // walk up to bill
		seq(new OverworldInteract(2)); // talk to bill
		seq(new SkipTextsSegment(18));
		
		save("nb7");
//		load("nb7");
//
		seq(Segment.repress(Move.START));
		seq(Segment.scroll(2)); // items
		seq(Move.A);
		seq(Move.SELECT); // pokeball
		seq(Segment.scrollFast(5));
		seq(Move.SELECT); // nugget
		seq(Segment.scrollFast(-4));
		seq(Move.A);
		seq(Segment.repress(Move.A));
		
		seq(Segment.skip(2));
		seq(new WalkToSegment(13, 15));
		seq(new WalkToSegment(1, 4));
		seq(new WalkToSegment(1, 3));
		seq(new OverworldInteract(2));
		seq(new SkipTextsSegment(3));
		seq(Move.START);
		seq(Move.B);
		seq(new WalkToSegment(2, 8, false));
		seq(new WalkToSegment(20, -1));
		
		seq(Move.START);
		
//		save("nb14a");
//		load("nb14a");
//
		seq(new SkipTextsSegment(1)); // wild missingno
		seq(new TextSegment()); // go A
		seq(Move.DOWN); // items
		seq(Move.A); // items
		seq(Segment.scrollFast(5)); // pokeball
		seq(Move.SELECT);
		seq(Segment.scrollFast(-3)); // rare candy
		seq(Move.SELECT);
		{
			seq(new BallSuccessSegment()); // pokeball
			seq(new SkipTextsSegment(4)); // cought, new dex data
			seq(Segment.press(Move.A)); // skip dex
			seq(Segment.press(Move.B)); // skip dex
			seq(new SkipTextsSegment(2)); // no nickname
//			seq(new SkipTextsSegment(2)); // transferred to PC
		}
	}
}
