package mrwint.gbtasgen.segment.pokemon.fight;

import static mrwint.gbtasgen.state.Gameboy.curGb;
import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.util.Util;

public class EflCheckMoveOrderMetric implements StateResettingMetric {
		final int[] move;
		final Boolean faster;
		public EflCheckMoveOrderMetric(Boolean faster, int... move) {
			this.faster = faster;
			this.move = move;
		}
		@Override
		public int getMetricInternal() {
			//System.out.println("runToAddress");
			//try {Thread.sleep(2000);} catch (InterruptedException e) {}
			Util.runToAddressNoLimit(0, 0, curGb.pokemon.fightDetermineAttackOrder);
			//System.out.println("ranToAddress");
			//try {Thread.sleep(2000);} catch (InterruptedException e) {}
			int enemyMove = curGb.readMemory(curGb.pokemon.fightCurEnemyMoveAddress); // selected enemy move
			if(move.length > 0 && !Util.arrayContains(move, enemyMove))
				return 0;
			int add = Util.runToAddressNoLimit(0,0,curGb.pokemon.fightDetermineAttackOrderPlayerFirst,curGb.pokemon.fightDetermineAttackOrderEnemyFirst);
			if (faster != null && (add == curGb.pokemon.fightDetermineAttackOrderPlayerFirst) != faster)
				return 0;
//			System.out.println("move "+enemyMove);
			if (faster != null && !faster)
				add = Util.runToAddressNoLimit(0,0,curGb.pokemon.fightAIMoveCheck); // Check for AI moves (item uses etc.)
			else
				return 1;
			return (add == curGb.pokemon.fightAIExecuteMove) ? 1 : 0;
		}
	}