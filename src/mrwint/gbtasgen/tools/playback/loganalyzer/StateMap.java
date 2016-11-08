package mrwint.gbtasgen.tools.playback.loganalyzer;

import static mrwint.gbtasgen.tools.playback.loganalyzer.TimedAction.Action.Type.HRAM;
import static mrwint.gbtasgen.tools.playback.loganalyzer.TimedAction.Action.Type.BGPALETTE;
import static mrwint.gbtasgen.tools.playback.loganalyzer.TimedAction.Action.Type.TILE;
import static mrwint.gbtasgen.tools.playback.loganalyzer.TimedAction.Action.Type.WRAM;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.TreeMap;
import java.util.function.Function;

import mrwint.gbtasgen.tools.playback.loganalyzer.PaletteRegistry.PaletteEntry;
import mrwint.gbtasgen.tools.playback.loganalyzer.TileRegistry.TileEntry;
import mrwint.gbtasgen.tools.playback.loganalyzer.TileRegistry.TileResult;
import mrwint.gbtasgen.tools.playback.loganalyzer.TimedAction.Action;
import mrwint.gbtasgen.tools.playback.loganalyzer.TimedAction.Action.Type;

public class StateMap {
  
  public static class TileState {
    public final TileEntry tile;
    public final boolean flipHorizontally;
    public final boolean flipVertically;
    public final boolean bgToOamPriority;
    public final PaletteEntry palette;

    public TileState(TileEntry tile, boolean flipHorizontally, boolean flipVertically, boolean bgToOamPriority, PaletteEntry palette) {
      this.tile = tile;
      this.flipHorizontally = flipHorizontally;
      this.flipVertically = flipVertically;
      this.bgToOamPriority = bgToOamPriority;
      this.palette = palette;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + (bgToOamPriority ? 1231 : 1237);
      result = prime * result + (flipHorizontally ? 1231 : 1237);
      result = prime * result + (flipVertically ? 1231 : 1237);
      result = prime * result + ((palette == null) ? 0 : palette.hashCode());
      result = prime * result + ((tile == null) ? 0 : tile.hashCode());
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      TileState other = (TileState) obj;
      if (bgToOamPriority != other.bgToOamPriority)
        return false;
      if (flipHorizontally != other.flipHorizontally)
        return false;
      if (flipVertically != other.flipVertically)
        return false;
      if (palette == null) {
        if (other.palette != null)
          return false;
      } else if (!palette.equals(other.palette))
        return false;
      if (tile == null) {
        if (other.tile != null)
          return false;
      } else if (!tile.equals(other.tile))
        return false;
      return true;
    }
  }

  private History<Integer> lcdc = new History<>();
  private History<Integer> wy = new History<>();
  private History<Integer> wx = new History<>();
  private History<Integer> scy = new History<>();
  private History<Integer> scx = new History<>();
  @SuppressWarnings("unchecked")
  private History<TileState>[] bgMaps = new History[0x800];

  private ArrayList<Integer> numFrames = new ArrayList<>();
  private int numScenes = 0;

