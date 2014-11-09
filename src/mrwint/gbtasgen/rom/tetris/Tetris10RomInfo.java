package mrwint.gbtasgen.rom.tetris;


public class Tetris10RomInfo extends TetrisRomInfo {

  public Tetris10RomInfo() {

    fileNameSuffix = "Tetris10";

    romFileName = "roms/tetris10.gb";
    romName = "Tetris (World)";
    romSHA1 = "74591CC9501AF93873F9A5D3EB12DA12C0723BBC";
    platform = "GB";

    readJoypadAddress = 0x29fa; // start of ReadJoypad
  }
}
