package mrwint.gbtasgen.segment.pokemon.gen1.catchemall;

import mrwint.gbtasgen.metric.pokemon.CheckEncounterMetric;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.pokemon.gen1.OverworldInteract;
import mrwint.gbtasgen.move.pokemon.gen1.WalkStep;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.CatchMonSegment;
import mrwint.gbtasgen.segment.pokemon.WalkToSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.DepositMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EncounterAndCatchSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.FishAndCatchSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.SwapWithSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.TossItemSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.UseBikeSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.WithdrawMonSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;

public class CeruleanCave extends SeqSegment {

	@Override
	public void execute() {
//		seq(new WalkToSegment(0, 7));
//		seq(new EncounterAndCatchSegment(54, Move.RIGHT)); // Magneton
//		save("cc1");
//		load("cc1");
//		seq(new WalkToSegment(3, 6));
//		seq(new EncounterAndCatchSegment(new CheckEncounterMetric(116).withMinDSum(0xa5), Move.RIGHT).withExtraSkips(18)); // Dodrio
//		save("cc2a");
//		load("cc2a");
//		seq(new WalkToSegment(5, 5));
//		seq(new EncounterAndCatchSegment(38, Move.UP).withExtraSkips(42)); // Kadabra
//		save("cc3b");
//		load("cc3a");
//		seq(new WalkToSegment(3, 4));
//		seq(new EncounterAndCatchSegment(85, Move.LEFT).withExtraSkips(20)); // Raichu
//		save("cc4");
//		load("cc4");
//		seq(new WalkToSegment(2, 3));
//		seq(new WalkToSegment(1, 3));
//		seq(new WalkToSegment(1, 2));
//		seq(new WalkToSegment(0, 2));
//		seq(new EncounterAndCatchSegment(101, Move.DOWN)); // Wigglytuff
//		save("cc5");
//		load("cc5");
//		seq(new UseBikeSegment());
//		seq(new WalkToSegment(1, 3));
//		seq(new WalkToSegment(2, 3));
//		seq(new WalkToSegment(5, 5));
//		seq(new EncounterAndCatchSegment(129, Move.DOWN)); // Hypno
//		save("cc6");
//		load("cc6");
//		seq(new WalkToSegment(0, 6));
//		seq(new WalkToSegment(11, 2));
//		seq(new EncounterAndCatchSegment(45, Move.RIGHT)); // Arbok
//		save("cc7");
//		load("cc7");
//		seq(Segment.repress(Move.START));
//		seq(Segment.scrollA(2)); // items
//		seq(Segment.scrollFastA(6)); // JACK
//		seq(Segment.repress(Move.A)); // use
////		seq(Segment.skip(80));
//		seq(Move.B);
//		seq(Segment.repress(Move.B));
//		seq(Move.START);
//		for (int i=0; i<3;i++) {
//			seq(new WalkToSegment(13, 2));
//			seq(new WalkToSegment(13, 1));
//			seq(new WalkToSegment(12, 1));
//			seq(new WalkToSegment(12, 2));
//		}
////		seq(new WalkToSegment(12, 1));
//		seq(new WalkToSegment(12, 3));
//		seq(new WalkToSegment(12, 5));
//		seq(new WalkToSegment(12, 6));
//		seq(new WalkToSegment(12, 7));
//		seq(new WalkToSegment(12, 8));
//		seq(new WalkToSegment(12, 9));
//		seq(new WalkToSegment(12, 10));
//		seq(new WalkToSegment(12, 11));
//		seq(new WalkToSegment(12, 12));
//		seq(new WalkToSegment(12, 13));
//		seq(new EncounterAndCatchSegment(40, Move.RIGHT)); // Chansey
//		save("cc8");
//		load("cc8");
//		seq(Move.DOWN);
//		seq(Segment.skip(1));
//		seq(new FishAndCatchSegment(158, 5)); // Seaking
//		seq(new FishAndCatchSegment(138, 5)); // Kingler
//		save("cc9a");
//		load("cc9a");
//		seq(Segment.repress(Move.START));
//		seq(Segment.scrollA(2)); // items
//		seq(Segment.scrollFast(34)); // X pos abs
//		seq(new SwapWithSegment(43)); // VOLCANOBADGE
//		seq(Segment.repress(Move.B));
//		seq(Move.START);
//		seq(new WalkStep(Move.UP, true));
//		seq(new WalkStep(Move.DOWN, false));
//		seq(new OverworldInteract(1));
//		seq(new SkipTextsSegment(1)); // Mew!
//		seq(new CatchMonSegment(0).withExtraSkips(27)); // Mewtwo
//		save("cc10");
////		load("cc10");
//		seq(new WalkStep(Move.UP, true));
//		seq(new WalkStep(Move.UP, true));
//		seq(new EncounterAndCatchSegment(new CheckEncounterMetric(141, 55).withSpcDV(3), Move.UP)); // Electrode
//
//		save("cc1");
//		load("cc1");
//
//		seq(Segment.repress(Move.START));
//		seq(Segment.scrollA(1)); // mons
//		seq(Segment.scrollA(1)); // mew
//		seq(Segment.repress(Move.A)); // Dig
//		seq(Segment.skip(1));
// TODO:		seq(new UseBikeSegment(1, 1));
//		seq(new WalkToSegment(18, -1));
//		seq(Move.START);
//		seq(new CatchMonSegment(0)); // Sandshrew
//		seq(new WalkToSegment(8, 36));
//		seq(new WalkToSegment(23, 13)); // mart
//		seq(Segment.repress(Move.START));
//		seq(Segment.scrollA(2)); // items
//		seq(Segment.scrollFast(20)); // sentinel+money (0:071)
//		seq(new SwapWithSegment(50)); // Poke flute
//		seq(Segment.scrollFast(-29)); // Connection 0xff
//		seq(new SwapWithSegment(-6)); // saved map
//		seq(new TossItemSegment(-83)); // warp 83:2
//		seq(Segment.repress(Move.B));
//		seq(Move.START);
//		seq(new WalkToSegment(3, 8, false)); // wrong warp
//		
//		save("cc2");
//		load("cc2");
//
//		seq(new WalkToSegment(1, 11));
//		seq(new EncounterAndCatchSegment(6, Move.RIGHT)); // Voltorb
//		save("cc3");
//		load("cc3");
//		seq(new WalkToSegment(4, 11));
//		seq(new EncounterAndCatchSegment(53, Move.RIGHT)); // Electabuzz
//		save("cc4");
//		load("cc4");
//		seq(new WalkToSegment(3, 11));
//		seq(new EncounterAndCatchSegment(173, Move.LEFT)); // Magnemite
//		save("cc5");
//		load("cc5");
//		seq(new WalkToSegment(4, 11));
//		seq(new EncounterAndCatchSegment(84, Move.UP)); // Pikachu
//		save("cc6");
//		load("cc6");
//		seq(new OverworldInteract(9));
//		seq(new SkipTextsSegment(1)); // Gyaoo!
//		seq(new CatchMonSegment(0)); // Zapdos
//		seq(Segment.repress(Move.START));
//		seq(Segment.scrollA(1)); // mons
//		seq(Segment.skip(1));
//		seq(Segment.scrollAF(1)); // mew
//		seq(Segment.repress(Move.A)); // Dig
//		seq(Segment.skip(1));
//		
//		save("cc3");
		load("cc3");

		seq(new WalkToSegment(11, 3));
		seq(new WalkToSegment(13,4));
		seq(new WalkToSegment(13,3, false)); // correct facing direction
		seq(Segment.press(Move.A)); // use PC
		seq(new SkipTextsSegment(1)); // booted PC
		seq(Segment.press(Move.A)); // someones PC
		seq(new SkipTextsSegment(2)); // someones PC, mon storage system
		seq(Segment.scroll(1)); // deposit
		seq(new DepositMonSegment(2)); // Ninetales
		seq(new DepositMonSegment(2)); // Arcanine
		seq(new DepositMonSegment(2)); // Rapidash
		seq(Segment.menu(Move.UP)); // withdraw
		seq(new WithdrawMonSegment(5)); // Sandshrew
		seq(Segment.menu(Move.DOWN)); // deposit
		seq(new DepositMonSegment(2)); // Magneton
		seq(Segment.menu(Move.DOWN));
		seq(Segment.menu(Move.DOWN)); // change box
		seq(Segment.menu(Move.A)); // change box
		seq(new SkipTextsSegment(2)); // data saved
		seq(new SkipTextsSegment(1, true)); // ok
		seq(Segment.menu(Move.DOWN)); // switch to box 4
		seq(Segment.menu(Move.A)); // switch to box 4
		seq(Segment.menu(Move.B)); // leave
		seq(Segment.menu(Move.B)); // leave
		seq(new WalkToSegment(4,8,false)); // leave center
//
//		save("cc7");
	}
}
