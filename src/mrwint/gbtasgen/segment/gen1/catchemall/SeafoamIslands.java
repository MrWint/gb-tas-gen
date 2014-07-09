package mrwint.gbtasgen.segment.gen1.catchemall;

import mrwint.gbtasgen.metric.CheckEncounterMetric;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.gen1.OverworldInteract;
import mrwint.gbtasgen.move.gen1.WalkStep;
import mrwint.gbtasgen.segment.CatchMonSegment;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.TextSegment;
import mrwint.gbtasgen.segment.WalkToSegment;
import mrwint.gbtasgen.segment.gen1.common.DepositMonSegment;
import mrwint.gbtasgen.segment.gen1.common.EncounterAndCatchSegment;
import mrwint.gbtasgen.segment.gen1.common.FishAndCatchSegment;
import mrwint.gbtasgen.segment.gen1.common.SwapWithSegment;
import mrwint.gbtasgen.segment.gen1.common.TossItemSegment;
import mrwint.gbtasgen.segment.gen1.common.UseBikeSegment;
import mrwint.gbtasgen.segment.gen1.common.UseEvoStoneSegment;
import mrwint.gbtasgen.segment.gen1.common.UseRareCandySegment;
import mrwint.gbtasgen.segment.gen1.common.WithdrawMonSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;

public class SeafoamIslands extends SeqSegment {

