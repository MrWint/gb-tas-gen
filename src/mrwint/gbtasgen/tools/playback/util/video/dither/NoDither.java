package mrwint.gbtasgen.tools.playback.util.video.dither;

public class NoDither implements Dither {
  @Override
  public void dither(long[][] frame, int x, int y, long newColor) {
    return;
  }
}
