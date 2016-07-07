package mrwint.gbtasgen.tools.playback.loganalyzer.operation;

import java.util.TreeMap;

import mrwint.gbtasgen.tools.playback.loganalyzer.Palette;

public class WriteBgPaletteDirect implements PlaybackOperation {
  public static final int JUMP_ADDRESS = 0x02F7;
  
  private final TreeMap<Integer, Integer> inputMap = new TreeMap<>();
  public WriteBgPaletteDirect(int index, Palette value) {
    int indexValue = 0x80 | (index << 3);
    inputMap.put(20, toJoypadInput1((indexValue >> 8) & 0xff));
    inputMap.put(36, toJoypadInput2((indexValue >> 8) & 0xff));
    for (int i = 0; i < 8; i++) {
      inputMap.put(32*i + 56, toJoypadInput1(value.get(i)));
      inputMap.put(32*i + 56 + 16, toJoypadInput2(value.get(i)));
    }
  }
  @Override
  public TreeMap<Integer, Integer> getInputMap() {
    return inputMap;
  }
  @Override
  public int getCycleCount() {
    return 328;
  }
  @Override
  public int getJumpAddress() {
    return JUMP_ADDRESS;
  }
  @Override
  public int getStartOutputCycle() {
    return 80;
  }
  @Override
  public int getEndOutputCycle() {
    return 304;
  }
}