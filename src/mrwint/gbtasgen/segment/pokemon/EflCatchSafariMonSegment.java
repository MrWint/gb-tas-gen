package mrwint.gbtasgen.segment.pokemon;

import static mrwint.gbtasgen.state.Gameboy.curGb;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.pokemon.gen1.common.NamingSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.state.StateBuffer;
import mrwint.gbtasgen.util.EflUtil;

public class EflCatchSafariMonSegment extends SeqSegment {

	String name = null;
	int extraSkips = 0;
	int bufferSize = -1;

	public EflCatchSafariMonSegment() {
    EflUtil.assertEfl();
	}

  public EflCatchSafariMonSegment withName(String name) {
    this.name = name;
    return this;
  }

  public EflCatchSafariMonSegment withExtraSkips(int extraSkips) {
    this.extraSkips = extraSkips;
    return this;
  }

  public EflCatchSafariMonSegment withBufferSize(int bufferSize) {
    this.bufferSize = bufferSize;
    return this;
  }

	@Override
	public void execute() {
    boolean partyFull = curGb.readMemory(curGb.pokemon.numPartyMonAddress) >= 6;
		seq(new EflSkipTextsSegment(1)); // wild mon
		seq(new EflBallSuccessSegment());

		seq(new EflSkipTextsSegment(4)); // cought, new dex data
    seqEflButton(Move.A); // skip dex
    seqEflButton(Move.B); // skip dex
    if (bufferSize >= 0 && !partyFull)
      StateBuffer.pushBufferSize(bufferSize);
		seq(new EflTextSegment(Move.A));
		if (extraSkips > 0)
		  seqEflSkipInput(extraSkips);
		seqEflButtonUnboundedNoDelay(Move.B);
		seq(new EflSkipTextsSegment(1, name != null)); // nickname?
		if (name != null) {
			seq(new NamingSegment(name));
			seqEflButton(Move.START);
		}
		if (partyFull) {
	    if (bufferSize >= 0)
	      StateBuffer.pushBufferSize(bufferSize);
			seq(new EflSkipTextsSegment(2)); // transferred to PC
		}
    if (bufferSize >= 0)
      StateBuffer.popBufferSize();
	}
}
