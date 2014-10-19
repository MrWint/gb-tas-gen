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
import mrwint.gbtasgen.util.Runner;

public class TetrisLockPiece extends SeqSegment {

  @Override
  protected void execute() {
    seq(new RunUntil(() -> {return (Gb.readMemory(RomInfo.tetris.hGameState) == 0x35 ? 1 : 0);}));
    seq(Move.START);
    seq(new RunUntil(() -> {return (Gb.readMemory(RomInfo.tetris.hGameState) == 0x7 ? 1 : 0);}));
    seq(Move.START | Move.DOWN);
    seq(new RunUntil(() -> {return (Gb.readMemory(RomInfo.tetris.hGameState) == 0xe ? 1 : 0);}));
    seq(Move.RIGHT);
    seq(Move.START);
    seq(new RunUntil(() -> {return (Gb.readMemory(RomInfo.tetris.hGameState) == 0x13 ? 1 : 0);}));
    seq(Move.DOWN);
    seq(Move.LEFT);
    seq(Move.DOWN);
    seq(Move.A);
    seq(Move.RIGHT);
    seq(Move.DOWN);
    seq(Move.RIGHT);
    seq(new Wait(1));
    seq(new PressButton(Move.A), 0);
    //    seq(new PressButton(Move.START), 0);
    seq(new RunUntil(() -> {return (Gb.readMemory(RomInfo.tetris.hGameState) == 0x0 ? 1 : 0);}));
    seq(() -> {
      short[] board = new short[Board.HEIGHT];

      int startAddress = 0xc802;
      for (int y = 0; y < Board.HEIGHT; y++)
        for (int x = 0; x < Board.WIDTH; x++)
          if (Gb.readMemory(startAddress + y*0x20 + x) != 0x2f)
            board[y] |= 1 << x;

      int curPiece = (Gb.readMemory(RomInfo.tetris.wCurPiece) >> 2);
      testLockPiece(board, curPiece, new LockPiece.SizeLog());
      return 1;
    });
    seq(new Wait(10));
    seq(new Move() {
      @Override public int getInitialKey() { return 0; }
      @Override
      public boolean doMove() {
        try {
          Thread.sleep(100000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        return true;
      }
    });
  }

  private static <T> void testLockPiece(short[] board, int curPiece, Log<T> logStrategy) {
    Board.print(board);
    LockPiece<T> pp = new LockPiece<>(board, curPiece, logStrategy);
    for(Map.Entry<Board, T> e : pp.lockPiece().entrySet()) {
      System.out.println("dist: " + e.getValue());
      e.getKey().print();
      System.out.println();
    }
  }

  public static void main(String[] args) {
    RomInfo.setRom(new TetrisRomInfo());

    Runner.run(new TetrisLockPiece());
  }
}
