package mrwint.gbtasgen.tools.playback.loganalyzer.accessibility;

import mrwint.gbtasgen.tools.playback.loganalyzer.GbConstants;

public class OamAccessibility implements Accessibility {

  @Override
  public long lastAccessibleCycleBefore(long cycle, AccessibilityGbState gbState) {
    if (cycle <= (GbConstants.DOUBLE_SPEED ? 156 : 76)) // before first frame
      return cycle;

    int lineCycle = (int) (cycle % GbConstants.LINE_CYCLES);
    int frameLine = (int) ((cycle % GbConstants.FRAME_CYCLES) / GbConstants.LINE_CYCLES);

    if (frameLine >= 144) // in mode 1 (vblank)
      if (frameLine != 153 || lineCycle < getOamInaccessibleFrom())
        return cycle;
      else { // in mode 2 already
        int offset = lineCycle - (getOamInaccessibleFrom() - 1);
        return ((cycle - offset) / 4) * 4; // align to multiples of 4
      }
    if (frameLine == 143 && lineCycle >= getOamInaccessibleFrom())
      return cycle;
    
    if (lineCycle >= getOamInaccessibleFrom()) { // in mode 2
      if (frameLine == 143) return cycle; // actually in mode 1 (vblank)
      int offset = lineCycle - (getOamInaccessibleFrom() - 1);
      return ((cycle - offset) / 4) * 4; // align to multiples of 4
    }

    if (lineCycle <= getOamInaccessibleTo(cycle, gbState)) { // in mode 3
      int offset = lineCycle + GbConstants.LINE_CYCLES - (getOamInaccessibleFrom() - 1);
      cycle = ((cycle - offset) / 4) * 4; // align to multiples of 4
      cycle = Math.max(cycle, GbConstants.DOUBLE_SPEED ? 156 : 76);
      return cycle;
    }
    
    return cycle; // in mode 0 (hblank)
  }

  public static final int getOamInaccessibleFrom() {
    return (453 - Accessibility.SAFETY_BUFFER + (GbConstants.DOUBLE_SPEED ? 2 : 0) - (GbConstants.GBC ? 1 : 0)) * GbConstants.DOUBLE_SPEED_FACTOR - 1;
  }
  public static final int getOamInaccessibleTo(long cycle, AccessibilityGbState gbState) {
    return Accessibility.getM0TimeOfLine(cycle, gbState) - 3 - (GbConstants.DOUBLE_SPEED ? 1 : 0) + (GbConstants.GBC ? 1 : 0);
  }
}
