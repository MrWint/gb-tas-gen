package mrwint.gbtasgen.rom.tetris;

import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.rom.RomInfo;

public class TetrisRomInfo extends RomInfo {

  @Override
  public int getRngState(Gb gb) {
    return gb.getDivState();
  }

  public int hGameState = 0xffe1;
  public int wCurPiece = 0xc203;
  public int wPreviewPiece = 0xc213;
  public int hNextPreviewPiece = 0xffae;
  public int hCurPieceState = 0xff98;
  public int hBoardUpdateState = 0xffe3;

  public TetrisRomInfo() {

    fileNameSuffix = "Tetris11";

    romFileName = "roms/tetris11.gb";
    romName = "Tetris (World) (Rev A)";
    romSHA1 = "74591CC9501AF93873F9A5D3EB12DA12C0723BBC";
    platform = "GB";

    readJoypadAddress = 0x29a6; // start of ReadJoypad
    hJoypadReleasedAddress = 0;
    hJoypadDownAddress = 0xff80;
    hJoypadPressedAddress = 0xff81;
    hJoypadMenuAddress = 0;
}
}
