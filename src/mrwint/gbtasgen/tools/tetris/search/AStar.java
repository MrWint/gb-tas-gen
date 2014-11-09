package mrwint.gbtasgen.tools.tetris.search;

import java.util.HashMap;
import java.util.PriorityQueue;

import mrwint.gbtasgen.tools.tetris.heuristic.Heuristic;

public class AStar extends SearchAlgorithm {

  public final HashMap<SearchState, Integer> stateDistMap;
  private final PriorityQueue<StateDist> stateDistQueue;
  private final Heuristic h;

  public AStar(short[] initialBoard, int[] forcedPieces, int rowsToClear, int initialDropDelay, Heuristic h) {
    super(initialBoard, forcedPieces, rowsToClear, initialDropDelay);
    stateDistMap = new HashMap<>();
    stateDistQueue = new PriorityQueue<>();
    this.h = h;
  }

  private void addState(SearchState state, int dist) {
    Integer curDist = stateDistMap.get(state);
    if (curDist == null || dist < curDist) {
      int estimatedDist = dist + h.estimateCostToGoal(state);
      if (estimatedDist >= OO)
        return;
      stateDistMap.put(state, dist);
      stateDistQueue.add(new StateDist(state, estimatedDist));
    }
  }

  @Override
  public StateDist search() {
//    stateDistMap.clear();
    stateDistQueue.clear();

    addState(getInitialState(), 0);
    int counter = 0;
    while (!stateDistQueue.isEmpty()) {
      StateDist stateDist = stateDistQueue.poll();
      SearchState oldState = stateDist.state;
      int oldDist = stateDistMap.get(oldState);
      if (stateDist.dist > oldDist + h.estimateCostToGoal(oldState))
        continue;
      if (counter++ % 100 == 0)
        System.out.println("Est: " + stateDist.dist + " Dist: " + oldDist + " size: " + stateDistMap.size() + " unexplored: " + stateDistQueue.size());

      if (oldState.rowsToGo <= 0)
        return stateDist;

      expandChildren(oldState, oldDist);
    }
    return null;
  }

  @Override
  protected StateDist exploreChild(SearchState newState, int newDist) {
    addState(newState, newDist);
    return null;
  }
}
