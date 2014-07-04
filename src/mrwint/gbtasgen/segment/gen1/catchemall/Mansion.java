package mrwint.gbtasgen.segment.gen1.catchemall;

import mrwint.gbtasgen.metric.CheckEncounterMetric;
import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.metric.gen1.CheckLowerStatEffectMisses;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.move.gen1.OverworldInteract;
import mrwint.gbtasgen.move.gen1.WalkStep;
import mrwint.gbtasgen.segment.BallSuccessSegment;
import mrwint.gbtasgen.segment.CatchMonSegment;
import mrwint.gbtasgen.segment.ResetAndContinueSegment;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.TextSegment;
import mrwint.gbtasgen.segment.WalkToSegment;
import mrwint.gbtasgen.segment.fight.KillEnemyMonSegment.CheckMoveOrderMetric;
import mrwint.gbtasgen.segment.gen1.common.CancelMoveLearnSegment;
import mrwint.gbtasgen.segment.gen1.common.DepositMonSegment;
import mrwint.gbtasgen.segment.gen1.common.EncounterAndCatchSegment;
import mrwint.gbtasgen.segment.gen1.common.SwapWithSegment;
import mrwint.gbtasgen.segment.gen1.common.TossItemSegment;
import mrwint.gbtasgen.segment.gen1.common.UseBikeSegment;
import mrwint.gbtasgen.segment.gen1.common.UseEvoStoneSegment;
import mrwint.gbtasgen.segment.gen1.common.UseRareCandySegment;
import mrwint.gbtasgen.segment.gen1.common.WithdrawMonSegment;
import mrwint.gbtasgen.segment.util.DelayMoveSegment.PressButtonFactory;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;

public class Mansion extends SeqSegment {

