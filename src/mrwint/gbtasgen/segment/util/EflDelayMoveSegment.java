package mrwint.gbtasgen.segment.util;

import static mrwint.gbtasgen.state.Gameboy.curGb;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.state.StateBuffer;
import mrwint.gbtasgen.util.EflUtil;

public class EflDelayMoveSegment implements Segment {
	Segment segment;

	int fullCutoffDelay = 0;
	int nonemptyCutoffDelay = 4;
	int maxSkips = 1000000;

	public EflDelayMoveSegment withMaxDelay(int maxDelay) {
		this.maxSkips = maxDelay;
		return this;
	}

	public EflDelayMoveSegment(Segment segment) {
		this.segment = segment;
	}

	@Override
	public StateBuffer execute(StateBuffer in) {
		System.out.println("EflDelayMoveSegment starts");
		StateBuffer ret = new StateBuffer();
		int fullFrame = Integer.MAX_VALUE;
		int nonemptyFrame = Integer.MAX_VALUE;

		StateBuffer next = new StateBuffer(0); // intermediate buffers are unbounded

		int skips = -1;
		while(in.size() > 0) {
			skips++;
			System.out.println("EflDelayMoveSegment processing "+in.size()+" active states at "+skips+" skips");
			//for(cs=0;cs<in.size();cs++) {
			for(State s : in.getStates()) {
				curGb.restore(s);
				EflUtil.runToNextInputFrame(0b11111111); // for any input
				int curActiveFrame = curGb.currentStepCount;

				if(skips > maxSkips) {
					System.out.println("EflDelayMoveSegment interrupting search (maxDelay)!");
					continue;
				}
				if(fullFrame < curActiveFrame-fullCutoffDelay) {
					System.out.println("EflDelayMoveSegment interrupting search (fullFrame)!");
					continue;
				}

				if(nonemptyFrame < curActiveFrame-nonemptyCutoffDelay) {
					System.out.println("EflDelayMoveSegment interrupting search (nonemptyFrame)!");
					continue;
				}

				StateBuffer sb = new StateBuffer();
				sb.addState(curGb.createState(false));

        curGb.step(); // skip this input frame
        next.addState(curGb.createState(true));

				sb = segment.execute(sb);
				ret.addAll(sb);
				if(sb.isEmpty())
					continue;
				//System.out.println("ret size: "+ret.size());
				if(ret.isFull() && curActiveFrame < fullFrame) {
					fullFrame = curActiveFrame;
					System.out.println("EflDelayMoveSegment set fullFrame to "+fullFrame);
				}
				if(ret.size()>0 && curActiveFrame < nonemptyFrame) {
					nonemptyFrame = curActiveFrame;
					System.out.println("EflDelayMoveSegment set nonemptyFrame to "+nonemptyFrame);
				}
			}

			in = next; // use next states;
	    next = new StateBuffer(0); // intermediate buffers are unbounded
		}

		System.out.println("EflDelayMoveSegment returns: "+ret);
		return ret;
	}
}
