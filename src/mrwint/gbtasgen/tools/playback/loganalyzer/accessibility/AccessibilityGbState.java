package mrwint.gbtasgen.tools.playback.loganalyzer.accessibility;

public interface AccessibilityGbState {
  public int getNumSprites(long cycle);
  public int getScx(long cycle);
  public boolean getWinEnabled(long cycle);
}
