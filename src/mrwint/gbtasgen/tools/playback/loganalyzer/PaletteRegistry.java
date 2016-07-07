package mrwint.gbtasgen.tools.playback.loganalyzer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class PaletteRegistry {
  public static class PaletteEntry {
    public final Palette palette;
    public final TreeMap<TimeStamp, Integer> usages = new TreeMap<>();
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
  
  public TreeMap<TimeStamp, ArrayList<PaletteEntry>> getUsageMap() {
    TreeMap<TimeStamp, ArrayList<PaletteEntry>> map = new TreeMap<>();
    for (PaletteEntry palette : knownPalettes.values()) {
      for (TimeStamp usage : palette.usages.keySet()) {
        if (!map.containsKey(usage))
          map.put(usage, new ArrayList<>());
        map.get(usage).add(palette);
      }
    }
    return map;
  }
}
