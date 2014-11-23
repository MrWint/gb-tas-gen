package mrwint.gbtasgen.testing;

import java.util.Map;

import mrwint.gbtasgen.tools.tetris.Board;
import mrwint.gbtasgen.tools.tetris.LockPiece;
import mrwint.gbtasgen.tools.tetris.LockPiece.Log;

public class TetrisLockPieceTest2 {

  private static <T> void testLockPiece(short[] board, int curPiece, Log<T> logStrategy) {
    Board.print(board);
    LockPiece<T> pp = new LockPiece<>(board, curPiece, logStrategy, 3);
    for(Map.Entry<Board, T> e : pp.lockPiece().entrySet()) {
      System.out.println("dist: " + logStrategy.print(e.getValue()));
      e.getKey().print();
      System.out.println();
    }
  }

  public static void main(String[] args) {
    short[] board = new short[4];
    board[0] = 0x3ef;
    board[1] = 0x3ef;
    board[2] = 0x3ef;
    board[3] = 0x3ef;
    testLockPiece(board, 2, new LockPiece.FullLog());
  }
}
