package mrwint.gbtasgen.segment.pokemon.gen1;

import static mrwint.gbtasgen.move.Move.A;
import static mrwint.gbtasgen.move.Move.B;
import static mrwint.gbtasgen.move.Move.DOWN;
import static mrwint.gbtasgen.move.Move.LEFT;
import static mrwint.gbtasgen.move.Move.RIGHT;
import static mrwint.gbtasgen.move.Move.START;
import static mrwint.gbtasgen.move.Move.UP;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.rom.pokemon.gen1.BlueRomInfo;
import mrwint.gbtasgen.rom.pokemon.gen1.RedRomInfo;
import mrwint.gbtasgen.segment.DualGbSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.CeladonBlue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.CeruleanCaveBlue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.CeruleanRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.ChooseSecondStarterRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.ChooseStarterBlue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.ChooseStarterDummyRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.CinnabarBlue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.CinnabarRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.CyclingRoadRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.GetDiploma;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.LickitungRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.MtMoonBlue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.MtMoonDummyRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.MtMoonRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.NuggetBridgeBlue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.NuggetBridgeDummyRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.OakSpeechBlue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.OakSpeechDummyRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.OakSpeechRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.OaksParcel;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.PewterBlue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.PewterDummyRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.PewterRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.PorygonBlue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.PostTradeTest;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.PowerplantRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.PreRockTunnelBlue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.PrepEndTrade2Blue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.PrepEndTrade2Red;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.PrepEndTrade3Blue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.PrepEndTrade3Red;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.PrepEndTrade4Blue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.PrepEndTrade4Red;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.PrepareBulbasaurTradeRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.PrepareEeveeTradeDummyRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.PrepareTradeTest;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.Rival1FightAgainRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.Rival1FightBlue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.Rival1FightDummyRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.PrepareEeveeTradeBlue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.RockTunnelBlue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.RockTunnelDummyRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.RockTunnelRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.RocketHideoutBlue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.RocketHideoutRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.Route3Blue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.Route3DummyRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.Route3Red;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.PrepareSquirtleTradeBlue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.SafariBlue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.SafariRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.SeafoamBlue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.SeafoamRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.SilphCoBlue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.SilphCoRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.SurgeBlue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.SurgeDummyRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.SurgeRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.TowerBlue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.PrepareSquirtleTradeRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.TowerRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.VictoryRoadBlue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.VictoryRoadRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.ViridianForestBlue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.ViridianForestDummyRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.ViridianForestRed;
import mrwint.gbtasgen.state.DualGbHelper;
import mrwint.gbtasgen.util.DualGbRunner;

public class Coop extends DualGbSegment {

