package mrwint.gbtasgen.state;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import mrwint.gbtasgen.rom.RomInfo;

public class StateBuffer {

  public static StateMetric STATE_METRIC = StateMetric.DSum;
  public static boolean SQUAREROOT_GROWTH = true;
  public static Decider DECIDER = Decider.SUM;
  public static SecondaryDecider SECONDARY_DECIDER = SecondaryDecider.OCD;

  public static final int MAX_BUFFER_SIZE = 1;//128;
  public static final int MAX_BUFFER_SIZE_HARDLIMIT = 128;
  public static final boolean BOUNDED_USE_MAPS = true;
  public static final boolean UNBOUNDED_USE_MAPS = true;

  public enum StateMetric {
    DSum,
    RNG1,
    RNG2,
    RNG,
    DIV,
    RANDOM
  }

  public enum Decider {
    SUM,
    MAXDIFF,
    MIN,
    MAX,
  }

  public enum SecondaryDecider {
    OCD,
    RANDOM,
  }

  public static int getStateMetric(State s) {
    switch(STATE_METRIC) {
    case DIV:
      return (((s.rngState>>16)&0x3F)<<8) + ((s.rngState>>22)&0xFF);
    case DSum:
      return (((s.rngState>>8)&0xFF) + (s.rngState&0xFF))&0xFF;
    case RNG:
      return s.rngState & 0xFFFF;
    case RNG1:
      return s.rngState & 0xFF;
    case RNG2:
      return (s.rngState >> 8) & 0xFF;
    case RANDOM:
    default:
      return (int)(Math.random()*0x100);
    }
  }

  public static int getStateMetricMaxVal() {
    switch(STATE_METRIC) {
    case DIV:
      return 0x4000;
    case DSum:
      return 0x100;
    case RNG:
      return 0x10000;
    case RNG1:
      return 0x100;
    case RNG2:
      return 0x100;
    case RANDOM:
    default:
      return 0x100;
    }
  }

  public static abstract class SubMap {
    public abstract boolean add(State s);
    public abstract int size();
    public abstract Collection<? extends State> getStates();

    public abstract void remove(State state);

    public void removeAny(boolean debugOut) {
      int minMetric=getStateMetricMaxVal();
      int maxMetric=-1;
      int minVal = Integer.MAX_VALUE;
      int maxSecondaryVal = -1;
      State minState = null;
      for(State s1 : getStates()) {
        int s1Metric = getStateMetric(s1);
        maxMetric = Math.max(maxMetric,s1Metric);
        minMetric = Math.min(minMetric,s1Metric);
        int sum = 0;
        int maxDiff = 0;
        for(State s2 : getStates()) {
          int s2Metric = getStateMetric(s2);
          int mn = Math.min(s1Metric, s2Metric);
          int mx = Math.max(s1Metric, s2Metric);
          int res = Math.min(mx-mn, mn + getStateMetricMaxVal() - mx);
          if(SQUAREROOT_GROWTH)
            res = (int)Math.sqrt(getStateMetricMaxVal()*res);
          sum+=res;
          maxDiff = Math.max(maxDiff, res);
        }
        int val = (DECIDER == Decider.SUM ? sum : maxDiff);
        if(DECIDER == Decider.MIN)
          val = s1Metric;
        if(DECIDER == Decider.MAX)
          val = -s1Metric;
        int secondaryVal = (SECONDARY_DECIDER == SecondaryDecider.OCD ? s1.ocdCount : (int)(Math.random() * 0x10000));
        if(minState == null || val < minVal || (val == minVal && secondaryVal > maxSecondaryVal)) {
          minVal = val;
          maxSecondaryVal = secondaryVal;
          minState = s1;
        }
      }
      remove(minState);
      if(debugOut)
        System.out.println("D-Sums: "+Integer.toHexString(minMetric)+" - "+Integer.toHexString(maxMetric));
    }
  }

  public static class SubMapList extends SubMap {

    ArrayList<State> list;

    public SubMapList() {
      list = new ArrayList<State>();
    }
    @Override
    public boolean add(State s) {
      list.add(s);
      return true;
    }
    @Override
    public int size() {
      return list.size();
    }
    @Override
    public Collection<? extends State> getStates() {
      return list;
    }
    @Override
    public void remove(State state) {
      list.remove(state);
    }
  }

