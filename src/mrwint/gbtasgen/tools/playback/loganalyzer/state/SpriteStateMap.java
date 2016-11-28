package mrwint.gbtasgen.tools.playback.loganalyzer.state;

import java.util.ArrayList;
import java.util.Iterator;

import mrwint.gbtasgen.tools.playback.loganalyzer.GbConstants;
import mrwint.gbtasgen.tools.playback.loganalyzer.Palette;
import mrwint.gbtasgen.tools.playback.loganalyzer.PaletteRegistry.PaletteEntry;
import mrwint.gbtasgen.tools.playback.loganalyzer.RawMemoryMap;
import mrwint.gbtasgen.tools.playback.loganalyzer.Tile;
import mrwint.gbtasgen.tools.playback.loganalyzer.TileRegistry.DoubleTileResult;
import mrwint.gbtasgen.tools.playback.loganalyzer.TileRegistry.TileResult;
import mrwint.gbtasgen.tools.playback.loganalyzer.TimeStamp;

public class SpriteStateMap {
  public static class SpriteStateRange {
    final SpriteState sprite;
    long notBeforeOrAt;
    long from;
    long to;
    long notAtOrAfter;
    
    public SpriteStateRange(SpriteState sprite) {
      this.sprite = sprite;
    }
    
    @Override
    public String toString() {
      return "" + sprite + " notBeforeOrAt: " + notBeforeOrAt + " from: " + from + " to: " + to + " notAtOrAfter: " + notAtOrAfter;
    }
  }
  
  StateHistory<Long, LcdcState> lcdc = StateHistory.withLongIndex(LcdcState.MERGER);
  ArrayList<SpriteStateRange> sprites = new ArrayList<>();
  
  public int numFrames;
  public int frameOffset;

  public SpriteStateMap(StateMap stateMap, RawMemoryMap memoryMap, int inputScene, int firstInputFrame, int numFrames) {
    this.numFrames = numFrames;
    
    ArrayList<SpriteStateRange> activeSprites = new ArrayList<>();
    long[] lineLastRendered = new long[144];
    for (int i = 0; i < 144; i++)
      lineLastRendered[i] = -1;

    for (int inputFrame = firstInputFrame; inputFrame < firstInputFrame + numFrames; inputFrame++) {
      if ((inputFrame - firstInputFrame) % 100 == 0)
        System.out.println("processing sprite input frame " + inputFrame);
      for (int scanLine = 0; scanLine < 144; scanLine++) {
        int lineStartFrameCycle = GbConstants.LINE_CYCLES * scanLine + 80 * GbConstants.DOUBLE_SPEED_FACTOR;
        TimeStamp inputTime = new TimeStamp(inputScene, inputFrame, lineStartFrameCycle);
        long curLineTime = (inputFrame - firstInputFrame) * GbConstants.FRAME_LINES + scanLine;

        int lcdc = memoryMap.getLCDC(inputTime);
        boolean lcdEnabled = (lcdc & 0x80) != 0;
        boolean objEnabled = (lcdc & 0x2) != 0;
        boolean obj8x16 = (lcdc & 0x4) != 0;

        this.lcdc.addRange(curLineTime, curLineTime + 1, LcdcState.forLcdDisplay(lcdEnabled));
        this.lcdc.addRange(curLineTime, curLineTime + 1, LcdcState.forObjDisplay(objEnabled));
        this.lcdc.addRange(curLineTime, curLineTime + 1, LcdcState.forObjSize(obj8x16));
        
        if (lcdEnabled && objEnabled) {
          ArrayList<SpriteState> lineSprites = new ArrayList<>();
          for (int add = 0xfe00; add < 0xfea0; add += 4) {
            int y = memoryMap.getOAM(add, inputTime);
            if (scanLine >= y - 16 && scanLine < y - (obj8x16 ? 0 : 8)) {
              int x = memoryMap.getOAM(add + 1, inputTime);
              if (x > 0 && x < 168) {
                SpriteState state = createTileState(stateMap, add, memoryMap, inputScene, inputFrame, scanLine);
                if (stateMap.spriteStateRegistry.containsKey(state))
                  state = stateMap.spriteStateRegistry.get(state);
                else
                  stateMap.spriteStateRegistry.put(state, state);
                lineSprites.add(state);
              }
            }
          }
          for (Iterator<SpriteStateRange> iterator = activeSprites.iterator(); iterator.hasNext();) {
            SpriteStateRange activeSprite =iterator.next();
            int lineSpriteIndex = lineSprites.indexOf(activeSprite.sprite);
            if (lineSpriteIndex == -1) {
              if (scanLine >= activeSprite.sprite.y - 16 && scanLine < activeSprite.sprite.y - (obj8x16 ? 0 : 8)) {
                activeSprite.notAtOrAfter = curLineTime;
                sprites.add(activeSprite);
                iterator.remove();
              }
            } else {
              activeSprite.to = curLineTime;
              lineSprites.remove(activeSprite.sprite);
            }
          }
          for (SpriteState lineSprite : lineSprites) {
            long lastBlockedTime = -1;
            for (int dy = 0; dy < (lineSprite.lowerTile == null ? 8 : 16); dy++) {
              int y = lineSprite.y - 16 + dy;
              if (y >= 0 && y < 144)
                lastBlockedTime = Math.max(lastBlockedTime, lineLastRendered[y]);
            }
            
            SpriteStateRange range = new SpriteStateRange(lineSprite);
            range.notBeforeOrAt = lastBlockedTime;
            range.from = curLineTime;
            range.to = curLineTime;
            activeSprites.add(range);
          }

          lineLastRendered[scanLine] = curLineTime;
        }
      }
    }

    for (SpriteStateRange activeSprite : activeSprites) {
      activeSprite.notAtOrAfter = Long.MAX_VALUE;
      sprites.add(activeSprite);
    }

    outputStats(stateMap);
  }

