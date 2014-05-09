package mrwint.gbtasgen.segment.gen1.catchemall;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.gen1.OverworldInteract;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.TextSegment;
import mrwint.gbtasgen.segment.WalkToSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;

public class Underflow extends SeqSegment {

	@Override
	public void execute() {

//		seq(new WalkToSegment(10, 36)); // enter cerulean
//		seq(new WalkToSegment(27, 11)); // enter house
//		seq(new WalkToSegment(3, 0)); // leave house
//		seq(new WalkToSegment(30, 9)); // engage rocket
//
//		seq(new InitFightSegment(4)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[3][1] = 1; // 1x mega punch crit
//			kems.numExpGainers = 1; // no level up
//			kems.onlyPrintInfo = false;
//			seq(kems); // machop
//		}
//		seq(NewEnemyMonSegment.any()); // next mon
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[3][1] = 1; // 1x mega punch crit
//			kems.numExpGainers = 1; // no level up
//			kems.onlyPrintInfo = false;
//			seq(kems); // drowzee
//		}
//		seq(new EndFightSegment(2)); // player defeated enemy
//
//		seq(new SkipTextsSegment(3)); // after rocket battle texts
//		
//		save("uf1");
//		load("uf1");
//
//		seq(new WalkToSegment(28,36)); // enter route 5
//		seq(new WalkToSegment(17,27)); // enter passage
//		seq(new WalkToSegment(4,4)); // enter passage
//		seq(new WalkToSegment(2,41)); // walk passage
//		seq(new WalkToSegment(4,8,false)); // leave passage
//
//		seq(new WalkToSegment(5,15)); // walk up to trainer
//		seq(new WalkToSegment(4,15)); // enter trainer-fly tile
//		seq(new PressButton(Move.START),0); // start trainer fly
//
//		save("uf2");
//		load("uf2");
//
//		{
//			seq(Segment.scroll(2)); // items
//			seq(Move.A); // items
//			seq(Segment.scrollFast(5)); // rare candy
//			for (int i=0;i<2;i++) {
//				seq(Segment.repress(Move.A));
//				seq(Segment.repress(Move.A)); // rare candy
//				seq(Segment.repress(Move.A)); // Charmeleon
//				seq(Move.B); // rose to lvl 23/24
//				seq(Move.A); // clear stats
//			}
//			seq(new CancelMoveLearnSegment()); // Rage
//			seq(Segment.repress(Move.B)); // items
//		}
//
//		seq(Segment.scroll(-1)); // mons
//		seq(Move.A); // mons
//		seq(Segment.scroll(-2)); // Abra
//		seq(Move.A); // abra
//		seq(Segment.repress(Move.A)); // teleport
//		seq(new TextSegment()); // teleport text
//
//		seq(new WalkToSegment(27, 11)); // enter house
//		seq(new WalkToSegment(3, 0)); // leave house
//		seq(new WalkToSegment(28,36)); // enter route 5
//		seq(new WalkToSegment(17,27)); // enter passage
//		seq(new WalkToSegment(4,4)); // enter passage
//		seq(new WalkToSegment(2,41)); // walk passage
//		seq(new WalkToSegment(4,8,false)); // leave passage
//		seq(new WalkToSegment(9,36).setIgnoreTrainers(true)); // enter vermillion
//		seq(new WalkToSegment(40,14)); // leave vermillion
//		seq(new WalkToSegment(44,9)); // walk up to trainer
//		seq(new WalkToSegment(45,9)); // walk up to trainer

//		save("uf3");
//		load("uf3");
//
//		seq(new InitFightSegment(1)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[3][1] = 1; // 1x mega punch crit
//			kems.numExpGainers = 1; // no level up
//			kems.onlyPrintInfo = false;
//			seq(kems); // voltorb
//		}
//		seq(NewEnemyMonSegment.any()); // next mon
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[3][1] = 1; // 1x mega punch crit
//			kems.numExpGainers = 1; // no level up
//			kems.onlyPrintInfo = false;
//			seq(kems); // magnemite
//		}
//		seq(new EndFightSegment(1)); // player defeated enemy
//
//		save("uf4");
		load("uf4");

		seq(new WalkToSegment(46, 9));
		seq(new WalkToSegment(48, 8));
		seq(new WalkToSegment(50, 8,false));
		seq(new WalkToSegment(8, 4,false));
		seq(new WalkToSegment(60, 8));
		seq(new WalkToSegment(-1, 62));
		seq(new WalkToSegment(57, 8,false));
		seq(new WalkToSegment(-1, 4,false));
		seq(new WalkToSegment(-1, 6));
		seq(new WalkToSegment(15, 13));
		seq(new WalkToSegment(3,7));
		seq(new WalkToSegment(3,6));
		seq(new OverworldInteract(1)); // trade
		seq(new SkipTextsSegment(1)); // trade spearow farfetchd
		seq(new SkipTextsSegment(1, true)); // trade spearow farfetchd
		seq(Segment.scroll(2));
		seq(Segment.repress(Move.A));
		seq(new SkipTextsSegment(1)); // trade spearow farfetchd
		seq(new SkipTextsSegment(1)); // trade traded
		seq(new TextSegment());
		seq(new SkipTextsSegment(1)); // thanks
		seq(new WalkToSegment(3,8, false));

//		seq(new WalkToSegment(21, -1)); // walk up
//		seq(new WalkToSegment(11, 32)); // walk up to trainer
//		seq(new MoveSegment(new OverworldInteract(7))); // talk to trainer 1

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
//		seq(new MoveSegment(new OverworldInteract(6))); // talk to trainer 2
//
//		save("nb2a");
//		load("nb2a");
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
//		seq(new MoveSegment(new OverworldInteract(5))); // talk to trainer 3
//
//		save("nb3a");
//		load("nb3a");
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
//		seq(new MoveSegment(new OverworldInteract(4))); // talk to trainer 4
//
//		save("nb4a");
//		load("nb4a");
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
////			kems.attackCount[0][0] = 1; // 1x ember
////			kems.attackCount[0][1] = 1; // 1x ember crit (better: 2x mega punch)
//			kems.attackCount[3][0] = 2; // 2x mega punch
//			kems.numExpGainers = 1; // no level up
//			kems.onlyPrintInfo = false;
//			seq(kems); // nidoF
//		}
//		seq(new EndFightSegment(1)); // player defeated enemy
//
//		seq(new WalkToSegment(11, 20)); // walk up to trainer
//		seq(new MoveSegment(new OverworldInteract(3))); // talk to trainer 5
//		
//		save("nb5a");
//		load("nb5a");
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
//		save("nb6a");
//		load("nb6a");
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
//		seq(new SkipTextsSegment(3)); // after rocket battle texts
//
//		save("nb7a");
//		load("nb7a");
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
//		save("nb8a");
//		load("nb8a");
//
//		seq(new WalkToSegment(9, 5)); // align with grass
//		seq(new WalkToSegment(8, 5)); // align with grass
//		seq(new EncounterAndCatchSegment(112, 8, Move.LEFT)); // weedle
//		seq(new WalkToSegment(7, 4)); // align with grass
//		seq(new WalkToSegment(6, 4)); // align with grass
//		seq(new EncounterAndCatchSegment(new CheckEncounterMetric(123, 8).withSpcDV(0,1,2,3,4), Move.DOWN)); // caterpie
//
//		save("nb9a");
//		load("nb9a");
//
//		seq(new WalkToSegment(15, 7)); // engage hiker
//		seq(new InitFightSegment(2)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.enemyMoveDesc = new EnemyMoveDesc[]{EnemyMoveDesc.missWith(new CheckLowerStatEffectMisses(), 103)}; // screech
//			kems.attackCount[0][1] = 2; // 2x ember crit
//			kems.numExpGainers = 2; // level up to 21
//			kems.onlyPrintInfo = false;
//			seq(kems); // Onix
//		}
//		seq(new EndFightSegment(1)); // player defeated enemy
//
//		seq(new WalkToSegment(18,7)); // walk up to trainer 2
//		seq(new WalkToSegment(18,8,false)); // walk up to trainer 2
//		seq(new OverworldInteract(4)); // talk to trainer 2
//
//		save("nb10a");
//		load("nb10a");
//
//		seq(new InitFightSegment(1)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[0][1] = 1; // 1x ember crit
//			kems.numExpGainers = 1; // no level up
//			kems.onlyPrintInfo = false;
//			seq(kems); // nidoM
//		}
//		seq(NewEnemyMonSegment.any()); // next mon
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[0][1] = 1; // 1x ember crit
//			kems.numExpGainers = 1; // no level up
//			kems.onlyPrintInfo = false;
//			seq(kems); // nidoF
//		}
//		seq(new EndFightSegment(1)); // player defeated enemy
//
//		seq(new WalkToSegment(24,6)); // walk up to trainer 3
//
//		save("nb11a");
//		load("nb11a");
//
//		seq(new InitFightSegment(2)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[0][0] = 1; // 1x ember
//			kems.numExpGainers = 1; // no level up
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
//		seq(new EndFightSegment(1)); // player defeated enemy
//
//		seq(new WalkToSegment(35,4)); // walk up to trainer 4
//		seq(new WalkToSegment(36,4)); // walk up to trainer 4
//		seq(new OverworldInteract(6)); // talk to trainer 4
//
//		save("nb12a");
//		load("nb12a");
//
//		seq(new InitFightSegment(2)); // start fight
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[0][0] = 1; // 1x ember
//			kems.numExpGainers = 1; // no level up
//			kems.onlyPrintInfo = false;
//			seq(kems); // oddish
//		}
//		seq(NewEnemyMonSegment.any()); // next mon
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[0][1] = 1; // 1x ember crit (better(?) 1xmega punch)
//			kems.numExpGainers = 2; // level up to 22
//			kems.onlyPrintInfo = false;
//			seq(kems); // pidgey
//		}
//		seq(NewEnemyMonSegment.any()); // next mon
//		{
//			KillEnemyMonSegment kems = new KillEnemyMonSegment();
//			kems.attackCount[0][0] = 1; // 1x ember
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
//		save("nb13a");
//		load("nb13a");
//
//		seq(Move.START);
//		seq(Segment.scroll(2)); // items
//		seq(Move.A);
//		seq(Move.SELECT); // pokeball
//		seq(Segment.scrollFast(5));
//		seq(Move.SELECT); // nugget
//		seq(Segment.scrollFast(-4));
//		seq(Move.A);
//		seq(Segment.repress(Move.A));
//		
//		seq(Segment.skip(2));
//		seq(new WalkToSegment(13, 15));
//		seq(new WalkToSegment(1, 4));
//		seq(new WalkToSegment(1, 3));
//		seq(new OverworldInteract(2));
//		seq(new SkipTextsSegment(3));
//		seq(Move.START);
//		seq(Move.B);
//		seq(new WalkToSegment(2, 8, false));
//		seq(new WalkToSegment(20, -1));
//		
//		seq(Move.START);
//		
//		save("nb14a");
//		load("nb14a");
//
//		seq(new SkipTextsSegment(1)); // wild missingno
//		seq(new TextSegment()); // go A
//		seq(Move.DOWN); // items
//		seq(Move.A); // items
//		seq(Segment.scrollFast(5)); // pokeball
//		seq(Move.SELECT);
//		seq(Segment.scrollFast(-3)); // rare candy
//		seq(Move.SELECT);
//		{
//			seq(new BallSuccessSegment()); // pokeball
//			seq(new SkipTextsSegment(4)); // cought, new dex data
//			seq(Segment.press(Move.A)); // skip dex
//			seq(Segment.press(Move.B)); // skip dex
//			seq(new SkipTextsSegment(2)); // no nickname
//			seq(new SkipTextsSegment(2)); // transferred to PC
//		}
	}
}