  public StateMap addScene(RawMemoryMap memoryMap, int scene, int firstFrame, int numFrames) {
    HashMap<TileState, TileState> tileStateRegistry = new HashMap<>();

    this.numScenes++;
    this.numFrames.add(numFrames);

    for (int frame = firstFrame; frame < firstFrame + numFrames; frame++) {
      if ((frame - firstFrame) % 100 == 0)
        System.out.println("frame " + frame);
      for (int scanLine = 0; scanLine < 144; scanLine++) {
        int frameCycle = GbConstants.LINE_CYCLES * scanLine + 80 * GbConstants.DOUBLE_SPEED_FACTOR;
        TimeStamp time = new TimeStamp(scene, frame, frameCycle);
        TimeStamp adjustedTime = new TimeStamp(numScenes - 1, frame - firstFrame, frameCycle);
        
        int lcdc = memoryMap.getHramValue(GbConstants.LCDC, time, GbConstants.LCDC_DEFAULT);
        boolean mLcdc = true;
        int wy = memoryMap.getHramValue(GbConstants.WY, time, GbConstants.WY_DEFAULT);
        boolean mWy = ((lcdc & 0x20) != 0) && ((lcdc & 0x1) != 0 || !GbConstants.GBC);
        int wx = memoryMap.getHramValue(GbConstants.WX, time, GbConstants.WX_DEFAULT);
        boolean mWx = mWy && wy <= scanLine;
        int scy = memoryMap.getHramValue(GbConstants.SCY, time, GbConstants.SCY_DEFAULT);
        int scx = memoryMap.getHramValue(GbConstants.SCX, time, GbConstants.SCX_DEFAULT);
        boolean mScyx = (lcdc & 0x1) != 0 && (!mWx || wx > 7); // right edge not rendered when WX < 7
        
        this.lcdc.add(adjustedTime, mLcdc ? canonicalizeLcdc(lcdc) : null);
        this.wy.add(adjustedTime, mWy ? canonicalizeWy(wy) : null);
        this.wx.add(adjustedTime, mWx ? canonicalizeWx(wx) : null);
        this.scy.add(adjustedTime, mScyx ? canonicalizeScy(scy) : null);
        this.scx.add(adjustedTime, mScyx ? canonicalizeScx(scx) : null);
        
        // bg map
        for (int add = 0x9800; add < 0xa000; add++) {
          boolean mWinTile = (add < 0x9c00) == ((lcdc & 0x40) == 0);
          boolean mBgTile = (add < 0x9c00) == ((lcdc & 0x08) == 0);
          int x = add & 0x1f;
          int y = (add >>> 5) & 0x1f;
          mWinTile &= mWx && y == (scanLine - wy) / 8 && x <= Math.min((166 - wx) / 8, 19); // Win tiles x>=20 never rendered even when WX < 7
          mBgTile &= mScyx && y == ((scy + scanLine) & 0xff) / 8 && ((8*x + 0x100 + 7 - scx) & 0xff) < 0xa7 // tile on screen
              && (!mWx || ((8*x + 0x100 + 7 - scx) & 0xff) < (wx - 7) + 7); // not covered by window
          
          TileState state = null;
          if (mWinTile) {
            int firstRowScanLine = y * 8 + wy;
            state = createTileState(add, memoryMap, scene, frame, scanLine, firstRowScanLine);
          } else if (mBgTile) {
            int firstRowScanLine = ((y * 8 + 0x100 - scy) & 0xff);
            if (firstRowScanLine >= 0x90)
              firstRowScanLine -= 0x100;
            state = createTileState(add, memoryMap, scene, frame, scanLine, firstRowScanLine);
          }
          if (tileStateRegistry.containsKey(state))
            state = tileStateRegistry.get(state);
          else
            tileStateRegistry.put(state, state);
          if (this.bgMaps[add - 0x9800] == null)
            this.bgMaps[add - 0x9800] = new History<>();
          this.bgMaps[add - 0x9800].add(adjustedTime, state);
          if (state != null) {
            state.tile.usages.put(adjustedTime, null);
            state.tile.usedForBackground = true;
            state.palette.usages.put(adjustedTime, null);
          }
        }
      }
    }
    
    outputStats();
    return this;
  }
  
  private void outputStats() {
    System.out.println("LCDC: " + lcdc.getSize());
    System.out.println("WY: " + wy.getSize());
    System.out.println("WX: " + wx.getSize());
    System.out.println("SCY: " + scy.getSize());
    System.out.println("SCX: " + scx.getSize());
    
    int sum = 0;
    int max = Integer.MIN_VALUE;
    int maxi = 0;
    int min = Integer.MAX_VALUE;
    int mini = 0;
    for (int i = 0; i < 0x800; i++) {
      int size = bgMaps[i].getSize();
      if (size > max) {
        max = size;
        maxi = i;
      }
      if (size < min) {
        min = size;
        mini = i;
      }
      sum += size;
    }
    System.out.println("max BG tile: " + maxi + " with " + max);
    System.out.println("min BG tile: " + mini + " with " + min);
    System.out.println("avg BG tile: " + (sum / 0x800) + " (" + sum + ")");
    System.out.println("known tiles: " + tileRegistry.getSize());
    System.out.println("known palettes: " + paletteRegistry.getSize());
    System.out.println();

    for (int y = 0; y < 32; y++) {
      for (int x = 0; x < 32; x++) {
        System.out.print(" " + bgMaps[y * 32 + x].getSize());
      }
      System.out.println();
    }
    System.out.println();
    
    for (int y = 0; y < 32; y++) {
      for (int x = 0; x < 32; x++) {
        System.out.print(" " + bgMaps[0x400 + y * 32 + x].getSize());
      }
      System.out.println();
    }
  }

  private final TileRegistry tileRegistry = new TileRegistry();
  private final PaletteRegistry paletteRegistry = new PaletteRegistry();

