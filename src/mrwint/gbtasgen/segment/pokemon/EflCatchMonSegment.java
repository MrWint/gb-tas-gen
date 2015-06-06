package mrwint.gbtasgen.segment.pokemon;

import static mrwint.gbtasgen.state.Gameboy.curGb;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.NamingSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.state.StateBuffer;
import mrwint.gbtasgen.util.EflUtil;

public class EflCatchMonSegment extends SeqSegment {

	int numScrolls;
	String name = null;
	int extraSkips = 0;
	int bufferSize = StateBuffer.MAX_BUFFER_SIZE;

	public EflCatchMonSegment(int numScrolls) {
    EflUtil.assertEfl();

    this.numScrolls = numScrolls;
	}

  public EflCatchMonSegment withName(String name) {
    this.name = name;
    return this;
  }

  public EflCatchMonSegment withExtraSkips(int extraSkips) {
    this.extraSkips = extraSkips;
    return this;
  }

  public EflCatchMonSegment withBufferSize(int bufferSize) {
    this.bufferSize = bufferSize;
    return this;
  }

	@Override
	public void execute() {
		seq(new EflSkipTextsSegment(2)); // wild mon, go mon
    boolean partyFull = curGb.readMemory(curGb.pokemon.numPartyMonAddress) >= 6;
		seqEflButton(Move.DOWN | Move.A); // items
		seqEflScrollFast(numScrolls); // select ball
		seq(new EflBallSuccessSegment());

		seq(new EflSkipTextsSegment(4)); // cought, new dex data
    seqEflButton(Move.A); // skip dex
    seqEflButton(Move.B); // skip dex
		seq(new EflTextSegment(Move.A, partyFull ? StateBuffer.MAX_BUFFER_SIZE : bufferSize));
		if (extraSkips > 0)
		  seqEflSkipInputUnbounded(extraSkips);
		seqEflButtonUnboundedNoDelay(Move.B);
		seq(new EflSkipTextsSegment(1, name != null).withBufferSize(partyFull ? StateBuffer.MAX_BUFFER_SIZE : bufferSize)); // nickname?
		if (name != null) {
			seq(new NamingSegment(name));
			seqEflButton(Move.START);
		}
		if (partyFull) {
			seq(new EflSkipTextsSegment(2).withBufferSize(bufferSize)); // transferred to PC
		}
	}
}
