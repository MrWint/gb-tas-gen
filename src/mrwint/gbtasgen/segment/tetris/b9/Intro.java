package mrwint.gbtasgen.segment.tetris.b9;

import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.move.RunUntil;
import mrwint.gbtasgen.move.Wait;
import mrwint.gbtasgen.rom.RomInfo;
import mrwint.gbtasgen.segment.util.SeqSegment;

public class Intro extends SeqSegment {
  private Move waitForGameState(int gameState) {
    return new RunUntil(() -> Gb.readMemory(RomInfo.tetris.hGameState) == gameState ? 1 : 0);
  }

  @Override
  protected void execute() {
    seq(waitForGameState(0x35), 0);
    seq(new PressButton(Move.START), 0);
    seq(new Wait(1), 0); // waitForGameState(0x7)
    seq(new PressButton(Move.START), 0);
    seq(new Wait(1), 0); // waitForGameState(0xe)
    seq(new PressButton(Move.RIGHT), 0);
    seq(new PressButton(Move.START), 0);
    seq(new Wait(1), 0); // waitForGameState(0x13)
    seq(new PressButton(Move.DOWN), 0);
    seq(new PressButton(Move.LEFT), 0);
    seq(new PressButton(Move.DOWN), 0);
    seq(new PressButton(Move.A), 0);
    seq(new PressButton(Move.RIGHT), 0);
    seq(new PressButton(Move.DOWN), 0);
    seq(new PressButton(Move.RIGHT), 0);
    seq(new PressButton(Move.START), 0);
    seq(new Wait(8), 0); // waitForGameState(0x0)
  }
}
