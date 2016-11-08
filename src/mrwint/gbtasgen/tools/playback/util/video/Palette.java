package mrwint.gbtasgen.tools.playback.util.video;

import java.util.Arrays;
import java.util.Random;

import mrwint.gbtasgen.tools.playback.util.video.dither.Dither;

public class Palette {
  public static final int PALETTES_PER_LINE = 2;
  public static final int NUM_PALETTES = 8;

  public static final int PALETTE_HEIGHT = NUM_PALETTES / PALETTES_PER_LINE;

  public static long[] calculateGbPalettesForFrame(long[][] frame) {
    int height = frame.length;
    int numPalettes = NUM_PALETTES + PALETTES_PER_LINE * (height - 1);
    return calculateGbPalettesForFrame(frame, new long[numPalettes], 0, 0);
  }

  public static long[] calculateGbPalettesForFrame(long[][] frame, long[] palettes, int freezeTop, int freezeBottom) {
    int height = frame.length;
    int width = frame[0].length;

    int numPalettes = NUM_PALETTES + PALETTES_PER_LINE * (height - 1);
    int startPalette = freezeTop <= 0 ? 0 : (PALETTE_HEIGHT - 1) * PALETTES_PER_LINE + freezeTop * 8 * PALETTES_PER_LINE;
    int endPalette = freezeBottom <= 0 ? numPalettes : (height - freezeBottom * 8) * PALETTES_PER_LINE;
    for (int i = startPalette; i < endPalette; i++) {
      int minX = ((i % NUM_PALETTES) * width / NUM_PALETTES)/8*8;
      int maxX = ((i % NUM_PALETTES + 1) * width / NUM_PALETTES)/8*8;
      int minY = (i / NUM_PALETTES) * PALETTE_HEIGHT - (PALETTE_HEIGHT - 1) + (i % NUM_PALETTES) / PALETTES_PER_LINE;
      minY = Math.max(minY, 0);
      int maxY = (i / NUM_PALETTES) * PALETTE_HEIGHT + 1 + (i % NUM_PALETTES) / PALETTES_PER_LINE;
      maxY = Math.min(maxY, height);
      long[] colors = new long[(maxY-minY) * (maxX-minX)];
      for (int y = minY; y < maxY; y++)
        for (int x = minX; x < maxX; x++)
          colors[(y - minY) * (maxX-minX) + x - minX] = frame[y][x];
      palettes[i] = determineGbPalette(colors);
    }

    return palettes;
  }

  public static int[] chosenMethodCounter = new int[6];

  public static void printPaletteMethodStats() {
    System.out.println("Palette method usage: " + Arrays.toString(chosenMethodCounter));
  }

  public static long determineGbPalette(long[] colors) {
    long minCosts = Long.MAX_VALUE;
    long minCostPalette = 0;
    int minCostMethod = -1;

    // Median Cut
    {
      long mcPalette = MedianCut.calculateGbPalette(colors);
      long mcPaletteCost = VideoUtil.getCosts(colors, mcPalette);
      long mcKmPalette = KMeans.kMeans(mcPalette, colors);
      long mcKmPaletteCost = VideoUtil.getCosts(colors, mcKmPalette);
      if (mcKmPaletteCost < minCosts) {
        minCosts = mcKmPaletteCost;
        minCostPalette = mcKmPalette;
        minCostMethod = 2;
      }
      if (mcPaletteCost < minCosts) {
        minCosts = mcPaletteCost;
        minCostPalette = mcPalette;
        minCostMethod = 3;
      }
    }

    // FourPalette
    {
      long fcPalette = FourPalette.calculateGbPalette(colors);
      long fcPaletteCost = VideoUtil.getCosts(colors, fcPalette);
      long fcKmPalette = KMeans.kMeans(fcPalette, colors);
      long fcKmPaletteCost = VideoUtil.getCosts(colors, fcKmPalette);
      if (fcKmPaletteCost < minCosts) {
        minCosts = fcKmPaletteCost;
        minCostPalette = fcKmPalette;
        minCostMethod = 0;
      }
      if (fcPaletteCost < minCosts) {
        minCosts = fcPaletteCost;
        minCostPalette = fcPalette;
        minCostMethod = 1;
      }
    }

    // Random
    Random random = new Random();
    for (int i = 0; i < 10000; i++) {
      long rPalette = random.nextLong() & 0x7fff7fff7fff7fffL;
      long rPaletteCost = VideoUtil.getCosts(colors, rPalette);
      long rKmPalette = KMeans.kMeans(rPalette, colors);
      long rKmPaletteCost = VideoUtil.getCosts(colors, rKmPalette);
      if (rKmPaletteCost < minCosts) {
        minCosts = rKmPaletteCost;
        minCostPalette = rKmPalette;
        minCostMethod = 4;
      }
      if (rPaletteCost < minCosts) {
        minCosts = rPaletteCost;
        minCostPalette = rPalette;
        minCostMethod = 5;
      }
    }

    chosenMethodCounter[minCostMethod]++;
    return minCostPalette;
  }





