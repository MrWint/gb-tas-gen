package mrwint.gbtasgen.segment.pokemon.gen1.catchemall;

import mrwint.gbtasgen.move.pokemon.gen1.OverworldInteract;
import mrwint.gbtasgen.segment.pokemon.WalkToSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;

public class OaksParcel extends SeqSegment {
	
	@Override
	public void execute() {
		seq(new WalkToSegment(5, 12, false)); // leave oaks lab
		seq(new WalkToSegment(10, -1)); // walk out of town
		seq(new WalkToSegment(10, -1)); // walk out to viridian
		seq(new WalkToSegment(29, 19)); // walk into mart
		seq(new SkipTextsSegment(5)); // take parcel
		seq(new WalkToSegment(3, 8, false)); // walk outside
		seq(new WalkToSegment(26, 27, false)); // viridian ledge
		seq(new WalkToSegment(21, 36)); // leave viridian
		seq(new WalkToSegment(14, 19, false)); // ledge jump 1
		seq(new WalkToSegment(11, 27, false)); // ledge jump 2
		seq(new WalkToSegment(11, 36)); // enter pallet town
		seq(new WalkToSegment(12, 11)); // enter oaks lab
		seq(new WalkToSegment(4, 2)); // stand left of oak
//		seq(new WalkToSegment(5, 1)); // stand behind oak
		seq(new WalkToSegment(5, 2, false)); // face towards oak
		//seq(new WalkToSegment(5, 3)); // stand in front of oak
		seq(new MoveSegment(new OverworldInteract(5))); // talk to oak
		seq(new SkipTextsSegment(41)); // oak parcel speech
		seq(new WalkToSegment(4, 12, false)); // leave oaks lab
		//seq(new WalkToSegment(5, 12, false)); // leave oaks lab
		seq(new WalkToSegment(10, -1)); // leave pallet town
		seq(new WalkToSegment(10, -1)); // enter viridian
	}
}
