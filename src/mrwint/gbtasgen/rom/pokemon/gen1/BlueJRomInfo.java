package mrwint.gbtasgen.rom.pokemon.gen1;

public class BlueJRomInfo extends RedRomInfo {
		public BlueJRomInfo() {
			super();

			readJoypadAddress = 0xc000; // line that reads hJoypadDown
			printLetterDelayJoypadAddress = 0x38f6;
			printLetterDelayDelayFrameAddress = 0x3905;
			printLetterDelayDoneAddress = 0x390f;

			type = BLUE_J;

			fileNameSuffix = "BlueJ";

			romFileName = "roms/bluej.gb";
//			romName = "Pokemon - Yellow Version (USA, Europe)";
//			romSHA1 = "D7037C83E1AE5B39BDE3C30787637BA1D4C48CE2";

			readJoypadAddress = 0xc03a; // line that assembles joypad data
			printLetterDelayJoypadAddress = 0x3954;
			printLetterDelayDelayFrameAddress = 0x3963;
			printLetterDelayDoneAddress = 0x396d;

			optionsAddress = 0xd2d4; // determines text speed

			afterTrainerIDGenerationAddress = 0xfbb4;
			trainerIDAddress = 0xd2d8;
		}
	}