package mrwint.gbtasgen.tools.playback.loganalyzer.operation;

import java.util.TreeMap;

import mrwint.gbtasgen.tools.playback.loganalyzer.accessibility.Accessibility;
import mrwint.gbtasgen.tools.playback.loganalyzer.accessibility.VramAccessibility;

public class WriteByteDirect implements PlaybackOperation {
  private final TreeMap<Integer, Integer> inputMap = new TreeMap<>();
  
  private final int newVramBank;

  public WriteByteDirect(int address, int value, int newVramBank) {
    this.newVramBank = newVramBank;

    inputMap.put(12, toJoypadInput1((address >> 8) & 0xff));
    inputMap.put(28, toJoypadInput2((address >> 8) & 0xff));
    inputMap.put(40, toJoypadInput1(address & 0xff));
    inputMap.put(56, toJoypadInput2(address & 0xff));
    inputMap.put(68, toJoypadInput1(value));
    inputMap.put(84, toJoypadInput2(value));
  }
  @Override
  public TreeMap<Integer, Integer> getInputMap() {
    return inputMap;
  }
  @Override
  public int getCycleCount() {
    switch (newVramBank) {
    case 0:
      return 132;
    case 1:
      return 132;
    default:
      return 116;
    }
  }
  @Override
  public int getJumpAddress() {
    switch (newVramBank) {
    case 0:
      return PlaybackAddresses.WRITE_BYTE_DIRECT_VRAM0;
    case 1:
      return PlaybackAddresses.WRITE_BYTE_DIRECT_VRAM1;
    default:
      return PlaybackAddresses.WRITE_BYTE_DIRECT;
    }
  }
  @Override
  public int getStartOutputCycle() {
    return 92;
  }
  @Override
  public int getEndOutputCycle() {
    return 92;
  }
  @Override
  public Accessibility getAccessibility() {
    return new VramAccessibility();
  }
}