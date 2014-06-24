package mrwint.gbtasgen.segment.gen1;

import mrwint.gbtasgen.main.RomInfo;
import mrwint.gbtasgen.main.Runner;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.gen1.catchemall.CeruleanCave;
import mrwint.gbtasgen.segment.gen1.catchemall.ChooseStarter;
import mrwint.gbtasgen.segment.gen1.catchemall.Intro;
import mrwint.gbtasgen.segment.gen1.catchemall.Mansion;
import mrwint.gbtasgen.segment.gen1.catchemall.MtMoonNidoking;
import mrwint.gbtasgen.segment.gen1.catchemall.MtMoonNoTrainer;
import mrwint.gbtasgen.segment.gen1.catchemall.MtMoonNormal;
import mrwint.gbtasgen.segment.gen1.catchemall.NuggetBridgeNidoking;
import mrwint.gbtasgen.segment.gen1.catchemall.NuggetBridgeNoTrainer;
import mrwint.gbtasgen.segment.gen1.catchemall.NuggetBridgeNormal;
import mrwint.gbtasgen.segment.gen1.catchemall.OakSpeech;
import mrwint.gbtasgen.segment.gen1.catchemall.OaksParcel;
import mrwint.gbtasgen.segment.gen1.catchemall.OldMan;
import mrwint.gbtasgen.segment.gen1.catchemall.PewterNormal;
import mrwint.gbtasgen.segment.gen1.catchemall.RivalFight;
import mrwint.gbtasgen.segment.gen1.catchemall.Route3Nidoking;
import mrwint.gbtasgen.segment.gen1.catchemall.Route3Normal;
import mrwint.gbtasgen.segment.gen1.catchemall.SafariZone;
import mrwint.gbtasgen.segment.gen1.catchemall.SeafoamIslands;
import mrwint.gbtasgen.segment.gen1.catchemall.Underflow;
import mrwint.gbtasgen.segment.gen1.catchemall.VictoryRoad;
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

//		load("viridianForestNormal");
//		seq(new PewterNormal());
//		save("pewterNormal");

//		load("pewterNormal");
//		seq(new Route3Normal());
//		save("route3Normal");
		
//		load("route3Normal");
//		seq(new MtMoonNormal());
//		save("mtMoonNormal");
		
//		load("mtMoonNormal");
//		seq(new NuggetBridgeNormal());
//		save("nuggetBridgeNormal");

//		load("nuggetBridgeNormal");
//		seq(new Underflow());
//		save("underflow");

//		load("underflow");
//		seq(new VictoryRoad());
//		save("victoryRoad");

//		load("victoryRoad");
//		seq(new Mansion());
//		save("mansion");

//		load("mansion");
//		seq(new CeruleanCave());
//		save("ceruleanCave");

//		load("ceruleanCave");
//		seq(new OldMan());
//		save("oldMan");

//		load("oldMan");
//		seq(new SeafoamIslands());
//		save("seafoamIslands");

		load("seafoamIslands");
		seq(new SafariZone());
		save("safariZone");
	}
	
	public static void main(String[] args) {
		RomInfo.rom = new RomInfo.RedRomInfo();
		
		Runner.run(new CatchEmAll());
	}
}
