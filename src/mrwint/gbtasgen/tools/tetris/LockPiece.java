package mrwint.gbtasgen.tools.tetris;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.TreeMap;

import mrwint.gbtasgen.move.Move;

public class LockPiece<T> {
  public static final int DROP_DELAY = 3;

  private final short[] board;
  private final int pieceIndex;
  private final Log<T> logStrategy;

  private static class LockPieceState {
    private int downCooldown;
    private int timedDropDelay;
    private boolean leftDown;
    private boolean rightDown;
    private boolean ADown;
    private boolean BDown;
    private Piece piece;

    public LockPieceState(int downCooldown, int timedDropDelay, boolean leftDown, boolean rightDown, boolean ADown, boolean BDown, Piece piece) {
      this.downCooldown = downCooldown;
      this.timedDropDelay = timedDropDelay;
      this.leftDown = leftDown;
      this.rightDown = rightDown;
      this.ADown = ADown;
      this.BDown = BDown;
      this.piece = piece;
    }
    public static LockPieceState start(int pieceIndex, short[] board) {
      return new LockPieceState(0, DROP_DELAY, false, false, false, false, Piece.fromIndex(pieceIndex, board));
    }
    public static LockPieceState withPiece(LockPieceState oldState, Piece piece) {
      return new LockPieceState(oldState.downCooldown, oldState.timedDropDelay, oldState.leftDown, oldState.rightDown, oldState.ADown, oldState.BDown, piece);
    }
    public boolean timedDrop() {
      if (timedDropDelay == 0) {
        if (piece.canMoveDown()) {
          piece.moveDown();
          timedDropDelay = DROP_DELAY;
        } else {
          return true;
        }
      } else {
        timedDropDelay--;
      }
      return false;
    }
    public LockPieceState tick() {
      if (downCooldown > 0)
        downCooldown--;
      leftDown = false;
      rightDown = false;
      return this;
    }
    public static LockPieceState tickAB(LockPieceState oldState) {
      return new LockPieceState(oldState.downCooldown, oldState.timedDropDelay, oldState.leftDown, oldState.rightDown, false, false, oldState.piece);
    }
    public boolean tickAndDrop() {
      tick();
      return timedDrop();
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + (ADown ? 1231 : 1237);
      result = prime * result + (BDown ? 1231 : 1237);
      result = prime * result + downCooldown;
      result = prime * result + (leftDown ? 1231 : 1237);
      result = prime * result + ((piece == null) ? 0 : piece.hashCode());
      result = prime * result + (rightDown ? 1231 : 1237);
      result = prime * result + timedDropDelay;
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
      LockPieceState other = (LockPieceState) obj;
      if (ADown != other.ADown)
        return false;
      if (BDown != other.BDown)
        return false;
      if (downCooldown != other.downCooldown)
        return false;
      if (leftDown != other.leftDown)
        return false;
      if (piece == null) {
        if (other.piece != null)
          return false;
      } else if (!piece.equals(other.piece))
        return false;
      if (rightDown != other.rightDown)
        return false;
      if (timedDropDelay != other.timedDropDelay)
        return false;
      return true;
    }

  }

  public static interface Log<T> {
    T appendLog(T curLog, int move);
    T emptyLog();
  }
  public static class FullLog implements Log<int[]> {
    @Override
    public int[] appendLog(int[] oldLog, int move) {
      int[] log = new int[oldLog.length + 1];
      System.arraycopy(oldLog, 0, log, 0, oldLog.length);
      log[oldLog.length] = move;
      return log;
    }

    @Override
    public int[] emptyLog() {
      return new int[0];
    }
  }
  public static class SizeLog implements Log<Integer> {
    @Override
    public Integer appendLog(Integer curLog, int move) {
      return curLog + 1;
    }
    @Override
    public Integer emptyLog() {
      return 0;
    }
  }
  public static class NoLog implements Log<Void> {
    @Override
    public Void appendLog(Void curLog, int move) {
      return null;
    }
    @Override
    public Void emptyLog() {
      return null;
    }
  }

  public LockPiece(short[] board, int pieceIndex, Log<T> logStrategy) {
    this.board = board;
    this.pieceIndex = pieceIndex;
    this.logStrategy = logStrategy;
  }


  private HashMap<LockPieceState, T> stateDistMap;
  private Queue<LockPieceState> stateDistQueue;
  private HashMap<Board, T> boardDistMap;

