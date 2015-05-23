package mrwint.gbtasgen.move;

import static mrwint.gbtasgen.state.Gameboy.curGb;
import mrwint.gbtasgen.util.EflUtil;
import mrwint.gbtasgen.util.EflUtil.PressMetric;

public class EflPressButton extends DelayableCachableMove {

	private final int move;
	private final PressMetric metric;
	private final boolean isCachable;

	public EflPressButton(int move) {
		this(move, null);
	}

	public EflPressButton(int move, PressMetric metric) {
		this.move = move;
		this.metric = metric;
		this.isCachable = (move & 0b00001111) == 0 || (move & 0b11110000) == 0; // mixed moves not cachable
	}

	@Override
	public boolean isCachable() {
	  return super.isCachable() && isCachable;
	}

	@Override
	public int getInitialKey() {
		return move;
	}

	@Override
	public boolean doMove() {
    if (twoFrames)
      curGb.step(move);
	  curGb.step(move, curGb.rom.readJoypadAddress); // run until joypad input is registered or frame ended (whichever comes first)
		return true;
	}

  boolean twoFrames;
	@Override
	public void prepareInternal(int skips, boolean assumeOnSkip) {
		if(!assumeOnSkip)
			if (metric != null)
			  twoFrames = EflUtil.runToNextInputFrameForMetric(move, metric);
			else
			  twoFrames = EflUtil.runToNextInputFrame(move);
		while(skips-- > 0) {
		  curGb.step();
      if (metric != null)
        twoFrames = EflUtil.runToNextInputFrameForMetric(move, metric);
      else
        twoFrames = EflUtil.runToNextInputFrame(move);
		}
	}
}
