package mrwint.gbtasgen.move;

import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.metric.comparator.Comparator;
import mrwint.gbtasgen.metric.comparator.Equal;

public class DelayUntil extends DelayableMove {

	private DelayableMove move;
	private Metric metric;
	private Comparator comp;
	private int value;
	private boolean checkMetricBeforeMove = false;
	
	public DelayUntil(DelayableMove move, boolean checkMetricBeforeMove, Metric metric) {
		this(move,checkMetricBeforeMove,metric,new Equal(),1);
	}
	
	@Override
	public int getInitialKey() {
		return move.getInitialKey();
	}
	
	public DelayUntil(DelayableMove move, boolean checkMetricBeforeMove, Metric metric, Comparator comp, int value) {
		this.move = move;
		this.metric = metric;
		this.comp = comp;
		this.value = value;
		this.checkMetricBeforeMove = checkMetricBeforeMove;
	}
	
	@Override
	public void clearCache() {
		super.clearCache();
		move.clearCache();
	}
	
	@Override
	public int prepareMoveInternal(int skips, boolean assumeOnSkip) throws Throwable {
		//System.out.println("DelayUntil "+skips);
		//State init = new State();
		int delay = -1;
		int skipsToGo = skips;
		do {
			int val;
			do {
				++delay;
				//System.out.println("DelayUntil: delay = "+delay);
				//init.restore();
				move.prepareMove(delay,true);
				//System.out.println("DelayUntil: delay = "+delay+" prepared");
				if(checkMetricBeforeMove)
					val = metric.getMetric();
				else {
					move.doMove();
					val = metric.getMetric();
					//init.restore();
					move.prepareMove(delay,true);
				}
				//System.out.println("DelayUntil: delay = "+delay+" value = "+val);
			} while(!comp.compare(val, value));
		}
		while(skipsToGo-- > 0);
		//System.out.println("delayed "+delay+" steps until condition met ("+skips+" skips)");
		return delay;
	}

	@Override
	public int doMove() throws Throwable {
		return move.doMove();
	}
}
