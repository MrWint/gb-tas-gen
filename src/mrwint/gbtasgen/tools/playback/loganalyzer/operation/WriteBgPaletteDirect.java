package mrwint.gbtasgen.tools.playback.loganalyzer.operation;

import java.util.TreeMap;

import mrwint.gbtasgen.tools.playback.loganalyzer.Palette;
import mrwint.gbtasgen.tools.playback.loganalyzer.accessibility.Accessibility;
import mrwint.gbtasgen.tools.playback.loganalyzer.accessibility.PaletteAccessibility;

public class WriteBgPaletteDirect implements PlaybackOperation {
  private final TreeMap<Integer, Integer> inputMap = new TreeMap<>();
  private final int skipHlAddressOffset;
  private final int skipHlCycleOffset;

  public WriteBgPaletteDirect(int index, Palette value, boolean skipHl) {
    skipHlAddressOffset = skipHl ? 3 : 0;
    skipHlCycleOffset = skipHl ? -12 : 0;

    inputMap.put(skipHlCycleOffset + 20, index ^ 0xf);
    for (int i = 0; i < 8; i++) {
      inputMap.put(skipHlCycleOffset + 32*i + 60, toJoypadInput1(value.get(i)));
      inputMap.put(skipHlCycleOffset + 32*i + 60 + 16, toJoypadInput2(value.get(i)));
    }
  }
  @Override
  public TreeMap<Integer, Integer> getInputMap() {
    return inputMap;
  }
  @Override
  public int getCycleCount() {
    return skipHlCycleOffset + 332;
  }
  @Override
  public int getJumpAddress() {
    return skipHlAddressOffset + PlaybackAddresses.WRITE_BG_PALETTE_DIRECT;
  }
  @Override
  public int getStartOutputCycle() {
    return skipHlCycleOffset + 84;
  }
  @Override
  public int getEndOutputCycle() {
    return skipHlCycleOffset + 308;
  }
  @Override
  public Accessibility getAccessibility() {
    return new PaletteAccessibility();
  }
}