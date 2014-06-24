package mrwint.gbtasgen.segment.gen1.common;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.TextSegment;
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
		seq(monscroll == 0 ? Segment.repress(Move.A) : Segment.scrollA(monscroll)); // mon
		seq(new TextSegment());
		seq(new TextSegment()); // evolution
	}
}
