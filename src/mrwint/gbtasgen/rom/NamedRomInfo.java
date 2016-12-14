package mrwint.gbtasgen.rom;

import mrwint.gbtasgen.Gb;

public class NamedRomInfo extends RomInfo {

  @Override
  public int getRngState(Gb gb) {
    throw new UnsupportedOperationException();
  }

  public NamedRomInfo(String romPath) {
    this.romFileName = romPath;
  }
}