  @Override
	protected void execute() {
    { // Blue Segments
//      seqL(new OakSpeechBlue());
//      saveL("OakSpeechBlue2");
//      loadL("OakSpeechBlue2");
//      seqL(new ChooseStarterBlue());
//      saveL("ChooseStarterBlue2");
//      loadL("ChooseStarterBlue2"); // fcff
//      seqL(new Rival1FightBlue());
//      saveL("Rival1FightBlue2");
//      loadL("Rival1FightBlue2");
//      seqL(new OaksParcel());
//      saveL("OaksParcel2b");
//      loadL("OaksParcel2b");
//      seqL(new ViridianForestBlue());
//      saveL("ViridianForestBlue2");
//      loadL("ViridianForestBlue2");
//      seqL(new PewterBlue());
//      saveL("PewterBlue2");
//      loadL("PewterBlue2");
//      seqL(new Route3Blue());
//      saveL("Route3Blue2a");
//      loadL("Route3Blue2a");
//      seqL(new MtMoonBlue());
////      saveL("MtMoonBlue2a");
//      loadL("MtMoonBlue2a");
//      seqL(new NuggetBridgeBlue());
//      saveL("NuggetBridgeBlue2");
//      loadL("NuggetBridgeBlue2");
//      seqL(new SurgeBlue());
//      saveL("SurgeBlue2a");
//      loadL("SurgeBlue2a");
//      seqL(new PrepareEeveeTradeBlue());
//      saveL("PrepareEeveeTradeBlue2a");
//      loadL("PrepareEeveeTradeBlue2a");
    }
    { // Red Segments
//      seqR(new OakSpeechDummyRed());
//      saveR("OakSpeechDummyRed2");
//      loadR("OakSpeechDummyRed2");
//      seqR(new ChooseStarterDummyRed());
//      saveR("ChooseStarterDummyRed2");
//      loadR("ChooseStarterDummyRed2"); // e0df
//      seqR(new Rival1FightDummyRed());
//      saveR("Rival1FightDummyRed2a");
//      loadR("Rival1FightDummyRed2a");
//      seqR(new OaksParcel());
//      saveR("OaksParcelDummyRed2a");
//      loadR("OaksParcelDummyRed2a");
//      seqR(new ViridianForestDummyRed());
//      saveR("ViridianForestDummyRed2");
//      loadR("ViridianForestDummyRed2");
//      seqR(new PewterDummyRed());
//      saveR("PewterDummyRed2a");
//      loadR("PewterDummyRed2a");
//      seqR(new Route3DummyRed());
//      saveR("Route3DummyRed2a");
//      loadR("Route3DummyRed2a");
//      seqR(new MtMoonDummyRed());
//      saveR("MtMoonDummyRed2");
//      loadR("MtMoonDummyRed2");
//      seqR(new NuggetBridgeDummyRed());
//      saveR("NuggetBridgeDummyRed2a");
//      loadR("NuggetBridgeDummyRed2a");
//      seqR(new SurgeDummyRed());
//      saveR("SurgeDummyRed2");
//      loadR("SurgeDummyRed2");
//      seqR(new RockTunnelDummyRed());
//      saveR("RockTunnelDummyRed2");
//      loadR("RockTunnelDummyRed2");
//      seqR(new PrepareEeveeTradeDummyRed());
//      saveR("PrepareEeveeTradeDummyRed2");
//      loadR("PrepareEeveeTradeDummyRed2");
    }
//    seqDualInputs(
//        new DualGbHelper.InputBuilder()
//            .withButton(A, 0) // talk to cable club
//            .withButton(B, 43) // welcome ; please apply
//            .withButton(B, 31) // before, we have to
//            .withButton(A, 18) // save the game
//            .withButton(A, 40) // save, where do you want to go (trade center)
//            .withButton(LEFT, 34) // just a moment; face table
//            .withButton(A, 1) // use table
//            .withButton(A, 15) // just a moment; select Beedrill
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 28) // x and y will
//            .withButton(A, 10) // be traded
//            .withButton(A, 1) // select Caterpie
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 24) // x and y will
//            .withButton(A, 11) // be traded
//            .reset(0)
////            .lookAndSee()
//            .build(),
//        new DualGbHelper.InputBuilder()
//            .withButton(A, 0) // talk to cable club
//            .withButton(B, 44) // welcome ; please apply
//            .withButton(B, 31) // before, we have to
//            .withButton(A, 18) // save the game
//            .withButton(0, 39) // save, where do you want to go (trade center by L)
//            .withButton(RIGHT, 35) // just a moment; face table
//            .withButton(A, 1) // use table
//            .withButton(A, 15) // just a moment; select charmander
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 28) // x and y will
//            .withButton(A, 10) // be traded
//            .withButton(A, 2) // select eevee
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 24) // x and y will
//            .withButton(A, 11) // be traded
//            .reset(0)
////            .lookAndSee()
//            .build());
    { // Blue Segments
//      saveL("PostEeveeTradeBlue2");
//      loadL("PostEeveeTradeBlue2");
//      seqL(new PreRockTunnelBlue());
//      saveL("PreRockTunnelBlue2");
//      loadL("PreRockTunnelBlue2");
    }
    { // Red Segments
//      saveR("PostEeveeTradeRed2");
//      loadR("PostEeveeTradeRed2");
//      seqR(new OakSpeechRed());
//      saveR("OakSpeechRed2");
//      loadR("OakSpeechRed2");
//      seqR(new ChooseSecondStarterRed());
//      saveR("ChooseSecondStarterRed2");
//      loadR("ChooseSecondStarterRed2");
//      seqR(new Rival1FightAgainRed());
//      saveR("Rival1FightAgainRed2a");
//      loadR("Rival1FightAgainRed2");
//      seqR(new OaksParcel());
//      saveR("OaksParcel2a");
//      loadR("OaksParcel2a");
//      seqR(new PrepareBulbasaurTradeRed());
//      saveR("PrepareBulbasaurTradeRed2a");
//      loadR("PrepareBulbasaurTradeRed2a");
    }
