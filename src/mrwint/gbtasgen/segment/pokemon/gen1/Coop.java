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
import mrwint.gbtasgen.segment.pokemon.gen1.coop.CeruleanRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.ChooseSecondStarterRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.ChooseStarterBlue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.ChooseStarterDummyRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.CinnabarBlue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.CyclingRoadRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.Intro;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.MtMoonBlue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.MtMoonDummyRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.MtMoonRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.NuggetBridgeBlue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.NuggetBridgeDummyRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.OakSpeechBlue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.OakSpeechRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.OaksParcel;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.PewterBlue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.PewterDummyRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.PewterRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.PostTradeTest;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.PreRockTunnelBlue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.PrepareBulbasaurTradeRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.PrepareEeveeTradeDummyRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.PrepareTradeTest;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.ReIntroRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.Rival1FightAgainRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.Rival1FightBlue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.Rival1FightDummyRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.PrepareEeveeTradeBlue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.RockTunnelBlue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.RockTunnelDummyRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.RockTunnelRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.RocketHideoutBlue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.Route3Blue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.Route3DummyRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.Route3Red;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.PrepareSquirtleTradeBlue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.SafariBlue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.SilphCoBlue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.SurgeBlue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.SurgeDummyRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.SurgeRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.TowerBlue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.PrepareSquirtleTradeRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.TowerRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.VictoryRoadBlue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.ViridianForestBlue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.ViridianForestDummyRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.ViridianForestRed;
import mrwint.gbtasgen.state.DualGbHelper;
import mrwint.gbtasgen.util.DualGbRunner;

public class Coop extends DualGbSegment {

  @Override
	protected void execute() {
    { // Blue Segments
//      seqL(new Intro());
//      seqL(new OakSpeechBlue());
//      saveL("OakSpeechBlue");
//      loadL("OakSpeechBlue");
//      seqL(new ChooseStarterBlue());
//      saveL("ChooseStarterBlue");
//      loadL("ChooseStarterBlue");
//      seqL(new Rival1FightBlue());
//      saveL("Rival1FightBlue");
//      loadL("Rival1FightBlue");
//      seqL(new OaksParcel());
//      saveL("OaksParcel");
//      loadL("OaksParcel");
//      seqL(new ViridianForestBlue());
//      saveL("ViridianForestBlue");
//      loadL("ViridianForestBlue");
//      seqL(new PewterBlue());
//      saveL("PewterBlue");
//      loadL("PewterBlue");
//      seqL(new Route3Blue());
//      saveL("Route3Blue");
//      loadL("Route3Blue");
//      seqL(new MtMoonBlue());
//      saveL("MtMoonBlue");
//      loadL("MtMoonBlue");
//      seqL(new NuggetBridgeBlue());
//      saveL("NuggetBridgeBlue");
//      loadL("NuggetBridgeBlue");
//      seqL(new SurgeBlue());
//      saveL("SurgeBlue");
//      loadL("SurgeBlue");
//      seqL(new PrepareEeveeTradeBlue());
//      saveL("PrepareEeveeTradeBlue");
//      loadL("PrepareEeveeTradeBlue");
    }
    { // Red Segments
//      seqR(new Intro());
//      seqR(new OakSpeechRed());
//      saveR("OakSpeechDummyRed");
//      loadR("OakSpeechDummyRed");
//      seqR(new ChooseStarterDummyRed());
//      saveR("ChooseStarterDummyRed");
//      loadR("ChooseStarterDummyRed");
//      seqR(new Rival1FightDummyRed());
//      saveR("Rival1FightDummyRed");
//      loadR("Rival1FightDummyRed");
//      seqR(new OaksParcel());
//      saveR("OaksParcelDummyRed");
//      loadR("OaksParcelDummyRed");
//      seqR(new ViridianForestDummyRed());
//      saveR("ViridianForestDummyRed");
//      loadR("ViridianForestDummyRed");
//      seqR(new PewterDummyRed());
//      saveR("PewterDummyRed");
//      loadR("PewterDummyRed");
//      seqR(new Route3DummyRed());
//      saveR("Route3DummyRed");
//      loadR("Route3DummyRed");
//      seqR(new MtMoonDummyRed());
//      saveR("MtMoonDummyRed");
//      loadR("MtMoonDummyRed");
//      seqR(new NuggetBridgeDummyRed());
//      saveR("NuggetBridgeDummyRed");
//      loadR("NuggetBridgeDummyRed");
//      seqR(new SurgeDummyRed());
//      saveR("SurgeDummyRed");
//      loadR("SurgeDummyRed");
//      seqR(new RockTunnelDummyRed());
//      saveR("RockTunnelDummyRed");
//      loadR("RockTunnelDummyRed");
//      seqR(new PrepareEeveeTradeDummyRed());
//      saveR("PrepareEeveeTradeDummyRed");
//      loadR("PrepareEeveeTradeDummyRed");
    }
//    seqDualInputs(
//        new DualGbHelper.InputBuilder()
//            .withButton(A, 0) // talk to cable club
//            .withButton(B, 44) // welcome ; please apply
//            .withButton(B, 31) // before, we have to
//            .withButton(A, 18) // save the game
//            .withButton(A, 39) // save, where do you want to go (trade center)
//            .withButton(LEFT, 31) // just a moment; face table
//            .withButton(A, 1) // use table
//            .withButton(DOWN | A, 15) // just a moment; select Jigglypuff
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 30) // x and y will
//            .withButton(A, 10) // be traded
//            .withButton(DOWN | A, 1) // select Clefable
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 22) // x and y will
//            .withButton(A, 11) // be traded
//            .reset(0)
////            .lookAndSee()
//            .build(),
//        new DualGbHelper.InputBuilder()
//            .withButton(A, 0) // talk to cable club
//            .withButton(B, 44) // welcome ; please apply
//            .withButton(B, 32) // before, we have to
//            .withButton(A, 17) // save the game
//            .withButton(0, 40) // save, where do you want to go (trade center by L)
//            .withButton(RIGHT, 30) // just a moment; face table
//            .withButton(A, 1) // use table
//            .withButton(A, 15) // just a moment; select charmander
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 29) // x and y will
//            .withButton(A, 10) // be traded
//            .withButton(A, 1) // select eevee
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 23) // x and y will
//            .withButton(A, 10) // be traded
//            .reset(0)
////            .lookAndSee()
//            .build());
    { // Blue Segments
//      saveL("PostEeveeTradeBlue1");
//      loadL("PostEeveeTradeBlue1");
//      seqL(new PreRockTunnelBlue());
//      saveL("PreRockTunnelBlue");
//      loadL("PreRockTunnelBlue");
    }
    { // Red Segments
//      saveR("PostEeveeTradeRed1");
//      loadR("PostEeveeTradeRed1");
//      seqR(new ReIntroRed());
//      seqR(new OakSpeechRed());
//      saveR("OakSpeechRed");
//      loadR("OakSpeechRed");
//      seqR(new ChooseSecondStarterRed());
//      saveR("ChooseSecondStarterRed");
//      loadR("ChooseSecondStarterRed");
//      seqR(new Rival1FightAgainRed());
//      saveR("Rival1FightAgainRed");
//      loadR("Rival1FightAgainRed");
//      seqR(new OaksParcel());
//      saveR("OaksParcel");
//      loadR("OaksParcel");
//      seqR(new PrepareBulbasaurTradeRed());
//      saveR("PrepareBulbasaurTradeRed");
//      loadR("PrepareBulbasaurTradeRed");
    }
