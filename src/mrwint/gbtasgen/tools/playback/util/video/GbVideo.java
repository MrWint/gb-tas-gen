package mrwint.gbtasgen.tools.playback.util.video;

import java.util.Arrays;

import mrwint.gbtasgen.tools.playback.util.video.dither.Dither;
import mrwint.gbtasgen.tools.playback.util.video.dither.FloydSteinberg;

public class GbVideo {
  private static final int FREEZE_TOP = 2;
  private static final int FREEZE_BOTTOM = 4;
  private static final int WIDTH = 160;
  private static final int HEIGHT = 144;

  private static final int FIRST_FRAME_PALETTES = Palette.NUM_PALETTES + Palette.PALETTES_PER_LINE * (HEIGHT - 1);

  @SuppressWarnings("unused")
  private static final int TOP_PALETTE_FREEZE = FREEZE_TOP <= 0 ? 0 : (Palette.PALETTE_HEIGHT - 1) * Palette.PALETTES_PER_LINE + FREEZE_TOP * 8 * Palette.PALETTES_PER_LINE;
  @SuppressWarnings("unused")
  private static final int BOTTOM_PALETTE_FREEZE = FREEZE_BOTTOM <= 0 ? FIRST_FRAME_PALETTES : (HEIGHT - FREEZE_BOTTOM * 8) * Palette.PALETTES_PER_LINE;

  public long[][] framePalettes;
  public byte[][][] framePaletteMap;
  public byte[][][] framePixels;

  public GbVideo(SimpleAvi avi) {
    if (avi.width != WIDTH || avi.height != HEIGHT)
      throw new RuntimeException("Video dimension mismatch");

    framePalettes = new long[avi.totalFrames][];
    framePaletteMap = new byte[avi.totalFrames][][];
    framePixels = new byte[avi.totalFrames][][];

    Dither dither = new FloydSteinberg();

    framePalettes[0] = Palette.calculateGbPalettesForFrame(avi.frames[0]);
    framePaletteMap[0] = Palette.calculatePaletteMapForFrame(avi.frames[0], framePalettes[0]);
    framePixels[0] = Palette.calculatePixelsForFrame(avi.frames[0], framePalettes[0], framePaletteMap[0], dither);

    long [] tmpPalettes = framePalettes[0].clone();
    for (int i = 1; i < avi.totalFrames; i++) {
      System.out.println("Frame " + i);

      Palette.calculateGbPalettesForFrame(avi.frames[i], tmpPalettes, FREEZE_TOP, FREEZE_BOTTOM);
      framePaletteMap[i] = Palette.calculatePaletteMapForFrame(avi.frames[i], tmpPalettes, FREEZE_TOP, FREEZE_BOTTOM);
      framePixels[i] = Palette.calculatePixelsForFrame(avi.frames[i], tmpPalettes, framePaletteMap[i], dither, FREEZE_TOP, FREEZE_BOTTOM);
      framePalettes[i] = Arrays.copyOfRange(tmpPalettes, TOP_PALETTE_FREEZE, BOTTOM_PALETTE_FREEZE);
    }
  }

  public SimpleAvi toSimpleAvi() {
    SimpleAvi avi = new SimpleAvi();
    avi.dwMicroSecPerFrame = 34296875 / 512;
    avi.dwRate = 65536;
    avi.dwScale = 4389;

    avi.width = WIDTH;
    avi.height = HEIGHT;

    avi.totalFrames = framePixels.length;
    avi.frames = new long[avi.totalFrames][HEIGHT][WIDTH];

    long [] tmpPalettes = framePalettes[0].clone();
    byte[][] tmpPaletteMap = framePaletteMap[0].clone();
    byte[][] tmpPixels = framePixels[0].clone();
    for (int frame = 0; frame < avi.totalFrames; frame++) {
      if (frame > 0) {
        for (int i = TOP_PALETTE_FREEZE; i < BOTTOM_PALETTE_FREEZE; i++)
          tmpPalettes[i] = framePalettes[frame][i - TOP_PALETTE_FREEZE];
        for (int i = FREEZE_TOP; i < HEIGHT / 8 - FREEZE_BOTTOM; i++)
          tmpPaletteMap[i] = framePaletteMap[frame][i - FREEZE_TOP];
        for (int i = 8*FREEZE_TOP; i < HEIGHT - 8*FREEZE_BOTTOM; i++)
          tmpPixels[i] = framePixels[frame][i - 8*FREEZE_TOP];
      }

      for (int y = 0; y < HEIGHT; y++) {
        for (int x = 0; x < WIDTH; x++) {
          int p = tmpPaletteMap[y/8][x/8];
          int paletteIndex = p + (y + (Palette.PALETTE_HEIGHT - 1) - (p / Palette.PALETTES_PER_LINE))/Palette.PALETTE_HEIGHT * Palette.NUM_PALETTES;
          avi.frames[frame][y][x] = Palette.fromGbColor((short) (tmpPalettes[paletteIndex] >> (tmpPixels[y][x] << 4)));
        }
      }
    }

    return avi;
  }
}
