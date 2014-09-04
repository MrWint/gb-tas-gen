package mrwint.gbtasgen.segment.gen1;

import mrwint.gbtasgen.main.RomInfo;
import mrwint.gbtasgen.main.Runner;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.gen1.noww.BrockToEliteFour;
import mrwint.gbtasgen.segment.gen1.noww.BrockToEliteFourTM;
import mrwint.gbtasgen.segment.gen1.noww.BrockToTM12;
import mrwint.gbtasgen.segment.gen1.noww.ChooseStarter;
import mrwint.gbtasgen.segment.gen1.noww.EliteFour;
import mrwint.gbtasgen.segment.gen1.noww.EliteFourTM;
import mrwint.gbtasgen.segment.gen1.noww.Intro;
import mrwint.gbtasgen.segment.gen1.noww.OakSpeech;
import mrwint.gbtasgen.segment.gen1.noww.OaksParcel;
import mrwint.gbtasgen.segment.gen1.noww.PewterNormal;
import mrwint.gbtasgen.segment.gen1.noww.RivalFight;
import mrwint.gbtasgen.segment.gen1.noww.ViridianForestNidoking;
import mrwint.gbtasgen.segment.util.SeqSegment;

public class NoWW extends SeqSegment {

	@Override
	protected void execute() {
//		seq(new Intro());
//		save("intro100");
//
//		load("intro100");
//		seq(new OakSpeech());
//		save("oakSpeech100");
		
//		load("oakSpeech100");
//		seq(new ChooseStarter());
//		save("chooseStarter100a");
		
//		load("chooseStarter100a");
//		seq(new RivalFight());
//		save("rivalFightLose50");
		
//		load("rivalFightLose50");
//		seq(new OaksParcel());
//		save("oaksParcel50");

//		load("oaksParcel50");
//		seq(new ViridianForestNidoking());
//		save("viridianForestNidoking100");
//
//		load("viridianForestNidoking100");
//		seq(new PewterNormal());
//		save("pewterNormal100");
//
//		load("pewterNormal1");
//		seq(new BrockToTM12());
//		save("brockToTM1");

//		load("brockToTM1");
//		seq(new BrockToEliteFourTM());
//		save("brockToEliteFourTM1");

//		load("brockToEliteFourTM1");
//		seq(new EliteFourTM());
//		save("eliteFourTM1");

//		load("pewterNormal100");
//		seq(new BrockToEliteFour());
//		save("brockToEliteFour100");
//
		load("brockToEliteFour100");
		seq(new EliteFour());
		save("eliteFour100");
	}
	
	public static void main(String[] args) {
		RomInfo.rom = new RomInfo.RedRomInfo();
		
		Runner.run(new NoWW());
	}
}
