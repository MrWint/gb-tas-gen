package mrwint.gbtasgen.segment.pokemon.fight;

import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;

public class EflEndFightSegment extends EflSkipTextsSegment {

	public EflEndFightSegment(int numEndTextScrolls) {
		super(1 + numEndTextScrolls + 1); // player defeated enemy, end of fight texts, player got money for winning
	}
}
