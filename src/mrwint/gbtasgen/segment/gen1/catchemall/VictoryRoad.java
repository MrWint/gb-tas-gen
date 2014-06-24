package mrwint.gbtasgen.segment.gen1.catchemall;

import java.awt.TextArea;

import mrwint.gbtasgen.metric.CheckEncounterMetric;
import mrwint.gbtasgen.metric.gen1.CheckEscapeSuccess;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.gen1.OverworldInteract;
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
//		seq(new WalkToSegment(2, 2));
//		seq(new EncounterAndCatchSegment(119, Move.DOWN)); // Venomoth
//		seq(new WalkToSegment(2, 0));
//		seq(new WalkToSegment(2, 1));
//		seq(new EncounterAndCatchSegment(107, Move.RIGHT)); // Zubat
//		seq(new WalkToSegment(5, 1));
//		seq(new EncounterAndCatchSegment(130, Move.RIGHT)); // Golbat
//		seq(new WalkToSegment(8, 1));
//		seq(new EncounterAndCatchSegment(169, Move.RIGHT)); // Geodude
//		seq(new WalkToSegment(11, 1));
//		seq(new EncounterAndCatchSegment(106, Move.RIGHT)); // Machop
//		seq(new WalkToSegment(12, 3));
//		seq(new EncounterAndCatchSegment(41, Move.DOWN)); // Machoke
//		seq(new WalkToSegment(12, 6));
//		seq(new EncounterAndCatchSegment(39, Move.DOWN)); // Graveler
//		seq(new WalkToSegment(10, 7));
//		seq(new EncounterAndCatchSegment(34, Move.LEFT)); // Onix
//		seq(new WalkToSegment(9, 5));
//		seq(new EncounterAndCatchSegment(145, Move.RIGHT)); // Marowak
//
//		seq(new OverworldInteract(6)); // Moltres
//		seq(new SkipTextsSegment(1)); // Gyaoo
//		seq(new CatchMonSegment(0));
//
//		save("vr2");
//		load("vr2");
//
//		seq(Move.START);
//		seq(Segment.scroll(2)); // items
//		seq(Move.A);
//		seq(Segment.scrollFast(2)); // rare candy
//		seq(new UseRareCandySegment(1, 0)); // charmeleon L33
//		seq(new CancelMoveLearnSegment()); // slash
//		seq(new UseRareCandySegment(3, 0)); // charmeleon L36
//		seq(new TextSegment());
//		seq(new TextSegment()); // evolution
//		seq(new CancelMoveLearnSegment()); // slash
//		seq(new UseRareCandySegment(1, -2)); // weedle L9
//		seq(new TextSegment());
//		seq(new TextSegment()); // evolution
//		seq(new UseRareCandySegment(1, 0)); // kakuna L10
//		seq(new TextSegment());
//		seq(new TextSegment()); // evolution
//		seq(Segment.scrollFast(1)); // moon stone
//		seq(Move.A);
//		seq(Segment.repress(Move.A));
//		seq(Segment.scroll(-2)); // clefairy
//		seq(Move.A);
//		seq(new TextSegment());
//		seq(new TextSegment()); // evolution
//		seq(Segment.scrollFast(2)); // escape rope
//		seq(new SwapWithSegment(9)); // 15th slot
//		seq(Move.A);
//		seq(Segment.repress(Move.A));
//		seq(Segment.skip(2));
//		seq(new WalkToSegment(12, 4));
//		seq(Move.START);
//		seq(Move.A);
//		seq(Segment.scrollFast(6)); // JACK
//		seq(Move.A);
//		seq(Segment.repress(Move.A));
//		seq(Move.B);
//		seq(Segment.repress(Move.B));
//		seq(Move.START);
//		seq(new WalkToSegment(18, -1));
//		seq(new WalkToSegment(8, 31).setIgnoreTrainers(true));
//		seq(new WalkToSegment(10, 31).setIgnoreTrainers(true));
//		seq(Move.START);
//		seq(Segment.scroll(-1)); // mons
//		seq(Move.A);
//		seq(Segment.scroll(1)); // abra
//		seq(Move.A);
//		seq(Segment.repress(Move.A)); // teleport
//		seq(new TextSegment());
//
//		save("vr3");
		load("vr3");

		seq(new WalkToSegment(11, 3)); // enter center
		seq(new WalkToSegment(13,4));
		seq(new WalkToSegment(13,3, false)); // correct facing direction
		seq(Segment.press(Move.A)); // use PC
		seq(new SkipTextsSegment(1)); // booted PC
		seq(Segment.press(Move.A)); // someones PC
		seq(new SkipTextsSegment(2)); // someones PC, mon storage system
		seq(Segment.scroll(1)); // deposit
		seq(new DepositMonSegment(0)); // charizard
		seq(new DepositMonSegment(0)); // jigglypuff
		seq(new DepositMonSegment(0)); // clefable
		seq(new DepositMonSegment(1)); // beedrill
