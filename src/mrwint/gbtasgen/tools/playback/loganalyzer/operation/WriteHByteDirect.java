package mrwint.gbtasgen.tools.playback.loganalyzer.operation;

import java.util.TreeMap;

import mrwint.gbtasgen.tools.playback.loganalyzer.accessibility.Accessibility;
import mrwint.gbtasgen.tools.playback.loganalyzer.accessibility.AlwaysAccessible;

public class WriteHByteDirect implements PlaybackOperation {
  private final TreeMap<Integer, Integer> inputMap = new TreeMap<>();
  private final int skipHlAddressOffset;
  private final int skipHlCycleOffset;

  public WriteHByteDirect(int address, int value, boolean skipHl) {
    skipHlAddressOffset = skipHl ? 3 : 0;
    skipHlCycleOffset = skipHl ? -12 : 0;

    inputMap.put(skipHlCycleOffset + 12, toJoypadInput1(address & 0xff));
    inputMap.put(skipHlCycleOffset + 28, toJoypadInput2(address & 0xff));
    inputMap.put(skipHlCycleOffset + 40, toJoypadInput1(value));
    inputMap.put(skipHlCycleOffset + 56, toJoypadInput2(value));
  }
  @Override
  public TreeMap<Integer, Integer> getInputMap() {
    return inputMap;
  }
  @Override
  public int getCycleCount() {
    return skipHlCycleOffset + 88;
  }
  @Override
  public int getJumpAddress() {
    return skipHlAddressOffset + PlaybackAddresses.WRITE_HBYTE_DIRECT;
  }
  @Override
  public int getStartOutputCycle() {
    return skipHlCycleOffset + 64;
  }
  @Override
  public int getEndOutputCycle() {
    return skipHlCycleOffset + 64;
  }
  @Override
  public Accessibility getAccessibility() {
    return new AlwaysAccessible();
  }
}