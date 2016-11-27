package mrwint.gbtasgen.tools.playback.loganalyzer.state;

import static mrwint.gbtasgen.tools.playback.loganalyzer.TimedAction.Action.Type.BGPALETTE;
import static mrwint.gbtasgen.tools.playback.loganalyzer.TimedAction.Action.Type.OBJPALETTE;
import static mrwint.gbtasgen.tools.playback.loganalyzer.TimedAction.Action.Type.HRAM;
import static mrwint.gbtasgen.tools.playback.loganalyzer.TimedAction.Action.Type.TILE;
import static mrwint.gbtasgen.tools.playback.loganalyzer.TimedAction.Action.Type.WRAM;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.function.BiFunction;

import mrwint.gbtasgen.tools.playback.loganalyzer.GbConstants;
import mrwint.gbtasgen.tools.playback.loganalyzer.PaletteRegistry;
import mrwint.gbtasgen.tools.playback.loganalyzer.PaletteRegistry.PaletteEntry;
import mrwint.gbtasgen.tools.playback.loganalyzer.TileRegistry;
import mrwint.gbtasgen.tools.playback.loganalyzer.TileRegistry.TileEntry;
import mrwint.gbtasgen.tools.playback.loganalyzer.TimeStamp;
import mrwint.gbtasgen.tools.playback.loganalyzer.TimedAction;
import mrwint.gbtasgen.tools.playback.loganalyzer.TimedAction.Action;
import mrwint.gbtasgen.tools.playback.loganalyzer.TimedAction.Action.Type;
import mrwint.gbtasgen.tools.playback.loganalyzer.accessibility.Accessibility;
import mrwint.gbtasgen.tools.playback.loganalyzer.accessibility.AccessibilityGbState;
import mrwint.gbtasgen.tools.playback.loganalyzer.state.SpriteStateMap.SpriteStateRange;

public class StateMap {
  final TileRegistry tileRegistry = new TileRegistry();
  final PaletteRegistry bgPaletteRegistry = new PaletteRegistry();
  final PaletteRegistry objPaletteRegistry = new PaletteRegistry();
  final HashMap<BgMapState, BgMapState> bgMapStateRegistry = new HashMap<>();
  final HashMap<SpriteState, SpriteState> spriteStateRegistry = new HashMap<>();


  private static class SceneAccessibilityState implements AccessibilityGbState {
    private StateHistory<Long, Integer> numSprites = StateHistory.withLongIndex(StateHistory.getIdentityMerger());
    private StateHistory<Long, Integer> scx = StateHistory.withLongIndex(StateHistory.getIdentityMerger());
    private StateHistory<Long, Boolean> winEnabled = StateHistory.withLongIndex(StateHistory.getIdentityMerger());
    
    @Override
    public int getNumSprites(long cycle) {
      Integer result = numSprites.getValueAt(cycle);
      return result == null ? 0 : result;
    }
    @Override
    public int getScx(long cycle) {
      Integer result = scx.getValueAt(cycle);
      return result == null ? 0 : result;
    }
    @Override
    public boolean getWinEnabled(long cycle) {
      Boolean result = winEnabled.getValueAt(cycle);
      return result == null ? false : result;
    }
  }

  public static class TimestampedSpriteStateRange {
    final SpriteState sprite;
    final TimeStamp notBeforeOrAt;
    final TimeStamp from;
    final TimeStamp to;
    final TimeStamp notAtOrAfter;
    
    public TimestampedSpriteStateRange(SpriteState sprite, TimeStamp notBeforeOrAt, TimeStamp from, TimeStamp to, TimeStamp notAtOrAfter) {
      this.sprite = sprite;
      this.notBeforeOrAt = notBeforeOrAt;
      this.from = from;
      this.to= to;
      this.notAtOrAfter = notAtOrAfter;
    }
    
    @Override
    public String toString() {
      return "" + sprite + " notBeforeOrAt: " + notBeforeOrAt + " from: " + from + " to: " + to + " notAtOrAfter: " + notAtOrAfter;
    }
  }
  
