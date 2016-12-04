package mrwint.gbtasgen.tools.playback.loganalyzer.operation;

import java.util.TreeMap;

import mrwint.gbtasgen.tools.playback.loganalyzer.accessibility.Accessibility;
import mrwint.gbtasgen.tools.playback.loganalyzer.accessibility.VramAccessibility;

public class WriteByteDirect implements PlaybackOperation {
  private final TreeMap<Integer, Integer> inputMap = new TreeMap<>();
  
  private final int newVramBank;
  private final int skipHlAddressOffset;
  private final int skipHlCycleOffset;

  public WriteByteDirect(int address, int value, int newVramBank, boolean skipHl) {
    this.newVramBank = newVramBank;
    skipHlAddressOffset = skipHl ? 3 : 0;
    skipHlCycleOffset = skipHl ? -12 : 0;

    inputMap.put(skipHlCycleOffset + 12, toJoypadInput1((address >> 8) & 0xff));
    inputMap.put(skipHlCycleOffset + 28, toJoypadInput2((address >> 8) & 0xff));
    inputMap.put(skipHlCycleOffset + 40, toJoypadInput1(address & 0xff));
    inputMap.put(skipHlCycleOffset + 56, toJoypadInput2(address & 0xff));
    inputMap.put(skipHlCycleOffset + 68, toJoypadInput1(value));
    inputMap.put(skipHlCycleOffset + 84, toJoypadInput2(value));
  }
  @Override
  public TreeMap<Integer, Integer> getInputMap() {
    return inputMap;
  }
  @Override
  public int getCycleCount() {
    switch (newVramBank) {
    case 0:
      return skipHlCycleOffset + 132;
    case 1:
      return skipHlCycleOffset + 132;
    default:
      return skipHlCycleOffset + 116;
    }
  }
  @Override
  public int getJumpAddress() {
    switch (newVramBank) {
    case 0:
      return skipHlAddressOffset + PlaybackAddresses.WRITE_BYTE_DIRECT_VRAM0;
    case 1:
      return skipHlAddressOffset + PlaybackAddresses.WRITE_BYTE_DIRECT_VRAM1;
    default:
      return skipHlAddressOffset + PlaybackAddresses.WRITE_BYTE_DIRECT;
    }
  }
  @Override
  public int getStartOutputCycle() {
    return skipHlCycleOffset + 92;
  }
  @Override
  public int getEndOutputCycle() {
    return skipHlCycleOffset + 92;
  }
  @Override
  public Accessibility getAccessibility() {
    return new VramAccessibility();
  }
}