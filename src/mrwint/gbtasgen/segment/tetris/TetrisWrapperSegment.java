package mrwint.gbtasgen.segment.tetris;

import static mrwint.gbtasgen.state.Gameboy.curGb;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.state.StateBuffer;
import mrwint.gbtasgen.state.tetris.TetrisStateBuffer;

public class TetrisWrapperSegment implements Segment {

  private TetrisSegment segment;

  public TetrisWrapperSegment(TetrisSegment segment) {
    this.segment = segment;
  }

  @Override
  public StateBuffer execute(StateBuffer in) {
    TetrisStateBuffer buf = new TetrisStateBuffer();
    for (State s : in.getStates())
      buf.addState(curGb, s);
    buf = segment.execute(buf);
    StateBuffer ret = new StateBuffer();
    for (StateBuffer sb : buf.getMap().values())
      ret.addAll(sb);
    return ret;
  }
}