  private int numScenes = 0;
  private ArrayList<SceneAccessibilityState> sceneAccessibilityStates = new ArrayList<>();
  private StateHistory<TimeStamp, LcdcState> lcdc = StateHistory.withTimestampIndex(LcdcState.MERGER); // 78 - 376
  private StateHistory<TimeStamp, WyState> wy = StateHistory.withTimestampIndex(WyState.MERGER); // 74 - 376
  private StateHistory<TimeStamp, Integer> wx = StateHistory.withTimestampIndex(StateHistory.getIdentityMerger()); // 78 - 376
  private StateHistory<TimeStamp, Integer> scy = StateHistory.withTimestampIndex(StateHistory.getIdentityMerger()); // 78 - 376
  private StateHistory<TimeStamp, Integer> scx = StateHistory.withTimestampIndex(StateHistory.getIdentityMerger()); // 78 - 376
  @SuppressWarnings("unchecked")
  private StateHistory<TimeStamp, BgMapState>[] bgMaps = new StateHistory[0x800];
  private ArrayList<TimestampedSpriteStateRange> sprites = new ArrayList<>();

  public void assembleScene(BackgroundStateMap[] bgStates, SpriteStateMap[] spriteStates) {
    SceneAccessibilityState sceneAccessibility = new SceneAccessibilityState();
    sceneAccessibilityStates.add(sceneAccessibility);
    int scene = numScenes++;
    int numFrames = 0;
    for (BackgroundStateMap bgState : bgStates)
      numFrames = Math.max(numFrames, bgState.frameOffset + bgState.numFrames);
    for (SpriteStateMap spriteState : spriteStates)
      numFrames = Math.max(numFrames, spriteState.frameOffset + spriteState.numFrames);

    // Determine accessibility
    for (int frame = 0; frame < numFrames; frame++) {
      if (frame % 100 == 0)
        System.out.println("assembling frame " + frame + ": determining accessibility");
      for (int scanLine = 0; scanLine < 144; scanLine++) {
        int frameCycle = scanLine * GbConstants.LINE_CYCLES;
        long cycle = frame * (long)GbConstants.FRAME_CYCLES + frameCycle;
        long lineIndex = frame * GbConstants.FRAME_LINES + scanLine;

        int numActiveSprites = 0;
        for (SpriteStateMap spriteState : spriteStates)
          for (SpriteStateRange sprite : spriteState.sprites)
            if (lineIndex >= sprite.from && lineIndex <= sprite.to && scanLine >= sprite.sprite.y - 16 && scanLine < sprite.sprite.y - (sprite.sprite.lowerTile != null ? 0 : 8))
              numActiveSprites++;
        sceneAccessibility.numSprites.addRange(cycle, cycle + GbConstants.LINE_CYCLES, Math.min(10, numActiveSprites));
        WyState wy = null;
        for (BackgroundStateMap bgState : bgStates)
          wy = WyState.MERGER.applyChecked(wy, bgState.wy.getValueAt(lineIndex));
        sceneAccessibility.winEnabled.addRange(cycle, cycle + GbConstants.LINE_CYCLES, wy != null && !wy.moreThan && wy.wy <= scanLine);
        Integer scx = null;
        for (BackgroundStateMap bgState : bgStates)
          scx = StateHistory.<Integer>getIdentityMerger().applyChecked(scx, bgState.scx.getValueAt(lineIndex));
        sceneAccessibility.scx.addRange(cycle, cycle + GbConstants.LINE_CYCLES, scx == null ? 0 : scx & 0x7);
      }
    }

    // Add BG states
    for (int frame = 0; frame < numFrames; frame++) {
      if (frame % 100 == 0)
        System.out.println("assembling frame " + frame + ": add background");
      for (int scanLine = 0; scanLine < 144; scanLine++) {
        int frameCycle = scanLine * GbConstants.LINE_CYCLES;
        long cycle = frame * (long)GbConstants.FRAME_CYCLES + frameCycle;
        long lineIndex = frame * GbConstants.FRAME_LINES + scanLine;
        int m0TimeOfLine = Accessibility.getM0TimeOfLine(cycle, sceneAccessibility);

        TimeStamp bgFromTimestamp = new TimeStamp(scene, frame, frameCycle + 78 * GbConstants.DOUBLE_SPEED_FACTOR);
        TimeStamp bgWyFromTimestamp = new TimeStamp(scene, frame, frameCycle + 74 * GbConstants.DOUBLE_SPEED_FACTOR);
        TimeStamp bgToTimestamp = new TimeStamp(scene, frame, frameCycle + m0TimeOfLine);
        for (BackgroundStateMap bgState : bgStates) {
          this.lcdc.addRange(bgFromTimestamp, bgToTimestamp, bgState.lcdc.getValueAt(lineIndex));
          this.wy.addRange(bgWyFromTimestamp, bgToTimestamp, bgState.wy.getValueAt(lineIndex));
          this.wx.addRange(bgFromTimestamp, bgToTimestamp, bgState.wx.getValueAt(lineIndex));
          this.scy.addRange(bgFromTimestamp, bgToTimestamp, bgState.scy.getValueAt(lineIndex));
          this.scx.addRange(bgFromTimestamp, bgToTimestamp, bgState.scx.getValueAt(lineIndex));
          for (int i = 0; i < 0x800; i++) {
            if (bgState.bgMaps[i] != null) {
              if (this.bgMaps[i] == null)
                this.bgMaps[i] = StateHistory.withTimestampIndex(StateHistory.getIdentityMerger());
              BgMapState bgMap = bgState.bgMaps[i].getValueAt(lineIndex);
              if (bgMap != null) {
                this.bgMaps[i].addRange(bgFromTimestamp, bgToTimestamp, bgMap);
                bgMap.tile.usages.addRange(bgFromTimestamp, bgToTimestamp, -1);
                bgMap.palette.usages.addRange(bgFromTimestamp, bgToTimestamp, -1);
              }
            }
          }
        }
      }
    }
    
    // Merge Sprites
    for (int frame = 0; frame < numFrames; frame++) {
      if (frame % 100 == 0)
        System.out.println("assembling frame " + frame + ": add sprite LCDC");
      for (int scanLine = 0; scanLine < 144; scanLine++) {
        int frameCycle = scanLine * GbConstants.LINE_CYCLES;
        long cycle = frame * (long)GbConstants.FRAME_CYCLES + frameCycle;
        long lineIndex = frame * GbConstants.FRAME_LINES + scanLine;
        int m0TimeOfLine = Accessibility.getM0TimeOfLine(cycle, sceneAccessibility);
        TimeStamp spriteLcdcFromTimestamp = new TimeStamp(scene, frame, frameCycle + 78 * GbConstants.DOUBLE_SPEED_FACTOR);
        TimeStamp spriteLcdcToTimestamp = new TimeStamp(scene, frame, frameCycle + m0TimeOfLine);

        for (SpriteStateMap spriteState : spriteStates) {
          this.lcdc.addRange(spriteLcdcFromTimestamp, spriteLcdcToTimestamp, spriteState.lcdc.getValueAt(lineIndex));
        }
      }
    }
    int numSprites = 0;
    for (SpriteStateMap spriteState : spriteStates) {
      for (SpriteStateRange sprite : spriteState.sprites) {
        if (++numSprites % 1000 == 0)
          System.out.println("assembling sprite " + numSprites);

        int notBeforeOrAtM0Time = Accessibility.getM0TimeOfLine((spriteState.frameOffset * GbConstants.FRAME_LINES + sprite.notBeforeOrAt) * GbConstants.LINE_CYCLES, sceneAccessibility);
        int toM0Time = Accessibility.getM0TimeOfLine((spriteState.frameOffset * GbConstants.FRAME_LINES + sprite.to) * GbConstants.LINE_CYCLES, sceneAccessibility);
        TimeStamp notBeforeOrAt = new TimeStamp(
            scene,
            (int)(spriteState.frameOffset + sprite.notBeforeOrAt / GbConstants.FRAME_LINES),
            (int)(sprite.notBeforeOrAt % GbConstants.FRAME_LINES) * GbConstants.LINE_CYCLES + notBeforeOrAtM0Time);
        TimeStamp from = new TimeStamp(
            scene,
            (int)(spriteState.frameOffset + sprite.from / GbConstants.FRAME_LINES),
            (int)(sprite.from % GbConstants.FRAME_LINES) * GbConstants.LINE_CYCLES + 78 * GbConstants.DOUBLE_SPEED_FACTOR);
        TimeStamp to = new TimeStamp(
            scene,
            (int)(spriteState.frameOffset + sprite.to / GbConstants.FRAME_LINES),
            (int)(sprite.to % GbConstants.FRAME_LINES) * GbConstants.LINE_CYCLES + toM0Time);
        TimeStamp notAtOrAfter = new TimeStamp(
            scene,
            (int)(spriteState.frameOffset + sprite.notAtOrAfter / GbConstants.FRAME_LINES),
            (int)(sprite.notAtOrAfter % GbConstants.FRAME_LINES) * GbConstants.LINE_CYCLES + 78 * GbConstants.DOUBLE_SPEED_FACTOR);
        sprites.add(new TimestampedSpriteStateRange(sprite.sprite, notBeforeOrAt, from, to, notAtOrAfter));
        
        sprite.sprite.upperTile.usages.addRange(from, to, -1);
        if (sprite.sprite.lowerTile != null)
          sprite.sprite.lowerTile.usages.addRange(from, to, -1);
        sprite.sprite.palette.usages.addRange(from, to, -1);
      }
    }

    outputAssemblyStats();
  }
  
