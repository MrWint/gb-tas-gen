package mrwint.gbtasgen.tools.playback.loganalyzer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class TileRegistry {
  public static class TileResult {
    public final TileEntry tile;
    public final boolean flipHorizontally;
    public final boolean flipVertically;
    
    public TileResult(TileEntry tile, boolean flipHorizontally, boolean flipVertically) {
      this.tile = tile;
      this.flipHorizontally = flipHorizontally;
      this.flipVertically = flipVertically;
    }
  }
  
  public static class TileEntry {
    public final Tile tile;
    public boolean usedForBackground = false;
    public boolean usedForSprites = false;
    public final TreeMap<TimeStamp, Integer> usages = new TreeMap<>();
    public TileEntry(Tile tile) {
      this.tile = tile;
    }
  }
  
  private final HashMap<Tile, TileEntry> knownTiles = new HashMap<>();
  
  
  public TileResult register(Tile tile) {
    TileEntry entry;
    if ((entry = knownTiles.get(tile)) != null)
      return new TileResult(entry, false, false);
    if ((entry = knownTiles.get(tile.flipHorizontally())) != null)
      return new TileResult(entry, true, false);
    if ((entry = knownTiles.get(tile.flipVertically())) != null)
      return new TileResult(entry, false, true);
    if ((entry = knownTiles.get(tile.flipHorizontally().flipVertically())) != null)
      return new TileResult(entry, true, true);

    entry = new TileEntry(tile);
    knownTiles.put(tile, entry);
    return new TileResult(entry, false, false);
  }
  
  public int getSize() {
    return knownTiles.size();
  }
  
  public TreeMap<TimeStamp, ArrayList<TileEntry>> getUsageMap() {
    TreeMap<TimeStamp, ArrayList<TileEntry>> map = new TreeMap<>();
    for (TileEntry tile : knownTiles.values()) {
      for (TimeStamp usage : tile.usages.keySet()) {
        if (!map.containsKey(usage))
          map.put(usage, new ArrayList<>());
        map.get(usage).add(tile);
      }
    }
    return map;
  }
}
