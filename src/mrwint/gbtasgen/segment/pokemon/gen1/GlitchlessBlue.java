package mrwint.gbtasgen.segment.pokemon.gen1;

import mrwint.gbtasgen.rom.RomInfo;
import mrwint.gbtasgen.rom.pokemon.gen1.BlueRomInfo;
import mrwint.gbtasgen.segment.SingleGbSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.glitchless.ChooseStarterSquirtle;
import mrwint.gbtasgen.segment.pokemon.gen1.glitchless.EliteFourSquirtle;
import mrwint.gbtasgen.segment.pokemon.gen1.glitchless.Intro;
import mrwint.gbtasgen.segment.pokemon.gen1.glitchless.MtMoonSquirtle;
import mrwint.gbtasgen.segment.pokemon.gen1.glitchless.NuggetBridgeNido;
import mrwint.gbtasgen.segment.pokemon.gen1.glitchless.NuggetBridgeSquirtle;
import mrwint.gbtasgen.segment.pokemon.gen1.glitchless.NuggetBridgeSquirtleAlt;
import mrwint.gbtasgen.segment.pokemon.gen1.glitchless.OakSpeech;
import mrwint.gbtasgen.segment.pokemon.gen1.glitchless.OaksParcel;
import mrwint.gbtasgen.segment.pokemon.gen1.glitchless.PewterSquirtle;
import mrwint.gbtasgen.segment.pokemon.gen1.glitchless.Rival1FightSquirtle;
import mrwint.gbtasgen.segment.pokemon.gen1.glitchless.RockTunnelSquirtle;
import mrwint.gbtasgen.segment.pokemon.gen1.glitchless.RockTunnelSquirtleAlt;
import mrwint.gbtasgen.segment.pokemon.gen1.glitchless.Route3Squirtle;
import mrwint.gbtasgen.segment.pokemon.gen1.glitchless.SafariSquirtle;
import mrwint.gbtasgen.segment.pokemon.gen1.glitchless.SilphCoSquirtle;
import mrwint.gbtasgen.segment.pokemon.gen1.glitchless.SurgeSquirtle;
import mrwint.gbtasgen.segment.pokemon.gen1.glitchless.SurgeSquirtleAlt;
import mrwint.gbtasgen.segment.pokemon.gen1.glitchless.TowerSquirtle;
import mrwint.gbtasgen.segment.pokemon.gen1.glitchless.VictoryRoadSquirtle;
import mrwint.gbtasgen.segment.pokemon.gen1.glitchless.ViridianForestSquirtle;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.state.Gameboy;
import mrwint.gbtasgen.state.SingleGbState;
import mrwint.gbtasgen.util.SingleGbRunner;

public class GlitchlessBlue extends SingleGbSegment {

  @Override
	protected void execute() {
		seq(new Intro());
//    save("intro1");
//
//    load("intro1");
    seq(new OakSpeech());
//    save("oakSpeech");
//
//    load("oakSpeech");
//    seq(new ChooseStarterSquirtle());
//    save("ChooseStarterSquirtle");

//    load("ChooseStarterSquirtle");
//    seq(new Rival1FightSquirtle());
//    save("Rival1FightSquirtle");

//    load("Rival1FightSquirtle");
//    seq(new OaksParcel());
//    save("OaksParcelSquirtle");

//    load("OaksParcelSquirtle");
//    seq(new ViridianForestSquirtle());
//    save("ViridianForestSquirtle");

//    load("ViridianForestSquirtle");
//    seq(new PewterSquirtle());
//    save("PewterSquirtle");

//    load("PewterSquirtle");
//    seq(new Route3Squirtle());
//    save("Route3Squirtle");

//    load("Route3Squirtle");
//    seq(new MtMoonSquirtle());
//    save("MtMoonSquirtle");

//    {
//    load("MtMoonSquirtle");
//    seq(new NuggetBridgeSquirtle());
//    save("NuggetBridgeSquirtle");

//    load("NuggetBridgeSquirtle");
//    seq(new SurgeSquirtle());
//    save("SurgeSquirtle");

//    load("SurgeSquirtle");
//    seq(new RockTunnelSquirtle());
//    save("RockTunnelSquirtle");

//    load("RockTunnelSquirtle");
//    }
    {
//      load("MtMoonSquirtle");
//      seq(new NuggetBridgeSquirtleAlt());
//      save("NuggetBridgeSquirtleAlt");

//      load("NuggetBridgeSquirtleAlt");
//      seq(new SurgeSquirtleAlt());
//      save("SurgeSquirtleAlt");

//      load("SurgeSquirtleAlt");
//      seq(new RockTunnelSquirtleAlt());
//      save("RockTunnelSquirtleAlt");

//      load("RockTunnelSquirtleAlt");
    }

//    seq(new TowerSquirtle());
//    save("TowerSquirtle");

//    load("TowerSquirtle");
//    seq(new SafariSquirtle());
//    save("SafariSquirtle");

//    load("SafariSquirtle");
//    seq(new SilphCoSquirtle());
//    save("SilphCoSquirtle");

//    load("SilphCoSquirtle");
//    seq(new VictoryRoadSquirtle());
//    save("VictoryRoadSquirtle");

//    load("VictoryRoadSquirtle");
//    seq(new EliteFourSquirtle());
//    save("EliteFourSquirtle");
	}

	public static void main(String[] args) throws Exception {
    SingleGbRunner.run(new BlueRomInfo(), new GlitchlessBlue());
	}
}
