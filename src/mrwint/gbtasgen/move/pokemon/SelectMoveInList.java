package mrwint.gbtasgen.move.pokemon;

import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.rom.RomInfo;

public class SelectMoveInList extends Move {

	int goalIndex;
	int numOwnMoves;
	
	public SelectMoveInList(int goalIndex, int numOwnMoves) {
		this.goalIndex = goalIndex;
		this.numOwnMoves = numOwnMoves;
	}
	
	@Override
	public int getInitialKey() {
		int curIndex = Gb.readMemory(RomInfo.pokemon.fightCurMoveNumAddress);
		int stepsDown = (goalIndex + numOwnMoves - curIndex) % numOwnMoves;
		int stepsUp = (curIndex + numOwnMoves - goalIndex) % numOwnMoves;
		if(stepsDown < stepsUp)
			return Move.DOWN;
		else
			return Move.UP;
	}

	@Override
	public boolean doMove() {
		int curIndex = Gb.readMemory(RomInfo.pokemon.fightCurMoveNumAddress);
		//System.out.println("current move index "+curIndex+", goal position is "+goalIndex);

		int stepsDown = (goalIndex + numOwnMoves - curIndex) % numOwnMoves;
		int stepsUp = (curIndex + numOwnMoves - goalIndex) % numOwnMoves;
		if(stepsDown < stepsUp) {
			//System.out.println("taking "+stepsDown+" steps down");
			while(stepsDown-->0)
				new PressButton(Move.DOWN, Metric.PRESSED_JOY).execute();
		} else if(stepsUp > 0) {
			//System.out.println("taking "+stepsUp+" steps up");
			while(stepsUp-->0)
				new PressButton(Move.UP, Metric.PRESSED_JOY).execute();
		}
		//Thread.sleep(1000);
		return true;
	}

}
