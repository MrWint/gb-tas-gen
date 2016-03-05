package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import static mrwint.gbtasgen.move.Move.A;
import static mrwint.gbtasgen.move.Move.B;
import static mrwint.gbtasgen.move.Move.DOWN;
import static mrwint.gbtasgen.move.Move.START;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.PRESSED;
import mrwint.gbtasgen.metric.pokemon.gen1.Gen1CheckDVMetric;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.pokemon.gen1.EflOverworldInteract;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.pokemon.EflWalkToSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflChangeOptionsSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.NamingSegment;
import mrwint.gbtasgen.segment.util.CheckMetricSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;

public class ChooseStarterBlue extends SeqSegment {

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
    seq(new EflWalkToSegment(7, 3, false)); // walk to ball
		seqMove(new EflOverworldInteract(3)); // Squirtle ball

		seqEflButton(B); // cancel dex
		seqEflButton(A); // cancel dex

		// chose mon text
		seq(new EflSkipTextsSegment(1)); // do you want?
		seq(new EflSkipTextsSegment(1, true)); // want!
		seq(new EflSkipTextsSegment(1)); // energetic

		seqUnbounded(new EflSkipTextsSegment(2)); // received! ; want to give a nick
		seqUnbounded(new EflSkipTextsSegment(1, true)); // to Squirtle? (yes)
//    seqUnbounded(new NamingSegment("J"));
//    seqEflButtonUnbounded(DOWN); // "J"
    seqEflButtonUnbounded(A, PRESSED); // "A"
    delayEfl(new SeqSegment() {
      @Override
      protected void execute() {
        seqEflButtonUnbounded(START);
//        seqMetric(new Gen1CheckDVMetric(15, 0, 12, 15, 15));
        seqMetric(new Gen1CheckDVMetric(15, 0, 15, 15, 15));
      }
    });
		seq(new EflSkipTextsSegment(2)); // rival choose mon
		seq(new EflWalkToSegment(5, 6)); // try leaving
		seq(new EflSkipTextsSegment(4)); // rival challenges you
	}
}
