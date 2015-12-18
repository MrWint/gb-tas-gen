package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import static mrwint.gbtasgen.move.Move.A;
import static mrwint.gbtasgen.move.Move.B;
import static mrwint.gbtasgen.move.Move.LEFT;
import static mrwint.gbtasgen.move.Move.RIGHT;
import static mrwint.gbtasgen.move.Move.START;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.PRESSED;
import mrwint.gbtasgen.metric.pokemon.gen1.Gen1CheckDVMetric;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.pokemon.gen1.EflOverworldInteract;
import mrwint.gbtasgen.move.pokemon.gen1.EflWalkStep;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.pokemon.EflWalkToSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflChangeOptionsSegment;
import mrwint.gbtasgen.segment.util.CheckMetricSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.util.EflUtil.PressMetric;

public class ChooseStarterDummyRed extends SeqSegment {

	@Override
	public void execute() {
    seq(EflChangeOptionsSegment.fromOverworld()); // set options
    seq(new EflWalkToSegment(7, 1)); // go downstairs
		seq(new EflWalkToSegment(3, 6)); // leave house
		seq(new EflWalkToSegment(3, 8, false)); // leave house
		seq(new EflWalkToSegment(10, 1)); // walk into grass
		seq(new EflTextSegment()); // OAK: don't go out
		seqEflButton(0); // skip holding key
    seq(new EflSkipTextsSegment(6));
    seq(new EflSkipTextsSegment(18));

    seqMove(new EflWalkStep(RIGHT, false, true), 0); // walk to ball
		seqMove(new EflOverworldInteract(2)); // Charmander ball

		seqEflButton(B); // cancel dex
		seqEflButton(A); // cancel dex

		// chose mon text
		seq(new EflSkipTextsSegment(1)); // do you want?
		seq(new EflSkipTextsSegment(1, true)); // want!
		seq(new EflSkipTextsSegment(1)); // energetic

		seqUnbounded(new EflSkipTextsSegment(2)); // received! ; want to give a nick
		seqUnbounded(new EflSkipTextsSegment(1, true)); // to Charmander? (yes)
    seqEflButtonUnbounded(LEFT); // I
    seqEflButtonUnbounded(A, PRESSED); // "I"
    delayEfl(new SeqSegment() {
      @Override
      protected void execute() {
        seqEflButtonUnbounded(START);
        seqMetric(new Gen1CheckDVMetric(15, 0, 12, 15, 15));
//        seqMetric(new Gen1CheckDVMetric(15, 0, 15, 15, 15));
      }
    });
		seq(new EflSkipTextsSegment(2)); // rival choose mon
		seq(new EflWalkToSegment(5, 6)); // try leaving
		seq(new EflSkipTextsSegment(4)); // rival challenges you
	}
}
