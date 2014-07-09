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
import mrwint.gbtasgen.segment.gen1.common.UseRareCandySegment;
import mrwint.gbtasgen.segment.gen1.common.WithdrawMonSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;

public class OldMan extends SeqSegment {

	@Override
	public void execute() {

//		seq(Segment.repress(Move.START));
//		seq(Segment.repress(Move.A)); // mons
//		seq(Segment.scrollA(1)); // mew
//		seq(Segment.scrollA(1)); // Fly
//		seq(Segment.repress(Move.A)); // pallet town
//		seq(Segment.skip(1));
//
//		seq(new WalkToSegment(6, 12));
//		seq(new WalkToSegment(6, 13));
//		seq(new FishAndCatchSegment(71, 5, 1)); // Poliwag
//		save("om1");
//		load("om1");
//		seq(Segment.repress(Move.START));
//		seq(Segment.scrollA(1)); // mons
//		seq(Segment.skip(1));
//		seq(Segment.scrollAF(1)); // mew
//		seq(Segment.skip(1));
//		seq(Segment.scrollAF(2)); // Surf
//		seq(new SkipTextsSegment(1)); // got on mew
//		seq(new WalkStep(Move.DOWN, true));
//		seq(new WalkStep(Move.DOWN, true));
//		seq(new WalkStep(Move.DOWN, true));
//		seq(new WalkStep(Move.DOWN, true));
//		seq(new WalkStep(Move.DOWN, true));
//		seq(new WalkStep(Move.DOWN, true));
//		seq(new WalkStep(Move.DOWN, true));
//		seq(new WalkStep(Move.DOWN, true));
//		seq(new WalkStep(Move.DOWN, true));
//		seq(new WalkStep(Move.DOWN, true));
//		seq(new WalkStep(Move.DOWN, true));
//		seq(new WalkStep(Move.DOWN, true));
//		seq(new WalkStep(Move.DOWN, true));
//		seq(new WalkStep(Move.DOWN, true));
//		seq(new WalkStep(Move.RIGHT, true));
//		seq(new EncounterAndCatchSegment(new CheckEncounterMetric(24, 30, 35, 40), Move.RIGHT)); // Tentacool
//		save("om2");
//		load("om2");
//		seq(new WalkToSegment(8, 7));
//		seq(new EncounterAndCatchSegment(new CheckEncounterMetric(150, 32), Move.UP)); // Pidgeotto L32
//		save("om3");
//		load("om3");
//		seq(new WalkToSegment(10, 6));
//		seq(new EncounterAndCatchSegment(30, Move.RIGHT)); // Tangela
//		save("om4");
//		load("om4");
//		seq(new WalkToSegment(12, 7));
//		seq(new EncounterAndCatchSegment(165, Move.UP).withExtraSkips(10)); // Rattata
//		save("om5");
//		load("om5");
//		seq(new WalkToSegment(10, 6));
//		seq(new EncounterAndCatchSegment(36, Move.LEFT).withExtraSkips(57)); // Pidgey
//		save("om6");
////		load("om6");
//		seq(new WalkToSegment(8, 5));
//		seq(new EncounterAndCatchSegment(166, Move.UP)); // Raticate
//	
//		seq(Segment.repress(Move.START));
//		seq(Segment.scrollA(1)); // mons
//		seq(Segment.skip(1));
//		seq(Segment.scrollAF(1)); // mew
//		seq(Segment.skip(1));
//		seq(Segment.scrollAF(1)); // Fly
//		seq(Segment.scrollA(-1)); // viridian city
//		seq(Segment.skip(1));
//		
//		save("om1");
//		load("om1");
//
//		seq(new WalkToSegment(23, 25)); // enter center
//		seq(Segment.repress(Move.START));
//		seq(Segment.scrollA(1)); // items
//		seq(Segment.scrollFast(2)); // Rare Candy
//		seq(new UseRareCandySegment(3, 1)); // Sandshrew L10
//		seq(new SkipTextsSegment(1)); // Sand-Attack
//		seq(new UseRareCandySegment(7, 0)); // Sandshrew L17
//		seq(new SkipTextsSegment(1)); // Slash
//		seq(new UseRareCandySegment(5, 0)); // Sandshrew L22
//		seq(new TextSegment());
//		seq(new TextSegment()); // evolution
//		seq(new UseRareCandySegment(1, 2)); // Tentacool L31
//		seq(new TextSegment());
//		seq(new TextSegment()); // evolution
//		seq(new UseRareCandySegment(4, 1)); // Pidgeotto L36
//		seq(new TextSegment());
//		seq(new TextSegment()); // evolution
//		seq(Segment.scrollFast(9)); // Fire stone
//		seq(new SwapWithSegment(3)); // swap with rare candy
//		seq(Segment.scrollFast(-3)); // rare candy
//		seq(new SwapWithSegment(35)); // swap with Dome Fossil
//		
//		save("tmp");
//		load("tmp");
//		
////		seq(Segment.scrollFast(-45)); // bike
//		seq(Segment.repress(Move.B));
//		seq(Move.START);
//		seq(new WalkToSegment(3, 8, false));
////		seq(new UseBikeSegment(0, 0));
//
//		seq(new WalkToSegment(18, 7)); // old man
//		seq(new WalkToSegment(18, 6)); // old man
//		seq(new OverworldInteract(7));
//		seq(new SkipTextsSegment(11)); // no hurry
//		seq(new SkipTextsSegment(1)); // wild weedle
//		seq(new TextSegment()); // old man used pokeball
//		seq(new SkipTextsSegment(2)); // weedle caught
//		seq(new SkipTextsSegment(2)); // weaken first
//
//		seq(Segment.repress(Move.START));
//		seq(Segment.scrollA(1)); // mons
//		seq(Segment.skip(1));
//		seq(Segment.scrollAF(1)); // mew
//		seq(Segment.skip(1));
//		seq(Segment.scrollAF(1)); // Fly
//		seq(Segment.skip(1));
//		seq(Segment.scrollA(1)); // cinnabar
//		seq(Segment.skip(1));
//		
//		save("om2");
//		load("om2a");
//
//		seq(new WalkToSegment(6, 9)); // enter lab
//		seq(new WalkToSegment(16, 4)); // room
//		seq(new WalkToSegment(4, 3)); // room
//		seq(new OverworldInteract(1));
//		seq(new SkipTextsSegment(4));
//		seq(Move.A); // Dome Fossil
//		seq(new SkipTextsSegment(6));
//		seq(new SkipTextsSegment(1, true)); // ress
//		seq(new SkipTextsSegment(4));
//		seq(new WalkToSegment(3, 8, false));
//		seq(new WalkToSegment(3, 8, false));
//		seq(new WalkToSegment(6, 9)); // enter lab
//		seq(new WalkToSegment(16, 4)); // room
//		seq(new WalkToSegment(4, 3)); // room
//		seq(new OverworldInteract(1));
//		seq(new SkipTextsSegment(3));
//		seq(new TextSegment()); // got kabuto
//		seq(new SkipTextsSegment(2)); // no nick
//		seq(new SkipTextsSegment(4)); // no room
//		seq(new OverworldInteract(1));
//		seq(new SkipTextsSegment(4));
//		seq(Segment.scrollA(1)); // Helix fossil
//		seq(new SkipTextsSegment(6));
//		seq(new SkipTextsSegment(1, true)); // ress
//		seq(new SkipTextsSegment(4));
//		seq(new WalkToSegment(3, 8, false));
//		seq(new WalkToSegment(3, 8, false));
//		save("om3");
//		load("om3");
//		seq(new WalkToSegment(6, 9)); // enter lab
//		seq(new WalkToSegment(16, 4)); // room
//		seq(new WalkToSegment(4, 3)); // room
//		seq(new OverworldInteract(1));
//		seq(new SkipTextsSegment(3));
//		seq(new TextSegment()); // got omanyte
//		seq(new SkipTextsSegment(2)); // no nick
//		seq(new SkipTextsSegment(4)); // no room
//		seq(new OverworldInteract(1));
//		seq(new SkipTextsSegment(4));
//		seq(Segment.scrollA(1)); // Old Amber
//		seq(new SkipTextsSegment(6));
//		seq(new SkipTextsSegment(1, true)); // ress
//		seq(new SkipTextsSegment(4));
//		seq(new WalkToSegment(3, 8, false));
//		seq(new WalkToSegment(3, 8, false));
//		seq(new WalkToSegment(6, 9)); // enter lab
//		seq(new WalkToSegment(16, 4)); // room
//		seq(new WalkToSegment(4, 3)); // room
//		seq(new OverworldInteract(1));
//		seq(new SkipTextsSegment(3));
//		seq(new TextSegment()); // got aerodactyl
//		seq(new SkipTextsSegment(2)); // no nick
//		seq(new SkipTextsSegment(4)); // no room
//		seq(new WalkToSegment(3, 8, false));
//		seq(new WalkToSegment(3, 8, false));
//		
//		save("om4");
//		load("om4");
//
//		seq(new WalkToSegment(19, 12));
//		seq(Segment.repress(Move.START));
//		seq(Segment.repress(Move.A)); // mons
//		seq(Segment.repress(Move.A)); // mew
//		seq(Segment.scrollA(2)); // Surf
//		seq(new SkipTextsSegment(1)); // got on mew
//		seq(new EncounterAndCatchSegment(153, Move.UP)); // Bulbasaur
//		save("om5");
//		load("om5");
//		seq(new WalkStep(Move.UP, true));
//		seq(new WalkStep(Move.UP, true));
//		seq(new EncounterAndCatchSegment(177, Move.UP)); // Squirtle
//		save("om6");
//		load("om6");
//		for (int i=0;i<5;i++)
//			seq(new WalkStep(Move.RIGHT, true));
//		for (int i=0;i<5;i++)
//			seq(new WalkStep(Move.LEFT, true));
//		seq(new WalkStep(Move.UP, true));
//		seq(new EncounterAndCatchSegment(new CheckEncounterMetric(149, 130).withSpcDV(10), Move.UP)); // Alakazam
//
//		seq(Segment.repress(Move.START));
//		seq(Segment.scrollA(1)); // mons
//		seq(Segment.skip(1));
//		seq(Segment.scrollAF(1)); // mew
//		seq(Segment.skip(1));
//		seq(Segment.scrollAF(1)); // Fly
//		seq(Segment.skip(1));
//		seq(Segment.scrollA(4)); // Lavender
//		seq(Segment.skip(1));
//		
//		save("om7");
//		load("om7");

// TODO:		seq(new UseBikeSegment(1, 1));
//		seq(new WalkToSegment(9, 18));
//		seq(Move.START);
//		seq(new CatchMonSegment(0));
//		seq(new WalkToSegment(10, 4, false)); // face water
//		seq(new FishAndCatchSegment(78, 4)); // Krabby
//		seq(new FishAndCatchSegment(157, 4)); // Goldeen
//		seq(new FishAndCatchSegment(133, 4)); // Magikarp
//		seq(Segment.repress(Move.START));
//		seq(Segment.scrollA(1)); // mons
//		seq(Segment.skip(1));
//		seq(Segment.scrollAF(1)); // mew
//		seq(Segment.skip(1));
//		seq(Segment.scrollAF(1)); // Fly
//		seq(Segment.skip(1));
//		seq(Segment.scrollA(2)); // Celadon
//
//		save("om8");
		load("om8");
		seq(Segment.skip(1));

		seq(new WalkToSegment(41, 9));
		seq(new WalkToSegment(13,4));
		seq(new WalkToSegment(13,3, false)); // correct facing direction
		seq(Segment.press(Move.A)); // use PC
		seq(new SkipTextsSegment(1)); // booted PC
		seq(Segment.press(Move.A)); // someones PC
		seq(new SkipTextsSegment(2)); // someones PC, mon storage system
		seq(Segment.scroll(1)); // deposit
		seq(new DepositMonSegment(2)); // Sandslash
		seq(new DepositMonSegment(2)); // Poliwag
		seq(new DepositMonSegment(2)); // Tentacruel
		seq(new DepositMonSegment(2)); // Pidgeot
		seq(Segment.menu(Move.UP)); // withdraw
		seq(new WithdrawMonSegment(0)); // Magikarp
		seq(new WithdrawMonSegment(4)); // Squritle
		seq(new WithdrawMonSegment(4)); // Bulbasaur
		seq(new WithdrawMonSegment(5)); // Omanyte
		seq(Segment.menu(Move.B)); // leave
		seq(Segment.menu(Move.B)); // leave
		seq(Segment.skip(1));
		seq(Segment.repress(Move.START));
		seq(Segment.scrollA(1)); // items
		seq(Segment.scrollFast(2)); // rare candy
		seq(new UseRareCandySegment(5, 2)); // Magikarp L20
		seq(new TextSegment());
		seq(new TextSegment()); // evolution
		seq(new SkipTextsSegment(1)); // learned Bite
		seq(new UseRareCandySegment(1, 1)); // Squirtle Lxxx
		seq(new TextSegment());
		seq(new TextSegment()); // evolution
		seq(new UseRareCandySegment(1, 0)); // Wartortle Lxxx
		seq(new TextSegment());
		seq(new TextSegment()); // evolution
		seq(new UseRareCandySegment(1, 1)); // Bulbasaur Lxxx
		seq(new TextSegment());
		seq(new TextSegment()); // evolution
		seq(new UseRareCandySegment(1, 0)); // Bulbasaur Lxxx
		seq(new TextSegment());
		seq(new TextSegment()); // evolution
		seq(new UseRareCandySegment(4, 1)); // Omanyte L34
		seq(new SkipTextsSegment(1)); // learned Horn Attack
		seq(new UseRareCandySegment(5, 0)); // Omanyte L39
		seq(new SkipTextsSegment(1)); // learned Leer
		seq(new UseRareCandySegment(1, 0)); // Omanyte L40
		seq(new TextSegment());
		seq(new TextSegment()); // evolution
		seq(Segment.repress(Move.B));
		seq(Move.START);
		seq(Segment.press(Move.A)); // use PC
		seq(new SkipTextsSegment(1)); // booted PC
		seq(Segment.press(Move.A)); // someones PC
		seq(new SkipTextsSegment(2)); // someones PC, mon storage system
		seq(Segment.scroll(1)); // deposit
		seq(new DepositMonSegment(2)); // Gyarados
		seq(new DepositMonSegment(2)); // Blastoise
		seq(new DepositMonSegment(2)); // Venusaur
		seq(new DepositMonSegment(2)); // Omastar
		seq(Segment.menu(Move.UP)); // withdraw
		seq(new WithdrawMonSegment(5)); // Kabuto
		seq(Segment.menu(Move.DOWN));
		seq(Segment.menu(Move.DOWN));
		seq(Segment.menu(Move.DOWN)); // change box
		seq(Segment.menu(Move.A)); // change box
		seq(new SkipTextsSegment(2)); // data saved
		seq(new SkipTextsSegment(1, true)); // ok
		seq(Segment.menu(Move.DOWN)); // switch to box 5
		seq(Segment.menu(Move.A)); // switch to box 5
		seq(Segment.menu(Move.B)); // leave
		seq(Segment.menu(Move.B)); // leave
		seq(new WalkToSegment(3, 4));
		seq(new WalkToSegment(3, 3));
		seq(new MoveSegment(new OverworldInteract(1))); // talk to nurse
		seq(new SkipTextsSegment(3));
		seq(Segment.press(Move.A)); // confirm healing mons
		seq(new TextSegment()); // need your mons
		seq(new SkipTextsSegment(3));
		seq(new WalkToSegment(3, 8, false)); // leave center
//
//		save("om7");
	}
}
