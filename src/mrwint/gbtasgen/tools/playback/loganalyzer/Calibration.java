package mrwint.gbtasgen.tools.playback.loganalyzer;

import static mrwint.gbtasgen.tools.playback.loganalyzer.GbConstants.FRAME_CYCLES;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import mrwint.gbtasgen.tools.playback.loganalyzer.operation.PlaySound;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.PlaybackAddresses;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.PlaybackOperation;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.Record;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.Wait;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.WriteByteDirect;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.WriteHByteDirect;
import mrwint.gbtasgen.tools.playback.util.SoundUtil;

public class Calibration {
  
  public static void main(String[] args) throws Exception {
//    new PlaybackWriter(calibratePlaybackInputCycleOffsetOperations(), PLAYBACK_INPUT_CYCLE_OFFSET).write("movies/calibrationTest.lsmv");
//    new PlaybackWriter(calibrateVramAccessible(), PLAYBACK_INPUT_CYCLE_OFFSET).write("movies/calibrationTest.lsmv");
    new PlaybackWriter(createSoundTest(), PLAYBACK_INPUT_CYCLE_OFFSET).write("movies/soundTest.lsmv");
  }
 
  // Initial Record cycleCounter:  202372
  // First frame end cycleCounter: 206592 -> Offset: FRAME_CYCLES - (206592 - 202372) = 136228
  /** Initial cycle offset when using playback.gbc */
  public static final int PLAYBACK_INPUT_CYCLE_OFFSET = FRAME_CYCLES + 136228;
  /**
   * Returns a set of playback operations to check the calibration of the PLAYBACK_INPUT_CYCLE_OFFSET value.
   * After the first Wait, the first input of the Record should fall into the a frame.
   * After the second Wait, the first input of the Record should fall into the previous frame.
   * I.e., the new input frames should be on the 17th and 34th input.
   */
  public static ArrayList<PlaybackOperation> calibratePlaybackInputCycleOffsetOperations() {
    Wait wait = new Wait( // 3880
        FRAME_CYCLES - (PLAYBACK_INPUT_CYCLE_OFFSET % FRAME_CYCLES) // Cycles left to next input frame boundary
        - Record.getCycleCount(4) // Time taken up by initial Record call
        - Record.getFirstInputCycles()); // Time until first input in second Record call
    Wait wait2 = new Wait(FRAME_CYCLES - Record.getCycleCount(4) - 4);
    Record record = Record.forStackFrames(Arrays.asList(wait.getJumpAddress(), PlaybackAddresses.RECORD));
    Record record2 = Record.forStackFrames(Arrays.asList(wait2.getJumpAddress(), PlaybackAddresses.RECORD));
    
    ArrayList<PlaybackOperation> playback = new ArrayList<>();
    playback.add(record);
    playback.add(wait);
    playback.add(record2);
    playback.add(wait2);
    playback.add(Record.forStackFrames(Arrays.asList(wait2.getJumpAddress())));
    playback.add(wait2);
    return playback;
  }

  public static ArrayList<PlaybackOperation> calibrateVramAccessible() {
    WriteHByteDirect setScx = new WriteHByteDirect(GbConstants.SCX, 0x7);
    WriteHByteDirect setWx = new WriteHByteDirect(GbConstants.WX, 0xa7);
    WriteHByteDirect setWy = new WriteHByteDirect(GbConstants.WY, 0x1);
    WriteHByteDirect enableLcd = new WriteHByteDirect(GbConstants.LCDC, 0xa3);
    WriteByteDirect writeVram = new WriteByteDirect(0x8000, 0xa3, -1);
    Wait wait = new Wait(GbConstants.LINE_CYCLES + 520 - (enableLcd.getCycleCount() - enableLcd.getEndOutputCycle()) - writeVram.getStartOutputCycle());
    Record record = Record.forStackFrames(Arrays.asList(
        setScx.getJumpAddress(),
        setWx.getJumpAddress(),
        setWy.getJumpAddress(),
        enableLcd.getJumpAddress(),
        wait.getJumpAddress(),
        writeVram.getJumpAddress()));
    
    ArrayList<PlaybackOperation> playback = new ArrayList<>();
    playback.add(record);
    playback.add(setScx);
    playback.add(setWx);
    playback.add(setWy);
    playback.add(enableLcd);
    playback.add(wait);
    playback.add(writeVram);
    return playback;
  }

  public static ArrayList<PlaybackOperation> createSoundTest() throws IOException {
    PlaySound playSound = SoundUtil.rewriteTo4bitFancy(SoundUtil.read16bitPcmMonoAudio("audio/in.wav"));
//    PlaySound playSound = createDummySound();
    SoundUtil.write16bitPcmMonoAudio("audio/out.wav", playSound);
    Record record = Record.forStackFrames(Arrays.asList(
//        0x501,
        playSound.getJumpAddress(),
        PlaybackAddresses.STOP_OPERATIONS));
    
    ArrayList<PlaybackOperation> playback = new ArrayList<>();
    playback.add(record);
    playback.add(playSound);
    return playback;
  }
  
  private static PlaySound createDummySound() {
    int len = 100000;
    int[] samples = new int[len];
    int[] so = new int[len];
    for (int i = 0; i < len; i++) {
      samples[i] = 0xeeee;
      so[i] = 7;
    }
    return new PlaySound(samples, so);
  }
}
