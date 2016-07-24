package mrwint.gbtasgen.tools.playback.loganalyzer.operation;

import java.util.TreeMap;

import mrwint.gbtasgen.tools.playback.loganalyzer.accessibility.Accessibility;
import mrwint.gbtasgen.tools.playback.loganalyzer.accessibility.AlwaysAccessible;

public class WriteHByteDirect implements PlaybackOperation {
  private final TreeMap<Integer, Integer> inputMap = new TreeMap<>();
  public WriteHByteDirect(int address, int value) {
    inputMap.put(12, toJoypadInput1(address & 0xff));
    inputMap.put(28, toJoypadInput2(address & 0xff));
    inputMap.put(40, toJoypadInput1(value));
    inputMap.put(56, toJoypadInput2(value));
  }
  @Override
  public TreeMap<Integer, Integer> getInputMap() {
    return inputMap;
  }
  @Override
  public int getCycleCount() {
    return 88;
  }
  @Override
  public int getJumpAddress() {
    return PlaybackAddresses.WRITE_HBYTE_DIRECT;
  }
  @Override
  public int getStartOutputCycle() {
    return 64;
  }
  @Override
  public int getEndOutputCycle() {
    return 64;
  }
  @Override
  public Accessibility getAccessibility() {
    return new AlwaysAccessible();
  }
}