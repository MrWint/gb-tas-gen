package mrwint.gbtasgen.tools.playback.loganalyzer.operation;

import java.util.TreeMap;

public class WriteByteDirect implements PlaybackOperation {
  public static final int JUMP_ADDRESS = 0x0339;
  
  private final TreeMap<Integer, Integer> inputMap = new TreeMap<>();
  public WriteByteDirect(int address, int value) {
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
    return 116;
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
    return 92;
  }
}