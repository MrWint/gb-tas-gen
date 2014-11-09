package mrwint.gbtasgen.tools.tetris.heuristic;

import mrwint.gbtasgen.tools.tetris.search.SearchAlgorithm.SearchState;

public class NullHeuristic implements Heuristic {
  @Override
  public int estimateCostToGoal(SearchState state) {
    return 0;
  }
}
