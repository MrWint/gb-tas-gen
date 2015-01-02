package mrwint.gbtasgen.tools.tetris.heuristic;

import static mrwint.gbtasgen.tools.tetris.TetrisUtil.reverseBoardBits;
import mrwint.gbtasgen.tools.tetris.search.SearchAlgorithm;
import mrwint.gbtasgen.tools.tetris.search.SearchAlgorithm.SearchState;

public class FieldEmptyHeuristic implements Heuristic {
  private int line;
  private short mask;
  public FieldEmptyHeuristic(int line, int mask) {
    this.line = line;
    this.mask = reverseBoardBits(mask);
  }
  @Override
  public int estimateCostToGoal(SearchState state) {
    return (state.board.board[line] & mask) == 0 ? 0 : SearchAlgorithm.OO;
  }
}
