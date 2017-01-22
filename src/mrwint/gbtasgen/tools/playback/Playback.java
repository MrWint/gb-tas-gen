package mrwint.gbtasgen.tools.playback;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

import mrwint.gbtasgen.movie.GbMovie;
import mrwint.gbtasgen.movie.LsmvMovie;
import mrwint.gbtasgen.rom.PlaybackRomInfo;
import mrwint.gbtasgen.segment.pokemon.gen1.ACE;
import mrwint.gbtasgen.tools.playback.loganalyzer.Calibration;
import mrwint.gbtasgen.tools.playback.loganalyzer.GbConstants;
import mrwint.gbtasgen.tools.playback.loganalyzer.LogInput;
import mrwint.gbtasgen.tools.playback.loganalyzer.PlaybackAssembler;
import mrwint.gbtasgen.tools.playback.loganalyzer.RawMemoryMap;
import mrwint.gbtasgen.tools.playback.loganalyzer.SceneDesc;
import mrwint.gbtasgen.tools.playback.loganalyzer.TimeStamp;
import mrwint.gbtasgen.tools.playback.loganalyzer.TimedAction;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.PlaybackOperation;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.WriteInitialOperations;
import mrwint.gbtasgen.tools.playback.loganalyzer.state.StateMap;
import mrwint.gbtasgen.tools.playback.loganalyzer.state.StateMap.SceneAccessibilityState;

public class Playback {
  // Initial Record cycleCounter: 202372
  // First frame end cycleCounter: 206592
  public static void main(String[] args) throws Exception {
    new Playback();
  }


  public Playback() throws Exception {
    ArrayList<PlaybackOperation> playback = getTestOperations();
    playback.add(0, new WriteInitialOperations());
    
    // Export to playback
    LsmvMovie.exportMovie(new GbMovie(new PlaybackRomInfo()).appendInputs(PlaybackOperation.toInputs(playback, Calibration.PLAYBACK_INPUT_CYCLE_OFFSET)), "playback");
    
    // Export to ACE
    LsmvMovie.exportMovie(ACE.getAceMovie(playback), "playback");
  }

  private static ArrayList<PlaybackOperation> getTestOperations() throws FileNotFoundException {
    return assemblePlaybackOperations(new String[]{"playback_s04.log", "smb.log"},
        new SceneDescs(new SceneDesc(0, 8, 0, 500, 25, false).noSprites().noAudio()),
        new SceneDescs(new SceneDesc(1, 7, 2200, 56, 10, false).noSprites().noAudio()),
        new SceneDescs(new SceneDesc(0, 8, 500, 250, 50, false).noSprites().noAudio()));
  }

  private static ArrayList<PlaybackOperation> getScene02Operations() throws FileNotFoundException {
    return assemblePlaybackOperations(new String[]{"playback_s02.log", "playback_s03.log", "tetris.log", "TLoZLA.log", "playback_s04.log", "smb.log", "playback_s01.log"},
        new SceneDescs(new SceneDesc(6, 9, 0, 676, 676, false)),
        new SceneDescs(new SceneDesc(0, 10, 0, 935, 935, false)),
        new SceneDescs(new SceneDesc(0, 11, 0, 349, 349, false)),
        new SceneDescs(new SceneDesc(1, 9, 0, 1542, 1542, false)),
        new SceneDescs(new SceneDesc(1, 10, 0, 903, 903, false)),
        new SceneDescs(new SceneDesc(1, 11, 0, 350, 350, false)),
        new SceneDescs(new SceneDesc(1, 12, 0, 367, 367, false)),
        new SceneDescs(new SceneDesc(2, 6, 0, 1464, 1464, true), new SceneDesc(2, 6, 1464, 787, 600, true)),
        new SceneDescs(new SceneDesc(2, 7, 0, 2473, 1200, true)),
        new SceneDescs(new SceneDesc(1, 12, 367, 255, 255, false)),
        new SceneDescs(new SceneDesc(3, 37, 0, 744, 744, false)),
        new SceneDescs(new SceneDesc(0, 7, 0, 182, 182, false)),
        new SceneDescs(new SceneDesc(4, 8, 0, 500, 500, false)),
        new SceneDescs(new SceneDesc(5, 7, 0, 2256, 2256, false)),
        new SceneDescs(new SceneDesc(4, 8, 500, 385, 385, false), new SceneDesc(4, 11, 0, 185, 185, false)));
  }

  private static ArrayList<PlaybackOperation> getTetrisOperations() throws FileNotFoundException {
    return assemblePlaybackOperations(new String[]{"tetris.log"},
        new SceneDescs(new SceneDesc(0, 6, 0, 1464, 1464, true), new SceneDesc(0, 6, 1464, 787, 600, true)),
        new SceneDescs(new SceneDesc(0, 7, 0, 2473, 1200, true)));
  }
  
  private static class SceneDescs {
    private SceneDesc[] descs;
    public SceneDescs(SceneDesc... descs) {
      this.descs = descs;
    }
  }
  
  private static ArrayList<PlaybackOperation> assemblePlaybackOperations(String[] logFiles, SceneDescs... descss) throws FileNotFoundException {
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

    System.out.println("Calculating tile positions...");
    stateMap.calculateTilePositions();
    System.out.println("Tile positions calculated");
    System.out.println("Calculating BG palette positions...");
    stateMap.calculateBgPalettePositions();
    System.out.println("BG palette positions calculated");
    System.out.println("Calculating obj palette positions...");
    stateMap.calculateObjPalettePositions();
    System.out.println("Obj palette positions calculated");
    System.out.println("Calculating OAM positions...");
    stateMap.calculateOamPositions();
    System.out.println("OAM positions calculated");
    System.out.println("Compressing states...");
    stateMap.compressStates();
    System.out.println("States compressed");
    
    ArrayList<TimedAction> actionList = stateMap.generateActionList();
    ArrayList<SceneAccessibilityState> accessibility = stateMap.sceneAccessibilityStates;
    stateMap = null; // drop state map

    ArrayList<PlaybackOperation> operations = new PlaybackAssembler(actionList).assemble(accessibility);
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
