package mrwint.gbtasgen.rom;

import mrwint.gbtasgen.Gb;

public class PlaybackRomInfo extends RomInfo {

  @Override
  public int getRngState(Gb gb) {
    throw new UnsupportedOperationException();
  }

  public PlaybackRomInfo() {

    fileNameSuffix = "Playback";

    romFileName = "temp/playback.gbc";
    romName = "PLAYBACK";
    romSHA1 = "DEADBEEF";
    platform = "GBC";
  }
}
