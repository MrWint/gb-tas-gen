package mrwint.gbtasgen.tools.tetris;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.state.ListStateBuffer;
import mrwint.gbtasgen.state.StateBuffer;
import mrwint.gbtasgen.tools.tetris.LockPiece.LockPieceState;

public class SBLockPiece {
  public StateBuffer appendLog(StateBuffer states, int move) {
    return new MoveSegment(new PressButton(move), 0).execute(states);
  }

  public ListStateBuffer appendLog(ListStateBuffer states, int move) {
    return states.appendMove(move);
  }

  public ListStateBuffer emptyLog() {
    ListStateBuffer buffer = new ListStateBuffer();
    buffer.addState(null);
    return buffer;
  }

  private final short[] board;
  private final int pieceIndex;
  private final int levelDropDelay;
  private final int initialRemainingDropDelay;

  public SBLockPiece(short[] board, int pieceIndex, int levelDropDelay, StateBuffer initialStates) {
    this(board, pieceIndex, levelDropDelay, levelDropDelay, initialStates);
  }
  public SBLockPiece(short[] board, int pieceIndex, int levelDropDelay, int initialRemainingDropDelay, StateBuffer initialStates) {
    this.board = board;
    this.pieceIndex = pieceIndex;
    this.levelDropDelay = levelDropDelay;
    this.initialRemainingDropDelay = initialRemainingDropDelay;
  }

  private HashMap<LockPieceState, ListStateBuffer> stateDistMap;
  private Queue<LockPieceState> stateDistQueue;
  private HashMap<Board, ListStateBuffer> boardDistMap;

  private void addState(LockPieceState oldState, LockPieceState newState, int move) {
    ListStateBuffer newLog = appendLog(stateDistMap.get(oldState), move);
    if (!stateDistMap.containsKey(newState)) {
      stateDistMap.put(newState, newLog);
      stateDistQueue.add(newState);
    } else {
      if (stateDistMap.get(newState) != null)
        stateDistMap.get(newState).addAll(newLog);
      else
        System.out.println("ERROR!!!");
    }
  }

  private void addBoard(LockPieceState oldState, short[] boardData, int move) {
    if (boardData != null) {
      Board board = new Board(boardData);
      ListStateBuffer newLog = appendLog(stateDistMap.get(oldState), move);
      if (!boardDistMap.containsKey(board)) {
        boardDistMap.put(board, newLog);
      } else
        boardDistMap.get(board).addAll(newLog);
    }
  }

  public HashMap<Board, ListStateBuffer> lockPiece() {
    stateDistMap = new HashMap<>();
    stateDistQueue = new PriorityQueue<>();
    boardDistMap = new HashMap<>();

    LockPieceState startState = LockPieceState.start(pieceIndex, board, levelDropDelay, initialRemainingDropDelay);
    stateDistMap.put(startState, emptyLog());
    stateDistQueue.add(startState);
    int counter = 0;
    while (!stateDistQueue.isEmpty()) {
      if(++counter % 1000 == 0) {
        System.out.println("size: " + stateDistMap.size() + " unexplored: " + stateDistQueue.size());
      }
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

        { // press nothing
          LockPieceState newState = LockPieceState.withPiece(state, new Piece(piece));
          boolean hit = newState.tickAndDrop();
          if (hit) {
            addBoard(oldState, piece.lock(), move);
          } else {
            addState(oldState, newState, move);
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
      }

      // clear StateBuffer
      stateDistMap.put(oldState, null);
//      stateDistMap.remove(oldState);
    }
    return boardDistMap;
  }
}
