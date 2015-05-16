package mrwint.gbtasgen.rom.sml2;

import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.rom.RomInfo;

public class Sml2RomInfo extends RomInfo {

  @Override
  public int getRngState(Gb gb) {
    return gb.getDivState();
  }

  public int hGameState = 0xff9b;
  public int sFrameCountDown = 0xa266;
  public int sFileSelectState = 0xa277;

  public Sml2RomInfo() {

    fileNameSuffix = "Sml10";

    romFileName = "roms/sml2_10.gb";
    romName = "Super Mario Land 2 - 6 Golden Coins (USA, Europe)";
    romSHA1 = "BBA408539ECBF8D322324956D859BC86E2A9977B";
    platform = "GB";

    readJoypadAddress = 0x1fd2; // start of ReadJoypad
    hJoypadReleasedAddress = 0;
    hJoypadDownAddress = 0xff80;
    hJoypadPressedAddress = 0xff81;
    hJoypadMenuAddress = 0;
  }
}
