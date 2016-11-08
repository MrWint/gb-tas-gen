package mrwint.gbtasgen.tools.playback.util.video;

import java.util.ArrayList;
import java.util.Collections;

public class MedianCut {
  static class Bucket {
    ArrayList<Long> colors;
    int[] minC;
    int[] maxC;
    public Bucket() {
      reset();
    }
    private void reset() {
      colors = new ArrayList<>();
      minC = new int[3];
      minC[0] = minC[1] = minC[2] = 0xffff;
      maxC = new int[3];
      maxC[0] = maxC[1] = maxC[2] = 0;
    }
    public void add(long color) {
      colors.add(color);
      for (int i = 0; i < 3; i++)
        minC[i] = Math.min(minC[i], VideoUtil.getComponent(color, i));
      for (int i = 0; i < 3; i++)
        maxC[i] = Math.max(maxC[i], VideoUtil.getComponent(color, i));
    }

    public int getMaxDiff() {
      int maxDiff = Integer.MIN_VALUE;
      for (int c = 0; c < 3; c++)
        maxDiff = Math.max(maxDiff, maxC[c] - minC[c]);
      return maxDiff;
    }
    public Bucket split() {
      int mc = -1;
      int maxDiff = Integer.MIN_VALUE;
      for (int c = 0; c < 3; c++) {
        int diff = maxC[c] - minC[c];
        if (diff > maxDiff) {
          maxDiff = diff;
          mc = c;
        }
      }
      final int fmc = mc;
      Collections.sort(colors, (o1, o2) -> VideoUtil.getComponent(o1, fmc) - VideoUtil.getComponent(o2, fmc));
      int minComponent = VideoUtil.getComponent(colors.get(0), mc);
      int medianComponent = VideoUtil.getComponent(colors.get((colors.size()+1)/2), mc);
      if (medianComponent == minComponent)
        medianComponent++;
      ArrayList<Long> tmpColors = colors;
      reset();
      Bucket upper = new Bucket();
      for (long color : tmpColors) {
        if (VideoUtil.getComponent(color, mc) >= medianComponent)
          upper.add(color);
        else
          this.add(color);
      }
      return upper;
    }
    public long getAvgColor() {
      long ret = 0;
      for (int c = 0; c < 3; c++) {
        long sum = 0;
        for (long color : colors)
          sum += VideoUtil.getComponent(color, c);
        ret = VideoUtil.setComponent(ret, c, (int) (sum/colors.size()));
      }
      return ret;
    }
  }

  public static long calculateGbPalette(long[] colors) {
    return Palette.toGbPalette(calculatePalette(colors, 4));
  }

  public static long[] calculatePalette(long[] colors, int numBuckets) {
    ArrayList<Bucket> buckets = new ArrayList<>();
    Bucket initialBucket = new Bucket();
    for (long color : colors)
      initialBucket.add(color);
    buckets.add(initialBucket);

    while (buckets.size() < numBuckets) {
      int maxI = -1;
      int maxDiff = Integer.MIN_VALUE;
      for (int i = 0; i < buckets.size(); i++) {
        int diff = buckets.get(i).getMaxDiff();
        if (diff > maxDiff) {
          maxDiff = diff;
          maxI = i;
        }
      }
      if (maxDiff <= 0)
        break;
      buckets.add(buckets.get(maxI).split());
    }

    long[] palette = new long[buckets.size()];
    for (int i = 0; i < buckets.size(); i++)
      palette[i] = buckets.get(i).getAvgColor();
    return palette;
  }
}
