package mrwint.gbtasgen.segment.tetris;

import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.metric.StateResettingMetric;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.move.RunUntil;
import mrwint.gbtasgen.move.SequenceMove;
import mrwint.gbtasgen.move.Wait;
import mrwint.gbtasgen.rom.RomInfo;
import mrwint.gbtasgen.rom.tetris.Tetris10RomInfo;
import mrwint.gbtasgen.rom.tetris.TetrisRomInfo;
import mrwint.gbtasgen.segment.util.FindShortestSequenceSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.segment.util.SleepSegment;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.state.StateBuffer;
import mrwint.gbtasgen.util.Runner;
import mrwint.gbtasgen.util.Util;

public class Tetris extends SeqSegment {

  private final class ThreeExtraPieces implements StateResettingMetric {
    @Override
    public int getMetricInternal() {
      State.steps(10);
      int numFull = 0;
      int numRegions = 0;
      int numSupports = 0;
      int numPointRegions = 0;
      int startAddress = 0xc902;
      for (int a = 0; a < 10; a++) {
        if (Gb.readMemory(startAddress + a) != 0x2f)
          numFull++;
        else {
          if (Gb.readMemory(startAddress + a + 0x20) != 0x2f)
            numSupports++;
          if (a == 0 || Gb.readMemory(startAddress + a - 1) != 0x2f) {
            numRegions++;
            if ((a == 9 || Gb.readMemory(startAddress + a + 1) != 0x2f) && Gb.readMemory(startAddress + a + 0x20) == 0x2f && Gb.readMemory(startAddress + a + 0x40) != 0x2f)
              numPointRegions++;
          }
        }
      }
      if (numFull < 6-numPointRegions || numRegions > 1+numPointRegions || numPointRegions > 1 || numSupports + numPointRegions < numRegions)
        return 0;
      int curPiece = (Gb.readMemory(RomInfo.tetris.wCurPiece) >> 2);
      int previewPiece = (Gb.readMemory(RomInfo.tetris.wPreviewPiece) >> 2);
      int nextPreviewPiece = (Gb.readMemory(RomInfo.tetris.hNextPreviewPiece) >> 2);
      if (numPointRegions > 0 && curPiece != 0 && curPiece != 1 && curPiece != 2)
        return 0;
      if (previewPiece == 3 && curPiece != 0 && curPiece != 1)
        return 0;
      if (nextPreviewPiece == 3 && curPiece != 0 && curPiece != 1 && previewPiece != 0 && previewPiece != 1)
        return 0;
      System.out.println(String.format("full: %s, regions: %s, pointRegions: %s", numFull, numRegions, numPointRegions));
      System.out.println("pieces: " + curPiece + " " + previewPiece + " " + nextPreviewPiece);
      return 1;
    }
  }

  private final class FourExtraPieces implements StateResettingMetric {
    @Override
    public int getMetricInternal() {
      State.steps(10);
      int numFull = 0;
      int numRegions = 0;
      int numSupports = 0;
      int numPointRegions = 0;
      int startAddress = 0xc902;
      for (int a = 0; a < 10; a++) {
        if (Gb.readMemory(startAddress + a) != 0x2f)
          numFull++;
        else {
          if (Gb.readMemory(startAddress + a + 0x20) != 0x2f)
            numSupports++;
          if (a == 0 || Gb.readMemory(startAddress + a - 1) != 0x2f) {
            numRegions++;
            if ((a == 9 || Gb.readMemory(startAddress + a + 1) != 0x2f) && Gb.readMemory(startAddress + a + 0x20) != 0x2f)
              numPointRegions++;
          }
        }
      }
      int curPiece = (Gb.readMemory(RomInfo.tetris.wCurPiece) >> 2);
      int previewPiece = (Gb.readMemory(RomInfo.tetris.wPreviewPiece) >> 2);
      int nextPreviewPiece = (Gb.readMemory(RomInfo.tetris.hNextPreviewPiece) >> 2);
      System.out.println(String.format("full: %s, regions: %s, pointRegions: %s", numFull, numRegions, numPointRegions));
      System.out.println("pieces: " + curPiece + " " + previewPiece + " " + nextPreviewPiece);
      return 1;
    }
  }

  private Move waitForGameState(int gameState) {
    return new RunUntil(() -> Gb.readMemory(RomInfo.tetris.hGameState) == gameState ? 1 : 0);
  }

  @Override
  protected void execute() {
    seq(new FindShortestSequenceSegment(new Move[]{
        waitForGameState(0x35),
        new PressButton(Move.START),
        waitForGameState(0x7),
        new PressButton(Move.START | Move.DOWN),
        waitForGameState(0xe),
        new SequenceMove(
            new PressButton(Move.RIGHT),
            new PressButton(Move.START)),
        new SequenceMove(
            waitForGameState(0x13),
            new PressButton(Move.DOWN),
            new PressButton(Move.LEFT),
            new PressButton(Move.DOWN),
            new PressButton(Move.A)),
        new SequenceMove(
            new PressButton(Move.RIGHT),
            new PressButton(Move.DOWN),
            new PressButton(Move.RIGHT),
            new PressButton(Move.START)),
        waitForGameState(0xa),
    }, new ThreeExtraPieces()));
    seq(new Wait(20));
    seq(new Move() {
      @Override public int getInitialKey() { return 0; }
      @Override
      public boolean doMove() {
        try {
          Thread.sleep(1000000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        return true;
      }
    });
  }

  public static void main(String[] args) {
    RomInfo.setRom(new TetrisRomInfo());

    Runner.run(new Tetris());
  }
}
