package mrwint.gbtasgen.segment.gen1.catchemall;

import java.awt.TextArea;

import mrwint.gbtasgen.metric.CheckEncounterMetric;
import mrwint.gbtasgen.metric.gen1.CheckEscapeSuccess;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.move.gen1.OverworldInteract;
import mrwint.gbtasgen.move.gen1.PrepWalkStep;
import mrwint.gbtasgen.move.gen1.WalkStep;
import mrwint.gbtasgen.segment.BallSuccessSegment;
import mrwint.gbtasgen.segment.CatchMonSegment;
import mrwint.gbtasgen.segment.ResetAndContinueSegment;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.TextSegment;
import mrwint.gbtasgen.segment.WalkToSegment;
import mrwint.gbtasgen.segment.gen1.common.CancelMoveLearnSegment;
import mrwint.gbtasgen.segment.gen1.common.DepositMonSegment;
import mrwint.gbtasgen.segment.gen1.common.EncounterAndCatchSegment;
import mrwint.gbtasgen.segment.gen1.common.NamingSegment;
import mrwint.gbtasgen.segment.gen1.common.SwapWithSegment;
import mrwint.gbtasgen.segment.gen1.common.TossItemSegment;
import mrwint.gbtasgen.segment.gen1.common.UseBikeSegment;
import mrwint.gbtasgen.segment.gen1.common.UseRareCandySegment;
import mrwint.gbtasgen.segment.gen1.common.WithdrawMonSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.segment.util.SleepSegment;

public class VictoryRoad extends SeqSegment {

