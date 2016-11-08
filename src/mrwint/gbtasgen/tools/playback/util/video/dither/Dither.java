package mrwint.gbtasgen.tools.playback.util.video.dither;

import mrwint.gbtasgen.tools.playback.util.video.VideoUtil;

public interface Dither {
  public void dither(long[][] frame, int x, int y, long newColor);

  public static void convertToIndexedColors(long[][] frame, long[] pal, Dither dither) {
    long[][][] palette = new long[frame.length][frame[0].length][];
    for (int y = 0; y < frame.length; y++) {
      for (int x = 0; x < frame[0].length; x++) {
        palette[y][x] = pal;
      }
    }
    convertToIndexedColors(frame, palette, dither);
  }
  public static void convertToIndexedColors(long[][] frame, long[][][] palette, Dither dither) {
    for (int y = 0; y < frame.length; y++) {
      for (int x = 0; x < frame[0].length; x++) {
        long newColor = VideoUtil.findClosestMatch(frame[y][x], palette[y][x]);
        dither.dither(frame, x, y, newColor);
        frame[y][x] = newColor;
      }
    }
  }

  public default void diffuse(long[][] frame, int x, int y, long newColor, int dx, int dy, int num, int denum) {
    int nx = x + dx;
    int ny = y + dy;
    if (nx >= 0 && nx < frame[0].length && ny >= 0 && ny < frame.length) {
      for (int c = 0; c < 3; c++) {
        int cError = ((VideoUtil.getComponent(frame[y][x], c) - VideoUtil.getComponent(newColor, c))*num)/denum;
        frame[ny][nx] = VideoUtil.addToComponent(frame[ny][nx], c, cError);
      }
    }
  }

}
