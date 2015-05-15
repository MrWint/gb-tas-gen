package mrwint.gbtasgen.segment.util;

import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.metric.comparator.Comparator;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PrepMove;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.move.SkipInput;
import mrwint.gbtasgen.move.Wait;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.util.DelayMoveSegment.DelayableMoveFactory;
import mrwint.gbtasgen.state.StateBuffer;

public abstract class SeqSegment extends Segment {

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
  public void seqButton(int move) {
    seqMove(new PressButton(move));
  }
  public void seqButtonNoDelay(int move) {
    seqMoveNoDelay(new PressButton(move));
  }
  public void seqWait(int frames) {
    seqMove(new Wait(frames));
  }
  public void seqWaitUnbounded(int frames) {
    seq(new MoveSegment(new Wait(frames), 0, 0));
  }
  public void seqSkipInput(int skips) {
    seqMove(new SkipInput(skips));
  }
  public void seqSkipInputUnbounded(int skips) {
    seq(new MoveSegment(new SkipInput(skips), 0, 0));
  }
  public void seqFunc(Runnable func) {
    seqMetric(() -> {func.run(); return 1;});
  }
  public void seqMove(Move m) {
    seq(new MoveSegment(m));
  }
  public void seqMoveUnbounded(Move m) {
    seq(new MoveSegment(m, MoveSegment.MAX_DELAY, 0));
  }
  public void seqMoveNoDelay(Move m) {
    seqMove(m, 0);
  }
  public void seqMove(Move m, int maxDelay) {
    seq(new MoveSegment(m, maxDelay));
  }
  public void seqMoveUnbounded(Move m, int maxDelay) {
    seq(new MoveSegment(m, maxDelay, 0));
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

	public void delay(Segment s) {
		seq(new DelayMoveSegment(s));
	}
	public void delay(DelayableMoveFactory m, Segment s) {
		seq(new DelayMoveSegment(m, s, true));
	}
	public void delayMetric(Metric m) {
		seq(new DelayMoveSegment(new CheckMetricSegment(m)));
	}

	public void load(StateBuffer sb) {
		in = sb;
	}

	public void load(String name) {
		in = StateBuffer.load(name);
	}
	public void qload() {
		in = save;
	}
	public StateBuffer save() {
		return in;
	}
	public void save(String name) {
		in.save(name);
	}
	public void qsave() {
		save = in;
	}
}
