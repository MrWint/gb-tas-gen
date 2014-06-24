package mrwint.gbtasgen.segment;

import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.main.RomInfo;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;

public class CatchSafariMonSegment extends SeqSegment {

	public void execute() {
		boolean partyFull = Gb.readMemory(RomInfo.rom.numPartyMonAddress) == 6;
		seq(new SkipTextsSegment(1)); // wild mon
		seq(new BallSuccessSegment());

		seq(new SkipTextsSegment(4)); // cought, new dex data
		seq(Segment.press(Move.A)); // skip dex
		seq(Segment.press(Move.B)); // skip dex
		seq(new SkipTextsSegment(2)); // nickname
		if (partyFull) {
			seq(new SkipTextsSegment(2)); // transferred to PC
		}
	}
}
