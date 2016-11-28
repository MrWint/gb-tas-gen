package mrwint.gbtasgen.tools.playback.loganalyzer.operation;

public final class PlaybackAddresses {
  public static final int RECORD = 0xc000;

  public static final int WAIT_SHORT = 0x018D;
  public static final int WAIT_LONG = 0x01A3;

  public static final int WRITE_TILE_DIRECT = 0x0289;
  public static final int WRITE_TILE_DIRECT_VRAM0 = 0x02F7;
  public static final int WRITE_TILE_DIRECT_VRAM1 = 0x0368;

  public static final int WRITE_BG_PALETTE_DIRECT = 0x03D9;
  public static final int WRITE_OBJ_PALETTE_DIRECT = 0x040F;

  public static final int WRITE_BYTE_DIRECT = 0x0449;
  public static final int WRITE_BYTE_DIRECT_VRAM0 = 0x045C;
  public static final int WRITE_BYTE_DIRECT_VRAM1 = 0x0472;

  public static final int WRITE_HBYTE_DIRECT = 0x048C;

  public static final int WRITE_SPRITE = 0x049A;
  public static final int CLEAR_SPRITE = 0x04BC;

  public static final int STOP_OPERATIONS = 0x04C9;
  public static final int PLAY_SOUND = 0x04CB;
  public static final int FMV = 0x05A0;
}
