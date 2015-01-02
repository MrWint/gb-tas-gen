package mrwint.gbtasgen.segment.tetris;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.PriorityQueue;

import mrwint.gbtasgen.state.ListStateBuffer;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.state.StateBuffer;
import mrwint.gbtasgen.state.tetris.TetrisStateBuffer;
import mrwint.gbtasgen.tools.tetris.Board;
import mrwint.gbtasgen.tools.tetris.LockPiece;
import mrwint.gbtasgen.tools.tetris.Piece;
import mrwint.gbtasgen.tools.tetris.SBLockPiece;
import mrwint.gbtasgen.tools.tetris.TetrisUtil;
import mrwint.gbtasgen.tools.tetris.heuristic.Heuristic;
import mrwint.gbtasgen.tools.tetris.search.AStar;
import mrwint.gbtasgen.tools.tetris.search.SearchAlgorithm.SearchState;
import mrwint.gbtasgen.tools.tetris.search.SearchAlgorithm.StateDist;
import mrwint.gbtasgen.util.Util;

public class TetrisDfsSegment implements TetrisSegment {

  // Dry search
  private short[] initialBoard;
  private int[] forcedPieces;
  private final int rowsToClear;
  private final int initialDropDelay;
  private final short[] expectedBoard;
  private final Heuristic heuristic;
  private final short[] possibleNextPieces;
  private AStar search;

  // State search
  public final HashMap<SearchState, Integer> stateDistMap;
  public final HashMap<SearchState, TetrisStateBuffer> stateBufferMap;
  private final PriorityQueue<StateDist> stateDistQueue;
  private final boolean withVeryFirstPiece;
  private boolean veryFirstPiece;

  private void determineInitialState(TetrisStateBuffer in) {
    Entry<Short, StateBuffer> firstEntry = in.getMap().entrySet().iterator().next();
    State firstState = firstEntry.getValue().getStates().iterator().next();

    // Determine initial Board state
    firstState.restore();
    Util.runToNextInputFrame();
    State.step();
    this.initialBoard = TetrisUtil.getBoard(expectedBoard.length);
    System.out.println("initial board:");
    Board.print(this.initialBoard);
    System.out.println("expected board:");
    Board.print(this.expectedBoard);

    // Determine forced pieces
    int numForcedPieces = 3;
    int[] forcedPieces = TetrisUtil.fromNextPieces(firstEntry.getKey());
    for (int i = 0; i < numForcedPieces; i++)
      if (forcedPieces[i] == 7)
        numForcedPieces = Math.min(numForcedPieces, i);
    for (short pieces : in.getMap().keySet()) {
      int[] cur = TetrisUtil.fromNextPieces(pieces);
      for (int i = 0; i < numForcedPieces; i++)
        if (cur[i] != forcedPieces[i])
          numForcedPieces = Math.min(numForcedPieces, i);
    }
    this.forcedPieces = new int[numForcedPieces];
    for (int i = 0; i < numForcedPieces; i++)
      this.forcedPieces[i] = forcedPieces[i];
    System.out.println("forced pieces: " + Arrays.toString(this.forcedPieces));
  }

  private void drySearch() {
    search = new AStar(initialBoard, forcedPieces, rowsToClear, initialDropDelay, expectedBoard, heuristic);
    if (possibleNextPieces != null)
      search.expectedNextPieces = possibleNextPieces;
    System.out.println("executing dry search...");
    search.search();
  }

  public TetrisDfsSegment(int rowsToClear, int initialDropDelay, short[] expectedBoard, Heuristic heuristic, short[] possibleNextPieces, boolean withVeryFirstPiece) {
    this.rowsToClear = rowsToClear;
    this.initialDropDelay = initialDropDelay;
    this.expectedBoard = expectedBoard;
    this.heuristic = heuristic;
    this.possibleNextPieces = possibleNextPieces;
    this.withVeryFirstPiece = withVeryFirstPiece;

    stateDistMap = new HashMap<>();
    stateBufferMap = new HashMap<>();
    stateDistQueue = new PriorityQueue<>();
  }

  @Override
  public TetrisStateBuffer execute(TetrisStateBuffer in) {
    determineInitialState(in);
    drySearch();
    return search(in);
  }

