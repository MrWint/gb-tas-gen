package mrwint.gbtasgen.move.pokemon;

import static mrwint.gbtasgen.state.Gameboy.curGb;
import mrwint.gbtasgen.move.EflPressButton;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.util.EflUtil;
import mrwint.gbtasgen.util.EflUtil.PressMetric;

public class EflSelectMoveInList extends Move {

	int goalIndex;
	int numOwnMoves;

	public EflSelectMoveInList(int goalIndex, int numOwnMoves) {
    EflUtil.assertEfl();

    this.goalIndex = goalIndex;
		this.numOwnMoves = numOwnMoves;
	}

	@Override
	public int getInitialKey() {
	  return 0; // no need in efl moves
	}

	@Override
	public boolean doMove() {
		int curIndex = curGb.readMemory(curGb.pokemon.fightCurMoveNumAddress);
		//System.out.println("current move index "+curIndex+", goal position is "+goalIndex);

		int stepsDown = (goalIndex + numOwnMoves - curIndex) % numOwnMoves;
		int stepsUp = (curIndex + numOwnMoves - goalIndex) % numOwnMoves;
		if(stepsDown < stepsUp) {
			//System.out.println("taking "+stepsDown+" steps down");
			while(stepsDown-->0)
				new EflPressButton(Move.DOWN, PressMetric.PRESSED).execute();
		} else if(stepsUp > 0) {
			//System.out.println("taking "+stepsUp+" steps up");
			while(stepsUp-->0)
				new EflPressButton(Move.UP, PressMetric.PRESSED).execute();
		}
		//Thread.sleep(1000);
		return true;
	}

}
