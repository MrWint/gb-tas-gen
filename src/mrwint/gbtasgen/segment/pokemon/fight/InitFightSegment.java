package mrwint.gbtasgen.segment.pokemon.fight;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.pokemon.TextSegment;
import mrwint.gbtasgen.segment.util.DelayMoveSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SkipTextsSegment;
import mrwint.gbtasgen.util.EflUtil;
import mrwint.gbtasgen.util.pokemon.PokemonUtil;

public class InitFightSegment extends SeqSegment {

	int numPreBattleTexts;
	int[] enemyInitialMove;

	public InitFightSegment(int numPreBattleTexts, int... enemyInitialMove) {
    EflUtil.assertNoEfl();

    this.numPreBattleTexts = numPreBattleTexts;
	  this.enemyInitialMove = enemyInitialMove;
	}
  @Override
  protected void execute() {
    seq(new SkipTextsSegment(numPreBattleTexts)); // trainer pre-text
    seq(new SkipTextsSegment(1)); // trainer wants to fight
    if(PokemonUtil.isGen2()) {
      seq(new TextSegment()); // trainer sent out ...

      seq(new DelayMoveSegment(new DelayMoveSegment.PressButtonFactory(Move.B), // scroll
          new SeqSegment() {
            @Override
            protected void execute() {
              seqUnbounded(new TextSegment()); // mon!
              seqUnbounded(new TextSegment(Move.A,false)); // Go! mon!
              seqMetric(KillEnemyMonSegment.CheckEnemyMoveMetric.noKeys(enemyInitialMove));
              seqWait(1); // skip last frame of text box
            }
          },1, 5));
    } else {
      seq(new TextSegment()); // trainer sent out mon
      seq(new TextSegment()); // Go! mon!
    }
  }
}
