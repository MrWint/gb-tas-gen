package mrwint.gbtasgen.segment.fight;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.Wait;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.TextSegment;
import mrwint.gbtasgen.segment.util.CheckMetricSegment;
import mrwint.gbtasgen.segment.util.DelayMoveSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.state.StateBuffer;
import mrwint.gbtasgen.util.Util;

public class NewEnemyMonSegment extends Segment {

	SequenceSegment sequence;
	
	private NewEnemyMonSegment(int... enemyInitialMove) {
		List<Segment> segments = new ArrayList<Segment>();
		
		if (Util.isGen1()) {
			segments.add(new TextSegment()); // trainer sent out mon
		} else {
			segments.add(new TextSegment()); // trainer sent out ...
			segments.add(new DelayMoveSegment(new DelayMoveSegment.PressButtonFactory(Move.B), // scroll
					new SequenceSegment(
							new TextSegment(Move.A,false,0), // mon!
							new CheckMetricSegment(KillEnemyMonSegment.CheckEnemyMoveMetric.noKeys(enemyInitialMove)),
							new MoveSegment(new Wait(1),0,0) // skip last frame of text box
					
			)));
		}

		sequence = new SequenceSegment(segments.toArray(new Segment[0]));
	}
	
	public static NewEnemyMonSegment any(int... enemyInitialMove) {
		return new NewEnemyMonSegment(enemyInitialMove);
	}
	
	@Override
	public StateBuffer execute(StateBuffer in) {
		return sequence.execute(in);
	}
}
