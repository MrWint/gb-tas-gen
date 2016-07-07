package mrwint.gbtasgen.tools.playback.loganalyzer.operation;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Record implements PlaybackOperation {
  public static final int JUMP_ADDRESS = 0xc000;
  
  private final TreeMap<Integer, Integer> inputMap = new TreeMap<>();
  private final int cycleCount;
  public Record(List<Integer> values) {
    if (values.size() == 0)
      throw new IllegalArgumentException("value list empty.");
    if (values.size() > 256 * 16)
      throw new IllegalArgumentException("value list size " + values.size() + " too large.");

    int numLoops = (1 + (values.size() - 1) / 16) % 256;
    int offset = ((16 - values.size() % 16) % 16) * 6;

    inputMap.put(36, toJoypadInput1(numLoops));
    inputMap.put(52, toJoypadInput2(numLoops));
    inputMap.put(64, toJoypadInput1(offset));
    inputMap.put(80, toJoypadInput2(offset));
    
    int curCycles = 116;
    for (int i = 0; i < values.size(); i++) {
      inputMap.put(curCycles, toJoypadInput1(values.get(i)));
      inputMap.put(curCycles + 16, toJoypadInput2(values.get(i)));

      curCycles += 40;
      if ((values.size() - i - 1) % 16 == 0)
        curCycles += 16;
    }
    cycleCount = curCycles + 12;
    if (cycleCount != getCycleCount(values.size()))
      throw new RuntimeException("cycle count " + cycleCount + "does not match calculation of " + getCycleCount(values.size()));
  }
  @Override
  public TreeMap<Integer, Integer> getInputMap() {
    return inputMap;
  }
  @Override
  public int getCycleCount() {
    return cycleCount;
  }
  @Override
  public int getJumpAddress() {
    return JUMP_ADDRESS;
  }
  
  public static Record forStackFrames(List<Integer> stackFrames) {
    List<Integer> values = new ArrayList<>();
    for (int stackFrame : stackFrames) {
      values.add(stackFrame & 0xff);
      values.add((stackFrame >> 8) & 0xff);
    }
    return new Record(values);
  }
  /** Total cycles of this operation. */
  public static int getCycleCount(int len) {
    return len * 40 + ((len + 15) / 16) * 16 + 128;
  }
  /** Relative cycles of first input read. */
  public static int getFirstInputCycles() {
    return 36;
  }
  
  @Override
  public int getStartOutputCycle() {
    return -1;
  }
  @Override
  public int getEndOutputCycle() {
    return -1;
  }
}