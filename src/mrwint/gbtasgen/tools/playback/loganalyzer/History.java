package mrwint.gbtasgen.tools.playback.loganalyzer;

import java.util.TreeMap;
import java.util.Map.Entry;

class History<T> {
  private TreeMap<TimeStamp, T> operations = new TreeMap<>();

  public void add(TimeStamp time, T value) {
    Entry<TimeStamp, T> lowerBound = operations.floorEntry(time);
    if (lowerBound == null && value == null)
      return;
    if (lowerBound != null && value == null && lowerBound.getValue() == null)
      return;
//    if (lowerBound != null && Objects.equals(lowerBound.getValue(), value))
//      return;

    operations.put(time, value);

    if (value != null
        && operations.lowerKey(time) != null
        && value.equals(operations.lowerEntry(time).getValue())
        && operations.lowerKey(operations.lowerKey(time)) != null
        && value.equals(operations.lowerEntry(operations.lowerKey(time)).getValue())) {
      operations.remove(operations.lowerKey(time)); // Remove equal inbetween values
    }

    // Remove don't cares between equal values
//    if (value != null
//        && operations.lowerKey(time) != null
//        && operations.lowerEntry(time).getValue() == null
//        && operations.lowerKey(operations.lowerKey(time)) != null
//        && value.equals(operations.lowerEntry(operations.lowerKey(time)).getValue())) { // remove don't cares between equal values
//      operations.remove(operations.lowerKey(time));
//      operations.remove(time);
//    }
  }
  
  public T getValueAt(TimeStamp time, T defaultValue) {
    Entry<TimeStamp, T> lowerBound = operations.floorEntry(time);
    return lowerBound == null ? defaultValue : lowerBound.getValue();
  }
  
  public int getSize() {
    return operations.size();
  }
  
  public TreeMap<TimeStamp, T> getOperations() {
    return operations;
  }
}