//    seqDualInputs(
//        new DualGbHelper.InputBuilder()
//            .withButton(A, 0) // talk to cable club
//            .withButton(B, 40) // welcome ; please apply
//            .withButton(B, 30) // before, we have to
//            .withButton(A, 16) // save the game
//            .withButton(A, 34) // save, where do you want to go (trade center)
//            .withButton(RIGHT, 28) // just a moment; face table
//            .withButton(A, 0) // use table
//            .withButton(DOWN, 14) // just a moment; select Charmander
//            .withButton(DOWN, 1) // just a moment; select Charmander
//            .withButton(DOWN, 1) // just a moment; select Charmander
//            .withButton(DOWN | A, 1) // just a moment; select Charmander
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 27) // x and y will
//            .withButton(A, 10) // be traded
//            .reset(0)
////            .lookAndSee()
//            .build(),
//        new DualGbHelper.InputBuilder()
//            .withButton(A, 0) // talk to cable club
//            .withButton(B, 43) // welcome ; please apply
//            .withButton(B, 31) // before, we have to
//            .withButton(A, 18) // save the game
//            .withButton(0, 34) // save, where do you want to go (trade center by L)
//            .withButton(LEFT, 36) // just a moment; face table
//            .withButton(A, 0) // use table
//            .withButton(A, 15) // just a moment; select Bulbasaur
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 29) // x and y will
//            .withButton(A, 11) // be traded
//            .reset(0)
////            .lookAndSee()
//            .build());
    { // Blue Segments
//      saveL("PostBulbasaurTradeBlue2a");
//      loadL("PostBulbasaurTradeBlue2a");
//      seqL(new RockTunnelBlue());
//      saveL("RockTunnelBlue2");
//      loadL("RockTunnelBlue2");
//      seqL(new CeladonBlue());
//      saveL("CeladonBlue3");
//      loadL("CeladonBlue3");
    }
    { // Red Segments
//      saveR("PostBulbasaurTradeRed2");
//      loadR("PostBulbasaurTradeRed2");
//      seqR(new ViridianForestRed());
//      saveR("ViridianForestRed2");
//      loadR("ViridianForestRed2");
//      seqR(new PewterRed());
//      saveR("PewterRed2");
//      loadR("PewterRed2");
//      seqR(new Route3Red());
//      saveR("Route3Red2");
//      loadR("Route3Red2");
//      seqR(new MtMoonRed());
//      saveR("MtMoonRed2");
//      loadR("MtMoonRed2");
//      seqR(new CeruleanRed());
//      saveR("CeruleanRed2");
//      loadR("CeruleanRed2");
    }
