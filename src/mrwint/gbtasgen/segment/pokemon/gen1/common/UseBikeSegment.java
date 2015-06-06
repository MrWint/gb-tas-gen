package mrwint.gbtasgen.segment.pokemon.gen1.common;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.util.EflUtil;

public class UseBikeSegment extends SeqSegment {

	int menuscroll;
	int itemscroll;
	public UseBikeSegment() {
		this(2, 1);
	}
	public UseBikeSegment(int menuscroll, int itemscroll) {
	  EflUtil.assertNoEfl();

		this.menuscroll = menuscroll;
		this.itemscroll = itemscroll;
	}

	@Override
	public void execute() {
		seq(Segment.repress(Move.START));
		seq(Segment.scrollA(menuscroll));
		if (itemscroll == 0)
			seq(Segment.repress(Move.A));
		else
			seq(Segment.scrollFastAF(itemscroll));
		seq(new SkipTextsSegment(1)); // got on bike
	}
}