  public static byte[][] calculatePaletteMapForFrame(long[][] frame, long[] gbPalettes) {
    return calculatePaletteMapForFrame(frame, gbPalettes, 0, 0);
  }

  public static byte[][] calculatePaletteMapForFrame(long[][] frame, long[] gbPalettes, int freezeTop, int freezeBottom) {
    int height = frame.length;
    int width = frame[0].length;

    byte[][] paletteMap = new byte[height/8 - freezeTop - freezeBottom][width/8];

    for (int y = 8*freezeTop; y < height - 8*freezeBottom; y += 8) {
      int paletteOffset = (y/8) * PALETTES_PER_LINE * 8;
      for (int x = 0; x < width; x += 8) {
        long minError = Long.MAX_VALUE;
        byte minErrorP = -1;
        for (byte p = 0; p < NUM_PALETTES; p++) {
          long curError = 0;
          for (int dy = 0; dy < 8; dy++) {
            int paletteIndex = paletteOffset + p + (dy + (PALETTE_HEIGHT - 1) - (p / PALETTES_PER_LINE))/PALETTE_HEIGHT * NUM_PALETTES;
            for (int dx = 0; dx < 8; dx++) {
              curError += VideoUtil.findClosestMatchDistance(frame[y+dy][x+dx], gbPalettes[paletteIndex]);
            }
          }
          if (curError < minError) {
            minError = curError;
            minErrorP = p;
          }
        }
        paletteMap[y/8 - freezeTop][x/8] = minErrorP;
      }
    }

    return paletteMap;
  }

  public static byte[][] calculatePixelsForFrame(long[][] frame, long[] gbPalettes, byte[][] paletteMap, Dither dither) {
    return calculatePixelsForFrame(frame, gbPalettes, paletteMap, dither, 0, 0);
  }

  public static byte[][] calculatePixelsForFrame(long[][] frame, long[] gbPalettes, byte[][] paletteMap, Dither dither, int freezeTop, int freezeBottom) {
    int height = frame.length;
    int width = frame[0].length;

    byte[][] pixels = new byte[height - freezeTop*8 - freezeBottom*8][width];

    for (int y = 8*freezeTop; y < height - 8*freezeBottom; y++) {
      for (int x = 0; x < width; x ++) {
        int p = paletteMap[y/8 - freezeTop][x/8];
        int paletteIndex = p + (y + (PALETTE_HEIGHT - 1) - (p / PALETTES_PER_LINE))/PALETTE_HEIGHT * NUM_PALETTES;
        pixels[y - 8*freezeTop][x] = VideoUtil.findClosestMatchIndex(frame[y][x], gbPalettes[paletteIndex]);
        long pixelColor = Palette.fromGbColor((short) (gbPalettes[paletteIndex] >> (pixels[y - 8*freezeTop][x] << 4)));
        dither.dither(frame, x, y, pixelColor);
      }
    }
    return pixels;
  }

  public static long toGbPalette(long[] colors) {
    return toGbColor(colors[0])
        + ((long)toGbColor(colors.length > 1 ? colors[1] : 0) << 16)
        + ((long)toGbColor(colors.length > 2 ? colors[2] : 0) << 32)
        + ((long)toGbColor(colors.length > 3 ? colors[3] : 0) << 48);
  }

  public static long[] fromGbPalette(long palette) {
    return new long[] {
        fromGbColor((short) palette),
        fromGbColor((short) (palette >> 16)),
        fromGbColor((short) (palette >> 32)),
        fromGbColor((short) (palette >> 48)) };
  }

  public static short toGbColor(long color) {
    return (short) (((color >> 43) & 0x1f) /*r*/
        | ((color >> 22) & 0x3e0) /*g*/
        | ((color >> 1) & 0x7c00) /*b*/);
  }

  public static long fromGbColor(short gbColor) {
    return ((long)(gbColor & 0x7c00) << 1) /*b*/
        | ((long)(gbColor & 0x3e0) << 22) /*g*/
        | ((long)(gbColor & 0x1f) << 43) /*r*/;
  }
}
