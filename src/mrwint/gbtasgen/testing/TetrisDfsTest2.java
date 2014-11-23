package mrwint.gbtasgen.testing;

import mrwint.gbtasgen.tools.tetris.Board;
import mrwint.gbtasgen.tools.tetris.LockPiece;
import mrwint.gbtasgen.tools.tetris.Piece;
import mrwint.gbtasgen.tools.tetris.heuristic.ClearLinesHeuristic;
import mrwint.gbtasgen.tools.tetris.heuristic.CompositeHeuristic;
import mrwint.gbtasgen.tools.tetris.heuristic.Heuristic;
import mrwint.gbtasgen.tools.tetris.heuristic.NoOverhangHeuristic;
import mrwint.gbtasgen.tools.tetris.heuristic.NullHeuristic;
import mrwint.gbtasgen.tools.tetris.search.AStar;
import mrwint.gbtasgen.tools.tetris.search.IDAStar;
import mrwint.gbtasgen.tools.tetris.search.SearchAlgorithm;
import mrwint.gbtasgen.tools.tetris.search.SearchAlgorithm.SearchState;
import mrwint.gbtasgen.tools.tetris.search.SearchAlgorithm.StateDist;

public class TetrisDfsTest2 {


  private static void testTetrisDfs(short[] initialBoard, int[] forcedPieces, int rows, short[] expectedBoard) {
    Board.print(initialBoard);
    if (expectedBoard != null) {
      System.out.println("to:");
      Board.print(expectedBoard);
    }
    //    SearchAlgorithm search = new IDAStar(board, forcedPieces, rows, new NullHeuristic());
    AStar search = new AStar(initialBoard, forcedPieces, rows, LockPiece.NINE_HEART_DROP_DELAY, expectedBoard, new CompositeHeuristic(
        new NoOverhangHeuristic(6),
        new ClearLinesHeuristic(1),
//        (SearchState s) -> (s.board.board[0] & 0x10) == 0 ? 0 : SearchAlgorithm.OO,
        (SearchState s) -> (s.board.board[1] & 0x10) == 0 ? 0 : SearchAlgorithm.OO,
        (SearchState s) -> (s.board.board[2] & 0x10) == 0 ? 0 : SearchAlgorithm.OO,
        (SearchState s) -> (s.board.board[3] & 0x10) == 0 ? 0 : SearchAlgorithm.OO,
        (SearchState s) -> (s.board.board[4] & 0x10) == 0 ? 0 : SearchAlgorithm.OO,
        (SearchState s) -> (s.board.board[5] & 0x10) == 0 ? 0 : SearchAlgorithm.OO
//      (SearchState s) -> Integer.bitCount(s.board.board[0]) <= 1 ? 0 : SearchAlgorithm.OO
    ));
//    search.stateDistMap.put(new SearchState(new Board(new short[]{0,0,0,0,0}), -1, 0, null, -1), 0);
    StateDist stateDist = search.search();
    if (stateDist != null) {
      System.out.println("Dist: " + stateDist.dist);
      printState(stateDist.state);
    } else {
      System.out.println("no solution!");
    }
  }

  private static void printState(SearchState state) {
    if (state.prevState != null) {
      printState(state.prevState);
      System.out.println();
      System.out.println("Used piece: " + Piece.PIECE_NAMES[state.prevPieceIndex]);
    }
    state.board.print();
  }

  public static void main(String[] args) {

    long start = System.currentTimeMillis();

    short[] initialBoard = new short[6];
//    initialBoard[0] = 0x3c0;
//    initialBoard[1] = 0x3c0;
//    initialBoard[2] = 0x3c0;
//    initialBoard[3] = 0x3cf;
//    initialBoard[4] = 0x3cf;
    initialBoard[5] = 0x3ef;

//    short[] expectedBoard = null;
    short[] expectedBoard = new short[6];
//    expectedBoard[1] = 0x3cf;
//    expectedBoard[2] = 0x3cf;
//    expectedBoard[3] = 0x3cf;
//    expectedBoard[4] = 0x3cf;
    expectedBoard[5] = 0x3ef;

    testTetrisDfs(initialBoard, new int[] {}, 4, expectedBoard);

    System.out.println("Time: " +(System.currentTimeMillis()-start));
  }
}
