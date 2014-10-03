package mrwint.gbtasgen.segment.pokemon.gen1.noww;

import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.metric.pokemon.gen1.Gen1CheckDVMetric;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.move.pokemon.ChangeOptionsMove;
import mrwint.gbtasgen.move.pokemon.gen1.OverworldInteract;
import mrwint.gbtasgen.rom.RomInfo;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.TextSegment;
import mrwint.gbtasgen.segment.pokemon.WalkToSegment;
import mrwint.gbtasgen.segment.util.CheckMetricSegment;
import mrwint.gbtasgen.segment.util.DelayMoveSegment;
import mrwint.gbtasgen.segment.util.DelayMoveSegment.PressButtonFactory;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.segment.util.SplitSegment;

public class ChooseStarter extends SeqSegment {

	@Override
	public void execute() {
//		seq(Move.START);
//		seq(Segment.repress(Move.UP));
//		seq(Segment.repress(Move.UP));
//		seq(Move.A); // options
//		seq(Move.LEFT); // text speed
//		seq(Move.DOWN);
//		seq(Move.LEFT); // battle scene
//		seq(Move.DOWN);
//		seq(Move.LEFT); // battle style
//		seq(Move.B);
//		seq(Move.START);
//
////		seq(new MoveSegment(ChangeOptionsMove.get(false))); // set options
//		seq(new WalkToSegment(7, 1)); // go downstairs
//		seq(new WalkToSegment(4, 7)); // leave house
//		seq(new WalkToSegment(3, 8, false)); // leave house
//		seq(new WalkToSegment(10, 1)); // walk into grass
//
//		seq(new TextSegment()); // OAK: don't go out
//		// oak in grass
//		seq(new SkipTextsSegment(6));
//		// go to oak's house
//		seq(new SkipTextsSegment(18));
//
//		seq(RomInfo.rom.getWalkStep(Move.RIGHT,false,true),0);
//		seq(new OverworldInteract(2));
//
//		seq(Segment.press(Move.B)); // cancel dex
//		seq(Segment.press(Move.A)); // cancel dex
//
//		// chose mon text
//		seq(new SkipTextsSegment(1)); // do you want?
//		seq(new SkipTextsSegment(1, true)); // want!
//		seq(new SkipTextsSegment(1)); // energetic
//		
//		save("cs2");
		load("cs2");

		seq(new TextSegment(Move.B));
		seq(new SkipTextsSegment(1)); // want to give a nick
		seq(new SplitSegment(new SeqSegment() {
			@Override
			protected void execute() {
				seq(new TextSegment(Move.B, true, 0)); // to charmander?
				seq(Segment.press(Move.A, 0)); // (yes)
//				seq(new SkipTextsSegment(1, true)); // to charmander (yes)
				seq(new MoveSegment(new PressButton(Move.A, Metric.PRESSED_JOY), 0, 0)); // "A"
//				seq(Segment.press(Move.START)); // name it "A"
				seq(new DelayMoveSegment(new PressButtonFactory(Move.START), new CheckMetricSegment(new Gen1CheckDVMetric(0, 0, 0, 0, 0, 15, 0x1024))).withMaxDelay(90));
			}
		}));

		seq(new SkipTextsSegment(2)); // rival choose mon
		seq(new WalkToSegment(5, 6)); // try leaving
		seq(new SkipTextsSegment(4)); // rival challenges you
	}
}
