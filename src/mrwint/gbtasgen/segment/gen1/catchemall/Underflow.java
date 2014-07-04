package mrwint.gbtasgen.segment.gen1.catchemall;

import mrwint.gbtasgen.metric.gen1.CheckEscapeSuccess;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.move.WriteMemory;
import mrwint.gbtasgen.move.gen1.OverworldInteract;
import mrwint.gbtasgen.move.gen1.WalkStep;
import mrwint.gbtasgen.segment.PokecenterSegment;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.TextSegment;
import mrwint.gbtasgen.segment.WalkToSegment;
import mrwint.gbtasgen.segment.fight.EndFightSegment;
import mrwint.gbtasgen.segment.fight.InitFightSegment;
import mrwint.gbtasgen.segment.fight.KillEnemyMonSegment;
import mrwint.gbtasgen.segment.fight.NewEnemyMonSegment;
import mrwint.gbtasgen.segment.gen1.common.BuyItemSegment;
import mrwint.gbtasgen.segment.gen1.common.CancelMoveLearnSegment;
import mrwint.gbtasgen.segment.gen1.common.TossItemSegment;
import mrwint.gbtasgen.segment.gen1.common.UseRareCandySegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
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
////		save("uf1");
////		load("uf1");
////
//		seq(new WalkToSegment(28,36)); // enter route 5
//		seq(new WalkToSegment(17,27)); // enter passage

