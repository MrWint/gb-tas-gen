package mrwint.gbtasgen.tools.playback.loganalyzer.state;

import mrwint.gbtasgen.tools.playback.loganalyzer.PaletteRegistry.PaletteEntry;
import mrwint.gbtasgen.tools.playback.loganalyzer.TileRegistry.TileEntry;

public class SpriteState {
  public final int y;
  public final int x;
  public final TileEntry upperTile;
  public final TileEntry lowerTile;
  public final boolean objToBgPriority;
  public final boolean flipVertically;
  public final boolean flipHorizontally;
  public final PaletteEntry palette;

  public SpriteState(int y, int x, TileEntry upperTile, TileEntry lowerTile, boolean objToBgPriority, boolean flipHorizontally, boolean flipVertically, PaletteEntry palette) {
    this.y = y;
    this.x = x;
    this.upperTile = upperTile;
    this.lowerTile = lowerTile;
    this.objToBgPriority = objToBgPriority;
    this.flipHorizontally = flipHorizontally;
    this.flipVertically = flipVertically;
    this.palette = palette;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (flipHorizontally ? 1231 : 1237);
    result = prime * result + (flipVertically ? 1231 : 1237);
    result = prime * result + (objToBgPriority ? 1231 : 1237);
    result = prime * result + ((palette == null) ? 0 : palette.hashCode());
    result = prime * result + ((upperTile == null) ? 0 : upperTile.hashCode());
    result = prime * result + ((lowerTile == null) ? 0 : lowerTile.hashCode());
    result = prime * result + x;
    result = prime * result + y;
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
    SpriteState other = (SpriteState) obj;
    if (flipHorizontally != other.flipHorizontally)
      return false;
    if (flipVertically != other.flipVertically)
      return false;
    if (objToBgPriority != other.objToBgPriority)
      return false;
    if (palette == null) {
      if (other.palette != null)
        return false;
    } else if (!palette.equals(other.palette))
      return false;
    if (upperTile == null) {
      if (other.upperTile != null)
        return false;
    } else if (!upperTile.equals(other.upperTile))
      return false;
    if (lowerTile == null) {
      if (other.lowerTile != null)
        return false;
    } else if (!lowerTile.equals(other.lowerTile))
      return false;
    if (x != other.x)
      return false;
    if (y != other.y)
      return false;
    return true;
  }
  
}