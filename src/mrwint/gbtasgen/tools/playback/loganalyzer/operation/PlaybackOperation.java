package mrwint.gbtasgen.tools.playback.loganalyzer.operation;

import java.util.TreeMap;

public interface PlaybackOperation {
  /** Maps relative cycle delay to inputs. */
  public TreeMap<Integer, Integer> getInputMap();
  /** Total number of cycles (including return). */
  public int getCycleCount();
  /** Start address. */
  public int getJumpAddress();
  public int getStartOutputCycle();
  public int getEndOutputCycle();
  
  public static final int JOYPAD_MASK = 0xCF; // No buttons pressed;
  public static final int COMBINED_XOR_NIBBLE_MASK = (JOYPAD_MASK & 0x0f) ^ ((JOYPAD_MASK & 0xf0) >> 4);
  
  public static int toJoypadNibble(int value) {
    return value ^ COMBINED_XOR_NIBBLE_MASK;
  }
  
  /** First nibble, that gets swapped. */
  public default int toJoypadInput1(int value) {
    return toJoypadNibble((value & 0xf0) >> 4);
  }
  /** Second nibble. */
  public default int toJoypadInput2(int value) {
    return toJoypadNibble(value & 0x0f);
  }
}
