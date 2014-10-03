package mrwint.gbtasgen.segment.pokemon.gen1.common;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;

public class BuyItemSegment extends SeqSegment {
	
	int scroll;
	public BuyItemSegment(int amount) {
		this.scroll = 1-amount;
	}
	
	@Override
	public void execute() {
		seq(Move.A); // select
		seq(Segment.scroll(scroll));
		seq(Segment.repress(Move.A));
		seq(new SkipTextsSegment(1)); // that will be
		seq(new SkipTextsSegment(1, true)); //ok?
		seq(new SkipTextsSegment(1)); // thank you
	}
}
