package mrwint.gbtasgen.state;

import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.metric.comparator.Comparator;
import mrwint.gbtasgen.rom.RomInfo;
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

  public State toState(State initialState, boolean linesCleared) {
    initialState.restore();
    execute(this);
    State.steps(2);
    if (linesCleared)
      Util.runToFrameBeforeUntil(0, () -> Gb.readMemory(RomInfo.tetris.hCurPieceState) + Gb.readMemory(RomInfo.tetris.hBoardUpdateState), Comparator.EQUAL, 0);
    return State.createState(true);
  }

  public static void execute(ListState state) {
    if (state == null)
      return;
    execute(state.prevState);
    if (state.stepCount <= 2)
      Util.runToNextInputFrame();
    State.step(state.move);
  }
}
