package mrwint.gbtasgen.segment.pokemon;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.pokemon.gen1.EflOverworldInteract;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.util.EflUtil;

public class EflPokecenterSegment extends SeqSegment {

	boolean firstVisit;

	public EflPokecenterSegment(boolean firstVisit) {
    EflUtil.assertEfl();

    this.firstVisit = firstVisit;
	}

	@Override
	public void execute() {
		seq(new EflWalkToSegment(3, 3)); // walk to counter
		seq(new MoveSegment(new EflOverworldInteract(1))); // talk to nurse
		seq(new EflSkipTextsSegment(3));
		if(firstVisit) {
      seqEflButton(Move.B); // save 30f
      seq(new EflTextSegment(Move.B)); // extra line on first visit
		}
		seqEflButton(Move.A); // confirm healing mons
		seq(new EflTextSegment()); // need your mons
    seq(new EflSkipTextsSegment(2));
    seqEflButton(Move.B); // save 30f
    seq(new EflSkipTextsSegment(1));
		seq(new EflWalkToSegment(3, 8, false)); // leave center
	}
}