  private TileState createTileState(int address, RawMemoryMap memoryMap, int scene, int frame, int scanLine, int firstRowScanLine) {
    byte[] tileData = new byte[16];
    byte[] paletteData = new byte[8];
    boolean flipHorizontally;
    boolean flipVertically;
    boolean bgToOamPriority;
    for (int dy = 0; dy < 8; dy++) {
      int frameCycle = Math.max(0, firstRowScanLine + dy) * GbConstants.LINE_CYCLES + 80 * GbConstants.DOUBLE_SPEED_FACTOR;
      TimeStamp time = new TimeStamp(scene, frame, frameCycle);
      int lcdc = memoryMap.getHramValue(GbConstants.LCDC, time, GbConstants.LCDC_DEFAULT);
      int bgMapTile = memoryMap.getVramValue(0, address, time, GbConstants.VRAM_DEFAULT);
      int bgMapTileAddress = ((lcdc & 0x10) != 0 ? 0x8000 : 0x9000) + bgMapTile * 0x10;
      if (bgMapTileAddress >= 0x9800)
        bgMapTileAddress -= 0x1000;
      int bgMapAttribute = memoryMap.getVramValue(1, address, time, GbConstants.VRAM_DEFAULT);
      int vramBank = (bgMapAttribute & 0x08) >> 3;
      tileData[2 * dy] = (byte)memoryMap.getVramValue(vramBank, bgMapTileAddress + 2*dy, time, GbConstants.VRAM_DEFAULT);
      tileData[2 * dy + 1] = (byte)memoryMap.getVramValue(vramBank, bgMapTileAddress + 2*dy + 1, time, GbConstants.VRAM_DEFAULT);
    }
    
    int frameCycle = scanLine * GbConstants.LINE_CYCLES + 80 * GbConstants.DOUBLE_SPEED_FACTOR;
    TimeStamp time = new TimeStamp(scene, frame, frameCycle);
    
    int bgMapAttribute = memoryMap.getVramValue(1, address, time, GbConstants.VRAM_DEFAULT);
    int paletteIndex = bgMapAttribute & 0x7;
    for (int i = 0; i < 8; i++) {
      paletteData[i] = (byte)memoryMap.getBgPaletteValue(paletteIndex * 8 + i, time);
      if ((i & 1) != 0)
        paletteData[i] &= 0x7f;
    }
    TileResult tileResult = tileRegistry.register(new Tile(tileData));
    flipHorizontally = tileResult.flipHorizontally ^ ((bgMapAttribute & 0x20) != 0);
    flipVertically = tileResult.flipVertically ^ ((bgMapAttribute & 0x40) != 0);
    bgToOamPriority = ((bgMapAttribute & 0x80) != 0);

    return new TileState(tileResult.tile, flipHorizontally, flipVertically, bgToOamPriority, paletteRegistry.register(new Palette(paletteData)));
  }

  private int canonicalizeLcdc(int value) {
    return (value & 0x80) == 0 ? 0 : (value & (~0x10));
  }
  private int canonicalizeWy(int value) {
    return Math.min(value, 0x90);
  }
  private int canonicalizeWx(int value) {
    return Math.min(value, 0xa7);
  }
  private int canonicalizeScy(int value) {
    return value;
  }
  private int canonicalizeScx(int value) {
    return value;
  }
  

  @SuppressWarnings("unchecked")
  private History<TileEntry>[][] tileHistory = new History[2][0x180];
  @SuppressWarnings("unchecked")
  private History<PaletteEntry>[] bgPaletteHistory = new History[8];

