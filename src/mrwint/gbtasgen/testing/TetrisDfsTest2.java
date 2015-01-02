package mrwint.gbtasgen.testing;

import static mrwint.gbtasgen.tools.tetris.TetrisUtil.reverseBoardBits;
import mrwint.gbtasgen.tools.tetris.Board;
import mrwint.gbtasgen.tools.tetris.LockPiece;
import mrwint.gbtasgen.tools.tetris.Piece;
import mrwint.gbtasgen.tools.tetris.TetrisUtil;
import mrwint.gbtasgen.tools.tetris.heuristic.CompositeHeuristic;
import mrwint.gbtasgen.tools.tetris.heuristic.FieldEmptyHeuristic;
import mrwint.gbtasgen.tools.tetris.heuristic.Heuristic;
import mrwint.gbtasgen.tools.tetris.heuristic.NoOverhangHeuristic;
import mrwint.gbtasgen.tools.tetris.search.AStar;
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
    AStar search = new AStar(initialBoard, forcedPieces, rows, LockPiece.NINE_DROP_DELAY, expectedBoard, new CompositeHeuristic(new Heuristic[] {
        new NoOverhangHeuristic(6),
        new FieldEmptyHeuristic(0, 0b0000100000),
        new FieldEmptyHeuristic(1, 0b0000100000),
        new FieldEmptyHeuristic(2, 0b0000100000),
        new FieldEmptyHeuristic(3, 0b0000100000),
        new FieldEmptyHeuristic(4, 0b0000100000),
        new FieldEmptyHeuristic(5, 0b0000100000),
//        new FieldEmptyHeuristic(6, 0b1110000000),
//        new FieldEmptyHeuristic(7, 0b1111100000),
//        new  Heuristic() {
//          @Override
//          public int estimateCostToGoal(SearchState state) {
//            int filled = Integer.bitCount(state.board.board[6]) + Integer.bitCount(state.board.board[7]);
//            if (filled > 6
//                || (filled > 0 && (state.board.board[6] & 0x38) != 0x38))
//              return SearchAlgorithm.OO;
//
//            return 0;
//          }
//        },
    }));
//    search.stateDistMap.put(new SearchState(new Board(new short[]{0,0,0,0,0}), -1, 0, null, -1), 0);
    StateDist stateDist = search.search();
    if (stateDist != null) {
      System.out.println("Dist: " + stateDist.dist);
      printState(stateDist.state);
      SearchState initialState = search.stateSet.get(search.getInitialState());
      System.out.println("Initial state dist: " + initialState.distToGoal);
      System.out.println("Initial state possible pieces:");
      for (short pieces : initialState.possibleNextPieces)
        TetrisUtil.printNextPieces(pieces);
      System.out.println(initialState.possibleNextPieces);
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
    initialBoard[0] = reverseBoardBits(0b0000000000);
    initialBoard[1] = reverseBoardBits(0b1111011111);
    initialBoard[2] = reverseBoardBits(0b1111011111);
    initialBoard[3] = reverseBoardBits(0b1111011111);
    initialBoard[4] = reverseBoardBits(0b1111011111);
    initialBoard[5] = reverseBoardBits(0b1111011111);
//    initialBoard[6] = reverseBoardBits(0b0000000000);
//    initialBoard[7] = reverseBoardBits(0b0000000000);

//    short[] expectedBoard = null;
    short[] expectedBoard = new short[6];
    expectedBoard[0] = reverseBoardBits(0b0000000000);
    expectedBoard[1] = reverseBoardBits(0b1111001111);
    expectedBoard[2] = reverseBoardBits(0b1111001111);
    expectedBoard[3] = reverseBoardBits(0b1111001111);
    expectedBoard[4] = reverseBoardBits(0b1111001111);
    expectedBoard[5] = reverseBoardBits(0b1111011111);
//    expectedBoard[6] = reverseBoardBits(0b0001110010);
//    expectedBoard[7] = reverseBoardBits(0b0000010010);
//    expectedBoard[6] = -1;
//    expectedBoard[7] = -1;

    testTetrisDfs(initialBoard, new int[] {}, 8, expectedBoard);

    System.out.println("Time: " +(System.currentTimeMillis()-start));
  }
}
