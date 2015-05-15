package mrwint.gbtasgen.segment.pokemon.gen1.sram;

import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.move.WriteMemory;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.TextSegment;
import mrwint.gbtasgen.segment.pokemon.WalkToSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.NamingSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.SwapWithSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.util.Util;

public class TestRed extends SeqSegment {

	SequenceSegment sequence;

	@Override
	public void execute() {

		seqButton(Move.DOWN);
		seqButton(Move.START);
		seqButton(Move.A);

		{
			seq(Segment.scrollAF(3)); // 4th
			seq(Segment.skip(1));
			seq(Segment.scrollAF(1)); // swap
			seq(Segment.skip(1));
			seq(Segment.scrollAF(9)); // 13th

			seq(Segment.skip(1));
			seq(Segment.scrollAF(-1)); // 11th
			seq(Segment.skip(1));
			seq(Segment.scrollAF(1)); // swap
			seq(Segment.skip(1));
			seq(Segment.scrollAF(-1)); // 12th

			seq(Segment.skip(1));
			seq(Segment.scrollAF(-1)); // 13th
			seq(Segment.skip(1));
			seq(Segment.scrollAF(1)); // swap
			seq(Segment.skip(1));
			seq(Segment.scrollAF(3)); // 10th

			seqButton(Move.B);
			seq(Segment.scrollA(1));
			seq(new SwapWithSegment(4)); // swap Item 1 with Item 5
			seq(Segment.scrollFast(-2)); // Item 3
			seq(new SwapWithSegment(10)); // Item 13
			seqButton(Move.B);

			seq(Segment.scrollA(-1));

			seq(Segment.scrollAF(4)); // 17th
			seq(Segment.skip(1));
			seq(Segment.scrollAF(1)); // swap
			seq(Segment.skip(1));
			seq(Segment.scrollAF(5)); // 22th

			seq(Segment.skip(1));
			seq(Segment.scrollAF(1)); // 23th
			seq(Segment.skip(1));
			seq(Segment.scrollAF(1)); // swap
			seq(Segment.scrollAF(-5)); // 18th

			seqButton(Move.B);
			seqButton(Move.START);
		}


//		{
//			seq(Segment.scrollAF(3)); // 4th
//			seq(Segment.skip(1));
//			seq(Segment.scrollAF(1)); // swap
//			seq(Segment.skip(1));
//			seq(Segment.scrollAF(9)); // 13th
//
//			seq(Segment.scrollAF(-2)); // 11th
//			seq(Segment.skip(1));
//			seq(Segment.scrollAF(1)); // swap
//			seq(Segment.skip(1));
//			seq(Segment.scrollAF(1)); // 12th
//
//			seq(Segment.skip(1));
//			seq(Segment.scrollAF(1)); // 13th
//			seq(Segment.skip(1));
//			seq(Segment.scrollAF(1)); // swap
//			seq(Segment.scrollAF(-3)); // 10th
//
//			seq(Move.B);
//			seq(Segment.scrollA(1));
//			seq(new SwapWithSegment(4)); // swap Item 1 with Item 5
//			seq(Segment.scrollFast(-2)); // Item 3
//			seq(new SwapWithSegment(10)); // Item 13
//			seq(Move.B);
//
//			seq(Segment.scrollA(-1));
//
//			seq(Segment.skip(1));
//			seq(Segment.scrollAF(1)); // 11th
//			seq(Segment.skip(1));
//			seq(Segment.scrollAF(1)); // swap
//			seq(Segment.skip(1));
//			seq(Segment.scrollAF(1)); // 12th
//
//			seq(Segment.skip(1));
//			seq(Segment.scrollAF(3)); // 15th
//			seq(Segment.skip(1));
//			seq(Segment.scrollAF(1)); // swap
//			seq(Segment.skip(1));
//			seq(Segment.scrollAF(1)); // 16th
//
//			seq(Segment.skip(1));
//			seq(Segment.scrollAF(1)); // 17th
//			seq(Segment.skip(1));
//			seq(Segment.scrollAF(1)); // swap
//			seq(Segment.scrollAF(-2)); // 15th
//
//			seq(Move.B);
//			seq(Move.START);
//		}

//		{
//			seq(Segment.scrollAF(3)); // 4th
//			seq(Segment.skip(1));
//			seq(Segment.scrollAF(1)); // swap
//			seq(Segment.skip(1));
//			seq(Segment.scrollAF(9)); // 13th
//
//			seq(Segment.skip(1));
//			seq(Segment.scrollAF(-1)); // 12th
//			seq(Segment.skip(1));
//			seq(Segment.scrollAF(1)); // swap
//			seq(Segment.skip(1));
//			seq(Segment.scrollAF(-1)); // 11th
//
//			seq(Segment.skip(1));
//			seq(Segment.scrollAF(-1)); // 10th
//			seq(Segment.skip(1));
//			seq(Segment.scrollAF(1)); // swap
//			seq(Segment.skip(1));
//			seq(Segment.scrollAF(3)); // 13th
//
//			seq(Move.B);
//			seq(Segment.scrollA(1));
//			seq(new SwapWithSegment(4)); // swap Item 1 with Item 5
//			seq(Segment.scrollFast(-2)); // Item 3
//			seq(new SwapWithSegment(10)); // Item 13
//			seq(Move.B);
//
//			seq(Segment.scrollA(-1));
//
//			seq(Segment.skip(1));
//			seq(Segment.scrollAF(-1)); // 12th
//			seq(Segment.skip(1));
//			seq(Segment.scrollAF(1)); // swap
//			seq(Segment.skip(1));
//			seq(Segment.scrollAF(-1)); // 11th
//
//			seq(Segment.scrollAF(4)); // 15th
//			seq(Segment.skip(1));
//			seq(Segment.scrollAF(1)); // swap
//			seq(Segment.skip(1));
//			seq(Segment.scrollAF(1)); // 16th
//
//			seq(Segment.skip(1));
//			seq(Segment.scrollAF(1)); // 17th
//			seq(Segment.skip(1));
//			seq(Segment.scrollAF(1)); // swap
//			seq(Segment.scrollAF(-2)); // 15th
//
//			seq(Move.B);
//			seq(Move.START);
//		}
	}
}
