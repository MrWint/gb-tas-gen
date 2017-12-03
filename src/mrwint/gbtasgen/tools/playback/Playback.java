package mrwint.gbtasgen.tools.playback;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import mrwint.gbtasgen.movie.GbMovie;
import mrwint.gbtasgen.movie.LsmvMovie;
import mrwint.gbtasgen.rom.PlaybackRomInfo;
import mrwint.gbtasgen.segment.pokemon.gen1.ACE;
import mrwint.gbtasgen.tools.playback.loganalyzer.Calibration;
import mrwint.gbtasgen.tools.playback.loganalyzer.GbConstants;
import mrwint.gbtasgen.tools.playback.loganalyzer.LogInput;
import mrwint.gbtasgen.tools.playback.loganalyzer.OamEntry;
import mrwint.gbtasgen.tools.playback.loganalyzer.PlaybackAssembler;
import mrwint.gbtasgen.tools.playback.loganalyzer.RawMemoryMap;
import mrwint.gbtasgen.tools.playback.loganalyzer.SceneDesc;
import mrwint.gbtasgen.tools.playback.loganalyzer.TimeStamp;
import mrwint.gbtasgen.tools.playback.loganalyzer.TimedAction;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.Fmv;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.PlaySound;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.PlaySoundHQ;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.PlaySoundSetBg;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.PlaySoundSetBg.BgOp;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.PlaybackAddresses;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.PlaybackOperation;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.Record;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.Wait;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.WriteHByteDirect;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.WriteInitialOperations;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.WriteOamDirect;
import mrwint.gbtasgen.tools.playback.loganalyzer.state.StateMap;
import mrwint.gbtasgen.tools.playback.loganalyzer.state.StateMap.ExtraBg;
import mrwint.gbtasgen.tools.playback.loganalyzer.state.StateMap.SceneAccessibilityState;
import mrwint.gbtasgen.tools.playback.util.audio.AudioUtil;
import mrwint.gbtasgen.tools.playback.util.audio.GbAudio;
import mrwint.gbtasgen.tools.playback.util.video.GbVideo;
import mrwint.gbtasgen.tools.playback.util.video.SimpleAvi;

public class Playback {
  // Initial Record cycleCounter: 202372
  // First frame end cycleCounter: 206592
  public static void main(String[] args) throws Exception {
    new Playback();
  }


  public Playback() throws Exception {
    ArrayList<PlaybackOperation> playback = getScene02Operations();
    playback.add(0, new WriteInitialOperations());
    
    // Export to playback
    LsmvMovie.exportMovie(new GbMovie(new PlaybackRomInfo()).appendInputs(PlaybackOperation.toInputs(playback, Calibration.PLAYBACK_INPUT_CYCLE_OFFSET)), "placeholder");
    
    // Export to ACE
    LsmvMovie.exportMovie(ACE.getAceMovie(playback), "placeholder");
  }

  private static ArrayList<PlaybackOperation> getTestOperations() throws IOException {
    ExtraTiles extraTiles = new ExtraTiles();
    
    ArrayList<PlaybackOperation> operations = new ArrayList<>();
//    ArrayList<PlaybackOperation> operations = assemblePlaybackOperations(new String[]{"playback_s04.log"},
//        new ExtraBg[] {extraTiles.extraBg},
//        new SceneDescs(new SceneDesc(0, 11, 50, 10, 10, false).noSprites().noBackground(), new SceneDesc(0, 11, 184, 1, 120, false).noAudio()));

//    System.out.println("end time: " + endTime);
//    endTime += Record.getCycleCount(2 * 45);
//    endTime %= GbConstants.FRAME_CYCLES;
//    System.out.println("end time offset: " + endTime);
//    long waitCycles = ((2 * GbConstants.FRAME_CYCLES - endTime) / 4) * 4;
//    System.out.println("wait time: " + waitCycles);
//    Wait wait = new Wait((int) waitCycles);
//    WriteHByteDirect lcdOff = new WriteHByteDirect(GbConstants.LCDC, 0, true);

//    PlaySoundSetBg playSound = new PlaySoundSetBg(AudioUtil.rewriteTo4bitFancy(AudioUtil.read16bitPcmMonoAudio("audio/in_compressed.wav", 1, 1)), extraTiles.getPortalOps());

    GbAudio audio = AudioUtil.rewriteTo4bitFancy(AudioUtil.read16bitPcmMonoAudio("audio/sb.wav"));
    GbVideo video = new GbVideo(SimpleAvi.fromFile("video/sb.avi"));
    Fmv fmv = new Fmv(video, audio);
    
    ArrayList<Integer> stackFrames = new ArrayList<>();
//    stackFrames.add(wait.getJumpAddress());
//    stackFrames.add(playSound.getJumpAddress());
//    stackFrames.add(lcdOff.getJumpAddress());
//    WriteOamDirect[] clearOAM = new WriteOamDirect[40];
//    for (int i = 0; i < 40; i++) {
//      clearOAM[i] = new WriteOamDirect(i, OamEntry.DISABLED, true);
//      stackFrames.add(clearOAM[i].getJumpAddress());
//    }
    stackFrames.add(fmv.getJumpAddress());
    stackFrames.add(PlaybackAddresses.STOP_OPERATIONS_ROM);

    operations.add(Record.forStackFrames(stackFrames));
//    operations.add(wait);
//    operations.add(playSound);
//    operations.add(lcdOff);
//    for (int i = 0; i < 40; i++)
//      operations.add(clearOAM[i]);
    operations.add(fmv);
    
    return operations;
  }

