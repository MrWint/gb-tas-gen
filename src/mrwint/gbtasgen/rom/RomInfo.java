package mrwint.gbtasgen.rom;

import mrwint.gbtasgen.rom.pokemon.PokemonRomInfo;
import mrwint.gbtasgen.rom.sml2.Sml2RomInfo;
import mrwint.gbtasgen.rom.tetris.TetrisRomInfo;

public abstract class RomInfo {
	public static PokemonRomInfo pokemon;
  public static TetrisRomInfo tetris;
  public static Sml2RomInfo sml2;
	public static RomInfo rom;

	public static void setRom(RomInfo rom) {
		RomInfo.rom = rom;
		if (rom instanceof PokemonRomInfo)
			RomInfo.pokemon = (PokemonRomInfo)rom;
    if (rom instanceof TetrisRomInfo)
      RomInfo.tetris = (TetrisRomInfo)rom;
    if (rom instanceof Sml2RomInfo)
      RomInfo.sml2 = (Sml2RomInfo)rom;
	}

	/// rom ///
	public String fileNameSuffix;
	public String romFileName;
	public String romName;
	public String romSHA1;
	public String platform;

	/// input ///
	public int readJoypadAddress;
	public int hJoypadReleasedAddress;
	public int hJoypadPressedAddress;
	public int hJoypadDownAddress;
	public int hJoypadMenuAddress;

	/// rng ///
	public abstract int getRngState();
}
