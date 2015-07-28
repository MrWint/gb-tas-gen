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
import mrwint.gbtasgen.segment.pokemon.gen1.coop.ChooseSecondStarterRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.ChooseStarterBlue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.ChooseStarterRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.Intro;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.MtMoonBlue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.MtMoonRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.NuggetBridgeBlue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.NuggetBridgeRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.OakSpeechBlue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.OakSpeechRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.OaksParcel;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.PewterBlue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.PewterRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.PostTradeTest;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.PrepareEeveeTradeRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.PrepareTradeTest;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.ReIntroRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.Rival1FightAgainRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.Rival1FightBlue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.Rival1FightRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.PrepareEeveeTradeBlue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.RockTunnel2Blue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.RockTunnelRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.Route3Blue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.Route3Red;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.SurgeBlue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.SurgeRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.ViridianForestBlue;
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
//      saveR("OakSpeechRed");
//      loadR("OakSpeechRed");
//      seqR(new ChooseStarterRed());
//      saveR("ChooseStarterRed");
//      loadR("ChooseStarterRed");
//      seqR(new Rival1FightRed());
//      saveR("Rival1FightRed");
//      loadR("Rival1FightRed");
//      seqR(new OaksParcel());
//      saveR("OaksParcel");
//      loadR("OaksParcel");
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
//      seqR(new NuggetBridgeRed());
//      saveR("NuggetBridgeRed");
//      loadR("NuggetBridgeRed");
//      seqR(new SurgeRed());
//      saveR("SurgeRed");
//      loadR("SurgeRed");
//      seqR(new RockTunnelRed());
//      saveR("RockTunnelRed");
//      loadR("RockTunnelRed");
//      seqR(new PrepareEeveeTradeRed());
//      saveR("PrepareEeveeTradeRed");
//      loadR("PrepareEeveeTradeRed");
    }
//    seqDualInputs(
//        new DualGbHelper.InputBuilder()
//            .withButton(A, 0) // talk to cable club
//            .withButton(B, 43) // welcome ; please apply
//            .withButton(B, 31) // before, we have to
//            .withButton(A, 17) // save the game
//            .withButton(A, 39) // save, where do you want to go (trade center)
//            .withButton(LEFT, 30) // just a moment; face table
//            .withButton(A, 1) // use table
//            .withButton(DOWN, 15) // just a moment; select Butterfree
//            .withButton(DOWN, 1) // select Butterfree
//            .withButton(DOWN, 1) // select Butterfree
//            .withButton(DOWN | A, 0) // select Butterfree
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 29) // x and y will
//            .withButton(A, 10) // be traded
//            .withButton(DOWN, 0) // select beedrill
//            .withButton(DOWN, 1) // select beedrill
//            .withButton(DOWN, 1) // select beedrill
//            .withButton(DOWN | A, 0) // select beedrill
//            .withButton(RIGHT, 0) // select trade
//            .withButton(A, 0) // select trade
//            .withButton(B, 22) // x and y will
//            .withButton(A, 11) // be traded
//            .reset(0)
////            .lookAndSee()
//            .build(),
//        new DualGbHelper.InputBuilder()
//            .withButton(A, 0) // talk to cable club
//            .withButton(B, 45) // welcome ; please apply
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
//            .withButton(B, 22) // x and y will
//            .withButton(A, 10) // be traded
//            .reset(0)
////            .lookAndSee()
//            .build());
    { // Blue Segments
//      saveL("PostEeveeTradeBlue");
      loadL("PostEeveeTradeBlue");
      seqL(new RockTunnel2Blue());
      saveL("RockTunnel2Blue");
      loadL("RockTunnel2Blue");
    }
    { // Red Segments
//      saveR("PostEeveeTradeRed");
//      loadR("PostEeveeTradeRed");
//      seqR(new ReIntroRed());
//      seqR(new OakSpeechRed());
//      saveR("OakSpeechRed2");
//      loadR("OakSpeechRed2");
//      seqR(new ChooseSecondStarterRed());
//      saveR("ChooseSecondStarterRed");
//      loadR("ChooseSecondStarterRed");
//      seqR(new Rival1FightAgainRed());
//      saveR("Rival1FightAgainRed");
//      loadR("Rival1FightAgainRed");
//      seqR(new OaksParcel());
//      saveR("OaksParcel2");
      loadR("OaksParcel2");

    }
//    save("Traded");
//    load("Traded");
//    seqL(new PostTradeTest());
//    save("PostTradeTestL");
//    seqR(new PostTradeTest());
//    save("PostTradeTestLR");
	}

	public static void main(String[] args) throws Exception {
    DualGbRunner.run(new BlueRomInfo(), new RedRomInfo(), new Coop());
	}
}