  private static ArrayList<PlaybackOperation> getSmbOperations() throws IOException {
    ExtraTiles extraTiles = new ExtraTiles();

    ArrayList<PlaybackOperation> operations = assemblePlaybackOperations(new String[]{"smb.log"},
        new SceneDescs(
            new SceneDesc(0, 7, 0, 103, 103, false),
            new SceneDesc(0, 7, 103, 146-103, (146-103)*6/7, false),
            new SceneDesc(0, 7, 146, 514-146, (514-146)*5/7, false),
            new SceneDesc(0, 7, 514, 1135-514, (1135-514)*4/7, false),
            new SceneDesc(0, 7, 1135, 1388-1135, (1388-1135)*3/7, false),
            new SceneDesc(0, 7, 1388, 2256-1388, 2256-1388, false)));
//            new SceneDesc(0, 7, 0, 2256, 2256, false)));
    
    return operations;
  }

  private static ArrayList<PlaybackOperation> getScene02Operations() throws IOException {
    ExtraTiles extraTiles = new ExtraTiles();
    
    ArrayList<PlaybackOperation> operations = assemblePlaybackOperations(new String[]{"playback_s02.log", "playback_s03.log", "tetris.log", "TLoZLA.log", "playback_s04.log", "smb.log", "playback_s01.log"},
        new ExtraBg[] {extraTiles.extraBg},
        new SceneDescs(new SceneDesc(6, 9, 0, 676, 676, false)),
        new SceneDescs(new SceneDesc(0, 10, 0, 935, 935, false)),
        new SceneDescs(new SceneDesc(0, 11, 0, 349, 349, false)),
        new SceneDescs(new SceneDesc(1, 9, 0, 50, 50, false).noAudio(), new SceneDesc(1, 9, 50, 1492, 1492, false)),
        new SceneDescs(new SceneDesc(1, 10, 0, 903, 903, false)),
        new SceneDescs(new SceneDesc(1, 11, 0, 350, 350, false)),
        new SceneDescs(new SceneDesc(1, 12, 0, 367, 367, false)),
        new SceneDescs(new SceneDesc(2, 6, 0, 1464, 1464, true), new SceneDesc(2, 6, 1464, 787, 600, true)),
        new SceneDescs(new SceneDesc(2, 7, 0, 2473, 1200, true)),
        new SceneDescs(new SceneDesc(1, 12, 367, 255, 255, false)),
        new SceneDescs(new SceneDesc(3, 37, 0, 744, 744, false)),
        new SceneDescs(new SceneDesc(0, 7, 0, 182, 182, false)),
        new SceneDescs(new SceneDesc(4, 8, 0, 500, 500, false)),
        new SceneDescs(
            new SceneDesc(5, 7, 0, 103, 103, false),
            new SceneDesc(5, 7, 103, 146-103, (146-103)*6/7, false),
            new SceneDesc(5, 7, 146, 514-146, (514-146)*5/7, false),
            new SceneDesc(5, 7, 514, 1135-514, (1135-514)*4/7, false),
            new SceneDesc(5, 7, 1135, 1388-1135, (1388-1135)*3/7, false),
            new SceneDesc(5, 7, 1388, 2256-1388, 2256-1388, false)),
        new SceneDescs(new SceneDesc(4, 8, 500, 385, 385, false), new SceneDesc(4, 11, 0, 185, 185, false), new SceneDesc(4, 11, 184, 1, 120, false).noAudio()));

    System.out.println("end time: " + endTime);
    endTime += Record.getCycleCount(2 * 44);
    endTime %= GbConstants.FRAME_CYCLES;
    System.out.println("end time offset: " + endTime);
    long waitCycles = ((2 * GbConstants.FRAME_CYCLES - endTime) / 4) * 4;
    System.out.println("wait time: " + waitCycles);
    Wait wait = new Wait((int) waitCycles);
    WriteHByteDirect lcdOff = new WriteHByteDirect(GbConstants.LCDC, 0, true);

    PlaySoundSetBg playSound = new PlaySoundSetBg(AudioUtil.rewriteTo4bitFancy(AudioUtil.read16bitPcmMonoAudio("audio/in_compressed.wav", 1, 1)), extraTiles.getPortalOps());

    GbAudio audio = AudioUtil.rewriteTo4bitFancy(AudioUtil.read16bitPcmMonoAudio("audio/sb_compressed.wav"));
    GbVideo video = new GbVideo(SimpleAvi.fromFile("video/sb.avi"));
    Fmv fmv = new Fmv(video, audio);
    
    ArrayList<Integer> stackFrames = new ArrayList<>();
    stackFrames.add(wait.getJumpAddress());
    stackFrames.add(playSound.getJumpAddress());
    stackFrames.add(lcdOff.getJumpAddress());
    WriteOamDirect[] clearOAM = new WriteOamDirect[40];
    for (int i = 0; i < 40; i++) {
      clearOAM[i] = new WriteOamDirect(i, OamEntry.DISABLED, true);
      stackFrames.add(clearOAM[i].getJumpAddress());
    }
    stackFrames.add(fmv.getJumpAddress());

    operations.add(Record.forStackFrames(stackFrames));
    operations.add(wait);
    operations.add(playSound);
    operations.add(lcdOff);
    for (int i = 0; i < 40; i++)
      operations.add(clearOAM[i]);
    operations.add(fmv);
    
    return operations;
  }

