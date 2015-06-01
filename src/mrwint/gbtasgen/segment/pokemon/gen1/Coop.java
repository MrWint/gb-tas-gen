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
import mrwint.gbtasgen.segment.pokemon.gen1.coop.ChooseStarterBlue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.ChooseStarterRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.Intro;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.OakSpeechBlue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.OakSpeechRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.OaksParcel;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.PewterBlue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.PewterRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.PostTradeTest;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.PrepareTradeTest;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.Rival1FightBlue;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.Rival1FightRed;
import mrwint.gbtasgen.segment.pokemon.gen1.coop.Route3Red;
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
      loadL("ViridianForestBlue");
      seqL(new PewterBlue());
      saveL("PewterBlue");
      loadL("PewterBlue");
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
      loadR("Route3Red");
    }
//    seqL(new Rival1FightBlue());
//    save("Rival1FightBlueL");
//    load("Rival1FightBlueL");
//    seqR(new Rival1FightRed());
//    save("Rival1FightBlueLR");
//    load("Rival1FightBlueLR");
//    seqL(new OaksParcel());
//    save("OaksParcelL");
//    load("OaksParcelL");
//    seqR(new OaksParcel());
//    save("OaksParcelLR");
//    load("OaksParcelLR");
//    seqL(new PrepareTradeTest());
//    seqR(new PrepareTradeTest());
//    save("PrepareTradeTestLR");
//    load("PrepareTradeTestLR");
//    seqDual(
//        new DualGbHelper.InputBuilder()
//            .withButton(A, 15) // talk to cable club
//            .withButton(B, 123) // welcome ; please apply
//            .withButton(B, 53) // before, we have to
//            .withButton(A, 29) // save the game
//            .withButton(0, 133) // save, where do you want to go (trade center by R)
//            .withButton(LEFT, 196) // just a moment; face table
//            .withButton(A, 3) // use table
//            .withButton(A, 301) // select squirtle
//            .withButton(RIGHT, 2) // select trade
//            .withButton(A, 2) // select trade
//            .withButton(B, 200) // x and y will
//            .withButton(A, 22) // be traded
//            .reset(2986)
////            .withButton(DOWN, 2986) // trade ; cancel
////            .withButton(A, 0) // cancel
////            .withButton(START, 123) // cancel
////            .withButton(UP, 22) // exit
////            .withButton(A, 1) // exit
////            .lookAndSee()
//            .build(),
//        new DualGbHelper.InputBuilder()
//            .withButton(A, 15) // talk to cable club
//            .withButton(B, 123) // welcome ; please apply
//            .withButton(B, 53) // before, we have to
//            .withButton(A, 29) // save the game
//            .withButton(A, 133) // save, where do you want to go (trade center)
//            .withButton(RIGHT, 194) // just a moment; face table
//            .withButton(A, 3) // use table
//            .withButton(A, 298) // select charmander
//            .withButton(RIGHT, 2) // select trade
//            .withButton(A, 2) // select trade
//            .withButton(B, 208) // x and y will
//            .withButton(A, 22) // be traded
//            .reset(2979)
////            .withButton(DOWN, 2979) // trade ; cancel
////            .withButton(A, 0) // cancel
////            .withButton(START, 122) // cancel
////            .withButton(UP, 22) // exit
////            .withButton(A, 2) // exit
////            .lookAndSee()
//            .build());
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
