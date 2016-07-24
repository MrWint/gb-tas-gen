package mrwint.gbtasgen.tools.playback.loganalyzer.accessibility;

public class AlwaysAccessible implements Accessibility {

  @Override
  public long lastAccessibleCycleBefore(long cycle, AccessibilityGbState gbState) {
    return cycle;
  }
}
