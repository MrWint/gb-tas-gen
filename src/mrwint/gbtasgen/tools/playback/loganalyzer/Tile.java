package mrwint.gbtasgen.tools.playback.loganalyzer;

import java.util.Arrays;

public class Tile {
  private final byte[] data;
  
  public Tile(byte[] data) {
    this.data = data;
  }
  
  public Tile flipVertically() {
    byte[] data = new byte[16];
    for (int i = 0; i < 16; i ++) {
      data[i] = this.data[(7-i/2)*2 + (i&1)];
    }
    return new Tile(data);
  }
  
  public Tile flipHorizontally() {
    byte[] data = new byte[16];
    for (int i = 0; i < 16; i ++) {
      data[i] = (byte)Integer.reverseBytes(Integer.reverse(Byte.toUnsignedInt(this.data[i])));
    }
    return new Tile(data);
  }
  
  public int get(int index) {
    return Byte.toUnsignedInt(data[index]);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + Arrays.hashCode(data);
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Tile other = (Tile) obj;
    if (!Arrays.equals(data, other.data))
      return false;
    return true;
  }
}