	@Override
	public void execute() {

//		seq(new UseBikeSegment(0, -1));
//		seq(new WalkToSegment(24, 22));
//		seq(new FishAndCatchSegment(110, 3, 0)); // Poliwhirl
//		seq(new WalkToSegment(31, 27));
//		seq(new WalkToSegment(5, 7));
//		seq(Segment.repress(Move.START));
//		seq(Segment.scrollA(2)); // items
//		seq(Segment.scrollFast(31)); // palette
//		seq(new SwapWithSegment(4)); // saved map
//		seq(new TossItemSegment(-10)); // warp 0ax0a
//		seq(Segment.scrollFast(-30)); // JACK
//		seq(Segment.repress(Move.B));
//		seq(Move.START);
//		seq(new WalkToSegment(4, 8, false));
//
//		seq(new Move() {
//			@Override public int getInitialKey() { return 0; }
//			@Override
//			public boolean doMove() {
//				WalkStep.runToNextWalkFrame(Move.LEFT);
//				return true;
//			}
//		});
//		seq(Move.LEFT); // turn left
//		{
//			seq(Segment.skip(1));
//			seq(Segment.repress(Move.START));
//			seq(Move.A); // items
//			seq(Segment.repress(Move.A)); // JACK
//			seq(Segment.repress(Move.A)); // use
//			seq(Move.B); // cancel
//			seq(Segment.repress(Move.B)); // cancel
//			seq(Move.START); // cancel
//		}
//		seq(new WalkStep(Move.DOWN, true));
//		{
//			seq(Segment.repress(Move.START));
//			seq(Segment.scrollA(-1)); // mons
//			seq(Move.B); // cancel
//			seq(Move.START); // cancel
//		}
//		seq(new WalkStep(Move.DOWN, true));
//		seq(new WalkStep(Move.DOWN, true));
//		seq(new WalkStep(Move.RIGHT, true));
//		seq(Segment.skip(1));
//		seq(Move.LEFT); // turn left
//		{
//			seq(Segment.skip(1));
//			seq(Segment.repress(Move.START));
//			seq(Segment.scrollA(1)); // items
//			seq(Segment.repress(Move.A)); // JACK
//			seq(Segment.repress(Move.A)); // use
//			seq(Move.B); // cancel
//			seq(Segment.repress(Move.B)); // cancel
//			seq(Move.START); // cancel
//		}
//		seq(new WalkStep(Move.RIGHT, true));
//		seq(new WalkStep(Move.DOWN, true));
//		{
//			seq(Segment.repress(Move.START));
//			seq(Segment.scrollA(-1)); // mons
//			seq(Move.B); // cancel
//			seq(Move.START); // cancel
//		}
//		seq(new WalkStep(Move.DOWN, true));
//		seq(Segment.skip(1));
//		seq(Move.RIGHT); // turn right
//		{
//			seq(Segment.skip(1));
//			seq(Segment.repress(Move.START));
//			seq(Segment.scrollA(1)); // items
//			seq(Segment.repress(Move.A)); // JACK
//			seq(Segment.repress(Move.A)); // use
//			seq(Move.B); // cancel
//			seq(Segment.repress(Move.B)); // cancel
//			seq(Move.START); // cancel
//		}
//		seq(new WalkStep(Move.LEFT, true));
//		seq(new WalkStep(Move.DOWN, true));
//		seq(new WalkStep(Move.DOWN, true));
//		seq(new WalkStep(Move.DOWN, true));
//		seq(new WalkStep(Move.DOWN, true));
//		seq(new WalkStep(Move.DOWN, true));
//
//		seq(Segment.repress(Move.START));
//		seq(Segment.scrollA(-1)); // mons
//		seq(Segment.scrollA(1)); // mew
//		seq(Segment.scrollA(1)); // fly
//		seq(Segment.scrollA(4)); // vermilion
//		seq(Segment.skip(1));
//
//		seq(new WalkToSegment(40, 14)); // leave vermilion
//		seq(new WalkToSegment(4, 5)); // enter diglett's cave
//		seq(new WalkToSegment(4, 4)); // enter diglett's cave
//		seq(new WalkToSegment(36, 31));
//		seq(new EncounterAndCatchSegment(88, Move.LEFT, true)); // Dratini
//
//		save("si1");
//		load("si1");
//
//		seq(new UseBikeSegment());
////		seq(Segment.repress(Move.START));
////		seq(Move.B);
//		seq(new WalkToSegment(37, 31)); // go up
//		seq(new WalkToSegment(3, 8, false)); // go up
//		seq(new WalkToSegment(-1, 6)); // vermilion
//		seq(new WalkToSegment(23, 13)); // mart
//		seq(new WalkToSegment(4, 2)); // pos
//		seq(Segment.repress(Move.START));
//		seq(Move.A); // items
//		seq(Move.B);
//		seq(Move.START);
//		seq(new WalkToSegment(3, 8, false)); // leave
//		seq(new WalkToSegment(40, 14)); // leave vermilion
//		seq(new WalkToSegment(4, 5)); // enter diglett's cave
//		seq(new WalkToSegment(4, 4)); // enter diglett's cave
//		seq(new WalkToSegment(36, 31));
//		seq(new EncounterAndCatchSegment(77, Move.LEFT, true)); // Meowth
//		save("si2a");
//		load("si2a");
//		seq(new UseBikeSegment());
//		seq(new WalkToSegment(36, 32));
//		seq(new EncounterAndCatchSegment(59, Move.RIGHT)); // Diglett
//		seq(new EncounterAndCatchSegment(118, Move.RIGHT)); // Dugtrio
//		save("si3a");
//		load("si3");
//		seq(new WalkToSegment(37, 31)); // go up
//		seq(new WalkToSegment(3, 8, false)); // go up
//		seq(new WalkToSegment(-1, 6)); // vermilion
//		seq(new WalkToSegment(23, 13)); // mart
//		seq(new WalkStep(Move.UP, true));
//		seq(Move.DOWN);
//		{
//			seq(Segment.skip(1));
//			seq(Segment.repress(Move.START));
//			seq(Segment.scrollA(2)); // items
//			seq(Segment.scrollFastA(16)); // Full heal
//			seq(Segment.repress(Move.A)); // use
//			seq(Segment.repress(Move.A)); // Ditto
//			seq(Move.B); // Ditto healed
//			seq(Segment.scrollFastA(-11)); // JACK
//			seq(Segment.repress(Move.A)); // use
//			seq(Move.B); // cancel
//			seq(Segment.repress(Move.B)); // cancel
//			seq(Move.START); // cancel
//		}
//		seq(new WalkStep(Move.RIGHT, true));
//		seq(new WalkStep(Move.UP, true));
//		seq(new WalkStep(Move.UP, true));
//		seq(new WalkStep(Move.UP, true));
//		seq(Segment.repress(Move.START));
//		seq(Segment.scrollA(-1)); // mons
//		seq(Move.B);
//		seq(Move.START);
//		seq(new WalkToSegment(3, 8, false)); // leave
//		seq(new WalkToSegment(40, 14)); // leave vermilion
//		seq(new WalkToSegment(4, 5)); // enter diglett's cave
//		seq(new WalkToSegment(4, 4)); // enter diglett's cave
//		seq(new WalkToSegment(36, 31));
//		seq(new EncounterAndCatchSegment(11, Move.LEFT, true)); // Lickitung
//		save("si4a");
//		load("si4a");
//		seq(new UseBikeSegment());
//		seq(new WalkToSegment(36, 32));
//		seq(new EncounterAndCatchSegment(59, Move.RIGHT)); // Diglett
//		seq(new EncounterAndCatchSegment(118, Move.RIGHT)); // Dugtrio
//		save("si5a");
//		load("si5");
//		seq(new WalkToSegment(37, 31)); // go up
//		seq(new WalkToSegment(3, 8, false)); // go up
//		seq(new WalkToSegment(-1, 6)); // vermilion
//		seq(new WalkToSegment(23, 13)); // mart
//
//		seq(Segment.scrollFast(2)); // rare candy
//		seq(new UseRareCandySegment(1, -1)); // Meowth L63
//		seq(new TextSegment());
//		seq(new TextSegment()); // evolution
//		seq(new UseRareCandySegment(1, -1)); // Dratini L81
//		seq(new TextSegment());
//		seq(new TextSegment()); // evolution
//		seq(new UseRareCandySegment(1, 0)); // Dragonair L82
//		seq(new TextSegment());
//		seq(new TextSegment()); // evolution
//		seq(new UseRareCandySegment(4, -2)); // Kabuto L34
//		seq(new SkipTextsSegment(1)); // Absorb
//		seq(new UseRareCandySegment(5, 0)); // Kabuto L39
//		seq(new SkipTextsSegment(1)); // Leer
//		seq(new UseRareCandySegment(1, 0)); // Kabuto L40
//		seq(new TextSegment());
//		seq(new TextSegment()); // evolution
//		seq(Segment.scrollFast(7)); // water stone
//		seq(new UseEvoStoneSegment(1)); // Poliwrath
//		
//		seq(Segment.scrollFast(14)); // rival name 0x0
//		seq(new SwapWithSegment(12)); // saved map
//		seq(new TossItemSegment(256-192)); // warp 192:2
//		seq(Segment.repress(Move.B));
//		seq(Move.START);
//		seq(Segment.repress(Move.START));
//		seq(Segment.scrollA(2)); // items
//		
//		seq(Segment.scrollFast(2)); // rare candy
//		seq(new UseRareCandySegment(1, -1)); // Meowth L63
//		seq(new TextSegment());
//		seq(new TextSegment()); // evolution
//		seq(new UseRareCandySegment(1, -1)); // Dratini L81
//		seq(new TextSegment());
//		seq(new TextSegment()); // evolution
//		seq(new UseRareCandySegment(1, 0)); // Dragonair L82
//		seq(new TextSegment());
//		seq(new TextSegment()); // evolution
//		seq(new UseRareCandySegment(4, -2)); // Kabuto L34
//		seq(new SkipTextsSegment(1)); // Absorb
//		seq(new UseRareCandySegment(5, 0)); // Kabuto L39
//		seq(new SkipTextsSegment(1)); // Leer
//		seq(new UseRareCandySegment(1, 0)); // Kabuto L40
//		seq(new TextSegment());
//		seq(new TextSegment()); // evolution
//		seq(Segment.scrollFast(7)); // water stone
//		seq(new UseEvoStoneSegment(1)); // Poliwrath
//		
//		seq(Segment.scrollFast(14)); // rival name 0x0
//		seq(new SwapWithSegment(12)); // saved map
//		seq(new TossItemSegment(256-192)); // warp 192:2
//		seq(Segment.repress(Move.B));
//		seq(Move.START);
//		seq(new WalkToSegment(3,8, false)); // wrong warp
//		
//		save("si2");
//		load("si2");
//
//		seq(new WalkToSegment(26, 16));
//		seq(new EncounterAndCatchSegment(128, Move.UP)); // Golduck
//		save("si3");
//		load("si3");
//		seq(new UseBikeSegment());
//		seq(new WalkToSegment(25, 14));
//		seq(new EncounterAndCatchSegment(47, Move.LEFT)); // Psyduck
//		save("si4");
//		load("si4");
//		seq(new WalkToSegment(23, 15)); // B1F
//		seq(new WalkToSegment(24, 14));
//		seq(new EncounterAndCatchSegment(58, Move.RIGHT)); // Seel
//		save("si5");
//		load("si5");
//		seq(new WalkToSegment(26, 13));
//		seq(new EncounterAndCatchSegment(120, Move.UP)); // Dewgong
//		save("si6");
//		load("si6");
//		seq(new WalkToSegment(25, 11)); // B2F
//		seq(new WalkToSegment(23, 13));
//		seq(new WalkToSegment(23, 14));
//		seq(new EncounterAndCatchSegment(27, Move.RIGHT)); // Staryu
//		save("si7");
//		load("si7");
//		seq(new WalkToSegment(25, 14)); // B3F
//		seq(new WalkToSegment(25, 12));
//		seq(new EncounterAndCatchSegment(23, Move.UP)); // Shellder
//		save("si8");
//		load("si8");
//		seq(new WalkToSegment(25, 13));
//		seq(new WalkToSegment(26, 8));
//		seq(new EncounterAndCatchSegment(93, Move.LEFT)); // Seadra
//		save("si9");
//		load("si9");
//		seq(new Move() {
//			@Override public int getInitialKey() { return 0; }
//			@Override
//			public boolean doMove() {
//				WalkStep.runToNextWalkFrame(Move.RIGHT);
//				return true;
//			}
//		});
//		seq(Segment.skip(1));
//		seq(Move.RIGHT);
//		seq(Segment.skip(1));
//		{
//			seq(Segment.repress(Move.START));
//			seq(Segment.scrollA(2)); // items
//			seq(Segment.scrollFastA(5)); // JACK
//			seq(Segment.repress(Move.A)); // use
//			seq(Move.B); // cancel
//			seq(Segment.repress(Move.B)); // cancel
//			seq(Move.START); // cancel
//		}
//		seq(new WalkToSegment(25, 6));
//		seq(new EncounterAndCatchSegment(37, Move.UP)); // Slowpoke
//		save("si10");
//		load("si10");
//		seq(new WalkToSegment(25, 4)); // B4F
//		seq(new WalkToSegment(25, 3));
//		seq(Segment.repress(Move.START));
//		seq(Segment.scrollA(2)); // items
//		seq(Segment.scrollFast(34)); // X abs
//		seq(new SwapWithSegment(1)); // swap with saved map
//		seq(Segment.skip(10)); // WAIT
//		seq(Segment.repress(Move.B)); // cancel
//		seq(Move.START); // cancel
////		seq(new WalkStep(Move.UP, true));
//		seq(new WalkStep(Move.UP, true));
//		seq(new EncounterAndCatchSegment(8, Move.UP)); // Slowbro
//		save("si11");
//		load("si11");
//		seq(new WalkStep(Move.RIGHT, true));
//		seq(new WalkStep(Move.RIGHT, true));
//		seq(new WalkStep(Move.RIGHT, true));
//		seq(new EncounterAndCatchSegment(92, Move.RIGHT)); // Horsea
//		save("si12");
//		load("si12");
//		seq(new OverworldInteract(3));
//		seq(new SkipTextsSegment(1)); // Gyaoo!
//		seq(new CatchMonSegment(0)); // Articuno
//		seq(Segment.repress(Move.START));
//		seq(Segment.scrollA(1)); // mons
//		seq(Segment.skip(1));
//		seq(Segment.scrollAF(1)); // mew
//		seq(Segment.repress(Move.A)); // dig
//		seq(Segment.skip(1));
//		
//		save("si3");
//		load("si3");
//
//		seq(new UseBikeSegment(1, 1));
//		seq(new WalkToSegment(35, 27));
//		seq(Segment.repress(Move.START));
//		seq(Segment.repress(Move.A)); // items
//		seq(Segment.scrollFast(31-1)); // palette
//		seq(new SwapWithSegment(4)); // saved map
//		seq(new TossItemSegment(-0x21)); // warp 21x0b
//		seq(Segment.repress(Move.B));
//		seq(Move.START);
//		seq(new WalkToSegment(2, 8, false));
//		seq(Segment.repress(Move.START));
//		seq(Segment.scrollA(-1)); // mons
//		seq(Segment.repress(Move.A)); // mew
//		seq(Segment.skip(1));
//		seq(Segment.scrollAF(1)); // fly
//		seq(Segment.skip(1));
//		seq(Segment.scrollA(3)); // celadon
//		seq(Segment.skip(1));
//		seq(new WalkToSegment(50, 10));
//		save("si4");
//		load("si4");
//		seq(new WalkToSegment(7, 2, false)); // ledge
//		seq(new EncounterAndCatchSegment(51, Move.DOWN, true)); // Magmar
//		seq(new WalkToSegment(12, 10, false)); // gate
//		
//		save("si5");
//		load("si5");
//		seq(new WalkToSegment(1, 4));
//
//		seq(Segment.repress(Move.START));
//		seq(Segment.scrollA(2)); // items
//		seq(Segment.scrollFast(33)); // Y abs
//		seq(new SwapWithSegment(1)); // swap with X abs
//		seq(new SwapWithSegment(1)); // swap with saved map
//		seq(Segment.scrollFast(-2)); // Y abs
//		seq(new SwapWithSegment(2)); // swap with saved map
//		seq(new TossItemSegment(-0x7d));
//		seq(Segment.repress(Move.B)); // cancel
//		seq(Move.START); // cancel
//
//		seq(new WalkToSegment(-1, 4, false));
//		seq(new WalkStep(Move.RIGHT, true));
//		seq(Segment.repress(Move.START));
//		seq(Move.A); // items
//		seq(new SwapWithSegment(1)); // tileset
//		seq(new TossItemSegment(-3)); // dig everywhere
//		seq(Segment.repress(Move.B)); // cancel
//		seq(Segment.scrollA(-1)); // mons
//		seq(Segment.skip(1));
//		seq(Segment.scrollAF(1)); // mew
//		seq(Segment.repress(Move.A)); // dig
//		seq(Segment.skip(1));
//		seq(new WalkToSegment(50, 10));
//		seq(new WalkToSegment(7, 2, false)); // ledge
//		seq(new WalkToSegment(8, 3));
//		seq(new EncounterAndCatchSegment(42, Move.DOWN, true)); // Mr.Mime
//		seq(new WalkToSegment(12, 10, false)); // gate
//		seq(new WalkToSegment(6, 4, false)); // gate
//		seq(new WalkToSegment(20, 10)); // saffron
//
//		save("si6");
//		load("si6");
//
//		seq(new WalkToSegment(1, 24)); // (1, 23)?
//		seq(Segment.repress(Move.START));
//		seq(Segment.scrollA(1)); // mons
//		seq(Segment.skip(1));
//		seq(Segment.scrollAF(1)); // mew
//		seq(Segment.skip(1));
//		seq(Segment.scrollAF(1)); // fly
//		seq(Segment.skip(1));
//		seq(Segment.scrollA(4)); // vermilion
//		seq(Segment.skip(1));
//		seq(new WalkToSegment(40, 14)); // leave vermilion
//		seq(new WalkToSegment(4, 5)); // enter diglett's cave
//		seq(new WalkToSegment(4, 4)); // enter diglett's cave
//		seq(new WalkToSegment(37, 32));
//		seq(new EncounterAndCatchSegment(14, Move.DOWN, true, 4 ,2)); // Gengar
//		seq(Segment.repress(Move.START));
//		seq(Segment.scrollA(1)); // mons
//		seq(Segment.skip(1));
//		seq(Segment.scrollAF(1)); // mew
//		seq(Segment.repress(Move.A)); // dig
//		seq(Segment.skip(1));
//
//		save("si7");
//		load("si7");
//
//		seq(new WalkToSegment(41, 9));
//		seq(new WalkToSegment(13,4));
//		seq(new WalkToSegment(13,3, false)); // correct facing direction
//		seq(Segment.press(Move.A)); // use PC
//		seq(new SkipTextsSegment(1)); // booted PC
//		seq(Segment.press(Move.A)); // someones PC
//		seq(new SkipTextsSegment(2)); // someones PC, mon storage system
//		seq(Segment.scroll(1)); // deposit
//		seq(new DepositMonSegment(2)); // Gyarados
//		seq(new DepositMonSegment(2)); // Poliwrath
//		seq(new DepositMonSegment(2)); // Dragonite
//		seq(Segment.menu(Move.UP)); // withdraw
//		seq(new WithdrawMonSegment(8)); // Shellder
//		seq(new WithdrawMonSegment(8)); // Staryu
//		seq(Segment.menu(Move.B)); // leave
//		seq(Segment.menu(Move.B)); // leave
//		seq(Segment.skip(1));
//		seq(Segment.repress(Move.START));
//		seq(Segment.scrollA(1)); // items
//		seq(Segment.scrollFast(9)); // water stone
//		seq(new UseEvoStoneSegment(-1)); // Starmie
//		seq(new UseEvoStoneSegment(-1)); // Cloyster
//		seq(Segment.repress(Move.B));
//		seq(Move.START);
//		seq(Segment.press(Move.A)); // use PC
//		seq(new SkipTextsSegment(1)); // booted PC
//		seq(Segment.press(Move.A)); // someones PC
//		seq(new SkipTextsSegment(2)); // someones PC, mon storage system
//		seq(Segment.scroll(1)); // deposit
//		seq(new DepositMonSegment(2)); // Persian
//		seq(new DepositMonSegment(2)); // Cloyster
//		seq(Segment.menu(Move.DOWN));
//		seq(Segment.menu(Move.DOWN)); // change box
//		seq(Segment.menu(Move.A)); // change box
//		seq(new SkipTextsSegment(2)); // data saved
//		seq(new SkipTextsSegment(1, true)); // ok
//		seq(Segment.menu(Move.DOWN)); // switch to box 6
//		seq(Segment.menu(Move.A)); // switch to box 6
//		seq(Segment.menu(Move.UP));
//		seq(Segment.menu(Move.UP)); // deposit
//		seq(new DepositMonSegment(2)); // Starmie
//		seq(Segment.menu(Move.B)); // leave
//		seq(Segment.menu(Move.B)); // leave
//		seq(new WalkToSegment(4, 8, false)); // leave center

//		save("si9");
	}
}