	@Override
	public void execute() {

//		seq(new WalkToSegment(8, 36)); // enter vermilion
//		seq(new WalkToSegment(11, 3)); // enter center
//
//		seq(Segment.repress(Move.START));
//		seq(Segment.scrollA(1)); // mons
//		seq(Segment.repress(Move.A)); // Abra
//		seq(Segment.scrollAF(2)); // switch
//		seq(Segment.scrollAF(-3)); // ditto
//		seq(Move.B);
//		seq(Segment.scrollA(1)); // items
//		seq(Segment.scrollFast(7)); // Dig
//		seq(new SwapWithSegment(7)); // swap with rare candy
//		seq(Move.A);
//		seq(Segment.repress(Move.A)); // use
//		seq(new SkipTextsSegment(2)); // booted up, contains dig
//		seq(new SkipTextsSegment(1, true)); // teach dig
//		seq(Segment.skip(1));
//		seq(Segment.scrollAF(2)); // mew
//		seq(new SkipTextsSegment(1)); // mew learned dig
//		
//		seq(Segment.scrollFast(5)); // 0x0
//		seq(new SwapWithSegment(30)); // swap with saved map
//		seq(new TossItemSegment(-8)); // warp 8:0
//		seq(Segment.repress(Move.B));
//		seq(Move.START);
//		seq(new WalkToSegment(3, 8, false)); // wrong warp
//
//		seq(new WalkToSegment(6, 3)); // enter mansion
//		save("ma1");
//		load("ma1");
//		seq(new WalkToSegment(5, 26));
//		seq(new EncounterAndCatchSegment(33, Move.UP)); // Growlithe
//		save("ma2");
//		load("ma2");
//		seq(new WalkToSegment(5, 20));
//		{
//			delay(new SeqSegment() {
//				@Override
//				protected void execute() {
//					seq(new WalkStep(Move.UP, false));
//					seq(new CheckEncounterMetric(163, 34)); // ponyta
//				}
//			});
//			seq(new SkipTextsSegment(1));
//			seq(new TextSegment());
//			seq(Move.A); // fight
//			save("tmp");
//			load("tmp");
//			delay(new PressButtonFactory(Move.A, Metric.PRESSED_JOY), new SeqSegment() {
//				@Override
//				protected void execute() {
//					seq(new CheckMoveOrderMetric(false, new int[]{39 /*Tail whip*/}, Move.A));
//					seq(new PressButton(Move.A), 0); // attack
//					seq(new TextSegment(Move.A, false)); // used Tail whip
//					seq(new CheckLowerStatEffectMisses());
//					seq(Segment.wait(1)); // finish last text frame
//				}
//			});
//			seq(new SkipTextsSegment(1)); // but it failed
//			seq(Segment.skip(1));
//			seq(new SkipTextsSegment(2)); // Ditto transformed, into ponyta
//			seq(Move.A); // fight
//			seq(Move.SELECT); // 1st move
//			seq(Move.DOWN); // 2nd move
//			seq(Move.SELECT); // 2nd move
//			seq(Move.B); // back
//			seq(Move.DOWN | Move.A); // items
//			seq(new BallSuccessSegment());
//			seq(new SkipTextsSegment(4)); // cought, new dex data
//			seq(Segment.press(Move.A)); // skip dex
//			seq(Segment.press(Move.B)); // skip dex
//			seq(new SkipTextsSegment(2)); // no nickname
//			seq(new SkipTextsSegment(2)); // transferred to PC
//		}
//		save("ma3");
//		load("ma3");
//		seq(new WalkToSegment(5, 17));
//		seq(new EncounterAndCatchSegment(13, Move.UP)); // Grimer
//		save("ma4");
//		load("ma4");
////		seq(new WalkStep(Move.UP, true));
////		seq(new WalkStep(Move.UP, true));
//		{
//			seq(new WalkToSegment(7, 16));
//			seq(Move.A);
//			seq(new TextSegment()); // found moon stone
//		}
////		seq(new WalkToSegment(5, 14));
//		seq(new EncounterAndCatchSegment(55, Move.UP)); // Koffing
//		save("ma5");
//		load("ma5");
//		seq(new WalkToSegment(12, 8));
//		seq(new EncounterAndCatchSegment(136, Move.UP)); // Muk
//		save("ma6");
		load("ma6");
		seq(new WalkToSegment(12, 3));
		seq(new EncounterAndCatchSegment(143, Move.RIGHT)); // Weezing
//		seq(new WalkToSegment(12, 3));
//		seq(new WalkToSegment(13, 3)); // go to escape rope

		seq(Segment.repress(Move.START));
		seq(Segment.scrollA(2)); // items
		seq(Segment.scrollFast(7)); // rare candy
		seq(new SwapWithSegment(32)); // swap with text pointer
		seq(new TossItemSegment(-0x4a)); // 284a text pointer
		seq(Segment.repress(Move.B));
		seq(Move.START);
		seq(new OverworldInteract(2));
		seq(new SkipTextsSegment(2)); // speech
		seq(new CatchMonSegment(0));
		seq(Segment.repress(Move.START));
		seq(Segment.scrollA(1)); // mons
		seq(Segment.skip(1));
		seq(Segment.scrollAF(-1)); // mew
		seq(Segment.repress(Move.A)); // dig
		seq(Segment.skip(1));
		
		save("ma2");
//		load("ma2");
//		
//		seq(new WalkToSegment(11, 3)); // enter center
//		seq(Segment.repress(Move.START));
//		seq(Segment.scrollA(1)); // items
//		seq(Segment.scrollFast(30)); // 0xbd
//		seq(new SwapWithSegment(5)); // swap with saved map (1x5)
//		seq(new TossItemSegment(0xbd-0x8e)); // 80x8e
//		seq(Segment.scrollFast(38)); // ID
//		seq(new TossItemSegment(0x17)); // warp 8e:29 = 8e:e9
//
//		seq(Segment.repress(Move.B));
//		seq(Move.START);
//		seq(new WalkToSegment(3, 8, false)); // wrong warp
//
//		seq(Segment.repress(Move.START));
//		seq(Segment.scrollA(-1)); // mons
//		seq(Segment.repress(Move.A)); // mew
//		seq(Segment.repress(Move.A)); // dig
//		seq(Segment.skip(1));
//
//		seq(new WalkToSegment(40, 14)); // leave vermilion
//		seq(new WalkToSegment(4, 5)); // enter diglett's cave
//		seq(new WalkToSegment(4, 4)); // enter diglett's cave
//		seq(new WalkToSegment(36, 31));
//		seq(new EncounterAndCatchSegment(170, Move.LEFT, true)); // porygon
//
//		save("ma4");
//		load("ma4");
//		
//		seq(Segment.repress(Move.START));
//		seq(Move.B);
//		seq(new WalkToSegment(37, 31)); // go up
//		seq(new WalkToSegment(3, 7)); // go up
//		seq(Segment.skip(1));
//		seq(Segment.repress(Move.START));
//		seq(Segment.scrollA(2)); // items
//		seq(Segment.scrollFast(5)); // 1x5
//		seq(new SwapWithSegment(54)); // swap with Silph Scope
//		seq(Segment.scrollFastA(19)); // HM02
//		{
//			seq(Segment.repress(Move.A)); // use
//			seq(new SkipTextsSegment(2)); // booted up HM, contains xyz
//			seq(new SkipTextsSegment(1, true)); // learn
//			seq(Segment.scrollA(-1)); // mew
//			seq(new SkipTextsSegment(1)); // learned HM
//		}
//		seq(Segment.scrollFastA(-15)); // HM03
//		{
//			seq(Segment.repress(Move.A)); // use
//			seq(new SkipTextsSegment(2)); // booted up HM, contains xyz
//			seq(new SkipTextsSegment(1, true)); // learn
//			seq(Segment.repress(Move.A)); // mew
//			seq(new SkipTextsSegment(1)); // learned HM
//		}
//		seq(Segment.scrollFast(-32)); // Old amber
//		seq(new SwapWithSegment(4)); // swap with saved map (overridden by map script so set quantity to 22)
//		seq(Segment.repress(Move.B));
//		seq(Move.START);
//		seq(Segment.repress(Move.START));
//		seq(Move.A); // items
//		seq(new SwapWithSegment(-17)); // empty 0x0
//		seq(new SwapWithSegment(-11)); // mansion text pointer
//		seq(Segment.repress(Move.B));
//		seq(Move.START);
//		seq(new WalkToSegment(3, 8, false));
//		seq(new WalkToSegment(-1, 6));
//		seq(new WalkToSegment(23, 13));
//
//		seq(Segment.repress(Move.START));
//		seq(Move.A); // items
//		seq(Segment.scrollFast(12)); // empty 0x0
//		seq(new SwapWithSegment(16)); // swap with saved map
//		seq(new TossItemSegment(-0x29)); // warp 29:2
//		seq(Segment.repress(Move.B));
//		seq(Move.START);
//		seq(new WalkToSegment(3, 8, false)); // wrong warp
//		
//		seq(new WalkStep(Move.UP, false)); // walk up
//		seq(Segment.skip(1));
//		seq(Move.DOWN);
//		seq(Segment.skip(1));
//		seq(Segment.repress(Move.START));
//		seq(Move.A); // items
//		seq(Segment.scrollFast(1)); // tileset
//		seq(new TossItemSegment(6-3)); // dig anywhere
//		seq(Segment.scrollFastA(-30)); // JACK
//		seq(Segment.repress(Move.A));
//		seq(Move.B);
//		seq(Segment.repress(Move.B));
//		seq(Move.START);
//		seq(Move.UP);
//		seq(Segment.repress(Move.START));
//		seq(Segment.scrollA(-1)); // mons
//		seq(Segment.repress(Move.A)); // mew
//		seq(Segment.repress(Move.A)); // dig
//		seq(Segment.skip(1));
//
//		seq(new WalkToSegment(40, 14)); // leave vermilion
//		seq(new WalkToSegment(4, 5)); // enter diglett's cave
//		seq(new WalkToSegment(4, 4)); // enter diglett's cave
//		seq(new WalkToSegment(36, 31));
//		seq(new EncounterAndCatchSegment(105, Move.LEFT, true)); // Vaporeon
//		
//		save("ma5");
//		load("ma5");
//		
//		seq(Segment.repress(Move.START));
//		seq(Move.B);
//		seq(new WalkToSegment(37, 31)); // go up
//		seq(new WalkToSegment(3, 8, false)); // go up
//		seq(new WalkToSegment(12, 6));
//		seq(new EncounterAndCatchSegment(48, Move.RIGHT)); // Drowzee
//		seq(new WalkToSegment(13, 8));
//		seq(new EncounterAndCatchSegment(108, Move.DOWN)); // Ekans
//		seq(new WalkToSegment(13, 15));
//		seq(Segment.repress(Move.START));
//		seq(Segment.scrollA(2)); // mons
//		seq(Segment.scrollFastA(16)); // Full heal
//		seq(Segment.repress(Move.A));
//		seq(Segment.repress(Move.A)); // Ditto
//		seq(Move.B); // Ditto healed
//		seq(Segment.repress(Move.B));
//		seq(Move.START);
//		seq(new WalkToSegment(31, 16));
//		seq(new EncounterAndCatchSegment(44, Move.RIGHT, true)); // Hitmonchan
//		seq(new WalkToSegment(36, 17));
//		seq(Segment.repress(Move.START));
//		seq(Segment.scrollA(1)); // mons
//		seq(Move.B);
//		seq(Move.START);
//		seq(new WalkToSegment(37, 15));
//		seq(new EncounterAndCatchSegment(82, Move.RIGHT, true)); // Vulpix
//		seq(new WalkToSegment(49, 9));
//		seq(new WalkToSegment(50, 9, false));
//		
//		save("ma6");
//		load("ma6");
//		
//		seq(new WalkToSegment(7, 5));
//		seq(Segment.skip(1));
//		seq(Segment.repress(Move.START));
//		seq(Segment.scrollA(2)); // items
//		seq(Segment.scrollFast(31)); // ultraballx0
//		seq(new SwapWithSegment(4)); // saved map
//		seq(new TossItemSegment(256-212)); // 212:3
//		seq(Segment.repress(Move.B));
//		seq(Move.START);
//		seq(new WalkToSegment(8, 5, false)); // wrong warp
//
//		seq(new WalkToSegment(3, 5));
//		seq(new WalkToSegment(2, 5)); // face npc
//		seq(new OverworldInteract(1)); // talk
//		seq(new SkipTextsSegment(5));
//		seq(new TextSegment()); // Lapras
//		seq(new SkipTextsSegment(14)); // no nick, no room, rest of text
//
//		seq(new WalkToSegment(5, 7)); // three frame save
//		seq(Segment.repress(Move.START));
//		seq(Segment.scrollA(-1)); // mons
//		seq(Segment.scrollA(-1)); // mew
//		seq(Segment.repress(Move.A)); // dig
//
//		seq(new WalkToSegment(11, 3));
//		seq(Segment.repress(Move.START));
//		seq(Segment.scrollA(1)); // items
//		seq(new SwapWithSegment(-4)); // ultraballs
//		seq(Segment.scrollFast(-1)); // TID: 1xBD
//		seq(new SwapWithSegment(5)); // saved map (ultraballs)
//		seq(new TossItemSegment(0xbd-148)); // warp 148:0
//		seq(Segment.repress(Move.B));
//		seq(Move.START);
//		seq(new WalkToSegment(3,8, false)); // wrong warp
//
//		seq(new WalkToSegment(10, 16));
//		seq(new EncounterAndCatchSegment(25, Move.RIGHT)); // Gastly
//		
//		save("ma7");
//		load("ma7");
//
//		seq(Segment.repress(Move.START));
//		seq(Segment.scrollA(2)); // items
//		seq(Segment.scrollFast(17)); // Masterball x5
//		seq(new SwapWithSegment(53)); // swap with Super Rod
//		seq(Segment.repress(Move.B));
//		seq(Move.START);
//
//		seq(new WalkToSegment(11, 14));
//		seq(new EncounterAndCatchSegment(147, Move.UP)); // Haunter
//		seq(new WalkToSegment(11, 15));
//		seq(new EncounterAndCatchSegment(17, Move.DOWN)); // Cubone
//
//		seq(Segment.repress(Move.START));
//		seq(Segment.scrollA(1)); // mons
//		seq(Segment.scrollA(-1)); // mew
//		seq(Segment.repress(Move.A)); // dig
//		seq(Segment.skip(1));
//
//		seq(new WalkToSegment(12, 4));
//		seq(Segment.repress(Move.START));
//		seq(Segment.scrollA(1)); // items
//		seq(Segment.scrollA(6)); // JACK
//		seq(Segment.repress(Move.A)); // use
//		seq(Move.B);
//		seq(Segment.repress(Move.B));
//		seq(Move.START);
//		seq(new WalkToSegment(18, -1));
//		seq(new WalkToSegment(8, 31).setIgnoreTrainers(true));
//		seq(new WalkToSegment(10, 31).setIgnoreTrainers(true));
//		seq(Move.START);
//		seq(Segment.scrollA(-1)); // mons
//		seq(Segment.repress(Move.A)); // mew
//		seq(Segment.scrollA(1)); // fly
//		seq(Segment.scrollA(3)); // vermilion
//
//		seq(new WalkToSegment(11, 3));
//		seq(new WalkToSegment(13,4));
//		seq(new WalkToSegment(13,3, false)); // correct facing direction
//		seq(Segment.press(Move.A)); // use PC
//		seq(new SkipTextsSegment(1)); // booted PC
//		seq(Segment.press(Move.A)); // someones PC
//		seq(new SkipTextsSegment(2)); // someones PC, mon storage system
//		seq(Segment.scroll(1)); // deposit
//		seq(new DepositMonSegment(1)); // Butterfree
//		seq(new DepositMonSegment(1)); // Dux
//		seq(new DepositMonSegment(1)); // Fearow
//		seq(Segment.menu(Move.UP)); // withdraw
//		seq(new WithdrawMonSegment(4)); // Vulpix
//		seq(new WithdrawMonSegment(10)); // Growlithe
//		seq(new WithdrawMonSegment(10)); // Ponyta
//		seq(Segment.menu(Move.DOWN)); // deposit
//		seq(new DepositMonSegment(1)); // Abra
//		seq(Segment.menu(Move.DOWN));
//		seq(Segment.menu(Move.DOWN)); // change box
//		seq(Segment.menu(Move.A)); // change box
//		seq(new SkipTextsSegment(2)); // data saved
//		seq(new SkipTextsSegment(1, true)); // ok
//		seq(Segment.menu(Move.DOWN)); // switch to box 3
//		seq(Segment.menu(Move.A)); // switch to box 3
//		seq(new ResetAndContinueSegment()); // quit and continue
//
//		seq(new WalkToSegment(4,8,false)); // leave center
//		seq(new WalkToSegment(7, 3)); // enter house
//		seq(new WalkToSegment(3, 7)); // move
//		seq(Segment.repress(Move.START));
//		seq(Segment.scrollA(2)); // items
//		seq(Segment.scrollFast(2));
//		seq(new UseRareCandySegment(1, -1)); // ponyta L35
//		seq(new SkipTextsSegment(1)); // Growl
//		seq(new UseRareCandySegment(4, 0)); // ponyta L39
//		seq(new CancelMoveLearnSegment()); // Fire spin
//		seq(new UseRareCandySegment(1, 0)); // ponyta L40
//		seq(new TextSegment());
//		seq(new TextSegment()); // evolution
//		seq(Segment.scrollFast(9)); // Fire Stone
//		seq(new UseEvoStoneSegment(-1)); // Growlite
//		seq(new UseEvoStoneSegment(-1)); // Vulpix
//		seq(Segment.scrollFast(6)); // super rod
//		seq(new SwapWithSegment(-12)); // swap with silph scope
//		seq(Segment.scrollFast(16)); // money 0x0
//		seq(new SwapWithSegment(14));
//		seq(new TossItemSegment(256-228)); // warp 228:8
//		seq(Segment.repress(Move.B));
//		seq(Move.START);
//		seq(new WalkToSegment(3, 8, false));
//
//		save("ma8");
	}
}
