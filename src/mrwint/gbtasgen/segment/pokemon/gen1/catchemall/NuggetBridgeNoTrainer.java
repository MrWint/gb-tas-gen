package mrwint.gbtasgen.segment.pokemon.gen1.catchemall;

import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.move.pokemon.gen1.OverworldInteract;
import mrwint.gbtasgen.segment.pokemon.TextSegment;
import mrwint.gbtasgen.segment.pokemon.WalkToSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EndFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.InitFightSegment;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.NewEnemyMonSegment;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment.EnemyMoveDesc;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;

public class NuggetBridgeNoTrainer extends SeqSegment {

	@Override
	public void execute() {
		seq(new InitFightSegment(8)); // start fight
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 28)}; // sand attack
			kems.attackCount[3][1] = 3; // 3x mega punch crit
			kems.numExpGainers = 2; // level up to 15
			kems.onlyPrintInfo = false;
			seq(kems); // pidgeotto
		}
		{ // cancel learning leer
			seq(new SkipTextsSegment(6));
			seq(new SkipTextsSegment(1, true));
			seq(new SkipTextsSegment(2));
		}
		seq(NewEnemyMonSegment.any()); // next mon
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(Metric.TRUE)}; // tackle
			kems.attackCount[3][1] = 1; // 1x mega punch crit
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			seq(kems); // abra
		}
		seq(NewEnemyMonSegment.any()); // next mon
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 39)}; // tail whip
			kems.attackCount[2][0] = 2; // 2x ember (better: 2x mega punch (-4f))
			kems.numExpGainers = 2; // level up to 16
			kems.onlyPrintInfo = false;
			seq(kems); // rattata
		}
		seq(NewEnemyMonSegment.any()); // next mon
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 39)}; // tail whip
			kems.attackCount[3][1] = 2; // 2x mega punch crit
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			seq(kems); // squirtle
		}
		seq(new EndFightSegment(2)); // player defeated enemy

		seq(new TextSegment()); // evolution
		seq(new TextSegment()); // evolution

		seq(new SkipTextsSegment(14)); // after rival battle texts

		seq(new WalkToSegment(21, -1)); // walk up
		seq(new WalkToSegment(11, 32)); // walk up to trainer
		seq(new MoveSegment(new OverworldInteract(7))); // talk to trainer 1
		
		save("nb1");
		load("nb1");

		seq(new InitFightSegment(4)); // start fight
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.attackCount[2][0] = 1; // 1x ember
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			seq(kems); // caterpie
		}
		seq(NewEnemyMonSegment.any()); // next mon
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.attackCount[2][0] = 1; // 1x ember
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			seq(kems); // weedle
		}
		seq(new EndFightSegment(1)); // player defeated enemy

		seq(new WalkToSegment(10, 29)); // walk up to trainer
		seq(new MoveSegment(new OverworldInteract(6))); // talk to trainer 2

		save("nb2");
		load("nb2");

		seq(new InitFightSegment(1)); // start fight
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.attackCount[3][1] = 1; // 1x mega punch crit
			kems.numExpGainers = 2; // level up to 17
			kems.onlyPrintInfo = false;
			seq(kems); // pidgey
		}
		seq(NewEnemyMonSegment.any()); // next mon
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(33)}; // tackle
//			kems.attackCount[2][0] = 1;
//			kems.attackCount[2][1] = 1; // 1xember, 1xember crit (better: 2x mega punch (-20+f))
			kems.attackCount[3][0] = 2;
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			seq(kems); // nidoF
		}
		seq(new EndFightSegment(1)); // player defeated enemy

		seq(new WalkToSegment(11, 26)); // walk up to trainer
		seq(new MoveSegment(new OverworldInteract(5))); // talk to trainer 3

		save("nb3");
		load("nb3");

		seq(new InitFightSegment(1)); // start fight
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.attackCount[2][1] = 1; // 1x ember crit (better: 1x mega punch crit (-9f))
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			seq(kems); // rattata
		}
		seq(NewEnemyMonSegment.any()); // next mon
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.attackCount[3][1] = 1; // 1x mega punch crit (alt: 1x ember crit (15 spc needed!))
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			seq(kems); // ekans
		}
		seq(NewEnemyMonSegment.any()); // next mon
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.attackCount[3][1] = 1; // 1x mega punch crit (alt: 1x ember crit+burn (15 spc needed!))
			kems.numExpGainers = 2; // level up to 18
			kems.onlyPrintInfo = false;
			seq(kems); // zubat
		}
		seq(new EndFightSegment(1)); // player defeated enemy

		seq(new WalkToSegment(10, 23)); // walk up to trainer
		seq(new MoveSegment(new OverworldInteract(4))); // talk to trainer 4

		save("nb4");
		load("nb4");

		seq(new InitFightSegment(1)); // start fight
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.attackCount[3][1] = 1; // 1x mega punch crit (alt: 1x ember crit + burn)
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			seq(kems); // pidgey
		}
		seq(NewEnemyMonSegment.any()); // next mon
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(33)}; // tackle
			kems.attackCount[2][0] = 1; // 1x ember
			kems.attackCount[2][1] = 1; // 1x ember crit (alt: 1xmega punch crit, 1xmega punch (+3f))
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			seq(kems); // nidoF
		}
		seq(new EndFightSegment(1)); // player defeated enemy

		seq(new WalkToSegment(11, 20)); // walk up to trainer
		seq(new MoveSegment(new OverworldInteract(3))); // talk to trainer 5
		
		save("nb5");
		load("nb5");

		seq(new InitFightSegment(1)); // start fight
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.attackCount[3][1] = 1; // 1x mega punch crit (needs 14 Atk IV)
			kems.numExpGainers = 2; // level up to 19
			kems.onlyPrintInfo = false;
			seq(kems); // mankey
		}
		seq(new EndFightSegment(1)); // player defeated enemy

		seq(new WalkToSegment(10, 15)); // walk up to rocket
		
		save("nb6");
		load("nb6");

		seq(new InitFightSegment(15)); // start fight
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.attackCount[2][1] = 1; // 1x ember crit (alt: 1x mega punch crit)
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			seq(kems); // Rattata
		}
		seq(NewEnemyMonSegment.any()); // next mon
		{
			KillEnemyMonSegment kems = new KillEnemyMonSegment();
			kems.attackCount[2][1] = 1; // 1x ember crit (alt: 1x mega punch crit)
			kems.numExpGainers = 1; // no level up
			kems.onlyPrintInfo = false;
			seq(kems); // Zubat
		}
		seq(new EndFightSegment(1)); // player defeated enemy

		seq(new SkipTextsSegment(3)); // after rocket battle texts

		save("nb7");
