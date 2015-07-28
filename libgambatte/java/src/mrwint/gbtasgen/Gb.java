package mrwint.gbtasgen;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Gb {

  private final long gb;
  public Gb(int screen, boolean equalLengthFrames) {
    gb = createGb(screen, equalLengthFrames);
  }
  
  private static native void initSdl(int numScreens);
  
  private static native long createGb(int screen, boolean equalLengthFrames);
  
  private static native void startEmulator(long gb, String rom);
  public void startEmulator(String rom) {
    startEmulator(gb, rom);
  }
  private static native void ninitDualGb(long gbL, long gbR);
  public static void initDualGb(Gb gbL, Gb gbR) {
    ninitDualGb(gbL.gb, gbR.gb);
  }

  private static native void nstep(long gb, int keymask);
	private static native int nstepUntil(long gb, int keymask, int[] addresses);
	private static native void nreset(long gb);
	public int step(int keymask, int... addresses){
		if ((keymask & 0x800) > 0) {
			nreset(gb);
			return 0;
		}
		if(addresses.length == 0) {
			nstep(gb, keymask);
			return 0;
		}
		return nstepUntil(gb, keymask, addresses);
	}

  private static native void nstepDual(long gbL, long gbR, int keymaskL, int keymaskR);
  private static native int nstepDualUntil(long gbL, long gbR, int keymaskL, int keymaskR, int[] addressesL, int[] addressesR);
  public static void stepDual(Gb gbL, Gb gbR, int keymaskL, int keymaskR) {
    nstepDual(gbL.gb, gbR.gb, keymaskL, keymaskR);
  }
  public static int stepDualUntilL(Gb gbL, Gb gbR, int keymaskL, int keymaskR, int... addresses) {
    return nstepDualUntil(gbL.gb, gbR.gb, keymaskL, keymaskR, addresses, new int[0]);
  }
  public static int stepDualUntilR(Gb gbL, Gb gbR, int keymaskL, int keymaskR, int... addresses) {
    return nstepDualUntil(gbL.gb, gbR.gb, keymaskL, keymaskR, new int[0], addresses);
  }

  private static native int saveState(long gb, ByteBuffer buffer, int size);
  private static native void loadState(long gb, ByteBuffer buffer, int size);

  private static native int saveDualState(long gbL, long gbR, ByteBuffer buffer, int size);
  private static native void loadDualState(long gbL, long gbR, ByteBuffer buffer, int size);

//  public static final int MAX_SAVE_SIZE = 211243;
//  public static final int MAX_SAVE_SIZE = 153894; // Tetris
//  public static final int MAX_SAVE_SIZE = 162092; // Sml2_10
//  private static final int MAX_SAVE_SIZE = 186675; // PokeRed
  private static final int MAX_SAVE_SIZE = 400000; // 2* PokeRed + buffer
	public static final ByteBuffer TMP_SAVE_BUFFER = createDirectByteBuffer(MAX_SAVE_SIZE);

	public ByteBuffer saveState() {
	  synchronized (TMP_SAVE_BUFFER) {
	    // read state to temporary buffer
	    TMP_SAVE_BUFFER.clear();
	    int size = saveState(gb, TMP_SAVE_BUFFER, TMP_SAVE_BUFFER.capacity());

	    // copy state to new buffer with exact size
	    ByteBuffer buf = createDirectByteBuffer(size);
	    TMP_SAVE_BUFFER.rewind();
	    TMP_SAVE_BUFFER.limit(size);
	    buf.put(TMP_SAVE_BUFFER);
	    buf.rewind();
	    return buf;
    }
	}

  public void loadState(ByteBuffer saveState){
    loadState(gb, saveState, saveState.capacity());
  }

  public static ByteBuffer saveDualState(Gb gbL, Gb gbR) {
    synchronized (TMP_SAVE_BUFFER) {
      // read state to temporary buffer
      TMP_SAVE_BUFFER.clear();
      int size = saveDualState(gbL.gb, gbR.gb, TMP_SAVE_BUFFER, TMP_SAVE_BUFFER.capacity());

      // copy state to new buffer with exact size
      ByteBuffer buf = createDirectByteBuffer(size);
      TMP_SAVE_BUFFER.rewind();
      TMP_SAVE_BUFFER.limit(size);
      buf.put(TMP_SAVE_BUFFER);
      buf.rewind();
      return buf;
    }
  }

  public static void loadDualState(Gb gbL, Gb gbR, ByteBuffer saveState){
    loadDualState(gbL.gb, gbR.gb, saveState, saveState.capacity());
  }

	private static native int getROMSize(long gb);
	public int getROMSize() {
	  return getROMSize(gb);
	}

	private static native void getROM(long gb, int[] store);
	public int[] getROM() {
	  int[] rom = new int[getROMSize()];
	  getROM(gb, rom);
	  return rom;
	}

  private static final int NUM_REGISTERS = 6;
	private static native void getRegisters(long gb, int[] store);
	public int[] getRegisters() {
	  int[] registers = new int[NUM_REGISTERS];
	  getRegisters(gb, registers);
	  return registers;
	}

	private static final int GB_MEMORY = 0x10000;
	private static native void getMemory(long gb, int[] store);
  public int[] getMemory() {
    int[] memory = new int[GB_MEMORY];
    getMemory(gb, memory);
    return memory;
  }

  private static native int readMemory(long gb, int address);
  public int readMemory(int address) {
    return readMemory(gb, address);
  }
	private static native void writeMemory(long gb, int address, int value);
	public void writeMemory(int address, int value) {
	  writeMemory(gb, address, value);
	}

	private static native int getDivState(long gb);
	public int getDivState() {
	  return getDivState(gb);
	}
	private static native void offsetDiv(long gb, int offset);
  public void offsetDiv(int offset) {
    offsetDiv(gb, offset);
  }


  public static void loadGambatte(int numScreens){
    System.loadLibrary("gambatte");
    initSdl(numScreens);
  }

  public static ByteBuffer createDirectByteBuffer(int capacity){
    return ByteBuffer.allocateDirect(capacity).order(ByteOrder.nativeOrder());
  }
}
