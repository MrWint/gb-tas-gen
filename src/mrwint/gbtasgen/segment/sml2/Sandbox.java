package mrwint.gbtasgen.segment.sml2;

import static mrwint.gbtasgen.rom.RomInfo.sml2;
import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.PressButton;
import mrwint.gbtasgen.move.Wait;
import mrwint.gbtasgen.rom.RomInfo;
import mrwint.gbtasgen.rom.sml2.Sml2RomInfo;
import mrwint.gbtasgen.segment.util.MoveSegment;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.util.Runner;
import mrwint.gbtasgen.util.Util;

public class Sandbox extends SeqSegment {

  @Override
  protected void execute() {
//    delayMetric(() -> Gb.readMemory(sml2.hGameState) == 1 ? 1 : 0); // Wait for title screen
//    delayMetric(() -> Util.getMemoryWordLE(sml2.sFrameCountDown) <= 0x500 ? 1 : 0); // Wait for cooldown timer
    seqWait(82);
    seqButtonNoDelay(Move.START);
//    delayMetric(() -> Gb.readMemory(sml2.hGameState) == 0x1a ? 1 : 0); // Wait for file select
//    delayMetric(() -> Gb.readMemory(sml2.sFileSelectState) == 1 ? 1 : 0); // Wait for file select ready
    seqWait(54);
    seqButtonNoDelay(Move.DOWN);
//    delayMetric(() -> Gb.readMemory(sml2.hGameState) == 0xc ? 1 : 0); // Wait for map
    seqWait(100);
    seqButtonNoDelay(Move.A);

//  seqFunc(() -> System.out.println("Gamestate: " + Gb.readMemory(sml2.hGameState)));

    seqWait(1000);
//    seq(new Wait(1), 0); // waitForGameState(0x7)
//    seq(new PressButton(Move.START), 0);
//    seq(new Wait(1), 0); // waitForGameState(0xe)
//    seq(new PressButton(Move.RIGHT), 0);
//    seq(new PressButton(Move.START), 0);
//    seq(new Wait(1), 0); // waitForGameState(0x13)
//    seq(new PressButton(Move.DOWN), 0);
//    seq(new PressButton(Move.LEFT), 0);
//    seq(new PressButton(Move.DOWN), 0);
//    seq(new PressButton(Move.A), 0);
//    seq(new PressButton(Move.RIGHT), 0);
//    seq(new PressButton(Move.DOWN), 0);
//    seq(new PressButton(Move.RIGHT), 0);
//    seq(new PressButton(Move.START), 0);
//    seq(new Wait(8), 0); // waitForGameState(0x0)
//    seq(new MoveSegment(new PressButton(Move.RIGHT), 0));
//    for(int i=0;i<108;i++)
//      seq(new MoveSegment(new PressButton(0), 0));
//    for(int i=0;i<2;i++)
//      seq(new MoveSegment(new PressButton(0), 0));
//    seq(new MoveSegment(new PressButton(Move.LEFT), 0));
  }

  public static void main(String[] args) {
    RomInfo.setRom(new Sml2RomInfo());

    Runner.run(new Sandbox());
  }
}
