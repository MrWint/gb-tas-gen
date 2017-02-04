package mrwint.gbtasgen.tools.playback.util.video.dither;

public class Atkinson implements Dither {
  @Override
  public void dither(long[][] frame, int x, int y, long newColor) {
    diffuse(frame, x, y, newColor,  1, 0, 1, 8);
    diffuse(frame, x, y, newColor,  2, 0, 1, 8);
    diffuse(frame, x, y, newColor, -1, 1, 1, 8);
    diffuse(frame, x, y, newColor,  0, 1, 1, 8);
    diffuse(frame, x, y, newColor,  1, 1, 1, 8);
    diffuse(frame, x, y, newColor,  0, 2, 1, 8);
  }
}
