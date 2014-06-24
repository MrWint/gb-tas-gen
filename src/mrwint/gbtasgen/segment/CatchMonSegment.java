package mrwint.gbtasgen.segment;

import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.main.RomInfo;
import mrwint.gbtasgen.metric.MemoryAddress;
import mrwint.gbtasgen.metric.comparator.Equal;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.gen1.common.NamingSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;

public class CatchMonSegment extends SeqSegment {

	int numScrolls;
	String name;
	int withCooltrainerMon;
	int extraBPresses, extraBPresses2;
	
	public CatchMonSegment(int numScrolls) {
		this(numScrolls, null, 0, 0, 0);
	}
	public CatchMonSegment(int numScrolls, int withCooltrainerMon, int extraBPresses, int extraBPresses2) {
		this(numScrolls, null, withCooltrainerMon, extraBPresses, extraBPresses2);
	}
	public CatchMonSegment(int numScrolls, String name) {
		this(numScrolls, name, 0, 0, 0);
	}
	public CatchMonSegment(int numScrolls, String name, int withCooltrainerMon, int extraBPresses, int extraBPresses2) {
		this.numScrolls = numScrolls;
		this.name = name;
		this.withCooltrainerMon = withCooltrainerMon;
		this.extraBPresses = extraBPresses;
		this.extraBPresses2 = extraBPresses2;
	}
	
	public void execute() {
		boolean partyFull = Gb.readMemory(RomInfo.rom.numPartyMonAddress) == 6;
		seq(new SkipTextsSegment(2)); // wild mon, go mon
		if (withCooltrainerMon > 0) {
			delay(new SeqSegment() {
				@Override
				protected void execute() {
					seq(Move.A); // select fight
					seq(Move.B); // back
					seq(new MemoryAddress(RomInfo.rom.encounterMonSpeciesAddress), new Equal(), withCooltrainerMon);
				}
			});
		}
		for(int i=0;i<extraBPresses;i++)
			seq(Segment.repress(Move.B));
		seq(Segment.press(Move.DOWN)); // items
		seq(Segment.press(Move.A)); // select items
		for(int i=0;i<extraBPresses2;i++)
			seq(Segment.repress(Move.B));
		seq(Segment.scrollFast(numScrolls)); // select ball
		seq(new BallSuccessSegment());

		seq(new SkipTextsSegment(4)); // cought, new dex data
		seq(Segment.press(Move.A)); // skip dex
		seq(Segment.press(Move.B)); // skip dex
		seq(new SkipTextsSegment(1)); // nickname
		seq(new SkipTextsSegment(1, name != null)); // nickname?
		if (name != null) {
			seq(new NamingSegment(name));
			seq(Move.START);
		}
		if (partyFull) {
			seq(new SkipTextsSegment(2)); // transferred to PC
		}
	}
}
