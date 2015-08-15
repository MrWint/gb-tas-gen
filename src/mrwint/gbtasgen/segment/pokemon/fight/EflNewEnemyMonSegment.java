package mrwint.gbtasgen.segment.pokemon.fight;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.pokemon.EflTextSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.util.pokemon.PokemonUtil;

public class EflNewEnemyMonSegment extends SeqSegment {

  int[] enemyInitialMove;

  @Override
  protected void execute() {
    if (PokemonUtil.isGen1()) {
      seq(new EflTextSegment()); // trainer sent out mon
    } else {
      seq(new EflTextSegment()); // trainer sent out ...
      delayEfl(new SeqSegment() {
        @Override
        protected void execute() {
          seqEflButtonUnboundedNoDelay(Move.B);
          seqUnbounded(new EflTextSegment(Move.A)); // mon!
          seqMetric(EflKillEnemyMonSegment.CheckEnemyMoveMetric.noKeys(enemyInitialMove));
        }
      });
    }
  }

	private EflNewEnemyMonSegment(int... enemyInitialMove) {
	  this.enemyInitialMove = enemyInitialMove;
	}

	public static EflNewEnemyMonSegment any(int... enemyInitialMove) {
	  return new EflNewEnemyMonSegment(enemyInitialMove);
	}
}
