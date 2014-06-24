package mrwint.gbtasgen.segment.gen1.catchemall;

import mrwint.gbtasgen.metric.CheckEncounterMetric;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.gen1.OverworldInteract;
import mrwint.gbtasgen.move.gen1.WalkStep;
import mrwint.gbtasgen.segment.CatchMonSegment;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.WalkToSegment;
import mrwint.gbtasgen.segment.gen1.common.DepositMonSegment;
import mrwint.gbtasgen.segment.gen1.common.EncounterAndCatchSegment;
import mrwint.gbtasgen.segment.gen1.common.FishAndCatchSegment;
import mrwint.gbtasgen.segment.gen1.common.SwapWithSegment;
import mrwint.gbtasgen.segment.gen1.common.TossItemSegment;
import mrwint.gbtasgen.segment.gen1.common.WithdrawMonSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;

public class CeruleanCave extends SeqSegment {

	@Override
	public void execute() {
		seq(new WalkToSegment(0, 7));
		seq(new EncounterAndCatchSegment(129, Move.RIGHT)); // Hypno
//		seq(new WalkToSegment(2, 6));
//		seq(new EncounterAndCatchSegment(46, Move.RIGHT)); // Parasect in safari zone
		seq(new WalkToSegment(5, 6));
		seq(new EncounterAndCatchSegment(116, Move.UP)); // Dodrio
		seq(new WalkToSegment(4, 4));
		seq(new EncounterAndCatchSegment(45, Move.LEFT)); // Arbok

		save("cc1");
		load("cc1");

		seq(new WalkToSegment(2, 3));
		seq(new WalkToSegment(1, 3));
		seq(new WalkToSegment(1, 2));
		seq(new EncounterAndCatchSegment(101, Move.LEFT)); // Wigglytuff
		seq(new WalkToSegment(1, 3));
		seq(new WalkToSegment(2, 3));
		seq(new WalkToSegment(2, 4));
		seq(new EncounterAndCatchSegment(38, Move.RIGHT)); // Kadabra
		seq(new WalkToSegment(5, 4));
		seq(new EncounterAndCatchSegment(85, Move.DOWN)); // Raichu
		seq(new WalkToSegment(4, 6));
		seq(new EncounterAndCatchSegment(54, Move.LEFT)); // Magneton
		seq(new WalkToSegment(0, 6));

		save("cc2");
		load("cc2");

		seq(new WalkToSegment(5, 2));
		seq(Segment.repress(Move.START));
		seq(Segment.scrollA(2)); // items
		seq(Segment.scrollFastA(6)); // JACK
		seq(Segment.repress(Move.A)); // use
		seq(Move.B);
		seq(Segment.repress(Move.B));
		seq(Move.START);
		seq(new WalkToSegment(12, 2));
		seq(new WalkToSegment(12, 3));
		seq(new WalkToSegment(12, 5));
		seq(new WalkToSegment(12, 6));
		seq(new EncounterAndCatchSegment(40, Move.DOWN)); // Chansey
		seq(new WalkToSegment(13, 13));
		seq(new FishAndCatchSegment(138, 5)); // Kingler
		seq(new FishAndCatchSegment(158, 5)); // Seaking
		
		save("cc3");
		load("cc3");

		seq(Segment.repress(Move.START));
		seq(Segment.scrollA(2)); // items
		seq(Segment.scrollFast(34)); // X pos abs
		seq(new SwapWithSegment(43)); // VOLCANOBADGE
		seq(Segment.repress(Move.B));
		seq(Move.START);
		seq(new WalkStep(Move.UP, true));
		seq(new WalkStep(Move.DOWN, false));
		seq(new OverworldInteract(1));
		seq(new SkipTextsSegment(1)); // Mew!
		seq(new CatchMonSegment(0)); // Mewtwo
		seq(new WalkStep(Move.UP, true));
		seq(new WalkStep(Move.UP, true));
		seq(new EncounterAndCatchSegment(new CheckEncounterMetric(141, 55).withSpcDV(3), Move.UP)); // Electrode
		
		save("cc4");
		load("cc4");

		seq(Segment.repress(Move.START));
		seq(Segment.scrollA(1)); // mons
		seq(Segment.scrollA(1)); // mew
		seq(Segment.repress(Move.A)); // Dig
		seq(Segment.skip(1));
		seq(new WalkToSegment(18, -1));
		seq(Move.START);
		seq(new CatchMonSegment(0)); // Sandshrew
		seq(new WalkToSegment(8, 36));
		seq(new WalkToSegment(23, 13)); // mart
		seq(Segment.repress(Move.START));
		seq(Segment.scrollA(2)); // items
		seq(Segment.scrollFast(20)); // sentinel+money (0:071)
		seq(new SwapWithSegment(50)); // Poke flute
		seq(Segment.scrollFast(-29)); // Connection 0xff
		seq(new SwapWithSegment(-6)); // saved map
		seq(new TossItemSegment(-83)); // warp 83:2
		seq(Segment.repress(Move.B));
		seq(Move.START);
		seq(new WalkToSegment(3, 8, false)); // wrong warp
		
		save("cc5");
		load("cc5");

		seq(new WalkToSegment(1, 11));
		seq(new EncounterAndCatchSegment(6, Move.RIGHT)); // Voltorb
		seq(new WalkToSegment(4, 11));
		seq(new EncounterAndCatchSegment(173, Move.RIGHT)); // Magnemite
		seq(new WalkToSegment(3, 11));
		seq(new EncounterAndCatchSegment(84, Move.LEFT)); // Pikachu
		seq(new WalkToSegment(4, 11));
		seq(new EncounterAndCatchSegment(53, Move.UP)); // Electabuzz
		seq(new OverworldInteract(9));
		seq(new SkipTextsSegment(1)); // Gyaoo!
		seq(new CatchMonSegment(0)); // Zapdos
		seq(Segment.repress(Move.START));
		seq(Segment.scrollA(1)); // mons
		seq(Segment.scrollA(1)); // mew
		seq(Segment.repress(Move.A)); // Dig
		seq(Segment.skip(1));
		
		save("cc6");
		load("cc6");

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
		seq(new DepositMonSegment(2)); // Hypno
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

		save("cc7");
	}
}
