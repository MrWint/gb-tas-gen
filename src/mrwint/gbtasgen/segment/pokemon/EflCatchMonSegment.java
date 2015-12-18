package mrwint.gbtasgen.segment.pokemon;

import static mrwint.gbtasgen.move.Move.A;
import static mrwint.gbtasgen.move.Move.B;
import static mrwint.gbtasgen.move.Move.DOWN;
import static mrwint.gbtasgen.move.Move.START;
import static mrwint.gbtasgen.segment.pokemon.gen1.common.Constants.POKE_BALL;
import static mrwint.gbtasgen.state.Gameboy.curGb;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.PRESSED;
import mrwint.gbtasgen.metric.pokemon.CheckCatchMonMetric;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSelectItemSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.NamingSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.state.StateBuffer;
import mrwint.gbtasgen.util.EflUtil;

public class EflCatchMonSegment extends SeqSegment {

  int item;
	String name = null;
	int extraSkips = 0;
	int bufferSize = -1;
	boolean noNew = false;

	public EflCatchMonSegment() {
    EflUtil.assertEfl();

    this.item = POKE_BALL;
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

  public EflCatchMonSegment noNew() {
    this.noNew = true;
    return this;
  }

	@Override
	public void execute() {
		seq(new EflSkipTextsSegment(2)); // wild mon, go mon
    boolean partyFull = curGb.readMemory(curGb.pokemon.numPartyMonAddress) >= 6;
		seqEflButton(DOWN | A, PRESSED); // items
    seqEflSkipInput(1);
    delayEfl(new SeqSegment() {
      @Override
      protected void execute() {
        seq(new EflSelectItemSegment(item)); // use ball
        seqUnbounded(new EflTextSegment(A)); // used ball
        seqMetric(new CheckCatchMonMetric()); // check catch
      }
    });

		if (noNew) {
      seq(new EflSkipTextsSegment(2)); // cought, new dex data
		} else {
  		seq(new EflSkipTextsSegment(4)); // cought, new dex data
      seqEflButton(A); // skip dex
      seqEflButton(B); // skip dex
		}
    if (bufferSize >= 0 && !partyFull)
      StateBuffer.pushBufferSize(bufferSize);
		seq(new EflTextSegment(A));
		if (extraSkips > 0)
		  seqEflSkipInput(extraSkips);
		seqEflButtonUnboundedNoDelay(B);
		seq(new EflSkipTextsSegment(1, name != null)); // nickname?
		if (name != null) {
			seq(new NamingSegment(name));
			seqEflButton(START);
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