//    seqDualInputs(
//        new DualGbHelper.InputBuilder()
//            .withButton(A, 0) // talk to cable club
//            .withButton(B, 45) // welcome ; please apply
//            .withButton(B, 32) // before, we have to
//            .withButton(A, 18) // save the game
//            .withButton(A, 40) // save, where do you want to go (trade center)
//            .withButton(LEFT, 31) // just a moment; face table
//            .withButton(A, 1) // use table
//            .withButton(DOWN, 15) // just a moment; select Charmander
//            .withButton(DOWN, 1) // just a moment; select Charmander
//            .withButton(DOWN, 1) // just a moment; select Charmander
//            .withButton(DOWN | A, 1) // just a moment; select Charmander
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 29) // x and y will
//            .withButton(A, 10) // be traded
//            .reset(0)
////            .lookAndSee()
//            .build(),
//        new DualGbHelper.InputBuilder()
//            .withButton(A, 0) // talk to cable club
//            .withButton(B, 44) // welcome ; please apply
//            .withButton(B, 32) // before, we have to
//            .withButton(A, 18) // save the game
//            .withButton(0, 40) // save, where do you want to go (trade center by L)
//            .withButton(RIGHT, 31) // just a moment; face table
//            .withButton(A, 1) // use table
//            .withButton(A, 15) // just a moment; select Bulbasaur
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 29) // x and y will
//            .withButton(A, 10) // be traded
//            .reset(0)
////            .lookAndSee()
//            .build());
    { // Blue Segments
//      saveL("PostBulbasaurTradeBlue");
//      loadL("PostBulbasaurTradeBlue");
//      seqL(new RockTunnelBlue());
//      saveL("RockTunnelBlue1");
//      loadL("RockTunnelBlue1");
//      seqL(new CeladonBlue());
//      saveL("CeladonBlue1");
//      loadL("CeladonBlue1");
    }
    { // Red Segments
//      saveR("PostBulbasaurTradeRed");
//      loadR("PostBulbasaurTradeRed");
//      seqR(new ViridianForestRed());
//      saveR("ViridianForestRed");
//      loadR("ViridianForestRed");
//      seqR(new PewterRed());
//      saveR("PewterRed");
//      loadR("PewterRed");
//      seqR(new Route3Red());
//      saveR("Route3Red");
//      loadR("Route3Red");
//      seqR(new MtMoonRed());
//      saveR("MtMoonRed");
//      loadR("MtMoonRed");
//      seqR(new CeruleanRed());
//      saveR("CeruleanRed");
//      loadR("CeruleanRed");
    }