  private SpriteState createTileState(StateMap stateMap, int add, RawMemoryMap memoryMap, int scene, int frame, int scanLine) {
    byte[] upperTileData = new byte[16];
    byte[] lowerTileData = new byte[16];
    byte[] paletteData = new byte[8];
    boolean flipHorizontally;
    boolean flipVertically;
    boolean objToBgPriority;

    TimeStamp inputTime = new TimeStamp(scene, frame, GbConstants.LINE_CYCLES * scanLine + 80 * GbConstants.DOUBLE_SPEED_FACTOR);

    int lcdc = memoryMap.getLCDC(inputTime);
    boolean obj8x16 = (lcdc & 0x4) != 0;
    int y = memoryMap.getOAM(add, inputTime);
    int x = memoryMap.getOAM(add + 1, inputTime);
    int firstRowScanLine = y - 16;

    for (int dy = 0; dy < 8; dy++) {
      int frameCycle = Math.max(0, firstRowScanLine + dy) * GbConstants.LINE_CYCLES + 80 * GbConstants.DOUBLE_SPEED_FACTOR;
      TimeStamp time = new TimeStamp(scene, frame, frameCycle);
      int tileNum = memoryMap.getOAM(add + 2, time);
      if (obj8x16) tileNum &= 0xFE;
      int tileAddress = 0x8000 + tileNum * 0x10;
      int attributes = memoryMap.getOAM(add + 3, time);
      int vramBank = (attributes & 0x08) >> 3;
      upperTileData[2 * dy] = (byte)memoryMap.getVramValue(vramBank, tileAddress + 2*dy, time);
      upperTileData[2 * dy + 1] = (byte)memoryMap.getVramValue(vramBank, tileAddress + 2*dy + 1, time);
    }
    if (obj8x16) {
      for (int dy = 0; dy < 8; dy++) {
        int frameCycle = Math.max(0, firstRowScanLine + dy + 8) * GbConstants.LINE_CYCLES + 80 * GbConstants.DOUBLE_SPEED_FACTOR;
        TimeStamp time = new TimeStamp(scene, frame, frameCycle);
        int tileNum = memoryMap.getOAM(add + 2, time);
        if (obj8x16) tileNum |= 0x01;
        int tileAddress = 0x8000 + tileNum * 0x10;
        int attributes = memoryMap.getOAM(add + 3, time);
        int vramBank = (attributes & 0x08) >> 3;
        lowerTileData[2 * dy] = (byte)memoryMap.getVramValue(vramBank, tileAddress + 2*dy, time);
        lowerTileData[2 * dy + 1] = (byte)memoryMap.getVramValue(vramBank, tileAddress + 2*dy + 1, time);
      }
    }
    
    int attributes = memoryMap.getOAM(add + 3, inputTime);
    int paletteIndex = attributes & 0x7;
    for (int i = 2; i < 8; i++) { // ignore color 0
      paletteData[i] = (byte)memoryMap.getObjPaletteValue(paletteIndex * 8 + i, inputTime);
      if ((i & 1) != 0)
        paletteData[i] &= 0x7f;
    }
    PaletteEntry palette = stateMap.objPaletteRegistry.register(new Palette(paletteData));
    if (obj8x16) {
      DoubleTileResult tileResult = stateMap.tileRegistry.registerForObj8x16(new Tile(upperTileData), new Tile(lowerTileData));
      flipHorizontally = tileResult.flipHorizontally ^ ((attributes & 0x20) != 0);
      flipVertically = tileResult.flipVertically ^ ((attributes & 0x40) != 0);
      objToBgPriority = ((attributes & 0x80) != 0);
      return new SpriteState(y, x, tileResult.upperTile, tileResult.lowerTile, objToBgPriority, flipHorizontally, flipVertically, palette);
    } else {
      TileResult upperTileResult = stateMap.tileRegistry.registerForObj8x8(new Tile(upperTileData));
      flipHorizontally = upperTileResult.flipHorizontally ^ ((attributes & 0x20) != 0);
      flipVertically = upperTileResult.flipVertically ^ ((attributes & 0x40) != 0);
      objToBgPriority = ((attributes & 0x80) != 0);
      return new SpriteState(y, x, upperTileResult.tile, null, objToBgPriority, flipHorizontally, flipVertically, palette);
    }
  }
  
  private void outputStats(StateMap stateMap) {
    System.out.println("LCDC: " + lcdc.getSize());
    
    SpriteStateRange minBefore = null;
    SpriteStateRange maxInFrame = null;
    SpriteStateRange minAfter = null;
    for (SpriteStateRange sprite : sprites) {
      if (sprite.notBeforeOrAt >= 0 && (minBefore == null || sprite.from - sprite.notBeforeOrAt < minBefore.from - minBefore.notBeforeOrAt))
        minBefore = sprite;
      if (maxInFrame == null || sprite.to - sprite.from > maxInFrame.to - maxInFrame.from)
        maxInFrame = sprite;
      if (minAfter == null || sprite.notAtOrAfter - sprite.to < minAfter.notAtOrAfter - minAfter.to)
        minAfter = sprite;
    }

    System.out.println("sprites: " + sprites.size());
    System.out.println("min before: " + minBefore);
    System.out.println("max in frame: " + maxInFrame);
    System.out.println("min after: " + minAfter);
    System.out.println("known tiles: " + stateMap.tileRegistry.getSize());
    System.out.println("known palettes: " + stateMap.objPaletteRegistry.getSize());
    System.out.println();

  }
}