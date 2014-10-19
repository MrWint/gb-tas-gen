package mrwint.gbtasgen.tools.tetris;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class TetrisDfs {
  private static final int NEXT_PIECE_DELAY = 3;

  private short[] initialBoard;
  private final int[] forcedPieces;
  private LockPiece.Log<Integer> logStrategy;

  public static class DfsState {
    public Board board;
    public int forcedPieceCounter;
    public int rowsToGo;

    public DfsState prevState;
    public int prevPieceIndex;

    public DfsState(Board board, int forcedPieceCounter, int rowsToGo, DfsState prevState, int prevPieceIndex) {
      this.board = board;
      this.forcedPieceCounter = forcedPieceCounter;
      this.rowsToGo = rowsToGo;

      this.prevState = prevState;
      this.prevPieceIndex = prevPieceIndex;
    }

    public static DfsState start(short[] initialBoard, int rowsToGo) {
      return new DfsState(new Board(initialBoard), 0, rowsToGo, null, -1);
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((board == null) ? 0 : board.hashCode());
      result = prime * result + forcedPieceCounter;
      result = prime * result + rowsToGo;
      return result;
    }
    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      DfsState other = (DfsState) obj;
      if (board == null) {
        if (other.board != null)
          return false;
      } else if (!board.equals(other.board))
        return false;
      if (forcedPieceCounter != other.forcedPieceCounter)
        return false;
      if (rowsToGo != other.rowsToGo)
        return false;
      return true;
    }
  }

  public class StateDist implements Comparable<StateDist> {
    public DfsState state;
    public int dist;

    public StateDist(DfsState state, int dist) {
      this.state = state;
      this.dist = dist;
    }

    @Override
    public int compareTo(StateDist o) {
      return dist - o.dist;
    }
  }

  public int appendLog(int curDist, int length) {
    return curDist + length + NEXT_PIECE_DELAY;
  }
  public int compareLogs(int curDist, int newDist) {
    return newDist - curDist;
  }

  public TetrisDfs(short[] initialBoard, int[] forcedPieces, LockPiece.Log<Integer> logStrategy) {
    this.initialBoard = initialBoard;
    this.forcedPieces = forcedPieces;
    this.logStrategy = logStrategy;
  }


  private HashMap<DfsState, Integer> stateDistMap;
  private PriorityQueue<StateDist> stateDistQueue;

  private void addState(DfsState state, int dist) {
    Integer curDist = stateDistMap.get(state);
    if (curDist == null || dist < curDist) {
      stateDistMap.put(state, dist);
      stateDistQueue.add(new StateDist(state, dist));
    }
  }

  private static int[] allPieces = {0, 1, 2, 3, 4, 5, 6};
  public StateDist dfs(int rowsToGo) {
    stateDistMap = new HashMap<>();
    stateDistQueue = new PriorityQueue<>();

    addState(DfsState.start(initialBoard, rowsToGo), 0);
    while (!stateDistQueue.isEmpty()) {
      StateDist stateDist = stateDistQueue.poll();
      DfsState oldState = stateDist.state;
      if (stateDist.dist > stateDistMap.get(oldState))
        continue;
      System.out.println("Dist: " + stateDist.dist + " size: " + stateDistQueue.size());

      if (oldState.rowsToGo <= 0)
        return stateDist;

      int[] pieceIndices = oldState.forcedPieceCounter >= 0 ? new int[]{forcedPieces[oldState.forcedPieceCounter]} : allPieces;
      int newForcedPieceCounter = oldState.forcedPieceCounter >= 0 && oldState.forcedPieceCounter+1 < forcedPieces.length ? oldState.forcedPieceCounter+1 : -1;
      for (int pieceIndex : pieceIndices) {
        LockPiece<Integer> lockPiece = new LockPiece<>(oldState.board.board, pieceIndex, logStrategy);
        HashMap<Board, Integer> locks = lockPiece.lockPiece();
        for (Entry<Board, Integer> e : locks.entrySet()) {
          Board board = e.getKey();
          int clearedLines = board.clearLines();
          int newRowsToGo = oldState.rowsToGo - clearedLines;
          if (newRowsToGo != oldState.rowsToGo && (newRowsToGo+3) / 4 == (oldState.rowsToGo+3) / 4)
            continue;
          addState(new DfsState(board, newForcedPieceCounter, newRowsToGo, oldState, pieceIndex), appendLog(stateDist.dist, e.getValue()));
        }
      }
    }


    return null;
  }
}
