package mrwint.gbtasgen.segment.pokemon.fight;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.Wait;
import mrwint.gbtasgen.segment.pokemon.TextSegment;
import mrwint.gbtasgen.segment.util.CheckMetricSegment;
import mrwint.gbtasgen.segment.util.DelayMoveSegment;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SequenceSegment;
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
          new SequenceSegment(
              new TextSegment(), // mon!
              new TextSegment(Move.A,false,0), // Go! mon!
              new CheckMetricSegment(KillEnemyMonSegment.CheckEnemyMoveMetric.noKeys(enemyInitialMove)),
              new MoveSegment(new Wait(1),0,0) // skip last frame of text box

      ),1, 5));
    } else {
      seq(new TextSegment()); // trainer sent out mon
      seq(new TextSegment()); // Go! mon!
    }
  }
}
