package mrwint.gbtasgen.tools.playback.loganalyzer.state;

import mrwint.gbtasgen.tools.playback.loganalyzer.GbConstants;
import mrwint.gbtasgen.tools.playback.loganalyzer.Palette;
import mrwint.gbtasgen.tools.playback.loganalyzer.RawMemoryMap;
import mrwint.gbtasgen.tools.playback.loganalyzer.SceneDesc;
import mrwint.gbtasgen.tools.playback.loganalyzer.Tile;
import mrwint.gbtasgen.tools.playback.loganalyzer.TileRegistry.TileResult;
import mrwint.gbtasgen.tools.playback.loganalyzer.TimeStamp;

public class BackgroundStateMap {
  StateHistory<Long, LcdcState> lcdc = StateHistory.withLongIndex(LcdcState.MERGER);
  StateHistory<Long, WyState> wy = StateHistory.withLongIndex(WyState.MERGER);
  StateHistory<Long, Integer> wx = StateHistory.withLongIndex(StateHistory.getIdentityMerger());
  StateHistory<Long, Integer> scy = StateHistory.withLongIndex(StateHistory.getIdentityMerger());
  StateHistory<Long, Integer> scx = StateHistory.withLongIndex(StateHistory.getIdentityMerger());
  @SuppressWarnings("unchecked")
  StateHistory<Long, BgMapState>[] bgMaps = new StateHistory[0x800];
  
  public final int numFrames;
  public int frameOffset;
  private final boolean gbMode;

