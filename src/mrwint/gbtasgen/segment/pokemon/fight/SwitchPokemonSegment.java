package mrwint.gbtasgen.segment.pokemon.fight;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.TextSegment;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment.CheckMoveOrderMetric;
import mrwint.gbtasgen.segment.pokemon.fight.KillEnemyMonSegment.EnemyMoveDesc;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.state.StateBuffer;

public class SwitchPokemonSegment extends SeqSegment {

	int monScrolls;
	EnemyMoveDesc enemyMoveDesc;
	boolean faster;

	public SwitchPokemonSegment(int monScrolls, EnemyMoveDesc enemyMoveDesc, boolean faster) {
	  this.monScrolls = monScrolls;
	  this.enemyMoveDesc = enemyMoveDesc;
	  this.faster = faster;
	}
  @Override
  protected void execute() {
    seqButton(Move.RIGHT);
    seqButton(Move.A);
    seq(Segment.scrollA(monScrolls));
    seqSkipInput(1);
    delay(new SeqSegment() {
      @Override
      protected void execute() {
        seqButton(Move.A);
        seq(new TextSegment()); // enough
        seqUnbounded(new TextSegment(Move.A, false)); // go
        seqMetric(new CheckMoveOrderMetric(faster, enemyMoveDesc.move, 0));
        seqWait(1); // end text frame
        seq(new Segment() {
          @Override
          public StateBuffer execute(StateBuffer in) {
            return enemyMoveDesc.segment.execute(in, 0);
          }
        }); // use and miss
      }
    });
    if (enemyMoveDesc.segment.getFinishMove() != null)
    seqMove(enemyMoveDesc.segment.getFinishMove().create());
  }
}
