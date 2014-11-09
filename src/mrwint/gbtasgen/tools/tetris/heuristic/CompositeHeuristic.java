package mrwint.gbtasgen.tools.tetris.heuristic;

import mrwint.gbtasgen.tools.tetris.search.SearchAlgorithm.SearchState;

public class CompositeHeuristic implements Heuristic {
  private Heuristic[] heuristics;

  public CompositeHeuristic(Heuristic... heuristics) {
    this.heuristics = heuristics;
  }
  @Override
  public int estimateCostToGoal(SearchState state) {
    int max = 0;
    for (Heuristic h : heuristics)
      max = Math.max(max, h.estimateCostToGoal(state));
    return max;
  }
}
