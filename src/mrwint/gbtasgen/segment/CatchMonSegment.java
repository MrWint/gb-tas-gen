package mrwint.gbtasgen.segment;

import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.main.RomInfo;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.gen1.common.NamingSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;

public class CatchMonSegment extends SeqSegment {

	int numScrolls;
	String name;
	
	public CatchMonSegment(int numScrolls) {
		this.numScrolls = numScrolls;
	}
	public CatchMonSegment(int numScrolls, String name) {
		this.numScrolls = numScrolls;
		this.name = name;
	}
	
	public void execute() {
		boolean partyFull = Gb.readMemory(RomInfo.rom.numPartyMonAddress) == 6;
		seq(new SkipTextsSegment(2)); // wild mon, go mon
		seq(Segment.press(Move.DOWN)); // items
		seq(Segment.press(Move.A)); // select items
		seq(Segment.scrollFast(numScrolls)); // select ball
		seq(new BallSuccessSegment());

		seq(new SkipTextsSegment(4)); // cought, new dex data
		seq(Segment.press(Move.A)); // skip dex
		seq(Segment.press(Move.B)); // skip dex
		seq(new SkipTextsSegment(1)); // nickname
		seq(new SkipTextsSegment(1, name != null)); // nickname?
		if (name != null) {
			seq(new NamingSegment("A"));
			seq(Move.START);
		}
		if (partyFull) {
			seq(new SkipTextsSegment(2)); // transferred to PC
		}
	}
}
