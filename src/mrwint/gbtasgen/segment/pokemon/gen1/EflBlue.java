package mrwint.gbtasgen.segment.pokemon.gen1;

import mrwint.gbtasgen.rom.pokemon.gen1.BlueRomInfo;
import mrwint.gbtasgen.segment.SingleGbSegment;
import mrwint.gbtasgen.segment.pokemon.gen1.eflblue.ChooseStarterSquirtle;
import mrwint.gbtasgen.segment.pokemon.gen1.eflblue.Intro;
import mrwint.gbtasgen.segment.pokemon.gen1.eflblue.OakSpeech;
import mrwint.gbtasgen.segment.pokemon.gen1.eflblue.OaksParcel;
import mrwint.gbtasgen.segment.pokemon.gen1.eflblue.PewterSquirtle;
import mrwint.gbtasgen.segment.pokemon.gen1.eflblue.Rival1FightSquirtle;
import mrwint.gbtasgen.segment.pokemon.gen1.eflblue.ViridianForestSquirtle;
import mrwint.gbtasgen.util.SingleGbRunner;

public class EflBlue extends SingleGbSegment {

  @Override
	protected void execute() {
//		seq(new Intro());
//    save("IntroEfl");
//
//    load("IntroEfl");
//    seq(new OakSpeech());
//    save("OakSpeechEfl");
//
//    load("OakSpeechEfl");
//    seq(new ChooseStarterSquirtle());
//    save("ChooseStarterSquirtleEfl");

//    load("ChooseStarterSquirtleEfl");
//    seq(new Rival1FightSquirtle());
//    save("Rival1FightSquirtleEfl");

//    load("Rival1FightSquirtleEfl");
//    seq(new OaksParcel());
//    save("OaksParcelEfl");

//    load("OaksParcelEfl");
//    seq(new ViridianForestSquirtle());
//    save("ViridianForestSquirtleEfl");

    load("ViridianForestSquirtleEfl");
    seq(new PewterSquirtle());
    save("PewterSquirtleEfl");
	}

	public static void main(String[] args) throws Exception {
    SingleGbRunner.run(new BlueRomInfo(), new EflBlue(), true);
	}
}
