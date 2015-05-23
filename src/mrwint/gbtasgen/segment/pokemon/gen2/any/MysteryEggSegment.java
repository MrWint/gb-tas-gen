package mrwint.gbtasgen.segment.pokemon.gen2.any;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.move.SkipInput;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.TextSegment;
import mrwint.gbtasgen.segment.pokemon.WalkToSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.state.StateBuffer;

public class MysteryEggSegment implements Segment {

	SequenceSegment sequence;

	public MysteryEggSegment() {
		List<Segment> segments = new ArrayList<Segment>();

		segments.add(new MoveSegment(new SkipInput(2)));
		segments.add(new WalkToSegment(-1, 8));						// New Bark -> route 29
		segments.add(new WalkToSegment(8, 6, false));				// jump ledge //go to ledge
		//segments.add(new MoveSegment(new PressButton(Move.LEFT)));	// jump ledge
		segments.add(new WalkToSegment(-1, 6));						// route 29 -> cherrygrove
		segments.add(new WalkToSegment(17, -1));					// cherrygrove -> route 30
		segments.add(new WalkToSegment(17, 5, false));				// enter lab

		segments.add(new SkipTextsSegment(4));
		segments.add(new TextSegment()); // mystery egg
		segments.add(new SkipTextsSegment(35));
		segments.add(new TextSegment()); // PokeDex
		segments.add(new SkipTextsSegment(10));
		segments.add(new WalkToSegment(3, 8, false));				// leave lab
		segments.add(new SkipTextsSegment(4));						// elm phone
		for(int i=0;i<4;i++)
			segments.add(new TextSegment());						// click, ..., ..., ...

		segments.add(new WalkToSegment(7, 54));						// route 30 -> cherrygrove
		segments.add(new WalkToSegment(33, 7, false));				// trigger rival encounter

		sequence = new SequenceSegment(segments.toArray(new Segment[0]));
	}

	@Override
	public StateBuffer execute(StateBuffer in) {
		return sequence.execute(in);
	}
}
