package mrwint.gbtasgen.tools.playback.loganalyzer.state;

import static mrwint.gbtasgen.tools.playback.loganalyzer.TimedAction.Action.Type.BGPALETTE;
import static mrwint.gbtasgen.tools.playback.loganalyzer.TimedAction.Action.Type.HRAM;
import static mrwint.gbtasgen.tools.playback.loganalyzer.TimedAction.Action.Type.OAM;
import static mrwint.gbtasgen.tools.playback.loganalyzer.TimedAction.Action.Type.OBJPALETTE;
import static mrwint.gbtasgen.tools.playback.loganalyzer.TimedAction.Action.Type.TILE;
import static mrwint.gbtasgen.tools.playback.loganalyzer.TimedAction.Action.Type.WRAM;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.function.Function;

import mrwint.gbtasgen.tools.playback.loganalyzer.GbConstants;
import mrwint.gbtasgen.tools.playback.loganalyzer.OamEntry;
import mrwint.gbtasgen.tools.playback.loganalyzer.Palette;
import mrwint.gbtasgen.tools.playback.loganalyzer.PaletteRegistry;
import mrwint.gbtasgen.tools.playback.loganalyzer.PaletteRegistry.PaletteEntry;
import mrwint.gbtasgen.tools.playback.loganalyzer.RawMemoryMap;
import mrwint.gbtasgen.tools.playback.loganalyzer.SceneDesc;
import mrwint.gbtasgen.tools.playback.loganalyzer.Tile;
import mrwint.gbtasgen.tools.playback.loganalyzer.TileRegistry;
import mrwint.gbtasgen.tools.playback.loganalyzer.TileRegistry.TileEntry;
import mrwint.gbtasgen.tools.playback.loganalyzer.TileRegistry.TileResult;
import mrwint.gbtasgen.tools.playback.loganalyzer.TimeStamp;
import mrwint.gbtasgen.tools.playback.loganalyzer.TimedAction;
import mrwint.gbtasgen.tools.playback.loganalyzer.TimedAction.Action;
import mrwint.gbtasgen.tools.playback.loganalyzer.TimedAction.Action.Type;
import mrwint.gbtasgen.tools.playback.loganalyzer.accessibility.Accessibility;
import mrwint.gbtasgen.tools.playback.loganalyzer.accessibility.AccessibilityGbState;
import mrwint.gbtasgen.tools.playback.loganalyzer.state.AudioStateMap.AudioChunk;
import mrwint.gbtasgen.tools.playback.loganalyzer.state.AudioStateMap.AudioOperation;
import mrwint.gbtasgen.tools.playback.loganalyzer.state.SpriteStateMap.SpriteStateRange;

public class StateMap {
  final TileRegistry tileRegistry = new TileRegistry();
  final PaletteRegistry bgPaletteRegistry = new PaletteRegistry();
  final PaletteRegistry objPaletteRegistry = new PaletteRegistry();
  final HashMap<BgMapState, BgMapState> bgMapStateRegistry = new HashMap<>();
  final HashMap<SpriteState, SpriteState> spriteStateRegistry = new HashMap<>();


  public static class SceneAccessibilityState implements AccessibilityGbState {
    private StateHistory<Long, Integer> numSprites = StateHistory.withLongIndex(StateHistory.getIdentityMerger());
    long lastNumSpritesCycle = Long.MIN_VALUE;
    int lastNumSpritesValue;
    private StateHistory<Long, Integer> scx = StateHistory.withLongIndex(StateHistory.getIdentityMerger());
    long lastScxCycle = Long.MIN_VALUE;
    int lastScxValue;
    private StateHistory<Long, Boolean> winEnabled = StateHistory.withLongIndex(StateHistory.getIdentityMerger());
    long lastWinEnabledCycle = Long.MIN_VALUE;
    boolean lastWinEnabledValue;
    
    @Override
    public int getNumSprites(long cycle) {
      if (cycle / GbConstants.LINE_CYCLES == lastNumSpritesCycle / GbConstants.LINE_CYCLES) return lastNumSpritesValue;
      Integer result = numSprites.getValueAt(cycle);
      lastNumSpritesCycle = cycle;
      return lastNumSpritesValue = result == null ? 0 : result;
    }
    @Override
    public int getScx(long cycle) {
      if (cycle / GbConstants.LINE_CYCLES == lastScxCycle / GbConstants.LINE_CYCLES) return lastScxValue;
      Integer result = scx.getValueAt(cycle);
      lastScxCycle = cycle;
      return lastScxValue = result == null ? 0 : result;
    }
    @Override
    public boolean getWinEnabled(long cycle) {
      if (cycle / GbConstants.LINE_CYCLES == lastWinEnabledCycle / GbConstants.LINE_CYCLES) return lastWinEnabledValue;
      Boolean result = winEnabled.getValueAt(cycle);
      lastWinEnabledCycle = cycle;
      return lastWinEnabledValue = result == null ? false : result;
    }
  }

