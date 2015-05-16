package mrwint.gbtasgen.segment.pokemon.gen1;

import mrwint.gbtasgen.rom.pokemon.gen1.RedRomInfo;
import mrwint.gbtasgen.segment.SingleGbSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.sram.IntroRed;
import mrwint.gbtasgen.segment.pokemon.gen1.sram.OakSpeechRed;
import mrwint.gbtasgen.segment.pokemon.gen1.sram.SaveCorruptRed;
import mrwint.gbtasgen.segment.pokemon.gen1.sram.TestRed;
import mrwint.gbtasgen.util.SingleGbRunner;

public class Sram extends SingleGbSegment {

	@Override
	protected void execute() {
		seq(new IntroRed());
		save("introR1");

		load("introR1");
		seq(new OakSpeechRed());
		save("oakSpeechR1");

		load("oakSpeechR1");
		seq(new SaveCorruptRed());
		save("saveCorruptR1");

		load("saveCorruptR1");
		seq(new TestRed());
		save("testR1");


//		seq(new Intro());
//		save("intro1");
//
//		load("intro1");
//		seq(new OakSpeech());
//		save("oakSpeech1");
//
//		load("oakSpeech1");
//		seq(new SaveCorrupt());
//		save("saveCorrupt1");
//
//		load("saveCorrupt1");
//		seq(new Test());
//		save("test1");
	}

	public static void main(String[] args) {
    SingleGbRunner.run(new RedRomInfo(), new Sram());
//		RomInfo.rom = new RomInfo.BlueRomInfo();
//		RomInfo.rom = new RomInfo.YellowRomInfo();
//		RomInfo.rom = new RomInfo.BlueJRomInfo();
	}
}
