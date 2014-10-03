package mrwint.gbtasgen.rom.pokemon.gen1;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.rom.pokemon.PokemonRomInfo;
import mrwint.gbtasgen.util.pokemon.map.Gen1Map;

public class GenIRomInfo extends PokemonRomInfo {
	public GenIRomInfo() {
		mapFactory = new Gen1Map.Factory();
		optionsTextSpeedMask = 0xF;
		curPositionOffset = 0;
		listMovesEntryLength = 6;

		platform = "GB";
	}
	@Override
	public Move getWalkStep(int dir, boolean check, boolean skipStandStillTest) {
		return new mrwint.gbtasgen.move.pokemon.gen1.WalkStep(dir, check, skipStandStillTest);
	}
}