  public BackgroundStateMap(StateMap stateMap, RawMemoryMap memoryMap, SceneDesc desc) {
    this(stateMap, memoryMap, desc.inputScene, desc.firstInputFrame, desc.numInputFrames, desc.numOutputFrames, desc.gbMode);
  }
  private BackgroundStateMap(StateMap stateMap, RawMemoryMap memoryMap, int inputScene, int firstInputFrame, int numInputFrames, int numOutputFrames, boolean gbMode) {
    this.gbMode = gbMode;
    this.numFrames = numOutputFrames;

    for (int outputFrame = 0; outputFrame < numOutputFrames; outputFrame++) {
      int inputFrame = outputFrame * numInputFrames / numOutputFrames + firstInputFrame;
      if (outputFrame % 100 == 0)
        System.out.println("processing background output frame " + outputFrame);
      for (int scanLine = 0; scanLine < 144; scanLine++) {
        int lineStartFrameCycle = GbConstants.LINE_CYCLES * scanLine + 80 * GbConstants.DOUBLE_SPEED_FACTOR;
        TimeStamp inputTime = new TimeStamp(inputScene, inputFrame, lineStartFrameCycle);
        long curLineTime = outputFrame * GbConstants.FRAME_LINES + scanLine;

        int lcdc = memoryMap.getLCDC(inputTime);
        int wy = memoryMap.getWY(inputTime);
        boolean mWy = ((lcdc & 0x20) != 0) && ((lcdc & 0x1) != 0 || !GbConstants.GBC);
        int wx = memoryMap.getWX(inputTime);
        boolean mWx = mWy && wy <= scanLine;
        int scy = memoryMap.getSCY(inputTime);
        int scx = memoryMap.getSCX(inputTime);
        boolean mScyx = (lcdc & 0x1) != 0 && (!mWx || wx > 7); // right edge not rendered when WX < 7

        this.lcdc.addRange(curLineTime, curLineTime + 1, LcdcState.forBgDisplay((lcdc & 0x1) != 0));
        this.lcdc.addRange(curLineTime, curLineTime + 1, LcdcState.forBgTileMap((lcdc & 0x8) != 0));
        this.lcdc.addRange(curLineTime, curLineTime + 1, LcdcState.forWindowDisplay((lcdc & 0x20) != 0));
        this.lcdc.addRange(curLineTime, curLineTime + 1, LcdcState.forWindowTileMap((lcdc & 0x40) != 0));
        this.lcdc.addRange(curLineTime, curLineTime + 1, LcdcState.forLcdDisplay((lcdc & 0x80) != 0));
        if (mWy)
          this.wy.addRange(curLineTime, curLineTime + 1, wy <= scanLine ? WyState.exact(wy) : WyState.moreThan(scanLine));
        if (mWx)
          this.wx.addRange(curLineTime, curLineTime + 1, canonicalizeWx(wx));
        if (mScyx) {
          this.scy.addRange(curLineTime, curLineTime + 1, canonicalizeScy(scy));
          this.scx.addRange(curLineTime, curLineTime + 1, canonicalizeScx(scx));
        }
        
        // bg map
        int winTileRange = -1;
        if (mWx)
          winTileRange = ((lcdc & 0x40) == 0 ? 0x9800 : 0x9c00) + (((scanLine - wy) / 8) << 5);
        int bgTileRange = -1;
        if (mScyx)
          bgTileRange = ((lcdc & 0x08) == 0 ? 0x9800 : 0x9c00) + ((((scy + scanLine) & 0xff) / 8) << 5);
        if (winTileRange != -1 || bgTileRange != -1) {
          int fromAdd = winTileRange == -1 ? bgTileRange : bgTileRange == -1 ? winTileRange : Math.min(winTileRange, bgTileRange);
          int toAdd = Math.max(winTileRange, bgTileRange) + 0x20;

//          for (int add = 0x9800; add < 0xa000; add++) {
          for (int add = fromAdd; add < toAdd; add++) {
            if (add == fromAdd + 0x20)
              add = toAdd - 0x20;
  
            boolean mWinTile = (add < 0x9c00) == ((lcdc & 0x40) == 0);
            boolean mBgTile = (add < 0x9c00) == ((lcdc & 0x08) == 0);
            int x = add & 0x1f;
            int y = (add >>> 5) & 0x1f;
            mWinTile &= mWx && y == (scanLine - wy) / 8 && x <= Math.min((166 - wx) / 8, 19); // Win tiles x>=20 never rendered even when WX < 7
            mBgTile &= mScyx && y == ((scy + scanLine) & 0xff) / 8 && ((8*x + 0x100 + 7 - scx) & 0xff) < 0xa7 // tile on screen
                && (!mWx || ((8*x + 0x100 + 7 - scx) & 0xff) < (wx - 7) + 7); // not covered by window
            
            if (mWinTile || mBgTile) {
              BgMapState state = null;
              if (mWinTile) {
                int firstRowScanLine = y * 8 + wy;
                state = createTileState(stateMap, add, memoryMap, inputScene, inputFrame, scanLine, firstRowScanLine);
              } else if (mBgTile) {
                int firstRowScanLine = ((y * 8 + 0x100 - scy) & 0xff);
                if (firstRowScanLine >= 0x90)
                  firstRowScanLine -= 0x100;
                state = createTileState(stateMap, add, memoryMap, inputScene, inputFrame, scanLine, firstRowScanLine);
              }
              if (stateMap.bgMapStateRegistry.containsKey(state))
                state = stateMap.bgMapStateRegistry.get(state);
              else
                stateMap.bgMapStateRegistry.put(state, state);
              if (this.bgMaps[add - 0x9800] == null)
                this.bgMaps[add - 0x9800] = StateHistory.withLongIndex(StateHistory.getIdentityMerger());
              this.bgMaps[add - 0x9800].addRange(curLineTime, curLineTime + 1, state);
            }
          }
        }
      }
    }
    
    outputStats(stateMap);
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
  
  private static final byte[] GB_BG_PALETTE = { (byte)0xff, 0x7f, (byte)0xe0, 0x7f, (byte)0x00, 0x7c, 0x0, 0x0 };

  private BgMapState createTileState(StateMap stateMap, int address, RawMemoryMap memoryMap, int scene, int frame, int scanLine, int firstRowScanLine) {
    byte[] tileData = new byte[16];
    byte[] paletteData = new byte[8];
    boolean flipHorizontally;
    boolean flipVertically;
    boolean bgToOamPriority;
    for (int dy = 0; dy < 8; dy++) {
      int frameCycle = Math.max(0, firstRowScanLine + dy) * GbConstants.LINE_CYCLES + 80 * GbConstants.DOUBLE_SPEED_FACTOR;
      TimeStamp time = new TimeStamp(scene, frame, frameCycle);
      int lcdc = memoryMap.getLCDC(time);
      int bgMapTile = memoryMap.getVramValue(0, address, time);
      int bgMapTileAddress = ((lcdc & 0x10) != 0 ? 0x8000 : 0x9000) + bgMapTile * 0x10;
      if (bgMapTileAddress >= 0x9800)
        bgMapTileAddress -= 0x1000;
      int bgMapAttribute = memoryMap.getVramValue(1, address, time);
      int vramBank = (bgMapAttribute & 0x08) >> 3;
      tileData[2 * dy] = (byte)memoryMap.getVramValue(vramBank, bgMapTileAddress + 2*dy, time);
      tileData[2 * dy + 1] = (byte)memoryMap.getVramValue(vramBank, bgMapTileAddress + 2*dy + 1, time);
    }
    
    int frameCycle = scanLine * GbConstants.LINE_CYCLES + 80 * GbConstants.DOUBLE_SPEED_FACTOR;
    TimeStamp time = new TimeStamp(scene, frame, frameCycle);
    
    int bgMapAttribute = memoryMap.getVramValue(1, address, time);
    if (gbMode)
      paletteData = GB_BG_PALETTE;
    else {
      int paletteIndex = bgMapAttribute & 0x7;
      for (int i = 0; i < 8; i++) {
        paletteData[i] = (byte)memoryMap.getBgPaletteValue(paletteIndex * 8 + i, time);
        if ((i & 1) != 0)
          paletteData[i] &= 0x7f;
      }
    }
    TileResult tileResult = stateMap.tileRegistry.registerForBg(new Tile(tileData));
    flipHorizontally = tileResult.flipHorizontally ^ ((bgMapAttribute & 0x20) != 0);
    flipVertically = tileResult.flipVertically ^ ((bgMapAttribute & 0x40) != 0);
    bgToOamPriority = ((bgMapAttribute & 0x80) != 0);

    return new BgMapState(tileResult.tile, flipHorizontally, flipVertically, bgToOamPriority, stateMap.bgPaletteRegistry.register(new Palette(paletteData)));
  }
  
  private void outputStats(StateMap stateMap) {
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
    System.out.println("known tiles: " + stateMap.tileRegistry.getSize());
    System.out.println("known palettes: " + stateMap.bgPaletteRegistry.getSize());
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
  }
}