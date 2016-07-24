package mrwint.gbtasgen.tools.playback.loganalyzer.accessibility;

public class FixedAccessibilityGbState implements AccessibilityGbState {

  private final int numSprites;
  private final int scx;
  private final boolean winEnabled;
  
  public FixedAccessibilityGbState(int numSprites, int scx, boolean winEnabled) {
    this.numSprites = numSprites;
    this.scx = scx;
    this.winEnabled = winEnabled;
  }

  @Override
  public int getNumSprites(long cycle) {
    return numSprites;
  }

  @Override
  public int getScx(long cycle) {
    return scx;
  }

  @Override
  public boolean getWinEnabled(long cycle) {
    return winEnabled;
  }

}
