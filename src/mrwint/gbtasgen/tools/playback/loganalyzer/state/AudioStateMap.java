package mrwint.gbtasgen.tools.playback.loganalyzer.state;

import java.util.ArrayList;

import mrwint.gbtasgen.tools.playback.loganalyzer.GbConstants;
import mrwint.gbtasgen.tools.playback.loganalyzer.RawMemoryMap;
import mrwint.gbtasgen.tools.playback.loganalyzer.TimeStamp;

public class AudioStateMap {
  public static final int AUDIO_CHUNK_LINE_SIZE = 50; // ~1/3 frame
  public static final int AUDIO_CHUNK_MIN_SIZE = 30; // must be <= 2/3 * AUDIO_CHUNK_LINE_SIZE

  public static class AudioChunk {
    public int from;
    public int to;
    public ArrayList<AudioOperation> operations = new ArrayList<>();
  }
  public static class AudioOperation {
    public final int address;
    public final int value;
    public AudioOperation(int address, int value) {
      this.address = address;
      this.value = value;
    }
  }

  public int numFrames;
  public int frameOffset;

  public ArrayList<AudioChunk> chunks = new ArrayList<>();

  public AudioStateMap(StateMap stateMap, RawMemoryMap memoryMap, int inputScene, int firstInputFrame, int numFrames) {
    this.numFrames = numFrames;
    TimeStamp startTime = new TimeStamp(inputScene, firstInputFrame, -1);
    TimeStamp endTime = new TimeStamp(inputScene, firstInputFrame + numFrames, -1);
    
    int[] curWaveRam = new int[0x10];
    for (int i = 0; i < 0x10; i++)
      curWaveRam[i] = -1;
    int initialFF24 = memoryMap.getAudioRegister(20).getValueAt(startTime, 0x77) & 0x77;
    int initialFF25 = memoryMap.getAudioRegister(21).getValueAt(startTime, 0xF3);
    int initialFF26 = memoryMap.getAudioRegister(22).getValueAt(startTime, 0xF1);
    boolean firstChunk = true;

    // Find first event
    TimeStamp firstEventTime = null;
    int firstIndex = -1;
    for (int i = 0; i < 23; i++) {
      TimeStamp nextEventTime = memoryMap.getAudioRegister(i).getNextStateStartTime(startTime);
      if (nextEventTime != null && (firstEventTime == null || firstEventTime.compareTo(nextEventTime) > 0)) {
        firstEventTime = nextEventTime;
        firstIndex = i;
      }
    }
    if (firstEventTime == null || firstEventTime.compareTo(endTime) >= 0)
      return;

    while (true) {
      TimeStamp curEventTime = firstEventTime;
      int curEventLine = toLineIndex(curEventTime, firstInputFrame);
      AudioChunk chunk = new AudioChunk();
      chunk.from = curEventLine;
      chunk.to = curEventLine;
      chunk.operations.add(new AudioOperation(0xff10 + firstIndex, memoryMap.getAudioRegister(firstIndex).getValueAt(curEventTime, 0)));
      
      TimeStamp minNextEventTime;
      int nextIndex;
      while (true) {
        minNextEventTime = null;
        nextIndex = -1;
        for (int i = 0; i < 23; i++) {
          TimeStamp nextEventTime = memoryMap.getAudioRegister(i).getNextStateStartTime(curEventTime);
          if (nextEventTime != null && (minNextEventTime == null || minNextEventTime.compareTo(nextEventTime) > 0)) {
            minNextEventTime = nextEventTime;
            nextIndex = i;
          }
        }
        if (minNextEventTime == null || minNextEventTime.compareTo(endTime) >= 0)
          break;
        if (toLineIndex(minNextEventTime, firstInputFrame) - curEventLine > AUDIO_CHUNK_LINE_SIZE)
          break;

        curEventTime = minNextEventTime;
        curEventLine = toLineIndex(curEventTime, firstInputFrame);
        chunk.to = curEventLine;
        chunk.operations.add(new AudioOperation(0xff10 + nextIndex, memoryMap.getAudioRegister(nextIndex).getValueAt(curEventTime, 0)));
      }

      // Update wave RAM if necessary
      boolean containsCh3Enable = chunk.operations.stream().anyMatch(x -> x.address == 0xff1a && x.value >= 0x80);
      boolean containsCh3Initial = chunk.operations.stream().anyMatch(x -> x.address == 0xff1e && x.value >= 0x80);
      if (containsCh3Initial) {
        boolean waveRamChanged = false;
        for (int i = 0; i < 0x10; i++)
          waveRamChanged |= memoryMap.getWaveRam(i, curEventTime) != curWaveRam[i];
        if (waveRamChanged) {
          if (!containsCh3Enable)
            chunk.operations.add(0, new AudioOperation(0xff1a, 0x80)); // enable ch3 again if not done anyway
          for (int i = 0; i < 0x10; i++) {
            int curVal = memoryMap.getWaveRam(i, curEventTime);
            if (curVal != curWaveRam[i]) {
              curWaveRam[i] = curVal;
              chunk.operations.add(0, new AudioOperation(0xff30 + i, curVal)); // disable ch3 so wave RAM becomes accessible
            }
          }
          chunk.operations.add(0, new AudioOperation(0xff1a, 0)); // disable ch3 so wave RAM becomes accessible
        }
      }
      
      // Set initial control registers
      if (firstChunk) {
        firstChunk = false;
        boolean containsFF24 = chunk.operations.stream().anyMatch(x -> x.address == 0xff24);
        boolean containsFF25 = chunk.operations.stream().anyMatch(x -> x.address == 0xff25);
        boolean containsFF26 = chunk.operations.stream().anyMatch(x -> x.address == 0xff26);
        if (!containsFF24) chunk.operations.add(0, new AudioOperation(0xff24, initialFF24));
        if (!containsFF25) chunk.operations.add(0, new AudioOperation(0xff25, initialFF25));
        if (!containsFF26) chunk.operations.add(0, new AudioOperation(0xff26, initialFF26)); // ff26 will be initial operation.
      }
      
      if (chunk.to - chunk.from < AUDIO_CHUNK_MIN_SIZE) {
        chunk.from -= (AUDIO_CHUNK_MIN_SIZE + chunk.from - chunk.to)/2;
        if (chunk.from <= 0) chunk.from = 0;
        chunk.to = chunk.from + AUDIO_CHUNK_MIN_SIZE;
        if (chunk.to >= numFrames * GbConstants.FRAME_LINES) {
          chunk.to = numFrames * GbConstants.FRAME_LINES - 1;
          chunk.from = chunk.to - AUDIO_CHUNK_MIN_SIZE;
        }
      }
      
      chunks.add(chunk);
      
      if (minNextEventTime == null || minNextEventTime.compareTo(endTime) >= 0)
        break;
      firstEventTime = minNextEventTime;
      firstIndex = nextIndex;
    }
    
    outputStats();
  }
  
