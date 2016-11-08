package mrwint.gbtasgen.tools.playback.util.video;

public class FourPalette {
  private static final int BITS_PER_ROUND = 2;
  private static final long BITS_PER_ROUND_MASK = (1 << BITS_PER_ROUND) - 1;

  public static long calculateGbPalette(long[] colors) {
    long gbPalette = 0;

    for (int counter = 0; counter < 1; counter++) {
      for (int c = 5; c != 2; c++) {
        c %= 3;
        gbPalette &= 0x83ff83ff83ff83ffL >> ((2 - c) * 5);

    for (int bitShift = 5 - BITS_PER_ROUND; bitShift >= 0; bitShift--) {
      long minError = Long.MAX_VALUE;
      long minErrorOffset = 0;
      for (long val = 0; val < (1 << (BITS_PER_ROUND * 4)); val++) {
        long gbPaletteOffset = ((val & BITS_PER_ROUND_MASK)
            | (((val >>       BITS_PER_ROUND) & BITS_PER_ROUND_MASK) << 16)
            | (((val >> (2 * BITS_PER_ROUND)) & BITS_PER_ROUND_MASK) << 32)
            | (((val >> (3 * BITS_PER_ROUND)) & BITS_PER_ROUND_MASK) << 48)) << (c * 5 + bitShift);
        long curError = VideoUtil.getCosts(colors, gbPalette + gbPaletteOffset);
        if (curError < minError) {
          minError = curError;
          minErrorOffset = gbPaletteOffset;
        }
      }
      // System.out.println("c " + c + " bitShift " + bitShift + " minErrorVal " + minErrorVal);
      for (int i = 0; i < 4; i++) {
        long offsetCorrection = 0x1L << ((i << 4) + c * 5 + bitShift + BITS_PER_ROUND - 2);
        long isolatedOffset = minErrorOffset & (0x1f << ((i << 4) + c * 5));
        if (isolatedOffset >= offsetCorrection) {
          minErrorOffset -= offsetCorrection;
        }
      }
      gbPalette += minErrorOffset;
    }
      }
    }
    return gbPalette;
  }
}