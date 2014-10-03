package mrwint.gbtasgen.segment.pokemon.gen1.demo;

import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.ResetAndContinueSegment;
import mrwint.gbtasgen.segment.pokemon.WalkToSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;

public class PewterNormal extends SeqSegment {

	@Override
	public void execute() {
		seq(Segment.press(Move.START)); // open menu
		for(int i=0;i<3;i++)
			seq(new PressButton(Move.UP,Metric.PRESSED_JOY)); // move to "save"
		seq(Segment.press(Move.START)); // close menu

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
