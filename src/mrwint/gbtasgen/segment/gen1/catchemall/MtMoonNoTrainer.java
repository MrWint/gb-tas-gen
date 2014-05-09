package mrwint.gbtasgen.segment.gen1.catchemall;

import mrwint.gbtasgen.metric.CheckEncounterMetric;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.move.gen1.OverworldInteract;
import mrwint.gbtasgen.segment.CatchMonSegment;
import mrwint.gbtasgen.segment.PokecenterSegment;
import mrwint.gbtasgen.segment.ResetAndContinueSegment;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.TextSegment;
import mrwint.gbtasgen.segment.WalkToSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;

public class MtMoonNoTrainer extends SeqSegment {
	
	@Override
	public void execute() {
		seq(new WalkToSegment(11,5));
		seq(new PokecenterSegment(true)); // set warp point in center
		seq(new WalkToSegment(18,5));

		seq(new WalkToSegment(17,11,false).setBlockAllWarps(true)); // l2
		seq(new WalkToSegment(17,11)); // l3

		seq(new WalkToSegment(29,6));
		seq(Segment.press(Move.UP)); // face item
		seq(new MoveSegment(new OverworldInteract(9))); // collect TM01
		seq(new TextSegment()); // found TM01

		seq(new WalkToSegment(29,7));
		seq(new MoveSegment(new PressButton(Move.START),0)); // start trainer fly
		
		seq(Segment.scroll(2));
		seq(Segment.press(Move.A)); // items
		{ // learn TM01
			seq(Segment.scrollFast(2));
			seq(Segment.press(Move.A)); // select TM01
			seq(Segment.repress(Move.A)); // use
			{
				seq(new SkipTextsSegment(2)); // booted up TM, contains xyz
				seq(new SkipTextsSegment(1, true)); // learn
				seq(Segment.repress(Move.A)); // select charmander
				seq(new SkipTextsSegment(1)); // learned TM
			}
		}
		seq(Segment.press(Move.A)); // select escape rope
		seq(Segment.repress(Move.A)); // use
		seq(Segment.skip(1)); // wait for rope to start
		seq(new WalkToSegment(11,5)); // into pokecenter
		seq(new WalkToSegment(13,4));
		seq(new WalkToSegment(13,3, false)); // correct facing direction
		seq(Segment.press(Move.A)); // use PC
		seq(new SkipTextsSegment(1)); // booted PC
		seq(Segment.press(Move.A)); // someones PC
		seq(new SkipTextsSegment(2)); // someones PC, mon storage system
		seq(Segment.scroll(3));
		seq(Segment.press(Move.A)); // change box
//		//seq(new TextSegment()); // text segments will be skipped (bug?)
		seq(Segment.press(Move.B)); // change box
		seq(Segment.repress(Move.B)); // saving
		seq(Segment.press(Move.A)); // save
		seq(Segment.menu(Move.A)); // switch to box 1
		seq(new ResetAndContinueSegment()); // quit and continue

		seq(new WalkToSegment(4,8,false));
		seq(new WalkToSegment(18,5));

		seq(new WalkToSegment(33,31).setBlockAllWarps(true)); // go to Rare Candy
		seq(new WalkToSegment(34,31)); // go to Rare Candy
		seq(new OverworldInteract(10)); // pick up Rare Candy
		seq(new TextSegment()); // pick up Rare Candy

		seq(new WalkToSegment(4,2).setBlockAllWarps(true)); // go to Moon Stone
		seq(new WalkToSegment(3,2)); // go to Moon Stone
		seq(new OverworldInteract(9)); // pick up Moon Stone
		seq(new TextSegment()); // pick up Moon Stone

		seq(new WalkToSegment(5,5,false).setBlockAllWarps(true)); // go to MtMoon2
		seq(new WalkToSegment(21,17)); // go to MtMoon3

		seq(new WalkToSegment(17,12).setIgnoreTrainers(true)); // go to Moon Stone
		seq(Move.A); // pick up Moon Stone
		seq(new TextSegment()); // pick up Moon Stone

		seq(new WalkToSegment(13,7).setIgnoreTrainers(true)); // go to fossil
		seq(new MoveSegment(new OverworldInteract(7))); // grab fossil
		seq(new SkipTextsSegment(1, true)); // grab fossil
		seq(new SkipTextsSegment(1)); // got fossil
		seq(new TextSegment()); // put fossil in bag
		seq(new WalkToSegment(13,5)); // go upwards (avoid running into moved nerd)

		seq(new WalkToSegment(10, 4)); // go in grass
		delay(new SeqSegment() {
			@Override
			protected void execute() {
				seq(Move.LEFT);
				seq(new CheckEncounterMetric(4, 0)); // Clefairy
			}
		});
		seq(new CatchMonSegment(0));

		seq(new WalkToSegment(5,7)); // go to MtMoon2
		seq(new WalkToSegment(27,3)); // leave MtMoon
		seq(new WalkToSegment(76,9,false));
		seq(new WalkToSegment(90,10)); // enter Cerulean
		seq(new WalkToSegment(19,17)); // enter Center
		seq(new PokecenterSegment(false)); // set warp point in center
		seq(new WalkToSegment(20,6)); // go to rival fight
	}
}
