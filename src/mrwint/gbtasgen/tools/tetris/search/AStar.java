package mrwint.gbtasgen.tools.tetris.search;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.TreeMap;

import mrwint.gbtasgen.tools.tetris.heuristic.Heuristic;

public class AStar extends SearchAlgorithm {

  public final HashMap<SearchState, Integer> stateDistMap;
  public final HashMap<SearchState, SearchState> stateSet;
  private final PriorityQueue<StateDist> stateDistQueue;
  private final Heuristic h;
  public short[] expectedNextPieces = new short[] {0b111111111};

  public AStar(short[] initialBoard, int[] forcedPieces, int rowsToClear, int initialDropDelay, short[] expectedBoard, Heuristic h) {
    super(initialBoard, forcedPieces, rowsToClear, initialDropDelay, expectedBoard);
    stateDistMap = new HashMap<>();
    stateSet = new HashMap<>();
    stateDistQueue = new PriorityQueue<>();
    this.h = h;
  }

  private void addState(SearchState state, int dist, int edgeDist) {
    int estimatedDist = dist + h.estimateCostToGoal(state);
    if (estimatedDist >= OO)
      return;

    SearchState oldState = stateSet.get(state);
    if (oldState == null) {
      stateSet.put(state, state);
      oldState = state;
    }
    if (state.prevState != null)
      oldState.backLinks.add(new BackLink(state.prevState, edgeDist, state.prevPieceIndex));
    Integer curDist = stateDistMap.get(state);
    if (curDist == null || dist < curDist) {
      oldState.prevState = state.prevState;
      oldState.prevPieceIndex = state.prevPieceIndex;
      stateDistMap.put(oldState, dist);
      stateDistQueue.add(new StateDist(oldState, estimatedDist));
    }
  }

  @Override
  public StateDist search() {
    stateDistQueue.clear();

    StateDist result = null;

    addState(getInitialState(), 0, 0);
    int counter = 0;
    while (!stateDistQueue.isEmpty()) {
      StateDist stateDist = stateDistQueue.poll();
      SearchState oldState = stateDist.state;
      int oldDist = stateDistMap.get(oldState);
      if (stateDist.dist > oldDist + h.estimateCostToGoal(oldState))
        continue;
      if (counter++ % 100 == 0)
        System.out.println("Est: " + stateDist.dist + " Dist: " + oldDist + " size: " + stateDistMap.size() + " unexplored: " + stateDistQueue.size());

      if (oldState.rowsToGo <= 0) {
        if (result == null && isExpectedBoard(oldState.board))
          result = stateDist;
        if (rowsToClear > 0)
          continue;
      }
      expandChildren(oldState, oldDist);
    }
    calcDistToGoal();
    prune();
    printDistStats();
    return result;
  }

  private void calcDistToGoal() {
    System.out.println("Calculate distances to goal");
    stateDistQueue.clear();
    for (SearchState s : stateSet.keySet()) {
      if (s.rowsToGo <= 0 && isExpectedBoard(s.board)) {
        s.distToGoal = 0;
        for (short expectedNextPiece : expectedNextPieces)
          s.possibleNextPieces.add(expectedNextPiece);
        stateDistQueue.add(new StateDist(s, 0));
      }
    }
    while (!stateDistQueue.isEmpty()) {
      StateDist stateDist = stateDistQueue.poll();
      SearchState s = stateDist.state;
      int distToGoal = stateDist.dist;
      for (BackLink backLink : s.backLinks) {
        boolean redo = false;
        for (short nextPieces : s.possibleNextPieces)
          redo |= backLink.state.possibleNextPieces.add((short)(((nextPieces << 3) & 0x1ff) | backLink.piece));
        int newDistToGoal = distToGoal + backLink.dist;
        if (redo || backLink.state.distToGoal > newDistToGoal) {
          if (backLink.state.distToGoal > newDistToGoal)
            backLink.state.distToGoal = newDistToGoal;
          stateDistQueue.add(new StateDist(backLink.state, backLink.state.distToGoal));
        }
      }
    }
  }

  private void prune() {
    System.out.println("Before pruning, stateSet contains " + stateSet.size() + " entries.");
    stateDistQueue.clear();
    stateDistMap.clear();
    HashMap<SearchState, SearchState> prunedStateSet = new HashMap<>();
    for (SearchState s : stateSet.keySet()) {
      if (s.distToGoal < OO)
        prunedStateSet.put(s, s);
    }
    stateSet.clear();
    stateSet.putAll(prunedStateSet);
    System.out.println("After pruning, stateSet contains " + stateSet.size() + " entries.");
  }

  private void printDistStats() {
    TreeMap<Integer, Integer> distCounter = new TreeMap<>();
    for (SearchState s : stateSet.keySet()) {
      if (!distCounter.containsKey(s.distToGoal))
        distCounter.put(s.distToGoal, 0);
      distCounter.put(s.distToGoal, distCounter.get(s.distToGoal) + 1);
    }
    for (Entry<Integer, Integer> e : distCounter.entrySet()) {
      System.out.println(String.format("Dist %s: %s entries.", e.getKey(), e.getValue()));
    }
  }

  @Override
  protected StateDist exploreChild(SearchState newState, int newDist, int edgeDist) {
    addState(newState, newDist, edgeDist);
    return null;
  }
}