//		load("nb7");
//
//		seq(new WalkToSegment(4, 17)); // walk into grass
//		delay(new SeqSegment() {
//			@Override
//			protected void execute() {
//				seq(Move.DOWN);
//				seq(new CheckEncounterMetric(148, 0)); // Abra
//			}
//		});
//		seq(new CatchMonSegment(0));
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
//		save("nb8");
//		load("nb8");
//
//		seq(new WalkToSegment(9, 5)); // align with grass
//		seq(new WalkToSegment(8, 5)); // align with grass
//		seq(new EncounterAndCatchSegment(112, 8, Move.LEFT)); // weedle
//		seq(new WalkToSegment(7, 4)); // align with grass
//		seq(new WalkToSegment(6, 4)); // align with grass
//		seq(new EncounterAndCatchSegment(new CheckEncounterMetric(123, 8).withSpcDV(0,1,2,3,4), Move.DOWN)); // caterpie
//
//		save("nb9");
//		load("nb9");
//
//		seq(new WalkToSegment(15, 7)); // engage hiker
//		seq(new InitFightSegment(2)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 103)}; // screech
//			kems.attackCount[2][1] = 2; // 2x ember crit
//			kems.numExpGainers = 1; // no level up
//			kems.onlyPrintInfo = false;
//			seq(kems); // Onix
//		}
//		seq(new EndFightSegment(1)); // player defeated enemy
//
//		seq(new WalkToSegment(18,7)); // walk up to trainer 2
//		seq(new WalkToSegment(18,8,false)); // walk up to trainer 2
//		seq(new OverworldInteract(4)); // talk to trainer 2
//
//		save("nb10");
//		load("nb10");
//
//		seq(new InitFightSegment(1)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[3][1] = 1; // 1x mega punch crit (alt: 1x ember crit + burn)
//			kems.numExpGainers = 2; // level up to 20
//			kems.onlyPrintInfo = false;
//			seq(kems); // nidoM
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
//		seq(new WalkToSegment(24,6)); // walk up to trainer 3
//
//		save("nb11");
//		load("nb11");
//
//		seq(new InitFightSegment(2)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[2][0] = 1; // 1x ember
//			kems.numExpGainers = 1; // no level up
//			kems.onlyPrintInfo = false;
//			seq(kems); // rattata
//		}
//		seq(NewEnemyMonSegment.any()); // next mon
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[2][1] = 1; // 1x ember crit
//			kems.numExpGainers = 1; // no level up
//			kems.onlyPrintInfo = false;
//			seq(kems); // ekans
//		}
//		seq(new EndFightSegment(1)); // player defeated enemy
//
//		seq(new WalkToSegment(35,4)); // walk up to trainer 4
//		seq(new WalkToSegment(36,4)); // walk up to trainer 4
//		seq(new OverworldInteract(6)); // talk to trainer 4
//
//		save("nb12");
//		load("nb12");
//
//		seq(new InitFightSegment(2)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[2][0] = 1; // 1x ember
//			kems.numExpGainers = 1; // no level up
//			kems.onlyPrintInfo = false;
//			seq(kems); // oddish
//		}
//		seq(NewEnemyMonSegment.any()); // next mon
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[2][1] = 1; // 1x ember crit
//			kems.numExpGainers = 2; // level up to 21
//			kems.onlyPrintInfo = false;
//			seq(kems); // pidgey
//		}
//		seq(NewEnemyMonSegment.any()); // next mon
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[2][0] = 1; // 1x ember
//			kems.numExpGainers = 1; // no level up
//			kems.onlyPrintInfo = false;
//			seq(kems); // oddish
//		}
//		seq(new EndFightSegment(1)); // player defeated enemy
//
//		seq(new WalkToSegment(45,3)); // enter bills house
//
//		seq(new WalkToSegment(4,5)); // walk up to bill
//		seq(new WalkToSegment(5,5)); // walk up to bill
//		seq(new OverworldInteract(1)); // talk to bill
//
//		seq(new SkipTextsSegment(10));
//		seq(new SkipTextsSegment(1, true)); // help
//		seq(new SkipTextsSegment(4));
//
//		seq(new WalkToSegment(1,4,false)); // walk up to desk
//		seq(Move.A); // talk to desk
//		seq(new SkipTextsSegment(2));
//
//		seq(new WalkToSegment(4,5)); // walk up to bill
//		seq(new WalkToSegment(4,4,false)); // walk up to bill
//		seq(new OverworldInteract(2)); // talk to bill
//		seq(new SkipTextsSegment(18));
//
//		seq(new WalkToSegment(3,8,false)); // leave
	}
}
