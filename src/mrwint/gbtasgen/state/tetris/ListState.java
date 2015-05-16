package mrwint.gbtasgen.state.tetris;

import mrwint.gbtasgen.metric.comparator.Comparator;
import mrwint.gbtasgen.state.Gameboy;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.util.Util;


public class ListState {
  public final int stepCount;
  public final int move;
  public final ListState prevState;

  public ListState(int move, ListState prevState) {
    this.move = move;
    this.prevState = prevState;
    this.stepCount = prevState == null ? 1 : prevState.stepCount + 1;
  }

  public State toState(Gameboy gb, State initialState, boolean linesCleared) {
    gb.restore(initialState);
    execute(gb, this);
    gb.steps(2);
    if (linesCleared)
      Util.runToFrameBeforeUntil(0, () -> gb.readMemory(gb.tetris.hCurPieceState) + gb.readMemory(gb.tetris.hBoardUpdateState), Comparator.EQUAL, 0);
    return gb.createState(true);
  }

  public static void execute(Gameboy gb, ListState state) {
    if (state == null)
      return;
    execute(gb, state.prevState);
    if (state.stepCount <= 2)
      Util.runToNextInputFrame();
    gb.step(state.move);
  }
}