//    seqDualInputs(
//        new DualGbHelper.InputBuilder()
//            .withButton(A, 0) // talk to cable club
//            .withButton(B, 44) // welcome ; please apply
//            .withButton(B, 32) // before, we have to
//            .withButton(A, 17) // save the game
//            .withButton(A, 40) // save, where do you want to go (trade center)
//            .withButton(RIGHT, 31) // just a moment; face table
//            .withButton(A, 1) // use table
//            .withButton(DOWN | A, 16) // just a moment; select Abra
//            .withButton(RIGHT, 1) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 26) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(DOWN | A, 1) // just a moment; select Farfetch'd
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 26) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(DOWN, 0) // just a moment; select Meowth
//            .withButton(DOWN, 1) // just a moment; select Meowth
//            .withButton(DOWN | A, 1) // just a moment; select Meowth
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 20) // x and y will
//            .withButton(A, 11) // be traded
//            .reset(0)
////            .lookAndSee()
//            .build(),
//        new DualGbHelper.InputBuilder()
//            .withButton(A, 0) // talk to cable club
//            .withButton(B, 43) // welcome ; please apply
//            .withButton(B, 31) // before, we have to
//            .withButton(A, 18) // save the game
//            .withButton(0, 40) // save, where do you want to go (trade center by L)
//            .withButton(LEFT, 29) // just a moment; face table
//            .withButton(A, 0) // use table
//            .withButton(DOWN | A, 15) // just a moment; select Pikachu
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 27) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(DOWN, 0) // just a moment; select Pidgey
//            .withButton(DOWN | A, 1) // just a moment; select Pidgey
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 26) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(DOWN, 0) // just a moment; select Ekans
//            .withButton(DOWN | A, 1) // just a moment; select Ekans
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 20) // x and y will
//            .withButton(A, 11) // be traded
//            .reset(0)
////            .lookAndSee()
//            .build());
    { // Blue Segments
//      saveL("PostAbraTradeBlue3");
//      loadL("PostAbraTradeBlue3");
//      seqL(new TowerBlue());
//      saveL("TowerBlue2");
//      loadL("TowerBlue2");
//      seqL(new PrepareSquirtleTradeBlue());
//      saveL("PrepareSquirtleTradeBlue2a");
//      loadL("PrepareSquirtleTradeBlue2a");
    }
    { // Red Segments
//      saveR("PostAbraTradeRed3");
//      loadR("PostAbraTradeRed3");
//      seqR(new SurgeRed());
//      saveR("SurgeRed3");
//      loadR("SurgeRed3");
//      seqR(new RockTunnelRed());
//      saveR("RockTunnelRed3");
//      loadR("RockTunnelRed3");
//      seqR(new PrepareSquirtleTradeRed());
//      saveR("PrepareSquirtleTradeRed3");
//      loadR("PrepareSquirtleTradeRed3"); // 2a
    }
