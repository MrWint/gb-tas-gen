package mrwint.gbtasgen.tools.tetris;

import java.util.Arrays;

public class Board {
  public static final int WIDTH = 10;
  public static final int HEIGHT = 18;

  public static void print(short[] board) {
    for (int y = 0; y < HEIGHT; y++) {
      for (int x = 0; x < WIDTH; x++) {
        System.out.print(((board[y] >> x) & 1) == 1 ? 'x' : '.');
      }
      System.out.println();
    }
  }

  public short[] board;
  public Board(short[] board) {
    this.board = board;
  }
  public void print() {
    print(board);
  }

  public boolean hasClearLines() {
    for (short line : board)
      if (line == 0x3ff)
        return true;
    return false;
  }
  public int clearLines() {
    int numClearedLines = 0;
    for (int curOldLine = HEIGHT-1 ; curOldLine >= 0; curOldLine--) {
      if (board[curOldLine] == 0x3ff)
        numClearedLines++;
      else
        board[curOldLine + numClearedLines] = board[curOldLine];
    }
    for (int i = numClearedLines-1; i >= 0; i--) {
      if (i > 0)
        board[i] = board[0]; // Simulate GB Tetris line clear bug.
      else
        board[0] = 0;
    }
    return numClearedLines;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + Arrays.hashCode(board);
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
    Board other = (Board) obj;
    if (!Arrays.equals(board, other.board))
      return false;
    return true;
  }
}
