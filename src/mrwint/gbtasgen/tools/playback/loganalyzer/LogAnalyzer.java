package mrwint.gbtasgen.tools.playback.loganalyzer;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

import mrwint.gbtasgen.tools.playback.loganalyzer.operation.PlaybackOperation;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.Record;
import mrwint.gbtasgen.tools.playback.loganalyzer.operation.Wait;

public class LogAnalyzer {
  // Initial Record cycleCounter: 202372
  // First frame end cycleCounter: 206592
  public static void main(String[] args) throws Exception {
//    new LogAnalyzer();
    
    new PlaybackWriter(generateDummyPlayback(), 70224*2 + 136228).write("movies/playbackTest.lsmv");
  }

  TreeMap<TimeStamp, LogInput> log = new TreeMap<>();
  
  RawMemoryMap memoryMap;
  StateMap stateMap;

  public LogAnalyzer() throws Exception {
    readLog();
    System.out.println("Read " + log.size() + " log entries");
    memoryMap = new RawMemoryMap(log);
    System.out.println("Memory map created");
    int maxScene = log.lastKey().scene;
    for (int scene = 0; scene <= maxScene; scene++) {
      int maxFrame = log.lowerKey(new TimeStamp(scene + 1, -1, 0)).frame;
      System.out.println("Scene " + scene + "/" + maxScene + " with " + (maxFrame + 1) + " frames");
    }
    stateMap = new StateMap()
        .addScene(memoryMap, 10, 500, 500);
    System.out.println("State map created");
    stateMap.calculateTilePositions();
    System.out.println("Tile positions calculated");
    stateMap.calculateBgPalettePositions();
    System.out.println("BG palette positions calculated");
    ArrayList<TimedAction> actions = stateMap.generateActionList();
    ArrayList<PlaybackOperation> playback = new PlaybackAssembler(actions).assemble();
    new PlaybackWriter(playback, 70224).write("movies/playbackTest.lsmv");
  }
  
  public static ArrayList<PlaybackOperation> generateDummyPlayback() {
    Wait wait = new Wait(3880);
    Record record = Record.forStackFrames(Arrays.asList(wait.getJumpAddress(), Record.JUMP_ADDRESS));
    Wait wait2 = new Wait(70224*2 - record.getCycleCount() - 4);
    Record record2 = Record.forStackFrames(Arrays.asList(wait2.getJumpAddress(), Record.JUMP_ADDRESS));
    
    ArrayList<PlaybackOperation> playback = new ArrayList<>();
    playback.add(record);
    playback.add(wait);
    playback.add(record2);
    playback.add(wait2);
    playback.add(Record.forStackFrames(Arrays.asList(wait.getJumpAddress())));
    playback.add(wait);
    return playback;
  }
  
  public void readLog() throws FileNotFoundException {
    Scanner s = new Scanner(new BufferedInputStream(new FileInputStream("log.txt")));
    while (s.hasNextInt()) {
      int scene = s.nextInt();
      int frame = s.nextInt();
      int frameCycle = s.nextInt();
      int address = s.nextInt(16);
      int value = s.nextInt(16);
      log.put(new TimeStamp(scene, frame, frameCycle), new LogInput(address, value));
    }
    s.close();
  }
}
