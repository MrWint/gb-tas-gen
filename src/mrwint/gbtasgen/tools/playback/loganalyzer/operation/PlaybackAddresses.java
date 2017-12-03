package mrwint.gbtasgen.tools.playback.loganalyzer.operation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public final class PlaybackAddresses {
  public static final int WRAM_START = 0xc000;
  public static final int STOP_OPERATIONS_ROM;
  public static final int RECORD_ROM;
  
  static {
    Scanner s;
    try {
      s = new Scanner(new FileInputStream("temp/playback.sym"));
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
    HashMap<String, Integer> addressMap = new HashMap<>();
    while (s.hasNextLine()) {
      String line = s.nextLine().trim();
      if (line.isEmpty() || line.startsWith(";"))
        continue;
      String[] ss = line.split(" ");
      if (ss.length != 2) {
        System.err.println("ignoring invalid line " + line);
        continue;
      }
      int address = Integer.valueOf(ss[0].substring(ss[0].indexOf(':') + 1), 16);
      addressMap.put(ss[1], address);
    }
    s.close();    
    
    STOP_OPERATIONS_ROM = addressMap.get("StopOperations");
    RECORD_ROM = addressMap.get("Record");

    int offset = WRAM_START - RECORD_ROM;

    RECORD = RECORD_ROM + offset;

    WAIT_SHORT = addressMap.get("WaitShort") + offset;
    WAIT_LONG = addressMap.get("WaitLong") + offset;

    WRITE_TILE_DIRECT = addressMap.get("WriteTileDirect") + offset;
    WRITE_TILE_DIRECT_VRAM0 = addressMap.get("WriteTileDirectVram0") + offset;
    WRITE_TILE_DIRECT_VRAM1 = addressMap.get("WriteTileDirectVram1") + offset;
  
    WRITE_BG_PALETTE_DIRECT = addressMap.get("WriteBgPaletteDirect") + offset;
    WRITE_OBJ_PALETTE_DIRECT = addressMap.get("WriteObjPaletteDirect") + offset;
  
    WRITE_BYTE_DIRECT = addressMap.get("WriteByteDirect") + offset;
    WRITE_BYTE_DIRECT_VRAM0 = addressMap.get("WriteByteDirectVram0") + offset;
    SET_VRAM0 = addressMap.get("SetVram0") + offset;
    WRITE_BYTE_DIRECT_VRAM1 = addressMap.get("WriteByteDirectVram1") + offset;
    SET_VRAM1 = addressMap.get("SetVram1") + offset;

    WRITE_HBYTE_DIRECT = addressMap.get("WriteHByteDirect") + offset;

    WRITE_SPRITE = addressMap.get("WriteSprite") + offset;
    CLEAR_SPRITE = addressMap.get("ClearSprite") + offset;

    PLAY_SOUND = addressMap.get("PlaySound") + offset;
    PLAY_SOUND_SET_BG = addressMap.get("PlaySoundSetBg") + offset;
    PLAY_SOUND_HQ = addressMap.get("PlaySoundHQ") + offset;
    FMV = addressMap.get("FMV") + offset;

    EOF = addressMap.get("EndOfFile") + offset;
  }

  public static final int RECORD;

  public static final int WAIT_SHORT;
  public static final int WAIT_LONG;

  public static final int WRITE_TILE_DIRECT;
  public static final int WRITE_TILE_DIRECT_VRAM0;
  public static final int WRITE_TILE_DIRECT_VRAM1;

  public static final int WRITE_BG_PALETTE_DIRECT;
  public static final int WRITE_OBJ_PALETTE_DIRECT;

  public static final int WRITE_BYTE_DIRECT;
  public static final int WRITE_BYTE_DIRECT_VRAM0;
  public static final int SET_VRAM0;
  public static final int WRITE_BYTE_DIRECT_VRAM1;
  public static final int SET_VRAM1;

  public static final int WRITE_HBYTE_DIRECT;

  public static final int WRITE_SPRITE;
  public static final int CLEAR_SPRITE;

  public static final int PLAY_SOUND;
  public static final int PLAY_SOUND_SET_BG;
  public static final int PLAY_SOUND_HQ;
  public static final int FMV;
  
  public static final int EOF;
}
