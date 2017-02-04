package mrwint.gbtasgen.tools.playback.loganalyzer.operation;

import java.util.Map;
import java.util.TreeMap;

import mrwint.gbtasgen.tools.playback.loganalyzer.accessibility.Accessibility;
import mrwint.gbtasgen.tools.playback.loganalyzer.accessibility.AlwaysAccessible;
import mrwint.gbtasgen.tools.playback.util.audio.GbAudio;

public class PlaySoundSetBg implements PlaybackOperation {
  private final TreeMap<Integer, Integer> inputMap = new TreeMap<>();
  private final int cycleCount;
  
  public static class BgOp {
    private final int address;
    private final int tileIndex;
    private final int attributes;
    public BgOp(int address, int tileIndex, int attributes) {
      this.address = address;
      this.tileIndex = tileIndex;
      this.attributes = attributes;
    }
  }
  
  public PlaySoundSetBg(GbAudio audio, Map<Integer, BgOp> tileOperations) {
    int[] samples = audio.samples;
    int[] soValues = audio.soValues;

    if (samples.length != soValues.length * 2)
      throw new IllegalArgumentException("samples length " + samples.length + " does not match twice so length " + soValues.length);
    if (soValues.length < 1)
      throw new IllegalArgumentException("need at least 1 set of samples.");
    System.out.println("create PlaySound with " + soValues.length + " sample pairs.");

    
    inputMap.put(40, getSampleOrPadding(samples, 0) ^ 0xf);
    for (int i = 0; i < 15; i++) {
      inputMap.put(60 + 60*i, toJoypadInput2(getSampleOrPadding(samples, 2*i + 1)));
      inputMap.put(76 + 60*i, toJoypadInput2(getSampleOrPadding(samples, 2*i + 2)));
    }
    
    int curCycles = 1520;
    for (int i = 0; i < soValues.length; i++) {
      inputMap.put(curCycles, toJoypadInput2(soValues[i]));
      
      inputMap.put(curCycles + 36, toJoypadInput2(getSampleOrPadding(samples, 2*i + 31)));
      inputMap.put(curCycles + 52, toJoypadInput2(getSampleOrPadding(samples, 2*i + 32)));
      
      if (tileOperations.containsKey(i)) {
        inputMap.put(curCycles + 72, 1);
        BgOp op = tileOperations.get(i);

        inputMap.put(curCycles + 112, toJoypadInput1((op.address >> 8) & 0xff));
        inputMap.put(curCycles + 128, toJoypadInput2((op.address >> 8) & 0xff));
        inputMap.put(curCycles + 140, toJoypadInput1(op.address & 0xff));
        inputMap.put(curCycles + 156, toJoypadInput2(op.address & 0xff));
        inputMap.put(curCycles + 168, toJoypadInput1(op.tileIndex));
        inputMap.put(curCycles + 184, toJoypadInput2(op.tileIndex));
        inputMap.put(curCycles + 216, toJoypadInput1(op.attributes));
        inputMap.put(curCycles + 232, toJoypadInput2(op.attributes));
      } else
        inputMap.put(curCycles + 72, 0);

      inputMap.put(curCycles + 884, i+1 < soValues.length ? 1 : 0);

      curCycles += 912;
    }
    cycleCount = curCycles + 28;
    if (cycleCount != getCycleCount(soValues.length))
      throw new RuntimeException("cycle count " + cycleCount + "does not match calculation of " + getCycleCount(soValues.length));
  }
  private int getSampleOrPadding(int[] samples, int index) {
    return index < samples.length ? samples[index] : 0;
  }
  
  @Override
  public TreeMap<Integer, Integer> getInputMap() {
    return inputMap;
  }
  @Override
  public int getCycleCount() {
    return cycleCount;
  }
  @Override
  public int getJumpAddress() {
    return PlaybackAddresses.PLAY_SOUND_SET_BG;
  }
  /** Total cycles of this operation. */
  public static int getCycleCount(int len) {
    return 1548 + len * 912;
  }
  /** Relative cycles of first input read. */
  public static int getFirstInputCycles() {
    return 40;
  }
  
  @Override
  public int getStartOutputCycle() {
    return -1;
  }
  @Override
  public int getEndOutputCycle() {
    return -1;
  }
  @Override
  public Accessibility getAccessibility() {
    return new AlwaysAccessible();
  }
}