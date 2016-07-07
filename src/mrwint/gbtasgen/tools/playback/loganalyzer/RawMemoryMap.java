package mrwint.gbtasgen.tools.playback.loganalyzer;

import java.util.Map.Entry;
import java.util.TreeMap;

public class RawMemoryMap {
  
  @SuppressWarnings("unchecked")
  private History<Integer>[][] vram = new History[2][0x2000];
  @SuppressWarnings("unchecked")
  private History<Integer>[] hram = new History[0x200];
  @SuppressWarnings("unchecked")
  private History<Integer>[] bgPalette = new History[0x40];
  @SuppressWarnings("unchecked")
  private History<Integer>[] objPalette = new History[0x40];

  public RawMemoryMap(TreeMap<TimeStamp, LogInput> log) {
    int vramBank = 0;
    int bgPaletteIndex = 0;
    int objPaletteIndex = 0;

    for (Entry<TimeStamp, LogInput> e : log.entrySet()) {
      LogInput input = e.getValue();
      TimeStamp time = e.getKey();
      if (input.address >= 0x8000 && input.address < 0xa000) {
        if (vram[vramBank][input.address - 0x8000] == null)
          vram[vramBank][input.address - 0x8000] = new History<>();
        vram[vramBank][input.address - 0x8000].add(time, input.value);
      } else if (input.address == 0xff68) {
        bgPaletteIndex = input.value;
      } else if (input.address == 0xff69) {
        if (bgPalette[bgPaletteIndex & 0x3f] == null)
          bgPalette[bgPaletteIndex & 0x3f] = new History<>();
        bgPalette[bgPaletteIndex & 0x3f].add(time, input.value);
        if (bgPaletteIndex >= 0x80)
          bgPaletteIndex++;
      } else if (input.address == 0xff6a) {
        objPaletteIndex = input.value;
      } else if (input.address == 0xff6b) {
        if (objPalette[objPaletteIndex & 0x3f] == null)
          objPalette[objPaletteIndex & 0x3f] = new History<>();
        objPalette[objPaletteIndex & 0x3f].add(time, input.value);
        if (objPaletteIndex >= 0x80)
          objPaletteIndex++;
      } else if (input.address >= 0xfe00 && input.address < 0x10000) {
        if (hram[input.address - 0xfe00] == null)
          hram[input.address - 0xfe00] = new History<>();
        hram[input.address - 0xfe00].add(time, input.value);
        if (input.address == 0xff4f)
          vramBank = input.value & 1;
      } else
        System.out.println("RawMemoryMap: unknown address " + input.address);
    }
  }

  public int getHramValue(int address, TimeStamp time, int defaultValue) {
    if (address >= 0xfe00 && address < 0x10000) {
      return hram[address - 0xfe00].getValueAt(time, defaultValue);
    } else
      throw new RuntimeException("Address " + address + " not in HRAM");
  }

  public int getVramValue(int vramBank, int address, TimeStamp time, int defaultValue) {
    if (address >= 0x8000 && address < 0xa000) {
      return vram[vramBank][address - 0x8000].getValueAt(time, defaultValue);
    } else
      throw new RuntimeException("Address " + address + " not in VRAM");
  }

  public int getBgPaletteValue(int address, TimeStamp time) {
    if (address >= 0 && address < 0x40) {
      return bgPalette[address].getValueAt(time, (address & 0x1) == 0 ? 0xff : 0x7f);
    } else
      throw new RuntimeException("Address " + address + " not in BG palette");
  }

  public int getObjPaletteValue(int address, TimeStamp time) {
    if (address >= 0 && address < 0x40) {
      return objPalette[address].getValueAt(time, (address & 0x1) == 0 ? 0xff : 0x7f);
    } else
      throw new RuntimeException("Address " + address + " not in OBJ palette");
  }
}
