package mrwint.gbtasgen.tools.playback.loganalyzer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import mrwint.gbtasgen.tools.playback.loganalyzer.state.StateHistory;

public class PaletteRegistry {
  public static class PaletteEntry {
    public final Palette palette;
    public final StateHistory<TimeStamp, Integer> usages = StateHistory.withTimestampIndex(StateHistory.getIdentityMerger());
    public PaletteEntry(Palette palette) {
      this.palette = palette;
    }
  }

  private final HashMap<Palette, PaletteEntry> knownPalettes = new HashMap<>();

  public PaletteEntry register(Palette palette) {
    PaletteEntry entry;
    if ((entry = knownPalettes.get(palette)) != null)
      return entry;

    entry = new PaletteEntry(palette);
    knownPalettes.put(palette, entry);
    return entry;
  }
  
  public int getSize() {
    return knownPalettes.size();
  }
  
  public int getUsageSize() {
    int usageSize = 0;
    for (PaletteEntry palette : knownPalettes.values())
      usageSize += palette.usages.getSize();
    return usageSize;
  }

  public TreeMap<TimeStamp, ArrayList<PaletteEntry>> getUsageEventMap() {
    TreeMap<TimeStamp, ArrayList<PaletteEntry>> map = new TreeMap<>();
    for (PaletteEntry palette : knownPalettes.values()) {
      for (TimeStamp usageEvent : palette.usages.getNonNullEventIndices()) {
        if (!map.containsKey(usageEvent))
          map.put(usageEvent, new ArrayList<>());
        map.get(usageEvent).add(palette);
      }
    }
    return map;
  }
}
