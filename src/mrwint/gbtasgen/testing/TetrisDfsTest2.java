package mrwint.gbtasgen.testing;

import static mrwint.gbtasgen.tools.tetris.heuristic.FieldEmptyHeuristic.reverseBoardBits;
import mrwint.gbtasgen.tools.tetris.Board;
import mrwint.gbtasgen.tools.tetris.LockPiece;
import mrwint.gbtasgen.tools.tetris.Piece;
import mrwint.gbtasgen.tools.tetris.TetrisUtil;
import mrwint.gbtasgen.tools.tetris.heuristic.CompositeHeuristic;
import mrwint.gbtasgen.tools.tetris.heuristic.FieldEmptyHeuristic;
import mrwint.gbtasgen.tools.tetris.heuristic.NoOverhangHeuristic;
import mrwint.gbtasgen.tools.tetris.search.AStar;
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
    AStar search = new AStar(initialBoard, forcedPieces, rows, LockPiece.NINE_DROP_DELAY, expectedBoard, new CompositeHeuristic(
        new NoOverhangHeuristic(6),
//        new ClearLinesHeuristic(1),
        new FieldEmptyHeuristic(0, 0b0000100000),
        new FieldEmptyHeuristic(1, 0b0000100000),
        new FieldEmptyHeuristic(2, 0b0000100000),
        new FieldEmptyHeuristic(3, 0b0000100000),
        new FieldEmptyHeuristic(4, 0b0000100000),
        new FieldEmptyHeuristic(5, 0b0000100000),
        new FieldEmptyHeuristic(6, 0b1110001101),
        new FieldEmptyHeuristic(7, 0b1111101101)
    ));
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

    short[] initialBoard = new short[8];
    initialBoard[0] = reverseBoardBits(0b0000000000);
    initialBoard[1] = reverseBoardBits(0b0000000000);
    initialBoard[2] = reverseBoardBits(0b0000000000);
    initialBoard[3] = reverseBoardBits(0b0000000000);
    initialBoard[4] = reverseBoardBits(0b0000000000);
    initialBoard[5] = reverseBoardBits(0b0000000000);
    initialBoard[6] = reverseBoardBits(0b0000000000);
    initialBoard[7] = reverseBoardBits(0b0000000000);

//    short[] expectedBoard = null;
    short[] expectedBoard = new short[8];
    expectedBoard[0] = reverseBoardBits(0b0000000000);
    expectedBoard[1] = reverseBoardBits(0b1111011111);
    expectedBoard[2] = reverseBoardBits(0b1111011111);
    expectedBoard[3] = reverseBoardBits(0b1111011111);
    expectedBoard[4] = reverseBoardBits(0b1111011111);
    expectedBoard[5] = reverseBoardBits(0b1111011111);
    expectedBoard[6] = reverseBoardBits(0b0001110010);
    expectedBoard[7] = reverseBoardBits(0b0000010010);

    testTetrisDfs(initialBoard, new int[] {1, 1, 5}, 4, expectedBoard);

    System.out.println("Time: " +(System.currentTimeMillis()-start));
  }
}
