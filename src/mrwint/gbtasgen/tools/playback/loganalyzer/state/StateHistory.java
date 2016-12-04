package mrwint.gbtasgen.tools.playback.loganalyzer.state;

import java.util.ArrayList;
import java.util.Objects;
import java.util.TreeMap;
import java.util.function.BinaryOperator;

import mrwint.gbtasgen.tools.playback.loganalyzer.GbConstants;
import mrwint.gbtasgen.tools.playback.loganalyzer.TimeStamp;
import mrwint.gbtasgen.util.SortedArrayMap;

public class StateHistory<I extends Comparable<I>, V> {
  public static interface StateMerger<T> extends BinaryOperator<T> {
    public boolean isCompatible(T t, T u);
    public default T applyChecked(T t, T u) {
      if (t == null) return u;
      if (u == null) return t;
      if (!isCompatible(t, u))
        throw new RuntimeException(t + ", " + u + " not compatible");
      return apply(t, u);
    }
  }
  public static class IdentityMerger<T> implements StateMerger<T> {
    @Override
    public T apply(T t, T u) {
      return t;
    }
    @Override
    public boolean isCompatible(T t, T u) {
      return Objects.equals(t, u);
    }
  }
  @SuppressWarnings("rawtypes")
  private static final IdentityMerger IDENTITY_MERGER = new IdentityMerger<>();
  @SuppressWarnings("unchecked")
  public static <V> IdentityMerger<V> getIdentityMerger() {
    return IDENTITY_MERGER;
  }
 
  private StateMerger<V> merger;
  // null equals "don't care"
  private SortedArrayMap<I, V> states = new SortedArrayMap<>();

  private StateHistory(StateMerger<V> merger, I lowestValue) {
    this.merger = merger;
    states.put(lowestValue, null); // States are initially set to "don't care"
  }
  public static <V> StateHistory<Long, V> withLongIndex(StateMerger<V> merger) {
    return new StateHistory<Long, V>(merger, Long.MIN_VALUE);
  }
  public static <V> StateHistory<TimeStamp, V> withTimestampIndex(StateMerger<V> merger) {
    return new StateHistory<TimeStamp, V>(merger, TimeStamp.MIN_VALUE);
  }

  public void addRange(I fromTime, I toTime, V value) {
    if (fromTime.compareTo(toTime) >= 0)
      throw new RuntimeException(fromTime + " not smaller than " + toTime);
    
    I floorKey= states.floorKey(fromTime);
    I lowerKey = states.lowerKey(fromTime);

    // Write/update starting timestamp
    V lastValue = states.get(floorKey);
    V mergedValue = merger.applyChecked(lastValue, value);
    V lastWrittenValue = states.get(lowerKey);
    if (!Objects.equals(lastWrittenValue, mergedValue)) {
      states.put(fromTime, mergedValue);
      lastWrittenValue = mergedValue;
    } else {
      states.remove(fromTime);
    }

    // Update inbetween timestamps
    I nextKey = states.higherKey(fromTime);
    while (nextKey != null && nextKey.compareTo(toTime) < 0) {
      lastValue = states.get(nextKey);
      mergedValue = merger.applyChecked(lastValue, value);
      I nextTime = nextKey;
      if (!Objects.equals(lastWrittenValue, mergedValue)) {
        states.put(nextKey, mergedValue);
        lastWrittenValue = mergedValue;
      } else {
        states.remove(nextTime);
      }
      
      nextKey = states.higherKey(nextTime);
    }
    if ((nextKey == null || nextKey.compareTo(toTime) > 0) && !Objects.equals(lastWrittenValue, lastValue)) { // not for the equal case
      states.put(toTime, lastValue);
      lastWrittenValue = lastValue;
    } else if (nextKey != null && Objects.equals(states.get(nextKey), lastWrittenValue)) {
      states.remove(nextKey);
    }
  }
  
  public V getValueAt(I time) {
    return states.get(states.floorKey(time));
  }
  
  public V getLastNonNullValueAt(I time) {
    I key = states.floorKey(time);
    while (key != null) {
      V value = states.get(key);
      if (value != null)
        return value;
      key = states.lowerKey(key);
    }
    return null;
  }

  public I getCurrentStateStartTime(I time) {
    return states.floorKey(time);
  }