  public StateMap calculateTilePositions() {
    for (int vramBank = 0; vramBank < 2; vramBank++) 
      for (int address = 0; address < 0x180; address++)
        tileHistory[vramBank][address] = new History<>();

    TreeMap<TimeStamp, ArrayList<TileEntry>> usageMap = tileRegistry.getUsageMap();
    System.out.println("usageMap size " + usageMap.size());
    for (Entry<TimeStamp, ArrayList<TileEntry>> e : usageMap.entrySet()) {
      TimeStamp time = e.getKey();
      for (TileEntry tile : e.getValue()) {
        TimeStamp maxNextUseTime = time;
        int maxNextUseVramBank = -1;
        int maxNextUseAddress = 0;
        searchHit: for (int vramBank = 0; vramBank < 2; vramBank++) {
          for (int address = (tile.usedForBackground ? 0x80 : 0); address < (tile.usedForSprites ? 0x100 : 0x180); address++) {
            if (tileHistory[vramBank][address].getValueAt(time, null) == tile) {
              maxNextUseTime = null;
              maxNextUseVramBank = vramBank;
              maxNextUseAddress = address;
              break searchHit;
            }
          }
        }
        if (maxNextUseVramBank == -1) {
          searchEmpty: for (int vramBank = 0; vramBank < 2; vramBank++) {
            for (int address = (tile.usedForBackground ? 0x80 : 0); address < (tile.usedForSprites ? 0x100 : 0x180); address++) {
              if (tileHistory[vramBank][address].getValueAt(time, null) == null) {
                maxNextUseTime = null;
                maxNextUseVramBank = vramBank;
                maxNextUseAddress = address;
                break searchEmpty;
              }
            }
          }
        }
        if (maxNextUseVramBank == -1) {
          searchMaxNextUse: for (int vramBank = 0; vramBank < 2; vramBank++) {
            for (int address = (tile.usedForBackground ? 0x80 : 0); address < (tile.usedForSprites ? 0x100 : 0x180); address++) {
              TileEntry curTile = tileHistory[vramBank][address].getValueAt(time, null);
              TimeStamp previousUse = curTile.usages.floorKey(time);
              if (previousUse.scene == time.scene && previousUse.frame == time.frame)
                continue; // Don't re-use tiles that are needed in the same frame.
              TimeStamp nextUse = curTile.usages.ceilingKey(time);
              if (nextUse == null || nextUse.compareTo(maxNextUseTime) > 0) {
                maxNextUseTime = nextUse;
                maxNextUseVramBank = vramBank;
                maxNextUseAddress = address;
                if (maxNextUseTime == null)
                  break searchMaxNextUse;
              }
            }
          }
        }
        if (maxNextUseVramBank == -1) {
          searchMaxNextUseDesperate: for (int vramBank = 0; vramBank < 2; vramBank++) {
            for (int address = (tile.usedForBackground ? 0x80 : 0); address < (tile.usedForSprites ? 0x100 : 0x180); address++) {
              TileEntry curTile = tileHistory[vramBank][address].getValueAt(time, null);
              TimeStamp nextUse = curTile.usages.ceilingKey(time);
              if (nextUse == null || nextUse.compareTo(maxNextUseTime) > 0) {
                maxNextUseTime = nextUse;
                maxNextUseVramBank = vramBank;
                maxNextUseAddress = address;
                if (maxNextUseTime == null)
                  break searchMaxNextUseDesperate;
              }
            }
          }
        }
        if (maxNextUseVramBank == -1)
          throw new RuntimeException("no possible tile location found!");
        tileHistory[maxNextUseVramBank][maxNextUseAddress].add(time, tile);
        tile.usages.put(time, maxNextUseVramBank * 0x180 + maxNextUseAddress);
      }
    }
    
    outputTileStats();
    return this;
  }

  private void outputTileStats() {
    for (int ram = 0; ram < 2; ram++) {
      for (int y = 0; y < 0x18; y++) {
        for (int x = 0; x < 0x10; x++) {
          System.out.print(" " + tileHistory[ram][y * 0x10 + x].getSize());
        }
        System.out.println();
      }
      System.out.println();
    }
  }

  public StateMap calculateBgPalettePositions() {
    for (int address = 0; address < 8; address++)
      bgPaletteHistory[address] = new History<>();

    TreeMap<TimeStamp, ArrayList<PaletteEntry>> usageMap = paletteRegistry.getUsageMap();
    System.out.println("BgPalette usageMap size " + usageMap.size());
    for (Entry<TimeStamp, ArrayList<PaletteEntry>> e : usageMap.entrySet()) {
      TimeStamp time = e.getKey();
      for (PaletteEntry palette : e.getValue()) {
        TimeStamp maxNextUseTime = time;
        int maxNextUseAddress = -1;
        for (int address = 0; address < 8; address++) {
          if (bgPaletteHistory[address].getValueAt(time, null) == palette) {
            maxNextUseTime = null;
            maxNextUseAddress = address;
            break;
          }
        }
        if (maxNextUseAddress == -1) {
          for (int address = 0; address < 8; address++) {
            if (bgPaletteHistory[address].getValueAt(time, null) == null) {
              maxNextUseTime = null;
              maxNextUseAddress = address;
              break;
            }
          }
        }
        if (maxNextUseAddress == -1) {
          for (int address = 0; address < 8; address++) {
            PaletteEntry curPalette = bgPaletteHistory[address].getValueAt(time, null);
            TimeStamp previousUse = curPalette.usages.floorKey(time);
            if (previousUse.scene == time.scene && previousUse.frame == time.frame)
              continue; // Don't re-use palettes that are needed in the same frame.
            TimeStamp nextUse = curPalette.usages.ceilingKey(time);
            if (nextUse == null || nextUse.compareTo(maxNextUseTime) > 0) {
              maxNextUseTime = nextUse;
              maxNextUseAddress = address;
              if (maxNextUseTime == null)
                break;
            }
          }
        }
        if (maxNextUseAddress == -1) {
          for (int address = 0; address < 8; address++) {
            PaletteEntry curPalette = bgPaletteHistory[address].getValueAt(time, null);
            TimeStamp nextUse = curPalette.usages.ceilingKey(time);
            if (nextUse == null || nextUse.compareTo(maxNextUseTime) > 0) {
              maxNextUseTime = nextUse;
              maxNextUseAddress = address;
              if (maxNextUseTime == null)
                break;
            }
          }
        }
        if (maxNextUseAddress == -1)
          throw new RuntimeException("no possible bg palette location found!");
        bgPaletteHistory[maxNextUseAddress].add(time, palette);
        palette.usages.put(time, maxNextUseAddress);
      }
    }
    
    outputBgPaletteStats();
    return this;
  }

