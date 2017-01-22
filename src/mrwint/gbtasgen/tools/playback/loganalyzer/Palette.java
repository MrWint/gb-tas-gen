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
  
  @Override
  public String toString() {
    return "[Palette " + toColorString(data[0], data[1])
        + ", " + toColorString(data[2], data[3])
        + ", " + toColorString(data[4], data[5])
        + ", " + toColorString(data[6], data[7]) + "]";
  }
  private String toColorString(byte lower, byte higher) {
    int gbColor = (higher << 8) + lower;
    int r = gbColor       & 0x1F;
    int g = gbColor >>  5 & 0x1F;
    int b = gbColor >> 10 & 0x1F;
    return "(" + r + "," + g + "," + b + ")";
  }
}
