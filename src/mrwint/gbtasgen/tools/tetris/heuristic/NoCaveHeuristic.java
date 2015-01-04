package mrwint.gbtasgen.tools.tetris.heuristic;

import mrwint.gbtasgen.tools.tetris.Board;
import mrwint.gbtasgen.tools.tetris.search.SearchAlgorithm;
import mrwint.gbtasgen.tools.tetris.search.SearchAlgorithm.SearchState;

public class NoCaveHeuristic implements Heuristic {

  private int maxLines;

  public NoCaveHeuristic(int maxLines) {
    this.maxLines = maxLines;
  }
  @Override
  public int estimateCostToGoal(SearchState state) {
    short[] board = state.board.board;
    for (int y = 0; y < maxLines - 1; y++) {
      boolean beforeCave = true;
      boolean inCave = false;
      for (int x = 0; x < Board.WIDTH; x++) {
        if (isSet(board[y+1], x)) {
          if (inCave)
            return SearchAlgorithm.OO;
          beforeCave = true;
          inCave = false;
          continue;
        }
        if (isSet(board[y], x)) {
          if (beforeCave)
            inCave = true;
          beforeCave = false;
          continue;
        }
        beforeCave = false;
        inCave = false;
      }
      if (inCave)
        return SearchAlgorithm.OO;
    }
    return 0;
  }

  public static boolean isSet(short line, int bit) {
    return (line & (1 << bit)) != 0;
  }
}