  public static class TimestampedSpriteStateRange {
    final SpriteState sprite;
    final TimeStamp notBefore;
    final TimeStamp from;
    final TimeStamp to;
    final TimeStamp notAtOrAfter;
    
    public TimestampedSpriteStateRange(SpriteState sprite, TimeStamp notBeforeOrAt, TimeStamp from, TimeStamp to, TimeStamp notAtOrAfter) {
      this.sprite = sprite;
      this.notBefore = notBeforeOrAt;
      this.from = from;
      this.to= to;
      this.notAtOrAfter = notAtOrAfter;
    }
    
    @Override
    public String toString() {
      return "" + sprite + " notBeforeOrAt: " + notBefore + " from: " + from + " to: " + to + " notAtOrAfter: " + notAtOrAfter;
    }

    public OamEntry toOamEntry() {
      return new OamEntry(new byte[] {
          (byte) sprite.y,
          (byte) sprite.x,
          (byte)(sprite.upperTile.usages.getValueAt(from) % 0x180),
          (byte) (sprite.palette.usages.getValueAt(from)
              | ((sprite.upperTile.usages.getValueAt(from) >= 0x180 ? 1 : 0) << 3)
              | ((sprite.flipHorizontally ? 1 : 0) << 5)
              | ((sprite.flipVertically ? 1 : 0) << 6)
              | ((sprite.objToBgPriority ? 1 : 0) << 7))
      });
    }
  }

  public static class TimestampedAudioChunk {
    public TimeStamp from;
    public TimeStamp to;
    public ArrayList<AudioOperation> operations;
  }

  public void assembleScene(RawMemoryMap memoryMap, SceneDesc... descs) {
    assembleScene(new RawMemoryMap[]{memoryMap}, descs);
  }
  public void assembleScene(RawMemoryMap[] memoryMaps, SceneDesc... descs) {
    ArrayList<BackgroundStateMap> tilesStates = new ArrayList<>();
    ArrayList<SpriteStateMap> spriteStates = new ArrayList<>();
    ArrayList<AudioStateMap> audioStates = new ArrayList<>();
    int frameOffset = 0;
    for (int i = 0; i < descs.length; i++) {
      if (!descs[i].noBackground) {
        BackgroundStateMap tilesState = new BackgroundStateMap(this, memoryMaps[descs[i].inputLog], descs[i]);
        tilesState.frameOffset = frameOffset;
        tilesStates.add(tilesState);
      }
      if (!descs[i].noSprites) {
        SpriteStateMap spriteState = new SpriteStateMap(this, memoryMaps[descs[i].inputLog], descs[i]);
        spriteState.frameOffset = frameOffset;
        spriteStates.add(spriteState);
      }
      if (!descs[i].noAudio) {
        AudioStateMap audioState = new AudioStateMap(this, memoryMaps[descs[i].inputLog], descs[i]);
        audioState.frameOffset = frameOffset;
        audioStates.add(audioState);
      }
      frameOffset += descs[i].numOutputFrames;
    }
    assembleScene(tilesStates.toArray(new BackgroundStateMap[0]), spriteStates.toArray(new SpriteStateMap[0]), audioStates.toArray(new AudioStateMap[0]));
  }

  private int numScenes = 0;
  private ArrayList<Integer> sceneFrames = new ArrayList<>();
  public ArrayList<SceneAccessibilityState> sceneAccessibilityStates = new ArrayList<>();
  private StateHistory<TimeStamp, LcdcState> lcdc = StateHistory.withTimestampIndex(LcdcState.MERGER); // 78 - 376
  private StateHistory<TimeStamp, WyState> wy = StateHistory.withTimestampIndex(WyState.MERGER); // 74 - 376
  private StateHistory<TimeStamp, Integer> wx = StateHistory.withTimestampIndex(StateHistory.getIdentityMerger()); // 78 - 376
  private StateHistory<TimeStamp, Integer> scy = StateHistory.withTimestampIndex(StateHistory.getIdentityMerger()); // 78 - 376
  private StateHistory<TimeStamp, Integer> scx = StateHistory.withTimestampIndex(StateHistory.getIdentityMerger()); // 78 - 376
  @SuppressWarnings("unchecked")
  private StateHistory<TimeStamp, BgMapState>[] bgMaps = new StateHistory[0x800];
  private ArrayList<TimestampedSpriteStateRange> sprites = new ArrayList<>();
  private ArrayList<TimestampedAudioChunk> audioChunks = new ArrayList<>();
  
