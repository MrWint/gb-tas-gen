package mrwint.gbtasgen.segment.pokemon.gen1.common;

import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;

public class EflCancelMoveLearnSegment extends SeqSegment {
	
	@Override
	public void execute() {
		seq(new EflSkipTextsSegment(6));
		seq(new EflSkipTextsSegment(1, true));
		seq(new EflSkipTextsSegment(2));
	}
}
