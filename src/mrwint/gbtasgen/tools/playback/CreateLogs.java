package mrwint.gbtasgen.tools.playback;

import java.io.IOException;
import java.util.ArrayList;

import mrwint.gbtasgen.Gb;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.movie.BizhawkMovie;
import mrwint.gbtasgen.rom.NamedRomInfo;
import mrwint.gbtasgen.rom.pokemon.gen1.RedRomInfo;
import mrwint.gbtasgen.rom.pokemon.gen1.YellowRomInfo;
import mrwint.gbtasgen.rom.pokemon.gen2.CrystalRomInfo;
import mrwint.gbtasgen.rom.pokemon.gen2.SilverRomInfo;
import mrwint.gbtasgen.state.Gameboy;
import mrwint.gbtasgen.state.State;

public class CreateLogs {

  public static void main(String[] args) throws IOException, InterruptedException {
    // Initialize Gambatte with 1 screen.
    Gb.loadGambatte(1);

    // load rom
//    Gameboy gb = new Gameboy(new RedRomInfo(), 0, false);
//    Gameboy gb = new Gameboy(new CrystalRomInfo(), 0, false);
//    Gameboy gb = new Gameboy(new SilverRomInfo(), 0, false);
//    Gameboy gb = new Gameboy(new YellowRomInfo(), 0, true);
    Gameboy gb = new Gameboy(new NamedRomInfo("roms/yellow_hack.gbc"), 0, true);

    ArrayList<Integer> inputs = BizhawkMovie.getBkmInputs("playback_s01");
//    ArrayList<Integer> inputs = BizhawkMovie.getBkmInputs("MrWint_pokemonsilver_109868");
//    ArrayList<Integer> inputs = BizhawkMovie.getBk2Inputs("MrWint_pokemonred_sram_4178");
//    ArrayList<Integer> inputs = BizhawkMovie.getBk2Inputs("smbTest");
    System.out.println("playing back " + inputs.size() + " inputs");
    for (int i = 0; i < 5000; i++) {
      gb.step(inputs.size() > i ? inputs.get(i) : 0);
//      gb.step(0);
//      if (inputs.get(i) == Move.RESET)
//        gb.step(0);
//        i++;
      Thread.sleep(1);
    }

//    Thread.sleep(2000);
//    gb.steps(1000);
//    Thread.sleep(2000);
  }
}
