package mrwint.gbtasgen.tools.tetris.heuristic;

import mrwint.gbtasgen.tools.tetris.search.SearchAlgorithm.SearchState;

public interface Heuristic {
  public int estimateCostToGoal(SearchState state);
}
