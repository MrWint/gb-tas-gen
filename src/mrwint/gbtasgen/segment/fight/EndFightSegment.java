package mrwint.gbtasgen.segment.fight;

import mrwint.gbtasgen.segment.util.SkipTextsSegment;

public class EndFightSegment extends SkipTextsSegment {
	
	public EndFightSegment(int numEndTextScrolls) {
		super(1 + numEndTextScrolls + 1); // player defeated enemy, end of fight texts, player got money for winning
	}
}
