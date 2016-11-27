package mrwint.gbtasgen.tools.playback.loganalyzer;

public class TimeStamp implements Comparable<TimeStamp> {
  public static final TimeStamp MIN_VALUE = new TimeStamp(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
  public final int scene;
  public final int frame;
  public final int frameCycle;

  public TimeStamp(int scene, int frame, int frameCycle) {
    this.scene = scene;
    this.frame = frame;
    this.frameCycle = frameCycle;
  }

  @Override
  public int compareTo(TimeStamp o) {
    if (scene != o.scene)
      return Integer.compare(scene, o.scene);
    if (frame != o.frame)
      return Integer.compare(frame, o.frame);
    return Integer.compare(frameCycle, o.frameCycle);
  }
  
  public TimeStamp addCycles(int cycles) {
    return new TimeStamp(scene, frame, frameCycle + cycles);
  }
}
