package mrwint.gbtasgen.segment.gen1.old;

import java.util.ArrayList;
import java.util.List;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.move.gen1.OverworldInteract;
import mrwint.gbtasgen.segment.PokecenterSegment;
import mrwint.gbtasgen.segment.ResetAndContinueSegment;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.TextSegment;
import mrwint.gbtasgen.segment.WalkToSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SequenceSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.state.StateBuffer;

public class MtMoonNoTrainerSegment extends Segment {

	SequenceSegment sequence;
	
	public MtMoonNoTrainerSegment() {
		List<Segment> segments = new ArrayList<Segment>();
		segments.add(new WalkToSegment(11,5));
		segments.add(new PokecenterSegment(true)); // set warp point in center
		segments.add(new WalkToSegment(18,5));

		segments.add(new WalkToSegment(17,11,false).setBlockAllWarps(true)); // l2
		segments.add(new WalkToSegment(17,11)); // l3

		segments.add(new WalkToSegment(29,6));
		segments.add(Segment.press(Move.UP)); // face item
		segments.add(new MoveSegment(new OverworldInteract(9))); // collect TM01
		segments.add(new WalkToSegment(29,7));
		segments.add(new MoveSegment(new PressButton(Move.START),0)); // start trainer fly
		segments.add(Segment.scroll(2));
		segments.add(Segment.press(Move.A)); // items
		{ // learn TM01
			segments.add(Segment.scrollFast(2));
			segments.add(Segment.press(Move.A)); // select TM01
			segments.add(Segment.repress(Move.A)); // use
			{
				segments.add(new SkipTextsSegment(2)); // booted up TM, contains xyz
				segments.add(new SkipTextsSegment(1, true)); // learn
				segments.add(Segment.repress(Move.A)); // select charmander
				segments.add(new SkipTextsSegment(1)); // learned TM
			}
		}
		segments.add(Segment.press(Move.A)); // select escape rope
		segments.add(Segment.repress(Move.A)); // use
		segments.add(Segment.skip(1)); // wait for rope to start
		segments.add(new WalkToSegment(11,5)); // into pokecenter
		segments.add(new WalkToSegment(13,4));
		segments.add(new WalkToSegment(13,3, false)); // correct facing direction
		segments.add(Segment.press(Move.A)); // use PC
		segments.add(new SkipTextsSegment(1)); // booted PC
		segments.add(Segment.press(Move.A)); // someones PC
		segments.add(new SkipTextsSegment(2)); // someones PC, mon storage system
		segments.add(Segment.scroll(3));
		segments.add(Segment.press(Move.A)); // change box
//		//segments.add(new TextSegment()); // text segments will be skipped (bug?)
		segments.add(Segment.press(Move.B)); // change box
		segments.add(Segment.repress(Move.B)); // saving
		segments.add(Segment.press(Move.A)); // save
		segments.add(Segment.menu(Move.A)); // switch to box 1
		segments.add(new ResetAndContinueSegment()); // quit and continue

		segments.add(new WalkToSegment(4,8,false));
		segments.add(new WalkToSegment(18,5));

//		segments.add(new WalkToSegment(17,11,-1,false,true)); // l2
//		segments.add(new WalkToSegment(17,11)); // l3
//		
//		segments.add(new WalkToSegment(32,9).setIgnoreTrainers(true)); // go to Ether
//		segments.add(new MoveSegment(new CollectHiddenItem())); // get Ether
//		segments.add(new WalkToSegment(25,9).setIgnoreTrainers(true)); // go to l2
//		segments.add(new WalkToSegment(25,9)); // go to l1
//		
		segments.add(new WalkToSegment(5,5,false).setBlockAllWarps(true)); // go to MtMoon2
		segments.add(new WalkToSegment(21,17)); // go to MtMoon3
		segments.add(new WalkToSegment(13,7).setIgnoreTrainers(true)); // go to fossil
		segments.add(new MoveSegment(new OverworldInteract(7))); // grab fossil
		segments.add(new SkipTextsSegment(1, true)); // grab fossil
		segments.add(new SkipTextsSegment(1)); // got fossil
		segments.add(new TextSegment()); // put fossil in bag
		segments.add(new WalkToSegment(13,5)); // go upwards (avoid running into moved nerd)
		segments.add(new WalkToSegment(5,7)); // go to MtMoon2
		segments.add(new WalkToSegment(27,3)); // leave MtMoon
		segments.add(new WalkToSegment(76,9,false));
		segments.add(new WalkToSegment(90,10)); // enter Cerulean
		segments.add(new WalkToSegment(19,17)); // enter Center
		segments.add(new PokecenterSegment(false)); // set warp point in center
		sequence = new SequenceSegment(segments.toArray(new Segment[0]));
	}
	
	@Override
	public StateBuffer execute(StateBuffer in) {
		return sequence.execute(in);
	}
}
