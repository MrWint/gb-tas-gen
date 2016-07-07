package mrwint.gbtasgen.tools.playback.loganalyzer.operation;

import java.util.TreeMap;

public class Wait implements PlaybackOperation {
  public static final int OFFSET_SHORT = 0x018D;
  public static final int OFFSET_LONG = 0x01A3;
  
  private final int waitCycles;
  private final int jumpAddress;
  private final TreeMap<Integer, Integer> inputMap = new TreeMap<>();
  public Wait(int waitCycles) {
    if (waitCycles < 16)
      throw new IllegalArgumentException("delay " + waitCycles + " too small.");
    if (waitCycles > 1051748)
      throw new IllegalArgumentException("delay " + waitCycles + " too large.");

    this.waitCycles = waitCycles;
    if (waitCycles <= 100) {
      jumpAddress = OFFSET_SHORT + (100 - waitCycles) / 4;
    } else {
      int remainingCycles = waitCycles - 104;
      int b = Math.min(remainingCycles / 4108, 255);
      remainingCycles -= b * 4108;
      int a = Math.min(remainingCycles / 16, 255);
      remainingCycles -= a * 16;
      int offset = 6 - (remainingCycles / 4);
      
      jumpAddress = OFFSET_LONG + offset;
      inputMap.put(12 + remainingCycles, toJoypadInput1((b + 1) % 256));
      inputMap.put(28 + remainingCycles, toJoypadInput2((b + 1) % 256));
      inputMap.put(40 + remainingCycles, toJoypadInput1((a + 1) % 256));
      inputMap.put(56 + remainingCycles, toJoypadInput2((a + 1) % 256));
    }
  }
  @Override
  public TreeMap<Integer, Integer> getInputMap() {
    return inputMap;
  }
  @Override
  public int getCycleCount() {
    return waitCycles;
  }
  @Override
  public int getJumpAddress() {
    return jumpAddress;
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