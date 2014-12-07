package mrwint.gbtasgen.tools.tetris;

public class TetrisUtil {
  public static void printNextPieces(short pieces) {
    for(int i=0;i<3;i++) {
      System.out.print(Piece.PIECE_NAMES[pieces & 7]);
      pieces >>= 3;
    }
    System.out.println();
  }
}
