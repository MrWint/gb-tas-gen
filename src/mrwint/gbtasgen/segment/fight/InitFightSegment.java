package mrwint.gbtasgen.segment.fight;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.move.Wait;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.TextSegment;
import mrwint.gbtasgen.segment.util.CheckMetricSegment;
import mrwint.gbtasgen.segment.util.DelayMoveSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.state.StateBuffer;

public class InitFightSegment extends Segment {

	SequenceSegment sequence;
	
	public InitFightSegment(int numPreBattleTexts, int... enemyInitialMove) {
		List<Segment> segments = new ArrayList<Segment>();
		for(int i=0;i<numPreBattleTexts;i++) {
			segments.add(new TextSegment());
			segments.add(new MoveSegment(new PressButton(Move.B))); // trainer pre-text
		}
		segments.add(new TextSegment()); // trainer wants to fight
		segments.add(new MoveSegment(new PressButton(Move.B))); // confirm
		segments.add(new TextSegment()); // trainer sent out ...
		
		segments.add(new DelayMoveSegment(new DelayMoveSegment.PressButtonFactory(Move.B), // scroll
				new SequenceSegment(
						new TextSegment(), // mon!
						new TextSegment(Move.A,false,0), // Go! mon!
						new CheckMetricSegment(KillEnemyMonSegment.CheckEnemyMoveMetric.noKeys(enemyInitialMove)),
						new MoveSegment(new Wait(1),0,0) // skip last frame of text box
				
		),1, 5));

		//segments.add(new MoveSegment(new PressButton(Move.B))); // scroll
		//segments.add(new TextSegment()); // mon!
		//segments.add(new TextSegment(Move.A,false)); // Go! mon!

		sequence = new SequenceSegment(segments.toArray(new Segment[0]));
	}
	
	@Override
	public StateBuffer execute(StateBuffer in) throws Throwable {
		return sequence.execute(in);
	}
}
