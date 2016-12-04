package mrwint.gbtasgen.tools.playback.loganalyzer.operation;

import java.util.TreeMap;

import mrwint.gbtasgen.tools.playback.loganalyzer.accessibility.Accessibility;
import mrwint.gbtasgen.tools.playback.loganalyzer.accessibility.AlwaysAccessible;

public class SetVram implements PlaybackOperation {
  private final static TreeMap<Integer, Integer> INPUT_MAP = new TreeMap<>();
  private final int newVramBank;

  public SetVram(int newVramBank) {
    this.newVramBank = newVramBank;
  }
  @Override
  public TreeMap<Integer, Integer> getInputMap() {
    return INPUT_MAP;
  }
  @Override
  public int getCycleCount() {
    return 32;
  }
  @Override
  public int getJumpAddress() {
    switch (newVramBank) {
    case 0:
      return PlaybackAddresses.SET_VRAM0;
    case 1:
      return PlaybackAddresses.SET_VRAM1;
    default:
      throw new RuntimeException("invalid VRAM bank " + newVramBank);
    }
  }
  @Override
  public int getStartOutputCycle() {
    return -1;
  }
  @Override
  public int getEndOutputCycle() {
    return -1;
  }
  @Override
  public Accessibility getAccessibility() {
    return new AlwaysAccessible();
  }
}