//    seqDualInputs(
//        new DualGbHelper.InputBuilder()
//            .withButton(A, 0) // talk to cable club
//            .withButton(B, 45) // welcome ; please apply
//            .withButton(B, 32) // before, we have to
//            .withButton(A, 18) // save the game
//            .withButton(A, 40) // save, where do you want to go (trade center)
//            .withButton(LEFT, 31) // just a moment; face table
//            .withButton(A, 1) // use table
//            .withButton(DOWN | A, 15) // just a moment; select Farfetch'd
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 29) // x and y will
//            .withButton(A, 10) // be traded
//            .withButton(DOWN, 1) // just a moment; select Meowth
//            .withButton(DOWN, 1) // just a moment; select Meowth
//            .withButton(DOWN | A, 1) // just a moment; select Meowth
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 29) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(DOWN, 1) // just a moment; select Abra
//            .withButton(DOWN, 1) // just a moment; select Abra
//            .withButton(DOWN | A, 1) // just a moment; select Abra
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
//            .withButton(B, 32) // before, we have to
//            .withButton(A, 18) // save the game
//            .withButton(0, 40) // save, where do you want to go (trade center by L)
//            .withButton(RIGHT, 36) // just a moment; face table
//            .withButton(A, 1) // use table
//            .withButton(DOWN, 15) // just a moment; select Pidgey
//            .withButton(DOWN | A, 1) // just a moment; select Pidgey
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 29) // x and y will
//            .withButton(A, 10) // be traded
//            .withButton(DOWN, 1) // just a moment; select Rattata
//            .withButton(DOWN, 1) // just a moment; select Rattata
//            .withButton(DOWN | A, 1) // just a moment; select Rattata
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 29) // x and y will
//            .withButton(A, 10) // be traded
//            .withButton(DOWN, 1) // just a moment; select Ekans
//            .withButton(DOWN, 1) // just a moment; select Ekans
//            .withButton(DOWN | A, 1) // just a moment; select Ekans
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 29) // x and y will
//            .withButton(A, 10) // be traded
//            .reset(0)
////            .lookAndSee()
//            .build());
    { // Blue Segments
//      saveL("PostAbraTradeBlue1");
//      loadL("PostAbraTradeBlue1");
//      seqL(new RocketHideoutBlue());
//      saveL("RocketHideoutBlue");
//      loadL("RocketHideoutBlue");
//      seqL(new TowerBlue());
//      saveL("TowerBlue");
//      loadL("TowerBlue");
//      seqL(new PrepareSquirtleTradeBlue());
//      saveL("PrepareSquirtleTradeBlue");
      loadL("PrepareSquirtleTradeBlue");
    }
    { // Red Segments
//      saveR("PostAbraTradeRed1");
//      loadR("PostAbraTradeRed1");
//      seqR(new SurgeRed());
//      saveR("SurgeRed");
//      loadR("SurgeRed");
//      seqR(new RockTunnelRed());
//      saveR("RockTunnelRed");
//      loadR("RockTunnelRed");
//      seqR(new PrepareSquirtleTradeRed());
//      saveR("PrepareSquirtleTradeRed");
      loadR("PrepareSquirtleTradeRed");
    }
