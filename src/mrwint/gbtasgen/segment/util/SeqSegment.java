package mrwint.gbtasgen.segment.util;

import static mrwint.gbtasgen.state.Gameboy.curGb;

import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.metric.comparator.Comparator;
import mrwint.gbtasgen.move.EflPressButton;
import mrwint.gbtasgen.move.EflSkipInput;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PrepMove;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.move.SkipInput;
import mrwint.gbtasgen.move.Wait;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.EflScroll;
import mrwint.gbtasgen.segment.util.DelayMoveSegment.DelayableMoveFactory;
import mrwint.gbtasgen.state.StateBuffer;
import mrwint.gbtasgen.util.EflUtil.PressMetric;

public abstract class SeqSegment implements Segment {

	private StateBuffer in;
	private StateBuffer save;

	protected abstract void execute();

	@Override
	public StateBuffer execute(StateBuffer in) {
		this.in = in;
		execute();
		return this.in;
	}

  public void seq(Segment s) {
    in = s.execute(in);
  }
  public void seqUnbounded(Segment s) {
    StateBuffer.pushBufferSize(0);
    in = s.execute(in);
    StateBuffer.popBufferSize();
  }
  public void seqButton(int move) {
    seqMove(new PressButton(move));
  }
  public void seqButtonNoDelay(int move) {
    seqMoveNoDelay(new PressButton(move));
  }
  public void seqEflButton(int move) {
    seqMove(new EflPressButton(move));
  }
  public void seqEflButton(int move, PressMetric metric) {
    seqMove(new EflPressButton(move, metric));
  }
  public void seqEflButtonNoDelay(int move) {
    seqMoveNoDelay(new EflPressButton(move));
  }
  public void seqEflButtonNoDelay(int move, PressMetric metric) {
    seqMoveNoDelay(new EflPressButton(move, metric));
  }
  public void seqEflButtonUnbounded(int move) {
    seqMoveUnbounded(new EflPressButton(move));
  }
  public void seqEflButtonUnbounded(int move, PressMetric metric) {
    seqMoveUnbounded(new EflPressButton(move, metric));
  }
  public void seqEflButtonUnboundedNoDelay(int move) {
    seqMoveUnboundedNoDelay(new EflPressButton(move));
  }
  public void seqEflButtonUnboundedNoDelay(int move, PressMetric metric) {
    seqMoveUnboundedNoDelay(new EflPressButton(move, metric));
  }
  public void seqEflScroll(int num) {
    seq(new EflScroll(false, num, 0));
  }
  public void seqEflScrollA(int num) {
    seq(new EflScroll(false, num, 0));
    seqEflButtonNoDelay(Move.A);
  }
  public void seqEflScrollAF(int num) {
    seq(new EflScroll(false, num, Move.A));
  }
  public void seqEflScrollFast(int num) {
    seq(new EflScroll(true, num, 0));
  }
  public void seqEflScrollFastA(int num) {
    seq(new EflScroll(true, num, 0));
    seqEflButtonNoDelay(Move.A);
  }
  public void seqEflScrollFastAF(int num) {
    seq(new EflScroll(true, num, Move.A));
  }
  public void seqWait(int frames) {
    seqMoveUnboundedNoDelay(new Wait(frames));
  }
  public void seqSkipInput(int skips) {
    seqMove(new SkipInput(skips));
  }
  public void seqSkipInputUnbounded(int skips) {
    seqMoveUnbounded(new SkipInput(skips));
  }
  public void seqEflSkipInput(int skips) {
    seqMoveUnboundedNoDelay(new EflSkipInput(skips));
  }
  public void seqFunc(Runnable func) {
    seqMetric(() -> {func.run(); return 1;});
  }
  public void seqMove(Move m) {
    seq(new MoveSegment(m));
  }
  public void seqMoveUnbounded(Move m) {
    seqUnbounded(new MoveSegment(m));
  }
  public void seqMoveUnboundedNoDelay(Move m) {
    seqUnbounded(new MoveSegment(m, 0));
  }
  public void seqMoveNoDelay(Move m) {
    seqMove(m, 0);
  }
  public void seqMove(Move m, int maxDelay) {
    seq(new MoveSegment(m, maxDelay));
  }
  public void seqMoveUnbounded(Move m, int maxDelay) {
    seqUnbounded(new MoveSegment(m, maxDelay));
  }
	public void seqMetric(Metric m) {
		seq(new CheckMetricSegment(m));
	}
	public void seqMetric(Metric m, Comparator c, int val) {
		seq(new CheckMetricSegment(m, c, val, null));
	}

	public void prep(Move m) {
		seqMove(new PrepMove(m));
	}

  public void delayEfl(Segment s) {
    seq(new EflDelayMoveSegment(s));
  }
  public void delay(Segment s) {
    seq(new DelayMoveSegment(s));
  }
	public void delay(DelayableMoveFactory m, Segment s) {
		seq(new DelayMoveSegment(m, s, true));
	}

	public void load(StateBuffer sb) {
		in = sb;
	}

	public void load(String name) {
		in = StateBuffer.load(name, curGb.rom.fileNameSuffix);
	}
	public void qload() {
		in = save;
	}
	public StateBuffer save() {
		return in;
	}
	public void save(String name) {
		in.save(name, curGb.rom.fileNameSuffix);
	}
	public void qsave() {
		save = in;
	}
}
