package mrwint.gbtasgen.tools.playback.loganalyzer.operation;

import java.util.TreeMap;

import mrwint.gbtasgen.tools.playback.loganalyzer.OamEntry;
import mrwint.gbtasgen.tools.playback.loganalyzer.accessibility.Accessibility;
import mrwint.gbtasgen.tools.playback.loganalyzer.accessibility.OamAccessibility;

public class WriteOamDirect implements PlaybackOperation {
  private final TreeMap<Integer, Integer> inputMap = new TreeMap<>();
  private final boolean clear;

  public WriteOamDirect(int position, OamEntry value) {
    this.clear = value.equals(OamEntry.DISABLED);
    
    inputMap.put(20, toJoypadInput1(position << 2));
    inputMap.put(36, toJoypadInput2(position << 2));
    
    if (!clear) {
      for (int i = 0; i < 4; i++) {
        inputMap.put(36*i + 48, toJoypadInput1(value.get(i)));
        inputMap.put(36*i + 48 + 16, toJoypadInput2(value.get(i)));
      }
    }
  }
  @Override
  public TreeMap<Integer, Integer> getInputMap() {
    return inputMap;
  }
  @Override
  public int getCycleCount() {
    return clear ? 76 : 204;
  }
  @Override
  public int getJumpAddress() {
    return clear ? PlaybackAddresses.CLEAR_SPRITE : PlaybackAddresses.WRITE_SPRITE;
  }
  @Override
  public int getStartOutputCycle() {
    return clear ? 52 : 72;
  }
  @Override
  public int getEndOutputCycle() {
    return clear ? 52 : 180;
  }
  @Override
  public Accessibility getAccessibility() {
    return new OamAccessibility();
  }
}
