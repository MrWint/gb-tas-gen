package mrwint.gbtasgen.tools.playback.loganalyzer.operation;

import java.util.TreeMap;

import mrwint.gbtasgen.tools.playback.loganalyzer.Tile;
import mrwint.gbtasgen.tools.playback.loganalyzer.accessibility.Accessibility;
import mrwint.gbtasgen.tools.playback.loganalyzer.accessibility.VramAccessibility;

public class WriteTileDirect implements PlaybackOperation {
  private final TreeMap<Integer, Integer> inputMap = new TreeMap<>();
  private final int newVramBank;

  public WriteTileDirect(int address, Tile value, int newVramBank) {
    this.newVramBank = newVramBank;
    
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
    switch (newVramBank) {
    case 0:
      return 672;
    case 1:
      return 672;
    default:
      return 656;
    }
  }
  @Override
  public int getJumpAddress() {
    switch (newVramBank) {
    case 0:
      return PlaybackAddresses.WRITE_TILE_DIRECT_VRAM0;
    case 1:
      return PlaybackAddresses.WRITE_TILE_DIRECT_VRAM1;
    default:
      return PlaybackAddresses.WRITE_TILE_DIRECT;
    }
  }
  @Override
  public int getStartOutputCycle() {
    return 92;
  }
  @Override
  public int getEndOutputCycle() {
    return 632;
  }
  @Override
  public Accessibility getAccessibility() {
    return new VramAccessibility();
  }
}
