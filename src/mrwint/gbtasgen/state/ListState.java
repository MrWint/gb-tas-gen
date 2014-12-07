package mrwint.gbtasgen.state;

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

  public State toState(State initialState) {
    initialState.restore();
    execute(this);
    return State.createState(true);
  }

  public static void execute(ListState state) {
    if (state == null)
      return;
    execute(state.prevState);
    Util.runToNextInputFrame();
    State.step(state.move);
  }
}
