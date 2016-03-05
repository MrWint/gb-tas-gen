package mrwint.gbtasgen.segment.pokemon.fight;

import static mrwint.gbtasgen.metric.comparator.Comparator.EQUAL;
import static mrwint.gbtasgen.move.Move.A;
import static mrwint.gbtasgen.move.Move.DOWN;
import static mrwint.gbtasgen.util.EflUtil.PressMetric.PRESSED;
import mrwint.gbtasgen.metric.pokemon.gen1.CheckNoAIMoveNew;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment.EflEnemyMoveDesc;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSelectItemSegment;
import mrwint.gbtasgen.segment.util.EflSkipTextsSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.state.StateBuffer;

public class EflUseBattleItemSegment extends SeqSegment {

	int item;
	EflEnemyMoveDesc enemyMoveDesc;

	public EflUseBattleItemSegment(int item, EflEnemyMoveDesc enemyMoveDesc) {
    this.item = item;
	  this.enemyMoveDesc = enemyMoveDesc;
	}
  @Override
  protected void execute() {
    seqEflButton(DOWN | A, PRESSED);
    seqEflSkipInput(1);
    delayEfl(new SeqSegment() {
      @Override
      protected void execute() {
        seq(new EflSelectItemSegment(item));
        seqUnbounded(new EflSkipTextsSegment(1)); // used item
        seqUnbounded(new EflSkipTextsSegment(1)); // stat rose (item effect)
        seqMetric(new EflCheckMoveOrderMetric(null, enemyMoveDesc != null ? enemyMoveDesc.move : new int[0]));
        seqMetric(new CheckNoAIMoveNew(), EQUAL, enemyMoveDesc != null ? 1 : 0);
        if (enemyMoveDesc != null)
          seq(new Segment() {
            @Override
            public StateBuffer execute(StateBuffer in) {
              return enemyMoveDesc.segment.execute(in, 0);
            }
          }); // use and miss
      }
    });
    if (enemyMoveDesc != null) {
      if (enemyMoveDesc.segment.getFinishSegment() != null)
        seq(enemyMoveDesc.segment.getFinishSegment());
    } else {
      seq(new EflSkipTextsSegment(2)); // Opp used X on Y
    }
  }
}
