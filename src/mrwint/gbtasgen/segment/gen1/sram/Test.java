package mrwint.gbtasgen.segment.gen1.sram;

import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.move.WriteMemory;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.TextSegment;
import mrwint.gbtasgen.segment.WalkToSegment;
import mrwint.gbtasgen.segment.gen1.common.NamingSegment;
import mrwint.gbtasgen.segment.gen1.common.SwapWithSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.util.Util;

public class Test extends SeqSegment {

	SequenceSegment sequence;

	@Override
	public void execute() {

		seq(Move.START);
		seq(Move.A);

//		{
//			seq(Segment.scrollA(1)); // 2nd
//			seq(Segment.scrollA(1)); // swap
//			seq(Segment.scrollA(8)); // 10th
//
//			seq(Segment.scrollA(6)); // 16th
//			seq(Segment.scrollA(1)); // swap
//			seq(Segment.scrollA(8)); // 24th
//
//			seq(Move.B);
//			seq(Move.START);
//			seq(new WalkToSegment(7, 1));
//
//			seq(new SkipTextsSegment(1));
//		}

//		{
//			seq(Segment.scrollA(1)); // 2nd
//			seq(Segment.scrollA(1)); // swap
//			seq(Segment.scrollA(8)); // 10th
//
//			seq(Segment.scrollA(-1)); // 9th
//			seq(Segment.scrollA(1)); // swap
//			seq(Segment.scrollA(2)); // 11th
//
//			seq(Segment.scrollA(4)); // 15th
//			seq(Segment.scrollA(1)); // swap
//			seq(Segment.scrollA(2)); // 17th
//
//			seq(Move.B);
//			seq(Segment.scrollA(1));
//			seq(Segment.scrollFast(30)); // Item 31
//			seq(new SwapWithSegment(2)); // Item 33
//			seq(Move.B);
//
//			seq(Move.START);
//			seq(new MoveSegment(new PressButton(Move.A, Metric.MENU_JOY, Move.B)));
////			seq(new SkipTextsSegment(1));
//		}
		{
			seq(Segment.scrollA(1)); // 2nd
			seq(Segment.scrollA(1)); // swap
			seq(Segment.scrollA(8)); // 10th

			seq(Segment.scrollA(2)); // 12th
			seq(Segment.scrollA(1)); // swap
			seq(Segment.scrollA(1)); // 13th

			seq(Segment.repress(Move.A)); // 13th
			seq(Segment.scrollA(1)); // swap
			seq(Segment.scrollA(-2)); // 11th

			seq(Move.B);
			seq(Segment.scrollA(1));
			seq(Segment.scrollFast(8)); // Item 9
			seq(new SwapWithSegment(2)); // Item 11
			seq(Move.B);

			seq(Segment.scrollA(-1));

			seq(Segment.repress(Move.A)); // 11th
			seq(Segment.scrollA(1)); // swap
			seq(Segment.scrollA(1)); // 12th
			seq(Move.B);
//			save("tmp");
			seq(Move.START);
			seq(Move.A);
			prep(new PressButton(Move.A));
			seq(new MoveSegment(new PressButton(Move.B, Metric.MENU_JOY, Move.A)));
//			seq(new SkipTextsSegment(1));
		}

//		for (int i=0x6400; i<=0x6470; i++) {
//		for (int i=0xa000; i<=0xffff; i++) {
//			load("tmp");
//			if(i % 0x100 == 0)
//				System.out.println("try " + Util.toHex(i, 4));
//			seq(new WriteMemory(0xd36d, i & 0xFF));
//			seq(new WriteMemory(0xd36e, (i>>8) & 0xFF));
//			seq(new PressButton(Move.START), 0);
//			final int ca = i;
//			seq(new Move() {
//				@Override public int getInitialKey() { return 0; }
//				@Override
//				public boolean doMove() {
//					int add = run(20, 0x5a461);
//					if (add != 0) {
//						System.out.println("FOUND: " + Util.toHex(ca, 4));
//						State.step();
//					}
//					return true;
//				}
//			});
//		}
	}
	// returns address or 0
	public static int run(int stepLimit, int... addresses) {
		int steps = 0;
		int[] moves = {Move.A, Move.B};
		while(steps < stepLimit) {
			int ret = State.step(moves[steps & 1], addresses);
			if(ret != 0)
				return ret;
			steps++;
		}
		return 0;
	}
}
