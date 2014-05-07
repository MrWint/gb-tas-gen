package mrwint.gbtasgen.segment.gen1;

import mrwint.gbtasgen.main.RomInfo;
import mrwint.gbtasgen.main.Runner;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.gen1.catchemall.ChooseStarter;
import mrwint.gbtasgen.segment.gen1.catchemall.Intro;
import mrwint.gbtasgen.segment.gen1.catchemall.OakSpeech;
import mrwint.gbtasgen.segment.gen1.catchemall.OaksParcel;
import mrwint.gbtasgen.segment.gen1.catchemall.RivalFight;
import mrwint.gbtasgen.segment.gen1.catchemall.ViridianForestNidoking;
import mrwint.gbtasgen.segment.gen1.catchemall.ViridianForestNormal;
import mrwint.gbtasgen.segment.util.SeqSegment;

public class CatchEmAll extends SeqSegment {

	@Override
	protected void execute() {
//		seq(new Intro());
//		save("intro");

//		load("intro");
//		seq(new OakSpeech());
//		save("oakSpeech");
		
//		load("oakSpeech");
//		seq(new ChooseStarter());
//		save("chooseStarter");
		
//		load("chooseStarter");
//		seq(new RivalFight());
//		save("rivalFight");
		
//		load("rivalFight");
//		seq(new OaksParcel());
//		save("oaksParcel");
		
//		load("oaksParcel");
//		seq(new ViridianForestNormal());
//		save("viridianForestNormal");
		
		load("oaksParcel");
		seq(new ViridianForestNidoking());
		save("viridianForestNidoking");
//		seq(Segment.press(Move.START));
	}
	
	public static void main(String[] args) {
		RomInfo.rom = new RomInfo.RedRomInfo();
		
		Runner.run(new CatchEmAll());
	}
}
