package mrwint.gbtasgen.state;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import mrwint.gbtasgen.state.tetris.TetrisStateBuffer;

public class ListStateBuffer {

  public static final int MAX_BUFFER_SIZE = 1;
  public static final int MAX_SUBMAP_SIZE = 1;

  public static final int MAX_STEP_COUNT_DIFFERENCE = 10;
  public static final int MAX_STEP_COUNT = 1600;

  public TreeMap<Integer, List<ListState>> stateMap;
  public int maxBufferSize;

  public static StateBuffer in;

  public ListStateBuffer() {
    this(MAX_BUFFER_SIZE);
  }

  public ListStateBuffer(int maxBufferSize) {
    this.stateMap = new TreeMap<Integer,List<ListState>>();
    this.maxBufferSize = maxBufferSize;
  }

  @Override
  public String toString() {
    return "("+super.toString()+", "+size()+" States"+(isEmpty() ? "" : ", ["+stateMap.firstKey()+","+stateMap.lastKey()+"]")+")";
  }

  public ListStateBuffer appendMove(int move) {
    ListStateBuffer buffer = new ListStateBuffer(maxBufferSize);
    for (ListState listState : getStates())
      buffer.addState(new ListState(move, listState));
    return buffer;
  }

  public StateBuffer toStateBuffer(State initialState) {
    StateBuffer buffer = new StateBuffer();
    for (ListState listState : getStates())
      buffer.addState(listState.toState(initialState));
    return buffer;
  }

  public StateBuffer toStateBuffer(StateBuffer initialStates) {
    StateBuffer buffer = new StateBuffer();
    for (State state : initialStates.getStates())
      buffer.addAll(toStateBuffer(state));
    return buffer;
  }

  public TetrisStateBuffer toTetrisStateBuffer(StateBuffer initialStates) {
    TetrisStateBuffer result = new TetrisStateBuffer();
    for (State initialState : initialStates.getStates())
      for (ListState listState : getStates())
        result.addState(listState.toState(initialState));
    return result;
  }

  public boolean addState(ListState s) {
    int stepCount = s == null ? 0 : s.stepCount;
    if (stepCount > MAX_STEP_COUNT)
      return false;
    if (!stateMap.isEmpty() && stateMap.firstKey() + MAX_STEP_COUNT_DIFFERENCE < stepCount)
      return false;
    if(!stateMap.containsKey(stepCount))
      stateMap.put(stepCount, new ArrayList<ListState>());
    List<ListState> subMap = stateMap.get(stepCount);

    subMap.add(s);
    if (in != null)
      pruneState(in);
    prune();
    return true;
  }

  public void addAll(ListStateBuffer buf) {
    for(ListState s : buf.getStates())
      addState(s);
  }

  public void prune() {
    while(size() > maxBufferSize) {
      boolean cont = false;
      for (Integer key : stateMap.descendingKeySet()) {
        if (stateMap.get(key).size() > MAX_SUBMAP_SIZE) {
          stateMap.get(key).remove(stateMap.get(key).size()-1);
          cont = true;
          break;
        }
      }
      if (cont) continue;
      Integer maxStepCount = stateMap.lastKey();
      stateMap.get(maxStepCount).remove(stateMap.get(maxStepCount).size()-1);
      if(stateMap.get(maxStepCount).size() == 0)
        stateMap.remove(maxStepCount);
    }
  }

  private void pruneState(StateBuffer initialStates) {
    for (Integer key : stateMap.keySet())
      if (size() > maxBufferSize)
        removeAny(stateMap.get(key), initialStates);
  }

  private void removeAny(List<ListState> listStates, StateBuffer initialStates) {
    HashMap<Integer, ListState> listStateMap = new HashMap<>();
    for (ListState listState : listStates) {
      for (State state : initialStates.getStates()) {
        State s = listState == null ? state : listState.toState(state);
        if (!listStateMap.containsKey(s.rngState))
          listStateMap.put(s.rngState, listState);
      }
    }
    listStates.retainAll(listStateMap.values());
  }

  public List<ListState> getStates() {
    List<ListState> ret = new ArrayList<ListState>();
    for(List<ListState> subMap : stateMap.values())
      ret.addAll(subMap);
    return ret;
  }

  public boolean isEmpty() {
    return stateMap.isEmpty();
  }

  public int size() {
    int ret = 0;
    for(List<ListState> subMap : stateMap.values())
      ret += subMap.size();
    return ret;
  }

  public boolean isFull() {
    return size() >= maxBufferSize;
  }
}
