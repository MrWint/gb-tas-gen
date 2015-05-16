package mrwint.gbtasgen.rom;

import mrwint.gbtasgen.Gb;

public abstract class RomInfo {

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
	public abstract int getRngState(Gb gb);
}