  private TetrisStateBuffer search(TetrisStateBuffer in) {
    stateDistQueue.clear();
    veryFirstPiece = withVeryFirstPiece;

    addState(search.stateSet.get(search.getInitialState()), in);
    int counter = 0;
    while (!stateDistQueue.isEmpty()) {
      StateDist stateDist = stateDistQueue.poll();
      SearchState oldState = stateDist.state;
      int oldDist = stateDistMap.get(oldState);
      if (stateDist.dist > oldDist + oldState.distToGoal)
        continue;
      if (counter++ % 1 == 0) {
        System.out.println("Board:");
        oldState.board.print();
        System.out.println("Est: " + stateDist.dist + " Dist: " + oldDist + " size: " + stateDistMap.size() + " unexplored: " + stateDistQueue.size());
      }

      if (oldState.rowsToGo <= 0 && search.isExpectedBoard(oldState.board))
        return stateBufferMap.get(oldState);

      expandChildren(oldState, oldDist);
      veryFirstPiece = false;
    }
    return null;
  }

  protected void expandChildren(SearchState oldState, int oldDist) {
    int newForcedPieceCounter = oldState.forcedPieceCounter >= 0 && oldState.forcedPieceCounter+1 < forcedPieces.length ? oldState.forcedPieceCounter+1 : -1;

    HashMap<Integer, HashMap<Board, ListStateBuffer>> cache = new HashMap<>();

    TetrisStateBuffer buf = stateBufferMap.get(oldState);
    for (Entry<Short, StateBuffer> stateEntry : buf.getMap().entrySet()) {
      int pieceIndex = stateEntry.getKey() & 0b111;
      if (!cache.containsKey(pieceIndex)) {
        System.out.println("Expand with piece " + Piece.PIECE_NAMES[pieceIndex]);
        SBLockPiece lockPiece = new SBLockPiece(oldState.board.board, pieceIndex, initialDropDelay, veryFirstPiece ? LockPiece.ZERO_DROP_DELAY : initialDropDelay, stateEntry.getValue());
        cache.put(pieceIndex, lockPiece.lockPiece());
      }
      HashMap<Board, ListStateBuffer> locks = cache.get(pieceIndex);
      for (Entry<Board, ListStateBuffer> e : locks.entrySet()) {
        Board board = e.getKey();
        int clearedLines = board.clearLines();
        int newRowsToGo = oldState.rowsToGo - clearedLines;
        if (newRowsToGo != oldState.rowsToGo && (newRowsToGo+3) / 4 == (oldState.rowsToGo+3) / 4)
          continue;
        SearchState newState = new SearchState(board, newForcedPieceCounter, newRowsToGo, oldState, pieceIndex);
        addState(newState, e.getValue(), stateEntry.getValue(), clearedLines > 0);
      }
    }
  }

  private void addState(SearchState state, ListStateBuffer buf, StateBuffer oldStates, boolean linesCleared) {
    state = search.stateSet.get(state);
    if (state != null) {
      addState(state, buf.toTetrisStateBuffer(oldStates, linesCleared));
    }
  }

  private void addState(SearchState state, TetrisStateBuffer buf) {
    int dist = Integer.MAX_VALUE;
    TetrisStateBuffer tmpBuffer = new TetrisStateBuffer();
    for (Entry<Short, StateBuffer> e : buf.getMap().entrySet()) {
      if (TetrisUtil.isValidNextPieces(e.getKey(), state.possibleNextPieces)) {
        tmpBuffer.addAll(e.getValue(), e.getKey());
        dist = Math.min(dist, e.getValue().getMinStepCount());
      }
    }

    if (!tmpBuffer.isEmpty()) {
      if (!stateBufferMap.containsKey(state))
        stateBufferMap.put(state, tmpBuffer);
      else
        stateBufferMap.get(state).addAll(tmpBuffer);

      int estimatedDist = dist + state.distToGoal;
      Integer curDist = stateDistMap.get(state);
      if (curDist == null || dist < curDist) {
        stateDistMap.put(state, dist);
        stateDistQueue.add(new StateDist(state, estimatedDist));
      }
    }
  }
}
