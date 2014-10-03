package mrwint.gbtasgen.segment.pokemon.gen1;

import mrwint.gbtasgen.rom.RomInfo;
import mrwint.gbtasgen.rom.pokemon.gen1.RedRomInfo;
import mrwint.gbtasgen.segment.pokemon.gen1.sram.Intro;
import mrwint.gbtasgen.segment.pokemon.gen1.sram.IntroRed;
import mrwint.gbtasgen.segment.pokemon.gen1.sram.OakSpeech;
import mrwint.gbtasgen.segment.pokemon.gen1.sram.OakSpeechJ;
import mrwint.gbtasgen.segment.pokemon.gen1.sram.OakSpeechRed;
import mrwint.gbtasgen.segment.pokemon.gen1.sram.SaveCorrupt;
import mrwint.gbtasgen.segment.pokemon.gen1.sram.SaveCorruptJ;
import mrwint.gbtasgen.segment.pokemon.gen1.sram.SaveCorruptRed;
import mrwint.gbtasgen.segment.pokemon.gen1.sram.Test;
import mrwint.gbtasgen.segment.pokemon.gen1.sram.TestJ;
import mrwint.gbtasgen.segment.pokemon.gen1.sram.TestRed;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.util.Runner;

public class Sram extends SeqSegment {

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
		RomInfo.pokemon = new RedRomInfo();
//		RomInfo.rom = new RomInfo.BlueRomInfo();
//		RomInfo.rom = new RomInfo.YellowRomInfo();
//		RomInfo.rom = new RomInfo.BlueJRomInfo();

		Runner.run(new Sram());
	}
}
