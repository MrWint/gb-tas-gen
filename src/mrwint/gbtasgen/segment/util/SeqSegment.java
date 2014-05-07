package mrwint.gbtasgen.segment.util;

import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.metric.comparator.Comparator;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.segment.Segment;
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
	
	public void seq(int move) {
		seq(new PressButton(move));
	}
	
	public void seq(Move m) {
		seq(new MoveSegment(m));
	}
	
	public void seq(Move m, int maxDelay) {
		seq(new MoveSegment(m, maxDelay));
	}

	public void seq(Metric m) {
		seq(new CheckMetricSegment(m));
	}

	public void seq(Metric m, Comparator c, int val) {
		seq(new CheckMetricSegment(m, c, val, null));
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
