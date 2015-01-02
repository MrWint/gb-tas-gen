package mrwint.gbtasgen.segment.tetris;

import java.util.Map.Entry;

import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.metric.Metric;
import mrwint.gbtasgen.metric.comparator.Comparator;
import mrwint.gbtasgen.rom.RomInfo;
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
        s.restore();
        Util.runToFrameBeforeUntil(0, () -> Gb.readMemory(RomInfo.tetris.hCurPieceState) + Gb.readMemory(RomInfo.tetris.hBoardUpdateState), Comparator.EQUAL, 0);
//        State.steps(numSkips);
        out.addState(State.createState(true), e.getKey());
      }
    }
    return out;
  }
}