//		seq(new WalkToSegment(4,4)); // enter passage
//		seq(new WalkStep(Move.LEFT, false));
//		seq(new MoveSegment(new PressButton(Move.A), 0));
//		seq(new TextSegment()); // found Full restore
//		seq(new WalkToSegment(2,41)); // walk passage
//		seq(new WalkToSegment(4,8,false)); // leave passage
//
//		seq(new WalkToSegment(5,15)); // walk up to trainer
//		seq(new WalkToSegment(4,15)); // enter trainer-fly tile
//		seq(new PressButton(Move.START),0); // start trainer fly
////
////		save("uf2");
////		load("uf2");
////
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
//		seq(Segment.scroll(2)); // Abra
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
////
////		save("uf3");
////		load("uf3");
////
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
////
////		save("uf4");
////		load("uf4");
//
//		seq(new WalkToSegment(46, 9));
//		seq(new WalkToSegment(48, 8));
//		seq(new WalkToSegment(50, 8,false));
//		seq(new WalkToSegment(8, 4,false));
//		seq(new WalkToSegment(60, 8));
//		seq(new WalkToSegment(-1, 62));
//		seq(new WalkToSegment(57, 8,false));
//		seq(new WalkToSegment(-1, 4,false));
//		seq(new WalkToSegment(-1, 6));
//		seq(new WalkToSegment(15, 13));
//		seq(new WalkToSegment(3,7));
//		seq(new WalkToSegment(3,6));
//		seq(new OverworldInteract(1)); // trade
//		seq(new SkipTextsSegment(1)); // trade spearow farfetchd
//		seq(new SkipTextsSegment(1, true)); // trade spearow farfetchd
//		seq(Segment.scroll(1));
//		seq(Segment.repress(Move.A));
//		seq(new SkipTextsSegment(1)); // trade spearow farfetchd
//		seq(new SkipTextsSegment(1)); // trade traded
//		seq(new TextSegment());
//		seq(new SkipTextsSegment(1)); // thanks
//		seq(Move.START);
//		seq(Move.B);
//		seq(new WalkToSegment(3, 8, false));
//		seq(new WalkToSegment(18, -1));
//		seq(Move.START);
//		seq(new SkipTextsSegment(1)); // wild missingno
//		seq(new TextSegment());
//		seq(Move.DOWN);
//		seq(Move.RIGHT);
//		delay(new CheckEscapeSuccess(Move.A));
//		seq(Move.A); // ensured escape
//		seq(new SkipTextsSegment(1)); // got away safely
//
////		save("uf5");
////		load("uf5");
//		
//		seq(new WalkToSegment(8, 36));
//		seq(new WalkToSegment(11 ,3));
//		seq(new PokecenterSegment(false));
//		seq(new WalkToSegment(40,14)); // leave vermilion
//		seq(new WalkToSegment(50, 8, false)); // enter gate
//		seq(new WalkToSegment(8, 4, false)); // leave gate
//		seq(new WalkToSegment(60, 8)); // enter route 12
//		seq(new WalkToSegment(14, 52)); // align
//		seq(new WalkToSegment(13, 52)); // enter engage range
//		seq(new PressButton(Move.START), 0);
//		seq(Segment.scroll(2)); // items
//		seq(Move.A);
//		seq(Segment.scrollFast(5)); // rare candy
//		seq(Move.SELECT);
//		seq(Segment.scrollFast(-4)); // escape rope
//		seq(Move.SELECT);
//		seq(Segment.scrollFast(-1)); // nugget
//		seq(new TossItemSegment(0)); // toss all
//		seq(new UseRareCandySegment(8, 0)); // charmeleon to lvl 32 (avoid learning slash yet)
//		seq(Segment.repress(Move.B));
//		seq(Segment.scroll(-1)); // mons
//		seq(Move.A);
//		seq(Segment.scroll(1)); // abra
//		seq(Move.A);
//		seq(Segment.repress(Move.A));
//		seq(new SkipTextsSegment(1)); // teleport
//		seq(new WalkToSegment(40,14)); // leave vermilion
//		seq(new WalkToSegment(50, 8, false)); // enter gate
//		seq(new WalkToSegment(8, 4, false)); // leave gate
//		seq(new WalkToSegment(60, 8)); // enter route 12
//		seq(new WalkToSegment(10, 21).setIgnoreTrainers(true)); // enter house
//		seq(new WalkToSegment(4, -1, false)); // leave house
//		seq(new WalkToSegment(9, -1)); // enter lavender
//		seq(new WalkToSegment(-1, 8)); // enter Route 8
//		seq(new WalkToSegment(49, 12)); // engage lass
//
////		save("uf6");
////		load("uf6");
//		
//		seq(new InitFightSegment(2)); // start fight
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
//		seq(new EndFightSegment(2)); // player defeated enemy
//
////		save("uf7");
////		load("uf7");
//
//		seq(new WalkToSegment(13, 3)); // enter passage
//		seq(new WalkToSegment(4, 4)); // enter passage
//		seq(new WalkToSegment(2, 5)); // walk passage
//		seq(new WalkToSegment(4, 8, false)); // exit passage
//		seq(new WalkToSegment(-1, 3)); // enter celadon
//		seq(new WalkToSegment(10, 13)); // enter mart
//		seq(new WalkToSegment(12, 1)); // 2nd floor
//		seq(new WalkToSegment(16, 1)); // 3rd floor
//		seq(new WalkToSegment(12, 1)); // 4th floor
//		seq(new WalkToSegment(5, 6, false)); // goto counter
//
////		save("uf8");
////		load("uf8");
//
//		seq(new OverworldInteract(1));
//		seq(new TextSegment()); // may I help you
//		seq(Move.DOWN); // sell
//		seq(Move.A);
//		seq(new TextSegment(Move.B)); // what sell
//		seq(Move.A); // rare candy
//		seq(Segment.scroll(2)); // all but 1
//		seq(Move.A);
//		seq(new SkipTextsSegment(1, true)); // sell
//		seq(Move.B);
//		seq(new TextSegment(Move.B)); // anything else?
//		seq(Move.A); // buy
//		seq(new TextSegment()); // take your time
//		seq(Segment.scrollFast(4));
//		seq(new BuyItemSegment(3+1)); // Leaf stone x3 + 1
//		seq(new BuyItemSegment(1+3)); // Thunder stone x1 + 3
//		seq(Segment.scrollFast(1));
//		seq(new BuyItemSegment(3+1)); // Water stone x3 + 1
//		seq(Segment.scrollFast(-1));
//		seq(new BuyItemSegment(3+1)); // Fire stone x3 + 1
//		seq(Segment.repress(Move.B)); // buy
//		seq(new SkipTextsSegment(2)); // anything else + thank you
//		seq(new WalkToSegment(16, 1)); // 5th floor
//		seq(new WalkToSegment(12, 1)); // roof
//		seq(new WalkToSegment(12,2,false)); // go to vending machine
//		seq(new OverworldInteract(5));
//		seq(new SkipTextsSegment(1)); // vending machine
//		seq(Move.DOWN); // lemonade
//		seq(Move.A); // buy water
//		seq(new SkipTextsSegment(1)); // popped out
//		seq(new WalkToSegment(15,2)); // 5th
//		seq(new WalkToSegment(16,1)); // 4th
//		seq(new WalkToSegment(12,1)); // 3rd
//		seq(new WalkToSegment(16,1)); // 2nd
//		seq(new WalkToSegment(12,1)); // 1st
//		seq(new WalkToSegment(16,8,false)); // out
//
////		save("uf9");
////		load("uf9");
//
//		seq(new WalkToSegment(50,11)); // leave celadon
//		seq(new WalkToSegment(12,10,false)); // enter house
//		seq(Segment.repress(Move.START));
//		seq(Segment.scroll(2)); // items
//		seq(Move.A);
//		for(int i=0;i<13;i++)
//			seq(new TossItemSegment(0)); // toss all items
//		seq(Move.A); // cancel
//		seq(Move.START);
//		seq(new WalkToSegment(3, 4));
//		seq(new SkipTextsSegment(15)); // saffron guard speech
	}
}
