package mrwint.gbtasgen.tools.tetris.search;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

import mrwint.gbtasgen.tools.tetris.heuristic.Heuristic;

public class IDAStar extends SearchAlgorithm {

  public IDAStar(short[] initialBoard, int[] forcedPieces, int rowsToClear, int initialDropDelay, Heuristic h) {
    super(initialBoard, forcedPieces, rowsToClear, initialDropDelay);
    this.h = h;
  }

  private final Heuristic h;
  private final HashMap<SearchState, Integer> stateDistMap = new HashMap<>();
  private HashMap<SearchState, Integer> statesWithUnexploredNodes;
  private int maxDist;

  @Override
  public StateDist search() {
    stateDistMap.clear();
    statesWithUnexploredNodes = new HashMap<>();

    SearchState initialState = getInitialState();
    maxDist = h.estimateCostToGoal(initialState);
    statesWithUnexploredNodes.put(initialState, 0);
    while (true) {
      for (SearchState state : statesWithUnexploredNodes.keySet()) {
        stateDistMap.remove(state);
      }
      HashMap<SearchState, Integer> tmp = statesWithUnexploredNodes;
      statesWithUnexploredNodes = new HashMap<>();
      int newMaxDist = OO;
      for (Entry<SearchState, Integer> state : tmp.entrySet()) {
        StateDist result = limitDfs(state.getKey(), state.getValue(), maxDist);
        if (result == null)
          continue;
        if (result.state != null)
          return result;
        newMaxDist = Math.min(newMaxDist, result.dist);
      }
      System.out.println("Dist: " + maxDist + " size: " + stateDistMap.size() + " unexplored: " + statesWithUnexploredNodes.size());
      if (newMaxDist >= OO)
        return null;
      maxDist = newMaxDist;
    }
  }

  private StateDist limitDfs(SearchState oldState, int curDist, int maxDist) {
    int estimatedCost = curDist + h.estimateCostToGoal(oldState);
    if (estimatedCost > maxDist) {
      if (estimatedCost >= OO)
        return null;
      Integer dist = statesWithUnexploredNodes.get(oldState.prevState);
      int prevDist = stateDistMap.get(oldState.prevState);
      if (dist == null || dist > prevDist) {
        if (dist != null)
          statesWithUnexploredNodes.remove(oldState.prevState);
        statesWithUnexploredNodes.put(oldState.prevState, prevDist);
      }
      return new StateDist(null, estimatedCost);

    }

    Integer dist = stateDistMap.get(oldState);
    if (dist != null && dist <= curDist)
      return null;
    stateDistMap.put(oldState, curDist);

    if (oldState.rowsToGo <= 0)
      return new StateDist(oldState, curDist);

    return expandChildren(oldState, curDist);
  }

  @Override
  protected StateDist exploreChild(SearchState newState, int newDist) {
    return limitDfs(newState, newDist, maxDist);
  }
}