//    seqDualInputs(
//        new DualGbHelper.InputBuilder()
//            .withButton(A, 0) // talk to cable club
//            .withButton(B, 45) // welcome ; please apply
//            .withButton(B, 32) // before, we have to
//            .withButton(A, 18) // save the game
//            .withButton(A, 40) // save, where do you want to go (trade center)
//            .withButton(LEFT, 31) // just a moment; face table
//            .withButton(A, 1) // use table
//            .withButton(DOWN | A, 15) // just a moment; select Squirtle
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 29) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(DOWN | A, 1) // just a moment; select Cubone
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 29) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(DOWN | A, 1) // just a moment; select Gastly
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 29) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(DOWN | A, 1) // just a moment; select Bellsprout
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 29) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(DOWN | A, 1) // just a moment; select Vulpix
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 29) // x and y will
//            .withButton(A, 11) // be traded
//            .reset(0)
////            .lookAndSee()
//            .build(),
//        new DualGbHelper.InputBuilder()
//            .withButton(A, 0) // talk to cable club
//            .withButton(B, 45) // welcome ; please apply
//            .withButton(B, 32) // before, we have to
//            .withButton(A, 18) // save the game
//            .withButton(0, 40) // save, where do you want to go (trade center by L)
//            .withButton(RIGHT, 36) // just a moment; face table
//            .withButton(A, 1) // use table
//            .withButton(A, 15) // just a moment; select Mr. Mime
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 29) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(A, 1) // just a moment; select Growlithe
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 29) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(A, 1) // just a moment; select Flareon
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 29) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(A, 2) // just a moment; select Mankey
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 29) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(A, 1) // just a moment; select Oddish
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 29) // x and y will
//            .withButton(A, 11) // be traded
//            .reset(0)
//            .build());
    { // Blue Segments
//      saveL("PostSquirtleTradeBlue");
//      loadL("PostSquirtleTradeBlue");
//      seqL(new SafariBlue());
//      saveL("SafariBlue");
//      loadL("SafariBlue");
//      seqL(new CinnabarBlue());
//      saveL("CinnabarBlue");
//      loadL("CinnabarBlue");
    }
    { // Red Segments
//      saveR("PostSquirtleTradeRed");
//      loadR("PostSquirtleTradeRed");
//      seqR(new TowerRed());
//      saveR("TowerRed");
//      loadR("TowerRed");
//      seqR(new CyclingRoadRed());
//      saveR("CyclingRoadRed");
//      loadR("CyclingRoadRed");
    }
//    seqDualInputs(
//        new DualGbHelper.InputBuilder()
//            .withButton(A, 0) // talk to cable club
//            .withButton(B, 45) // welcome ; please apply
//            .withButton(B, 32) // before, we have to
//            .withButton(A, 18) // save the game
//            .withButton(A, 40) // save, where do you want to go (trade center)
//            .withButton(LEFT, 31) // just a moment; face table
//            .withButton(A, 1) // use table
//            .withButton(DOWN | A, 15) // just a moment; select Magmar
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 29) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(DOWN | A, 1) // just a moment; select Omanyte
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 29) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(DOWN, 1) // just a moment; select Pinsir
//            .withButton(DOWN, 1) // just a moment; select Pinsir
//            .withButton(DOWN | A, 1) // just a moment; select Pinsir
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 29) // x and y will
//            .withButton(A, 11) // be traded
//            .reset(0)
//      //      .lookAndSee()
//            .build(),
//        new DualGbHelper.InputBuilder()
//            .withButton(A, 0) // talk to cable club
//            .withButton(B, 45) // welcome ; please apply
//            .withButton(B, 32) // before, we have to
//            .withButton(A, 18) // save the game
//            .withButton(0, 40) // save, where do you want to go (trade center by L)
//            .withButton(RIGHT, 36) // just a moment; face table
//            .withButton(A, 1) // use table
//            .withButton(A, 15) // just a moment; select Charmeleon
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 29) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(DOWN, 1) // just a moment; select Persian
//            .withButton(DOWN, 1) // just a moment; select Persian
//            .withButton(DOWN | A, 1) // just a moment; select Persian
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 29) // x and y will
//            .withButton(A, 11) // be traded
//            .withButton(DOWN, 1) // just a moment; select Jynx
//            .withButton(DOWN, 1) // just a moment; select Jynx
//            .withButton(DOWN | A, 1) // just a moment; select Jynx
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 29) // x and y will
//            .withButton(A, 11) // be traded
//            .reset(0)
//            .build());
    { // Blue Segments
//      saveL("PostOmanyteTradeBlue");
//      loadL("PostOmanyteTradeBlue");
//      seqL(new SilphCoBlue());
//      saveL("SilphCoBlue");
      loadL("SilphCoBlue");
      seqL(new VictoryRoadBlue());
      saveL("VictoryRoadBlue");
      loadL("VictoryRoadBlue");
    }
    { // Red Segments
//      saveR("PostOmanyteTradeRed");
      loadR("PostOmanyteTradeRed");
    }
	}

  public static void main(String[] args) throws Exception {
    DualGbRunner.run(new BlueRomInfo(), new RedRomInfo(), new Coop());
  }
}
