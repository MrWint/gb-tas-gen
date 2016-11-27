package mrwint.gbtasgen.tools.playback.loganalyzer.state;

import mrwint.gbtasgen.tools.playback.loganalyzer.state.StateHistory.StateMerger;

class LcdcState {
  private final int lcdc;
  private final int mask;
  private LcdcState(int lcdc, int mask) {
    this.lcdc = lcdc;
    this.mask = mask;
  }
  private static LcdcState forBit(int index, boolean enabled) {
    return new LcdcState((enabled ? 1 : 0) << index, 1 << index);
  }
  public static LcdcState forLcdDisplay(boolean enable) {
    return forBit(7, enable);
  }
  public static LcdcState forWindowTileMap(boolean enable9c00) {
    return forBit(6, enable9c00);
  }
  public static LcdcState forWindowDisplay(boolean enable) {
    return forBit(5, enable);
  }
  public static LcdcState forTileData(boolean enable8000) {
    return forBit(4, enable8000);
  }
  public static LcdcState forBgTileMap(boolean enable9c00) {
    return forBit(3, enable9c00);
  }
  public static LcdcState forObjSize(boolean enable8x16) {
    return forBit(2, enable8x16);
  }
  public static LcdcState forObjDisplay(boolean enabled) {
    return forBit(1, enabled);
  }
  public static LcdcState forBgDisplay(boolean enabled) {
    return forBit(0, enabled);
  }

  public int toValue() {
    return lcdc;
  }

  static class Merger implements StateMerger<LcdcState> {
    @Override
    public LcdcState apply(LcdcState t, LcdcState u) {
      return new LcdcState((t.lcdc & t.mask) | (u.lcdc & u.mask), t.mask | u.mask);
    }
    @Override
    public boolean isCompatible(LcdcState t, LcdcState u) {
      return (t.lcdc & t.mask & u.mask) == (u.lcdc & t.mask & u.mask);
    }
  }
  static final Merger MERGER = new Merger();
}