  public static class SubMapMap extends SubMap {

    Map<Integer, State> map;

    public SubMapMap() {
      map = new HashMap<Integer, State>();
    }
    @Override
    public boolean add(State s) {
      Integer rngState = Integer.valueOf(s.rngState);
      if(map.containsKey(rngState)) {
        State oldState = map.get(rngState);
        int secondary = (SECONDARY_DECIDER == SecondaryDecider.OCD ? s.ocdCount : (int)(Math.random() * 0x10000));
        int oldSecondary = (SECONDARY_DECIDER == SecondaryDecider.OCD ? oldState.ocdCount : (int)(Math.random() * 0x10000));
        if(oldState.stepCount < s.stepCount || (oldState.stepCount == s.stepCount && oldSecondary < secondary))
          return false;
      }
      map.put(rngState, s);
      return true;
    }
    @Override
    public int size() {
      return map.size();
    }
    @Override
    public Collection<? extends State> getStates() {
      return map.values();
    }
    @Override
    public void remove(State state) {
      map.remove(state.rngState);
    }
  }



  public Map<Integer,SubMap> stateMap;
  public int maxBufferSize;

  private boolean useList;

  public StateBuffer() {
    this(MAX_BUFFER_SIZE);
  }

  public StateBuffer(int maxBufferSize) {
    this.stateMap = new TreeMap<Integer,SubMap>();
    this.maxBufferSize = maxBufferSize;
    this.useList = !((this.maxBufferSize <= 0 && UNBOUNDED_USE_MAPS) || (this.maxBufferSize > 0 && BOUNDED_USE_MAPS));
  }

  @Override
  public String toString() {
    return "("+super.toString()+", "+size()+" States)";
  }

  public boolean addState(State s) {
    if (s.rngState == -1)
      throw new IllegalArgumentException("Tried to add state "+s+" without rngState.");

    if(!stateMap.containsKey(s.stepCount))
      stateMap.put(s.stepCount, useList ? new SubMapList() : new SubMapMap());
    SubMap subMap = stateMap.get(s.stepCount);

    if(!subMap.add(s))
      return false;
    prune();
    return true;
  }

  public void addAll(StateBuffer buf) {
    for(State s : buf.getStates())
      addState(s);
  }

  public void prune() {
    while(size() > (maxBufferSize <= 0 ? MAX_BUFFER_SIZE_HARDLIMIT : maxBufferSize)) {
      int maxStepCount = Integer.MIN_VALUE;
      for(Integer e : stateMap.keySet())
        if(e > maxStepCount)
          maxStepCount = e;
      stateMap.get(maxStepCount).removeAny(false /*stateMap.size() <= 1*/);
      if(stateMap.get(maxStepCount).size() == 0)
        stateMap.remove(maxStepCount);
    }
  }

  public Collection<State> getStates() {
    List<State> ret = new ArrayList<State>();
    for(SubMap subMap : stateMap.values())
      ret.addAll(subMap.getStates());
    return ret;
  }

  public boolean isEmpty() {
    return stateMap.isEmpty();
  }

  public int size() {
    int ret = 0;
    for(SubMap subMap : stateMap.values())
      ret += subMap.size();
    return ret;
  }

  public boolean isFull() {
    return size() >= (maxBufferSize <= 0 ? MAX_BUFFER_SIZE_HARDLIMIT : maxBufferSize);
  }


  public void save(String filename) {
    try {
      String path = "saves/" + filename + RomInfo.rom.fileNameSuffix;
      ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
      save(oos);
      oos.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void save(ObjectOutputStream oos) {
    try {
      int len = size();
      oos.writeInt(maxBufferSize);
      oos.writeInt(len);
      for(SubMap subMap : stateMap.values())
        for(State s : subMap.getStates())
          s.save(oos);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  public static StateBuffer load(String filename) {
    try {
      String path = "saves/" + filename + RomInfo.rom.fileNameSuffix;

      ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
      StateBuffer ret = load(ois);
      ois.close();
      return ret;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static StateBuffer load(ObjectInputStream ois) {
    try {
      int maxBufferSize = ois.readInt();
      StateBuffer ret = new StateBuffer(maxBufferSize);
      int len = ois.readInt();
      for(int i=0;i<len;i++)
        ret.addState(State.load(ois));
      return ret;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
}
