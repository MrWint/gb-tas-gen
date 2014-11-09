package mrwint.gbtasgen.tools.tetris.search;

import java.util.HashMap;
import java.util.Map.Entry;

import mrwint.gbtasgen.tools.tetris.Board;
import mrwint.gbtasgen.tools.tetris.LockPiece;
import mrwint.gbtasgen.tools.tetris.LockPiece.SizeLog;

public abstract class SearchAlgorithm {
  protected static final int NEXT_PIECE_DELAY = 3;
  protected static int[] ALL_PIECES = {0, 1, 2, 3, 4, 5, 6};
  public static int OO = 1000000;

  private short[] initialBoard;
  private final int[] forcedPieces;
  private final int rowsToClear;
  private LockPiece.Log<Integer> logStrategy;
  private final int initialDropDelay;

  public static class SearchState {
    public Board board;
    public int forcedPieceCounter;
    public int rowsToGo;

    public SearchState prevState;
    public int prevPieceIndex;

    public SearchState(Board board, int forcedPieceCounter, int rowsToGo, SearchState prevState, int prevPieceIndex) {
      this.board = board;
      this.forcedPieceCounter = forcedPieceCounter;
      this.rowsToGo = rowsToGo;

      this.prevState = prevState;
      this.prevPieceIndex = prevPieceIndex;
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
      SearchState other = (SearchState) obj;
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
    public SearchState state;
    public int dist;

    public StateDist(SearchState state, int dist) {
      this.state = state;
      this.dist = dist;
    }

    @Override
    public int compareTo(StateDist o) {
      return dist - o.dist;
    }
  }

  private int appendDist(int curDist, int length) {
    return curDist + length + NEXT_PIECE_DELAY;
  }

  public SearchAlgorithm(short[] initialBoard, int[] forcedPieces, int rowsToClear, int initialDropDelay) {
    this.initialBoard = initialBoard;
    this.forcedPieces = forcedPieces;
    this.rowsToClear = rowsToClear;
    this.logStrategy = new SizeLog();
    this.initialDropDelay = initialDropDelay;
  }

  protected SearchState getInitialState() {
    return new SearchState(new Board(initialBoard), forcedPieces.length == 0 ? -1 : 0, rowsToClear, null, -1);
  }
  protected int getRowsToClear() {
    return rowsToClear;
  }

  public abstract StateDist search();

  protected abstract StateDist exploreChild(SearchState newState, int newDist);

  protected StateDist expandChildren(SearchState oldState, int oldDist) {
    int[] pieceIndices = oldState.forcedPieceCounter >= 0 ? new int[]{forcedPieces[oldState.forcedPieceCounter]} : ALL_PIECES;
    int newForcedPieceCounter = oldState.forcedPieceCounter >= 0 && oldState.forcedPieceCounter+1 < forcedPieces.length ? oldState.forcedPieceCounter+1 : -1;
    StateDist bestStateDist = null;
    for (int pieceIndex : pieceIndices) {
      LockPiece<Integer> lockPiece = new LockPiece<>(oldState.board.board, pieceIndex, logStrategy, initialDropDelay);
      HashMap<Board, Integer> locks = lockPiece.lockPiece();
      for (Entry<Board, Integer> e : locks.entrySet()) {
        Board board = e.getKey();
        int clearedLines = board.clearLines();
        int newRowsToGo = oldState.rowsToGo - clearedLines;
        if (newRowsToGo != oldState.rowsToGo && (newRowsToGo+3) / 4 == (oldState.rowsToGo+3) / 4)
          continue;
        StateDist sd = exploreChild(new SearchState(board, newForcedPieceCounter, newRowsToGo, oldState, pieceIndex), appendDist(oldDist, e.getValue()));
        if (sd != null) {
          if (sd.state != null)
            return sd;
          if (bestStateDist == null || bestStateDist.dist > sd.dist)
            bestStateDist = sd;
        }
      }
    }
    return bestStateDist;
  }
}