  public I getNextStateStartTime(I time) {
    return states.higherKey(time);
  }

  public ArrayList<I> getNonNullEventIndices() {
    ArrayList<I> indices = new ArrayList<>();
    for (I key : states.keySet()) {
      V value = states.get(key);
      if (value != null)
        indices.add(key);
    }
    return indices;
  }
  
  public void updateEventValue(I time, V value) {
    if (!states.containsKey(time))
      throw new RuntimeException("time " + time + " not found in states " + states);
    states.put(time, value);
  }
  
  public int getSize() {
    return states.size();
  }

  public static <V> void compressTimestampHistory(StateHistory<TimeStamp, V> history) {
    compressTimestampHistory(history, false);
  }

  public static <V> void compressTimestampHistory(StateHistory<TimeStamp, V> history, boolean dontCombineOverSceneBoundaries) {
    TreeMap<TimeStamp, V> tmpStates = new TreeMap<>();
    for (TimeStamp key : history.states.keySet())
      tmpStates.put(key, history.states.get(key));
    
    if (tmpStates.get(tmpStates.firstKey()) != null)
      throw new RuntimeException("First history entry " + tmpStates.get(tmpStates.firstKey()) + " expected to be null");
    if (tmpStates.get(tmpStates.lastKey()) != null)
      throw new RuntimeException("Last history entry " + tmpStates.get(tmpStates.lastKey()) + " expected to be null");
    TimeStamp startKey = tmpStates.higherKey(tmpStates.firstKey());
    if (startKey == null)
      return;
    TimeStamp endKey = tmpStates.lastKey();
    
    // Force merge any consecutive non-null values
    for (TimeStamp curKey = tmpStates.firstKey(); curKey != endKey; curKey = tmpStates.higherKey(curKey)) {
      if (tmpStates.get(curKey) != null) {
        TimeStamp nextKey = tmpStates.higherKey(curKey);
        while (tmpStates.get(nextKey) != null) {
          tmpStates.put(curKey, history.merger.applyChecked(tmpStates.get(curKey), tmpStates.get(nextKey)));
          tmpStates.remove(nextKey);
          nextKey = tmpStates.higherKey(curKey);
        }
      } else if (tmpStates.get(tmpStates.higherKey(curKey)) == null)
        throw new RuntimeException("Consecutive null values in StateHistory " + tmpStates);
    }
    
    // Try collapse null values in increasing order of their length
    ArrayList<TimeStamp> nullTimeStamps = new ArrayList<>();
    for (TimeStamp curKey = startKey; curKey != endKey; curKey = tmpStates.higherKey(curKey))
      if (tmpStates.get(curKey) == null)
        nullTimeStamps.add(curKey);
    nullTimeStamps.sort((t1, t2) -> {
      long t1Length = difference(tmpStates.higherKey(t1), t1);
      long t2Length = difference(tmpStates.higherKey(t2), t2);
      return Long.compare(t1Length, t2Length);
    });
    for (TimeStamp nullTimeStamp : nullTimeStamps) {
      TimeStamp prevKey = tmpStates.lowerKey(nullTimeStamp);
      TimeStamp nextKey = tmpStates.higherKey(nullTimeStamp);
      if (dontCombineOverSceneBoundaries && nullTimeStamp.scene != nextKey.scene)
        continue;
      V prevValue = tmpStates.get(prevKey);
      V nextValue = tmpStates.get(nextKey);
      if (history.merger.isCompatible(prevValue, nextValue)) {
        tmpStates.put(prevKey, history.merger.apply(prevValue, nextValue));
        tmpStates.remove(nextKey);
        tmpStates.remove(nullTimeStamp);
      }
    }
    
    history.states.clear();
    for (TimeStamp key : tmpStates.keySet())
      history.states.put(key, tmpStates.get(key));
    history.states.trim();
  }
  private static long difference(TimeStamp t1, TimeStamp t2) {
    if (t1.scene > t2.scene)
      return Long.MAX_VALUE;
    if (t1.scene < t2.scene)
      return Long.MIN_VALUE;
    return (t1.frame * (long)GbConstants.FRAME_CYCLES + t1.frameCycle) - (t2.frame * (long)GbConstants.FRAME_CYCLES + t2.frameCycle);
  }
  
  public String toStringAround(I time) {
    return states.toStringAround(time);
  }
}

