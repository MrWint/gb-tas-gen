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
//		save("intro10");

//		load("intro10");
//		seq(new OakSpeech());
//		save("oakSpeech50");
		
//		load("oakSpeech50");
//		seq(new ChooseStarter());
//		save("chooseStarter50");
		
//		load("chooseStarter50");
//		seq(new RivalFight());
//		save("rivalFight20");
		
//		load("rivalFight15");
//		seq(new OaksParcel());
//		save("oaksParcel20");

//		load("oaksParcel20");
//		seq(new ViridianForestNormal());
//		save("viridianForestNormal30");

//		load("viridianForestNormal10");
//		seq(new PewterNormal());
//		save("pewterNormal20");

//		load("pewterNormal20");
//		seq(new Route3Normal());
//		save("route3Normal20");
		
//		load("route3Normal20");
//		seq(new MtMoonNormal());
//		save("mtMoonNormal50");
		
//		load("mtMoonNormal50");
//		seq(new NuggetBridgeNormal());
//		save("nuggetBridgeNormal20");

//		load("nuggetBridgeNormal20");
//		seq(new Underflow());
//		save("underflow20");

//		load("underflow20");
//		seq(new VictoryRoad());
//		save("victoryRoad100a");

		load("victoryRoad100a");
		seq(new Mansion());
		save("mansion1");

//		load("mansion");
//		seq(new CeruleanCave());
//		save("ceruleanCave");

//		load("ceruleanCave");
//		seq(new OldMan());
//		save("oldMan");

//		load("oldMan");
//		seq(new SeafoamIslands());
//		save("seafoamIslands");

//		load("seafoamIslands");
//		seq(new SafariZone());
//		save("safariZone");
	}
	
	public static void main(String[] args) {
		RomInfo.rom = new RomInfo.RedRomInfo();
		
		Runner.run(new CatchEmAll());
	}
}
