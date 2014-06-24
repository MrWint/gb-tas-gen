package mrwint.gbtasgen.segment.gen1.catchemall;

import mrwint.gbtasgen.metric.CheckEncounterMetric;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.gen1.OverworldInteract;
import mrwint.gbtasgen.move.gen1.WalkStep;
import mrwint.gbtasgen.segment.CatchMonSegment;
import mrwint.gbtasgen.segment.CatchSafariMonSegment;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.TextSegment;
import mrwint.gbtasgen.segment.WalkToSegment;
import mrwint.gbtasgen.segment.gen1.common.CancelMoveLearnSegment;
import mrwint.gbtasgen.segment.gen1.common.DepositMonSegment;
import mrwint.gbtasgen.segment.gen1.common.EncounterAndCatchSafariSegment;
import mrwint.gbtasgen.segment.gen1.common.EncounterAndCatchSegment;
import mrwint.gbtasgen.segment.gen1.common.FishAndCatchSegment;
import mrwint.gbtasgen.segment.gen1.common.ReleaseMonSegment;
import mrwint.gbtasgen.segment.gen1.common.SwapWithSegment;
import mrwint.gbtasgen.segment.gen1.common.TossItemSegment;
import mrwint.gbtasgen.segment.gen1.common.UseEvoStoneSegment;
import mrwint.gbtasgen.segment.gen1.common.UseRareCandySegment;
import mrwint.gbtasgen.segment.gen1.common.WithdrawMonSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;

public class SafariZone extends SeqSegment {

