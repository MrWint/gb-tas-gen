package mrwint.gbtasgen.segment.gen1.noww;

import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.move.gen1.OverworldInteract;
import mrwint.gbtasgen.segment.PokecenterSegment;
import mrwint.gbtasgen.segment.ResetAndContinueSegment;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.TextSegment;
import mrwint.gbtasgen.segment.WalkToSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;

public class PewterNormal extends SeqSegment {

	@Override
	public void execute() {
		seq(Segment.press(Move.START)); // open menu
		for(int i=0;i<3;i++)
			seq(new PressButton(Move.UP,Metric.PRESSED_JOY)); // move to "save"
		seq(Segment.press(Move.START)); // close menu

//		seq(new WalkToSegment(13, 25)); // enter pewter center
//		seq(new PokecenterSegment(false));
//
//		seq(new WalkToSegment(23, 17)); // enter pewter mart
//
//		seq(new WalkToSegment(3, 5));
//		seq(new WalkToSegment(2, 5));
//		seq(new MoveSegment(new OverworldInteract(1)));
//		{
//			seq(new SkipTextsSegment(1, true)); // buy
//			seq(new TextSegment());
//			{
//				seq(Segment.scrollFastA(2)); // escape rope
//				seq(Segment.repress(Move.A)); // buy x1
//				seq(new SkipTextsSegment(1)); // confirmation text
//				seq(new SkipTextsSegment(1, true)); // "yes"
//				seq(new SkipTextsSegment(1)); // thank you text
//			}
//			seq(Segment.repress(Move.B)); // cancel
//			seq(new SkipTextsSegment(2)); // cancel + bye
//		}
//
//		seq(new WalkToSegment(3,8,false)); // leave mart

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
