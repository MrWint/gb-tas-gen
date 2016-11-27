package mrwint.gbtasgen.tools.playback.loganalyzer;

public class GbConstants {
  public static final boolean GBC = true;
  public static final boolean DOUBLE_SPEED = true;
  public static final int DOUBLE_SPEED_FACTOR = (DOUBLE_SPEED ? 2 : 1);
  
  public static final int FRAME_LINES = 154;
  public static final int LINE_CYCLES = 456 * DOUBLE_SPEED_FACTOR;
  public static final int FRAME_CYCLES = FRAME_LINES * LINE_CYCLES;
  
  public static final int LCDC = 0xff40;
  public static final int LCDC_DEFAULT = 0x91;
  public static final int WY = 0xff4a;
  public static final int WY_DEFAULT = 0x0;
  public static final int WX = 0xff4b;
  public static final int WX_DEFAULT = 0x0;
  public static final int SCY = 0xff42;
  public static final int SCY_DEFAULT = 0x0;
  public static final int SCX = 0xff43;
  public static final int SCX_DEFAULT = 0x0;
  public static final int VRAM_BANK = 0xff4f;
  public static final int VRAM_BANK_DEFAULT = 0x0;
  public static final int VRAM_DEFAULT = 0x0;
  public static final int OAM_DEFAULT = 0x0;
}
