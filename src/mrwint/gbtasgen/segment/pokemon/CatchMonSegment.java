package mrwint.gbtasgen.segment.pokemon;

import static mrwint.gbtasgen.metric.comparator.Comparator.EQUAL;
import static mrwint.gbtasgen.state.Gameboy.curGb;
import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.gen1.common.NamingSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.util.EflUtil;

public class CatchMonSegment extends SeqSegment {

	int numScrolls;
	String name;
	int withCooltrainerMon;
	int extraBPresses, extraBPresses2;
	int extraSkips = 0;

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
	  EflUtil.assertNoEfl();

		this.numScrolls = numScrolls;
		this.name = name;
		this.withCooltrainerMon = withCooltrainerMon;
		this.extraBPresses = extraBPresses;
		this.extraBPresses2 = extraBPresses2;
	}

	public CatchMonSegment withExtraSkips(int extraSkips) {
		this.extraSkips = extraSkips;
		return this;
	}

	@Override
	public void execute() {
		seq(new SkipTextsSegment(2)); // wild mon, go mon
    boolean partyFull = curGb.readMemory(curGb.pokemon.numPartyMonAddress) >= 6;
		if (withCooltrainerMon > 0) {
			delay(new SeqSegment() {
				@Override
				protected void execute() {
					seqButton(Move.A); // select fight
					seqButton(Move.B); // back
					seqMetric(Metric.forAddress(curGb.pokemon.encounterMonSpeciesAddress), EQUAL, withCooltrainerMon);
				}
			});
		}
		for(int i=0;i<extraBPresses;i++)
			seq(Segment.repress(Move.B));
		seq(Segment.press(Move.DOWN | Move.A)); // items
//		seq(Segment.press(Move.A)); // select items
		for(int i=0;i<extraBPresses2;i++)
			seq(Segment.repress(Move.B));
		seq(Segment.scrollFast(numScrolls)); // select ball
		seq(new BallSuccessSegment());

		seq(new SkipTextsSegment(4)); // cought, new dex data
		seq(Segment.press(Move.A)); // skip dex
		seq(Segment.press(Move.B)); // skip dex
//		seq(new SkipTextsSegment(1)); // nickname
		seq(new TextSegment());
		if (extraSkips > 0)
			seq(Segment.skip(extraSkips));
		seqButton(Move.B);
		seq(new SkipTextsSegment(1, name != null)); // nickname?
		if (name != null) {
			seq(new NamingSegment(name));
			seqButton(Move.START);
		}
		if (partyFull) {
			seq(new SkipTextsSegment(2)); // transferred to PC
		}
	}
}
