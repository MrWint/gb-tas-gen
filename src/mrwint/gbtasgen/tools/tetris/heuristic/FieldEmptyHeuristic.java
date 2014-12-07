package mrwint.gbtasgen.tools.tetris.heuristic;

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
  public static short reverseBoardBits(int mask) {
    short result = 0;
    for (int i=0;i<10;i++) {
      int bit = mask & 1;
      mask >>= 1;
      result = (short)(bit + (result << 1));
    }
    return result;
  }
}
