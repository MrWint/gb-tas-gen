package mrwint.gbtasgen.segment.util;

import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.state.StateBuffer;

public class SleepSegment extends Segment {

	public int millis;
	
	public SleepSegment(int millis) {
		this.millis = millis;
	}
	
	@Override
	public StateBuffer execute(StateBuffer in) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return in;
	}
}
