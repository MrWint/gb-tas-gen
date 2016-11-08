package mrwint.gbtasgen.tools.playback.util.video;

public class KMeans {
  public static long kMeans(long gbPalette, long[] colors) {
    int[] colorMap = new int[colors.length];
    for (int i = 0; i < colorMap.length; i++)
      colorMap[i] = -1;
    int[][] sums = new int[4][3];
    int[] counts = new int[4];
    long[] newPalette = Palette.fromGbPalette(gbPalette);

    boolean mappingChanged;
    int cycles = 0;
    do {
      cycles++;
      mappingChanged = false;
      for (int i = 0; i < 4; i++)
        sums[i][0] = sums[i][1] = sums[i][2] = counts[i] = 0;

      for (int i = 0; i < colors.length; i++) {
        int index = VideoUtil.findClosestMatchIndex(colors[i], newPalette);
        if (index != colorMap[i])
          mappingChanged = true;
        colorMap[i] = index;
        for (int j = 0; j < 3; j++)
          sums[index][j] += VideoUtil.getComponent(colors[i], j);
        counts[index]++;
      }
      for (int i = 0; i < 4; i++) {
        if (counts[i] > 0) {
          for (int j = 0; j < 3; j++) {
            newPalette[i] = VideoUtil.setComponent(newPalette[i], j, sums[i][j] / counts[i]);
          }
        }
      }

      if (cycles >= 1000) {
        System.err.println("Force stop K-Means after " + cycles + " cycles!");
        break;
      }
    } while (mappingChanged);

    return Palette.toGbPalette(newPalette);
  }
}
