package mrwint.gbtasgen.rom.pokemon.gen2;

public class SilverRomInfo extends GoldRomInfo {
	public SilverRomInfo() {
		super();

		type = SILVER;

		fileNameSuffix = "Silver";

		romFileName = "roms/silver.gbc";
		romName = "Pokemon - Silver Version (USA, Europe)";
		romSHA1 = "49B163F7E57702BC939D642A18F591DE55D92DAE";

		catchSuccessAddress = 0xea1e;
		catchFailureAddress = 0xea21;
	}
}