  private void outputBgPaletteStats() {
    for (int x = 0; x < 8; x++) {
      System.out.print(" " + bgPaletteHistory[x].getSize());
    }
    System.out.println();
  }
  
  public ArrayList<TimedAction> generateActionList() {
    ArrayList<TimedAction> actions = new ArrayList<>();
    
    addToActions(lcdc, Entry::getValue, HRAM, GbConstants.LCDC, actions);
    addToActions(wy, Entry::getValue, HRAM, GbConstants.WY, actions);
    addToActions(wx, Entry::getValue, HRAM, GbConstants.WX, actions);
    addToActions(scy, Entry::getValue, HRAM, GbConstants.SCY, actions);
    addToActions(scx, Entry::getValue, HRAM, GbConstants.SCX, actions);
    
    // BG map
    for (int address = 0; address < 0x800; address++) {
      addToActions(bgMaps[address], e -> (e.getValue().tile.usages.get(e.getKey()) % 0x180) & 0xff, WRAM, address, actions);
      addToActions(bgMaps[address], e -> e.getValue().palette.usages.get(e.getKey())
          | ((e.getValue().tile.usages.get(e.getKey()) >= 0x180 ? 1 : 0) << 3)
          | ((e.getValue().flipHorizontally ? 1 : 0) << 5)
          | ((e.getValue().flipVertically ? 1 : 0) << 6)
          | ((e.getValue().bgToOamPriority ? 1 : 0) << 7), WRAM, 0x800 + address, actions);
    }
    
    // Tiles
    for (int address = 0; address < 0x300; address++) {
      addToActions(tileHistory[address / 0x180][address % 0x180], e -> e.getValue().tile, TILE, address, actions);
    }
    
    // BG Palettes
    for (int address = 0; address < 8; address++) {
      addToActions(bgPaletteHistory[address], e -> e.getValue().palette, BGPALETTE, address, actions);
    }
    
    outputActionStats(actions);

    return actions;
  }

  private void outputActionStats(ArrayList<TimedAction> actions) {
    System.out.println("# of Actions: " + actions.size());
    int nonStartingActions = 0;
    for (TimedAction action : actions) {
      if (action.from != -1)
        nonStartingActions++;
    }
    System.out.println("# of non-starting actions: " + nonStartingActions);
  }

  private <T, R> void addToActions(History<T> history, Function<Entry<TimeStamp, T>, R> toValue, Type type, int address, ArrayList<TimedAction> actions) {
    R lastNonNullValue = null;
    int lastNonNullScene = 0;
    long lastNonNullCycles = -1;
    for (Entry<TimeStamp, T> e : history.getOperations().entrySet()) {
      if (e.getValue() == null)
        continue;
      R value = toValue.apply(e);

      // If this action spans scenes, put it into the LCD off period before the scene.
      if (e.getKey().scene > lastNonNullScene) {
        lastNonNullScene = e.getKey().scene;
        lastNonNullCycles = -1;
      }

      if (!Objects.equals(lastNonNullValue, value))
        actions.add(new TimedAction(new Action<R>(type, value, address), lastNonNullScene, lastNonNullCycles, toCycles(e.getKey())));
      lastNonNullCycles = toCycles(e.getKey());
      lastNonNullValue = value;
    }
  }
  
  private static long toCycles(TimeStamp time) {
    return (long)time.frame * GbConstants.FRAME_CYCLES + time.frameCycle;
  }
}
