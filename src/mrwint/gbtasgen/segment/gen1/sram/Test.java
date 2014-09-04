package mrwint.gbtasgen.segment.gen1.sram;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.WriteMemory;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.TextSegment;
import mrwint.gbtasgen.segment.gen1.common.NamingSegment;
import mrwint.gbtasgen.segment.gen1.common.SwapWithSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;

public class Test extends SeqSegment {

	SequenceSegment sequence;

	@Override
	public void execute() {
		for (int i=0xd163; i<=0xd2f6; i++)
			seq(new WriteMemory(i, 0xFF));

		seq(new WriteMemory(0xd359, 0x64));
		seq(new WriteMemory(0xd35a, 0xd0));
		
		seq(Move.START);
		seq(Move.A);
		seq(Segment.scrollA(1)); // 2nd
		{
			seq(Segment.skip(1));
			seq(Segment.scrollAF(1));
			seq(Segment.skip(1));
		}
		seq(Segment.scrollA(8)); // 10th

		seq(Segment.scrollA(2)); // 12th
		{
			seq(Segment.skip(1));
			seq(Segment.scrollAF(1));
			seq(Segment.skip(1));
		}
		seq(Segment.scrollA(1)); // 13th

		seq(Segment.skip(1));
		seq(Move.A); // 13th
		{
			seq(Segment.skip(1));
			seq(Segment.scrollAF(1));
		}
		seq(Segment.scrollA(-2)); // 11th

		seq(Move.B);
		seq(Segment.scrollA(1));
		seq(Segment.scrollFast(8)); // Item 9
		seq(new SwapWithSegment(2)); // Item 11
		seq(Move.B);
		
		seq(Segment.scrollA(-1));

		seq(Segment.skip(1));
		seq(Move.A); // 11th
		{
			seq(Segment.skip(1));
			seq(Segment.scrollAF(1));
			seq(Segment.skip(1));
		}
		seq(Segment.scrollA(1)); // 12th
		seq(Move.B);
		seq(new WriteMemory(0xd36e, 0xbb));
		seq(Move.START);
	}
}
