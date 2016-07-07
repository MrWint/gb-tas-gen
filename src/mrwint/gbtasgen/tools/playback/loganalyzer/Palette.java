package mrwint.gbtasgen.tools.playback.loganalyzer;

import java.util.Arrays;

public class Palette {
  private final byte[] data;
  
  public Palette(byte[] data) {
    this.data = data;
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
    Palette other = (Palette) obj;
    if (!Arrays.equals(data, other.data))
      return false;
    return true;
  }
}
