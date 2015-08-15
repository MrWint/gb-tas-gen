package mrwint.gbtasgen.util.pokemon;

import static mrwint.gbtasgen.state.Gameboy.curGb;
import mrwint.gbtasgen.rom.pokemon.PokemonRomInfo;
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
			char c = charConversionTable.charAt(curGb.getROM()[startAdd++]);
			if(c == '@') break;
			ret += c;
		}
		return ret;
	}
	public static String getStringFromList(int startAdd, int skips) {
		while(skips-- > 0)
			while(curGb.getROM()[startAdd++] != 0x50);
		return getString(startAdd);
	}
	public static String getStringFromPointerList(int startAdd, int skips) {
		return getString(Util.getRomPointerAddressLE(startAdd+2*skips));
	}
	
  public static String getMoveName(int move) {
    return getStringFromList(curGb.pokemon.listMoveNamesAddress, move-1);
  }

  public static String getMonName(int mon) {
    int address = curGb.pokemon.listMonNamesAddress + (mon-1) * 10;
    String ret = "";
    for (int i = 0; i < 10; i++) {
      char c = charConversionTable.charAt(curGb.getROM()[address++]);
      if(c == '@') break;
      ret += c;
    }
    return ret;
  }

  public static String getItemName(int item) {
    if (item < 0xc4)
      return getStringFromList(curGb.pokemon.listItemNamesAddress, item-1);
    if (item < 0xc9)
      return "HM" + (item - 0xc3);
    return "TM" + (item - 0xc8);
  }

	public static boolean isCrystal() {
		return curGb.pokemon.type == PokemonRomInfo.CRYSTAL;
	}
	public static boolean isGold() {
		return curGb.pokemon.type == PokemonRomInfo.GOLD;
	}
	public static boolean isSilver() {
		return curGb.pokemon.type == PokemonRomInfo.SILVER;
	}
	public static boolean isGen2() {
		return curGb.pokemon.type == PokemonRomInfo.SILVER || curGb.pokemon.type == PokemonRomInfo.GOLD || curGb.pokemon.type == PokemonRomInfo.CRYSTAL;
	}
	public static boolean isGen1() {
		return curGb.pokemon.type == PokemonRomInfo.RED || curGb.pokemon.type == PokemonRomInfo.BLUE || curGb.pokemon.type == PokemonRomInfo.BLUE_J;
	}
}
