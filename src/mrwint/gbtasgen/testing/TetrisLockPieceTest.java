package mrwint.gbtasgen.testing;

import java.util.Map;

import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.move.RunUntil;
import mrwint.gbtasgen.move.Wait;
import mrwint.gbtasgen.rom.RomInfo;
import mrwint.gbtasgen.rom.tetris.TetrisRomInfo;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.tools.tetris.Board;
import mrwint.gbtasgen.tools.tetris.LockPiece;
import mrwint.gbtasgen.tools.tetris.LockPiece.Log;
import mrwint.gbtasgen.tools.tetris.Piece;
import mrwint.gbtasgen.util.Runner;

public class TetrisLockPieceTest extends SeqSegment {

  @Override
  protected void execute() {
    seqMove(new RunUntil(() -> Gb.readMemory(RomInfo.tetris.hGameState) == 0x35 ? 1 : 0));
//    seq(new Wait(1));
    seqButton(Move.START);
    seqMove(new RunUntil(() -> Gb.readMemory(RomInfo.tetris.hGameState) == 0x7 ? 1 : 0));
//    seq(new Wait(1));
    seqButton(Move.START /*| Move.DOWN*/);
    seqMove(new RunUntil(() -> Gb.readMemory(RomInfo.tetris.hGameState) == 0xe ? 1 : 0));
    seqButton(Move.RIGHT);
    seqMove(new Wait(1));
    seqButton(Move.START);
    seqMove(new RunUntil(() -> Gb.readMemory(RomInfo.tetris.hGameState) == 0x13 ? 1 : 0));
    seqButton(Move.DOWN);
    seqButton(Move.LEFT);
    seqButton(Move.DOWN);
//    seq(new Wait(1));
    seqButton(Move.A);
    seqButton(Move.RIGHT);
    seqButton(Move.DOWN);
    seqButton(Move.RIGHT);
    seqMove(new Wait(2));
//    seq(new PressButton(Move.A), 0);
    seqMove(new PressButton(Move.START), 0);
    seqMove(new RunUntil(() -> {return (Gb.readMemory(RomInfo.tetris.hGameState) == 0x0 ? 1 : 0);}));
    seqMetric(() -> {
      short[] board = new short[Board.HEIGHT];

      int startAddress = 0xc802;
      for (int y = 0; y < Board.HEIGHT; y++)
        for (int x = 0; x < Board.WIDTH; x++)
          if (Gb.readMemory(startAddress + y*0x20 + x) != 0x2f)
            board[y] |= 1 << x;

      int curPiece = (Gb.readMemory(RomInfo.tetris.wCurPiece) >> 2);
      int previewPiece = (Gb.readMemory(RomInfo.tetris.wPreviewPiece) >> 2);
      int nextPreviewPiece = (Gb.readMemory(RomInfo.tetris.hNextPreviewPiece) >> 2);
      testLockPiece(board, curPiece, new LockPiece.SizeLog());
      System.out.println("pieces: " + Piece.PIECE_NAMES[curPiece] + " " + Piece.PIECE_NAMES[previewPiece] + " " + Piece.PIECE_NAMES[nextPreviewPiece]);
      return 1;
    });
    seqMove(new Wait(10));
    seqMove(new Move() {
      @Override public int getInitialKey() { return 0; }
      @Override
      public boolean doMove() {
        try {
          Thread.sleep(10000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        return true;
      }
    });
  }

  private static <T> void testLockPiece(short[] board, int curPiece, Log<T> logStrategy) {
    Board.print(board);
    LockPiece<T> pp = new LockPiece<>(board, curPiece, logStrategy, LockPiece.NINE_HEART_DROP_DELAY);
    for(Map.Entry<Board, T> e : pp.lockPiece().entrySet()) {
      System.out.println("dist: " + e.getValue());
      e.getKey().print();
      System.out.println();
    }
  }

  public static void main(String[] args) {
    RomInfo.setRom(new TetrisRomInfo());

    Runner.run(new TetrisLockPieceTest());
  }
}
