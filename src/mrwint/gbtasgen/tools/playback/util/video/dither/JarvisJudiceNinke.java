package mrwint.gbtasgen.tools.playback.util.video.dither;

public class JarvisJudiceNinke implements Dither {
  @Override
  public void dither(long[][] frame, int x, int y, long newColor) {
    diffuse(frame, x, y, newColor,  1, 0, 7, 48);
    diffuse(frame, x, y, newColor,  2, 0, 5, 48);
    diffuse(frame, x, y, newColor, -2, 1, 3, 48);
    diffuse(frame, x, y, newColor, -1, 1, 5, 48);
    diffuse(frame, x, y, newColor,  0, 1, 7, 48);
    diffuse(frame, x, y, newColor,  1, 1, 5, 48);
    diffuse(frame, x, y, newColor,  2, 1, 3, 48);
    diffuse(frame, x, y, newColor, -2, 2, 1, 48);
    diffuse(frame, x, y, newColor, -1, 2, 3, 48);
    diffuse(frame, x, y, newColor,  0, 2, 5, 48);
    diffuse(frame, x, y, newColor,  1, 2, 3, 48);
    diffuse(frame, x, y, newColor,  2, 2, 1, 48);
  }
}
