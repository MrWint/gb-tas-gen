package mrwint.gbtasgen.tools.playback.loganalyzer;

import java.util.Arrays;

public class TimedAction {
  
  @SuppressWarnings("rawtypes")
  private final Action[] actions;
  private int actionIndex = 0;

  public final int scene;
  public final long from;
  public final long to;
  
  public TimedAction(int scene, long from, long to, Action<?>... actions) {
    this.actions = actions;
    this.scene = scene;
    this.from = from;
    this.to = to;
  }
  
  public Action<?> getNextAction() {
    if (actionIndex >= actions.length)
      return null;
    return actions[actionIndex];
  }
  
  public boolean consumeNextAction() {
    if (actionIndex >= actions.length)
      throw new RuntimeException("Consuming non-existant action in " + this);
    return ++actionIndex >= actions.length;
  }

  public static class Action<T> {
    public enum Type {
      TILE,
      OAM,
      BGPALETTE,
      OBJPALETTE,
      WRAM,
      HRAM,
    }
    public final Type type;
    public final T value;
    public final int position;
    public Action(Type type, T value, int position) {
      this.type = type;
      this.value = value;
      this.position = position;
    }
    
    @Override
    public String toString() {
      return "[Action type " + type + " position " + Integer.toHexString(position) + " value " + value + "]";
    }
  }
  
  @Override
  public String toString() {
    return "[TimedAction scene " + scene + " from " + from + " to " + to + " actions " + Arrays.toString(actions) + " actionIndex " + actionIndex + "]";
  }
}

