package mrwint.gbtasgen.tools.playback.loganalyzer.operation;

import java.util.TreeMap;

import mrwint.gbtasgen.tools.playback.loganalyzer.Tile;

public class WriteTileDirect implements PlaybackOperation {
  public static final int JUMP_ADDRESS = 0x0289;
  
  private final TreeMap<Integer, Integer> inputMap = new TreeMap<>();
  public WriteTileDirect(int address, Tile value) {
    inputMap.put(12, toJoypadInput1((address >> 8) & 0xff));
    inputMap.put(28, toJoypadInput2((address >> 8) & 0xff));
    inputMap.put(40, (address >> 4) ^ 0xf);
    for (int i = 0; i < 16; i++) {
      inputMap.put(36*i + 68, toJoypadInput1(value.get(i)));
      inputMap.put(36*i + 68 + 16, toJoypadInput2(value.get(i)));
    }
  }
  @Override
  public TreeMap<Integer, Integer> getInputMap() {
    return inputMap;
  }
  @Override
  public int getCycleCount() {
    return 656;
  }
  @Override
  public int getJumpAddress() {
    return JUMP_ADDRESS;
  }
  @Override
  public int getStartOutputCycle() {
    return 92;
  }
  @Override
  public int getEndOutputCycle() {
    return 632;
  }
}
