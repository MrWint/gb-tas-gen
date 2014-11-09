package mrwint.gbtasgen.tools.tetris.heuristic;

import mrwint.gbtasgen.tools.tetris.search.SearchAlgorithm;
import mrwint.gbtasgen.tools.tetris.search.SearchAlgorithm.SearchState;

public class ClearLinesHeuristic implements Heuristic {
  private int numLines;
  public ClearLinesHeuristic(int numLines) {
    this.numLines = numLines;
  }
  @Override
  public int estimateCostToGoal(SearchState state) {
    short[] board = state.board.board;
    for (int y = 0; y < numLines; y++) {
      if (board[y] != 0)
        return SearchAlgorithm.OO;
    }
    return 0;
  }
}
