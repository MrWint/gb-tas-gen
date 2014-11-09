package mrwint.gbtasgen.testing;

import java.util.Map;

import mrwint.gbtasgen.tools.tetris.Board;
import mrwint.gbtasgen.tools.tetris.LockPiece;
import mrwint.gbtasgen.tools.tetris.LockPiece.Log;

public class TetrisLockPieceTest2 {

  private static <T> void testLockPiece(short[] board, int curPiece, Log<T> logStrategy) {
    Board.print(board);
    LockPiece<T> pp = new LockPiece<>(board, curPiece, logStrategy, LockPiece.NINE_DROP_DELAY);
    for(Map.Entry<Board, T> e : pp.lockPiece().entrySet()) {
      System.out.println("dist: " + logStrategy.print(e.getValue()));
      e.getKey().print();
      System.out.println();
    }
  }

  public static void main(String[] args) {
    short[] board = new short[4];
    testLockPiece(board, 2, new LockPiece.SizeLog());
  }
}
