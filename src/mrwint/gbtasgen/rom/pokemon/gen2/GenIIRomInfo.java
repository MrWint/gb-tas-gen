package mrwint.gbtasgen.rom.pokemon.gen2;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.rom.pokemon.PokemonRomInfo;
import mrwint.gbtasgen.util.pokemon.map.Gen2Map;

public class GenIIRomInfo extends PokemonRomInfo {
	public GenIIRomInfo() {
		optionsTextSpeedMask = 0x7;
		mapFactory = new Gen2Map.Factory();
		curPositionOffset = -4;
		listMovesEntryLength = 7;

		platform = "GBC";
	}
	@Override
	public Move getWalkStep(int dir, boolean check, boolean skipStandStillTest) {
		return new mrwint.gbtasgen.move.pokemon.gen2.WalkStep(dir, check, skipStandStillTest);
	}
}