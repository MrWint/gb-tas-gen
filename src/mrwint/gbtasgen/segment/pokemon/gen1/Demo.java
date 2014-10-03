package mrwint.gbtasgen.segment.pokemon.gen1;

import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.rom.RomInfo;
import mrwint.gbtasgen.rom.pokemon.gen1.RedRomInfo;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.gen1.demo.BrockDemo;
import mrwint.gbtasgen.segment.pokemon.gen1.demo.ChooseStarter;
import mrwint.gbtasgen.segment.pokemon.gen1.demo.Intro;
import mrwint.gbtasgen.segment.pokemon.gen1.demo.OakSpeech;
import mrwint.gbtasgen.segment.pokemon.gen1.demo.OaksParcel;
import mrwint.gbtasgen.segment.pokemon.gen1.demo.PewterNormal;
import mrwint.gbtasgen.segment.pokemon.gen1.demo.RivalFight;
import mrwint.gbtasgen.segment.pokemon.gen1.demo.ViridianForestNormal;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.util.Runner;

public class Demo extends SeqSegment {

	@Override
	protected void execute() {
//		seq(new Intro());
//		save("intro10");
//
//		load("intro10");
//		seq(new OakSpeech());
//		save("oakSpeech10");
//		
//		load("oakSpeech10");
//		seq(new ChooseStarter());
//		save("chooseStarter10");
		
//		load("chooseStarter10");
//		seq(new RivalFight());
//		save("rivalFight10");
		
//		load("rivalFight10");
//		seq(new OaksParcel());
//		save("oaksParcel10");

//		load("oaksParcel10");
//		seq(new ViridianForestNormal());
//		save("viridianForestNormal10");
//
//		load("viridianForestNormal10");
//		seq(new PewterNormal());
//		save("pewterNormal10");

		load("pewterNormal10");
		seq(new BrockDemo());
		save("brockDemo10");

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

//		load("victoryRoad100a");
//		seq(new Mansion());
//		save("mansion100");

//		load("mansion100");
//		seq(new CeruleanCave());
//		save("ceruleanCave100");

//		load("ceruleanCave100");
//		seq(new OldMan());
//		save("oldMan100");

//		load("oldMan100");
//		seq(new SeafoamIslands());
//		save("seafoamIslands100");

//		load("seafoamIslands100");
//		seq(new SafariZone());
//		save("safariZone100");
	}
	
	public static void main(String[] args) {
		RomInfo.pokemon = new RedRomInfo();
		
		Runner.run(new Demo());
	}
}
