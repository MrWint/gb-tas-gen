package mrwint.gbtasgen.tools.playback.loganalyzer.operation;

import java.util.TreeMap;

import mrwint.gbtasgen.tools.playback.loganalyzer.OamEntry;
import mrwint.gbtasgen.tools.playback.loganalyzer.accessibility.Accessibility;
import mrwint.gbtasgen.tools.playback.loganalyzer.accessibility.OamAccessibility;

public class WriteOamDirect implements PlaybackOperation {
  private final TreeMap<Integer, Integer> inputMap = new TreeMap<>();
  private final boolean clear;
  private final int skipHlAddressOffset;
  private final int skipHlCycleOffset;

  public WriteOamDirect(int position, OamEntry value, boolean skipHl) {
    this.clear = value.equals(OamEntry.DISABLED);
    skipHlAddressOffset = skipHl ? 3 : 0;
    skipHlCycleOffset = skipHl ? -12 : 0;
    
    inputMap.put(skipHlCycleOffset + 20, toJoypadInput1(position << 2));
    inputMap.put(skipHlCycleOffset + 36, toJoypadInput2(position << 2));
    
    if (!clear) {
      for (int i = 0; i < 4; i++) {
        inputMap.put(skipHlCycleOffset + 36*i + 48, toJoypadInput1(value.get(i)));
        inputMap.put(skipHlCycleOffset + 36*i + 48 + 16, toJoypadInput2(value.get(i)));
      }
    }
  }
  @Override
  public TreeMap<Integer, Integer> getInputMap() {
    return inputMap;
  }
  @Override
  public int getCycleCount() {
    return skipHlCycleOffset + (clear ? 76 : 204);
  }
  @Override
  public int getJumpAddress() {
    return skipHlAddressOffset + (clear ? PlaybackAddresses.CLEAR_SPRITE : PlaybackAddresses.WRITE_SPRITE);
  }
  @Override
  public int getStartOutputCycle() {
    return skipHlCycleOffset + (clear ? 52 : 72);
  }
  @Override
  public int getEndOutputCycle() {
    return skipHlCycleOffset + (clear ? 52 : 180);
  }
  @Override
  public Accessibility getAccessibility() {
    return new OamAccessibility();
  }
}
