package mrwint.gbtasgen.segment.pokemon.gen1;

import mrwint.gbtasgen.rom.RomInfo;
import mrwint.gbtasgen.rom.pokemon.gen1.RedRomInfo;
import mrwint.gbtasgen.segment.SingleGbSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.glitchless.MtMoonNido;
import mrwint.gbtasgen.segment.pokemon.gen1.glitchless.MtMoonSquirtle;
import mrwint.gbtasgen.segment.pokemon.gen1.glitchless.NuggetBridgeNido;
import mrwint.gbtasgen.segment.pokemon.gen1.glitchless.NuggetBridgeSquirtle;
import mrwint.gbtasgen.segment.pokemon.gen1.glitchless.OaksParcel;
import mrwint.gbtasgen.segment.pokemon.gen1.glitchless.PewterNido;
import mrwint.gbtasgen.segment.pokemon.gen1.glitchless.PewterSquirtle;
import mrwint.gbtasgen.segment.pokemon.gen1.glitchless.RockTunnelNido;
import mrwint.gbtasgen.segment.pokemon.gen1.glitchless.Route3Nido;
import mrwint.gbtasgen.segment.pokemon.gen1.glitchless.Route3Squirtle;
import mrwint.gbtasgen.segment.pokemon.gen1.glitchless.SurgeNido;
import mrwint.gbtasgen.segment.pokemon.gen1.glitchless.ViridianForestNido;
import mrwint.gbtasgen.segment.pokemon.gen1.glitchless.ViridianForestSquirtle;
import mrwint.gbtasgen.segment.util.SeqSegment;
import mrwint.gbtasgen.util.SingleGbRunner;

public class GlitchlessRed extends SingleGbSegment {

	@Override
	protected void execute() {
//		seq(new Intro());
//    save("intro1");
//
//    load("intro1");
//    seq(new OakSpeech());
//    save("oakSpeech");
//
//    {
//      load("oakSpeech");
//      seq(new ChooseStarterCharmander());
//      save("cooseStarter");
//
//      load("cooseStarter");
//      seq(new Rival1FightCharmander());
//      save("rival1Fight");
//
//      load("rival1Fight");
//      seq(new OaksParcelCharmander());
//      save("oaksParcel");
//
//      load("oaksParcel");
//      seq(new ViridianForestCharmander());
//      save("viridianForestCharmander");
//
//      load("viridianForestCharmander");
//      seq(new PewterCharmander());
//      save("pewterCharmander");
//
//      load("pewterCharmander");
//      seq(new Route3Charmander());
//      save("route3Charmander");

//      load("route3Charmander");
//    }
	  {
//    load("oakSpeech");
//    seq(new ChooseStarterSquirtle());
//    save("ChooseStarterSquirtle");

//    load("ChooseStarterSquirtle");
//    seq(new Rival1FightSquirtle());
//    save("Rival1FightSquirtle");

//    load("Rival1FightSquirtle");
//    seq(new OaksParcel());
//    save("OaksParcelSquirtle");

//      load("OaksParcelSquirtle");
//      seq(new ViridianForestNido());
//      save("ViridianForestNido");
//
//      load("ViridianForestNido");
//      seq(new PewterNido());
//      save("PewterNido");

//      load("PewterNido");
//      seq(new Route3Nido());
//      save("Route3Nido");

//      load("Route3Nido");
//      seq(new MtMoonNido());
//      save("MtMoonNido");

//      load("MtMoonNido");
//      seq(new NuggetBridgeNido());
//      save("NuggetBridgeNido");

//      load("NuggetBridgeNido");
//      seq(new SurgeNido());
//      save("SurgeNido");

//      load("SurgeNido");
//      seq(new RockTunnelNido());
//      save("RockTunnelNido");

//      load("RockTunnelNido");
    }
	}

	public static void main(String[] args) {
    SingleGbRunner.run(new RedRomInfo(), new GlitchlessRed());
	}
}