  public void assembleScene(BackgroundStateMap[] bgStates, SpriteStateMap[] spriteStates, AudioStateMap[] audioStates) {
    int scene = numScenes++;
    int numFrames = 0;
    for (BackgroundStateMap bgState : bgStates)
      numFrames = Math.max(numFrames, bgState.frameOffset + bgState.numFrames);
    for (SpriteStateMap spriteState : spriteStates)
      numFrames = Math.max(numFrames, spriteState.frameOffset + spriteState.numFrames);
    sceneFrames.add(numFrames);
    if (sceneFrames.size() != numScenes)
      throw new RuntimeException(sceneFrames.size() + " != " + numScenes);
    SceneAccessibilityState sceneAccessibility = new SceneAccessibilityState();
    sceneAccessibilityStates.add(sceneAccessibility);
    if (sceneAccessibilityStates.size() != numScenes)
      throw new RuntimeException(sceneAccessibilityStates.size() + " != " + numScenes);

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
          if (frame >= spriteState.frameOffset && frame < spriteState.frameOffset + spriteState.numFrames) {
            long adjustedLineIndex = lineIndex - spriteState.frameOffset * GbConstants.FRAME_LINES;
            for (SpriteStateRange sprite : spriteState.sprites)
              if (adjustedLineIndex >= sprite.from && adjustedLineIndex <= sprite.to && scanLine >= sprite.sprite.y - 16 && scanLine < sprite.sprite.y - (sprite.sprite.lowerTile != null ? 0 : 8))
                numActiveSprites++;            
          }
        sceneAccessibility.numSprites.addRange(cycle, cycle + GbConstants.LINE_CYCLES, Math.min(10, numActiveSprites));
        WyState wy = null;
        for (BackgroundStateMap bgState : bgStates)
          if (frame >= bgState.frameOffset && frame < bgState.frameOffset + bgState.numFrames) {
            long adjustedLineIndex = lineIndex - bgState.frameOffset * GbConstants.FRAME_LINES;
            wy = WyState.MERGER.applyChecked(wy, bgState.wy.getValueAt(adjustedLineIndex));
          }
        sceneAccessibility.winEnabled.addRange(cycle, cycle + GbConstants.LINE_CYCLES, wy != null && !wy.moreThan && wy.wy <= scanLine);
        Integer scx = null;
        for (BackgroundStateMap bgState : bgStates)
          if (frame >= bgState.frameOffset && frame < bgState.frameOffset + bgState.numFrames) {
            long adjustedLineIndex = lineIndex - bgState.frameOffset * GbConstants.FRAME_LINES;
            scx = StateHistory.<Integer>getIdentityMerger().applyChecked(scx, bgState.scx.getValueAt(adjustedLineIndex));
          }
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
          if (frame >= bgState.frameOffset && frame < bgState.frameOffset + bgState.numFrames) {
            long adjustedLineIndex = lineIndex - bgState.frameOffset * GbConstants.FRAME_LINES;
            this.lcdc.addRange(bgFromTimestamp, bgToTimestamp, bgState.lcdc.getValueAt(adjustedLineIndex));
            this.wy.addRange(bgWyFromTimestamp, bgToTimestamp, bgState.wy.getValueAt(adjustedLineIndex));
            this.wx.addRange(bgFromTimestamp, bgToTimestamp, bgState.wx.getValueAt(adjustedLineIndex));
            this.scy.addRange(bgFromTimestamp, bgToTimestamp, bgState.scy.getValueAt(adjustedLineIndex));
            this.scx.addRange(bgFromTimestamp, bgToTimestamp, bgState.scx.getValueAt(adjustedLineIndex));
            for (int i = 0; i < 0x800; i++) {
              if (bgState.bgMaps[i] != null) {
                if (this.bgMaps[i] == null)
                  this.bgMaps[i] = StateHistory.withTimestampIndex(StateHistory.getIdentityMerger());
                BgMapState bgMap = bgState.bgMaps[i].getValueAt(adjustedLineIndex);
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
          if (frame >= spriteState.frameOffset && frame < spriteState.frameOffset + spriteState.numFrames) {
            long adjustedLineIndex = lineIndex - spriteState.frameOffset * GbConstants.FRAME_LINES;
            this.lcdc.addRange(spriteLcdcFromTimestamp, spriteLcdcToTimestamp, spriteState.lcdc.getValueAt(adjustedLineIndex));
          }
        }
      }
    }
    int numSprites = 0;
    for (SpriteStateMap spriteState : spriteStates) {
      for (SpriteStateRange sprite : spriteState.sprites) {
        if (++numSprites % 1000 == 0)
          System.out.println("assembling sprite " + numSprites);

        TimeStamp notBeforeOrAt;
        
        if (sprite.notBeforeOrAt < 0 && spriteState.frameOffset == 0) {
          notBeforeOrAt = new TimeStamp(scene, 0, 0);
        } else {
          int notBeforeOrAtM0Time = Accessibility.getM0TimeOfLine((spriteState.frameOffset * GbConstants.FRAME_LINES + sprite.notBeforeOrAt) * GbConstants.LINE_CYCLES, sceneAccessibility);
          notBeforeOrAt = new TimeStamp(
              scene,
              (int)(spriteState.frameOffset + sprite.notBeforeOrAt / GbConstants.FRAME_LINES),
              (int)(sprite.notBeforeOrAt % GbConstants.FRAME_LINES) * GbConstants.LINE_CYCLES + notBeforeOrAtM0Time);
        }
        TimeStamp from = new TimeStamp(
            scene,
            (int)(spriteState.frameOffset + sprite.from / GbConstants.FRAME_LINES),
            (int)(sprite.from % GbConstants.FRAME_LINES) * GbConstants.LINE_CYCLES + 78 * GbConstants.DOUBLE_SPEED_FACTOR);
        int toM0Time = Accessibility.getM0TimeOfLine((spriteState.frameOffset * GbConstants.FRAME_LINES + sprite.to) * GbConstants.LINE_CYCLES, sceneAccessibility);
        TimeStamp to = new TimeStamp(
            scene,
            (int)(spriteState.frameOffset + sprite.to / GbConstants.FRAME_LINES),
            (int)(sprite.to % GbConstants.FRAME_LINES) * GbConstants.LINE_CYCLES + toM0Time);
        TimeStamp notAtOrAfter;
        if (sprite.notAtOrAfter >= spriteState.numFrames * GbConstants.FRAME_LINES && spriteState.numFrames + spriteState.frameOffset >= numFrames) {
          notAtOrAfter = new TimeStamp(
              scene+1,
              0,
              Math.max(0, sprite.sprite.y - 16) * GbConstants.LINE_CYCLES + 78 * GbConstants.DOUBLE_SPEED_FACTOR);
        } else {
          notAtOrAfter = new TimeStamp(
              scene,
              (int)(spriteState.frameOffset + sprite.notAtOrAfter / GbConstants.FRAME_LINES),
              (int)(sprite.notAtOrAfter % GbConstants.FRAME_LINES) * GbConstants.LINE_CYCLES + 78 * GbConstants.DOUBLE_SPEED_FACTOR);
        }
        sprites.add(new TimestampedSpriteStateRange(sprite.sprite, notBeforeOrAt, from, to, notAtOrAfter));
        
        sprite.sprite.upperTile.usages.addRange(from, to, -1);
        if (sprite.sprite.lowerTile != null)
          sprite.sprite.lowerTile.usages.addRange(from, to, -1);
        sprite.sprite.palette.usages.addRange(from, to, -1);
      }
    }
    
    // Merge audio states
    for (AudioStateMap audioState : audioStates) {
      for (AudioChunk chunk : audioState.chunks) {
        TimestampedAudioChunk timestampedChunk = new TimestampedAudioChunk();
        timestampedChunk.operations = chunk.operations;
        timestampedChunk.from = new TimeStamp(scene, audioState.frameOffset + chunk.from / GbConstants.FRAME_LINES, (chunk.from % GbConstants.FRAME_LINES) * GbConstants.LINE_CYCLES + 4);
        timestampedChunk.to = new TimeStamp(scene, audioState.frameOffset + chunk.to / GbConstants.FRAME_LINES, (chunk.to % GbConstants.FRAME_LINES) * GbConstants.LINE_CYCLES + GbConstants.LINE_CYCLES - 4);
        audioChunks.add(timestampedChunk);
      }
    }
    // Add initial audio disable
    {
      TimestampedAudioChunk timestampedChunk = new TimestampedAudioChunk();
      timestampedChunk.operations = new ArrayList<>();
      timestampedChunk.operations.add(new AudioOperation(0xff26, 0));
      timestampedChunk.from = new TimeStamp(scene, 0, 0);
      timestampedChunk.to = new TimeStamp(scene, 0, 1);
      audioChunks.add(timestampedChunk);
    }

    audioChunks.sort((x, y) -> x.from.compareTo(y.from));
    for (int i = 0; i < audioChunks.size() - 1; i++)
      if (audioChunks.get(i+1).from.compareTo(audioChunks.get(i).to) <= 0)
        throw new RuntimeException("audio chunks overlap at " + audioChunks.get(i+1).from + " < " + audioChunks.get(i).to);

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
    if (bgMaps != null) {
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
    }
    System.out.println("max BG tile: " + maxi + " with " + max);
    System.out.println("min BG tile: " + mini + " with " + min);
    System.out.println("avg BG tile: " + (sum / 0x800) + " (" + sum + ")");
    System.out.println("known tiles: " + tileRegistry.getSize());
    System.out.println("known tiles usage size: " + tileRegistry.getUsageSize());
    System.out.println("known bg palettes: " + bgPaletteRegistry.getSize());
    System.out.println("known bg palettes usage size: " + bgPaletteRegistry.getUsageSize());
    System.out.println("known obj palettes: " + objPaletteRegistry.getSize());
    System.out.println("known obj palettes usage size: " + objPaletteRegistry.getUsageSize());
    System.out.println("num sprites: " + sprites.size());
    System.out.println();

    if (bgMaps != null) {
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

    System.out.println("num audio chunks: " + audioChunks.size());
    
    long minLen = Long.MAX_VALUE;
    long maxLen = Long.MIN_VALUE;
    int minSize = Integer.MAX_VALUE;
    int maxSize = Integer.MIN_VALUE;
    int sumOperations = 0;
    for (TimestampedAudioChunk chunk : audioChunks) {
      minLen = Math.min(minLen, chunk.to.toCycles() - chunk.from.toCycles());
      maxLen = Math.max(maxLen, chunk.to.toCycles() - chunk.from.toCycles());
      minSize = Math.min(minSize, chunk.operations.size());
      maxSize = Math.max(maxSize, chunk.operations.size());
      sumOperations += chunk.operations.size();
    }
    System.out.println("chunk length min: " + minLen + " max: " + maxLen);
    System.out.println("chunk size min: " + minSize + " max: " + maxSize);
    System.out.println("total operations: " + sumOperations);
    
    long minDist = Integer.MAX_VALUE;
    long maxDist = Integer.MIN_VALUE;
    for (int i = 0; i < audioChunks.size() - 1; i++) {
      minDist = Math.min(minDist, audioChunks.get(i+1).from.toCycles() - audioChunks.get(i).to.toCycles());
      maxDist = Math.max(maxDist, audioChunks.get(i+1).from.toCycles() - audioChunks.get(i).to.toCycles());
    }
    System.out.println("chunk dist min: " + minDist + " max: " + maxDist);
    System.out.println();
  }
  
  public static class ExtraBg {
    public Tile[] tiles;
    public Palette[] palettes;
    public TileResult[] tileResults;
    public PaletteEntry[] paletteEntries;
    
    public ExtraBg(Tile[] tiles, Palette[] palettes) {
      this.tiles = tiles;
      this.palettes = palettes;
    }
    
    public int getTilePosition(int index) {
      TileEntry e = tileResults[index].tile;
      return e.usages.getLastNonNullValueAt(TimeStamp.MAX_VALUE);
    }
    
    public int getPalettePosition(int index) {
      PaletteEntry e = paletteEntries[index];
      return e.usages.getLastNonNullValueAt(TimeStamp.MAX_VALUE);
    }
  }
  
  public void registerExtraBgs(ExtraBg... extraBgs) {
    int lastScene = numScenes - 1;
    int lastFrame = sceneFrames.get(lastScene) - 1;
    TimeStamp fromTime = new TimeStamp(lastScene, lastFrame, 0);
    TimeStamp toTime = new TimeStamp(lastScene, lastFrame + 1, 0);
    
    for (ExtraBg extraBg : extraBgs) {
      extraBg.tileResults = new TileResult[extraBg.tiles.length];
      for (int i = 0; i < extraBg.tiles.length; i++) {
        extraBg.tileResults[i] = tileRegistry.registerForBg(extraBg.tiles[i]);
        extraBg.tileResults[i].tile.usages.addRange(fromTime, toTime, -1);
      }
      extraBg.paletteEntries = new PaletteEntry[extraBg.palettes.length];
      for (int i = 0; i < extraBg.palettes.length; i++) {
        extraBg.paletteEntries[i] = bgPaletteRegistry.register(extraBg.palettes[i]);
        extraBg.paletteEntries[i].usages.addRange(fromTime, toTime, -1);
      }
    }
  }
 
  public void finishAssembly() {
    System.out.println("Calculating tile positions...");
    calculateTilePositions();
    System.out.println("Tile positions calculated");
    System.out.println("Calculating BG palette positions...");
    calculateBgPalettePositions();
    System.out.println("BG palette positions calculated");
    System.out.println("Converting BG map...");
    convertBgMap();
    System.out.println("BG map converted");
    System.out.println("Calculating obj palette positions...");
    calculateObjPalettePositions();
    System.out.println("Obj palette positions calculated");
    System.out.println("Calculating OAM positions...");
    calculateOamPositions();
    System.out.println("OAM positions calculated");
    System.out.println("Compressing states...");
    compressStates();
    System.out.println("States compressed");
  }

  @SuppressWarnings("unchecked")
  private StateHistory<TimeStamp, TileEntry>[][] tileHistory = new StateHistory[2][0x180];
  int numTileOverrides;

  private StateMap calculateTilePositions() {
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
                  if (curTile.usages.getValueAt(time) != null) {
                    if (curTile.usages.getCurrentStateStartTime(time).compareTo(time) == 0)
                      continue; // This tile is being needed again at this exact time as well, don't overwrite.
                    throw new RuntimeException("tile " + curTile + " used at " + time + " but tileHistory " + tileHistory[vramBank][address] + " free");
                  }
                  nextUse = min(nextUse, curTile.usages.getNextStateStartTime(time));
                }
                if (tile.pairedWith != null) {
                  curTile = tileHistory[vramBank][address+1].getLastNonNullValueAt(time);
                  if (curTile != null) {
                    if (curTile.usages.getValueAt(time) != null) {
                      if (curTile.usages.getCurrentStateStartTime(time).compareTo(time) == 0)
                        continue; // This tile is being needed again at this exact time as well, don't overwrite.
                      throw new RuntimeException("tile " + curTile + " used at " + time + " but tileHistory " + tileHistory[vramBank][address+1] + " free");
                    }
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
  int numBgPaletteOverrides;
  int numObjPaletteOverrides;
  
  private StateMap calculateBgPalettePositions() {
    numBgPaletteOverrides = calculatePalettePositions(bgPaletteHistory, bgPaletteRegistry);
    return this;
  }
  private StateMap calculateObjPalettePositions() {
    numObjPaletteOverrides = calculatePalettePositions(objPaletteHistory, objPaletteRegistry);
    return this;
  }
  private int calculatePalettePositions(StateHistory<TimeStamp, PaletteEntry>[] paletteHistory, PaletteRegistry paletteRegistry) {
    int numPaletteOverrides = 0;
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
            if (curPalette.usages.getValueAt(time) != null) {
              if (curPalette.usages.getCurrentStateStartTime(time).compareTo(time) == 0)
                continue; // This palette is being needed again at this exact time as well, don't overwrite.
              throw new RuntimeException("palette " + curPalette + " used at " + time + " (" + curPalette.usages.toStringAround(time) + ") (start time " + curPalette.usages.getCurrentStateStartTime(time) + ") but paletteHistory " + paletteHistory[address].toStringAround(time) + " free");
            }
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
            if (curPalette.usages.getValueAt(time) != null) {
              if (curPalette.usages.getCurrentStateStartTime(time).compareTo(time) == 0)
                continue; // This palette is being needed again at this exact time as well, don't overwrite.
              throw new RuntimeException("palette " + curPalette + " used at " + time + " (" + curPalette.usages.toStringAround(time) + ") but paletteHistory " + paletteHistory[address].toStringAround(time) + " free");
            }
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
    
    outputPaletteStats(paletteHistory, numPaletteOverrides);
    return numPaletteOverrides;
  }

  private void outputPaletteStats(StateHistory<TimeStamp, PaletteEntry>[] paletteHistory, int numPaletteOverrides) {
    for (int x = 0; x < 8; x++) {
      System.out.print(" " + paletteHistory[x].getSize());
    }
    System.out.println();
    System.out.println("num palette overrides: " + numPaletteOverrides);
  }
  
  @SuppressWarnings("unchecked")
  private StateHistory<TimeStamp, Integer>[] bgHistory = new StateHistory[0x1000];
  private StateMap convertBgMap() {
    
    for (int address = 0; address < 0x800; address++) {
      if (bgMaps[address] != null) {
        bgHistory[address] = StateHistory.withTimestampIndex(StateHistory.getIdentityMerger());
        bgHistory[0x800 + address] = StateHistory.withTimestampIndex(StateHistory.getIdentityMerger());
        
        StateHistory<TimeStamp, BgMapState> history = bgMaps[address];
        {
          TimeStamp t = TimeStamp.MIN_VALUE;
          while (true) {
            BgMapState v = history.getValueAt(t);
            TimeStamp nextTimestamp = history.getNextStateStartTime(t);
            if (v != null) {
              if (nextTimestamp == null) // reached end prematurely
                throw new RuntimeException("unexpected end after value " + v + " at " + t + " in " + history);
              bgHistory[address].addRange(t, nextTimestamp, (v.tile.usages.getValueAt(t) % 0x180) & 0xff);
              bgHistory[0x800 + address].addRange(t, nextTimestamp, v.palette.usages.getValueAt(t)
                  | ((v.tile.usages.getValueAt(t) >= 0x180 ? 1 : 0) << 3)
                  | ((v.flipHorizontally ? 1 : 0) << 5)
                  | ((v.flipVertically ? 1 : 0) << 6)
                  | ((v.bgToOamPriority ? 1 : 0) << 7));
            }
            if (nextTimestamp == null) // reached end
              break;
            t = nextTimestamp;
          }
        }
        bgMaps[address] = null; // drop bgMap
      }
    }
    
    bgMaps = null; // drop all bgMaps
    
    return this;
  }

  @SuppressWarnings("unchecked")
  private StateHistory<TimeStamp, OamEntry>[] oamHistory = new StateHistory[40];
  private int numOamReplace = 0;
  private int numOamNew = 0;
  private int numOamEvict = 0;
  private int numOamClose = 0;

  private StateMap calculateOamPositions() {
    sprites.sort((a, b) -> a.from.compareTo(b.from));
    for (int pos = 0; pos < 40; pos++)
      oamHistory[pos] = StateHistory.withTimestampIndex(StateHistory.getIdentityMerger());
    TimestampedSpriteStateRange[] activeSprites = new TimestampedSpriteStateRange[40];
    
    for (TimestampedSpriteStateRange sprite : sprites) {
      int chosenOamPos = -1;
      // Look for sprite to replace
      long maxOverlap = 10000; // minimum required acceptable overlap, in cycles.
      for (int pos = 0; pos < 40; pos++) {
        if (activeSprites[pos] != null
            && sprite.from.compareTo(activeSprites[pos].to) > 0
            && sprite.notBefore.compareTo(activeSprites[pos].notAtOrAfter) < 0) {
          TimeStamp timeFromExcl = max(activeSprites[pos].to, sprite.notBefore);
          TimeStamp timeToExcl = min(activeSprites[pos].notAtOrAfter, sprite.from);
          if (timeFromExcl.compareTo(timeToExcl) >= 0)
            throw new RuntimeException("from " + timeFromExcl + " not less than to " + timeToExcl);
          if (timeFromExcl.scene != timeToExcl.scene) {
            chosenOamPos = pos;
            break;
          }
          long overlap = timeToExcl.toCycles() - timeFromExcl.toCycles();
          if (overlap > maxOverlap) {
            maxOverlap = overlap;
            chosenOamPos = pos;
          }
        }
      }
      if (chosenOamPos == -1)
        // Look for unused sprite position
        for (int pos = 0; pos < 40; pos++) {
          if (activeSprites[pos] == null) {
            chosenOamPos = pos;
            break;
          }
        }
      if (chosenOamPos == -1)
        // Look for sprite to evict and replace
        for (int pos = 0; pos < 40; pos++) {
          if (activeSprites[pos] != null
              && activeSprites[pos].notAtOrAfter.compareTo(sprite.notBefore) < 0) {
            chosenOamPos = pos;
            break;
          }
        }
      
      if (chosenOamPos == -1)
        throw new RuntimeException("found no OAM position for " + sprite);
      if (activeSprites[chosenOamPos] == null) {
        numOamNew++;
        if (TimeStamp.ZERO.compareTo(sprite.notBefore) < 0)
          oamHistory[chosenOamPos].addRange(TimeStamp.ZERO, sprite.notBefore, OamEntry.DISABLED);
        oamHistory[chosenOamPos].addRange(sprite.from, sprite.to, sprite.toOamEntry());
        activeSprites[chosenOamPos] = sprite;
      } else  if (activeSprites[chosenOamPos].notAtOrAfter.compareTo(sprite.notBefore) < 0) {
        numOamEvict++;
        oamHistory[chosenOamPos].addRange(activeSprites[chosenOamPos].notAtOrAfter, sprite.notBefore, OamEntry.DISABLED);
        oamHistory[chosenOamPos].addRange(sprite.from, sprite.to, sprite.toOamEntry());
        activeSprites[chosenOamPos] = sprite;
      } else {
        numOamReplace++;
        if (sprite.from.compareTo(activeSprites[chosenOamPos].to) <= 0)
          throw new RuntimeException("chosen RAM position is no fit");

        if (sprite.notBefore.compareTo(activeSprites[chosenOamPos].to) > 0)
          oamHistory[chosenOamPos].addRange(activeSprites[chosenOamPos].to, sprite.notBefore, activeSprites[chosenOamPos].toOamEntry());

        oamHistory[chosenOamPos].addRange(min(activeSprites[chosenOamPos].notAtOrAfter, sprite.from), sprite.to, sprite.toOamEntry());
        activeSprites[chosenOamPos] = sprite;
      }
    }
    
    // close still active sprites
    for (int pos = 0; pos < 40; pos++) {
      if (activeSprites[pos] != null) {
        if (activeSprites[pos].notAtOrAfter.scene < numScenes) {
          numOamClose++;
          oamHistory[pos].addRange(activeSprites[pos].notAtOrAfter, activeSprites[pos].notAtOrAfter.addCycles(1), OamEntry.DISABLED);
        }
      }
    }
    
    outputOamStats();
    return this;
  }

  private void outputOamStats() {
    for (int x = 0; x < 40; x++) {
      System.out.print(" " + oamHistory[x].getSize());
    }
    System.out.println();
    System.out.println("num OAM new: " + numOamNew);
    System.out.println("num OAM replace: " + numOamReplace);
    System.out.println("num OAM evict: " + numOamEvict);
    System.out.println("num OAM close: " + numOamClose);
  }

  
  private void compressStates() {
    StateHistory.compressTimestampHistory(lcdc, true);
    StateHistory.compressTimestampHistory(wy);
    StateHistory.compressTimestampHistory(wx);
    StateHistory.compressTimestampHistory(scy);
    StateHistory.compressTimestampHistory(scx);
//    for (int i = 0; i < 0x800; i++)
//      if (bgMaps[i] != null)
//        StateHistory.compressTimestampHistory(bgMaps[i]);
    for (int i = 0; i < 0x1000; i++)
      if (bgHistory[i] != null)
        StateHistory.compressTimestampHistory(bgHistory[i]);
    for (int vramBank = 0; vramBank < 2; vramBank++) 
      for (int address = 0; address < 0x180; address++)
        StateHistory.compressTimestampHistory(tileHistory[vramBank][address]);
    for (int address = 0; address < 8; address++)
      StateHistory.compressTimestampHistory(bgPaletteHistory[address]);
    for (int address = 0; address < 8; address++)
      StateHistory.compressTimestampHistory(objPaletteHistory[address]);
    for (int pos = 0; pos < 40; pos++)
      StateHistory.compressTimestampHistory(oamHistory[pos]);
    
    outputAssemblyStats();
    outputTileStats();
    outputPaletteStats(bgPaletteHistory, numBgPaletteOverrides);
    outputPaletteStats(objPaletteHistory, numObjPaletteOverrides);
    outputOamStats();
  }
  
  public ArrayList<TimedAction> generateActionList() {
    ArrayList<TimedAction> actions = new ArrayList<>();
    
    addToActions(lcdc, v -> v.toValue(), HRAM, GbConstants.LCDC, actions);
    addToActions(wy, v -> v.toValue(), HRAM, GbConstants.WY, actions);
    addToActions(wx, v -> v, HRAM, GbConstants.WX, actions);
    addToActions(scy, v -> v, HRAM, GbConstants.SCY, actions);
    addToActions(scx, v -> v, HRAM, GbConstants.SCX, actions);
    
    // BG
    for (int address = 0; address < 0x1000; address++) {
      if (bgHistory[address] != null)
        addToActions(bgHistory[address], v -> v, WRAM, address, actions);
    }
    
    // Tiles
    for (int address = 0; address < 0x300; address++) {
      addToActions(tileHistory[address / 0x180][address % 0x180], v -> v.tile, TILE, address, actions);
    }
    
    // OAM
    for (int pos = 0; pos < 40; pos++) {
      addToActions(oamHistory[pos], v -> v, OAM, pos, actions);
    }
    
    // BG Palettes
    for (int address = 0; address < 8; address++) {
      addToActions(bgPaletteHistory[address], v -> v.palette, BGPALETTE, address, actions);
    }
    
    // Obj Palettes
    for (int address = 0; address < 8; address++) {
      addToActions(objPaletteHistory[address], v -> v.palette, OBJPALETTE, address, actions);
    }
    
    // Audio
    for (TimestampedAudioChunk chunk : audioChunks) {
      @SuppressWarnings("unchecked")
      Action<Integer>[] chunkActions = new Action[chunk.operations.size()];
      for (int i = 0; i < chunk.operations.size(); i++)
        chunkActions[chunk.operations.size() - i - 1] = new Action<Integer>(HRAM, chunk.operations.get(i).value, chunk.operations.get(i).address);
      actions.add(new TimedAction(chunk.from.scene, chunk.from.toCycles(), chunk.to.toCycles(), chunkActions));
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

  private <T, R> void addToActions(StateHistory<TimeStamp, T> history, Function<T, R> toActionValue, Type type, int address, ArrayList<TimedAction> actions) {
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
      long fromCycles = curTimestamp.toCycles();
      actions.add(new TimedAction(curTimestamp.scene, fromCycles, nextTimestamp.toCycles(), new Action<R>(type, toActionValue.apply(nextValue), address)));
      curTimestamp = history.getNextStateStartTime(nextTimestamp);
      if (curTimestamp == null) // reached end
        throw new RuntimeException("unexpected end of history after " + nextTimestamp + " value " + nextValue);
    }
  }
}
