package mrwint.gbtasgen.segment.tetris;

import static mrwint.gbtasgen.state.Gameboy.curGb;

import java.util.Map.Entry;

import mrwint.gbtasgen.metric.comparator.Comparator;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.state.StateBuffer;
import mrwint.gbtasgen.state.tetris.TetrisStateBuffer;
import mrwint.gbtasgen.util.Util;

public class TetrisWaitSegment implements TetrisSegment {

  int numSkips;

  public TetrisWaitSegment(int numSkips) {
    this.numSkips = numSkips;
  }

  @Override
  public TetrisStateBuffer execute(TetrisStateBuffer in) {
    TetrisStateBuffer out = new TetrisStateBuffer();

    for (Entry<Short, StateBuffer> e : in.getMap().entrySet()) {
      for (State s : e.getValue().getStates()) {
        curGb.restore(s);
        Util.runToFrameBeforeUntil(0, () -> curGb.readMemory(curGb.tetris.hCurPieceState) + curGb.readMemory(curGb.tetris.hBoardUpdateState), Comparator.EQUAL, 0);
//        State.steps(numSkips);
        out.addState(curGb.createState(true), e.getKey());
      }
    }
    return out;
  }
}
