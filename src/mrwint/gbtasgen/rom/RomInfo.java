package mrwint.gbtasgen.rom;

import mrwint.gbtasgen.rom.pokemon.PokemonRomInfo;

public abstract class RomInfo {
	public static PokemonRomInfo pokemon;
	public static RomInfo rom;

	public static void setRom(RomInfo rom) {
		RomInfo.rom = rom;
		if (rom instanceof PokemonRomInfo)
			RomInfo.pokemon = (PokemonRomInfo)rom;
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
