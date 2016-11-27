package mrwint.gbtasgen.tools.playback.loganalyzer.state;

import mrwint.gbtasgen.tools.playback.loganalyzer.PaletteRegistry.PaletteEntry;
import mrwint.gbtasgen.tools.playback.loganalyzer.TileRegistry.TileEntry;

public class BgMapState {
  public final TileEntry tile;
  public final boolean flipHorizontally;
  public final boolean flipVertically;
  public final boolean bgToOamPriority;
  public final PaletteEntry palette;

  public BgMapState(TileEntry tile, boolean flipHorizontally, boolean flipVertically, boolean bgToOamPriority, PaletteEntry palette) {
    this.tile = tile;
    this.flipHorizontally = flipHorizontally;
    this.flipVertically = flipVertically;
    this.bgToOamPriority = bgToOamPriority;
    this.palette = palette;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (bgToOamPriority ? 1231 : 1237);
    result = prime * result + (flipHorizontally ? 1231 : 1237);
    result = prime * result + (flipVertically ? 1231 : 1237);
    result = prime * result + ((palette == null) ? 0 : palette.hashCode());
    result = prime * result + ((tile == null) ? 0 : tile.hashCode());
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
    BgMapState other = (BgMapState) obj;
    if (bgToOamPriority != other.bgToOamPriority)
      return false;
    if (flipHorizontally != other.flipHorizontally)
      return false;
    if (flipVertically != other.flipVertically)
      return false;
    if (palette == null) {
      if (other.palette != null)
        return false;
    } else if (!palette.equals(other.palette))
      return false;
    if (tile == null) {
      if (other.tile != null)
        return false;
    } else if (!tile.equals(other.tile))
      return false;
    return true;
  }
}