package mrwint.gbtasgen.segment.pokemon.gen1.common;

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
			if (monscroll == 0)
				seq(Segment.repress(Move.A)); // mon
			else {
				if (monscroll == 1 || monscroll == -1)
					seq(Segment.skip(1));
				seq(Segment.scrollAF(monscroll));
			}
			seqButton(Move.B); // rose to lvl
			seqButton(Move.A); // clear stats
			
			monscroll = 0;
		}
	}
}
