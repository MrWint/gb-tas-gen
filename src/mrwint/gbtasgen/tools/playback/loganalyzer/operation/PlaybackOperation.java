package mrwint.gbtasgen.tools.playback.loganalyzer.operation;

import static mrwint.gbtasgen.tools.playback.loganalyzer.GbConstants.FRAME_CYCLES;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Map.Entry;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.tools.playback.loganalyzer.GbConstants;
import mrwint.gbtasgen.tools.playback.loganalyzer.accessibility.Accessibility;
import mrwint.gbtasgen.tools.playback.loganalyzer.accessibility.AccessibilityGbState;

public interface PlaybackOperation {
  /** Maps relative cycle delay to inputs. */
  public TreeMap<Integer, Integer> getInputMap();
  /** Total number of cycles (including return). */
  public int getCycleCount();
  /** Start address. */
  public int getJumpAddress();
  /** First cycle which outputs. */
  public int getStartOutputCycle();
  /** Last cycle which outputs. */
  public int getEndOutputCycle();
  
  /** Returns the accessibility restrictions for the used resource. */
  public Accessibility getAccessibility();
  
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
  /** Returns latest cycle the operation can be executed successfully that is not greater than the given value. */
  public default long getLatestStartingCycleBefore(long maxCycle, AccessibilityGbState gbState) {
    Accessibility accessibility = getAccessibility();
    long curCycle = (maxCycle / 4) * 4;
    loop: while (true) {
      for (int offset = getStartOutputCycle(); offset <= getEndOutputCycle(); offset += 4) {
        long tmp = accessibility.lastAccessibleCycleBefore(curCycle + offset, gbState);
        if (tmp != curCycle + offset) {
          curCycle = tmp - offset;
          continue loop;
        }
      }
      return curCycle;
    }
  }
  
  
  public static int[] toInputs(ArrayList<PlaybackOperation> playback, long cycleOffset) {
    ArrayList<Integer> inputList = new ArrayList<>();
    
    long curCycle = cycleOffset;
    for (PlaybackOperation op : playback) {
      for (Entry<Integer, Integer> input : op.getInputMap().entrySet()) {
        boolean frameBoundary = curCycle + input.getKey() >= FRAME_CYCLES;
        if (frameBoundary) {
          curCycle -= FRAME_CYCLES;
          while (curCycle + input.getKey() >= FRAME_CYCLES) {
            inputList.add(0 | Move.FRAME_START);
            curCycle -= FRAME_CYCLES;
          }
        }
        inputList.add(input.getValue() | (frameBoundary ? Move.FRAME_START : 0));
      }
      curCycle += op.getCycleCount();
    }
    
    int[] inputs = new int[inputList.size()];
    for (int i = 0; i < inputs.length; i++)
      inputs[i] = inputList.get(i);
    
    return inputs;
  }
}
