package mrwint.gbtasgen.segment.util;

import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.move.DelayUntil;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.move.WithMetric;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.state.StateBuffer;

public class DelayMoveSegment extends Segment {
	
	public static interface DelayableMoveFactory {
		Move create();
	}
	
	public static class PressButtonFactory implements DelayableMoveFactory{
		int move;
		Metric metric;
		public PressButtonFactory(int move) {
			this(move,Metric.DOWN_JOY);
		}
		public PressButtonFactory(int move, Metric metric) {
			this.move = move;
			this.metric = metric;
		}
		@Override
		public Move create() {
			return new PressButton(move, metric);
		}
	}
	
	public static class DelayUntilFactory implements DelayableMoveFactory{
		DelayableMoveFactory moveFactory;
		Metric metric;

		public DelayUntilFactory(DelayableMoveFactory moveFactory, Metric metric) {
			this.moveFactory = moveFactory;
			this.metric = metric;
		}
		@Override
		public Move create() {
			return new DelayUntil(new WithMetric(moveFactory.create(), true, metric));
		}
	}
	
	DelayableMoveFactory factory;
	Segment verificationSegment;
	
	int fullCutoffDelay = 2;
	int nonemptyCutoffDelay = 60;
	
	boolean metricBeforeExecution = false;
	
	public DelayMoveSegment(Segment verificationSegment) {
		this.factory = new PressButtonFactory(0, null);
		this.verificationSegment = verificationSegment;
		this.metricBeforeExecution = true;
	}
	
	public DelayMoveSegment(DelayableMoveFactory factory, Segment verificationSegment) {
		this.factory = factory;
		this.verificationSegment = verificationSegment;
	}
	
	public DelayMoveSegment(DelayableMoveFactory factory, Segment verificationSegment, boolean metricBeforeExecution) {
		this.factory = factory;
		this.verificationSegment = verificationSegment;
		this.metricBeforeExecution = metricBeforeExecution;
	}
	
	public DelayMoveSegment(DelayableMoveFactory factory, Segment verificationSegment, int fullCutoffDelay, int nonemptyCutoffDelay) {
		this.factory = factory;
		this.verificationSegment = verificationSegment;
		this.fullCutoffDelay = fullCutoffDelay;
		this.nonemptyCutoffDelay = nonemptyCutoffDelay;
	}
	
	public DelayMoveSegment(DelayableMoveFactory factory, Segment verificationSegment, int fullCutoffDelay, int nonemptyCutoffDelay, boolean metricBeforeExecution) {
		this.factory = factory;
		this.verificationSegment = verificationSegment;
		this.fullCutoffDelay = fullCutoffDelay;
		this.nonemptyCutoffDelay = nonemptyCutoffDelay;
		this.metricBeforeExecution = metricBeforeExecution;
	}
	
	@Override
	public StateBuffer execute(StateBuffer in) {
		System.out.println("DelayMoveSegment starts");
		StateBuffer ret = new StateBuffer();
		int fullFrame = Integer.MAX_VALUE;
		int nonemptyFrame = Integer.MAX_VALUE;

		boolean[] active = new boolean[in.size()];
		int numActive = in.size();
		Move[] dus = new Move[in.size()];
		int cs = -1;
		for(cs=0;cs<in.size();cs++) {
			active[cs] = true;
			dus[cs] = factory.create();
		}
		
		int skips = -1;
		while(numActive > 0) {
			skips++;
			System.out.println("DelayMoveSegment processing "+numActive+" active states at "+skips+" skips");
			cs = -1;
			//for(cs=0;cs<in.size();cs++) {
			for(State s : in.getStates()) {
				cs++;
				if(!active[cs])
					continue;
				s.restore();
				dus[cs].prepare(skips,true);
				
				if(!metricBeforeExecution)
					dus[cs].doMove();
				
				int curActiveFrame = State.currentStepCount;
				
				if(fullFrame < curActiveFrame-fullCutoffDelay) {
					System.out.println("DelayMoveSegment interrupting search (fullFrame)!");
					active[cs] = false;
					numActive--;
					continue;
				}
				
				if(nonemptyFrame < curActiveFrame-nonemptyCutoffDelay) {
					System.out.println("DelayMoveSegment interrupting search (nonemptyFrame)!");
					active[cs] = false;
					numActive--;
					continue;
				}
				
				StateBuffer sb = new StateBuffer();
				sb.addState(State.createState(true));
				sb = verificationSegment.execute(sb);
				ret.addAll(sb);
				if(sb.isEmpty())
					continue;
				//System.out.println("ret size: "+ret.size());
				if(ret.isFull() && curActiveFrame < fullFrame) {
					fullFrame = curActiveFrame;
					System.out.println("DelayMoveSegment set fullFrame to "+fullFrame);
				}
				if(ret.size()>0 && curActiveFrame < nonemptyFrame) {
					nonemptyFrame = curActiveFrame;
					System.out.println("DelayMoveSegment set nonemptyFrame to "+nonemptyFrame);
				}
			}
		}
		
		System.out.println("DelayMoveSegment returns: "+ret);
		return ret;
	}
}
