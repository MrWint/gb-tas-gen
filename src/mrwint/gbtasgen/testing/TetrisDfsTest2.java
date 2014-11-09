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


  private static void testTetrisDfs(short[] board, int[] forcedPieces, int rows) {
    Board.print(board);
    //    SearchAlgorithm search = new IDAStar(board, forcedPieces, rows, new NullHeuristic());
    AStar search = new AStar(board, forcedPieces, rows, LockPiece.NINE_DROP_DELAY, new CompositeHeuristic(
        new NoOverhangHeuristic(6),
        new ClearLinesHeuristic(2)
//      (SearchState s) -> s.board.board[5] == 0x238 ? 0 : SearchAlgorithm.OO
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

    short[] board = new short[6];
//    board[1] = 0x8;
//    board[2] = 0x8;
//    board[3] = 0x8;
//    board[4] = 0x8;

    testTetrisDfs(board, new int[] {}, 4);

    System.out.println("Time: " +(System.currentTimeMillis()-start));
  }
}