  private static int toLineIndex(TimeStamp time, int firstInputFrame) {
    return (time.frame - firstInputFrame) * GbConstants.FRAME_LINES + time.frameCycle / GbConstants.LINE_CYCLES;
  }
  
  public void outputStats() {
    System.out.println("num chunks: " + chunks.size());
    
    int minLen = Integer.MAX_VALUE;
    int maxLen = Integer.MIN_VALUE;
    int minSize = Integer.MAX_VALUE;
    int maxSize = Integer.MIN_VALUE;
    int sumOperations = 0;
    for (AudioChunk chunk : chunks) {
      minLen = Math.min(minLen, chunk.to - chunk.from);
      maxLen = Math.max(maxLen, chunk.to - chunk.from);
      minSize = Math.min(minSize, chunk.operations.size());
      maxSize = Math.max(maxSize, chunk.operations.size());
      sumOperations += chunk.operations.size();
    }
    System.out.println("chunk length min: " + minLen + " max: " + maxLen);
    System.out.println("chunk size min: " + minSize + " max: " + maxSize);
    System.out.println("total operations: " + sumOperations);
    
    int minDist = Integer.MAX_VALUE;
    int maxDist = Integer.MIN_VALUE;
    for (int i = 0; i < chunks.size() - 1; i++) {
      minDist = Math.min(minDist, chunks.get(i+1).from - chunks.get(i).to);
      maxDist = Math.max(maxDist, chunks.get(i+1).from - chunks.get(i).to);
    }
    System.out.println("chunk dist min: " + minDist + " max: " + maxDist);
    System.out.println();
  }
}