  private static ArrayList<PlaybackOperation> getPlaceholderOperations() throws FileNotFoundException {
    return assemblePlaybackOperations(new String[]{"placeholder.log"},
        new SceneDescs(new SceneDesc(0, 9, 0, 2358, 2358, false)));
  }
  
  private static class SceneDescs {
    private SceneDesc[] descs;
    public SceneDescs(SceneDesc... descs) {
      this.descs = descs;
    }
  }
 
  private static long endTime;
  private static ArrayList<PlaybackOperation> assemblePlaybackOperations(String[] logFiles, SceneDescs... descss) throws FileNotFoundException {
    return assemblePlaybackOperations(logFiles, new ExtraBg[0], descss);
  }

  private static ArrayList<PlaybackOperation> assemblePlaybackOperations(String[] logFiles, ExtraBg[] extraBgs, SceneDescs... descss) throws FileNotFoundException {
    RawMemoryMap[] memoryMaps = new RawMemoryMap[logFiles.length];
    for (int i = 0; i < logFiles.length; i++) {
      System.out.println("reading log file " + logFiles[i] + "...");
      TreeMap<TimeStamp, LogInput> log = readLog(logFiles[i]);
      System.out.println("Read " + log.size() + " log entries");

      int maxScene = log.lastKey().scene;
      for (int scene = 0; scene <= maxScene; scene++) {
        int maxFrame = log.lowerKey(new TimeStamp(scene + 1, -1, 0)).frame;
        System.out.println("Scene " + scene + "/" + maxScene + " with " + (maxFrame + 1) + " frames");
      }
      System.out.println();
      System.out.println("Creating memory map...");
      memoryMaps[i] = new RawMemoryMap(log);
      System.out.println("Memory map created");
    }
    
    System.out.println("Assembling " + descss.length + " scenes...");
    StateMap stateMap = new StateMap();
    for (SceneDescs descs : descss)
      stateMap.assembleScene(memoryMaps, descs.descs);
    memoryMaps = null; // drop memory maps
    System.out.println("Scenes assembled");
    
    stateMap.registerExtraBgs(extraBgs);
    stateMap.finishAssembly();
    
    ArrayList<TimedAction> actionList = stateMap.generateActionList();
    ArrayList<SceneAccessibilityState> accessibility = stateMap.sceneAccessibilityStates;
    stateMap = null; // drop state map

    PlaybackAssembler assembler = new PlaybackAssembler(actionList);
    ArrayList<PlaybackOperation> operations = assembler.assemble(accessibility);
    endTime = assembler.endTime;
    System.out.println("Finished assembling " + operations.size() + " playback operations");
    return operations;
  }

  private static TreeMap<TimeStamp, LogInput> readLog(String logName) throws FileNotFoundException {
    TreeMap<TimeStamp, LogInput> log = new TreeMap<>();
    Scanner s = new Scanner(new BufferedInputStream(new FileInputStream(logName)));
    while (s.hasNextInt()) {
      int scene = s.nextInt();
      int frame = s.nextInt();
      int frameCycle = s.nextInt() * GbConstants.DOUBLE_SPEED_FACTOR; // assumes input is normal speed
      int address = s.nextInt(16);
      int value = s.nextInt(16);
      log.put(new TimeStamp(scene, frame, frameCycle), new LogInput(address, value));
    }
    s.close();
    return log;
  }
}
