package mrwint.gbtasgen.tools.playback.loganalyzer.accessibility;

import mrwint.gbtasgen.tools.playback.loganalyzer.GbConstants;

public class PaletteAccessibility implements Accessibility {

  @Override
  public long lastAccessibleCycleBefore(long cycle, AccessibilityGbState gbState) {
    if (cycle < getCbgpInaccessibleFrom()) // before first frame
      return cycle;

    int lineCycle = (int) (cycle % GbConstants.LINE_CYCLES);
    int frameLine = (int) ((cycle % GbConstants.FRAME_CYCLES) / GbConstants.LINE_CYCLES);
    
    if (frameLine >= 144) // in mode 1 (vblank)
      return cycle;
    
    if (lineCycle < getCbgpInaccessibleFrom()) // in mode 2
      return cycle;
    
    if (lineCycle <= getCbgpInaccessibleTo(cycle, gbState)) { // in mode 3
      int offset = lineCycle - (getCbgpInaccessibleFrom() - 1);
      return ((cycle - offset) / 4) * 4; // align to multiples of 4
    }
    
    return cycle; // in mode 0 (hblank)
  }
  
  public static final int getCbgpInaccessibleFrom() {
    return 80 * GbConstants.DOUBLE_SPEED_FACTOR - 1 + (GbConstants.DOUBLE_SPEED ? 2 : 0);
  }
  public static final int getCbgpInaccessibleTo(long cycle, AccessibilityGbState gbState) {
    return Accessibility.getM0TimeOfLine(cycle, gbState) + 2 - (GbConstants.DOUBLE_SPEED ? 1 : 0);
  }
}
