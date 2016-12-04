package mrwint.gbtasgen.tools.playback.loganalyzer;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;

import mrwint.gbtasgen.tools.playback.loganalyzer.operation.PlaybackAddresses;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.PlaybackOperation;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.Record;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.Wait;
import mrwint.gbtasgen.tools.playback.loganalyzer.state.AudioStateMap;
import mrwint.gbtasgen.tools.playback.loganalyzer.state.BackgroundStateMap;
import mrwint.gbtasgen.tools.playback.loganalyzer.state.SpriteStateMap;
import mrwint.gbtasgen.tools.playback.loganalyzer.state.StateMap;

public class LogAnalyzer {
  // Initial Record cycleCounter: 202372
  // First frame end cycleCounter: 206592
  public static void main(String[] args) throws Exception {
    new LogAnalyzer();
    
//    new PlaybackWriter(generateDummyPlayback(), 70224*2 + 136228).write("movies/playbackTest.lsmv");
  }


  public LogAnalyzer() throws Exception {
    
    TreeMap<TimeStamp, LogInput> log = readLog();
    System.out.println("Read " + log.size() + " log entries");
    int maxScene = log.lastKey().scene;
    for (int scene = 0; scene <= maxScene; scene++) {
      int maxFrame = log.lowerKey(new TimeStamp(scene + 1, -1, 0)).frame;
      System.out.println("Scene " + scene + "/" + maxScene + " with " + (maxFrame + 1) + " frames");
    }

    RawMemoryMap memoryMap = new RawMemoryMap(log);
    System.out.println("Memory map created");
    log = null; // drop log

    StateMap stateMap = new StateMap();
    BackgroundStateMap tilesState1 = new BackgroundStateMap(stateMap, memoryMap, 53, 0, 4000);
//    BackgroundStateMap tilesState2 = new BackgroundStateMap(stateMap, memoryMap, 3, 200, 200);
    SpriteStateMap spriteStates1 = new SpriteStateMap(stateMap, memoryMap, 53, 0, 4000);
//    SpriteStateMap spriteStates2 = new SpriteStateMap(stateMap, memoryMap, 3, 200, 200);
    AudioStateMap audioStates1 = new AudioStateMap(stateMap, memoryMap, 53, 0, 4000);
//        .addScene(memoryMap, 10, 0, 2000);
//        .addScene(memoryMap, 7, 0, 20)
//        .addScene(memoryMap, 10, 0, 20);
    System.out.println("State map created");
    memoryMap = null; // drop memory map
    
//    tilesState2.frameOffset = 200;
//    spriteStates2.frameOffset = 200;
//    stateMap.assembleScene(new BackgroundStateMap[] {tilesState1, tilesState2}, new SpriteStateMap[] {spriteStates1, spriteStates2});
    stateMap.assembleScene(new BackgroundStateMap[] {tilesState1}, new SpriteStateMap[] {spriteStates1}, new AudioStateMap[] {audioStates1});
//    stateMap.assembleScene(new BackgroundStateMap[] {tilesState2}, new SpriteStateMap[] {spriteStates2});
//    System.out.println("Scene assembled");
    tilesState1 = null; // drop tiles
//    tilesState2 = null; // drop tiles
    spriteStates1 = null; // drop sprites
//    spriteStates2 = null; // drop sprites
    audioStates1 = null; // drop audio

    System.out.println("Scene assembled");
    stateMap.calculateTilePositions();
    System.out.println("Tile positions calculated");
    stateMap.calculateBgPalettePositions();
    System.out.println("BG palette positions calculated");
    stateMap.calculateObjPalettePositions();
    System.out.println("Obj palette positions calculated");
    stateMap.calculateOamPositions();
    System.out.println("OAM positions calculated");
    stateMap.compressStates();
    System.out.println("States compressed");

    ArrayList<TimedAction> actions = stateMap.generateActionList();
    ArrayList<PlaybackOperation> playback = new PlaybackAssembler(actions).assemble(stateMap.sceneAccessibilityStates);
    new PlaybackWriter(playback, Calibration.PLAYBACK_INPUT_CYCLE_OFFSET).write("movies/playback3Test.lsmv");
  }
  
  public static ArrayList<PlaybackOperation> generateDummyPlayback() {
    Wait wait = new Wait(3880);
    Record record = Record.forStackFrames(Arrays.asList(wait.getJumpAddress(), PlaybackAddresses.RECORD));
    Wait wait2 = new Wait(70224*2 - record.getCycleCount() - 4);
    Record record2 = Record.forStackFrames(Arrays.asList(wait2.getJumpAddress(), PlaybackAddresses.RECORD));
    
    ArrayList<PlaybackOperation> playback = new ArrayList<>();
    playback.add(record);
    playback.add(wait);
    playback.add(record2);
    playback.add(wait2);
    playback.add(Record.forStackFrames(Arrays.asList(wait.getJumpAddress())));
    playback.add(wait);
    return playback;
  }
  
  public static TreeMap<TimeStamp, LogInput> readLog() throws FileNotFoundException {
    TreeMap<TimeStamp, LogInput> log = new TreeMap<>();
    Scanner s = new Scanner(new BufferedInputStream(new FileInputStream("log.txt")));
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
