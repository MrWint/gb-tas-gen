package mrwint.gbtasgen.util.pokemon;

import mrwint.gbtasgen.rom.RomInfo;
import mrwint.gbtasgen.rom.pokemon.PokemonRomInfo;
import mrwint.gbtasgen.state.State;
import mrwint.gbtasgen.util.Util;

public class PokemonUtil {
	public static String charConversionTable =
			"................" +
			"................" +
			"................" +
			"................" +
			"................" +
			"@...#..........." +
			"................" +
			"....№.......... " +
			"ABCDEFGHIJKLMNOP" +
			"QRSTUVWXYZ():;[]" +
			"abcdefghijklmnop" +
			"qrstuvwxyzédlstv" +
			"................" +
			"................" +
			"'..-rm?!........" +
			"......0123456789";

	public static String getString(int startAdd) {
		String ret = "";
		while(true) {
			char c = charConversionTable.charAt(State.getROM()[startAdd++]);
			if(c == '@') break;
			ret += c;
		}
		return ret;
	}
	public static String getStringFromList(int startAdd, int skips) {
		while(skips-- > 0)
			while(State.getROM()[startAdd++] != 0x50);
		return getString(startAdd);
	}
	public static String getStringFromPointerList(int startAdd, int skips) {
		return getString(Util.getRomPointerAddressLE(startAdd+2*skips));
	}

	public static boolean isCrystal() {
		return RomInfo.pokemon.type == PokemonRomInfo.CRYSTAL;
	}
	public static boolean isGold() {
		return RomInfo.pokemon.type == PokemonRomInfo.GOLD;
	}
	public static boolean isSilver() {
		return RomInfo.pokemon.type == PokemonRomInfo.SILVER;
	}
	public static boolean isGen2() {
		return RomInfo.pokemon.type == PokemonRomInfo.SILVER || RomInfo.pokemon.type == PokemonRomInfo.GOLD || RomInfo.pokemon.type == PokemonRomInfo.CRYSTAL;
	}
	public static boolean isGen1() {
		return RomInfo.pokemon.type == PokemonRomInfo.RED || RomInfo.pokemon.type == PokemonRomInfo.BLUE || RomInfo.pokemon.type == PokemonRomInfo.BLUE_J;
	}
}
