package mrwint.gbtasgen.tools.playback.util.video.dither;

public class FloydSteinberg implements Dither {
  @Override
  public void dither(long[][] frame, int x, int y, long newColor) {
    diffuse(frame, x, y, newColor,  1, 0, 7, 26);
    diffuse(frame, x, y, newColor, -1, 1, 3, 26);
    diffuse(frame, x, y, newColor,  0, 1, 5, 26);
    diffuse(frame, x, y, newColor,  1, 1, 1, 26);
  }
}
