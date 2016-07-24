package mrwint.gbtasgen.tools.playback.loganalyzer.accessibility;

import mrwint.gbtasgen.tools.playback.loganalyzer.GbConstants;

public interface Accessibility {
  /** Returns the largest cycle not greater than {@code cycle} in which the resource is accessible. */
  public long lastAccessibleCycleBefore(long cycle, AccessibilityGbState gbState);

  public static int getM0TimeOfLine(long cycle, AccessibilityGbState gbState) {
    return (253 + (gbState.getScx(cycle) % 8) + (gbState.getWinEnabled(cycle) ? 6 : 0) + gbState.getNumSprites(cycle) * 11) * GbConstants.DOUBLE_SPEED_FACTOR;
  }
}
