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
  public int readJoypadInputHi; // last line that pings the joypad directional keys
  public int readJoypadInputLo; // last line that pings the joypad button keys
  public int readJoypadAddress;
  public int[] readJoypadEndAddressReleased; // after value of hJoypadReleasedAddress was determined
  public int[] readJoypadEndAddressPressed; // after value of hJoypadPressedAddress was determined
  public int[] readJoypadEndAddressDown; // after value of hJoypadDownAddress was determined
  public int[] readJoypadEndAddressMenu; // after value of hJoypadMenuAddress was determined
	public int hJoypadReleasedAddress;
	public int hJoypadPressedAddress;
	public int hJoypadDownAddress;
	public int hJoypadMenuAddress;

	/// rng ///
	public abstract int getRngState(Gb gb);
}
