package mrwint.gbtasgen.tools.playback.loganalyzer.operation;

import java.io.FileInputStream;
import java.util.TreeMap;

import mrwint.gbtasgen.tools.playback.loganalyzer.accessibility.Accessibility;
import mrwint.gbtasgen.tools.playback.loganalyzer.accessibility.AlwaysAccessible;
import mrwint.gbtasgen.util.Util;

public class WriteInitialOperations implements PlaybackOperation {
  private final TreeMap<Integer, Integer> inputMap = new TreeMap<>();
  
  private static final int END_OF_INPUT_MARKER = 0x5d;

  public WriteInitialOperations() {
    byte[] data = new byte[PlaybackAddresses.EOF - PlaybackAddresses.RECORD];
    try {
      FileInputStream is = new FileInputStream("temp/playback.gbc");
      is.skip(PlaybackAddresses.RECORD_ROM);
      is.read(data);
      is.close();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    for (int i = 0; i < data.length; i++) {
      int value = Byte.toUnsignedInt(data[i]);
      if (value == END_OF_INPUT_MARKER)
        throw new RuntimeException("found end of input marker " + END_OF_INPUT_MARKER + " in data at " + Util.toHex(i + PlaybackAddresses.RECORD_ROM));
      
      inputMap.put(12 + 56 * i, toJoypadInput1(value));
      inputMap.put(32 + 56 * i, toJoypadInput2(value));
    }
    inputMap.put(12 + 56 * data.length, toJoypadInput1(END_OF_INPUT_MARKER));
    inputMap.put(32 + 56 * data.length, toJoypadInput2(END_OF_INPUT_MARKER));
  }
  @Override
  public TreeMap<Integer, Integer> getInputMap() {
    return inputMap;
  }
  @Override
  public int getCycleCount() {
    return (inputMap.size()/2) * 56 + 24;
  }
  @Override
  public int getJumpAddress() {
    throw new UnsupportedOperationException();
  }
  @Override
  public int getStartOutputCycle() {
    throw new UnsupportedOperationException();
  }
  @Override
  public int getEndOutputCycle() {
    throw new UnsupportedOperationException();
  }
  @Override
  public Accessibility getAccessibility() {
    return new AlwaysAccessible();
  }
}
