package mrwint.gbtasgen.tools.playback.loganalyzer;

import java.util.Map.Entry;
import java.util.TreeMap;

public class RawMemoryMap {
  private static class RawMemoryHistory {
    private TreeMap<TimeStamp, Integer> operations = new TreeMap<>();
  
    public void add(TimeStamp time, Integer value) {
      // Ignore duplicate values (assuming values are added in chronological order).
      Entry<TimeStamp, Integer> lowerBound = operations.floorEntry(time);
      if (lowerBound != null && value.equals(lowerBound.getValue()))
        return;
  
      operations.put(time, value);
    }
    
    public int getValueAt(TimeStamp time, int defaultValue) {
      Entry<TimeStamp, Integer> lowerBound = operations.floorEntry(time);
      return lowerBound == null ? defaultValue : lowerBound.getValue();
    }
  }

  private RawMemoryHistory[][] vram = new RawMemoryHistory[2][0x2000];
  private RawMemoryHistory[] hram = new RawMemoryHistory[0x200];
  private RawMemoryHistory[] bgPalette = new RawMemoryHistory[0x40];
  private RawMemoryHistory[] objPalette = new RawMemoryHistory[0x40];

  public RawMemoryMap(TreeMap<TimeStamp, LogInput> log) {
    int vramBank = 0;
    int bgPaletteIndex = 0;
    int objPaletteIndex = 0;

    for (Entry<TimeStamp, LogInput> e : log.entrySet()) {
      LogInput input = e.getValue();
      TimeStamp time = e.getKey();
      if (input.address >= 0x8000 && input.address < 0xa000) {
        if (vram[vramBank][input.address - 0x8000] == null)
          vram[vramBank][input.address - 0x8000] = new RawMemoryHistory();
        vram[vramBank][input.address - 0x8000].add(time, input.value);
      } else if (input.address == 0xff68) {
        bgPaletteIndex = input.value;
      } else if (input.address == 0xff69) {
        if (bgPalette[bgPaletteIndex & 0x3f] == null)
          bgPalette[bgPaletteIndex & 0x3f] = new RawMemoryHistory();
        bgPalette[bgPaletteIndex & 0x3f].add(time, input.value);
        if (bgPaletteIndex >= 0x80)
          bgPaletteIndex = (bgPaletteIndex + 1) | 0x80;
      } else if (input.address == 0xff6a) {
        objPaletteIndex = input.value;
      } else if (input.address == 0xff6b) {
        if (objPalette[objPaletteIndex & 0x3f] == null)
          objPalette[objPaletteIndex & 0x3f] = new RawMemoryHistory();
        objPalette[objPaletteIndex & 0x3f].add(time, input.value);
        if (objPaletteIndex >= 0x80)
          objPaletteIndex = (objPaletteIndex + 1) | 0x80;
      } else if (input.address >= 0xfe00 && input.address < 0x10000) {
        if (hram[input.address - 0xfe00] == null)
          hram[input.address - 0xfe00] = new RawMemoryHistory();
        hram[input.address - 0xfe00].add(time, input.value);
        if (input.address == 0xff4f)
          vramBank = input.value & 1;
      } else
        System.out.println("RawMemoryMap: unknown address " + input.address);
    }
  }

  public int getLCDC(TimeStamp time) {
    return getHramValue(GbConstants.LCDC, time, GbConstants.LCDC_DEFAULT);
  }
  public int getWY(TimeStamp time) {
    return getHramValue(GbConstants.WY, time, GbConstants.WY_DEFAULT);
  }
  public int getWX(TimeStamp time) {
    return getHramValue(GbConstants.WX, time, GbConstants.WX_DEFAULT);
  }
  public int getSCY(TimeStamp time) {
    return getHramValue(GbConstants.SCY, time, GbConstants.SCY_DEFAULT);
  }
  public int getSCX(TimeStamp time) {
    return getHramValue(GbConstants.SCX, time, GbConstants.SCX_DEFAULT);
  }
  private int getHramValue(int address, TimeStamp time, int defaultValue) {
    if (hram[address - 0xfe00] == null)
      return defaultValue;
    return hram[address - 0xfe00].getValueAt(time, defaultValue);
  }

  public int getVramValue(int vramBank, int address, TimeStamp time) {
    if (address >= 0x8000 && address < 0xa000) {
      if (vram[vramBank][address - 0x8000] == null)
        return GbConstants.VRAM_DEFAULT;
      return vram[vramBank][address - 0x8000].getValueAt(time, GbConstants.VRAM_DEFAULT);
    } else
      throw new RuntimeException("Address " + address + " not in VRAM");
  }
  public int getBgPaletteValue(int address, TimeStamp time) {
    if (address >= 0 && address < 0x40) {
      if (bgPalette[address] == null)
        return (address & 0x1) == 0 ? 0xff : 0x7f;
      return bgPalette[address].getValueAt(time, (address & 0x1) == 0 ? 0xff : 0x7f);
    } else
      throw new RuntimeException("Address " + address + " not in BG palette");
  }
  public int getObjPaletteValue(int address, TimeStamp time) {
    if (address >= 0 && address < 0x40) {
      if (objPalette[address] == null)
        return (address & 0x1) == 0 ? 0xff : 0x7f;
      return objPalette[address].getValueAt(time, (address & 0x1) == 0 ? 0xff : 0x7f);
    } else
      throw new RuntimeException("Address " + address + " not in OBJ palette");
  }

  public int getOAM(int address, TimeStamp time) {
    return getHramValue(address, time, GbConstants.OAM_DEFAULT);
  }
}
