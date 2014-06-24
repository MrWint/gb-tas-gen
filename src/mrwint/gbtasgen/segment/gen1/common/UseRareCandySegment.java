package mrwint.gbtasgen.segment.gen1.common;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.util.SeqSegment;

public class UseRareCandySegment extends SeqSegment {
	
	int num;
	int monscroll;
	public UseRareCandySegment(int num, int monscroll) {
		this.num = num;
		this.monscroll = monscroll;
	}
	
	@Override
	public void execute() {
		for (int i=0;i<num;i++) {
			seq(Segment.repress(Move.A));
			seq(Segment.repress(Move.A)); // use rare candy
			seq(Segment.scroll(monscroll));
			seq(Segment.repress(Move.A)); // mon
			seq(Move.B); // rose to lvl
			seq(Move.A); // clear stats
			
			monscroll = 0;
		}
	}
}
