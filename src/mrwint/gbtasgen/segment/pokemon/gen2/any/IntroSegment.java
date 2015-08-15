package mrwint.gbtasgen.segment.pokemon.gen2.any;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.segment.util.SeqSegment;

public class IntroSegment extends SeqSegment {

  @Override
  protected void execute() {
//		segments.add(new MoveSegment(new SkipInput(12)));
		seqMoveUnbounded(new PressButton(Move.A), 10);		// skip intro cutscene
//		segments.add(new MoveSegment(new SkipInput(39)));
		seqMoveUnbounded(new PressButton(Move.START));	// skip title screen
//		segments.add(new MoveSegment(new Move() {
//
//			@Override
//			public int getInitialKey() {
//				return 0;
//			}
//
//			@Override
//			public int execute() throws Throwable {
//				return new IntroOptionsMove().execute(9);
//			}
//		}));	// skip title screen
//		segments.add(new MoveSegment(ChangeOptionsMove.get(true), 10, 0));	// options
//		segments.add(new ChangeOptionsSegment(true, 0));
//		segments.add(new DelayMoveSegment(new PressButtonFactory(Move.A), new CheckMetricSegment(new TrainerIDMetric(0xD9E9, 0xF9E9)), 0, 0));
		seqMove(new PressButton(Move.A));		// start new game

//		segments.add(new MoveSegment(new SequenceMove(
//				new PressButton(Move.A),
//				new PressButton(Move.START),
//				new SequenceMove(
//						new PressButton(Move.DOWN,Metric.PRESSED_JOY),
//						new PressButton(Move.A,Metric.PRESSED_JOY)) // options
//				)));

//		segments.add(new FindShortestSequenceSegment(new DelayableMove[]{
//			new SequenceMove(
//				new SkipInput(12),
//				new PressButton(Move.A),
//			new SequenceMove(
//				new SkipInput(46),
//				new PressButton(Move.START),
//				new IntroOptionsMove()
//			new SequenceMove(
//				new PressButton(Move.DOWN,Metric.PRESSED_JOY),
//				new PressButton(Move.A,Metric.PRESSED_JOY)), // options
//			new SequenceMove(
////				new SkipInput(9),
//				new PressButton(Move.LEFT,Metric.PRESSED_JOY), // text speed
//				new PressButton(Move.DOWN,Metric.PRESSED_JOY),
//				new PressButton(Move.LEFT,Metric.PRESSED_JOY), // battle scene
//				new PressButton(Move.DOWN,Metric.PRESSED_JOY),
//				new PressButton(Move.LEFT,Metric.PRESSED_JOY), // battle style
//
//				new PressButton(Move.DOWN,Metric.PRESSED_JOY),
//				new PressButton(Move.LEFT,Metric.PRESSED_JOY), // sound
//				new PressButton(Move.DOWN,Metric.PRESSED_JOY),
//				new PressButton(Move.DOWN,Metric.PRESSED_JOY),
//				new PressButton(Move.LEFT,Metric.PRESSED_JOY), // menu account
//				new PressButton(Move.DOWN,Metric.PRESSED_JOY),
//				new PressButton(Move.RIGHT,Metric.PRESSED_JOY), // border
////			new PressButton(Move.LEFT,Metric.PRESSED_JOY), // border
////			new PressButton(Move.LEFT,Metric.PRESSED_JOY), // border
//
//				new PressButton(Move.B,Metric.PRESSED_JOY)),
////			ChangeOptionsMove.get(true),
////			new MenuInteraction(Move.A),
//				new PressButton(Move.A)
//		}, new TrainerIDMetric(0xD9E9, 0xF9E9)));
	}
}
