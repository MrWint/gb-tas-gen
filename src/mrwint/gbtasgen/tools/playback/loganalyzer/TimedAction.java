package mrwint.gbtasgen.tools.playback.loganalyzer;

public class TimedAction {
  
  public final Action<?> action;
  public final int scene;
  public final long from;
  public final long to;
  
  public TimedAction(Action<?> action, int scene, long from, long to) {
    this.action = action;
    this.scene = scene;
    this.from = from;
    this.to = to;
  }

  public static class Action<T> {
    public enum Type {
      TILE,
      BGPALETTE,
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
  }
}

