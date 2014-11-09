package mrwint.gbtasgen.tools.tetris.heuristic;

import mrwint.gbtasgen.tools.tetris.search.SearchAlgorithm;
import mrwint.gbtasgen.tools.tetris.search.SearchAlgorithm.SearchState;

public class NoOverhangHeuristic implements Heuristic {

  private int maxLines;
  private int skipLines;
  public NoOverhangHeuristic(int maxLines) {
    this(maxLines, 0);
  }
  public NoOverhangHeuristic(int maxLines, int skipLines) {
    this.maxLines = maxLines;
    this.skipLines = skipLines;
  }
  @Override
  public int estimateCostToGoal(SearchState state) {
    short[] board = state.board.board;
    for (int y = skipLines; y < maxLines - 1; y++) {
      if ((board[y] | board[y+1]) != board[y+1])
        return SearchAlgorithm.OO;
    }
    return 0;
  }
}
