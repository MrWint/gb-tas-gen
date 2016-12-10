package mrwint.gbtasgen.segment.pokemon.gen1.ace;

import static mrwint.gbtasgen.state.Gameboy.curGb;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.pokemon.EflScroll;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSwapWithSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflTossItemSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.util.EflUtil.PressMetric;
import mrwint.gbtasgen.util.Util;

public class Test extends SeqSegment {

	SequenceSegment sequence;

	@Override
	public void execute() {

		seqEflButton(Move.START);
		seqEflButton(Move.A);

		{
			seqEflScrollA(1); // 2nd
			seqEflScrollA(1); // swap
			seqEflScrollA(8); // 10th

      seqEflScrollA(7); // 17th
      seqEflScrollA(1); // swap
      seqEflScrollA(3); // 20th
      seqEflButton(Move.B);
    }
    {
      seqEflScrollA(1); // to items
      seqEflScrollFast(3); // Item 4
      seq(new EflSwapWithSegment(1)); // Item 5
      seqEflScrollFast(3); // Item 4
      seq(new EflTossItemSegment(14).withSkipBeforeScroll()); // toss 14
      seqEflScrollFast(1); // Item 5
      seq(new EflTossItemSegment(9).withSkipBeforeScroll()); // toss 9
      seqEflScrollFast(1); // Item 6
      seq(new EflTossItemSegment(13).withSkipBeforeScroll()); // toss 13
      seqEflScrollFast(1); // Item 7
      seq(new EflTossItemSegment(45).withSkipBeforeScroll()); // toss 45
      seqEflScrollFast(1); // Item 8
      seq(new EflTossItemSegment(16).withSkipBeforeScroll()); // toss 16
      seq(new EflSwapWithSegment(-1)); // 8 <-> 7
      seq(new EflSwapWithSegment(-1)); // 7 <-> 6
      seqEflButton(Move.B);
    }
    {
      seqEflScrollA(-1); // to mons
      seqEflScrollA(-1, null); // 19th
      seqEflScrollA(1); // swap
      seqEflScrollA(-2); // 17th

      seqEflScrollA(-5, null); // 12th
      seqEflScrollA(1); // swap
      seqEflScrollA(-1); // 11th
      seqEflButton(Move.B);
      seqEflButton(Move.START);
    }
    
    // stage 1
    {
      seqMove(new Move() {
        @Override
        public int getInitialKey() {
          return 0;
        }
        
        @Override
        public boolean doMove() {
          curGb.steps(31);
          curGb.step(0xe2);
          curGb.step(0x18);
          curGb.step(0x07);
          curGb.step(0xf2);
          curGb.step(0xcb);
          curGb.step(0x37);
          curGb.step(0x57);
          curGb.step(0xf2);
          curGb.step(0xaa);
          curGb.step(0x22);
          curGb.step(0xab);
          curGb.step(0x7d);
          curGb.step(0xab);
          curGb.step(0x0);
          return true;
        }
      });
    }
    
    seqFunc(() -> {
      for (int add = 0xd350; add < 0xd370; add++)
        System.out.print(Util.toHex(curGb.readMemory(add)) + " ");
      System.out.println();
    });
	}
}
