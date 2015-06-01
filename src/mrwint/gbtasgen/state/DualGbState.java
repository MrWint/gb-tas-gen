package mrwint.gbtasgen.state;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class DualGbState {

  public final Gameboy gbL;
  public final Gameboy gbR;

  public final StateBuffer stateBufferL;
  public final StateBuffer stateBufferR;

  public DualGbState(Gameboy gbL, Gameboy gbR, StateBuffer stateBufferL, StateBuffer stateBufferR) {
    this.gbL = gbL;
    this.gbR = gbR;
    this.stateBufferL = stateBufferL;
    this.stateBufferR = stateBufferR;
  }

  public void save(String filename) {
    try {
      String path = "saves/" + filename + "Dual" + gbL.rom.fileNameSuffix + gbR.rom.fileNameSuffix;
      ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
      stateBufferL.save(oos);
      stateBufferR.save(oos);
      oos.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static DualGbState load(Gameboy gbL, Gameboy gbR, String filename) {
    try {
      String path = "saves/" + filename + "Dual" + gbL.rom.fileNameSuffix + gbR.rom.fileNameSuffix;
      ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
      StateBuffer stateBufferL = StateBuffer.load(ois);
      StateBuffer stateBufferR = StateBuffer.load(ois);
      ois.close();
      return new DualGbState(gbL, gbR, stateBufferL, stateBufferR);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
}
