package mrwint.gbtasgen.tools.playback.loganalyzer.state;

import mrwint.gbtasgen.tools.playback.loganalyzer.state.StateHistory.StateMerger;

class WyState {
  int wy;
  boolean moreThan;
  private WyState(int wy, boolean moreThan) {
    this.moreThan = moreThan;
    this.wy = wy;
  }
  public static WyState exact(int wy) {
    return new WyState(wy, false);
  }
  public static WyState moreThan(int wy) {
    return new WyState(wy, true);
  }
  public int toValue() {
    return moreThan ? 0x90 : wy;
  }
  public static class Merger implements StateMerger<WyState> {
    @Override
    public WyState apply(WyState t, WyState u) {
      return new WyState(Math.max(t.wy, u.wy), t.moreThan && u.moreThan);
    }
    @Override
    public boolean isCompatible(WyState t, WyState u) {
      if (t.moreThan) {
        return u.moreThan || u.wy > t.wy;
      } else {
        return u.moreThan ? t.wy > u.wy : t.wy == u.wy;
      }
    }
  }
  static final Merger MERGER = new Merger();
}