  private void outputAssemblyStats() {
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
      int size = bgMaps[i] == null ? 0 : bgMaps[i].getSize();
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
    System.out.println("known tiles usage size: " + tileRegistry.getUsageSize());
    System.out.println("known palettes: " + bgPaletteRegistry.getSize());
    System.out.println("known palettes usage size: " + bgPaletteRegistry.getUsageSize());
    System.out.println("num sprites: " + sprites.size());
    System.out.println();

    for (int y = 0; y < 32; y++) {
      for (int x = 0; x < 32; x++) {
        System.out.print(" " + (bgMaps[y * 32 + x] == null ? "-" : bgMaps[y * 32 + x].getSize()));
      }
      System.out.println();
    }
    System.out.println();
    
    for (int y = 0; y < 32; y++) {
      for (int x = 0; x < 32; x++) {
        System.out.print(" " + (bgMaps[0x400 + y * 32 + x] == null ? "-" : bgMaps[0x400 + y * 32 + x].getSize()));
      }
      System.out.println();
    }
    System.out.println();
  }
 
  

  @SuppressWarnings("unchecked")
  private StateHistory<TimeStamp, TileEntry>[][] tileHistory = new StateHistory[2][0x180];
  int numTileOverrides;

  public StateMap calculateTilePositions() {
    for (int vramBank = 0; vramBank < 2; vramBank++) 
      for (int address = 0; address < 0x180; address++)
        tileHistory[vramBank][address] = StateHistory.withTimestampIndex(StateHistory.getIdentityMerger());

    TreeMap<TimeStamp, ArrayList<TileEntry>> usageEventMap = tileRegistry.getUsageEventMap();
    System.out.println("usageEventMap size " + usageEventMap.size());
    for (Entry<TimeStamp, ArrayList<TileEntry>> e : usageEventMap.entrySet()) {
      TimeStamp time = e.getKey();
      for (TileEntry tile : e.getValue()) {
        if (tile.usedForSpriteLower) // handled by upper sprite instead.
          continue;
        int addressIncrement = (tile.usedForBackground ? -1 : 1) * (tile.pairedWith != null ? 2 : 1);
        int startAddress = tile.usedForBackground ? (tile.usedForSpriteAny ? 0x100 : 0x180) + addressIncrement : 0;
        int endAddress = tile.usedForBackground ? 0x80 + addressIncrement : 0x100;
        
        TimeStamp maxNextUseTime = time;
        int maxNextUseVramBank = -1;
        int maxNextUseAddress = 0;
        searchHit: for (int vramBank = 0; vramBank < 2; vramBank++) {
          for (int address = startAddress; address != endAddress; address += addressIncrement) {
            if (tileHistory[vramBank][address].getLastNonNullValueAt(time) == tile) {
              maxNextUseTime = null;
              maxNextUseVramBank = vramBank;
              maxNextUseAddress = address;
              break searchHit;
            }
          }
        }
        if (maxNextUseVramBank == -1) {
          searchEmpty: for (int vramBank = 0; vramBank < 2; vramBank++) {
            for (int address = startAddress; address != endAddress; address += addressIncrement) {
              if (tileHistory[vramBank][address].getLastNonNullValueAt(time) == null) {
                if (tile.pairedWith == null || tileHistory[vramBank][address+1].getLastNonNullValueAt(time) == null) {
                  maxNextUseTime = null;
                  maxNextUseVramBank = vramBank;
                  maxNextUseAddress = address;
                  break searchEmpty;
                }
              }
            }
          }
        }
        for (int desperation = 0; desperation < 2; desperation++) {
          if (maxNextUseVramBank == -1) {
            numTileOverrides++;
            searchMaxNextUse: for (int vramBank = 0; vramBank < 2; vramBank++) {
              for (int address = startAddress; address != endAddress; address += addressIncrement) {
                if (tileHistory[vramBank][address].getValueAt(time) != null) // currently used
                  continue;
                if (tile.pairedWith != null && tileHistory[vramBank][address+1].getValueAt(time) != null) // currently used
                  continue;
  
                if (desperation == 0) {
                  TimeStamp freeSince = tileHistory[vramBank][address].getCurrentStateStartTime(time);
                  if (tile.pairedWith != null)
                    freeSince = max(freeSince, tileHistory[vramBank][address+1].getCurrentStateStartTime(time));
                  if (freeSince.scene == time.scene && freeSince.frame == time.frame)
                    continue; // Don't re-use tiles that are needed in the same frame if not desperate.
                }
  
                TimeStamp nextUse = null;
                TileEntry curTile = tileHistory[vramBank][address].getLastNonNullValueAt(time);
                if (curTile != null) {
                  if (curTile.usages.getValueAt(time) != null)
                    throw new RuntimeException("tile " + curTile + " used at " + time + " but tileHistory " + tileHistory[vramBank][address] + " free");
                  nextUse = min(nextUse, curTile.usages.getNextStateStartTime(time));
                }
                if (tile.pairedWith != null) {
                  curTile = tileHistory[vramBank][address+1].getLastNonNullValueAt(time);
                  if (curTile != null) {
                    if (curTile.usages.getValueAt(time) != null)
                      throw new RuntimeException("tile " + curTile + " used at " + time + " but tileHistory " + tileHistory[vramBank][address+1] + " free");
                    nextUse = min(nextUse, curTile.usages.getNextStateStartTime(time));
                  }
                }
  
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
        }
        if (maxNextUseVramBank == -1)
          throw new RuntimeException("no possible tile location found for tile " + tile);
        
        TimeStamp endTime = tile.usages.getNextStateStartTime(time);
        tileHistory[maxNextUseVramBank][maxNextUseAddress].addRange(time, endTime, tile);
        tile.usages.updateEventValue(time, maxNextUseVramBank * 0x180 + maxNextUseAddress);
        if (tile.pairedWith != null) {
          if (!tile.pairedWith.usages.getNextStateStartTime(time).equals(endTime))
            throw new RuntimeException("end time " + endTime + " does not match " + tile.pairedWith.usages.getNextStateStartTime(time));
          tileHistory[maxNextUseVramBank][maxNextUseAddress+1].addRange(time, endTime, tile.pairedWith);
          tile.pairedWith.usages.updateEventValue(time, maxNextUseVramBank * 0x180 + maxNextUseAddress+1);
        }
      }
    }
    
    outputTileStats();
    return this;
  }

  public static <T extends Comparable<? super T>> T max(T a, T b) {
    if (a == null) return b;
    if (b == null) return a;
    return a.compareTo(b) > 0 ? a : b;
  }
  public static <T extends Comparable<? super T>> T min(T a, T b) {
    if (a == null) return b;
    if (b == null) return a;
    return a.compareTo(b) > 0 ? b : a;
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
    System.out.println("num tile overrides: " + numTileOverrides);
  }

  @SuppressWarnings("unchecked")
  private StateHistory<TimeStamp, PaletteEntry>[] bgPaletteHistory = new StateHistory[8];
  @SuppressWarnings("unchecked")
  private StateHistory<TimeStamp, PaletteEntry>[] objPaletteHistory = new StateHistory[8];
  int numPaletteOverrides;
  
  public StateMap calculateBgPalettePositions() {
    return calculatePalettePositions(bgPaletteHistory, bgPaletteRegistry);
  }
  public StateMap calculateObjPalettePositions() {
    return calculatePalettePositions(objPaletteHistory, objPaletteRegistry);
  }
  private StateMap calculatePalettePositions(StateHistory<TimeStamp, PaletteEntry>[] paletteHistory, PaletteRegistry paletteRegistry) {
    for (int address = 0; address < 8; address++)
      paletteHistory[address] = StateHistory.withTimestampIndex(StateHistory.getIdentityMerger());

    TreeMap<TimeStamp, ArrayList<PaletteEntry>> usageEventMap = paletteRegistry.getUsageEventMap();
    System.out.println("Palette usageEventMap size " + usageEventMap.size());
    for (Entry<TimeStamp, ArrayList<PaletteEntry>> e : usageEventMap.entrySet()) {
      TimeStamp time = e.getKey();
      for (PaletteEntry palette : e.getValue()) {
        TimeStamp maxNextUseTime = time;
        int maxNextUseAddress = -1;
        for (int address = 0; address < 8; address++) {
          if (paletteHistory[address].getLastNonNullValueAt(time) == palette) {
            maxNextUseTime = null;
            maxNextUseAddress = address;
            break;
          }
        }
        if (maxNextUseAddress == -1) {
          for (int address = 0; address < 8; address++) {
            if (paletteHistory[address].getLastNonNullValueAt(time) == null) {
              maxNextUseTime = null;
              maxNextUseAddress = address;
              break;
            }
          }
        }
        if (maxNextUseAddress == -1) {
          numPaletteOverrides++;
          for (int address = 0; address < 8; address++) {
            if (paletteHistory[address].getValueAt(time) != null) // currently used
              continue;
            TimeStamp freeSince = paletteHistory[address].getCurrentStateStartTime(time);
            if (freeSince.scene == time.scene && freeSince.frame == time.frame)
              continue; // Don't re-use palettes that are needed in the same frame.
            PaletteEntry curPalette = paletteHistory[address].getLastNonNullValueAt(time);
            if (curPalette.usages.getValueAt(time) != null)
              throw new RuntimeException("palette " + curPalette + " used at " + time + " but paletteHistory " + paletteHistory[address] + " free");
            TimeStamp nextUse = curPalette.usages.getNextStateStartTime(time);
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
            if (paletteHistory[address].getValueAt(time) != null) // currently used
              continue;
            PaletteEntry curPalette = paletteHistory[address].getLastNonNullValueAt(time);
            if (curPalette.usages.getValueAt(time) != null)
              throw new RuntimeException("palette " + curPalette + " used at " + time + " but paletteHistory " + paletteHistory[address] + " free");
            TimeStamp nextUse = curPalette.usages.getNextStateStartTime(time);
            if (nextUse == null || nextUse.compareTo(maxNextUseTime) > 0) {
              maxNextUseTime = nextUse;
              maxNextUseAddress = address;
              if (maxNextUseTime == null)
                break;
            }
          }
        }
        if (maxNextUseAddress == -1)
          throw new RuntimeException("no possible palette location found!");

        TimeStamp endTime = palette.usages.getNextStateStartTime(time);
        paletteHistory[maxNextUseAddress].addRange(time, endTime, palette);
        palette.usages.updateEventValue(time, maxNextUseAddress);
      }
    }
    
    outputPaletteStats(paletteHistory);
    return this;
  }

  private void outputPaletteStats(StateHistory<TimeStamp, PaletteEntry>[] paletteHistory) {
    for (int x = 0; x < 8; x++) {
      System.out.print(" " + paletteHistory[x].getSize());
    }
    System.out.println();
    System.out.println("num palette overrides: " + numPaletteOverrides);
  }
  
  public void compressStates() {
    StateHistory.compressTimestampHistory(lcdc, true);
    StateHistory.compressTimestampHistory(wy);
    StateHistory.compressTimestampHistory(wx);
    StateHistory.compressTimestampHistory(scy);
    StateHistory.compressTimestampHistory(scx);
    for (int i = 0; i < 0x800; i++)
      if (bgMaps[i] != null)
        StateHistory.compressTimestampHistory(bgMaps[i]);
    for (int vramBank = 0; vramBank < 2; vramBank++) 
      for (int address = 0; address < 0x180; address++)
        StateHistory.compressTimestampHistory(tileHistory[vramBank][address]);
    for (int address = 0; address < 8; address++)
      StateHistory.compressTimestampHistory(bgPaletteHistory[address]);
    for (int address = 0; address < 8; address++)
      StateHistory.compressTimestampHistory(objPaletteHistory[address]);
    
    outputAssemblyStats();
    outputTileStats();
    outputPaletteStats(bgPaletteHistory);
    outputPaletteStats(objPaletteHistory);
  }
  
  public ArrayList<TimedAction> generateActionList() {
    ArrayList<TimedAction> actions = new ArrayList<>();
    
    addToActions(lcdc, (t, v) -> v.toValue(), HRAM, GbConstants.LCDC, actions);
    addToActions(wy, (t, v) -> v.toValue(), HRAM, GbConstants.WY, actions);
    addToActions(wx, (t, v) -> v, HRAM, GbConstants.WX, actions);
    addToActions(scy, (t, v) -> v, HRAM, GbConstants.SCY, actions);
    addToActions(scx, (t, v) -> v, HRAM, GbConstants.SCX, actions);
    
    // BG map
    for (int address = 0; address < 0x800; address++) {
      if (bgMaps[address] != null) {
        addToActions(bgMaps[address], (t, v) -> (v.tile.usages.getValueAt(t) % 0x180) & 0xff, WRAM, address, actions);
        addToActions(bgMaps[address], (t, v) -> v.palette.usages.getValueAt(t)
            | ((v.tile.usages.getValueAt(t) >= 0x180 ? 1 : 0) << 3)
            | ((v.flipHorizontally ? 1 : 0) << 5)
            | ((v.flipVertically ? 1 : 0) << 6)
            | ((v.bgToOamPriority ? 1 : 0) << 7), WRAM, 0x800 + address, actions);
      }
    }
    
    // Tiles
    for (int address = 0; address < 0x300; address++) {
      addToActions(tileHistory[address / 0x180][address % 0x180], (t, v) -> v.tile, TILE, address, actions);
    }
    
    // BG Palettes
    for (int address = 0; address < 8; address++) {
      addToActions(bgPaletteHistory[address], (t, v) -> v.palette, BGPALETTE, address, actions);
    }
    
    // Obj Palettes
    for (int address = 0; address < 8; address++) {
      addToActions(objPaletteHistory[address], (t, v) -> v.palette, OBJPALETTE, address, actions);
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

  private <T, R> void addToActions(StateHistory<TimeStamp, T> history, BiFunction<TimeStamp, T, R> toActionValue, Type type, int address, ArrayList<TimedAction> actions) {
    TimeStamp curTimestamp = TimeStamp.MIN_VALUE;
    while (true) {
      T curValue = history.getValueAt(curTimestamp);
      if (curValue != null)
        throw new RuntimeException("unexpected non-null value " + curValue + " at " + curTimestamp + " in " + history);
      TimeStamp nextTimestamp = history.getNextStateStartTime(curTimestamp);
      if (nextTimestamp == null) // reached end
        break;
      T nextValue = history.getValueAt(nextTimestamp);
      if (nextValue == null)
        throw new RuntimeException("unexpected null value at " + nextTimestamp + " in " + history);
      // If this action spans scenes, put it into the LCD off period before the scene.
      if (nextTimestamp.scene > curTimestamp.scene)
        curTimestamp = new TimeStamp(nextTimestamp.scene, 0, 0);
      long fromCycles = toCycles(curTimestamp);
      actions.add(new TimedAction(new Action<R>(type, toActionValue.apply(nextTimestamp, nextValue), address), curTimestamp.scene, fromCycles, toCycles(nextTimestamp)));
      curTimestamp = history.getNextStateStartTime(nextTimestamp);
      if (curTimestamp == null) // reached end
        throw new RuntimeException("unexpected end of history after " + nextTimestamp + " value " + nextValue);
    }
  }
  
  private static long toCycles(TimeStamp time) {
    return (long)time.frame * GbConstants.FRAME_CYCLES + time.frameCycle;
  }
}
