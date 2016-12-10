package mrwint.gbtasgen.rom.pokemon.gen1;

public class YellowRomInfo extends RedRomInfo {
		public YellowRomInfo() {
			super();

			type = YELLOW;

			fileNameSuffix = "Yellow";

			romFileName = "roms/yellow.gbc";
			romName = "Pokemon - Yellow Version (USA, Europe)";
//			romSHA1 = "D7037C83E1AE5B39BDE3C30787637BA1D4C48CE2";

      readJoypadInputHi = 0xc00a; // last line that pings the joypad directional keys
      readJoypadInputLo = 0xc020; // last line that pings the joypad button keys
			readJoypadAddress = 0xc02d; // line that reads hJoypadDown
			printLetterDelayJoypadAddress = 0x38eb;
			printLetterDelayDelayFrameAddress = 0x38fa;
			printLetterDelayDoneAddress = 0x3904;

			optionsAddress = 0xd354; // determines text speed

			afterTrainerIDGenerationAddress = 0xf6e6;
			trainerIDAddress = 0xd358;
		}
	}