package mrwint.gbtasgen.tools.tetris;

import java.util.HashSet;
import java.util.Set;

import mrwint.gbtasgen.Gb;

public class TetrisUtil {
  public static void printNextPieces(short pieces) {
    for(int i=0;i<3;i++) {
      System.out.print(Piece.PIECE_NAMES[pieces & 7]);
      pieces >>= 3;
    }
    System.out.println();
  }

  public static int[] fromNextPieces(short pieces) {
    int[] ret = new int[3];
    for(int i=0;i<3;i++) {
      ret[i] = pieces & 7;
      pieces >>= 3;
    }
    return ret;
  }

  public static short[] getBoard(int numRows) {
    short[] board = new short[numRows];

    int startAddress = 0xc802;
    for (int y = 0; y < numRows; y++)
      for (int x = 0; x < Board.WIDTH; x++)
        if (Gb.readMemory(startAddress + y*0x20 + x) != 0x2f)
          board[y] |= 1 << x;

    return board;
  }

  public static short reverseBoardBits(int mask) {
    short result = 0;
    for (int i = 0; i < 10; i++) {
      int bit = mask & 1;
      mask >>= 1;
      result = (short)(bit + (result << 1));
    }
    return result;
  }

  public static boolean isValidNextPieces(short pieces, Set<Short> validPieces) {
    return validPieces.contains(pieces)
        || validPieces.contains((short) (pieces | 0b111000000))
        || validPieces.contains((short) (pieces | 0b111111000))
        || validPieces.contains((short) (pieces | 0b111111111));
  }
}
