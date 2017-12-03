package mrwint.gbtasgen.tools.playback.loganalyzer;

import static mrwint.gbtasgen.tools.playback.loganalyzer.GbConstants.FRAME_CYCLES;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import mrwint.gbtasgen.tools.playback.loganalyzer.operation.Fmv;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.PlaySound;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.PlaybackAddresses;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.PlaybackOperation;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.Record;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.Wait;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.WriteByteDirect;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.WriteHByteDirect;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.WriteInitialOperations;
import mrwint.gbtasgen.tools.playback.util.audio.AudioUtil;
import mrwint.gbtasgen.tools.playback.util.audio.GbAudio;
import mrwint.gbtasgen.tools.playback.util.video.GbVideo;
import mrwint.gbtasgen.tools.playback.util.video.SimpleAvi;

public class Calibration {
  
  public static void main(String[] args) throws Exception {
//    new PlaybackWriter(calibratePlaybackInputCycleOffsetOperations(), PLAYBACK_INPUT_CYCLE_OFFSET).write("movies/calibrationTest.lsmv");
//    new PlaybackWriter(calibrateVramAccessible(), PLAYBACK_INPUT_CYCLE_OFFSET).write("movies/calibrationTest.lsmv");
//    new PlaybackWriter(createSoundTest(), PLAYBACK_INPUT_CYCLE_OFFSET).write("movies/soundTest.lsmv");
    new PlaybackWriter(createFmvTest(), PLAYBACK_INPUT_CYCLE_OFFSET).write("movies/fmvTest.lsmv");
  }
 
  // Initial Record cycleCounter:  197364
  // First frame end cycleCounter: 206600 -> Offset: FRAME_CYCLES - (206600 - 197364) + 4 = 131216
  /** Initial cycle offset when using playback.gbc */
  public static final int PLAYBACK_INPUT_CYCLE_OFFSET = FRAME_CYCLES + 131216;
  /**
   * Returns a set of playback operations to check the calibration of the PLAYBACK_INPUT_CYCLE_OFFSET value.
   * After the first Wait, the first input of the Record should fall into the new frame.
   * After the second Wait, the first input of the Record should fall into the previous frame.
   * I.e., the new input frames should be on the 17th and 34th input.
   */
  public static ArrayList<PlaybackOperation> calibratePlaybackInputCycleOffsetOperations() {
    WriteInitialOperations writeInitialOperations = new WriteInitialOperations();
    Wait wait = new Wait( // 3880
        2 * FRAME_CYCLES - (PLAYBACK_INPUT_CYCLE_OFFSET % FRAME_CYCLES) // Cycles left to next input frame boundary
        - writeInitialOperations.getCycleCount() // Time taken up by initial operation write
        - Record.getCycleCount(4) // Time taken up by initial Record call
        - Record.getFirstInputCycles()); // Time until first input in second Record call
    Wait wait2 = new Wait(FRAME_CYCLES - Record.getCycleCount(4) - 4);
    Record record = Record.forStackFrames(Arrays.asList(wait.getJumpAddress(), PlaybackAddresses.RECORD));
    Record record2 = Record.forStackFrames(Arrays.asList(wait2.getJumpAddress(), PlaybackAddresses.RECORD));
    
    ArrayList<PlaybackOperation> playback = new ArrayList<>();
    playback.add(writeInitialOperations);
    playback.add(record);
    playback.add(wait);
    playback.add(record2);
    playback.add(wait2);
    playback.add(Record.forStackFrames(Arrays.asList(wait2.getJumpAddress())));
    playback.add(wait2);
    return playback;
  }

  public static ArrayList<PlaybackOperation> calibrateVramAccessible() {
    WriteHByteDirect setScx = new WriteHByteDirect(GbConstants.SCX, 0x7, true);
    WriteHByteDirect setWx = new WriteHByteDirect(GbConstants.WX, 0xa7, true);
    WriteHByteDirect setWy = new WriteHByteDirect(GbConstants.WY, 0x1, true);
    WriteHByteDirect enableLcd = new WriteHByteDirect(GbConstants.LCDC, 0xa3, true);
    WriteByteDirect writeVram = new WriteByteDirect(0x8000, 0xa3, -1, true);
    Wait wait = new Wait(GbConstants.LINE_CYCLES + 520 - (enableLcd.getCycleCount() - enableLcd.getEndOutputCycle()) - writeVram.getStartOutputCycle());
    Record record = Record.forStackFrames(Arrays.asList(
        setScx.getJumpAddress(),
        setWx.getJumpAddress(),
        setWy.getJumpAddress(),
        enableLcd.getJumpAddress(),
        wait.getJumpAddress(),
        writeVram.getJumpAddress()));
    
    ArrayList<PlaybackOperation> playback = new ArrayList<>();
    playback.add(new WriteInitialOperations());
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
    PlaySound playSound = new PlaySound(AudioUtil.rewriteTo4bitFancy(AudioUtil.read16bitPcmMonoAudio("audio/in.wav")));
    AudioUtil.write16bitPcmMonoAudio("audio/outLQ.wav", playSound);
    Record record = Record.forStackFrames(Arrays.asList(
//        0x501,
        playSound.getJumpAddress(),
        PlaybackAddresses.STOP_OPERATIONS_ROM));
    
    ArrayList<PlaybackOperation> playback = new ArrayList<>();
    playback.add(new WriteInitialOperations());
    playback.add(record);
    playback.add(playSound);
    return playback;
  }

  public static ArrayList<PlaybackOperation> createFmvTest() throws IOException {
    GbAudio audio = AudioUtil.rewriteTo4bitFancy(AudioUtil.read16bitPcmMonoAudio("audio/sb.wav"));
    GbVideo video = new GbVideo(SimpleAvi.fromFile("video/sb.avi"));
    Fmv fmv = new Fmv(video, audio);
    
    video.toSimpleAvi().writeTo("video/out.avi");

//    PlaySound tmpPlaySound = new PlaySound(audio);
//    System.out.println("compare o a");
//    compareGbAudio(audio, AudioUtil.toGbAudio(tmpPlaySound));
//    System.out.println("compare o b");
//    compareGbAudio(audio, AudioUtil.toGbAudio(fmv));
//    System.out.println("compare a b");
//    compareGbAudio(AudioUtil.toGbAudio(tmpPlaySound), AudioUtil.toGbAudio(fmv));
//    AudioUtil.write16bitPcmMonoAudio("audio/fmvout1.wav", AudioUtil.toGbAudio(tmpPlaySound));
//    AudioUtil.write16bitPcmMonoAudio("audio/fmvout2.wav", AudioUtil.toGbAudio(fmv));

    Record record = Record.forStackFrames(Arrays.asList(
        fmv.getJumpAddress(),
        PlaybackAddresses.STOP_OPERATIONS_ROM));
    
    ArrayList<PlaybackOperation> playback = new ArrayList<>();
    playback.add(new WriteInitialOperations());
    playback.add(record);
    playback.add(fmv);
    return playback;
  }
  
  private static void compareGbAudio(GbAudio a, GbAudio b) {
    for (int i = 0; i < Math.min(a.soValues.length,  b.soValues.length); i++) {
      if (a.soValues[i] != b.soValues[i]) {
        System.out.println("diff so " + i + ": " + a.soValues[i] + " " + b.soValues[i]);
        break;
      }
    }
    for (int i = 0; i < Math.min(a.samples.length,  b.samples.length); i++) {
      if (a.samples[i] != b.samples[i]) {
        System.out.println("diff sample " + i + ": " + a.samples[i] + " " + b.samples[i]);
        break;
      }
    }
  }
}
