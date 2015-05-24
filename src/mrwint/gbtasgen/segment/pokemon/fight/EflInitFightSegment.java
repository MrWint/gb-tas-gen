package mrwint.gbtasgen.segment.pokemon.fight;

import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.util.EflUtil;

public class EflInitFightSegment extends SeqSegment {

	int numPreBattleTexts;

	public EflInitFightSegment(int numPreBattleTexts) {
    EflUtil.assertEfl();

    this.numPreBattleTexts = numPreBattleTexts;
	}
  @Override
  protected void execute() {
    seq(new EflSkipTextsSegment(numPreBattleTexts)); // trainer pre-text
    seq(new EflSkipTextsSegment(1)); // trainer wants to fight
    seq(new EflTextSegment()); // trainer sent out mon
    seq(new EflTextSegment()); // Go! mon!
  }
}
