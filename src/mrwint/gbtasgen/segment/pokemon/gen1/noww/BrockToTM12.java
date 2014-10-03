package mrwint.gbtasgen.segment.pokemon.gen1.noww;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.move.pokemon.gen1.OverworldInteract;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.ResetAndContinueSegment;
import mrwint.gbtasgen.segment.pokemon.TextSegment;
import mrwint.gbtasgen.segment.pokemon.WalkToSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.DepositMonSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.WithdrawMonSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;

public class BrockToTM12 extends SeqSegment {

	@Override
	public void execute() {
//		seq(new WalkToSegment(0, 8));
//		seq(new WalkToSegment(-1, 8));
//		seq(new WalkToSegment(36, 16));
//		seq(new OverworldInteract(5));
//		seq(new SkipTextsSegment(4)); // pewter skip text
//		for (int i=0;i<8;i++) {
//			seq(new PressButton(Move.RIGHT | Move.UP), 0);
//		}
//		for (int i=0;i<71;i++) {
//			seq(new PressButton(Move.RIGHT | Move.RIGHT), 0);
//		}
//		for (int i=0;i<14;i++) {
//			seq(new PressButton(Move.RIGHT | Move.UP), 0);
//		}
//		seq(new WalkToSegment(7,32));
//		seq(new WalkToSegment(6,32));
//		seq(new OverworldInteract(13));
//		seq(new TextSegment());
//		
//		save("tm1");
		load("tm1");
		
		seq(Segment.skip(1));
		seq(Move.START);
		seq(Segment.scrollA(2)); // items
		seq(Segment.scrollFastA(1)); // TM12
		seq(Segment.repress(Move.A)); // use
		seq(new SkipTextsSegment(2));
		seq(new SkipTextsSegment(1, true));
		seq(Segment.repress(Move.A)); // Nidoking
		seq(new SkipTextsSegment(5));
		seq(new SkipTextsSegment(1, true));
		seq(new TextSegment(Move.B));
		seq(Segment.press(Move.A)); // Tackle
		seq(new SkipTextsSegment(2));
		seq(Segment.press(Move.A));
		seq(new SkipTextsSegment(3));
		seq(Segment.press(Move.A)); // escape rope
		seq(Segment.repress(Move.A)); // use

		seq(new SkipTextsSegment(2));
		seq(Segment.press(Move.START)); // open menu
		seq(Segment.scroll(2)); // save
		seq(Segment.press(Move.START)); // close menu

		seq(new WalkToSegment(29, 13)); // enter house
		seq(new WalkToSegment(2, 8, false)); // leave house

		seq(new WalkToSegment(34, 18)); // walk up to pewter skip
		seq(new WalkToSegment(37, 18)); // walk up to pewter skip
		seq(new SkipTextsSegment(4)); // pewter skip text
		seq(new PressButton(Move.START), 0); // open menu
		seq(Segment.press(Move.A)); // save
		seq(new SkipTextsSegment(1, true)); // say "yes"
		seq(new ResetAndContinueSegment()); // reset
		seq(new SkipTextsSegment(2)); // pewter skip text
		seq(new WalkToSegment(40, 18)); // leave pewter
	}
}