//    seqDualInputs(
//        new DualGbHelper.InputBuilder()
//            .withButton(A, 0) // talk to cable club
//            .withButton(B, 44) // welcome ; please apply
//            .withButton(B, 31) // before, we have to
//            .withButton(A, 17) // save the game
//            .withButton(A, 40) // save, where do you want to go (trade center)
//            .withButton(RIGHT, 30) // just a moment; face table
//            .withButton(A, 0) // use table
//            .withButton(DOWN, 15) // just a moment; select Squirtle
//            .withButton(DOWN | A, 1) // just a moment; select Squirtle
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 25) // x and y will
//            .withButton(A, 10) // be traded
//            .withButton(DOWN, 0) // just a moment; select Bellsprout
//            .withButton(DOWN, 1) // just a moment; select Bellsprout
//            .withButton(DOWN | A, 1) // just a moment; select Bellsprout
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 26) // x and y will
//            .withButton(A, 10) // be traded
//            .withButton(DOWN, 0) // just a moment; select Flareon
//            .withButton(DOWN, 1) // just a moment; select Flareon
//            .withButton(DOWN | A, 1) // just a moment; select Flareon
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 25) // x and y will
//            .withButton(A, 10) // be traded
//            .reset(0)
////            .lookAndSee()
//            .build(),
//        new DualGbHelper.InputBuilder()
//            .withButton(A, 0) // talk to cable club
//            .withButton(B, 42) // welcome ; please apply
//            .withButton(B, 30) // before, we have to
//            .withButton(A, 16) // save the game
//            .withButton(0, 40) // save, where do you want to go (trade center by L)
//            .withButton(LEFT, 23) // just a moment; face table
//            .withButton(A, 0) // use table
//            .withButton(DOWN, 15) // just a moment; select Mr. Mime
//            .withButton(DOWN | A, 1) // just a moment; select Mr. Mime
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 1) // select trade
//            .withButton(B, 23) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(DOWN, 0) // just a moment; select Mankey
//            .withButton(DOWN | A, 1) // just a moment; select Mankey
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 24) // x and y will
//            .withButton(A, 10) // be traded
//            .withButton(DOWN, 0) // just a moment; select Growlithe
//            .withButton(DOWN | A, 1) // just a moment; select Growlithe
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 25) // x and y will
//            .withButton(A, 10) // be traded
//            .reset(0)
//            .build());
    { // Blue Segments
//      saveL("PostSquirtleTradeBlue2");
//      loadL("PostSquirtleTradeBlue2");
//      seqL(new SafariBlue());
//      saveL("SafariBlue2");
//      loadL("SafariBlue2");
//      seqL(new CinnabarBlue());
//      saveL("CinnabarBlue2");
//      loadL("CinnabarBlue2");
    }
    { // Red Segments
//      saveR("PostSquirtleTradeRed2");
//      loadR("PostSquirtleTradeRed2");
//      seqR(new RocketHideoutRed());
//      saveR("RocketHideoutRed2");
//      loadR("RocketHideoutRed2");
//      seqR(new TowerRed());
//      saveR("TowerRed2");
//      loadR("TowerRed2");
//      seqR(new CyclingRoadRed());
//      saveR("CyclingRoadRed2");
//      loadR("CyclingRoadRed2");
    }
//    seqDualInputs(
//        new DualGbHelper.InputBuilder()
//            .withButton(A, 0) // talk to cable club
//            .withButton(B, 44) // welcome ; please apply
//            .withButton(B, 31) // before, we have to
//            .withButton(A, 17) // save the game
//            .withButton(A, 39) // save, where do you want to go (trade center)
//            .withButton(LEFT, 30) // just a moment; face table
//            .withButton(A, 0) // use table
//            .withButton(A, 15) // just a moment; select Vulpix
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 26) // x and y will
//            .withButton(A, 10) // be traded
//            .withButton(A, 1) // just a moment; select Sandshrew
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 23) // x and y will
//            .withButton(A, 10) // be traded
//            .withButton(A, 1) // just a moment; select Magmar
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 24) // x and y will
//            .withButton(A, 10) // be traded
//            .withButton(A, 1) // just a moment; select Omanyte
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 23) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(A, 1) // just a moment; select Pinsir
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 21) // x and y will
//            .withButton(A, 11) // be traded
//            .reset(0)
////            .lookAndSee()
//            .build(),
//        new DualGbHelper.InputBuilder()
//            .withButton(A, 0) // talk to cable club
//            .withButton(B, 45) // welcome ; please apply
//            .withButton(B, 31) // before, we have to
//            .withButton(A, 17) // save the game
//            .withButton(0, 40) // save, where do you want to go (trade center by L)
//            .withButton(RIGHT, 31) // just a moment; face table
//            .withButton(A, 0) // use table
//            .withButton(A, 15) // just a moment; select Charmeleon
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 25) // x and y will
//            .withButton(A, 10) // be traded
//            .withButton(A, 1) // just a moment; select Persian
//            .withButton(RIGHT, 1) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 25) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(DOWN | A, 1) // just a moment; select Wartortle
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 24) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(DOWN | A, 1) // just a moment; select Cubone
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 22) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(DOWN | A, 1) // just a moment; select Oddish
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 20) // x and y will
//            .withButton(A, 10) // be traded
//            .reset(0)
//            .build());
    { // Blue Segments
//      saveL("PostOmanyteTradeBlue2");
//      loadL("PostOmanyteTradeBlue2");
//      seqL(new SilphCoBlue());
//      saveL("SilphCoBlue3");
//      loadL("SilphCoBlue3");
//      seqL(new VictoryRoadBlue());
//      saveL("VictoryRoadBlue3");
//      loadL("VictoryRoadBlue3");
//      seqL(new SeafoamBlue());
//      saveL("SeafoamBlue3");
//      loadL("SeafoamBlue3");
//      seqL(new CeruleanCaveBlue());
//      saveL("CeruleanCaveBlue3");
//      loadL("CeruleanCaveBlue3");
//      seqL(new PorygonBlue());
//      saveL("PorygonBlue3");
//      loadL("PorygonBlue3");
    }
    { // Red Segments
//      saveR("PostOmanyteTradeRed2");
//      loadR("PostOmanyteTradeRed2");
//      seqR(new SafariRed());
//      saveR("SafariRed3");
//      loadR("SafariRed3");
//      seqR(new CinnabarRed());
//      saveR("CinnabarRed3");
//      loadR("CinnabarRed3");
//      seqR(new SilphCoRed());
//      saveR("SilphCoRed3");
//      loadR("SilphCoRed3");
//      seqR(new SeafoamRed());
//      saveR("SeafoamRed3");
//      loadR("SeafoamRed3");
//      seqR(new PowerplantRed());
//      saveR("PowerplantRed3");
//      loadR("PowerplantRed3");
//      seqR(new VictoryRoadRed());
//      saveR("VictoryRoadRed3");
//      loadR("VictoryRoadRed3");
//      seqR(new LickitungRed());
//      saveR("LickitungRed3");
//      loadR("LickitungRed3");
    }
