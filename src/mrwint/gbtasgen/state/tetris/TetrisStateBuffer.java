package mrwint.gbtasgen.state.tetris;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import mrwint.gbtasgen.metric.comparator.Comparator;
import mrwint.gbtasgen.state.Gameboy;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.state.StateBuffer;
import mrwint.gbtasgen.util.Util;

public class TetrisStateBuffer {

  public Map<Short, StateBuffer> stateBufferMap;
  public int maxBufferSize;

  public TetrisStateBuffer() {
    this(StateBuffer.MAX_BUFFER_SIZE);
  }

  public TetrisStateBuffer(int maxBufferSize) {
    this.stateBufferMap = new TreeMap<Short,StateBuffer>();
    this.maxBufferSize = maxBufferSize;
  }

  public static short getNextPieces(Gameboy gb, State s) {
    gb.restore(s);
    Util.runUntil(0, () -> gb.readMemory(gb.tetris.hCurPieceState) + gb.readMemory(gb.tetris.hBoardUpdateState), Comparator.EQUAL, 0);
//    State.steps(3);
    int curPiece = (gb.readMemory(gb.tetris.wCurPiece) >> 2);
    int previewPiece = (gb.readMemory(gb.tetris.wPreviewPiece) >> 2);
    int nextPreviewPiece = (gb.readMemory(gb.tetris.hNextPreviewPiece) >> 2);
    return (short)(curPiece | (previewPiece << 3) | (nextPreviewPiece << 6));
  }

  public boolean addState(Gameboy gb, State s) {
    return addState(s, getNextPieces(gb, s));
  }

  public boolean addState(State s, short nextPieces) {
    if (!stateBufferMap.containsKey(nextPieces))
      stateBufferMap.put(nextPieces, new StateBuffer(maxBufferSize));
    return stateBufferMap.get(nextPieces).addState(s);
  }

  public void addAll(StateBuffer buf, short nextPieces) {
    for (State s : buf.getStates())
      addState(s, nextPieces);
  }

  public void addAll(TetrisStateBuffer buf) {
    for(Entry<Short, StateBuffer> e : buf.getMap().entrySet())
      addAll(e.getValue(), e.getKey());
  }

  public Map<Short, StateBuffer> getMap() {
    return stateBufferMap;
  }

  public boolean isEmpty() {
    return stateBufferMap.isEmpty();
  }

  public int size() {
    int ret = 0;
    for(StateBuffer subMap : stateBufferMap.values())
      ret += subMap.size();
    return ret;
  }

  public void save(String filename, String suffix) {
    try {
      String path = "saves/" + filename + suffix;
      ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
      save(oos);
      oos.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void save(ObjectOutputStream oos) {
    try {
      oos.writeInt(maxBufferSize);
      oos.writeInt(stateBufferMap.size());
      for(Entry<Short, StateBuffer> e : stateBufferMap.entrySet()) {
        oos.writeShort(e.getKey());
        e.getValue().save(oos);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  public static TetrisStateBuffer load(String filename, String suffix) {
    try {
      String path = "saves/" + filename + suffix;

      ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
      TetrisStateBuffer ret = load(ois);
      ois.close();
      return ret;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static TetrisStateBuffer load(ObjectInputStream ois) {
    try {
      int maxBufferSize = ois.readInt();
      TetrisStateBuffer ret = new TetrisStateBuffer(maxBufferSize);
      int len = ois.readInt();
      for(int i=0;i<len;i++) {
        short nextPieces = ois.readShort();
        StateBuffer buf = StateBuffer.load(ois);
        ret.addAll(buf, nextPieces);
      }
      return ret;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
}
