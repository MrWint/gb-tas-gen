package mrwint.gbtasgen.segment.pokemon.gen2.any;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.TextSegment;
import mrwint.gbtasgen.segment.pokemon.WalkToSegment;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.state.StateBuffer;
import mrwint.gbtasgen.util.pokemon.PokemonUtil;


public class ToViolet2Segment implements Segment {

	SequenceSegment sequence;

	public ToViolet2Segment() {
		List<Segment> segments = new ArrayList<Segment>();

		segments.add(new SkipTextsSegment(8));			// mom phone
		for(int i=0;i<4;i++)
			segments.add(new TextSegment());			// click, ..., ..., ...
		if(!PokemonUtil.isCrystal()) {
//			{
//				segments.add(new WalkToSegment(21, 11));
//				segments.add(new MoveSegment(new OverworldInteract()));
//				segments.add(new TextSegment());
//				segments.add(new SkipTextsSegment(2));
//			}
			segments.add(new WalkToSegment(16, 11));	// jump ledge
		}
		segments.add(new WalkToSegment(3, 7, false));	// route 31 -> gate
		segments.add(new WalkToSegment(-1, 5, false));	// gate -> violet
		segments.add(new WalkToSegment(18, 17, false));	// enter gym
		segments.add(new WalkToSegment(4, 10, false));	// engage bird keeper abe

		sequence = new SequenceSegment(segments.toArray(new Segment[0]));
	}

	@Override
	public StateBuffer execute(StateBuffer in) {
		return sequence.execute(in);
	}
}
