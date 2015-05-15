package mrwint.gbtasgen.segment.pokemon.gen1.demo;

import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.move.pokemon.gen1.OverworldInteract;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.ResetAndContinueSegment;
import mrwint.gbtasgen.segment.pokemon.WalkToSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;

public class BrockDemo extends SeqSegment {

	@Override
	public void execute() {
		seq(new WalkToSegment(0, 8));
		seq(new WalkToSegment(-1, 8));
		seq(new WalkToSegment(36, 16));
		seqMove(new OverworldInteract(5));
		seq(new SkipTextsSegment(4)); // pewter skip text
		for (int i=0;i<35;i++) {
			seqButton(Move.SELECT | Move.RIGHT);
		}
		seqButton(Move.SELECT | Move.UP);
		for (int i=0;i<30;i++) {
			seqButton(Move.SELECT | Move.RIGHT);
		}
		for (int i=0;i<8;i++) {
			seqButton(Move.SELECT | Move.UP);
		}
		for (int i=0;i<93;i++) {
			seqButton(Move.SELECT | Move.RIGHT);
		}
	}
}