//		seq(new DepositMonSegment(1)); // dux
		seq(Segment.menu(Move.UP)); // withdraw
		seq(new WithdrawMonSegment(11)); // Caterpie
		seq(Segment.menu(Move.DOWN));
		seq(Segment.menu(Move.DOWN));
		seq(Segment.menu(Move.DOWN)); // change box
		seq(Segment.menu(Move.A)); // change box
		seq(new SkipTextsSegment(2)); // data saved
		seq(new SkipTextsSegment(1, true)); // ok
		seq(Segment.menu(Move.DOWN)); // switch to box 2
		seq(Segment.menu(Move.A)); // switch to box 2
		seq(new ResetAndContinueSegment()); // quit and continue

		seq(new WalkToSegment(4,8,false)); // leave center
		seq(new WalkToSegment(23, 13)); // enter mart
		seq(Segment.repress(Move.START));
		seq(Segment.scroll(2)); // items
		seq(Move.A);
		seq(Segment.scrollFast(2));
		seq(new UseRareCandySegment(1, -1)); // caterpie L9
		seq(new TextSegment());
		seq(new TextSegment()); // evolution
		seq(new UseRareCandySegment(1, 0)); // metapod L10
		seq(new TextSegment());
		seq(new TextSegment()); // evolution
		seq(Segment.scrollFast(15));
		seq(new SwapWithSegment(18));
		seq(new TossItemSegment(-34)); // warp 34:2
		seq(Segment.repress(Move.B));
		seq(Move.START);
		seq(new WalkToSegment(3, 8, false));

		save("vr4");
//		load("vr4");

		seq(new WalkToSegment(5, 35)); // goto guard
		seq(new SkipTextsSegment(5));
		seq(new WalkToSegment(11, 46)); // goto grass
		seq(new EncounterAndCatchSegment(35, Move.UP));
		seq(new WalkStep(Move.UP, true));
		seq(new WalkStep(Move.LEFT, true));
		seq(new EncounterAndCatchSegment(76, Move.DOWN)); // ditto
		{
			seq(new WalkStep(Move.RIGHT, true));
			seq(new WalkStep(Move.UP, true));
			delay(new SeqSegment() {
				@Override
				protected void execute() {
					seq(new WalkStep(Move.LEFT, false));
					seq(new CheckEncounterMetric(76, 0)); // ditto
				}
			});
			seq(new SkipTextsSegment(1));
			seq(new TextSegment());
			seq(Move.RIGHT);
			seq(Move.A);
			seq(Segment.scroll(2));
			seq(Move.A);
			seq(Segment.repress(Move.A)); // switch for butterfree
			seq(new TextSegment()); // come back
			seq(Segment.skip(1)); // go
			seq(new TextSegment()); // go
			seq(new SkipTextsSegment(2)); // transformed into butterfree
			seq(Move.DOWN); // items
			seq(Move.RIGHT); // run
			delay(new CheckEscapeSuccess(Move.A));
			seq(Move.A); // ensured escape
			seq(new SkipTextsSegment(1)); // got away safely
		}
		seq(Segment.repress(Move.START));
		seq(Segment.scroll(1)); // mons
		seq(Move.A);
		seq(Segment.repress(Move.A)); // abra
		seq(Segment.repress(Move.A)); // teleport
		seq(new TextSegment()); // teleport back

		save("vr5");
//		load("vr5");

		seq(Segment.skip(1));
		seq(new WalkToSegment(18, -1)); // trigger tfly
		seq(Move.START);
		seq(new CatchMonSegment(0)); // Mew

		save("vr6");
	}
}