	@Override
	public void execute() {
//		seq(new WalkToSegment(35, 27));
//		seq(Segment.repress(Move.START));
//		seq(Segment.repress(Move.A)); // items
//		seq(Segment.scrollFast(31-9)); // palette
//		seq(new SwapWithSegment(4)); // saved map
//		seq(new TossItemSegment(256-0xc1)); // warp c1x0b
//		seq(Segment.repress(Move.B));
//		seq(Move.START);
//		seq(new WalkToSegment(2, 8, false));
//		seq(Segment.repress(Move.START));
//		seq(Segment.repress(Move.A)); // items
//		seq(Segment.scrollFast(4)); // text pointer
//		seq(new SwapWithSegment(-3)); // swap with tileset
//		seq(new TossItemSegment(-3)); // dig everywhere
//		seq(Segment.repress(Move.B));
//		seq(Move.START);
//
//		//DDLDDLLDDDRDRDRRRRRU
//		seq(new WalkStep(Move.DOWN, true));
//		seq(new WalkStep(Move.DOWN, true));
//		seq(new WalkStep(Move.LEFT, true));
//		seq(new WalkStep(Move.DOWN, true));
//		seq(new WalkStep(Move.DOWN, true));
//		seq(new WalkStep(Move.LEFT, true));
//		seq(new WalkStep(Move.LEFT, true));
//		seq(new WalkStep(Move.DOWN, true));
//		seq(new WalkStep(Move.DOWN, true));
//		seq(new WalkStep(Move.DOWN, true));
//		seq(new WalkStep(Move.RIGHT, true));
//		seq(new WalkStep(Move.DOWN, true));
//		seq(new WalkStep(Move.RIGHT, true));
//		seq(new WalkStep(Move.DOWN, true));
//		seq(new WalkStep(Move.RIGHT, true));
//		seq(new WalkStep(Move.RIGHT, true));
//		seq(new WalkStep(Move.RIGHT, true));
//		seq(new WalkStep(Move.RIGHT, true));
//		seq(new WalkStep(Move.RIGHT, true));
//		seq(new WalkStep(Move.UP, true));
//		seq(Segment.repress(Move.START));
//		seq(Segment.scrollA(-1)); // mons
//		seq(Segment.scrollA(1)); // mew
//		seq(Segment.repress(Move.A)); // dig
//		seq(Segment.skip(1));
//		seq(new WalkToSegment(50, 10));
//		seq(new WalkToSegment(7, 2, false)); // ledge
//		seq(new EncounterAndCatchSegment(new CheckEncounterMetric(0, 21, 22), Move.RIGHT, 188)); // Bellsprout
//		seq(new WalkToSegment(11, 2, false)); // ledge
//		seq(new EncounterAndCatchSegment(new CheckEncounterMetric(57, 20), Move.DOWN)); // Mankey
//		seq(new WalkToSegment(10, 4, false)); // ledge
//		seq(new EncounterAndCatchSegment(new CheckEncounterMetric(185, 22), Move.DOWN)); // Oddish
//		seq(new WalkToSegment(12, 10, false)); // gate
		
//		save("sz1");
//		load("sz1");
//
//		seq(new WalkToSegment(1, 4));
//		seq(Segment.repress(Move.START));
//		seq(Segment.scrollA(2)); // items
//		seq(Segment.scrollFast(33)); // Y abs
//		seq(new SwapWithSegment(1)); // swap with X abs
//		seq(new SwapWithSegment(1)); // swap with saved map
//		seq(Segment.scrollFast(-2)); // Y abs
//		seq(new SwapWithSegment(2)); // swap with saved map
//		seq(Segment.repress(Move.B)); // cancel
//		seq(Move.START); // cancel
//		seq(new WalkToSegment(-1, 4, false)); // wrong warp 0:3
//		seq(new WalkStep(Move.DOWN, true));
//		seq(Segment.repress(Move.START));
//		seq(Segment.scrollA(-1)); // mons
//		seq(Segment.scrollA(1)); // mew
//		seq(Segment.scrollA(1)); // fly
//		seq(Segment.scrollA(4)); // vermilion
//		seq(Segment.skip(1));
//		seq(new WalkToSegment(40, 14)); // leave vermilion
//		seq(new WalkToSegment(4, 5)); // enter diglett's cave
//		seq(new WalkToSegment(4, 4)); // enter diglett's cave
//		seq(new WalkToSegment(36, 31));
//		seq(new EncounterAndCatchSegment(102, Move.LEFT, true, 4, 2)); // Eevee
//		seq(Segment.repress(Move.START));
//		seq(Segment.scrollA(1)); // mons
//		seq(Segment.scrollA(1)); // mew
//		seq(Segment.repress(Move.A)); // dig
//		seq(Segment.skip(1));
//		
//		save("sz2");
//		load("sz2");
//
//		seq(new WalkToSegment(25, 4, false)); // eevee house
//		seq(new WalkToSegment(2, 1));
//		seq(new WalkToSegment(4, 1));
//		seq(new WalkToSegment(2, 1));
//		seq(new WalkToSegment(2, 7));
//		seq(new WalkToSegment(4, 3, false));
//		seq(new OverworldInteract(2));
//		seq(new TextSegment()); // got Eevee
//		seq(new SkipTextsSegment(6)); // no nick, no room
//		seq(new WalkToSegment(3, 8, false));
//		seq(new WalkToSegment(2, 1));
//		seq(new WalkToSegment(4, 1));
//		seq(new WalkToSegment(2, 1));
//		seq(new WalkToSegment(4, 0));
//		{
//			seq(Segment.repress(Move.START));
//			seq(Segment.scrollA(1)); // items
//			seq(Segment.scrollA(5)); // JACK
//			seq(Segment.repress(Move.A)); // use
//			seq(Move.B); // cancel
//			seq(Segment.repress(Move.B)); // cancel
//			seq(Move.START); // cancel
//		}
//		seq(new WalkStep(Move.LEFT, true));
//		seq(new WalkStep(Move.LEFT, true));
//		seq(new WalkStep(Move.LEFT, true));
//		seq(new WalkStep(Move.LEFT, true));
//		seq(new WalkStep(Move.DOWN, true));
//		{
//			seq(Segment.repress(Move.START));
//			seq(Segment.scrollA(-1)); // mons
//			seq(Move.B); // cancel
//			seq(Move.START); // cancel
//		}
//		seq(new WalkToSegment(-1, 18));
//		seq(new WalkToSegment(27, 10));
//		seq(Segment.repress(Move.START));
//		seq(Segment.scrollA(1)); // items
//		seq(Segment.scrollA(15)); // Poke flute
//		seq(Segment.repress(Move.A)); // use
//		seq(new SkipTextsSegment(3)); // snorlax
//		seq(new CatchMonSegment(0)); // Snorlax
//		seq(new WalkToSegment(23, 10, false));
//		
//		save("sz3");
//		load("sz3");
//
//		seq(new WalkToSegment(1, 8));
//		seq(Segment.repress(Move.START));
//		seq(Segment.scrollA(2)); // items
//		seq(Segment.scrollFast(2)); // Rare Candy
//		seq(new UseRareCandySegment(1, -2)); // Oddish L23
//		seq(new TextSegment());
//		seq(new TextSegment()); // evolution
//		seq(new UseRareCandySegment(1, -1)); // Mankey L21
//		seq(new SkipTextsSegment(1)); // Fury Swipes
//		seq(new UseRareCandySegment(6, 0)); // Mankey L27
//		seq(new CancelMoveLearnSegment()); // Focus Energy
//		seq(new UseRareCandySegment(1, 0)); // Mankey L28
//		seq(new TextSegment());
//		seq(new TextSegment()); // evolution
//		seq(new UseRareCandySegment(1, -1)); // Bellsprout L23
//		seq(new TextSegment());
//		seq(new TextSegment()); // evolution
//		seq(Segment.scrollFast(5)); // leaf stone
//		seq(new UseEvoStoneSegment(0)); // Victreebel
//		seq(new UseEvoStoneSegment(2)); // Vileplume
//		seq(Segment.scrollFast(1)); // thunderstone
//		seq(new UseEvoStoneSegment(1)); // jolteon
//
//		seq(Segment.scrollFast(25)); // Y abs
//		seq(new SwapWithSegment(1)); // swap with X abs
//		seq(new SwapWithSegment(1)); // swap with saved map
//		seq(Segment.scrollFast(-2)); // Y abs
//		seq(new SwapWithSegment(2)); // swap with saved map
//		seq(new TossItemSegment(256-217));
//		seq(Segment.repress(Move.B)); // cancel
//		seq(Move.START); // cancel
//		seq(new WalkToSegment(-1, 8, false)); // wrong warp 217:0
//
//		seq(new WalkToSegment(8, 3));
//		seq(new EncounterAndCatchSafariSegment(70, Move.RIGHT)); // Doduo
//		seq(new WalkToSegment(11, 3));
//		seq(new EncounterAndCatchSafariSegment(12, Move.RIGHT)); // Exeggcute
//		seq(new WalkToSegment(10, 3));
//		seq(new EncounterAndCatchSafariSegment(109, Move.LEFT)); // Paras
//		seq(new WalkToSegment(11, 3));
//		seq(new EncounterAndCatchSafariSegment(2, Move.RIGHT)); // Kangaskhan
//		seq(new WalkToSegment(10, 3));
//		seq(new EncounterAndCatchSafariSegment(15, Move.LEFT)); // NidoranF
//		seq(new WalkToSegment(11, 3));
//		seq(new EncounterAndCatchSafariSegment(46, Move.RIGHT)); // Parasect
//		seq(new WalkToSegment(10, 3));
//		seq(new EncounterAndCatchSafariSegment(26, Move.LEFT)); // Scyther
//		seq(new WalkToSegment(11, 3));
//		seq(new WalkToSegment(-1, 5, false)); // north
//		
//		save("sz4");
//		load("sz4");
//
//		seq(new WalkToSegment(28, 32));
//		seq(new EncounterAndCatchSafariSegment(18, Move.LEFT)); // Rhyhorn
//		seq(new WalkToSegment(26, 33));
//		seq(new EncounterAndCatchSafariSegment(60, Move.LEFT)); // Tauros
//		seq(new WalkToSegment(23, 33));
//		seq(new EncounterAndCatchSafariSegment(3, Move.LEFT)); // NidoranM
//		seq(new WalkToSegment(21, 36, false)); // center
//		seq(new WalkToSegment(15, 3));
//		seq(new EncounterAndCatchSafariSegment(65, Move.DOWN)); // Venonat
//		
//		save("sz5");
//		load("sz5");
//
//		seq(new WalkToSegment(14, 6));
//		seq(new WalkToSegment(14, 7));
//		seq(Segment.repress(Move.START));
//		seq(Segment.scrollA(1)); // mons
//		seq(Segment.scrollA(1)); // mew
//		seq(Segment.scrollA(2)); // surf
//		seq(new SkipTextsSegment(1)); // got on mew
//		seq(new WalkStep(Move.DOWN, true));
//
//		seq(Segment.repress(Move.START));
//		seq(Segment.scrollA(1)); // items
//		seq(Segment.scrollFast(18)); // mansion text pointer
//		seq(new SwapWithSegment(21)); // swap with text pointer
//		seq(Segment.repress(Move.B));
//		seq(Move.START);
//		seq(new OverworldInteract(1));
//		seq(new SkipTextsSegment(2));
//		seq(new CatchSafariMonSegment());
//		seq(Segment.repress(Move.START));
//		seq(Segment.scrollA(1)); // mons
//		seq(Segment.scrollA(1)); // mew
//		seq(Segment.repress(Move.A)); // dig
//		seq(Segment.skip(1));
//		seq(Move.START);
//		seq(Segment.repress(Move.A)); // mons
//		seq(Segment.repress(Move.A)); // mew
//		seq(Segment.scrollA(1)); // fly
//		seq(Segment.scrollA(-2)); // pewter
//		
//		save("sz6");
//		load("sz6");
//		seq(Segment.skip(1));
//
//		seq(new WalkToSegment(14, 7));
//		seq(new WalkToSegment(9, 5));
//		seq(new WalkToSegment(9, 4));
//		seq(new SkipTextsSegment(1));
//		seq(new SkipTextsSegment(1, true)); // buy ticket
//		seq(new SkipTextsSegment(1));
//		seq(new WalkToSegment(7, 7));
//		seq(new WalkToSegment(13, 1));
//		seq(Segment.repress(Move.START));
//		seq(Move.A); // mons
//		seq(Move.B); // cancel
//		seq(Move.START); // cancel
//		seq(new WalkToSegment(7, 7));
//		seq(new WalkToSegment(10, 8, false));
//		seq(new WalkToSegment(18, 36));
//		seq(new WalkToSegment(8, 2));
//		seq(new EncounterAndCatchSegment(72, Move.LEFT, true)); // Jynx
//		seq(new WalkToSegment(8, -1));
//		seq(new WalkToSegment(31, 2));
//		seq(Segment.repress(Move.START));
//		seq(Segment.scrollA(1)); // mons
//		seq(Move.B); // cancel
//		seq(Move.START); // cancel
//		seq(new WalkToSegment(19, 36));
//		seq(new WalkToSegment(8, 2));
//		seq(new EncounterAndCatchSegment(43, Move.LEFT, true)); // Hitmonlee
//		seq(Segment.repress(Move.START));
//		seq(Segment.scrollA(1)); // mons
//		seq(Segment.scrollA(1)); // mew
//		seq(Segment.scrollA(1)); // fly
//		seq(Segment.scrollA(4)); // vermilion
//		
//		save("sz7");
//		load("sz7");
//		seq(Segment.skip(1));
//
//		seq(new WalkToSegment(11, 3));
//		seq(new WalkToSegment(13,4));
//		seq(new WalkToSegment(13,3, false)); // correct facing direction
//		seq(Segment.press(Move.A)); // use PC
//		seq(new SkipTextsSegment(1)); // booted PC
//		seq(Segment.press(Move.A)); // someones PC
//		seq(new SkipTextsSegment(2)); // someones PC, mon storage system
//		seq(Segment.scroll(2)); // release
//		seq(new ReleaseMonSegment(0)); // Hitmonlee
//		seq(new ReleaseMonSegment(0)); // Jynx
//		seq(Segment.menu(Move.UP)); // deposit
//		seq(new DepositMonSegment(0)); // Ditto
//		seq(new DepositMonSegment(1)); // Victreebel
//		seq(new DepositMonSegment(1)); // Primeape
//		seq(new DepositMonSegment(1)); // Vileplume
//		seq(new DepositMonSegment(1)); // Jolteon
//		seq(Segment.menu(Move.UP)); // withdraw
//		seq(new WithdrawMonSegment(2)); // NidoM
//		seq(new WithdrawMonSegment(6)); // NidoF
//		seq(new WithdrawMonSegment(8)); // Exeggcute
//		seq(new WithdrawMonSegment(10)); // Eevee
//		seq(Segment.menu(Move.B)); // leave
//		seq(Segment.menu(Move.B)); // leave
//		seq(Segment.skip(1));
//		seq(Segment.repress(Move.START));
//		seq(Segment.scrollA(1)); // items
//		seq(Segment.scrollFast(2)); // rare candy
//		seq(new UseRareCandySegment(1, 1)); // NidoM Lxx
//		seq(new TextSegment());
//		seq(new TextSegment()); // evolution
//		seq(new UseRareCandySegment(1, 1)); // NidoF Lxx
//		seq(new TextSegment());
//		seq(new TextSegment()); // evolution
//		seq(Segment.scrollFast(1)); // moon stone
//		seq(new UseEvoStoneSegment(-1)); // Nidoking
//		seq(new CancelMoveLearnSegment()); // Thrash
//		seq(new UseEvoStoneSegment(1)); // Nidoqueen
//		seq(Segment.scrollFast(6)); // leaf stone
//		seq(new UseEvoStoneSegment(1)); // Exeggutor
//		seq(Segment.scrollFast(8)); // fire stone
//		seq(new UseEvoStoneSegment(1)); // Flareon
//		seq(Segment.repress(Move.B));
//		seq(Move.START);
//		seq(Segment.press(Move.A)); // use PC
//		seq(new SkipTextsSegment(1)); // booted PC
//		seq(Segment.press(Move.A)); // someones PC
//		seq(new SkipTextsSegment(2)); // someones PC, mon storage system
//		seq(Segment.scroll(1)); // deposit
//		seq(new DepositMonSegment(1)); // Nidoking
//		seq(new DepositMonSegment(1)); // Nidoqueen
//		seq(new DepositMonSegment(1)); // Exeggutor
//		seq(new DepositMonSegment(1)); // Flareon
//		seq(Segment.menu(Move.B)); // leave
//		seq(Segment.menu(Move.B)); // leave
//		seq(new WalkToSegment(4, 7));
//
//		save("sz8");
		load("sz8");

		seq(Segment.skip(1));
		seq(Segment.repress(Move.START));
		seq(Segment.repress(Move.A)); // items
		seq(Segment.scrollFast(31-14)); // palette
		seq(new SwapWithSegment(4)); // saved map
		seq(new TossItemSegment(-0x76)); // warp 76:0
		seq(Segment.repress(Move.B));
		seq(Move.START);
		seq(new WalkToSegment(4, 8, false));
		seq(new SkipTextsSegment(15)); // Oak HoF speech
	}
}
