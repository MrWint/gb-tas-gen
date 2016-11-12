package mrwint.gbtasgen.tools.playback.util.video;

public class ColorUtil {

  public static int getComponent(long color, int index) {
    return (int) ((color >> (index << 4)) & 0xffff);
  }

  public static long setComponent(long color, int index, int component) {
    component = Math.max(component, 0);
    component = Math.min(component, 0xffff);
    return (color & ~(0xffffL << (index << 4))) | (component & 0xffffL) << (index << 4);
  }

  public static long addToComponent(long color, int index, int delta) {
    return setComponent(color, index, getComponent(color, index) + delta);
  }

  public static long getSquaredDistance(long color1, long color2) {
    long distR = getComponent(color1, 2) - getComponent(color2, 2);
    long distG = getComponent(color1, 1) - getComponent(color2, 1);
    long distB = getComponent(color1, 0) - getComponent(color2, 0);
    return distR * distR + distG * distG + distB * distB;
  }

  public static long findClosestMatch(long color, long[] palette) {
    long minSquaredError = Long.MAX_VALUE;
    long minErrorColor = 0;
    for (long pColor : palette) {
      long squaredError = getSquaredDistance(color, pColor);
      if (squaredError < minSquaredError) {
        minSquaredError = squaredError;
        minErrorColor = pColor;
      }
    }
    return minErrorColor;
  }

  public static int findClosestMatchIndex(long color, long[] palette) {
    long minSquaredError = Long.MAX_VALUE;
    int minErrorIndex = 0;
    for (int i = 0; i < palette.length; i++) {
      long squaredError = getSquaredDistance(color, palette[i]);
      if (squaredError < minSquaredError) {
        minSquaredError = squaredError;
        minErrorIndex = i;
      }
    }
    return minErrorIndex;
  }

  public static byte findClosestMatchIndex(long color, long gbPalette) {
    long minSquaredError = Long.MAX_VALUE;
    byte minErrorIndex = 0;
    for (byte i = 0; i < 4; i++) {
      long squaredError = getSquaredDistance(color, Palette.fromGbColor((short) (gbPalette >> (i << 4))));
      if (squaredError < minSquaredError) {
        minSquaredError = squaredError;
        minErrorIndex = i;
      }
    }
    return minErrorIndex;
  }

  public static long findClosestMatchDistance(long color, long gbPalette) {
    long minSquaredError = Long.MAX_VALUE;
    for (int i = 0; i < 4; i++) {
      long squaredError = getSquaredDistance(color, Palette.fromGbColor((short) (gbPalette >> (i << 4))));
      minSquaredError = Math.min(minSquaredError, squaredError);
    }
    return minSquaredError;
  }

  public static long getCosts(long[] colors, long gbPalette) {
    long result = 0;
    for (long color : colors)
      result += findClosestMatchDistance(color, gbPalette);
    return result;
  }
}
