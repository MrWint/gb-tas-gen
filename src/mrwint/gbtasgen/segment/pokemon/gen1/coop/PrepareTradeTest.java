package mrwint.gbtasgen.segment.pokemon.gen1.coop;

import mrwint.gbtasgen.segment.pokemon.EflWalkToSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;

public class PrepareTradeTest extends SeqSegment {

	@Override
	public void execute() {
    seq(new EflWalkToSegment(23, 25)); // enter center
    seq(new EflWalkToSegment(11, 4)); // cable club
    seq(new EflWalkToSegment(11, 3)); // cable club
	}
}