//    seqDualInputs(
//        new DualGbHelper.InputBuilder()
//            .withButton(A, 0) // talk to cable club
//            .withButton(B, 44) // welcome ; please apply
//            .withButton(B, 31) // before, we have to
//            .withButton(A, 18) // save the game
//            .withButton(A, 40) // save, where do you want to go (trade center)
//            .withButton(LEFT, 31) // just a moment; face table
//            .withButton(A, 1) // use table
//            .withButton(A, 15) // just a moment; select 1
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 27) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(DOWN | A, 1) // just a moment; select 2
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 23) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(DOWN | A, 1) // just a moment; select 3
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 25) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(DOWN | A, 1) // just a moment; select 4
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 25) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(DOWN | A, 1) // just a moment; select 5
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 25) // x and y will
//            .withButton(A, 11) // be traded
//            .reset(0)
//      //      .lookAndSee()
//            .build(),
//        new DualGbHelper.InputBuilder()
//            .withButton(A, 0) // talk to cable club
//            .withButton(B, 43) // welcome ; please apply
//            .withButton(B, 32) // before, we have to
//            .withButton(A, 18) // save the game
//            .withButton(0, 40) // save, where do you want to go (trade center by L)
//            .withButton(RIGHT, 36) // just a moment; face table
//            .withButton(A, 1) // use table
//            .withButton(A, 15) // just a moment; select 1
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 27) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(A, 1) // just a moment; select 2
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 22) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(A, 1) // just a moment; select 3
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 24) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(A, 1) // just a moment; select 4
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 25) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(DOWN | A, 1) // just a moment; select 5
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 25) // x and y will
//            .withButton(A, 11) // be traded
//            .reset(0)
//            .build());
    { // Blue Segments
//      saveL("PostEndTrade1Blue3b");
//      loadL("PostEndTrade1Blue3b");
//      seqL(new PrepEndTrade2Blue());
//      saveL("PrepEndTrade2Blue3b");
//      loadL("PrepEndTrade2Blue3b");
    }
    { // Red Segments
//      saveR("PostEndTrade1Red3b");
//      loadR("PostEndTrade1Red3b");
//      seqR(new PrepEndTrade2Red());
//      saveR("PrepEndTrade2Red3b");
//      loadR("PrepEndTrade2Red3b");
    }
