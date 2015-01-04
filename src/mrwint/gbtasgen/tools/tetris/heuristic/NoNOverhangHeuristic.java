package mrwint.gbtasgen.tools.tetris.heuristic;

import mrwint.gbtasgen.tools.tetris.Board;
import mrwint.gbtasgen.tools.tetris.search.SearchAlgorithm;
import mrwint.gbtasgen.tools.tetris.search.SearchAlgorithm.SearchState;

public class NoNOverhangHeuristic implements Heuristic {

  private int maxLines;
  private int n;
  public NoNOverhangHeuristic(int maxLines, int n) {
    this.maxLines = maxLines;
    this.n = n;
  }
  @Override
  public int estimateCostToGoal(SearchState state) {
    short[] board = state.board.board;
    for (int y = 0; y < maxLines - 1; y++) {
      for (int x = 0; x < Board.WIDTH - (n-1); x++) {
        int mask = (((1 << n) - 1) << x);
        if ((board[y] & mask) == mask && (board[y+1] & mask) == 0)
          return SearchAlgorithm.OO;
      }
    }
    return 0;
  }
}
