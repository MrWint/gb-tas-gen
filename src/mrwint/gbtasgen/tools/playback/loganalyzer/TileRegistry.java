package mrwint.gbtasgen.tools.playback.loganalyzer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import mrwint.gbtasgen.tools.playback.loganalyzer.state.StateHistory;

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
  public static class DoubleTileResult {
    public final TileEntry upperTile;
    public final TileEntry lowerTile;
    public final boolean flipHorizontally;
    public final boolean flipVertically;
    
    public DoubleTileResult(TileEntry upperTile, TileEntry lowerTile, boolean flipHorizontally, boolean flipVertically) {
      this.upperTile = upperTile;
      this.lowerTile = lowerTile;
      this.flipHorizontally = flipHorizontally;
      this.flipVertically = flipVertically;
    }
  }
  
  public static class TileEntry {
    public final Tile tile;
    public boolean usedForBackground = false;
    public boolean usedForSpriteAny = false;
    public boolean usedForSpriteUpper = false;
    public boolean usedForSpriteLower = false;
    public TileEntry pairedWith = null;
    public final StateHistory<TimeStamp, Integer> usages = StateHistory.withTimestampIndex(StateHistory.getIdentityMerger());
    public TileEntry(Tile tile) {
      this.tile = tile;
    }
  }
  
  private final HashMap<Tile, ArrayList<TileEntry>> knownTiles = new HashMap<>();

  private TileResult registerSingleTile(Tile tile) {
    ArrayList<TileEntry> entries;
    if ((entries = knownTiles.get(tile)) != null)
      for (TileEntry entry : entries)
        if (entry.pairedWith == null)
          return new TileResult(entry, false, false);
    if ((entries = knownTiles.get(tile.flipHorizontally())) != null)
      for (TileEntry entry : entries)
        if (entry.pairedWith == null)
          return new TileResult(entry, true, false);
    if ((entries = knownTiles.get(tile.flipVertically())) != null)
      for (TileEntry entry : entries)
        if (entry.pairedWith == null)
          return new TileResult(entry, false, true);
    if ((entries = knownTiles.get(tile.flipHorizontally().flipVertically())) != null)
      for (TileEntry entry : entries)
        if (entry.pairedWith == null)
          return new TileResult(entry, true, true);

    entries = new ArrayList<TileEntry>();
    entries.add(new TileEntry(tile));
    knownTiles.put(tile, entries);
    return new TileResult(entries.get(0), false, false);
  }

  public TileResult registerForBg(Tile tile) {
    TileResult result = registerSingleTile(tile);
    
    result.tile.usedForBackground = true;
    return result;
  }

  public TileResult registerForObj8x8(Tile tile) {
    TileResult result = registerSingleTile(tile);
    
    result.tile.usedForSpriteAny = true;
    return result;
  }

  public DoubleTileResult registerForObj8x16(Tile upperTile, Tile lowerTile) {
    
    // Search for existing pair
    for (int flip = 0; flip < 4; flip++) {
      boolean flipVertically = (flip & 0x2) != 0;
      boolean flipHorizontally = (flip & 0x1) != 0;
      Tile flippedUpperTile = flipVertically ? (flipHorizontally ? lowerTile.flipVertically().flipHorizontally() : lowerTile.flipVertically()) : (flipHorizontally ? upperTile.flipHorizontally() : upperTile);
      Tile flippedLowerTile = flipVertically ? (flipHorizontally ? upperTile.flipVertically().flipHorizontally() : upperTile.flipVertically()) : (flipHorizontally ? lowerTile.flipHorizontally() : lowerTile);
 
      ArrayList<TileEntry> upperEntries = knownTiles.get(flippedUpperTile);
      if (upperEntries != null)
        for (TileEntry upperEntry : upperEntries)
          if (!upperEntry.usedForSpriteLower)
            if (upperEntry.pairedWith != null && upperEntry.pairedWith.tile.equals(flippedLowerTile))
              return new DoubleTileResult(upperEntry, upperEntry.pairedWith, flipHorizontally, flipVertically);

      ArrayList<TileEntry> lowerEntries = knownTiles.get(flippedLowerTile);
      if (lowerEntries != null)
        for (TileEntry lowerEntry : lowerEntries)
          if (!lowerEntry.usedForSpriteUpper)
            if (lowerEntry.pairedWith != null && lowerEntry.pairedWith.tile.equals(flippedUpperTile))
              throw new RuntimeException("found lowerTile match but no corresponding upperTile match");
    }

    // Create new pair
    TileEntry upperEntry = new TileEntry(upperTile);
    if (!knownTiles.containsKey(upperTile))
      knownTiles.put(upperTile, new ArrayList<>());
    knownTiles.get(upperTile).add(upperEntry);

    TileEntry lowerEntry = new TileEntry(lowerTile);
    if (!knownTiles.containsKey(lowerTile))
      knownTiles.put(lowerTile, new ArrayList<>());
    knownTiles.get(lowerTile).add(lowerEntry);
    
    upperEntry.usedForSpriteAny = true;
    upperEntry.usedForSpriteUpper = true;
    upperEntry.pairedWith = lowerEntry;
    lowerEntry.usedForSpriteAny = true;
    lowerEntry.usedForSpriteLower = true;
    lowerEntry.pairedWith = upperEntry;
    
    return new DoubleTileResult(upperEntry, lowerEntry, false, false);
  }
  
  public int getSize() {
    return knownTiles.size();
  }
  
  public int getUsageSize() {
    int usageSize = 0;
    for (ArrayList<TileEntry> tiles : knownTiles.values())
      for (TileEntry tile : tiles)
        usageSize += tile.usages.getSize();
    return usageSize;
  }

  public TreeMap<TimeStamp, ArrayList<TileEntry>> getUsageEventMap() {
    TreeMap<TimeStamp, ArrayList<TileEntry>> map = new TreeMap<>();
    for (ArrayList<TileEntry> tiles : knownTiles.values())
      for (TileEntry tile : tiles) {
        for (TimeStamp usageEvent : tile.usages.getNonNullEventIndices()) {
          if (!map.containsKey(usageEvent))
            map.put(usageEvent, new ArrayList<>());
          map.get(usageEvent).add(tile);
        }
      }
    return map;
  }
}
