package mrwint.gbtasgen.segment.gen1;

import mrwint.gbtasgen.main.RomInfo;
import mrwint.gbtasgen.main.Runner;
import mrwint.gbtasgen.segment.gen1.sram.Intro;
import mrwint.gbtasgen.segment.gen1.sram.OakSpeech;
import mrwint.gbtasgen.segment.gen1.sram.Test;
import mrwint.gbtasgen.segment.util.SeqSegment;

public class Sram extends SeqSegment {

	@Override
	protected void execute() {
//		seq(new Intro());
//		save("intro1");
//
//		load("intro1");
//		seq(new OakSpeech());
//		save("oakSpeech1");

		load("oakSpeech1");
		seq(new Test());
		save("test1");
	}
	
	public static void main(String[] args) {
		RomInfo.rom = new RomInfo.RedRomInfo();
		
		Runner.run(new Sram());
	}
}
