package mrwint.gbtasgen.state;


public class SingleGbState {

  public final Gameboy gb;
  public final StateBuffer stateBuffer;

  public SingleGbState(Gameboy gb, StateBuffer stateBuffer) {
    this.gb = gb;
    this.stateBuffer = stateBuffer;
  }

  public void save(String filename) {
    stateBuffer.save(filename, gb.rom.fileNameSuffix);
  }

  public static SingleGbState load(Gameboy gb, String filename) {
    return new SingleGbState(gb, StateBuffer.load(filename, gb.rom.fileNameSuffix));
  }
}
