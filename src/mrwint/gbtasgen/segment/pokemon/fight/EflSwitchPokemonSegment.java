package mrwint.gbtasgen.segment.pokemon.fight;

import mrwint.gbtasgen.metric.pokemon.gen1.CheckNoAIMoveNew;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.pokemon.fight.EflKillEnemyMonSegment.EflEnemyMoveDesc;
import mrwint.gbtasgen.segment.pokemon.gen1.common.EflSelectMonSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.state.StateBuffer;

public class EflSwitchPokemonSegment extends SeqSegment {

	int mon;
	EflEnemyMoveDesc enemyMoveDesc;

	public EflSwitchPokemonSegment(int mon, EflEnemyMoveDesc enemyMoveDesc) {
	  this.mon = mon;
	  this.enemyMoveDesc = enemyMoveDesc;
	}
  @Override
  protected void execute() {
    seqEflButton(Move.RIGHT);
    seqEflButton(Move.A);
    seq(new EflSelectMonSegment(mon));
    seqEflSkipInput(1);
    delayEfl(new SeqSegment() {
      @Override
      protected void execute() {
        seqEflButtonUnbounded(Move.A);
        seqUnbounded(new EflTextSegment()); // enough
        seqUnbounded(new EflTextSegment(Move.A)); // go
        seqMetric(new EflCheckMoveOrderMetric(null, enemyMoveDesc.move));
        seqMetric(new CheckNoAIMoveNew());
        seq(new Segment() {
          @Override
          public StateBuffer execute(StateBuffer in) {
            return enemyMoveDesc.segment.execute(in, 0);
          }
        }); // use and miss
      }
    });
    if (enemyMoveDesc.segment.getFinishSegment() != null)
      seq(enemyMoveDesc.segment.getFinishSegment());
  }
}
