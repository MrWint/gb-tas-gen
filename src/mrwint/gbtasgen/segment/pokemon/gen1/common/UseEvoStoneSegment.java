package mrwint.gbtasgen.segment.pokemon.gen1.common;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.TextSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;

public class UseEvoStoneSegment extends SeqSegment {
	
	int monscroll;
	public UseEvoStoneSegment(int monscroll) {
		this.monscroll = monscroll;
	}
	
	@Override
	public void execute() {
		seq(Segment.repress(Move.A)); // select
		seq(Segment.repress(Move.A)); // use
		if (monscroll == 0)
			seq(Segment.repress(Move.A)); // mon
		else {
			if (monscroll == 1 || monscroll == -1)
				seq(Segment.skip(1));
			seq(Segment.scrollAF(monscroll));
		}
		seq(new TextSegment());
		seq(new TextSegment()); // evolution
	}
}