//    seqDualInputs(
//        new DualGbHelper.InputBuilder()
//            .withButton(A, 0) // talk to cable club
//            .withButton(B, 44) // welcome ; please apply
//            .withButton(B, 32) // before, we have to
//            .withButton(A, 18) // save the game
//            .withButton(A, 40) // save, where do you want to go (trade center)
//            .withButton(LEFT, 31) // just a moment; face table
//            .withButton(A, 1) // use table
//            .withButton(DOWN, 15) // just a moment; select 1
//            .withButton(DOWN, 1) // just a moment; select 1
//            .withButton(DOWN, 1) // just a moment; select 1
//            .withButton(DOWN | A, 1) // just a moment; select 1 // KADABRA
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 24) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(A, 130) // just a moment; select 2
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 29) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(A, 1) // just a moment; select 3
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 26) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(A, 1) // just a moment; select 4
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 29) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(A, 1) // just a moment; select 5
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 26) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(A, 1) // just a moment; select 6
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 26) // x and y will
//            .withButton(A, 11) // be traded
//            .reset(0)
////            .lookAndSee()
//            .build(),
//        new DualGbHelper.InputBuilder()
//            .withButton(A, 0) // talk to cable club
//            .withButton(B, 44) // welcome ; please apply
//            .withButton(B, 32) // before, we have to
//            .withButton(A, 18) // save the game
//            .withButton(0, 40) // save, where do you want to go (trade center by L)
//            .withButton(RIGHT, 36) // just a moment; face table
//            .withButton(A, 1) // use table
//            .withButton(DOWN, 15) // just a moment; select 1
//            .withButton(DOWN | A, 1) // just a moment; select 1 // MACHOKE
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 24) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(A, 130) // just a moment; select 2
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 29) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(DOWN | A, 1) // just a moment; select 3
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 26) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(DOWN | A, 1) // just a moment; select 4
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 29) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(DOWN | A, 1) // just a moment; select 5
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 26) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(DOWN | A, 1) // just a moment; select 6 // re-trade
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 26) // x and y will
//            .withButton(A, 11) // be traded
//            .reset(0)
//            .build());
    { // Blue Segments
//      saveL("PostEndTrade2Blue3b");
//      loadL("PostEndTrade2Blue3b");
//      seqL(new PrepEndTrade3Blue());
//      saveL("PrepEndTrade3Blue3b");
//      loadL("PrepEndTrade3Blue3b");
    }
    { // Red Segments
//      saveR("PostEndTrade2Red3b");
//      loadR("PostEndTrade2Red3b");
//      seqR(new PrepEndTrade3Red());
//      saveR("PrepEndTrade3Red3b");
//      loadR("PrepEndTrade3Red3b");
    }
//    seqDualInputs(
//        new DualGbHelper.InputBuilder()
//            .withButton(A, 0) // talk to cable club
//            .withButton(B, 43) // welcome ; please apply
//            .withButton(B, 31) // before, we have to
//            .withButton(A, 17) // save the game
//            .withButton(A, 40) // save, where do you want to go (trade center)
//            .withButton(LEFT, 31) // just a moment; face table
//            .withButton(A, 1) // use table
//            .withButton(A, 15) // just a moment; select 1
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 26) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(A, 1) // just a moment; select 2
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 26) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(A, 1) // just a moment; select 3
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 26) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(A, 1) // just a moment; select 4
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 29) // x and y will
//            .withButton(A, 11) // be traded
//            .reset(0)
////            .lookAndSee()
//            .build(),
//        new DualGbHelper.InputBuilder()
//            .withButton(A, 0) // talk to cable club
//            .withButton(B, 44) // welcome ; please apply
//            .withButton(B, 31) // before, we have to
//            .withButton(A, 17) // save the game
//            .withButton(0, 36) // save, where do you want to go (trade center by L)
//            .withButton(RIGHT, 34) // just a moment; face table
//            .withButton(A, 1) // use table
//            .withButton(A, 15) // just a moment; select 1
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 26) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(DOWN, 0) // just a moment; select 2
//            .withButton(DOWN | A, 1) // just a moment; select 2
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 26) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(DOWN, 0) // just a moment; select 3
//            .withButton(DOWN | A, 1) // just a moment; select 3
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 26) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(DOWN, 0) // just a moment; select 4
//            .withButton(DOWN | A, 1) // just a moment; select 4
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 28) // x and y will
//            .withButton(A, 11) // be traded
//            .reset(0)
//            .build());
    { // Blue Segments
//      saveL("PostEndTrade3Blue3b");
//      loadL("PostEndTrade3Blue3b");
//      seqL(new PrepEndTrade4Blue());
//      saveL("PrepEndTrade4Blue3b");
//      loadL("PrepEndTrade4Blue3b");
    }
    { // Red Segments
//      saveR("PostEndTrade3Red3b");
//      loadR("PostEndTrade3Red3b");
//      seqR(new PrepEndTrade4Red());
//      saveR("PrepEndTrade4Red3b");
//      loadR("PrepEndTrade4Red3b");
    }