  private boolean containsState(LockPieceState newState) {
    if (newState.ADown) {
      newState.ADown = false;
      if (containsState(newState)) {
        newState.ADown = true;
        return true;
      }
      newState.ADown = true;
    }
    if (newState.BDown) {
      newState.BDown = false;
      if (containsState(newState)) {
        newState.BDown = true;
        return true;
      }
      newState.BDown = true;
    }
    if (newState.leftDown) {
      newState.leftDown = false;
      if (containsState(newState)) {
        newState.leftDown = true;
        return true;
      }
      newState.leftDown = true;
    }
    if (newState.rightDown) {
      newState.rightDown = false;
      if (containsState(newState)) {
        newState.rightDown = true;
        return true;
      }
      newState.rightDown = true;
    }
    return stateDistMap.containsKey(newState);
  }

  private void addState(LockPieceState oldState, LockPieceState newState, int move) {
    if (!containsState(newState)) {
      T newLog = logStrategy.appendLog(stateDistMap.get(oldState), move);
      stateDistMap.put(newState, newLog);
      stateDistQueue.add(newState);
    }
  }

  private void addBoard(LockPieceState oldState, short[] boardData, int move) {
    Board board = new Board(boardData);
    if (!boardDistMap.containsKey(board)) {
      T newLog = logStrategy.appendLog(stateDistMap.get(oldState), move);
      boardDistMap.put(board, newLog);
    }
  }

  public HashMap<Board, T> lockPiece() {
    stateDistMap = new HashMap<>();
    stateDistQueue = new LinkedList<>();
    boardDistMap = new HashMap<>();

    LockPieceState startState = LockPieceState.start(pieceIndex, board);
    stateDistMap.put(startState, logStrategy.emptyLog());
    stateDistQueue.add(startState);
    while (!stateDistQueue.isEmpty()) {
      LockPieceState oldState = stateDistQueue.poll();

      TreeMap<Integer, LockPieceState> rotationStates = new TreeMap<>();
      rotationStates.put(0, LockPieceState.tickAB(oldState));
      if (!oldState.ADown && oldState.piece.canRotateA()) {
        LockPieceState rotState = LockPieceState.withPiece(LockPieceState.tickAB(oldState), new Piece(oldState.piece).rotateA());
        rotState.ADown = true;
        rotationStates.put(Move.A, rotState);
      }
      if (!oldState.BDown && oldState.piece.canRotateB()) {
        LockPieceState rotState = LockPieceState.withPiece(LockPieceState.tickAB(oldState), new Piece(oldState.piece).rotateB());
        rotState.BDown = true;
        rotationStates.put(Move.B, rotState);
      }

      for (Entry<Integer, LockPieceState> entry : rotationStates.entrySet()) {
        LockPieceState state = entry.getValue();
        int move = entry.getKey();
        Piece piece = state.piece;

        if (!state.leftDown && piece.canMoveLeft()) { // press left
          LockPieceState newState = LockPieceState.withPiece(state, new Piece(piece).moveLeft());
          boolean hit = newState.tickAndDrop();
          newState.leftDown = true;
          if (hit) {
            addBoard(oldState, newState.piece.lock(), move | Move.LEFT);
          } else {
            addState(oldState, newState, move | Move.LEFT);
          }
        }
        if (!state.rightDown && piece.canMoveRight()) { // press right
          LockPieceState newState = LockPieceState.withPiece(state, new Piece(piece).moveRight());
          boolean hit = newState.tickAndDrop();
          newState.rightDown = true;
          if (hit) {
            addBoard(oldState, newState.piece.lock(), move | Move.RIGHT);
          } else {
            addState(oldState, newState, move | Move.RIGHT);
          }
        }
        { // press down
          if (state.downCooldown == 0) {
            if (piece.canMoveDown()) {
              LockPieceState newState = LockPieceState.withPiece(state, new Piece(piece).moveDown()).tick();
              newState.downCooldown = 2;
              addState(oldState, newState, move | Move.DOWN);
            } else {
              addBoard(oldState, piece.lock(), move | Move.DOWN);
            }
          } else {
            addState(oldState, LockPieceState.withPiece(state, new Piece(piece)).tick(), move | Move.DOWN);
          }
        }
        { // press nothing
          LockPieceState newState = LockPieceState.withPiece(state, new Piece(piece));
          boolean hit = newState.tickAndDrop();
          if (hit) {
            addBoard(oldState, piece.lock(), move);
          } else {
            addState(oldState, newState, move);
          }
        }
      }
    }


    return boardDistMap;
  }
}