	@Override
	public void execute() {

//		seq(new WalkToSegment(2, 4)); // go to door
//		
//		seq(Segment.skip(1));
//		seq(Move.START);
//		seq(Segment.scroll(2)); // save
//		seq(Move.A);
//		seq(new SkipTextsSegment(1)); // don't save
//		seq(Segment.skip(1));
//		seq(Move.START);
//		seq(Segment.scroll(-2)); // items
//		seq(Move.A);
//		seq(new SwapWithSegment(2));
//		seq(Segment.scrollFast(4)); // SS Ticket
//		seq(new SwapWithSegment(22)); // badges
//		seq(Segment.scrollFast(-1)); // options
//		seq(new TossItemSegment(1)); // fastest text
//		seq(Segment.scrollFast(-4)); // rival name jack
//		seq(new SwapWithSegment(-17)); // swap with ex-SS Ticket
//		seq(Segment.scrollFast(10)); // 17th slot
//		seq(new SwapWithSegment(5)); // money (Full Heal)
//		seq(Segment.scrollFast(-6)); // 16th slot
//		seq(new SwapWithSegment(-14)); // swap with 2nd slot
//		seq(new SwapWithSegment(29)); // swap with TID bike
//		seq(Segment.scrollFast(4)); // X abs
//		seq(new SwapWithSegment(1)); // swap with saved ID
//		seq(new TossItemSegment(58)); // set to 198
//		seq(Segment.repress(Move.B));
//		seq(Move.START);
//		seq(new WalkToSegment(-1, 3, false)); // wrong warp
//		
//		save("vr1");
//		load("vr1");
//		
//		seq(new WalkToSegment(2, 1));
//		seq(new EncounterAndCatchSegment(106, Move.DOWN)); // Machop
//		save("vr2");
////		load("vr2");
//		
//		seq(new UseBikeSegment());
//		
//		seq(new WalkToSegment(2, 3));
//		seq(new WalkToSegment(2, 2));
//		seq(new EncounterAndCatchSegment(41, Move.UP)); // Machoke
//		save("vr3");
//		load("vr3");
//		seq(new WalkToSegment(2, 3));
//		seq(new EncounterAndCatchSegment(169, Move.DOWN)); // Geodude
//		save("vr4");
//		load("vr4");
//		seq(new WalkToSegment(2, 2));
//		seq(new EncounterAndCatchSegment(107, Move.UP)); // Zubat
//		save("vr5");
//		load("vr5");
//		seq(new WalkToSegment(2, 3));
//		seq(new EncounterAndCatchSegment(119/*119*/, Move.UP)); // Venomoth
//		seq(new WalkToSegment(2, 0));
//		save("vr6");
//		load("vr6");
//		seq(new WalkToSegment(3, 1));
//		seq(new EncounterAndCatchSegment(34, Move.RIGHT)); // Onix
//		save("vr7");
//		load("vr7");
//		seq(new WalkToSegment(6, 1));
//		seq(new EncounterAndCatchSegment(145, Move.RIGHT)); // Marowak
//		save("vr8");
//		load("vr8");
//		seq(new WalkToSegment(12, 5));
//		seq(new EncounterAndCatchSegment(39, Move.DOWN)); // Graveler
//		save("vr9");
//		load("vr9");
//		
//		seq(new WalkToSegment(9, 6));
//		for(int i=0;i<5;i++) {
//			seq(new PressButton(0), 0);
//			seq(new PrepWalkStep(Move.DOWN));
//			seq(new PressButton(0), 0);
//			seq(new PrepWalkStep(Move.RIGHT));
//			seq(new PressButton(0), 0);
//			seq(new PrepWalkStep(Move.UP));
//			seq(new PressButton(0), 0);
//			seq(new PrepWalkStep(Move.LEFT));
//		}
//		
//		seq(new WalkToSegment(10, 6));
//		seq(new WalkToSegment(10, 4));
//		seq(new WalkToSegment(12, 4));
//		seq(new WalkToSegment(9, 5));
//		seq(new WalkToSegment(13, 5));
//		seq(new WalkToSegment(9, 6));
//		seq(new WalkToSegment(9, 5));
//		seq(new WalkToSegment(13, 4));
//		seq(new WalkToSegment(13, 5));
//		seq(new WalkToSegment(11, 3));
//		seq(new EncounterAndCatchSegment(130, Move.RIGHT)); // Golbat
//		save("vr10a");
//		load("vr10");
//
//		seq(new OverworldInteract(6)); // Moltres
//		seq(new SkipTextsSegment(1)); // Gyaoo
//		seq(new CatchMonSegment(0));
//
//		save("vr2");
//		load("vr2");
//
//		seq(Move.START);
//		seq(Segment.scrollA(2)); // items
//		seq(Segment.scrollFast(2)); // rare candy
//		seq(new UseRareCandySegment(1, 0)); // charmeleon L33
//		seq(new CancelMoveLearnSegment()); // slash
//		seq(new UseRareCandySegment(3, 0)); // charmeleon L36
//		seq(new TextSegment());
//		seq(new TextSegment()); // evolution
//		seq(new CancelMoveLearnSegment()); // slash
//		seq(new UseRareCandySegment(1, 2)); // caterpie L9
//		seq(new TextSegment());
//		seq(new TextSegment()); // evolution
//		seq(new UseRareCandySegment(1, 0)); // butterfree L10
//		seq(new TextSegment());
//		seq(new TextSegment()); // evolution
//		seq(new UseRareCandySegment(1, 1)); // weedle L9
//		seq(new TextSegment());
//		seq(new TextSegment()); // evolution
//		seq(new UseRareCandySegment(1, 0)); // kakuna L10
//		seq(new TextSegment());
//		seq(new TextSegment()); // evolution
//		seq(Segment.scrollFast(3)); // escape rope
//		seq(new SwapWithSegment(9)); // 15th slot
//		seq(Move.A);
//		seq(Segment.repress(Move.A));
//		seq(Segment.skip(3));
//		seq(new PrepWalkStep(Move.RIGHT));
//		seq(Segment.skip(1));
//		seq(Segment.repress(Move.START));
//		seq(Move.A);
//		seq(Segment.scrollFastA(6)); // JACK
//		seq(Segment.repress(Move.A));
//		seq(Move.B);
//		seq(Segment.scrollFastA(-5)); // Bike
//		seq(new SkipTextsSegment(1)); // got on bike
//		seq(new WalkToSegment(18, -1));
//		seq(new WalkToSegment(8, 31).setIgnoreTrainers(true));
//		seq(new WalkToSegment(10, 31).setIgnoreTrainers(true));
//		seq(Move.START);
//		seq(Segment.scroll(-1)); // mons
//		seq(Move.A);
//		seq(Segment.scroll(-2)); // abra
//		seq(Move.A);
//		seq(Segment.repress(Move.A)); // teleport
//		seq(new TextSegment());
//
//		save("vr3");
//		load("vr3");
//
//		seq(new WalkToSegment(11, 3)); // enter center
//		seq(new WalkToSegment(13,4));
//		seq(new WalkToSegment(13,3, false)); // correct facing direction
//		seq(Segment.press(Move.A)); // use PC
//		seq(new SkipTextsSegment(1)); // booted PC
//		seq(Segment.press(Move.A)); // someones PC
//		seq(new SkipTextsSegment(2)); // someones PC, mon storage system
//		seq(Segment.scroll(1)); // deposit
//		seq(new DepositMonSegment(0)); // charizard
//		seq(new DepositMonSegment(2)); // beedrill
//		seq(new DepositMonSegment(2)); // Rhydon
//		seq(Segment.menu(Move.DOWN));
//		seq(Segment.menu(Move.DOWN)); // change box
//		seq(Segment.menu(Move.A)); // change box
//		seq(new SkipTextsSegment(2)); // data saved
//		seq(new SkipTextsSegment(1, true)); // ok
//		seq(Segment.menu(Move.DOWN)); // switch to box 2
//		seq(Segment.menu(Move.A)); // switch to box 2
//		seq(new ResetAndContinueSegment()); // quit and continue
//
//		seq(new WalkToSegment(4,8,false)); // leave center
//		seq(new WalkToSegment(23, 13)); // enter mart
//		seq(Segment.repress(Move.START));
//		seq(Segment.scrollA(2)); // items
//		seq(Segment.scrollFast(17));
//		seq(new SwapWithSegment(18));
//		seq(new TossItemSegment(-34)); // warp 34:2
//		seq(Segment.repress(Move.B));
//		seq(Move.START);
//		seq(new WalkToSegment(3, 8, false));
//
//		save("vr4");
//		load("vr4");
//
//		seq(new WalkToSegment(5, 35)); // goto guard
//		seq(new SkipTextsSegment(5));
//		seq(new WalkToSegment(11, 46)); // goto grass
////		seq(new EncounterAndCatchSegment(new CheckEncounterMetric(35, 38, 43), Move.UP)); // Fearow
//		seq(new EncounterAndCatchSegment(new CheckEncounterMetric(76, 38, 43), Move.UP)); // ditto
//		save("vr5a");
//		load("vr5a");
//		seq(new WalkStep(Move.RIGHT, true));
////		seq(new WalkStep(Move.RIGHT, true));
//		seq(new WalkStep(Move.UP, true));
////		seq(new WalkStep(Move.LEFT, true));
//		seq(new EncounterAndCatchSegment(35, Move.LEFT)); // Fearow
////		seq(new EncounterAndCatchSegment(76, Move.LEFT)); // ditto
//		save("vr6a");
//		load("vr6a");
//		{
//			seq(new WalkStep(Move.RIGHT, true));
////			seq(new WalkStep(Move.RIGHT, true));
//			seq(new WalkStep(Move.DOWN, true));
////			seq(new WalkStep(Move.LEFT, true));
//			delay(new SeqSegment() {
//				@Override
//				protected void execute() {
//					seq(new WalkStep(Move.LEFT, false));
//					seq(new CheckEncounterMetric(76)); // ditto
//				}
//			});
//			seq(new SkipTextsSegment(1));
//			seq(new TextSegment());
//			seq(Move.RIGHT);
//			seq(Move.A);
//			seq(Segment.scroll(1));
//			seq(Move.A);
//			seq(Segment.repress(Move.A)); // switch for butterfree
//			seq(new TextSegment()); // come back
//			seq(Segment.skip(1)); // go
//			seq(new TextSegment()); // go
//			seq(new SkipTextsSegment(2)); // transformed into butterfree
//			seq(Move.DOWN); // items
//			seq(Move.RIGHT); // run
//			delay(new CheckEscapeSuccess(Move.A));
//			seq(Move.A); // ensured escape
//			seq(new SkipTextsSegment(1)); // got away safely
//		}
//		seq(Segment.repress(Move.START));
//		seq(Segment.scroll(1)); // mons
//		seq(Move.A);
//		seq(Segment.repress(Move.A)); // abra
//		seq(Segment.repress(Move.A)); // teleport
//		seq(new TextSegment()); // teleport back
//
//		
//		seq(Segment.skip(1));
//		seq(new UseBikeSegment(1, 1));
//		seq(new WalkToSegment(18, -1)); // trigger tfly
//		seq(Move.START);
//		seq(new CatchMonSegment(0)); // Mew

//		save("vr7");
	}
}