//    seqDualInputs(
//        new DualGbHelper.InputBuilder()
//            .withButton(A, 0) // talk to cable club
//            .withButton(B, 45) // welcome ; please apply
//            .withButton(B, 31) // before, we have to
//            .withButton(A, 17) // save the game
//            .withButton(A, 39) // save, where do you want to go (trade center)
//            .withButton(LEFT, 31) // just a moment; face table
//            .withButton(A, 1) // use table
//            .withButton(DOWN, 15) // just a moment; select GRAVELER
//            .withButton(DOWN, 1) // just a moment; select GRAVELER
//            .withButton(DOWN, 1) // just a moment; select GRAVELER
//            .withButton(DOWN, 1) // just a moment; select GRAVELER
//            .withButton(DOWN | A, 1) // just a moment; select GRAVELER
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 26) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(A, 130) // just a moment; select 1
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 27) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(A, 1) // just a moment; select 2
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 26) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(A, 1) // just a moment; select 3
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 26) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(A, 1) // just a moment; select 4
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 26) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(A, 1) // just a moment; select 5
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 26) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(A, 1) // just a moment; select 6
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 26) // x and y will
//            .withButton(A, 11) // be traded
//            .reset(0)
////            .lookAndSee()
//            .build(),
//        new DualGbHelper.InputBuilder()
//            .withButton(A, 0) // talk to cable club
//            .withButton(B, 44) // welcome ; please apply
//            .withButton(B, 33) // before, we have to
//            .withButton(A, 17) // save the game
//            .withButton(0, 34) // save, where do you want to go (trade center by L)
//            .withButton(RIGHT, 36) // just a moment; face table
//            .withButton(A, 1) // use table
//            .withButton(DOWN, 15) // just a moment; select HAUNTER
//            .withButton(DOWN, 1) // just a moment; select HAUNTER
//            .withButton(DOWN, 1) // just a moment; select HAUNTER
//            .withButton(DOWN | A, 1) // just a moment; select HAUNTER
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 26) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(A, 130) // just a moment; select 1
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 27) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(A, 1) // just a moment; select 2
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 26) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(A, 1) // just a moment; select 3
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 26) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(A, 1) // just a moment; select 4
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 26) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(A, 1) // just a moment; select 5
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 26) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(A, 1) // just a moment; select 6
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 26) // x and y will
//            .withButton(A, 11) // be traded
//            .reset(0)
//            .build());
      { // Blue Segments
//        saveL("PostEndTrade4Blue3b");
        loadL("PostEndTrade4Blue3b");
        seqL(new GetDiploma());
      }
      { // Red Segments
//        saveR("PostEndTrade4Red3b");
        loadR("PostEndTrade4Red3b");
        seqR(new GetDiploma());
      }
    }

  public static void main(String[] args) throws Exception {
    DualGbRunner.run(new BlueRomInfo(), new RedRomInfo(), new Coop());
  }
}
