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
    seqMove(waitForGameState(0x35), 0);
    seqMove(new PressButton(Move.START), 0);
    seqMove(new Wait(1), 0); // waitForGameState(0x7)
    seqMove(new PressButton(Move.START), 0);
    seqMove(new Wait(1), 0); // waitForGameState(0xe)
    seqMove(new PressButton(Move.RIGHT), 0);
    seqMove(new PressButton(Move.START), 0);
    seqMove(new Wait(1), 0); // waitForGameState(0x13)
    seqMove(new PressButton(Move.DOWN), 0);
    seqMove(new PressButton(Move.LEFT), 0);
    seqMove(new PressButton(Move.DOWN), 0);
    seqMove(new PressButton(Move.A), 0);
    seqMove(new PressButton(Move.RIGHT), 0);
    seqMove(new PressButton(Move.DOWN), 0);
    seqMove(new PressButton(Move.RIGHT), 0);
    seqMove(new PressButton(Move.START), 0);
    seqMove(new Wait(8), 0); // waitForGameState(0x0)
  }
}
