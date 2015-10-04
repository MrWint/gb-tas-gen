package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import mrwint.gbtasgen.move.pokemon.gen1.EflOverworldInteract;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.pokemon.EflWalkToSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflUseBikeSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;

public class PrepareEeveeTradeDummyRed extends SeqSegment {

	@Override
	public void execute() {
    seq(new EflWalkToSegment(25, 4, false)); // house
    seq(new EflWalkToSegment(2, 1)); // house
    seq(new EflWalkToSegment(4, 1)); // house
    seq(new EflWalkToSegment(2, 1)); // house
    seq(new EflWalkToSegment(2, 7)); // house
    seq(new EflWalkToSegment(4, 3, false)); // eevee ball
    seqMove(new EflOverworldInteract(2)); // eevee ball
    seq(new EflTextSegment()); // got eevee
    seq(new EflSkipTextsSegment(2)); // no nick
    seq(new EflWalkToSegment(3, 6)); // leave
    seq(new EflWalkToSegment(3, 8, false)); // leave
    seq(new EflWalkToSegment(2, 1)); // house
    seq(new EflWalkToSegment(4, 1)); // house
    seq(new EflWalkToSegment(2, 1)); // house
    seq(new EflWalkToSegment(4, 0)); // house
    seq(new EflUseBikeSegment(0, 0));
    seq(new EflWalkToSegment(41, 9)); // enter center
    seq(new EflWalkToSegment(11, 4)); // cable club
    seq(new EflWalkToSegment(11, 3)); // cable club
	}
}
