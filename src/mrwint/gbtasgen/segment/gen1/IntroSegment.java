package mrwint.gbtasgen.segment.gen1;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.state.StateBuffer;

public class IntroSegment extends Segment {

	SequenceSegment sequence;
	
	public IntroSegment() {
		List<Segment> segments = new ArrayList<Segment>();
		
		/*segments.add(new MoveSegment(new MenuInteraction(Move.START),20,0)); // vid 1
		segments.add(new MoveSegment(new MenuInteraction(Move.A),20,0)); // vid 2
		segments.add(new MoveSegment(new MenuInteraction(Move.START),20,0)); // title screen
		segments.add(new DelayMoveSegment(new DelayMoveSegment.DelayableMoveFactory() {
			@Override
			public DelayableMove create() {
				return new MenuInteraction(Move.A);
			}
		}, new Segment() {
			@Override
			public StateBuffer execute(StateBuffer in) throws Throwable {
				return new CheckMetricSegment(new TrainerIDMetric(new Integer[]{0x2E}, new Integer[]{0x5E})).execute(in);
//				return new CheckMetricSegment(new TrainerIDMetric(null, new Integer[]{0xBB,0xBD,0xBE,0xC2})).execute(in);
			}
		}));*/
		
		segments.add(new MoveSegment(new PressButton(Move.START)));
		segments.add(new MoveSegment(new PressButton(Move.A)));
		segments.add(new MoveSegment(new PressButton(Move.START)));
		segments.add(new MoveSegment(new PressButton(Move.A)));
				
//		segments.add(new FindShortestSequenceSegment(new DelayableMove[]{
//				new MenuInteraction(Move.START),
//				new MenuInteraction(Move.A),
//				new MenuInteraction(Move.START),
//				new MenuInteraction(Move.A)
//		}, new TrainerIDMetric(new Integer[]{0x2E}, new Integer[]{0x5E})));
		
		sequence = new SequenceSegment(segments.toArray(new Segment[0]));
	}
	
	@Override
	public StateBuffer execute(StateBuffer in) throws Throwable {
		return sequence.execute(in);
	}
}
