package mrwint.gbtasgen.segment.section;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.move.CoinCaseMove;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.OverworldInteract;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.move.SkipInput;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.TextSegment;
import mrwint.gbtasgen.segment.WalkToSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.state.StateBuffer;

public class GetCoincase2Segment extends Segment {

	SequenceSegment sequence;
	
	public GetCoincase2Segment() {
		List<Segment> segments = new ArrayList<Segment>();

//		{
//			segments.add(new MoveSegment(new SkipInput(4)));
//			segments.add(new MoveSegment(new PressButton(Move.START)));	// open menu
//			segments.add(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY)));	// select mons
//			segments.add(new MoveSegment(new PressButton(Move.A, Metric.PRESSED_JOY)));		// select mons
//			segments.add(new MoveSegment(new PressButton(Move.A, Metric.PRESSED_JOY)));		// select toto
//			segments.add(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY)));	// switch
//			segments.add(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY)));	// switch
//			segments.add(new MoveSegment(new PressButton(Move.A, Metric.PRESSED_JOY)));		// switch
//			segments.add(new MoveSegment(new PressButton(Move.UP, Metric.PRESSED_JOY)));	// bellsprout
//			segments.add(new MoveSegment(new PressButton(Move.A, Metric.PRESSED_JOY)));		// bellsprout
//			segments.add(new MoveSegment(new PressButton(Move.B, Metric.PRESSED_JOY)));		// leave
//			segments.add(new MoveSegment(new PressButton(Move.START, Metric.PRESSED_JOY)));		// leave
//		}
//		
		segments.add(new WalkToSegment(6, 25));	// face coin case
		segments.add(new MoveSegment(new OverworldInteract()));	// grab coin case
		segments.add(new TextSegment());		// found coin case
		segments.add(new SkipTextsSegment(2));	// put in key items pocket

		segments.add(new WalkToSegment(3, 25));	// align
		segments.add(new MoveSegment(new PressButton(Move.START)));	// open menu
//		{
//			segments.add(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY)));	// mons
//			segments.add(new MoveSegment(new PressButton(Move.A, Metric.PRESSED_JOY)));		// select mons
//			segments.add(new MoveSegment(new PressButton(Move.A, Metric.PRESSED_JOY)));		// select toto
//			segments.add(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY)));	// switch
////			segments.add(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY)));	// switch
//			segments.add(new MoveSegment(new PressButton(Move.A, Metric.PRESSED_JOY)));		// switch
//			segments.add(new MoveSegment(new PressButton(Move.UP, Metric.PRESSED_JOY)));	// bellsprout
//			segments.add(new MoveSegment(new PressButton(Move.A, Metric.PRESSED_JOY)));		// bellsprout
//			segments.add(new MoveSegment(new PressButton(Move.UP, Metric.PRESSED_JOY)));	// bellsprout
//			segments.add(new MoveSegment(new PressButton(Move.UP, Metric.PRESSED_JOY)));	// bellsprout
//			segments.add(new MoveSegment(new PressButton(Move.A, Metric.PRESSED_JOY)));		// select bellsprout
//			segments.add(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY)));	// to stats
//			segments.add(new MoveSegment(new PressButton(Move.A, Metric.PRESSED_JOY)));		// select stats
//			segments.add(new MoveSegment(new PressButton(Move.B, Metric.PRESSED_JOY)));		// leave
//			segments.add(new MoveSegment(new PressButton(Move.B, Metric.PRESSED_JOY)));		// leave
//			segments.add(new MoveSegment(new SkipInput(4)));
//		}
//		segments.add(new MoveSegment(new PressButton(Move.UP, Metric.PRESSED_JOY)));	// dex
		segments.add(new MoveSegment(new PressButton(Move.A, Metric.PRESSED_JOY)));		// select dex
		segments.add(new MoveSegment(new SkipInput(1)));
		segments.add(new MoveSegment(new PressButton(Move.RIGHT, Metric.PRESSED_JOY)));	// go to bellsprout
		segments.add(new MoveSegment(new PressButton(Move.RIGHT, Metric.PRESSED_JOY)));	// go to bellsprout
		segments.add(new MoveSegment(new PressButton(Move.RIGHT, Metric.PRESSED_JOY)));	// go to bellsprout
		segments.add(new MoveSegment(new PressButton(Move.RIGHT, Metric.PRESSED_JOY)));	// go to bellsprout
		segments.add(new MoveSegment(new PressButton(Move.RIGHT, Metric.PRESSED_JOY)));	// go to bellsprout
		segments.add(new MoveSegment(new PressButton(Move.RIGHT, Metric.PRESSED_JOY)));	// go to bellsprout
		segments.add(new MoveSegment(new PressButton(Move.RIGHT, Metric.PRESSED_JOY)));	// go to bellsprout
		segments.add(new MoveSegment(new PressButton(Move.RIGHT, Metric.PRESSED_JOY)));	// go to bellsprout
		segments.add(new MoveSegment(new PressButton(Move.RIGHT, Metric.PRESSED_JOY)));	// go to bellsprout
		segments.add(new MoveSegment(new PressButton(Move.A, Metric.PRESSED_JOY)));		// select bellsprout
		segments.add(new MoveSegment(new SkipInput(1)));
		segments.add(new MoveSegment(new PressButton(Move.B, Metric.PRESSED_JOY)));		// cancel
		segments.add(new MoveSegment(new PressButton(Move.B, Metric.PRESSED_JOY)));		// cancel
		segments.add(new MoveSegment(new SkipInput(2)));
		segments.add(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY)));	// go to pack
		segments.add(new MoveSegment(new PressButton(Move.DOWN, Metric.PRESSED_JOY)));	// go to pack
		segments.add(new MoveSegment(new PressButton(Move.A, Metric.PRESSED_JOY)));		// select pack
		segments.add(new MoveSegment(new SkipInput(3)));
		segments.add(new MoveSegment(new PressButton(Move.LEFT, Metric.PRESSED_JOY)));	// go to key items
		segments.add(new MoveSegment(new SkipInput(2)));
		segments.add(new MoveSegment(new PressButton(Move.LEFT, Metric.PRESSED_JOY)));	// go to key items
		segments.add(new MoveSegment(new SkipInput(2)));
		segments.add(new MoveSegment(new PressButton(Move.A, Metric.PRESSED_JOY)));		// select coin case
		segments.add(new MoveSegment(new PressButton(Move.A, Metric.PRESSED_JOY)));		// use
		segments.add(new MoveSegment(new CoinCaseMove()));								// use
		segments.add(new MoveSegment(new PressButton(Move.LEFT)));						// teleport
		segments.add(new WalkToSegment(9, 12));	// align
		segments.add(new WalkToSegment(9, 11));	// face red
		segments.add(new MoveSegment(new OverworldInteract()));	// engage red
		segments.add(new MoveSegment(new PressButton(Move.B, Metric.PRESSED_JOY)));		// talk
		segments.add(new MoveSegment(new PressButton(Move.B, Metric.PRESSED_JOY), 0, 1));		// talk

		sequence = new SequenceSegment(segments.toArray(new Segment[0]));
	}
	
	@Override
	public StateBuffer execute(StateBuffer in) throws Throwable {
		return sequence.execute(in);
	}
}
