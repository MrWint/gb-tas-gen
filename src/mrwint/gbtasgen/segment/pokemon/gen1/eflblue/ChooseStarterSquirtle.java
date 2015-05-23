package mrwint.gbtasgen.segment.pokemon.gen1.eflblue;

import mrwint.gbtasgen.metric.pokemon.gen1.Gen1CheckDVMetric;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.SkipInput;
import mrwint.gbtasgen.move.pokemon.ChangeOptionsMove;
import mrwint.gbtasgen.move.pokemon.EflChangeOptionsMove;
import mrwint.gbtasgen.move.pokemon.gen1.EflOverworldInteract;
import mrwint.gbtasgen.move.pokemon.gen1.EflWalkStep;
import mrwint.gbtasgen.move.pokemon.gen1.OverworldInteract;
import mrwint.gbtasgen.move.pokemon.gen1.WalkStep;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.pokemon.EflWalkToSegment;
import mrwint.gbtasgen.segment.pokemon.TextSegment;
import mrwint.gbtasgen.segment.pokemon.WalkToSegment;
import mrwint.gbtasgen.segment.util.CheckMetricSegment;
import mrwint.gbtasgen.segment.util.DelayMoveSegment;
import mrwint.gbtasgen.segment.util.DelayMoveSegment.PressButtonFactory;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.util.EflUtil.PressMetric;

public class ChooseStarterSquirtle extends SeqSegment {

	@Override
	public void execute() {
//    seqMove(EflChangeOptionsMove.get(false)); // set options
//    seq(new EflWalkToSegment(7, 1)); // go downstairs
//		seq(new EflWalkToSegment(3, 6)); // leave house
//		seq(new EflWalkToSegment(3, 8, false)); // leave house
//		seq(new EflWalkToSegment(10, 1)); // walk into grass
//
//    save("tmp");
//    load("tmp");
//
//		seq(new EflTextSegment()); // OAK: don't go out
//		seqEflButton(0); // skip holding key
////		// oak in grass
////		seq(new EflSkipTextsSegment(1));
//    seq(new EflSkipTextsSegment(6));
////    seq(new SkipTextsSegment(6));
////		// go to oak's house
//    seq(new EflSkipTextsSegment(18));
////
//    save("tmp2");
    load("tmp2");

//    seqMove(new WalkStep(Move.DOWN, true)); // test
//    seqMove(new EflWalkStep(Move.DOWN, true)); // test

//    seq(new WalkToSegment(7, 3, false)); // walk to ball
    seq(new EflWalkToSegment(7, 3, false)); // walk to ball
		seqMove(new EflOverworldInteract(3)); // Squirtle ball

		seqEflButton(Move.B); // cancel dex
		seqEflButton(Move.A); // cancel dex

		// chose mon text
		seq(new EflSkipTextsSegment(1)); // do you want?
		seq(new EflSkipTextsSegment(1, true)); // want!
		seq(new EflSkipTextsSegment(1)); // energetic

    seq(new EflSkipTextsSegment(1)); // received! ; want to give a nick
		seq(new EflTextSegment(Move.B, 0)); // to Squirtle?
		seqEflButtonUnbounded(Move.A); // (yes)
    seqEflButtonUnbounded(Move.A, PressMetric.PRESSED); // "A"
    delayEfl(new SeqSegment() {
      @Override
      protected void execute() {
        seqEflButtonUnbounded(Move.START);
        seq(new CheckMetricSegment(new Gen1CheckDVMetric(15, 0, 15, 15, 15)));
      }
    });
//    seqEflButtonUnbounded(Move.START); // name it "A"
////		seq(new SkipTextsSegment(1, true)); // to Squirtle (yes)
//		seq(new MoveSegment(new SkipInput(1), 0, 0)); // wait to input "A"
//		seq(Segment.press(Move.A, 0)); // "A"
////		seq(Segment.press(Move.START)); // name it "A"
//		seq(new DelayMoveSegment(new PressButtonFactory(Move.START), new CheckMetricSegment(new Gen1CheckDVMetric(15, 0, 15, 15, 15))));
//
		seq(new EflSkipTextsSegment(2)); // rival choose mon
		seq(new EflWalkToSegment(5, 6)); // try leaving
		seq(new EflSkipTextsSegment(4)); // rival challenges you
	}
}
